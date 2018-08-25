package com.globallogic.gllambdademo.message;

import java.io.Serializable;
import java.util.Map;

public class NotificationMessage implements Serializable {

    private String notificationType;
    private String email;
    private Map<String, String> payload;

    public String getNotificationType() {
        return notificationType;
    }

    public void setNotificationType(String notificationType) {
        this.notificationType = notificationType;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Map<String, String> getPayload() {
        return payload;
    }

    public void setPayload(Map<String, String> payload) {
        this.payload = payload;
    }
}