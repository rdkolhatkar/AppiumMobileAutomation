package com.appium.practice.code;

import com.appium.practice.utils.GenericTestUtils;
import io.appium.java_client.AppiumBy;
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
    }

}
