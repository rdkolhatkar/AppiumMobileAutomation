package com.appium.practice.code;

import com.appium.practice.utils.EcommerceAppGenericUtils;
import com.appium.practice.utils.MobileActionUtils;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class EcommerceAppMiscellaneousTest extends EcommerceAppGenericUtils {
    // First we have to login to the Ecommerce app
    // Then we have to add items to the cart
    // To add items to the cart we have to scroll to the item and then click on the add to cart button
    // But we don't know the exact position of the item so we have to scroll until we find the item
    // After adding the items to the cart we have to go to the cart page

    @Test
    public void miscellaneousEcommerceAppTest() throws InterruptedException {
        driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/nameField")).sendKeys("Niranjana");
        driver.hideKeyboard();
        driver.findElement(AppiumBy.xpath("//android.widget.RadioButton[@text='Female']")).click();
        driver.findElement(AppiumBy.id("android:id/text1")).click();
        MobileActionUtils mobileActionUtils = new MobileActionUtils(driver);
        mobileActionUtils.scrollToFindTheElementInDropdownList("Argentina");
        driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/btnLetsShop")).click();
        Thread.sleep(1000);
        // Scrolling to the item "Jordan 6 Rings" and adding it to the cart
        driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Jordan 6 Rings\"));"));
        // As this is a list of items we have to find the parent of the item and then find the add to cart button
        // To identify the correct Add to Cart button we can use xpath with parent-child relationship
        // For that first we have to get all product names and then iterate through them to find the correct product
        // Then we can click on the corresponding Add to Cart button
        int productCount = driver.findElements(AppiumBy.id("com.androidsample.generalstore:id/productName")).size();
        for (int i = 0; i < productCount; i++) {
            String productName = driver.findElements(AppiumBy.id("com.androidsample.generalstore:id/productName")).get(i).getText();
            if (productName.equalsIgnoreCase("Jordan 6 Rings")) {
                driver.findElements(AppiumBy.id("com.androidsample.generalstore:id/productAddCart")).get(i).click();
                break; // Exit the loop once the item is found and added to the cart
            }
        }
        // Now we have to go to the cart page
        driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/appbar_btn_cart")).click();
        // Thread.sleep(2000); // Adding sleep to observe the action, can be removed in production code
        // Here the product id is same for both products page and carts page, So there might be a chance that it will fetch the element text before cart page is loaded.
        // So we have to wait till cart page is loaded completely.
        // Now we have to apply wait till next page is fully loaded. We can put a check in the code which will wait till page title changes from "Products" to "Cart"
        // Now to use the explicit wait we have to install selenium-support library.
        WebDriverWait webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(5));
        webDriverWait.until(
                ExpectedConditions.attributeContains(
                driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/toolbar_title")),
                "text",
                "Cart"
            )
        );
        // Now we are inside the Cart Page
        // Now we have to validate if item is present in the cart or not by using assertions
        String cartPageProduct = driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/productName")).getText();
        Assert.assertEquals(cartPageProduct, "Jordan 6 Rings");
    }
}
