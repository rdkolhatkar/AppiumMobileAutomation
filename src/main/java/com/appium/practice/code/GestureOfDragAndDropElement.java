package com.appium.practice.code;

import com.appium.practice.utils.GenericTestUtils;
import com.appium.practice.utils.MobileActionUtils;
import com.google.common.collect.ImmutableMap;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class GestureOfDragAndDropElement extends GenericTestUtils {

    // This class is intended to implement drag and drop gestures on elements in an appium test.
    // The actual implementation will depend on the specific appium client library being used.
    // Note: Actual implementation details would require additional context about the appium setup and the application under test.

    @Test
    public void gestureOfDragDropMobileElement() throws InterruptedException {
        // Click on Views Option
        driver.findElement(AppiumBy.accessibilityId("Views")).click();
        // Click on Drag and Drop Option
        driver.findElement(AppiumBy.accessibilityId("Drag and Drop")).click();
        // Perform drag and drop action
        WebElement source = driver.findElement(By.id("io.appium.android.apis:id/drag_dot_1"));
        // Drag Gesture
        ((JavascriptExecutor)driver).executeScript(
                "mobile: dragGesture",
                ImmutableMap.of(
                        "elementId", ((RemoteWebElement)source).getId(), // Get the ID of the source element
                        "endX", 619, // X coordinate where to drop the source element or the X coordinate of the target element
                        "endY", 560, // Y coordinate where to drop the source element or the Y coordinate of the target element
                        "duration", 1000 // Duration of the drag gesture in milliseconds
                )
        );
        Thread.sleep(2000); // Wait for 2 seconds to observe the drag and drop action
        String resultText = driver.findElement(By.id("io.appium.android.apis:id/drag_result_text"))
                .getText(); // Get the text of the result element to verify the drag and drop action
        Assert.assertEquals(resultText, "Dropped!",
                "The drag and drop action did not result in the expected text 'Dropped!'.");
    }

    @Test
    public void dragAndDropMobileActionUtilsTest(){
        driver.findElement(AppiumBy.accessibilityId("Views")).click();
        driver.findElement(AppiumBy.accessibilityId("Drag and Drop")).click();
        WebElement sourceElement = driver.findElement(By.id("io.appium.android.apis:id/drag_dot_1"));
        MobileActionUtils mobileActionUtils = new MobileActionUtils(driver);
        // Drag and drop the source element to the target coordinates
        mobileActionUtils.dragAndDropElement(
                sourceElement,
                619, // X coordinate where to drop the source element or the X coordinate of the target element
                560, // Y coordinate where to drop the source element or the Y coordinate of the target element
                1000 // Duration of the drag gesture in milliseconds
        );
    }
}
