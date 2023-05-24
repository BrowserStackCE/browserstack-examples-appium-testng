package com.browserstack.app.pages;

import com.browserstack.test.utils.MobileHelper;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.percy.appium.AppPercy;
import org.openqa.selenium.support.PageFactory;

public class BasePage {
    protected AppiumDriver<?> driver;
    protected MobileHelper mobileHelper;
    protected AppPercy percy;
    public BasePage(AppiumDriver<?> driver,AppPercy percy) {
        this.driver = driver;
        this.mobileHelper = new MobileHelper(driver);
        this.percy = percy;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }
}
