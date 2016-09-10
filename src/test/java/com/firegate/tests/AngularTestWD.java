package com.firegate.tests;

import com.firegate.config.DriverFactory;
import com.paulhammant.ngwebdriver.ByAngular;
import com.paulhammant.ngwebdriver.NgWebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;


public class AngularTestWD extends DriverFactory {

    String url = "http://192.168.56.101:3000";


    @Test(testName = "ToDo list - join", enabled = true)
    public void toDoListJoin() throws Exception {
        WebDriver driver;
        driver = DriverFactory.getDriver();
        WebDriverWait wait20 = new WebDriverWait(driver, 50);
        driver.get(url);
        driver.manage().timeouts().setScriptTimeout(30, TimeUnit.SECONDS);
        NgWebDriver ngDriver = new NgWebDriver((JavascriptExecutor) driver);
        //ngDriver.waitForAngular2RequestsToFinish();
        WebElement loginButton = driver.findElement(By.cssSelector("[ng-click=\"login()\"]"));
        loginButton.click();
        driver.findElement(ByAngular.model("user.email")).sendKeys("admin@abc.com");
        driver.findElement(ByAngular.model("user.password")).sendKeys("changeme");
        driver.findElement(By.cssSelector("button.btn.btn-primary.login")).click();
        WebElement elem = new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(driver.findElement(By.cssSelector("a#adminmenu.dropdown-toggle"))));
        elem.click();
        driver.findElement(By.linkText("Manage Projects")).click();
        WebElement newProjectButton = wait20.until(ExpectedConditions.presenceOfElementLocated(ByAngular.buttonText("New Project")));
        newProjectButton.click();
        //Thread.sleep(2000);
        WebElement projectName = wait20.until(ExpectedConditions.presenceOfElementLocated(ByAngular.model("project.name")));
        projectName.sendKeys("DEMO");
        driver.findElement(ByAngular.model("project.desc")).sendKeys("DEMO field");

        Thread.sleep(1000);


    }
}
