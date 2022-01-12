package com.browserstack.test.suites.product;

import com.browserstack.test.suites.TestBase;
import io.appium.java_client.MobileBy;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CartTest extends TestBase {

    @Test
    public void deleteProductFromCart() {
        By deleteButton = mobileHelper.isAndroid() ? MobileBy.xpath("//*[@text = 'Delete']") : MobileBy.id("Delete");
        
        mobileHelper.scrollToElement("add-to-cart-16");
        getDriver().findElement(MobileBy.AccessibilityId("add-to-cart-16")).click();
        
        getDriver().findElement(MobileBy.AccessibilityId("nav-cart")).click();
        
        mobileHelper.swipeLeft(getDriver().findElement(MobileBy.AccessibilityId("cart-item")));
        getDriver().findElement(deleteButton).click();
        
        Assert.assertEquals(getDriver().findElement(MobileBy.AccessibilityId("number-of-products")).getText(), "0 product(s) found.");
    }
}
