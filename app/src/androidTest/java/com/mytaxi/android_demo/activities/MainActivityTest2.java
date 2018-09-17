package com.mytaxi.android_demo.activities;


import android.Manifest;
import android.os.SystemClock;
import android.support.test.rule.ActivityTestRule;
import android.support.test.rule.GrantPermissionRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import android.util.Log;

import com.mytaxi.android_demo.R;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.InstrumentationRegistry.getTargetContext;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;


@LargeTest
@RunWith(AndroidJUnit4.class)
public class MainActivityTest2 {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Rule
    public GrantPermissionRule permissionRule = GrantPermissionRule.grant(
            //android.Manifest.permission.INTERNET,
           // android.Manifest.permission.READ_PHONE_STATE,
            //android.Manifest.permission.ACCESS_NETWORK_STATE,
            android.Manifest.permission.ACCESS_FINE_LOCATION);


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

    @Test
    public void mainActivityTest2() {

        Log.d("mytest", "insidemainActivityTest2: ");

        onView(withId(R.id.textSearch)).check(matches(isDisplayed()));


        SystemClock.sleep(1000);
        Log.d("test", "test: ");

        /*ViewInteraction button = onView(
                allOf(withId(R.id.permission_allow_button),
                        childAtPosition(
                                allOf(withId(R.id.button_group),
                                        childAtPosition(
                                                IsInstanceOf.<View>instanceOf(LinearLayout.class),
                                                0)),
                                1),
                        isDisplayed()));
        button.check(matches(isDisplayed()));

        button.perform(click());

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }



    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }*/
    }
}
