package com.learn.travellingapp.model;

import java.io.Serial;
import java.io.Serializable;

public class JourneyRequest implements Serializable {

    @Serial
    private static final long serialVersionUID = 1002L;

    private User sender;
    private String comment;
    private JourneyRequestStatus status;

    public JourneyRequest() {
    }

    public JourneyRequest(User sender, String comment, JourneyRequestStatus status) {
        this.sender = sender;
        this.comment = comment;
        this.status = status;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public JourneyRequestStatus getStatus() {
        return status;
    }

    public void setStatus(JourneyRequestStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Sender: " + sender.getName() + System.lineSeparator() +
                "Comment: " + comment + System.lineSeparator() +
                "Status: " + status;
    }
}
