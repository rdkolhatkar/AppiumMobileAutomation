package com.appium.practice.code;

import com.appium.practice.utils.MobileActionUtils;
import io.appium.java_client.AppiumBy;
import org.testng.Assert;
import org.testng.annotations.Test;

public class HandlingToastMessagesInMobileApp extends EcommerceAppTest{
    // Toast messages are brief notifications that appear on the screen for a short duration and then disappear in Android mobile applications.
    // For example, when a user performs an action like saving a file or sending a message, a toast message may appear to confirm that the action was successful.
    // Another example is when a user tries to perform an invalid action, such as entering incorrect login credentials, a toast message may appear to inform the user of the error.
    // In Android, Whoever creates the toast message is responsible for giving a unique tag to it.
    // For Example, In our app, we have unique tag as "android.widget.Toast" for toast messages.
    // To handle toast messages in Appium, we can use the following code snippet:

    @Test
    public void handleToastMessageInAndroidMobileApp() throws InterruptedException {
        driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/nameField")).sendKeys("");
        driver.hideKeyboard();
        driver.findElement(AppiumBy.xpath("//android.widget.RadioButton[@text='Female']")).click();
        driver.findElement(AppiumBy.id("android:id/text1")).click();
        MobileActionUtils mobileActionUtils = new MobileActionUtils(driver);
        mobileActionUtils.scrollToFindTheElementInDropdownList("Argentina");
        driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/btnLetsShop")).click();
        Thread.sleep(2000);
        // XPath of the toast message & Retriving the text of the toast message
        // Here getAttribute("name") is used to get the text of the toast message
        // Here .getText() will not working to get the text of the toast message
        String toastMessage = driver.findElement(AppiumBy.xpath("//android.widget.Toast[1]")).getAttribute("name");
        Assert.assertEquals(toastMessage, "Please enter your name");

    }
}
