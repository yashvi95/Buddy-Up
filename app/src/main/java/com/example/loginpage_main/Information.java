package com.example.loginpage_main;

import java.lang.reflect.Constructor;

public class Information {
    private String firstname;
    private String lastname;
    private String username;
    private String gym;

    public Information()
    {

    }

    public Information(String firstname, String lastname, String username, String gym) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.username = username;
        this.gym = gym;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getGym() {
        return gym;
    }

    public void setGym(String gym) {
        this.gym = gym;
    }
}
