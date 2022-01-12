package com.browserstack.app.pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class CartPage extends BasePage {
    @iOSXCUITFindBy(accessibility = "checkout-btn")
    @AndroidFindBy(accessibility = "checkout-btn")
    private MobileElement checkoutBtn;

    public CartPage(AppiumDriver<?> driver) {
        super(driver);
    }

    public CheckoutPage proceedToCheckout() {
        mobileHelper.scrollToElement("checkout-btn");
        checkoutBtn.click();
        return new CheckoutPage(driver);
    }
}
