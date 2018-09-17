package com.mytaxi.android_demo;

import android.support.test.rule.ActivityTestRule;

import com.mytaxi.android_demo.activities.AuthenticatedActivity;
import com.mytaxi.android_demo.activities.AuthenticationActivity;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

public class AuthenticatedActivityTest {


    @Rule
    public ActivityTestRule<AuthenticationActivity> mActivityRule = new ActivityTestRule<>(AuthenticationActivity.class);



    @Rule
    public ActivityTestRule<AuthenticatedActivity> mActivityRule1 = new ActivityTestRule<>(AuthenticatedActivity.class);


    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void onCreate() {
    }

    @Test
    public void isAuthenticated() {
    }
}