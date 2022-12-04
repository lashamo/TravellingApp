package com.learn.travellingapp.service;

import com.learn.travellingapp.model.TravellingAPPException;
import com.learn.travellingapp.model.User;

import java.io.*;

public class UserServiceImpl implements UserService {

    @Override
    public void addUser(User user, String rootDirectory) throws TravellingAPPException {
        File directory = new File(rootDirectory + "\\" + user.getPersonalId());
        if (directory.exists()) {
            throw new TravellingAPPException(String.format("User with personal ID %s already exists", user.getPersonalId()));
        } else {
            directory.mkdir();

            File journeysDirectory = new File(rootDirectory + "\\" + user.getPersonalId() + "\\journeys");
            journeysDirectory.mkdir();

            File file = new File(rootDirectory + "\\" + user.getPersonalId() + "\\info.txt");
            try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file))) {
                bufferedWriter.write(user.getPersonalId() + System.lineSeparator());
                bufferedWriter.write(user.getName() + System.lineSeparator());
                bufferedWriter.write(user.getEmail());
            } catch (IOException ex) {
                throw new TravellingAPPException("Error occurred while create the user: " + ex.getMessage());
            }
        }
    }

    @Override
    public User logIn(String personalId, String rootDirectory) throws TravellingAPPException {
        File directory = new File(rootDirectory + "\\" + personalId);
        if (directory.exists()) {
            File file = new File(rootDirectory + "\\" + personalId + "\\info.txt");
            try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
                String id = bufferedReader.readLine();
                String name = bufferedReader.readLine();
                String email = bufferedReader.readLine();
                return new User(id, name, email);
            } catch (IOException ex) {
                throw new TravellingAPPException("Error occurred while read the user: " + ex.getMessage());
            }
        } else {
            throw new TravellingAPPException(String.format("Use with personal ID %s does not exist", personalId));
        }
    }
}
