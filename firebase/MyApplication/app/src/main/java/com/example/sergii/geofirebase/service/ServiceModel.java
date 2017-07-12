package com.example.sergii.geofirebase.service;

/**
 * Created by sergii on 11.07.17.
 */

public class ServiceModel {
    private final String emailAddress;

    public ServiceModel(String emailAddress){
        this.emailAddress = emailAddress;
    }

    public String getEmailAddress() {
        return emailAddress;
    }
}
