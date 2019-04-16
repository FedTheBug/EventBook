package com.example.eventbook.models;

import java.io.Serializable;

/**
 *
 * Model class for EVENTS
 * that stores all information
 * and have GETTER, SETTER
 *
 */

public class Event implements Serializable {

    private int id;
    private String name;
    private String venue;
    private String event_date;
    private String reg_deadline;
    private String description;
    private int organizer_id;

    public Event(int id, String name, String venue, String event_date, String reg_deadline,
                       String description, int organizer_id) {
        this.id = id;
        this.name = name;
        this.venue = venue;
        this.event_date = event_date;
        this.reg_deadline = reg_deadline;
        this.description = description;
        this.organizer_id = organizer_id;

    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getOrganizer_id() {
        return organizer_id;
    }

    public void setOrganizer_id(int organizer_id) {
        this.organizer_id = organizer_id;
    }

    public Event() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public String getEvent_date() {
        return event_date;
    }

    public void setEvent_date(String event_date) {
        this.event_date = event_date;
    }

    public String getReg_deadline() {
        return reg_deadline;
    }

    public void setReg_deadline(String reg_deadline) {
        this.reg_deadline = reg_deadline;
    }

    @Override
    public String toString() {
        return "Name: " + name + "\nVenue: " + venue +"\nEvent Date: " +
                event_date + "\nRegistration Deadline: " + reg_deadline;
    }
}
