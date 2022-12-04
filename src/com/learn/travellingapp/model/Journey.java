package com.learn.travellingapp.model;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Journey implements Serializable {

    @Serial
    private static final long serialVersionUID = 1001L;

    private String id;
    private User owner;
    private String location;
    private String destination;
    private LocalDate date;
    private JourneyType type;
    private String description;
    private List<JourneyRequest> requests = new ArrayList<>();

    public Journey() {
    }

    public Journey(String id, User owner, String location, String destination, LocalDate date, JourneyType type, String description) {
        this.id = id;
        this.owner = owner;
        this.location = location;
        this.destination = destination;
        this.date = date;
        this.type = type;
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public JourneyType getType() {
        return type;
    }

    public void setType(JourneyType type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<JourneyRequest> getRequests() {
        return requests;
    }

    @Override
    public String toString() {
        return "ID: " + id + System.lineSeparator() +
                "Owner: " + owner.getName() + System.lineSeparator() +
                "Location: " + location + System.lineSeparator() +
                "Destination: " + destination + System.lineSeparator() +
                "Date: " + date + System.lineSeparator() +
                "Type: " + type + System.lineSeparator() +
                "Description: " + description;
    }
}
