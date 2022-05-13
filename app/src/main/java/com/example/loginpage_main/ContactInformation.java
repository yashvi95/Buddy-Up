package com.example.loginpage_main;

public class ContactInformation {
    public String firstname, phonenumber, email, schedule,image;

    public ContactInformation(String firstname, String phonenumber, String email, String schedule, String image) {
        this.firstname = firstname;
        this.phonenumber = phonenumber;
        this.email = email;
        this.schedule = schedule;
        this.image = image;


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

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
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


}
