package com.learn.travellingapp.service;

import com.learn.travellingapp.model.Journey;
import com.learn.travellingapp.model.JourneyRequest;
import com.learn.travellingapp.model.TravellingAPPException;
import com.learn.travellingapp.model.User;

import java.util.List;

public interface JourneyService {

    void addJourney(Journey journey, String rootDirectory) throws TravellingAPPException;

    List<Journey> findJourneys(String rootDirectory) throws TravellingAPPException;

    void sendJoinRequest(User sender, String journeyId, String comment, String rootDirectory) throws TravellingAPPException;

    List<JourneyRequest> getAllJourneyRequests(User user, String journeyId, String rootDirectory) throws TravellingAPPException;
}
