package com.sheela.mobilestore;

import androidx.test.espresso.Espresso;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;

import com.sheela.mobilestore.activity.Login;

import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

@LargeTest
public class LoginTest {
@Rule
  public ActivityTestRule<Login> testRule= new ActivityTestRule<>(Login.class);
    @Test
    public void LoginTest(){
     onView(withId(R.id.etUserName))
             .perform(typeText("sheelabhandari"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.etPassword))
                .perform(typeText("sheela"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.btnLogin))
                .perform(click());
        isDisplayed();

    }

}
