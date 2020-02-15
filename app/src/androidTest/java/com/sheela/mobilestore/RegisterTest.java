package com.sheela.mobilestore;

import androidx.test.espresso.Espresso;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;

import com.sheela.mobilestore.activity.Login;
import com.sheela.mobilestore.activity.SignUp;

import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
@LargeTest
public class RegisterTest {
    @Rule
    public ActivityTestRule<SignUp> testRule= new ActivityTestRule<>(SignUp.class);
    @Test
    public void RegisterTest(){
        onView(withId(R.id.imgsheela))
                .perform(click());

        onView(withId(R.id.etfirstName))
                .perform(typeText("bibek"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.etlastName))
                .perform(typeText("magar"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.etPhoneNo))
                .perform(typeText("8887848499"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.etUserName))
                .perform(typeText("bibekmagar"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.etPassword))
                .perform(typeText("bibek"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.etConfirmPassword))
                .perform(typeText("sheela"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.btnRegister))
                .perform(click());
        isDisplayed();

    }
}
