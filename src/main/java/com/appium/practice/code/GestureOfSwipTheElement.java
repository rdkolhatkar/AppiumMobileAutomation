package com.appium.practice.code;

import com.appium.practice.utils.GenericTestUtils;
import com.appium.practice.utils.MobileActionUtils;
import com.google.common.collect.ImmutableMap;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class GestureOfSwipTheElement extends GenericTestUtils {

    // This class is intended to implement swipe gestures on elements in an Appium context.
    // The actual implementation will depend on the specific requirements and the Appium setup.
    // Example methods could include:
    // - swipeLeft(Element element)
    // - swipeRight(Element element)
    // - swipeUp(Element element)
    // - swipeDown(Element element)
    // Each method would use Appium's touch actions to perform the swipe gesture on the specified element.
    // Note: Actual implementation details would require additional context and dependencies.

    @Test
    public void galleryImagesSwipingTest(){
        // This method will swipe through gallery images
        // Click on Views Option
        driver.findElement(AppiumBy.accessibilityId("Views")).click();
        // Click on Gallery Option
        driver.findElement(AppiumBy.accessibilityId("Gallery")).click();
        // Select the photos option in the Gallery
        driver.findElement(AppiumBy.xpath("//android.widget.TextView[@content-desc='1. Photos']")).click();
        // Now by default the focus is on the first image in the gallery
        // Swipe left to view the next image
        // Swipe left to select the second image, once done the focus on the first image will be false.
        // Now first we have to check when clicking on the "photos" option, the first image is selected, and for that we have to assert if the focus value is true for the first image.
        // After that we will scroll left to select the second image and then assert if the focus value is false for the first image and true for the second image.
        // Check if focus is on the first image is true
        boolean isFirstImageFocused = driver.findElement(AppiumBy.xpath("(//android.widget.ImageView)[1]")).getAttribute("focusable").equals("true");
        assert isFirstImageFocused : "First image is not focused as expected";
        /*
        AssertEquals("true", driver.findElement(AppiumBy.xpath("(//android.widget.ImageView)[1]")).getAttribute("focusable"),
                "First image is not focused as expected");
        */
        // Swipe left to select the second image
        // Swipe Gesture
        WebElement firstImageElement = driver.findElement(AppiumBy.xpath("(//android.widget.ImageView)[1]"));
        ((JavascriptExecutor)driver).executeScript("mobile: swipeGesture", ImmutableMap.of(
                "elementId", ((RemoteWebElement)firstImageElement).getId(),
                "direction", "left", // We have different directions like "left", "right", "up", "down"
                "percent", 0.75 // Swipe left with 75% of the element width, Limit of percent is 0.0 to 1.0
        ));
        // Check if focus is on the first image is false
        boolean isFirstImageNotFocused = driver.findElement(AppiumBy.xpath("(//android.widget.ImageView)[1]")).getAttribute("focusable").equals("false");
        assert isFirstImageNotFocused : "First image is still focused after swiping left";
        /*
        AssertEquals("false", driver.findElement(AppiumBy.xpath("(//android.widget.ImageView)[1]")).getAttribute("focusable"),
                "First image is still focused after swiping left");
        */
    }

    @Test
    public void swipMobileActionUtilsTest() {
        // Using MobileActionUtils class to perform swip action
        // Click on Views Option
        driver.findElement(AppiumBy.accessibilityId("Views")).click();
        // Click on Gallery Option
        driver.findElement(AppiumBy.accessibilityId("Gallery")).click();
        // Select the photos option in the Gallery
        driver.findElement(AppiumBy.xpath("//android.widget.TextView[@content-desc='1. Photos']")).click();
        MobileActionUtils mobileActionUtils = new MobileActionUtils(driver);
        mobileActionUtils.swipeOnElement(
                driver.findElement(AppiumBy.xpath("(//android.widget.ImageView)[1]")),
                "left",
                0.75
        );
        assert driver.findElement(AppiumBy.xpath("(//android.widget.ImageView)[1]"))
                .getAttribute("focusable")
                .equals("false") : "First image is still focused after swiping left";
    }

    @Test
    public void swipActionUtilsMobileTest() {
        // Using MobileActionUtils class to perform swip action
        // Click on Views Option
        driver.findElement(AppiumBy.accessibilityId("Views")).click();
        // Click on Gallery Option
        driver.findElement(AppiumBy.accessibilityId("Gallery")).click();
        // Select the photos option in the Gallery
        driver.findElement(AppiumBy.xpath("//android.widget.TextView[@content-desc='1. Photos']")).click();
        MobileActionUtils mobileActionUtils = new MobileActionUtils(driver);
        WebElement firstImageElement = driver.findElement(AppiumBy.xpath("(//android.widget.ImageView)[1]"));
        mobileActionUtils.swipeOnElement(
                firstImageElement,
                "left",
                0.75
        );
        Assert.assertEquals("false", driver.findElement(AppiumBy.xpath("(//android.widget.ImageView)[1]")).getAttribute("focusable"),
                "First image is still focused after swiping left");
    }


}

