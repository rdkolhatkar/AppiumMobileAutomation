package com.appium.practice.code;

import com.appium.practice.utils.GenericTestUtils;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;
import org.openqa.selenium.DeviceRotation;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SavingContentInAndroidClipboard extends GenericTestUtils {
    // In this class, we will write code to save content in the Android clipboard.
    // Saving content in the Android clipboard is useful when we want to copy some text from one application and paste it into another application.

    @Test
    public void saveContentInAndroidClipboardForTextCopy() {
        // Here we will copy some text content to the Android clipboard.
        // Then we will paste the copied text content into another application.
        driver.findElement(AppiumBy.accessibilityId("Preference")).click();
        driver.findElement(AppiumBy.xpath("//android.widget.TextView[@content-desc='3. Preference dependencies']")).click();
        driver.findElement(By.id("android:id/checkbox")).click();
        DeviceRotation landscapeRotation = new DeviceRotation(0, 0, 90);
        driver.rotate(landscapeRotation);
        driver.findElement(By.xpath("(//android.widget.RelativeLayout)[2]")).click();
        String wifiAlertTitle = driver.findElement(By.id("android:id/alertTitle")).getText();
        Assert.assertEquals(wifiAlertTitle, "WiFi settings");

        // Here we will write code to save content in the Android clipboard
        driver.setClipboardText("RatnakarWiFi"); // This will save the text "RatnakarWiFi" in the Android clipboard
        // Now we will open the "WiFi settings" popup and paste the copied text into the edit box
        // Note: The setClipboardText() method is used to set the text in the Android clipboard
        // Now we will click on the edit box to paste the copied text
        // Note: The getClipboardText() method is used to get the text from the Android clipboard
        driver.findElement(By.id("android:id/edit")).sendKeys(driver.getClipboardText());
        driver.findElements(AppiumBy.className("android.widget.Button")).get(1).click();
    }
}
