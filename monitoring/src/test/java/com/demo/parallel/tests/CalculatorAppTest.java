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
                new Object[]{1, 1, 2},
                new Object[]{3, 4, 7},
                new Object[]{4, 4, 8},
                new Object[]{5, 2, 7},
                new Object[]{2, 2, 8}, //FAILED
                new Object[]{3, 1, 10} //FAILED
        };
    }

    @Test(dataProvider = "testNumbers")
    public void sumUpNumbers(int num1, int num2, int result){
        calPage = new CalculatorPage(this.driver);
        calPage.calculate(num1, num2);
        Assert.assertEquals(calPage.getResult(), result);
    }
}
