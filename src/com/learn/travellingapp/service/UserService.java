package com.learn.travellingapp.service;

import com.learn.travellingapp.model.TravellingAPPException;
import com.learn.travellingapp.model.User;

public interface UserService {

    void addUser(User user, String rootDirectory) throws TravellingAPPException;

    User logIn(String personalId, String rootDirectory) throws TravellingAPPException;
}
