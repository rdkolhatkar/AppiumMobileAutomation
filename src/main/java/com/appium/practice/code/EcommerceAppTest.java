package com.appium.practice.code;

import com.appium.practice.utils.EcommerceAppGenericUtils;
import com.appium.practice.utils.MobileActionUtils;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.annotations.Test;
import org.openqa.selenium.JavascriptExecutor;

import java.util.HashMap;

public class EcommerceAppTest extends EcommerceAppGenericUtils {
    @Test
    public void fillFormInsideEcommerceMobileApplication() throws InterruptedException {
        // Code to fill the form in the e-commerce app
        driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/nameField")).sendKeys("Niranjana");
        // In our emulator when we click on the name field the keyboard is getting opened
        // In our emulator keyboard is not closing after entering the name
        // To go for next field we need to close the keyboard
        driver.hideKeyboard();
        // Now we have to select the gender
        driver.findElement(AppiumBy.xpath("//android.widget.RadioButton[@text='Female']")).click();
        // Now we have to select the country
        // This country is in the dropdown
        // First Click on the dropdown
        driver.findElement(AppiumBy.id("android:id/text1")).click();
        // Now we have to scroll to the country we want to select
        driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Argentina\"));"));
        // Now to click on Argentina we have to find the element by writing the xpath
        driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Argentina']")).click();
        // Now we have to click on the "Let's Shop" button
        driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/btnLetsShop")).click();
        Thread.sleep(2000); // Adding sleep to observe the action, can be removed in production code
    }

    // Using MobileActionUtils class to scroll and find the element
    @Test
    public void fillFormInsideEcommerceApplicationWithMobileActionUtils() throws InterruptedException {
        driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/nameField")).sendKeys("Niranjana");
        driver.hideKeyboard();
        driver.findElement(AppiumBy.xpath("//android.widget.RadioButton[@text='Female']")).click();
        driver.findElement(AppiumBy.id("android:id/text1")).click();
        MobileActionUtils mobileActionUtils = new MobileActionUtils(driver);
        mobileActionUtils.scrollToFindTheElementInDropdownList("Argentina");
        driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/btnLetsShop")).click();
        Thread.sleep(2000);
    }

}
