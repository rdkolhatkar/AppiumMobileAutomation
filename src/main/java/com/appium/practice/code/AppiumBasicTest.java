package com.appium.practice.code;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.testng.annotations.Test;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.time.Duration;

public class AppiumBasicTest {

    @Test
    public void appiumTest() throws URISyntaxException, MalformedURLException {
        // In appium to start our test execution we have to configure the AndroidDriver class for Android devices
        // In appium to start our test execution we have to configure the IOSDriver class for iOS devices.
        // Start the Appium Server on your local device
        AppiumDriverLocalService serviceBuilder = new AppiumServiceBuilder()
                .withAppiumJS(new File("C:/Users/Ratnakar/AppData/Roaming/npm/node_modules/appium/build/lib/main.js")) // Appium requires the absolute path to the "appium/build/lib/main.js" file
                .withIPAddress("127.0.0.1")
                .usingPort(4723)
                .withTimeout(Duration.ofSeconds(120))
                .build();
        serviceBuilder.start(); // Start the Appium server service
        // Create an instance of UiAutomator2Options to set the desired capabilities for the Android driver
        UiAutomator2Options options = new UiAutomator2Options();
        options.setDeviceName("MyAndroidDevice");
        options.setApp("D:/Core Java/projects/AppiumMobileAutomation/src/main/resources/applications/ApiDemos-debug.apk"); // Appium requires the absolute path to the ".apk" files
        AndroidDriver driver = new AndroidDriver(new URI("http://127.0.0.1:4723").toURL(), options);


        driver.quit();
        serviceBuilder.start();
        serviceBuilder.clearOutPutStreams();// Stop the Appium server service
    }
}
