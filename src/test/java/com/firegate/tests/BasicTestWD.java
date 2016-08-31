package com.firegate.tests;

import com.firegate.config.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class BasicTestWD extends DriverFactory {

    private WebDriver driver;

    @BeforeClass
    private void setUp() throws Exception {
        driver = DriverFactory.getDriver();
        driver.get("http://www.google.com");
    }

    @Test(testName = "Google search", priority = 0, enabled = true)
    public void googleCheeseExample() throws Exception {
        WebElement searchField = driver.findElement(By.name("q"));
        searchField.clear();
        searchField.sendKeys("test");
        System.out.println("Page title is: " + driver.getTitle());
        searchField.submit();

        /*(new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driverObject) {
                return driverObject.getTitle().toLowerCase().startsWith("test".toLowerCase());
            }
        });

        System.out.println("Page title is: " + driver.getTitle());*/
    }

    @Test
    public void googleMilkExample() throws Exception {
        //googleExampleThatSearchesFor("Milk!");
    }
}
