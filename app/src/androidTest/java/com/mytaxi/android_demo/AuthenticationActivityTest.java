package com.mytaxi.android_demo;

import android.Manifest;
import android.os.SystemClock;
import android.support.test.espresso.intent.Intents;
import android.support.test.rule.ActivityTestRule;
import android.support.test.rule.GrantPermissionRule;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import com.mytaxi.android_demo.activities.AuthenticationActivity;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.InstrumentationRegistry.getTargetContext;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.clearText;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;





@RunWith(AndroidJUnit4.class)

public class AuthenticationActivityTest {


    @Rule
    public GrantPermissionRule permissionRule = GrantPermissionRule.grant(
            //android.Manifest.permission.INTERNET,
            // android.Manifest.permission.READ_PHONE_STATE,
            //android.Manifest.permission.ACCESS_NETWORK_STATE,
            android.Manifest.permission.ACCESS_FINE_LOCATION);



    @Rule
   public ActivityTestRule<AuthenticationActivity> mActivityRule = new ActivityTestRule<>(AuthenticationActivity.class);






        @Before
        public void setUp() throws Exception {

            // mActivityTestRule.getActivity().getSupportFragmentManager().beginTransaction();

            ArrayList<String> permissions = new ArrayList<>();
            permissions.add(Manifest.permission.ACCESS_FINE_LOCATION);
            //add here your other permissions

            for (int i = 0; i < permissions.size(); i++) {
                String command = String.format("pm grant %s %s", getTargetContext().getPackageName(), permissions.get(i));
                getInstrumentation().getUiAutomation().executeShellCommand(command);
                // wait a bit until the command is finished
                //SystemClock.sleep(1000);
            }

        }



    @After
    public void tearDown() throws Exception {
    }


    @Test
    public void test1ChatId() {
        Log.d("test", "test: ");

        onView(withId(R.id.edt_username)).check(matches(isDisplayed()));
        onView(withId(R.id.edt_username)). perform(clearText(),typeText("crazydog335"));
        onView(withId(R.id.edt_password)).check(matches(isDisplayed()));
        onView(withId(R.id.edt_password)). perform(clearText(),typeText("ventre"));
        onView(withId(R.id.btn_login)).check(matches(isDisplayed()));
        Intents.init();
        onView(withId(R.id.btn_login)).perform(click());
        Intents.release();
        SystemClock.sleep(1000);






    }
}