package com.appium.practice.code;

import com.appium.practice.utils.GenericTestUtils;
import com.google.common.collect.ImmutableMap;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URISyntaxException;

public class GestureOfLongPressOnElements extends GenericTestUtils {

    @Test
    public void longPressOnPeopleNamesButtonInAndroidApp() throws MalformedURLException, URISyntaxException {
        appiumConfiguration();
        // Click on Views Option
        driver.findElement(AppiumBy.accessibilityId("Views")).click();
        // Click on Expandable Lists option
        driver.findElement(By.xpath("//android.widget.TextView[@text='Expandable Lists']")).click();
        // Click on Custom Adapter option
        driver.findElement(AppiumBy.accessibilityId("1. Custom Adapter")).click();
        // Now we have to perform the long press gesture on Android app button "People Names"
        // We have to click and hold on the element called "People Names" so that we can see the settings popup
        // For long press we have to inject the "JavaScript" in our mobile App
        // For long press we have to give the map of key-value pairs which will specify the exact position of element inside the screen
        // We can specify x & y co-ordinates of element
        // Also we have to specify the time which means for how long we have to hold the button after pressing that means the exact duration after which popup will become visible
        // executeScript() -> this method will allow us to inject the JavaScript
        // Here we are storing the Android Mobile App Element "//android.widget.ExpandableListView[@text='People Names']" into an WebElement
        // As we are storing the Android Mobile App Element inside the WebElement, we are doing type casting as (RemoteWebElement) in the ((RemoteWebElement)peopleNameAndroidAppElement)
        // Below is the standard syntax of the code which we have to use for Long Press
        /*
        RemoteWebElement longPress = (RemoteWebElement) driver.findElement(AppiumBy.accessibilityId("longpress"));
        driver.executeScript("gesture: longPress", Map.of("elementId", longPress.getId(), "pressure", 0.5, "duration", 800));
        */
        WebElement peopleNameAndroidAppElement = driver.findElement(By.xpath("//android.widget.ExpandableListView[@text='People Names']"));
        ((JavascriptExecutor)driver).executeScript(
                "mobile: longClickGesture",
                ImmutableMap.of(
                        "elementId",((RemoteWebElement)peopleNameAndroidAppElement).getId(),
                        "duration",2000
                )
        );
    }

}
