package com.globallogic.gllambdademo.dto;

public class SpeakerDTO {

    private String name;
    private String email;

    public String getName() {
        return name;
    }

    public SpeakerDTO setName(String name) {
        this.name = name;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public SpeakerDTO setEmail(String email) {
        this.email = email;
        return this;
    }
}
