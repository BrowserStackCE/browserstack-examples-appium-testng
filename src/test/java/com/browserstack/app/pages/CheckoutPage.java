package com.browserstack.app.pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.WebElement;

public class CheckoutPage extends BasePage {
    @AndroidFindBy(accessibility = "firstNameInput")
    @iOSXCUITFindBy(accessibility = "firstNameInput")
    private WebElement firstnameInput;

    @AndroidFindBy(accessibility = "lastNameInput")
    @iOSXCUITFindBy(accessibility = "lastNameInput")
    private WebElement lastnameInput;

    @AndroidFindBy(accessibility = "addressInput")
    @iOSXCUITFindBy(accessibility = "addressInput")
    private WebElement addressInput;

    @AndroidFindBy(accessibility = "stateInput")
    @iOSXCUITFindBy(accessibility = "stateInput")
    private WebElement stateInput;

    @AndroidFindBy(accessibility = "postalCodeInput")
    @iOSXCUITFindBy(accessibility = "postalCodeInput")
    private WebElement postcodeInput;

    @AndroidFindBy(accessibility = "submit-btn")
    @iOSXCUITFindBy(accessibility = "submit-btn")
    private WebElement checkoutButton;

    public CheckoutPage(AppiumDriver<?> driver) {
        super(driver);
    }

    public ConfirmationPage enterShippingDetails(String firstname, String lastname, String address, String state, String postcode) {
        firstnameInput.sendKeys(firstname);
        lastnameInput.sendKeys(lastname);
        addressInput.sendKeys(address);
        stateInput.sendKeys(state);
        postcodeInput.sendKeys(postcode);
        driver.hideKeyboard();
        checkoutButton.click();
        return new ConfirmationPage(driver);
    }
}
