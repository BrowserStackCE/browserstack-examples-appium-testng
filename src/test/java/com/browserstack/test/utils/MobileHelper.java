package com.browserstack.test.utils;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;

public class MobileHelper {
    AppiumDriver<?> driver;

    public MobileHelper(AppiumDriver<?> driver) {
        this.driver = driver;
    }

    public boolean isAndroid() {
        return driver.getPlatformName().equalsIgnoreCase("android");
    }

    public boolean isiOS() {
        return driver.getPlatformName().equalsIgnoreCase("ios");
    }

    public void selectFromPickerWheel(String iOSPicker, String option) {
        if (isAndroid()) {
            driver.findElement(MobileBy.xpath("//*[@text = '" + option + "']")).click();
        } else if (isiOS()) {
            driver.findElement(By.xpath(iOSPicker)).sendKeys(new CharSequence[]{option});
            driver.findElement(By.name("done_button")).click();
        }

    }

    public void swipeLeft(WebElement el) {
        if (isAndroid()) {
            Point loc = el.getLocation();
            Dimension dimension = driver.manage().window().getSize();
            (new TouchAction(driver)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(500L))).press(PointOption.point(dimension.getWidth() - 200, loc.getY())).waitAction(WaitOptions.waitOptions(Duration.ofMillis(500L))).moveTo(PointOption.point(dimension.getWidth() - 500, loc.getY())).release().perform();
        } else if (isiOS()) {
            JavascriptExecutor js = driver;
            Map<String, Object> params = new HashMap();
            params.put("direction", "left");
            params.put("velocity", 2500);
            params.put("element", ((RemoteWebElement)el).getId());
            js.executeScript("mobile: swipe", new Object[]{params});
        }

    }

    public void scrollToElement(String accessibilityId) {
        if (isAndroid()) {
            driver.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true)).setMaxSearchSwipes(25).scrollIntoView(new UiSelector().description(\"" + accessibilityId + "\"))"));
        } else if (isiOS()) {
            RemoteWebElement element = (RemoteWebElement)driver.findElement(By.id(accessibilityId));
            String elementID = element.getId();
            HashMap<String, String> scrollObject = new HashMap();
            scrollObject.put("element", elementID);
            scrollObject.put("direction", "down");
            driver.executeScript("mobile:scroll", new Object[]{scrollObject});
        }

    }
}
