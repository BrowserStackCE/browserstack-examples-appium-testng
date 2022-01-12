package com.browserstack.test.suites.login;

import com.browserstack.test.suites.TestBase;
import io.appium.java_client.MobileBy;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginFailTest extends TestBase {

    @Test
    public void loginFail() {
        getDriver().findElement(MobileBy.AccessibilityId("menu")).click();
        getDriver().findElement(MobileBy.AccessibilityId("nav-signin")).click();

        getDriver().findElement(MobileBy.AccessibilityId("username-input")).click();
        mobileHelper.selectFromPickerWheel("//XCUIElementTypePickerWheel[@value='Accepted usernames are']", "fav_user");

        getDriver().findElement(MobileBy.AccessibilityId("password-input")).click();
        mobileHelper.selectFromPickerWheel("//XCUIElementTypePickerWheel[@value='Password for all users']", "testingisfun99");

        getDriver().findElement(MobileBy.AccessibilityId("login-btn")).click();
        getDriver().findElement(MobileBy.AccessibilityId("menu")).click();
        Assert.assertEquals(getDriver().findElement(MobileBy.AccessibilityId("username")).getText(), "Welcome");
    }
}
