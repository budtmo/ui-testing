package com.demo.parallel.tests;


import com.demo.parallel.AndroidTestBase;
import com.demo.parallel.pages.CalculatorPage;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class CalculatorAppTest extends AndroidTestBase {
    private CalculatorPage calPage;

    @DataProvider()
    private Object[][] testNumbers() {
        return new Object[][] {
                new Object[]{1, 2, 3},
                new Object[]{3, 3, 6},
                new Object[]{5, 2, 10}
        };
    }

    @Test(dataProvider = "testNumbers")
    public void sumUpNumbers(int num1, int num2, int result){
        calPage = new CalculatorPage(this.driver);
        calPage.calculate(num1, num2);
        Assert.assertEquals(calPage.getResult(), result);
    }
}
