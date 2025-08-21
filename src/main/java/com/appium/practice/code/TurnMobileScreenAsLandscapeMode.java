package com.appium.practice.code;

import com.appium.practice.utils.GenericTestUtils;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;
import org.openqa.selenium.DeviceRotation;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TurnMobileScreenAsLandscapeMode extends GenericTestUtils { // extends GenericTestUtils is used to inherit the appiumConfiguration() method from GenericTestUtils class
    // In this class, we will write code to turn the mobile screen into landscape mode.
    // And then we will execute our test cases in landscape mode.
    @Test
    public void TestWifiSettingsInLandscapeMode(){
        driver.findElement(AppiumBy.accessibilityId("Preference")).click();
        driver.findElement(AppiumBy.xpath("//android.widget.TextView[@content-desc='3. Preference dependencies']")).click();
        driver.findElement(By.id("android:id/checkbox")).click();

        // Here we will turn the mobile screen into landscape mode
        DeviceRotation landscapeRotation = new DeviceRotation(0, 0, 90); // 0 degrees on X-axis, 0 degrees on Y-axis, 90 degrees on Z-axis
        // DeviceRotation class is used to set the co-ordinates of orientation of the mobile screen
        driver.rotate(landscapeRotation); // This will turn the mobile screen into landscape mode
        // rotate() method is used to set the orientation of the mobile screen
        // Here in this test our mobile screen will get rotated after we click on the "WiFi settings Check Box" option

        driver.findElement(By.xpath("(//android.widget.RelativeLayout)[2]")).click();
        String wifiAlertTitle = driver.findElement(By.id("android:id/alertTitle")).getText();
        Assert.assertEquals(wifiAlertTitle, "WiFi settings");
        driver.findElement(By.id("android:id/edit")).sendKeys("RatnakarWiFi");
        driver.findElements(AppiumBy.className("android.widget.Button")).get(1).click();
    }

}
