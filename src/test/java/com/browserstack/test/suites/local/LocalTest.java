package com.browserstack.test.suites.local;

import com.browserstack.test.suites.TestBase;
import io.appium.java_client.MobileBy;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LocalTest extends TestBase {

    @BeforeMethod
    public void setLocalApi() {
        By settingsMenuItem = mobileHelper.isAndroid() ? MobileBy.xpath("//*[@text = 'Settings']") : MobileBy.id("Settings");

        driver.findElement(MobileBy.AccessibilityId("menu")).click();
        driver.findElement(settingsMenuItem).click();

        driver.findElement(MobileBy.AccessibilityId("url-tab")).click();
        driver.findElement(MobileBy.AccessibilityId("url-input")).clear();
        driver.findElement(MobileBy.AccessibilityId("url-input")).sendKeys("http://bs-local.com:3000/api/");
        driver.hideKeyboard();
        driver.findElement(MobileBy.AccessibilityId("update-configuration-button")).click();
    }

    @Test
    public void loginMessageTest() {
        driver.findElement(MobileBy.AccessibilityId("menu")).click();
        driver.findElement(MobileBy.AccessibilityId("nav-signin")).click();

        driver.findElement(MobileBy.AccessibilityId("username-input")).click();
        mobileHelper.selectFromPickerWheel("//XCUIElementTypePickerWheel[@value='Accepted usernames are']", "locked_user");

        driver.findElement(MobileBy.AccessibilityId("password-input")).click();
        mobileHelper.selectFromPickerWheel("//XCUIElementTypePickerWheel[@value='Password for all users']", "testingisfun99");

        driver.findElement(MobileBy.AccessibilityId("login-btn")).click();

        WebDriverWait wait = new WebDriverWait(driver, 20);
        Assert.assertTrue(wait.until(ExpectedConditions.textToBe(MobileBy.AccessibilityId("api-error"), "Something went wrong.")));
    }

}
