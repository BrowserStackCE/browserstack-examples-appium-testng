package com.browserstack.app.pages;

import com.browserstack.test.utils.MobileHelper;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;

public class BasePage {
    protected AppiumDriver<?> driver;
    protected MobileHelper mobileHelper;

    public BasePage(AppiumDriver<?> driver) {
        this.driver = driver;
        this.mobileHelper = new MobileHelper(driver);
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }
}
