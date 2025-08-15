package com.appium.practice.utils;

import com.google.common.collect.ImmutableMap;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;

public class MobileActionUtils {
    public AndroidDriver driver;

    // This class can be used to define common mobile actions like tap, swipe, scroll, etc.
    public MobileActionUtils(AndroidDriver driver) {
        this.driver = driver;
    }

    // Long press action can be defined here


    public void longPressOnElement(WebElement element, int duration) {
        // Implementation for long press on the given element for the specified duration
        // This can use JavaScriptExecutor or Appium's touch actions
        ((JavascriptExecutor)driver).executeScript(
                "mobile: longClickGesture",
                ImmutableMap.of(
                        "elementId",((RemoteWebElement)element).getId(),
                        "duration",duration
                )
        );
    }
}
