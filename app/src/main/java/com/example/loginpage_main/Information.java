package com.example.loginpage_main;

public class Information {
    private String firstname;
    private String lastname;
    private String username;
    private String gym;
    private String password;
    private String phonenumber;
    private String email;
    private String c1;
    private String c2;
    private String c3;
    private String schedule;
    private String image;

    public Information()
    {

    }

    public Information(String firstname, String lastname, String username, String gym, String schedule) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.username = username;
        this.gym = gym;
        this.schedule = schedule;
    }

    public Information(String firstname, String lastname, String username, String gym, String password, String phonenumber, String email, String c1, String c2, String c3, String schedule) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.username = username;
        this.gym = gym;
        this.password = password;
        this.phonenumber = phonenumber;
        this.email = email;
        this.c1 = c1;
        this.c2 = c2;
        this.c3 = c3;
        this.schedule = schedule;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getC1() {
        return c1;
    }

    public void setC1(String c1) {
        this.c1 = c1;
    }

    public String getC2() {
        return c2;
    }

    public void setC2(String c2) {
        this.c2 = c2;
    }

    public String getC3() {
        return c3;
    }

    public void setC3(String c3) {
        this.c3 = c3;
    }

    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }


}
