package com.appium.practice.code;

import com.appium.practice.utils.EcommerceAppGenericUtils;
import com.appium.practice.utils.EcommerceAppReusableMethods;
import com.appium.practice.utils.MobileActionUtils;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;
import java.util.Set;

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
        // Now we have to fetch the Total Amount displayed on the Cart page
        String totalAmountDisplayedOnCartPage = driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/totalAmountLbl")).getText();
        // Same problem as before it will come as a String with $ value
        // Implementing reusable method from EcommerceAppReusableMethods class
        EcommerceAppReusableMethods ecommerceAppReusableMethods = new EcommerceAppReusableMethods();
        double formattedTotalAmountDisplayedOnCartPage = ecommerceAppReusableMethods.getFormattedAmounts(totalAmountDisplayedOnCartPage);
        Assert.assertEquals(sumOfAmountsOfProducts, formattedTotalAmountDisplayedOnCartPage);
    }
    @Test
    public void longPressOnEcommerceAppTermsOfConditionsButton() throws InterruptedException {
        driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/nameField")).sendKeys("Niranjana");
        driver.hideKeyboard();
        driver.findElement(AppiumBy.xpath("//android.widget.RadioButton[@text='Female']")).click();
        driver.findElement(AppiumBy.id("android:id/text1")).click();
        MobileActionUtils mobileActionUtils = new MobileActionUtils(driver);
        mobileActionUtils.scrollToFindTheElementInDropdownList("Argentina");
        driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/btnLetsShop")).click();
        Thread.sleep(1000);
        // Adding items to cart
        driver.findElements(AppiumBy.xpath("(//android.widget.TextView[@text='ADD TO CART'])")).get(0).click();
        driver.findElement(AppiumBy.xpath("(//android.widget.TextView[@text='ADD TO CART'])[1]")).click();
        // Now we have to go to the cart page
        driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/appbar_btn_cart")).click();
        // Navigate to terms of Conditions
        WebElement termsOfConditionsButton = driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/termsButton"));
        // Applying Gesture of Long Press
        mobileActionUtils.longPressOnElement(termsOfConditionsButton, 2000);
        // Alert Message will display which contains the Terms Of Conditions.
        // Extract the Title of Alert message and close the Alert PopUp
        String TermsOfConditionsAlertMessageTitle = driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/alertTitle")).getText();
        System.out.println(TermsOfConditionsAlertMessageTitle);
        driver.findElement(AppiumBy.id("android:id/button1")).click();
        // Now click on the CheckBox & Placing the order using purchase button
        driver.findElement(AppiumBy.className("android.widget.CheckBox")).click();
        driver.findElement(By.id("com.androidsample.generalstore:id/btnProceed")).click();
        Thread.sleep(2000);
    }

    @Test
    public void hybridAppWebBrowserRenderingTest() throws InterruptedException {
        /*
        HybridApp → The app under test is a hybrid application (built using web technologies like HTML, CSS, JavaScript, but packaged in a native shell).
        WebBrowserRendering → The test focuses on how the hybrid app’s embedded browser component (WebView/WKWebView/Chromium engine) renders the UI.
        */
        // In our case Ecommerce app is a hybrid app because when we click on the "Visit to the website to complete purchase" button it will navigate us to a webview page
        // When clicking on the button "Visit to the website to complete purchase" it will open a Google page inside the Mobile device
        // By default android driver will not have the knowledge of webview page, So we have to switch the context from native app to webview
        driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/nameField")).sendKeys("Niranjana");
        driver.hideKeyboard();
        driver.findElement(AppiumBy.xpath("//android.widget.RadioButton[@text='Female']")).click();
        driver.findElement(AppiumBy.id("android:id/text1")).click();
        MobileActionUtils mobileActionUtils = new MobileActionUtils(driver);
        mobileActionUtils.scrollToFindTheElementInDropdownList("Argentina");
        driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/btnLetsShop")).click();
        driver.findElements(AppiumBy.xpath("(//android.widget.TextView[@text='ADD TO CART'])")).get(0).click();
        driver.findElement(AppiumBy.xpath("(//android.widget.TextView[@text='ADD TO CART'])[1]")).click();
        driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/appbar_btn_cart")).click();
        driver.findElement(AppiumBy.className("android.widget.CheckBox")).click();
        driver.findElement(By.id("com.androidsample.generalstore:id/btnProceed")).click();
        Thread.sleep(6000); // Waiting for 6 seconds to load the chrome browser page
        // Now we have to switch the context from native app to webview
        Set<String> AppContextViews = driver.getContextHandles(); // This will give us the list of all contexts available in the app
        // Now we have to print each of the context available in the app
        for(String AppContextNames : AppContextViews){
            System.out.println(AppContextNames);
        }// We need to for loop to identify the correct name of the webview context
        driver.context("WEBVIEW_com.androidsample.generalstore"); // This will switch the context to webview
        // Here the driver expects the ChromeDriver.exe file to be present in the system, So we have to set the path of chromedriver.exe in the system variable
        // In our Error log Emulator is a required ChromeDriver version as 95.0.4638.54 but in our system we have 116 version, So we have to download the required version of chromedriver.exe file and set the path in the system variable
        // error: No Chromedriver found that can automate Chrome '95.0.4638'
        // This we will add in our "EcommerceAppGenericUtils.class"
        // Now we will search on google page for "Rahul Shetty Academy"
        driver.findElement(By.name("q")).sendKeys("Rahul Shetty Academy");
        driver.findElement(By.name("q")).sendKeys(Keys.ENTER);
        // Now after searching we have to capture the title of the page and then go back to native app
        Thread.sleep(4000);
        System.out.println(driver.getTitle());
        // Now go back to mobile App
        driver.pressKey(new KeyEvent(AndroidKey.BACK));
        // Now switch context to native app
        driver.context("NATIVE_APP");
    }
}
