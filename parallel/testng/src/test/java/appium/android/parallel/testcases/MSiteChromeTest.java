package appium.android.parallel.testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.*;
import appium.android.parallel.AndroidTestBase;


public class MSiteChromeTest extends AndroidTestBase {

    @Test
    public void searchForAppiumConf(){
        driver.get("https://www.google.com/");

        WebElement element = driver.findElement(By.name("q"));
        element.sendKeys("AppiumConf");
        element.submit();

        driver.findElement(By.id("ires")).isDisplayed();
    }
}
