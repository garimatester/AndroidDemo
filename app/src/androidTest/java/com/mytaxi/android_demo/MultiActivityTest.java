package com.mytaxi.android_demo;
import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ResolveInfo;
import android.os.SystemClock;
import android.support.test.espresso.ViewInteraction;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.rule.GrantPermissionRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import android.util.Log;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasAction;
import static android.support.test.espresso.intent.matcher.IntentMatchers.toPackage;
import com.mytaxi.android_demo.activities.MainActivity;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import java.util.ArrayList;
import java.util.List;
import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.InstrumentationRegistry.getTargetContext;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.clearText;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.RootMatchers.withDecorView;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class MultiActivityTest {


    @Rule
    public IntentsTestRule<MainActivity> mActivityTestRule = new IntentsTestRule<MainActivity>(MainActivity.class);


    @Rule
    public GrantPermissionRule permissionRule = GrantPermissionRule.grant(
            android.Manifest.permission.ACCESS_FINE_LOCATION

    );

    /**
     * Setup method to allow location access on device
     *
     */

    @Before
    public void setUp() {

        ArrayList<String> permissions = new ArrayList<>();
        permissions.add(Manifest.permission.ACCESS_FINE_LOCATION);
        //add here other permissions whenever required

        for (int i = 0; i < permissions.size(); i++) {
            String command = String.format("pm grant %s %s", getTargetContext().getPackageName(), permissions.get(i));
            getInstrumentation().getUiAutomation().executeShellCommand(command);

        }
    }


    /**
     * simulates flow:
     * fill in valid login details and go to driver search screen
     * type sa and then select sarah scott
     * on driver profile screen assert if sarah scott is displayed
     * press call button
     * asert dialer option is shown
     *
     */

    @Test
    public void searchDriverAndCall(){

        Log.d("searchDriverAndCall", "user name text box is displayed ");

        //locate user name text box
        ViewInteraction  usrNameTextbox= onView(withId(R.id.edt_username));
        //check if usernametextbox is displayed
        usrNameTextbox.check((matches(isDisplayed())));
        //Enter UserName
        usrNameTextbox.perform(clearText(), typeText("crazydog335"));




        Log.d("searchDriverAndCall", "password text box is displayed ");

        //locate password text box
        ViewInteraction  passwordTextbox= onView(withId(R.id.edt_password));
        //check if password is displayed
        passwordTextbox.check((matches(isDisplayed())));
        //Enter password
        passwordTextbox.perform(clearText(), typeText("venture"));


        Log.d("searchDriverAndCall", "Login button is displayed ");

        //locate login Button
        ViewInteraction  loginButton= onView(withId(R.id.btn_login));

        //check if login button is displayed

        loginButton.check(matches(isDisplayed()));
        //click login button

        loginButton.perform(click());


        MainActivity mActivity = mActivityTestRule.getActivity();   //resume main activity again

       //Thread.sleep(5000);


        //locate text search box
        ViewInteraction  textSearchBox= onView(withId(R.id.textSearch));

        //check if text search boxis displayed

        textSearchBox.check(matches(isDisplayed()));
        //fill text box with search words

        textSearchBox.perform(clearText(), typeText("Sa"));

        //check search results

        ViewInteraction  searchResult= onView(withId(R.id.searchContainer));




       // Thread.sleep(5000);

        // Check that suggestions are displayed.

        searchResult.check(matches(isDisplayed()));

        onView(withText("Sarah Scott"))
                .inRoot(withDecorView(not(is(mActivity.getWindow().getDecorView()))))
                .check(matches(isDisplayed()));

        // Tap on the  suggestion with name.
        onView(withText("Sarah Scott"))
                .inRoot(withDecorView(not(is(mActivity.getWindow().getDecorView()))))
                .perform(click());

        // By clicking on the auto complete term, the driver details are matched.check if correct driver name displayed on the screen
        onView(withId(R.id.textViewDriverName))
                .check(matches(withText("Sarah Scott")));


       // Thread.sleep(5000);
        //Click on call button
        onView(withId(R.id.fab))
                .perform(click());

        // get list of pacjage names for dialer option in devices

        List<String> packagename= getPackagesOfDialerApps(getTargetContext());

        //validates Action_DIAL action and package name after call button click

        intended(allOf(
                hasAction(Intent.ACTION_DIAL),
                toPackage(packagename.get(0))));




    }




    //this function gets all package names to initiate phone call

    public List<String> getPackagesOfDialerApps(Context context){

        List<String> packageNames = new ArrayList<>();
        // Declare action which target application listen to initiate phone call
        final Intent intent = new Intent();
        intent.setAction(Intent.ACTION_DIAL);
        // Query for all those applications
        List<ResolveInfo> resolveInfos = context.getPackageManager().queryIntentActivities(intent, 0);
        // Read package name of all those applications
        for(ResolveInfo resolveInfo : resolveInfos){
            ActivityInfo activityInfo = resolveInfo.activityInfo;
            packageNames.add(activityInfo.applicationInfo.packageName);
        }
        Log.d("LOG package names", String.valueOf(packageNames));
        return packageNames;
    }


}
