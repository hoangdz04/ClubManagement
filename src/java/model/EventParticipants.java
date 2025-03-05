/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author A
 */
public class EventParticipants {
    private int eventParticipantId;
    private int eventId;
    private int userId;
    private int statusId;

    public EventParticipants() {
    }

    public EventParticipants(int eventParticipantId, int eventId, int userId, int statusId) {
        this.eventParticipantId = eventParticipantId;
        this.eventId = eventId;
        this.userId = userId;
        this.statusId = statusId;
    }

    public int getEventParticipantId() {
        return eventParticipantId;
    }

    public void setEventParticipantId(int eventParticipantId) {
        this.eventParticipantId = eventParticipantId;
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    @Override
    public String toString() {
        return "EventParticipants{" + "eventParticipantId=" + eventParticipantId + ", eventId=" + eventId + ", userId=" + userId + ", statusId=" + statusId + '}';
    }
    
    
}
