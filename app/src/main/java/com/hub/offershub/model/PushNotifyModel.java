package com.hub.offershub.model;

public class PushNotifyModel {
    public String title;
    public String message;
    public String channelID;

    public PushNotifyModel(String title, String message, String channelID) {
        this.title = title;
        this.message = message;
        this.channelID = channelID;
    }
}
