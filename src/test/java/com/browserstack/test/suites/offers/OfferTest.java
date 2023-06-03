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
    public void checkOffersInSingaporeNativeApp() throws InterruptedException {
        By offersMenuItem = mobileHelper.isAndroid() ? MobileBy.xpath("//*[@text = 'Offers']") : MobileBy.id("Offers");

        driver.findElement(MobileBy.AccessibilityId("menu")).click();
        driver.findElement(MobileBy.AccessibilityId("nav-signin")).click();

        driver.findElement(MobileBy.AccessibilityId("username-input")).click();
        mobileHelper.selectFromPickerWheel("//XCUIElementTypePickerWheel[@value='Accepted usernames are']", "fav_user");

        driver.findElement(MobileBy.AccessibilityId("password-input")).click();
        mobileHelper.selectFromPickerWheel("//XCUIElementTypePickerWheel[@value='Password for all users']", "testingisfun99");

        driver.findElement(MobileBy.AccessibilityId("login-btn")).click();

        driver.setLocation(singapore);
        Thread.sleep(5000); // wait, for location change
        
        driver.findElement(MobileBy.AccessibilityId("menu")).click();
        driver.findElement(offersMenuItem).click();
        if (mobileHelper.isiOS()) {
            driver.findElement(By.id("Allow Once")).click();
        } else if (mobileHelper.isAndroid()) {
            driver.findElement(By.id("com.android.permissioncontroller:id/permission_allow_foreground_only_button")).click();
        }

        Assert.assertEquals(driver.findElements(MobileBy.AccessibilityId("offer")).size(), 3);
    }

    @Test
    public void checkOffersInAmsterdamNativeApp() throws InterruptedException {
        By offersMenuItem = mobileHelper.isAndroid() ? MobileBy.xpath("//*[@text = 'Offers']") : MobileBy.id("Offers");
        
        driver.findElement(MobileBy.AccessibilityId("menu")).click();
        driver.findElement(MobileBy.AccessibilityId("nav-signin")).click();
        
        driver.findElement(MobileBy.AccessibilityId("username-input")).click();
        mobileHelper.selectFromPickerWheel("//XCUIElementTypePickerWheel[@value='Accepted usernames are']", "fav_user");
        
        driver.findElement(MobileBy.AccessibilityId("password-input")).click();
        mobileHelper.selectFromPickerWheel("//XCUIElementTypePickerWheel[@value='Password for all users']", "testingisfun99");
        
        driver.findElement(MobileBy.AccessibilityId("login-btn")).click();
        
        driver.setLocation(amsterdam);
        Thread.sleep(5000); // wait, for location change
        
        driver.findElement(MobileBy.AccessibilityId("menu")).click();
        driver.findElement(offersMenuItem).click();
        if (mobileHelper.isiOS()) {
            driver.findElement(By.id("Allow Once")).click();
        } else if (mobileHelper.isAndroid()) {
            driver.findElement(By.id("com.android.permissioncontroller:id/permission_allow_foreground_only_button")).click();
        }

        Assert.assertTrue(driver.findElement(MobileBy.AccessibilityId("no-offers")).isDisplayed());
    }
}
