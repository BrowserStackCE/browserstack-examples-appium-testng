package com.browserstack.test.suites.login;

import com.browserstack.test.suites.TestBase;
import io.appium.java_client.MobileBy;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginRequestedTest extends TestBase {

    @Test
    public void navigateFavoritesLoginRequested() {
        By favouritesMenuItem = mobileHelper.isAndroid() ? MobileBy.xpath("//*[@text = 'Favourites']") : MobileBy.id("Favourites");

        getDriver().findElement(MobileBy.AccessibilityId("menu")).click();
        getDriver().findElement(favouritesMenuItem).click();
        Assert.assertTrue(getDriver().findElement(MobileBy.AccessibilityId("login-form")).isDisplayed());
    }
}
