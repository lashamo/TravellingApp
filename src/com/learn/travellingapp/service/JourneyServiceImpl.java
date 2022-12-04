package com.learn.travellingapp.service;

import com.learn.travellingapp.model.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class JourneyServiceImpl implements JourneyService {

    @Override
    public void addJourney(Journey journey, String rootDirectory) throws TravellingAPPException {
        File journeyFile = new File(rootDirectory + "\\" + journey.getOwner().getPersonalId() +
                "\\journeys\\" + journey.getId()+".txt");
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(journeyFile))) {
            out.writeObject(journey);
        } catch (IOException ex) {
            throw new TravellingAPPException("Error occurred while create the journey: " + ex.getMessage());
        }
    }

    @Override
    public List<Journey> findJourneys(String rootDirectory) throws TravellingAPPException {
        List<Journey> journeys = new ArrayList<>();

        File root = new File(rootDirectory);
        for (File userDirectory : root.listFiles()) {
            if (userDirectory.isDirectory()) {
                File journeysDirectory = new File(rootDirectory + "\\" + userDirectory.getName() + "\\journeys");
                for (File file : journeysDirectory.listFiles()) {
                    try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))) {
                        Journey journey = (Journey) in.readObject();
                        journeys.add(journey);
                    } catch (IOException | ClassNotFoundException ex) {
                        throw new TravellingAPPException("Error occurred while read the journey: " + ex.getMessage());
                    }
                }
            }
        }

        return journeys;
    }

    @Override
    public void sendJoinRequest(User sender, String journeyId, String comment, String rootDirectory) throws TravellingAPPException {
        // Read the journey
        File root = new File(rootDirectory);
        Journey journey = null;
        for (File userDirectory : root.listFiles()) {
            File file = new File(rootDirectory + "\\" + userDirectory.getName() + "\\journeys\\" +
                    journeyId + ".txt");
            if (file.exists()) {
                try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))) {
                    journey = (Journey) in.readObject();
                    break;
                } catch (IOException | ClassNotFoundException ex) {
                    throw new TravellingAPPException("Error occurred while read the journey: " + ex.getMessage());
                }
            }
        }

        // Add request in journey
        if (journey == null) {
            throw new TravellingAPPException("Can't find the journey with id: " + journeyId);
        }
        JourneyRequest journeyRequest = new JourneyRequest(sender, comment, JourneyRequestStatus.ACTIVE);
        journey.getRequests().add(journeyRequest);

        // Save the journey
        addJourney(journey, rootDirectory);
    }

    @Override
    public List<JourneyRequest> getAllJourneyRequests(User user, String journeyId, String rootDirectory) throws TravellingAPPException {
        File file = new File(rootDirectory + "\\" + user.getPersonalId() + "\\journeys\\" + journeyId + ".txt");
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))) {
            Journey journey = (Journey) in.readObject();
            return journey.getRequests();
        } catch (IOException | ClassNotFoundException ex) {
            throw new TravellingAPPException("Error occurred while read the journey: " + ex.getMessage());
        }
    }
}
