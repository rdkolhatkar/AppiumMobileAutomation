package com.appium.practice.code;

import com.appium.practice.utils.GenericTestUtils;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import org.openqa.selenium.By;
import org.openqa.selenium.DeviceRotation;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AndroidKeyAndKeyboardEvents extends GenericTestUtils {
    // In this class, we will write code to handle Android key events and keyboard events.
    // Android key events are used to simulate the physical keys on the Android device, such as the back button, home button, volume up/down buttons, etc.
    // Keyboard events are used to simulate the keyboard input on the Android device, such as typing text, pressing enter, etc.

    @Test
    public void androidKeyEventsTest() {
        driver.findElement(AppiumBy.accessibilityId("Preference")).click();
        driver.findElement(AppiumBy.xpath("//android.widget.TextView[@content-desc='3. Preference dependencies']")).click();
        driver.findElement(By.id("android:id/checkbox")).click();
        DeviceRotation landscapeRotation = new DeviceRotation(0, 0, 90);
        driver.rotate(landscapeRotation);
        driver.findElement(By.xpath("(//android.widget.RelativeLayout)[2]")).click();
        String wifiAlertTitle = driver.findElement(By.id("android:id/alertTitle")).getText();
        Assert.assertEquals(wifiAlertTitle, "WiFi settings");
        driver.setClipboardText("RatnakarWiFi");
        driver.findElement(By.id("android:id/edit")).sendKeys(driver.getClipboardText());

        // Simulating the enter key press
        driver.pressKey(new KeyEvent(AndroidKey.ENTER)); // This will press the enter button on the Android device
        // Now we have to click on OK button of the wifi popup

        driver.findElements(AppiumBy.className("android.widget.Button")).get(1).click();
        // Simulating the back button press
        //pressKey is used to simulate the physical keys on the Android device
        driver.pressKey(new KeyEvent(AndroidKey.BACK)); // This will press the back button on the Android device
        driver.pressKey(new KeyEvent(AndroidKey.HOME)); // This will press the home button on the Android device

    }

}
