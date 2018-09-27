package com.app;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

import android.support.test.runner.AndroidJUnit4;
import android.support.test.rule.ActivityTestRule;
import org.junit.Test;
import org.junit.Rule;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class AppTest {

  @Rule
  public ActivityTestRule<MainActivity> activityRule = new ActivityTestRule<>(
          MainActivity.class);

  @Test
  public void testClick() {
    onView(withId(R.id.button)).perform(click());
    onView(withId(R.id.text)).check(matches(withText("Button clicked")));
  }

}
