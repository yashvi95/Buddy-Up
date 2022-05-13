package com.example.loginpage_main;

import static org.junit.Assert.*;

import org.junit.Test;

public class SignUpTests {

    // Written by Spencer Ekin
    // May 12th, 2022

    // Username is Asmith1
    @Test
    public void userNameIsValid1() {
        User myTestObject = new User("Al","Smith","Asmith1","password","7077077707","buddyup@yahoo.com","WREC","Cardio","Yoga","Body Building","Morning","imagestring");
        assertTrue(myTestObject.userNameIsValid());
    }

    // Username is 123456789012345678901234, exactly 24 chars, which is upper limit
    @Test
    public void userNameIsValid2() {
        User myTestObject = new User("Al","Smith","123456789012345678901234","password","7077077707","buddyup@yahoo.com","WREC","Cardio","Yoga","Body Building","Morning","imagestring");
        assertTrue(myTestObject.userNameIsValid());
    }

    // Username is 123, exactly 3 chars, which is lower limit
    @Test
    public void userNameIsValid3() {
        User myTestObject = new User("Al","Smith","123","password","7077077707","buddyup@yahoo.com","WREC","Cardio","Yoga","Body Building","Morning","imagestring");
        assertTrue(myTestObject.userNameIsValid());
    }

    // Username is Empty: ""
    @Test
    public void userNameIsInvalid1() {
        User myTestObject = new User("Al","Smith","","password","7077077707","buddyup@yahoo.com","WREC","Cardio","Yoga","Body Building","Morning","imagestring");
        assertFalse(myTestObject.userNameIsValid());
    }

    // Username is 12, exactly 2 chars, which is one under the lower limit
    @Test
    public void userNameIsInvalid2() {
        User myTestObject = new User("Al","Smith","12","password","7077077707","buddyup@yahoo.com","WREC","Cardio","Yoga","Body Building","Morning","imagestring");
        assertFalse(myTestObject.userNameIsValid());
    }

    // Username is 1234567890123456789012345, exactly 25 chars, which is one past the upper limit
    @Test
    public void userNameIsInvalid3() {
        User myTestObject = new User("Al","Smith","1234567890123456789012345","password","7077077707","buddyup@yahoo.com","WREC","Cardio","Yoga","Body Building","Morning","imagestring");
        assertFalse(myTestObject.userNameIsValid());
    }

    // Firstname is Albert
    @Test
    public void firstNameIsValid1() {
        User myTestObject = new User("Albert","Smith","Asmith1","password","7077077707","buddyup@yahoo.com","WREC","Cardio","Yoga","Body Building","Morning","imagestring");
        assertTrue(myTestObject.firstNameIsValid());
    }

    // Firstname is aaaaaaaaaaaaaaaaaaaaaaaa, 24 chars, exactly upper limit
    @Test
    public void firstNameIsValid2() {
        User myTestObject = new User("aaaaaaaaaaaaaaaaaaaaaaaa","Smith","Asmith1","password","7077077707","buddyup@yahoo.com","WREC","Cardio","Yoga","Body Building","Morning","imagestring");
        assertTrue(myTestObject.firstNameIsValid());
    }

    // Firstname is Al, 2 chars, exactly lower limit
    @Test
    public void firstNameIsValid3() {
        User myTestObject = new User("Al","Smith","Asmith1","password","7077077707","buddyup@yahoo.com","WREC","Cardio","Yoga","Body Building","Morning","imagestring");
        assertTrue(myTestObject.firstNameIsValid());
    }

    // Firstname is A, 1 char, one past lower limit
    @Test
    public void firstNameIsInvalid1() {
        User myTestObject = new User("A","Smith","Asmith1","password","7077077707","buddyup@yahoo.com","WREC","Cardio","Yoga","Body Building","Morning","imagestring");
        assertFalse(myTestObject.firstNameIsValid());
    }

    // Firstname is aaaaaaaaaaaaaaaaaaaaaaaaa, exactly 25 chars, 1 past upper limit
    @Test
    public void firstNameIsInvalid2() {
        User myTestObject = new User("aaaaaaaaaaaaaaaaaaaaaaaaa","Smith","Asmith1","password","7077077707","buddyup@yahoo.com","WREC","Cardio","Yoga","Body Building","Morning","imagestring");
        assertFalse(myTestObject.firstNameIsValid());
    }

    // Firstname is Empty ""
    @Test
    public void firstNameIsInvalid3() {
        User myTestObject = new User("","Smith","Asmith1","password","7077077707","buddyup@yahoo.com","WREC","Cardio","Yoga","Body Building","Morning","imagestring");
        assertFalse(myTestObject.firstNameIsValid());
    }

    // Firstname is Albert1, string may only contain alphabet chars
    @Test
    public void firstNameIsInvalid4() {
        User myTestObject = new User("Albert1","Smith","Asmith1","password","7077077707","buddyup@yahoo.com","WREC","Cardio","Yoga","Body Building","Morning","imagestring");
        assertFalse(myTestObject.firstNameIsValid());
    }

    // Lastname is Smith
    @Test
    public void lastNameIsValid1() {
        User myTestObject = new User("Albert","Smith","Asmith1","password","7077077707","buddyup@yahoo.com","WREC","Cardio","Yoga","Body Building","Morning","imagestring");
        assertTrue(myTestObject.lastNameIsValid());
    }

    // Lastname is S
    @Test
    public void lastNameIsValid2() {
        User myTestObject = new User("Albert","S","Asmith1","password","7077077707","buddyup@yahoo.com","WREC","Cardio","Yoga","Body Building","Morning","imagestring");
        assertTrue(myTestObject.lastNameIsValid());
    }

    // Lastname is aaaaaaaaaaaaaaaaaaaaaaaa, exactly 24 chars, upper limit
    @Test
    public void lastNameIsValid3() {
        User myTestObject = new User("Albert","aaaaaaaaaaaaaaaaaaaaaaaa","Asmith1","password","7077077707","buddyup@yahoo.com","WREC","Cardio","Yoga","Body Building","Morning","imagestring");
        assertTrue(myTestObject.lastNameIsValid());
    }

    // Lastname is Empty ""
    @Test
    public void lastNameIsInvalid1() {
        User myTestObject = new User("Albert","","Asmith1","password","7077077707","buddyup@yahoo.com","WREC","Cardio","Yoga","Body Building","Morning","imagestring");
        assertFalse(myTestObject.lastNameIsValid());
    }

    // Lastname is 1234567890123456789012345, exactly 25 chars, 1 past upper limit
    @Test
    public void lastNameIsInvalid2() {
        User myTestObject = new User("Albert","1234567890123456789012345","Asmith1","password","7077077707","buddyup@yahoo.com","WREC","Cardio","Yoga","Body Building","Morning","imagestring");
        assertFalse(myTestObject.lastNameIsValid());
    }

    // Lastname is Bobby12, only lowercase and uppercase letters allowed
    @Test
    public void lastNameIsInvalid3() {
        User myTestObject = new User("Albert","Bobby12","Asmith1","password","7077077707","buddyup@yahoo.com","WREC","Cardio","Yoga","Body Building","Morning","imagestring");
        assertFalse(myTestObject.lastNameIsValid());
    }

    // Phone number is 12345678901, exactly 11 chars, upper limit
    @Test
    public void phoneNumberIsValid1() {
        User myTestObject = new User("Albert","Smith","Asmith1","password","12345678901","buddyup@yahoo.com","WREC","Cardio","Yoga","Body Building","Morning","imagestring");
        assertTrue(myTestObject.phoneNumberIsValid());
    }

    // Phone number is 1234567890, exactly 10 chars, lower limit
    @Test
    public void phoneNumberIsValid2() {
        User myTestObject = new User("Albert","Smith","Asmith1","password","1234567890","buddyup@yahoo.com","WREC","Cardio","Yoga","Body Building","Morning","imagestring");
        assertTrue(myTestObject.phoneNumberIsValid());
    }

    // Phone number is 123456789012, exactly 12 chars, 1 past upper limit
    @Test
    public void phoneNumberIsInvalid1() {
        User myTestObject = new User("Albert","Smith","Asmith1","password","123456789012","buddyup@yahoo.com","WREC","Cardio","Yoga","Body Building","Morning","imagestring");
        assertFalse(myTestObject.phoneNumberIsValid());
    }

    // Phone number is 123456789, exactly 9 chars, 1 under lower limit
    @Test
    public void phoneNumberIsInvalid2() {
        User myTestObject = new User("Albert","Smith","Asmith1","password","123456789","buddyup@yahoo.com","WREC","Cardio","Yoga","Body Building","Morning","imagestring");
        assertFalse(myTestObject.phoneNumberIsValid());
    }

    // Phone number is 123456789a, 10 chars, but contains a letter
    @Test
    public void phoneNumberIsInvalid3() {
        User myTestObject = new User("Albert","Smith","Asmith1","password","123456789a","buddyup@yahoo.com","WREC","Cardio","Yoga","Body Building","Morning","imagestring");
        assertFalse(myTestObject.phoneNumberIsValid());
    }

    // Gym is WREC
    @Test
    public void gymIsValid1() {
        User myTestObject = new User("Albert","Smith","Asmith1","password","12345678901","buddyup@yahoo.com","WREC","Cardio","Yoga","Body Building","Morning","imagestring");
        assertTrue(myTestObject.gymIsValid());
    }

    // Gym is ABC, 3 chars, lower limit
    @Test
    public void gymIsValid2() {
        User myTestObject = new User("Albert","Smith","Asmith1","password","12345678901","buddyup@yahoo.com","ABC","Cardio","Yoga","Body Building","Morning","imagestring");
        assertTrue(myTestObject.gymIsValid());
    }

    // Gym is 123456789012345678901234, exactly 24 chars, upper limit
    @Test
    public void gymIsValid3() {
        User myTestObject = new User("Albert","Smith","Asmith1","password","12345678901","buddyup@yahoo.com","123456789012345678901234","Cardio","Yoga","Body Building","Morning","imagestring");
        assertTrue(myTestObject.gymIsValid());
    }

    // Gym is AB, 2 chars, 1 under lower limit
    @Test
    public void gymIsInvalid1() {
        User myTestObject = new User("Albert","Smith","Asmith1","password","12345678901","buddyup@yahoo.com","AB","Cardio","Yoga","Body Building","Morning","imagestring");
        assertFalse(myTestObject.gymIsValid());
    }

    // Gym is 1234567890123456789012345, 25 chars, 1 past upper limit
    @Test
    public void gymIsInvalid2() {
        User myTestObject = new User("Albert","Smith","Asmith1","password","12345678901","buddyup@yahoo.com","1234567890123456789012345","Cardio","Yoga","Body Building","Morning","imagestring");
        assertFalse(myTestObject.gymIsValid());
    }

    // Gym is Empty ""
    @Test
    public void gymIsInvalid3() {
        User myTestObject = new User("Albert","Smith","Asmith1","password","12345678901","buddyup@yahoo.com","","Cardio","Yoga","Body Building","Morning","imagestring");
        assertFalse(myTestObject.gymIsValid());
    }

    // c1 is Cardio
    @Test
    public void c1IsValid1() {
        User myTestObject = new User("Albert","Smith","Asmith1","password","12345678901","buddyup@yahoo.com","WREC","Cardio","Yoga","Body Building","Morning","imagestring");
        assertTrue(myTestObject.c1IsValid());
    }

    // c1 is Body Building
    @Test
    public void c1IsValid2() {
        User myTestObject = new User("Albert","Smith","Asmith1","password","12345678901","buddyup@yahoo.com","WREC","Body Building","Yoga","Body Building","Morning","imagestring");
        assertTrue(myTestObject.c1IsValid());
    }

    // c1 is Strength Training
    @Test
    public void c1IsValid3() {
        User myTestObject = new User("Albert","Smith","Asmith1","password","12345678901","buddyup@yahoo.com","WREC","Strength Training","Yoga","Body Building","Morning","imagestring");
        assertTrue(myTestObject.c1IsValid());
    }

    // c1 is Weight Loss
    @Test
    public void c1IsValid4() {
        User myTestObject = new User("Albert","Smith","Asmith1","password","12345678901","buddyup@yahoo.com","WREC","Weight Loss","Yoga","Body Building","Morning","imagestring");
        assertTrue(myTestObject.c1IsValid());
    }

    // c1 is Yoga
    @Test
    public void c1IsValid5() {
        User myTestObject = new User("Albert","Smith","Asmith1","password","12345678901","buddyup@yahoo.com","WREC","Yoga","Yoga","Body Building","Morning","imagestring");
        assertTrue(myTestObject.c1IsValid());
    }

    // c1 is Outdoor Activities
    @Test
    public void c1IsValid6() {
        User myTestObject = new User("Albert","Smith","Asmith1","password","12345678901","buddyup@yahoo.com","WREC","Outdoor Activities","Yoga","Body Building","Morning","imagestring");
        assertTrue(myTestObject.c1IsValid());
    }

    // c1 is Choose a Category
    @Test
    public void c1IsInvalid1() {
        User myTestObject = new User("Albert","Smith","Asmith1","password","12345678901","buddyup@yahoo.com","WREC","Choose a Category","Yoga","Body Building","Morning","imagestring");
        assertFalse(myTestObject.c1IsValid());
    }

    // c1 is Empty
    @Test
    public void c1IsInvalid2() {
        User myTestObject = new User("Albert","Smith","Asmith1","password","12345678901","buddyup@yahoo.com","WREC","","Yoga","Body Building","Morning","imagestring");
        assertFalse(myTestObject.c1IsValid());
    }

    // c1 is Make Friends
    @Test
    public void c1IsInvalid3() {
        User myTestObject = new User("Albert","Smith","Asmith1","password","12345678901","buddyup@yahoo.com","WREC","Make Friends","Yoga","Body Building","Morning","imagestring");
        assertFalse(myTestObject.c1IsValid());
    }

    // c1 is Literally Anything
    @Test
    public void c1IsInvalid4() {
        User myTestObject = new User("Albert","Smith","Asmith1","password","12345678901","buddyup@yahoo.com","WREC","Literally Anything","Yoga","Body Building","Morning","imagestring");
        assertFalse(myTestObject.c1IsValid());
    }

    // Schedule is Morning
    @Test
    public void scheduleIsValid1() {
        User myTestObject = new User("Albert","Smith","Asmith1","password","12345678901","buddyup@yahoo.com","WREC","Cardio","Yoga","Body Building","Morning","imagestring");
        assertTrue(myTestObject.scheduleIsValid());
    }

    // Schedule is Afternoon
    @Test
    public void scheduleIsValid2() {
        User myTestObject = new User("Albert","Smith","Asmith1","password","12345678901","buddyup@yahoo.com","WREC","Cardio","Yoga","Body Building","Afternoon","imagestring");
        assertTrue(myTestObject.scheduleIsValid());
    }

    // Schedule is Evening
    @Test
    public void scheduleIsValid3() {
        User myTestObject = new User("Albert","Smith","Asmith1","password","12345678901","buddyup@yahoo.com","WREC","Cardio","Yoga","Body Building","Evening","imagestring");
        assertTrue(myTestObject.scheduleIsValid());
    }

    // Schedule is Flexible
    @Test
    public void scheduleIsValid4() {
        User myTestObject = new User("Albert","Smith","Asmith1","password","12345678901","buddyup@yahoo.com","WREC","Cardio","Yoga","Body Building","Flexible","imagestring");
        assertTrue(myTestObject.scheduleIsValid());
    }

    // Schedule is Anytime
    @Test
    public void scheduleIsInvalid1() {
        User myTestObject = new User("Albert","Smith","Asmith1","password","12345678901","buddyup@yahoo.com","WREC","Cardio","Yoga","Body Building","Anytime","imagestring");
        assertFalse(myTestObject.scheduleIsValid());
    }

    // Schedule is Choose a time that works best for you!
    @Test
    public void scheduleIsInvalid2() {
        User myTestObject = new User("Albert","Smith","Asmith1","password","12345678901","buddyup@yahoo.com","WREC","Cardio","Yoga","Body Building","Choose a time that works best for you!","imagestring");
        assertFalse(myTestObject.scheduleIsValid());
    }

    // Schedule is Empty
    @Test
    public void scheduleIsInvalid3() {
        User myTestObject = new User("Albert","Smith","Asmith1","password","12345678901","buddyup@yahoo.com","WREC","Cardio","Yoga","Body Building","","imagestring");
        assertFalse(myTestObject.scheduleIsValid());
    }

}