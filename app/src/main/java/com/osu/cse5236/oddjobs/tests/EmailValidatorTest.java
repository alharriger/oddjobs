package com.osu.cse5236.oddjobs.tests;

import com.osu.cse5236.oddjobs.activities.LoginActivity;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Zenith on 4/9/2017.
 */

public class EmailValidatorTest {
    @Test
    public void emailValidator_CorrectEmailSimple_ReturnsTrue() {
        assertEquals(LoginActivity.isEmailValid("name@email.com"), true);
    }
    @Test
    public void emailValidator_BadEmailSimple_ReturnsFlase() {
        assertEquals(LoginActivity.isEmailValid("name_email.com"), false);
    }
}
