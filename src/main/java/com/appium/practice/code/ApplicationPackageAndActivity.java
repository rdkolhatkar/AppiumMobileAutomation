package com.appium.practice.code;

import com.appium.practice.utils.GenericTestUtils;
import com.google.common.collect.ImmutableMap;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.Activity;
import io.appium.java_client.android.StartsActivity;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import org.openqa.selenium.By;
import org.openqa.selenium.DeviceRotation;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Map;

public class ApplicationPackageAndActivity extends GenericTestUtils {
    // Here we are going to launch the desired mobile application menu without launching the mobile application itself.
    // For example we can launch the wifi settings menu without launching the settings application.
    // App package and App activity are used to identify the application and the specific screen or activity within that application.
    // Collection of mobile application activities are stored in the Application package, For our mobile app there will be only one package and multiple activities for different features
    @Test
    public void launchApplicationPackageAndActivity() {
        // To launch the application package and activity, we need to use the following code
        // Syntax -> Activity activity = new Activity("<Mobile Application Package Name>", "<Mobile Application Activity Name>");
        Activity activity = new Activity("io.appium.android.apis", "io.appium.android.apis.preference.PreferenceDependencies");
        // How to check and identify the mobile application package and activity name?
        // Refer the ImportantAppiumNotes file in for package and activity identification.
        // Now we will execute the JavaScript to launch the application package and activity
        // Reference documentation - https://github.com/appium/appium-uiautomator2-driver#mobile-startactivity
        ((JavascriptExecutor) driver).executeScript("mobile: startActivity",
                ImmutableMap.of(
                        "intent", "io.appium.android.apis/io.appium.android.apis.preference.PreferenceDependencies"
                ));
//        driver.findElement(AppiumBy.accessibilityId("Preference")).click();
//        driver.findElement(AppiumBy.xpath("//android.widget.TextView[@content-desc='3. Preference dependencies']")).click();
        driver.findElement(By.id("android:id/checkbox")).click();
        DeviceRotation landscapeRotation = new DeviceRotation(0, 0, 90);
        driver.rotate(landscapeRotation);
        driver.findElement(By.xpath("(//android.widget.RelativeLayout)[2]")).click();
        String wifiAlertTitle = driver.findElement(By.id("android:id/alertTitle")).getText();
        Assert.assertEquals(wifiAlertTitle, "WiFi settings");
        driver.setClipboardText("RatnakarWiFi");
        driver.findElement(By.id("android:id/edit")).sendKeys(driver.getClipboardText());
        driver.pressKey(new KeyEvent(AndroidKey.ENTER));
        driver.findElements(AppiumBy.className("android.widget.Button")).get(1).click();
        driver.pressKey(new KeyEvent(AndroidKey.BACK));
        driver.pressKey(new KeyEvent(AndroidKey.HOME));
    }
}
