package com.appium.practice.code;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.time.Duration;

public class AppiumBasicTest {

    @Test
    public void appiumTest() throws URISyntaxException, MalformedURLException, InterruptedException {
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

        // Now for our test scenario we have to open the API Demos application on the Android device & click on the "Preferences" Option
        // Now we have to identify the locator for the "Preferences" option and click on it
        // List of locators which are supported by Appium are : Xpath, id, accessibilityId, className, androidUIAutomator, androidDataMatcher, androidViewMatcher, androidViewTagMatcher, androidViewIdMatcher, androidViewTextMatcher, androidViewContentDescriptionMatcher
        // Note: The above locators are not supported by iOS devices, for iOS devices we have to use Xpath, id, accessibilityId, className, iosUIAutomation, iosDataMatcher, iosViewMatcher, iosViewTagMatcher, iosViewIdMatcher, iosViewTextMatcher, iosViewContentDescriptionMatcher
        // Now to create the locator for "preferences" We have to use appium "inspector" tool to inspect the element in the application
        // Note: The Appium inspector tool is used to inspect the elements in the application and create the locators for the elements
        // Now we have to download and install the appium inspector tool from the website "https://github.com/appium/appium-inspector" & "https://github.com/appium/appium-inspector/releases"

        // There are two different syntax's to call the locator one is "By.xpath()" & "AppiumBy.xpath()"
        // "By.xpath()" -> Can be used for both Selenium and Appium
        // "AppiumBy.xpath()" -> Can be used only for Appium
        // Note: AppiumBy is only used when we are using these locators androidUIAutomator, androidDataMatcher, androidViewMatcher, androidViewTagMatcher, androidViewIdMatcher, androidViewTextMatcher, androidViewContentDescriptionMatcher
        // Note: AppiumBy is only used when we are using these locators iosUIAutomation, iosDataMatcher, iosViewMatcher, iosViewTagMatcher, iosViewIdMatcher, iosViewTextMatcher, iosViewContentDescriptionMatcher
        // driver.findElement(By.xpath("//android.widget.TextView[@content-desc='Preference']")).click();
        driver.findElement(AppiumBy.accessibilityId("Preference")).click();
        Thread.sleep(1000); // Wait for 1 second to see the action performed on the device
        driver.quit();
        serviceBuilder.start();
        serviceBuilder.clearOutPutStreams();// Stop the Appium server service
    }
}
