package com.osu.cse5236.oddjobs;

import android.support.test.runner.AndroidJUnit4;

import com.osu.cse5236.oddjobs.activities.LoginActivity;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class EmailValidatorTest {
    @Test
    public void emailValidator_CorrectEmailSimple_ReturnsTrue() throws Exception {
        assertEquals(LoginActivity.isEmailValid("name@email.com"), true);
    }
    @Test
    public void emailValidator_BadEmailSimple_ReturnsFalse() throws Exception {
        assertEquals(LoginActivity.isEmailValid("name_email.com"), false);
    }
}
