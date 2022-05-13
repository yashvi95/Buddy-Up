package com.example.loginpage_main;

import static org.junit.Assert.*;

import org.junit.Test;

public class LogInTests {

    // Written by Spencer Ekin
    // May 11, 2022

    // Email is buddyup@yahoo.com
    @Test
    public void emailIsValid1() {
        User myTestObject = new User("buddyup@yahoo.com","password");
        assertTrue(myTestObject.emailIsValid());
    }

    // Email is 0123456789@gmail.com
    @Test
    public void emailIsValid2() {
        User myTestObject = new User("0123456789@gmail.com","password");
        assertTrue(myTestObject.emailIsValid());
    }

    // Email is a@aol.com
    @Test
    public void emailIsValid3() {
        User myTestObject = new User("a@aol.com","password");
        assertTrue(myTestObject.emailIsValid());
    }

    // Email is buddyup.com
    @Test
    public void emailIsInvalid1() {
        User myTestObject = new User("buddyup.com","password");
        assertFalse(myTestObject.emailIsValid());
    }

    // Email is @gmail.com
    @Test
    public void emailIsInvalid2() {
        User myTestObject = new User("@gmail.com","password");
        assertFalse(myTestObject.emailIsValid());
    }

    // Email is empty string: ""
    @Test
    public void emailIsInvalid3() {
        User myTestObject = new User("","password");
        assertFalse(myTestObject.emailIsValid());
    }

    // Email is buddyup
    @Test
    public void emailIsInvalid4() {
        User myTestObject = new User("buddyup","password");
        assertFalse(myTestObject.emailIsValid());
    }

    // Password is 123456
    @Test
    public void passwordIsValid1() {
        User myTestObject = new User("buddyup@gmail.com","123456");
        assertTrue(myTestObject.passwordIsValid());
    }

    // Password is 123456#$%asdf
    @Test
    public void passwordIsValid2() {
        User myTestObject = new User("buddyup@gmail.com","123456#$%asdf");
        assertTrue(myTestObject.passwordIsValid());
    }

    // Password is, 123456789012345678901234, exactly 24 chars, which is upper limit
    @Test
    public void passwordIsValid3() {
        User myTestObject = new User("buddyup@gmail.com","123456789012345678901234");
        assertTrue(myTestObject.passwordIsValid());
    }

    // Password 12345 is too short, must be 6 chars or bigger
    @Test
    public void passwordIsInvalid1() {
        User myTestObject = new User("buddyup@gmail.com","12345");
        assertFalse(myTestObject.passwordIsValid());
    }

    // Password is Empty String: ""
    @Test
    public void passwordIsInvalid2() {
        User myTestObject = new User("buddyup@gmail.com","");
        assertFalse(myTestObject.passwordIsValid());
    }

    // Password is too long, 1234567890123456789012345, exactly 25 chars, 1 past upper limit
    @Test
    public void passwordIsInvalid3() {
        User myTestObject = new User("buddyup@gmail.com","1234567890123456789012345");
        assertFalse(myTestObject.passwordIsValid());
    }
}