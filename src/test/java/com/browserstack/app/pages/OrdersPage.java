package com.browserstack.app.pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import io.percy.appium.AppPercy;

import java.util.List;

public class OrdersPage extends BasePage {
    @AndroidFindBy(accessibility = "order-item")
    @iOSXCUITFindBy(accessibility = "order-item")
    private List<MobileElement> orderItems;

    public OrdersPage(AppiumDriver<?> driver, AppPercy percy) {
        super(driver,percy);
    }

    public int getItemsFromOrder() {
        return orderItems.size();
    }
}
