package com.foxtrot.sudoku;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import android.content.Context;
import android.content.Intent;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.SdkSuppress;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.uiautomator.By;
import androidx.test.uiautomator.UiDevice;
import androidx.test.uiautomator.UiObject;
import androidx.test.uiautomator.UiObjectNotFoundException;
import androidx.test.uiautomator.UiSelector;
import androidx.test.uiautomator.Until;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
@SdkSuppress(minSdkVersion = 18)
public class UiTest {

    private static final String BASIC_SAMPLE_PACKAGE = "com.foxtrot.sudoku";
    private static final int LAUNCH_TIMEOUT = 5000;
    private UiDevice device;

    @Before
    public void startMainActivityFromHomeScreen() {
        // Initialize UiDevice instance
        device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());

        // Start from the home screen
        device.pressHome();

        // Wait for launcher
        final String launcherPackage = device.getLauncherPackageName();
        assertThat(launcherPackage, notNullValue());
        device.wait(Until.hasObject(By.pkg(launcherPackage).depth(0)), LAUNCH_TIMEOUT);

        // Launch the app
        Context context = ApplicationProvider.getApplicationContext();
        final Intent intent = context.getPackageManager().getLaunchIntentForPackage(BASIC_SAMPLE_PACKAGE);

        // Clear out any previous instances
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(intent);

        // Wait for the app to appear
        device.wait(Until.hasObject(By.pkg(BASIC_SAMPLE_PACKAGE).depth(0)), LAUNCH_TIMEOUT);
    }

    public void clickButton(String id) throws UiObjectNotFoundException {
        // Simulate a user-click on a button, if found and enabled
        UiObject btn = device.findObject(new UiSelector().resourceId(id));
        if (btn.exists() && btn.isEnabled()) {
            btn.click();
        }
    }

    @Test
    public void test4x4Btn() throws UiObjectNotFoundException {
        clickButton("com.foxtrot.sudoku:id/button_four");
    }

    @Test
    public void test6x6Btn() throws UiObjectNotFoundException {
        clickButton("com.foxtrot.sudoku:id/button_six");
    }

    @Test
    public void test9x9Btn() throws UiObjectNotFoundException {
        clickButton("com.foxtrot.sudoku:id/button_nine");
    }

    @Test
    public void test12x12Btn() throws UiObjectNotFoundException {
        clickButton("com.foxtrot.sudoku:id/button_twelve");
    }

    @Test
    public void testSubmitBtn() throws UiObjectNotFoundException {
        clickButton("com.foxtrot.sudoku:id/button_four");
        clickButton("com.foxtrot.sudoku:id/submit_button");
        clickButton("android:id/button1");
    }

    @Test
    public void testHintBtn() throws UiObjectNotFoundException {
        clickButton("com.foxtrot.sudoku:id/button_four");
        clickButton("com.foxtrot.sudoku:id/hint_button");
        clickButton("android:id/button1");
    }

    @Test
    public void testRestartBtn() throws UiObjectNotFoundException {
        clickButton("com.foxtrot.sudoku:id/button_four");
        clickButton("com.foxtrot.sudoku:id/restart_button");
        clickButton("android:id/button1");
    }

    @Test
    public void testHomeBtn() throws UiObjectNotFoundException {
        clickButton("com.foxtrot.sudoku:id/button_four");
        clickButton("com.foxtrot.sudoku:id/back_button");
    }

    @Test
    public void testEraseBtn() throws UiObjectNotFoundException {
        clickButton("com.foxtrot.sudoku:id/button_four");
        clickButton("com.foxtrot.sudoku:id/erase_button");
    }

    @Test
    public void testListeningComprehensionMode() throws UiObjectNotFoundException {
        clickButton("com.foxtrot.sudoku:id/game_mode_listening_comprehension");
        test4x4Btn();
        clickButton("com.foxtrot.sudoku:id/back_button");
        clickButton("com.foxtrot.sudoku:id/game_mode_listening_comprehension");
        test6x6Btn();
        clickButton("com.foxtrot.sudoku:id/back_button");
        clickButton("com.foxtrot.sudoku:id/game_mode_listening_comprehension");
        test9x9Btn();
        clickButton("com.foxtrot.sudoku:id/back_button");
        clickButton("com.foxtrot.sudoku:id/game_mode_listening_comprehension");
        test12x12Btn();
        clickButton("com.foxtrot.sudoku:id/back_button");
    }

    @Test
    public void testNormalEnglishMode() throws UiObjectNotFoundException {
        clickButton("com.foxtrot.sudoku:id/game_mode_normal");
        clickButton("com.foxtrot.sudoku:id/board_language_english");
        test4x4Btn();
        clickButton("com.foxtrot.sudoku:id/back_button");
        clickButton("com.foxtrot.sudoku:id/board_language_english");
        test6x6Btn();
        clickButton("com.foxtrot.sudoku:id/back_button");
        clickButton("com.foxtrot.sudoku:id/board_language_english");
        test9x9Btn();
        clickButton("com.foxtrot.sudoku:id/back_button");
        clickButton("com.foxtrot.sudoku:id/board_language_english");
        test12x12Btn();
        clickButton("com.foxtrot.sudoku:id/back_button");
    }

    @Test
    public void testNormalFrenchMode() throws UiObjectNotFoundException {
        clickButton("com.foxtrot.sudoku:id/game_mode_normal");
        clickButton("com.foxtrot.sudoku:id/board_language_english");
        clickButton("com.foxtrot.sudoku:id/board_language_french");
        test4x4Btn();
        clickButton("com.foxtrot.sudoku:id/back_button");
        clickButton("com.foxtrot.sudoku:id/board_language_english");
        clickButton("com.foxtrot.sudoku:id/board_language_french");
        test6x6Btn();
        clickButton("com.foxtrot.sudoku:id/back_button");
        clickButton("com.foxtrot.sudoku:id/board_language_english");
        clickButton("com.foxtrot.sudoku:id/board_language_french");
        test9x9Btn();
        clickButton("com.foxtrot.sudoku:id/back_button");
        clickButton("com.foxtrot.sudoku:id/board_language_english");
        clickButton("com.foxtrot.sudoku:id/board_language_french");
        test12x12Btn();
        clickButton("com.foxtrot.sudoku:id/back_button");
    }
}
