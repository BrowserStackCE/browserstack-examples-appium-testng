package com.browserstack.app.pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import io.percy.appium.AppPercy;
import org.openqa.selenium.WebElement;

public class HomePage extends BasePage {
    @AndroidFindBy(accessibility = "menu")
    @iOSXCUITFindBy(accessibility = "menu")
    private WebElement menuLink;

    @AndroidFindBy(accessibility = "nav-signin")
    @iOSXCUITFindBy(accessibility = "nav-signin")
    private WebElement signInLink;

    @AndroidFindBy(accessibility = "nav-cart")
    @iOSXCUITFindBy(accessibility = "nav-cart")
    private WebElement cartLink;

    @AndroidFindBy(xpath = "//*[@text = 'Orders']")
    @iOSXCUITFindBy(id = "Orders")
    private WebElement ordersLink;

    public HomePage(AppiumDriver<?> driver, AppPercy percy) {
        super(driver,percy);
    }

    public LoginPage navigateToSignIn() {
        menuLink.click();
        signInLink.click();
        percy.screenshot("Login Form");
        return new LoginPage(driver,percy);
    }

    public HomePage addProductToCart(String productId) {
        mobileHelper.scrollToElement("add-to-cart-" + productId);
        driver.findElement(MobileBy.AccessibilityId("add-to-cart-" + productId)).click();
        return this;
    }

    public OrdersPage navigateToOrders() {
        menuLink.click();
        ordersLink.click();
        percy.screenshot("Cart Page");
        return new OrdersPage(driver,percy);
    }

    public CartPage openCart() {
        cartLink.click();
        percy.screenshot("Orders Page");
        return new CartPage(driver,percy);
    }
}
