package com.appium.practice.utils;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.Duration;

public class EcommerceAppGenericUtils {

    public AppiumDriverLocalService serviceBuilder;
    public AndroidDriver driver;

    @BeforeClass // TestNG annotation which will this method before any tests in the class are executed
    public void appiumConfiguration() throws URISyntaxException, MalformedURLException {

        serviceBuilder = new AppiumServiceBuilder()
                .withAppiumJS(new File("C:/Users/Ratnakar/AppData/Roaming/npm/node_modules/appium/build/lib/main.js"))
                .withIPAddress("127.0.0.1")
                .usingPort(4723)
                .withTimeout(Duration.ofSeconds(120))
                .build();
        serviceBuilder.start(); // Start the Appium server service
        UiAutomator2Options options = new UiAutomator2Options();
        options.setDeviceName("MyAndroidDevice");
        options.setApp("D:/Core Java/projects/AppiumMobileAutomation/src/main/resources/applications/General-Store.apk");
        driver = new AndroidDriver(new URI("http://127.0.0.1:4723").toURL(), options);
        // Adding timeouts to invoke driver during the elemental interaction with emulator
        // These are the global time out applicable for server initialization
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); // Implicit wait

    }

    @AfterClass // TestNG annotation which will this method after any tests in the class are executed
    public void tearDownAppiumServer() {
        driver.quit();
        serviceBuilder.stop();
    }
}
