package com.demo.parallel.pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class CalculatorPage {
    @AndroidFindBy(id = "first_input")
    public WebElement num1Field;

    @AndroidFindBy(id = "second_input")
    public WebElement num2Field;

    @AndroidFindBy(id = "btn_calculate")
    public WebElement calculateBtn;

    @AndroidFindBy(id = "result")
    public WebElement resultField;

    public CalculatorPage(AppiumDriver driver) {
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    public void calculate(int num1, int num2) {
        num1Field.sendKeys(String.valueOf(num1));
        num2Field.sendKeys(String.valueOf(num2));
        calculateBtn.click();
    }

    public int getResult() {
        int res = 0;

        try {
            res = Integer.parseInt(resultField.getText());
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        return res;
    }
}
