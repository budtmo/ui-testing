package appium.android.parallel.testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.*;
import appium.android.parallel.AndroidTestBase;


public class MSiteChromeTest extends AndroidTestBase {

    @Test
    public void searchForAppiumConf(){
        driver.get("https://budtmo.github.io/");

        driver.findElement(By.linkText("About")).click();

        driver.findElement(By.xpath("/html/body/div/div/p[2]")).isDisplayed();
        driver.findElement(By.linkText("Home")).isDisplayed();
    }
}
