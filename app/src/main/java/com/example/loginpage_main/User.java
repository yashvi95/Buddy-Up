package com.example.loginpage_main;

import android.util.Patterns;

import androidx.core.util.PatternsCompat;

import java.util.regex.Pattern;

public class User {
    public String firstname, lastname, username, password, phonenumber, email, gym,c1,c2,c3,schedule,image;

    public User(String firstname, String lastname, String username, String password, String phonenumber, String email, String gym, String c1, String c2, String c3, String schedule, String image) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.username = username;
        this.password = password;
        this.phonenumber = phonenumber;
        this.email = email;
        this.gym = gym;
        this.c1 = c1;
        this.c2 = c2;
        this.c3 = c3;
        this.schedule = schedule;
        this.image = image;

    }

    public User(String uName, String pWord) {
        this.email = uName;
        this.password = pWord;
    }

    // This functions checks to see if the username, aka email, is of valid format
    public boolean emailIsValid()
    {
        if (email.isEmpty()) {
            return false;
        }

        return PatternsCompat.EMAIL_ADDRESS.matcher(email).matches();
    }

    // This function checks to see if the password is of valid format
    public boolean passwordIsValid()
    {
        if (password.length() < 6 || password.length() > 24) {
            return false;
        }
        return true;
    }

    public boolean userNameIsValid()
    {
        if (username.length() < 3 || username.length() > 24) {
            return false;
        }
        return true;
    }

    public boolean firstNameIsValid()
    {
        if (firstname.length() < 2 || firstname.length() > 24) {
            return false;
        }
        return true;
    }

    public boolean lastNameIsValid()
    {
        if (lastname.isEmpty() || lastname.length() > 24) {
            return false;
        }
        return true;
    }

    public boolean phoneNumberIsValid()
    {
        // Implement if the strings contains anything but numbers return false
        for (int i = 0; i < phonenumber.length(); ++i) {

            if (phonenumber.charAt(i) < 48 || phonenumber.charAt(i) > 57) {
                return false;
            }
        }

        if (phonenumber.length() == 10 || phonenumber.length() == 11) {
            return true;
        }
        return false;
    }

    public boolean gymIsValid()
    {
        if (gym.length() < 3 || gym.length() > 24) {
            return false;
        }
        return true;
    }

    public boolean c1IsValid()
    {
        if (c1 == "Choose a Category" || c1 != "Body Building" && c1 != "Strength Training" && c1 != "Weight Loss" && c1 != "Yoga" && c1 != "Cardio" && c1 != "Outdoor Activities") {
            return false;
        }
        return true;
    }

    public boolean scheduleIsValid()
    {
        if (schedule == "Choose a time that works best for you!" || schedule != "Morning" && schedule != "Afternoon" && schedule != "Evening" && schedule != "Flexible") {
            return false;
        }
        return true;
    }

    public boolean imageIsValid()
    {
        if (image.isEmpty()) {
            return false;
        }
        return true;
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

    public String getGym() {
        return gym;
    }

    public void setGym(String gym) {
        this.gym = gym;
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
}

