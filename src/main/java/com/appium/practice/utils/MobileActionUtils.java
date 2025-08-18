package com.appium.practice.utils;

import com.google.common.collect.ImmutableMap;
import io.appium.java_client.AppiumBy;
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
        ((JavascriptExecutor) driver).executeScript(
                "mobile: longClickGesture",
                ImmutableMap.of(
                        "elementId", ((RemoteWebElement) element).getId(),
                        "duration", duration
                )
        );
    }

    // Scroll action can be defined here
    public boolean scrollToElement(String elementText) {
        // Implementation for scrolling to the specified element text
        boolean canScrollMore;
        do {
            // Loop will keep on executing until the element is found or no more scrolling is possible
            // Loop will keep on executing until "canScrollMore" is true, once the element is found or no more scrolling is possible, it will become false
            canScrollMore = (Boolean) ((JavascriptExecutor) driver)
                    .executeScript(
                            "mobile: scrollGesture",
                            ImmutableMap.of(
                                    "left", 100,
                                    "top", 500,
                                    "width", 100,
                                    "height", 100,
                                    "direction", "down",
                                    "percent", 3.0
                            )
                    );
            // Check if the element is present after scrolling
            try {
                driver.findElement(AppiumBy.accessibilityId(elementText));
                // If the element is found, break the loop
                canScrollMore = false;
            } catch (Exception e) {
                // If the element is not found, continue scrolling
                canScrollMore = true;
            }
        } while (canScrollMore);
        // Return whether scrolling can continue or not
        return canScrollMore;
    }

    // Swipe action can be defined here
    public void swipeOnElement(WebElement element, String direction, double percent) {
        // Implementation for swiping on the given element in the specified direction with the given percentage
        ((JavascriptExecutor) driver).executeScript("mobile: swipeGesture", ImmutableMap.of(
                "elementId", ((RemoteWebElement) element).getId(),
                "direction", direction,
                "percent", percent
        ));
    }

    // Drag and drop action can be defined here
    public void dragAndDropElement(WebElement source, int endX, int endY, int duration) {
        // Implementation for dragging and dropping the source element to the specified coordinates
        ((JavascriptExecutor) driver).executeScript(
                "mobile: dragGesture",
                ImmutableMap.of(
                        "elementId", ((RemoteWebElement) source).getId(),
                        "endX", endX,
                        "endY", endY,
                        "duration", duration
                )
        );
    }

}
