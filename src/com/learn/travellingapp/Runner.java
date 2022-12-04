package com.learn.travellingapp;

import com.learn.travellingapp.model.*;
import com.learn.travellingapp.service.JourneyService;
import com.learn.travellingapp.service.JourneyServiceImpl;
import com.learn.travellingapp.service.UserService;
import com.learn.travellingapp.service.UserServiceImpl;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class Runner {

    private static Scanner scanner = new Scanner(System.in);

    private static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static String rootDirectory;

    private static UserService userService = new UserServiceImpl();
    private static JourneyService journeyService = new JourneyServiceImpl();

    public static void main(String[] args) {
        rootDirectory = args[0];

        System.out.println("-----------Welcome to Travelling APP-----------");
        while (true) {
            System.out.println("1. Log in");
            System.out.println("2. Register");
            System.out.println("0. Exit");
            int mode = Integer.parseInt(scanner.nextLine());
            if (mode == 1) {
                logInMode();
            } else if (mode == 2) {
                registerMode();
            } else if (mode == 0) {
                break;
            }
        }
    }

    private static void logInMode() {
        System.out.println("Please enter your personal ID");
        String personalId = scanner.nextLine();
        try {
            User user = userService.logIn(personalId, rootDirectory);
            System.out.println("Hello " + user.getName());

            while (true) {
                System.out.println("1. Add journey");
                System.out.println("2. Find journey");
                System.out.println("3. Send join request");
                System.out.println("4. Show active requests");
                System.out.println("0. Exit");
                int mode = Integer.parseInt(scanner.nextLine());
                if (mode == 1) {
                    addJourneyMode(user);
                } else if (mode == 2) {
                    findJourneyMode();
                } else if (mode == 3) {
                    sendJoinRequest(user);
                } else if (mode == 4) {
                    showActiveRequests(user);
                } else if (mode == 0) {
                    break;
                }
            }
        } catch (TravellingAPPException ex) {
            System.out.println("<<Error>> " + ex.getMessage());
        }
    }

    private static void addJourneyMode(User user) {
        String uniqueID = UUID.randomUUID().toString();
        System.out.println("Please enter location");
        String location = scanner.nextLine();
        System.out.println("Please enter destination");
        String destination = scanner.nextLine();
        System.out.println("Please enter date(please use dd/MM/yyyy format)");
        LocalDate date = LocalDate.parse(scanner.nextLine(), dateTimeFormatter);
        System.out.println("Please enter journey type(FREE/SPLIT_EXPENSE)");
        JourneyType journeyType = JourneyType.valueOf(scanner.nextLine());
        System.out.println("Please enter description");
        String description = scanner.nextLine();
        Journey journey = new Journey(uniqueID, user, location, destination, date, journeyType, description);
        System.out.println("Journey created successfully");
        try {
            journeyService.addJourney(journey, rootDirectory);
        } catch (TravellingAPPException ex) {
            System.out.println("<<Error>> " + ex.getMessage());
        }
    }

    private static void findJourneyMode() {
        try {
            List<Journey> journeys = journeyService.findJourneys(rootDirectory);
            for (Journey journey : journeys) {
                System.out.println("-------------Journey-------------");
                System.out.println(journey);
                System.out.println("---------------------------------");
            }
        } catch (TravellingAPPException ex) {
            System.out.println("<<Error>> " + ex.getMessage());
        }
    }

    private static void sendJoinRequest(User user) {
        System.out.println("Please enter ID of journey(You can find IDs in Find journey mode)");
        String journeyId = scanner.nextLine();
        System.out.println("Please enter any comment");
        String comment = scanner.nextLine();
        try {
            journeyService.sendJoinRequest(user, journeyId, comment, rootDirectory);
        } catch (TravellingAPPException ex) {
            System.out.println("<<Error>> " + ex.getMessage());
        }
    }

    private static void showActiveRequests(User user) {
        System.out.println("Please enter ID of journey(You can find IDs in Find journey mode)");
        String journeyId = scanner.nextLine();
        try {
            List<JourneyRequest> requests = journeyService.getAllJourneyRequests(user, journeyId, rootDirectory);
            for (JourneyRequest request : requests) {
                System.out.println("-------------Request-------------");
                System.out.println(request);
                System.out.println("---------------------------------");
            }
        } catch (TravellingAPPException ex) {
            System.out.println("<<Error>> " + ex.getMessage());
        }
    }

    private static void registerMode() {
        System.out.println("Please enter personal ID");
        String personalId = scanner.nextLine();
        System.out.println("Please enter name");
        String name = scanner.nextLine();
        System.out.println("Please enter email");
        String email = scanner.nextLine();
        User user = new User(personalId, name, email);
        try {
            userService.addUser(user, rootDirectory);
            System.out.println("User created successfully");
        } catch (TravellingAPPException ex) {
            System.out.println("<<Error>> " + ex.getMessage());
        }
    }
}
