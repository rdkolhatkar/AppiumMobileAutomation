package com.appium.practice.code;

import com.appium.practice.utils.MobileBrowserGenericUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.annotations.Test;

public class MobileBrowserAppTest extends MobileBrowserGenericUtils {
    // Every Android or i0S device has a default browser app
    // We can use that browser app to launch a URL
    // Here we will use the Selenium incorporated in Appium to launch a URL in the default browser app
    @Test
    public void mobileBrowserApplicationTest() throws InterruptedException {
        driver.get("http://google.com");
        System.out.println("Title of the page is: "+driver.getTitle());
        driver.findElement(By.name("q")).sendKeys("Rahul Shetty Academy");
        driver.findElement(By.name("q")).sendKeys(Keys.ENTER);
        Thread.sleep(2000);
        // In mobile browser Testing Appium inspector cannot help in identifying the xpath and locators of the website.
        // So for that we have to go to use the traditional selenium concepts
        // Refer the important notes section for detailed description.
    }
    @Test
    public void mobileBrowserFunctionalityTestForAndroidApp(){

    }
}
