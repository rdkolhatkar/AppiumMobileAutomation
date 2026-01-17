package com.appium.iOS.code;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.Duration;

public class BasicTestIOS {
    public static void main(String[] args) throws URISyntaxException, MalformedURLException {
        // This code for Starting the Appium server will remain same for both Android and IOS
        AppiumDriverLocalService serviceBuilder = new AppiumServiceBuilder()
                .withAppiumJS(new File("C:/Users/Ratnakar/AppData/Roaming/npm/node_modules/appium/build/lib/main.js")) // Appium requires the absolute path to the "appium/build/lib/main.js" file
                .withIPAddress("127.0.0.1")
                .usingPort(4723)
                .withTimeout(Duration.ofSeconds(120))
                .build();
        serviceBuilder.start();
        // For IOS we have the XCUITestOptions just like in Android we have the UiAutomator2Options
        // To install the xcui driver use the command called "appium driver install xcuitest"
        XCUITestOptions options = new XCUITestOptions();
        // Now we will set all the Capabilities
        options.setDeviceName("iPhone 13 Pro");
        options.setApp("/Users/rahulshetty/Desktop/UIKitCatalog.app");
        options.setPlatformVersion("15.5");
        // In IOS there is an intermediate webDriver Agent which calls the IOS for Appium Code execution
        // To handle that intermediate timeout we use the 'setWdaLaunchTimeout()' -> Wda stands for WebDriver Agent
        options.setWdaLaunchTimeout(Duration.ofSeconds(20));
        // Setting up the IOS driver
        IOSDriver driver = new IOSDriver(new URI("http://127.0.0.1:4723").toURL(), options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        // Close the Driver and Stop the Service
        driver.quit();
        serviceBuilder.stop(); // Stop the Appium server service
        serviceBuilder.clearOutPutStreams();// Stop the Appium server service
    }
}
