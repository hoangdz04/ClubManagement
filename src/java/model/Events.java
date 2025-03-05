/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Date;

/**
 *
 * @author A
 */
public class Events {
    private int eventId;
    private String eventName;
    private String description;
    private Date eventDate;
    private String location;
    private int clubId;

    public Events() {
    }

    public Events(int eventId, String eventName, String description, Date eventDate, String location, int clubId) {
        this.eventId = eventId;
        this.eventName = eventName;
        this.description = description;
        this.eventDate = eventDate;
        this.location = location;
        this.clubId = clubId;
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getEventDate() {
        return eventDate;
    }

    public void setEventDate(Date eventDate) {
        this.eventDate = eventDate;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getClubId() {
        return clubId;
    }

    public void setClubId(int clubId) {
        this.clubId = clubId;
    }

    @Override
    public String toString() {
        return "Events{" + "eventId=" + eventId + ", eventName=" + eventName + ", description=" + description + ", eventDate=" + eventDate + ", location=" + location + ", clubId=" + clubId + '}';
    }
    
    
}
