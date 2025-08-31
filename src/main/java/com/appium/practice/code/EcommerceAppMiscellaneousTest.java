package com.appium.practice.code;

import com.appium.practice.utils.EcommerceAppGenericUtils;
import com.appium.practice.utils.MobileActionUtils;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

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

    // Now we have one more test scenario, first we have to add two different products in Ecommerce application and then go to cart and check the total amount displayed is correct or not.
    // Once the amount validation is successful then we have to click on button called "Visit to the website to complete purchase"

    @Test
    public void verifyTotalAmountDisplayedInCart() throws InterruptedException {
        driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/nameField")).sendKeys("Niranjana");
        driver.hideKeyboard();
        driver.findElement(AppiumBy.xpath("//android.widget.RadioButton[@text='Female']")).click();
        driver.findElement(AppiumBy.id("android:id/text1")).click();
        MobileActionUtils mobileActionUtils = new MobileActionUtils(driver);
        mobileActionUtils.scrollToFindTheElementInDropdownList("Argentina");
        driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/btnLetsShop")).click();
        Thread.sleep(1000);
        // Adding First item to cart
        driver.findElements(AppiumBy.xpath("(//android.widget.TextView[@text='ADD TO CART'])")).get(0).click(); // Here we are using .findElements().get() method for calling the index elements
        driver.findElement(AppiumBy.xpath("(//android.widget.TextView[@text='ADD TO CART'])[1]")).click(); // Here we are passing the index values in the xpath itself
        // But here is one catch that we have to understand. First is when we click on the "ADD TO CART" Button it will get disabled for that item and we will be seeing the "ADDED TO CART" to cart message
        // Then the cart item index value for button "ADD TO CART" will change for below products, So in this case .findElements() will not work, because it will give us the error as array out of bound
        // To fix this we can again use the same code ->  driver.findElements(AppiumBy.xpath("(//android.widget.TextView[@text='ADD TO CART'])")).get(0).click();
        // Now we have to go to the cart page
        driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/appbar_btn_cart")).click();
        // Now we have to retrieve the text values of price from each product displayed in the cart and then we have to apply sum function to calculate the Total Amount.
        // Then we will extract the Total Amount Displayed on the cart page and then we have to compare the calculated Total Amount & Extracted Total Amount for validation
        // Extracting price of each item
        List<WebElement> productPrices = driver.findElements(AppiumBy.id("com.androidsample.generalstore:id/productPrice"));
        int productCount = driver.findElements(AppiumBy.id("com.androidsample.generalstore:id/productPrice")).size(); // With this code we can check how many items are present in the cart.
        double sumOfAmountsOfProducts = 0;
        for(int i = 0; i < productCount; i++){
            String retrivedAmountOfEachProduct = productPrices.get(i).getText();
            // Now on Cart page Amount is displayed in "$123" format and getText() method will retrieve it as String, So we have to remove the $ symbol and then convert string to integer or double
            String productAmount = retrivedAmountOfEachProduct.substring(1); // With this step we have successfully removed the $
            Double actualPriceOfProduct = Double.parseDouble(productAmount);
            sumOfAmountsOfProducts = sumOfAmountsOfProducts + actualPriceOfProduct;

        }
        System.out.println(sumOfAmountsOfProducts);
    }
}
