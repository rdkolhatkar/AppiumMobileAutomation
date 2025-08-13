package com.appium.practice.code;

import com.appium.practice.utils.GenericTestUtils;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class WifiSettingsTest extends GenericTestUtils { // extends GenericTestUtils is used to inherit the appiumConfiguration() method from GenericTestUtils class

    @Test
    public void wifiSettingsTest() throws Exception {
        appiumConfiguration(); // This method is used to start the Appium server and create an instance of AndroidDriver
        // Click on the "Preferences" option
        driver.findElement(AppiumBy.accessibilityId("Preference")).click();
        Thread.sleep(1000); // Wait for 1 second to see the action performed on the device
        tearDownAppiumServer(); // This method is used to stop the Appium server and quit the driver
    }

    @Test
    public void wifiSettingsOptionTest(){
        // As this "WifiSettingsTest" class is extending the "GenericTestUtils" class, we can directly use the driver instance created in the appiumConfiguration() method
        // In the "GenericTestUtils" class we have given the "@BeforeClass" annotation to the "appiumConfiguration()" method, which means this method will be executed before any test methods in the class are executed
        // In the "GenericTestUtils" class we have given the "@AfterClass" annotation to the "tearDownAppiumServer()" method, which means this method will be executed after all the test methods in the class are executed
        // So here we don't need to call the "appiumConfiguration()" & "tearDownAppiumServer()" methods again, as it is already called before the test methods are executed
        driver.findElement(AppiumBy.accessibilityId("Preference")).click();
        // Common syntax of writing xpath in Appium is: //tagName[@attribute='value']
        driver.findElement(AppiumBy.xpath("//android.widget.TextView[@content-desc='3. Preference dependencies']")).click();
        // Click on the "WiFi settings Check Box" option
        driver.findElement(By.id("android:id/checkbox")).click();
        // Click on the "WiFi settings" option
        // Another way of writing xpath in Appium is: //tagName
        // In this case we have two elements with the same tag name, so we need to use the index to select the second element
        driver.findElement(By.xpath("(//android.widget.RelativeLayout)[2]")).click(); //(android.widget.RelativeLayout)[2] -> here [2] indicates the second element with the same tag name
        // Now we have to validate the wifi popup tile
        String wifiAlertTitle = driver.findElement(By.id("android:id/alertTitle")).getText();
        Assert.assertEquals(wifiAlertTitle, "WiFi settings"); // Asserting the title validation
        // Now we have to enter the password on wifi settings popup editbox
        driver.findElement(By.id("android:id/edit")).sendKeys("RatnakarWiFi");
        // Now we have to click on OK button of the wifi popup
        // driver.findElement(By.id("android:id/button1")).click(); // This is one way to it
        // Now as we have multiple elements with same class name we can use the below code
        driver.findElements(AppiumBy.className("android.widget.Button")).get(1).click();


    }

}
