package com.appium.practice.code;

import com.appium.practice.utils.GenericTestUtils;
import com.appium.practice.utils.MobileActionUtils;
import com.google.common.collect.ImmutableMap;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.Test;

public class GestureOfScrollingTheElement extends GenericTestUtils {

    @Test
    public void scrollToFindTheListOfWebViewElements() throws InterruptedException {
        // This method will scroll to find the list of elements
        // Click on Views Option
        // Scroll down to find the "WebView" option
        driver.findElement(AppiumBy.accessibilityId("Views")).click();
        // There are two ways to scroll in the Appium
        // 1. Using "androidUIAutomator" Method Which is developed by Google
        // 2. Using JavaScript Executor
        // Here we are using the "androidUIAutomator" Method Utils
        /*
        driver.findElement(AppiumBy.androidUIAutomator(
                "new UiScrollable(new UiSelector())
                .scrollIntoView(text(\"WebView\"))";
        ));
        */
        driver.findElement(AppiumBy.androidUIAutomator(
                "new UiScrollable(new UiSelector().scrollable(true)).scrollIntoView(new UiSelector().text(\"WebView\")" +
                        ".instance(0))" // instance(0) is used to specify the first occurrence of the element
        )).click();
        Thread.sleep(2000); // Adding sleep to observe the action, can be removed in production code

    }

    @Test
    public void scrollToFindTheSystemUIVisibilityElement() throws InterruptedException {
        // This method will scroll to find the "System UI Visibility" element
        driver.findElement(AppiumBy.accessibilityId("Views")).click();
        // Scroll down to find the "System UI Visibility" option
        driver.findElement(AppiumBy.androidUIAutomator(
                "new UiScrollable(new UiSelector().scrollable(true)).scrollIntoView(new UiSelector().text(\"System UI Visibility\")" +
                        ".instance(0))" // instance(0) is used to specify the first occurrence of the element
        )).click();
        Thread.sleep(2000); // Adding sleep to observe the action, can be removed in production code
    }

    @Test
    public void scrollToFindTheWebViewElementWithJavaScriptExecutor() {
        // Scrolling by using JavaScript Executor
        // For this we have to give the coordinates of the element
        // Where to scroll is known prior or We have to scroll bit by bit and check if element exists or not
        driver.findElement(AppiumBy.accessibilityId("Views")).click();
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
                driver.findElement(AppiumBy.accessibilityId("WebView"));
                // If the element is found, break the loop
                canScrollMore = false;
            } catch (Exception e) {
                // If the element is not found, continue scrolling
                canScrollMore = true;
            }
        } while (canScrollMore);
    }

    @Test
    public void scrollMobileActionUtilsTest() throws InterruptedException {
        // Using MobileActionUtils class to perform scroll action
        // This will scroll to find the "WebView" element
        driver.findElement(AppiumBy.accessibilityId("Views")).click();
        MobileActionUtils mobileActionUtils = new MobileActionUtils(driver);
        mobileActionUtils.scrollToElement("WebView");
        Thread.sleep(2000); // Adding sleep to observe the action, can be removed in production code
    }
}
