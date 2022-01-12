package com.browserstack.test.suites.offers;

import com.browserstack.test.suites.TestBase;
import io.appium.java_client.MobileBy;
import org.openqa.selenium.By;
import org.openqa.selenium.html5.Location;
import org.testng.Assert;
import org.testng.annotations.Test;

@Test
public class OfferTest extends TestBase {
    private final Location singapore = new Location(1.0, 103.0, 10.0);
    private final Location amsterdam = new Location(52.36, 4.9, 10.0);

    @Test
    public void checkOffersInSingapore() throws InterruptedException {
        By offersMenuItem = mobileHelper.isAndroid() ? MobileBy.xpath("//*[@text = 'Offers']") : MobileBy.id("Offers");

        getDriver().findElement(MobileBy.AccessibilityId("menu")).click();
        getDriver().findElement(MobileBy.AccessibilityId("nav-signin")).click();

        getDriver().findElement(MobileBy.AccessibilityId("username-input")).click();
        mobileHelper.selectFromPickerWheel("//XCUIElementTypePickerWheel[@value='Accepted usernames are']", "fav_user");

        getDriver().findElement(MobileBy.AccessibilityId("password-input")).click();
        mobileHelper.selectFromPickerWheel("//XCUIElementTypePickerWheel[@value='Password for all users']", "testingisfun99");

        getDriver().findElement(MobileBy.AccessibilityId("login-btn")).click();

        getDriver().setLocation(singapore);
        Thread.sleep(5000); // wait, for location change
        
        getDriver().findElement(MobileBy.AccessibilityId("menu")).click();
        getDriver().findElement(offersMenuItem).click();
        if (mobileHelper.isiOS()) {
            getDriver().findElement(By.id("Allow Once")).click();
        }

        Assert.assertEquals(getDriver().findElements(MobileBy.AccessibilityId("offer")).size(), 3);
    }

    @Test
    public void checkOffersInAmsterdam() throws InterruptedException {
        By offersMenuItem = mobileHelper.isAndroid() ? MobileBy.xpath("//*[@text = 'Offers']") : MobileBy.id("Offers");
        
        getDriver().findElement(MobileBy.AccessibilityId("menu")).click();
        getDriver().findElement(MobileBy.AccessibilityId("nav-signin")).click();
        
        getDriver().findElement(MobileBy.AccessibilityId("username-input")).click();
        mobileHelper.selectFromPickerWheel("//XCUIElementTypePickerWheel[@value='Accepted usernames are']", "fav_user");
        
        getDriver().findElement(MobileBy.AccessibilityId("password-input")).click();
        mobileHelper.selectFromPickerWheel("//XCUIElementTypePickerWheel[@value='Password for all users']", "testingisfun99");
        
        getDriver().findElement(MobileBy.AccessibilityId("login-btn")).click();
        
        getDriver().setLocation(amsterdam);
        Thread.sleep(5000); // wait, for location change
        
        getDriver().findElement(MobileBy.AccessibilityId("menu")).click();
        getDriver().findElement(offersMenuItem).click();
        if (mobileHelper.isiOS()) {
            getDriver().findElement(By.id("Allow Once")).click();
        }

        Assert.assertTrue(getDriver().findElement(MobileBy.AccessibilityId("no-offers")).isDisplayed());
    }
}
