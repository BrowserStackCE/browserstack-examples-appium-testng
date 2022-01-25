package com.browserstack.test.suites.user;

import com.browserstack.test.suites.TestBase;
import io.appium.java_client.MobileBy;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class UserTest extends TestBase {
    public UserTest() {
    }

    @Test
    public void loginAndCheckExistingOrders() {
        By ordersMenuItem = mobileHelper.isAndroid() ? MobileBy.xpath("//*[@text = 'Orders']") : MobileBy.id("Orders");
        getDriver().findElement(MobileBy.AccessibilityId("menu")).click();
        getDriver().findElement(MobileBy.AccessibilityId("nav-signin")).click();
        getDriver().findElement(MobileBy.AccessibilityId("username-input")).click();
        mobileHelper.selectFromPickerWheel("//XCUIElementTypePickerWheel[@value='Accepted usernames are']", "existing_orders_user");
        getDriver().findElement(MobileBy.AccessibilityId("password-input")).click();
        mobileHelper.selectFromPickerWheel("//XCUIElementTypePickerWheel[@value='Password for all users']", "testingisfun99");
        getDriver().findElement(MobileBy.AccessibilityId("login-btn")).click();
        getDriver().findElement(MobileBy.AccessibilityId("menu")).click();
        getDriver().findElement(ordersMenuItem).click();
        Assert.assertEquals(getDriver().findElement(MobileBy.AccessibilityId("number-of-orders")).getText(), "5 order(s) found.");
    }

    @Test
    public void addToFavourites() {
        By favouritesMenuItem = mobileHelper.isAndroid() ? MobileBy.xpath("//*[@text = 'Favourites']") : MobileBy.id("Favourites");
        getDriver().findElement(MobileBy.AccessibilityId("menu")).click();
        getDriver().findElement(MobileBy.AccessibilityId("nav-signin")).click();
        getDriver().findElement(MobileBy.AccessibilityId("username-input")).click();
        mobileHelper.selectFromPickerWheel("//XCUIElementTypePickerWheel[@value='Accepted usernames are']", "demouser");
        getDriver().findElement(MobileBy.AccessibilityId("password-input")).click();
        mobileHelper.selectFromPickerWheel("//XCUIElementTypePickerWheel[@value='Password for all users']", "testingisfun99");
        getDriver().findElement(MobileBy.AccessibilityId("login-btn")).click();
        mobileHelper.scrollToElement("mark-favourite-12");
        getDriver().findElement(MobileBy.AccessibilityId("mark-favourite-12")).click();
        mobileHelper.scrollToElement("mark-favourite-16");
        getDriver().findElement(MobileBy.AccessibilityId("mark-favourite-16")).click();
        getDriver().findElement(MobileBy.AccessibilityId("menu")).click();
        getDriver().findElement(favouritesMenuItem).click();
        Assert.assertEquals(getDriver().findElement(MobileBy.AccessibilityId("number-of-favourites")).getText(), "2 product(s) marked favourite.");
    }
}
