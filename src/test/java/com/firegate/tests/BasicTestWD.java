package com.firegate.tests;

import com.firegate.config.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;


public class BasicTestWD extends DriverFactory {

    String url = "http://192.168.56.101:3000";


    @Test(testName = "ToDo list - join", enabled = true)
    public void toDoListJoin() throws Exception {
        WebDriver driver;
        driver = DriverFactory.getDriver();
        driver.get(url);

        WebElement weJoinButton = new WebDriverWait(driver, 10)
                .until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath(".//*[@id='menu']/div[1]/a[2]"))));
        weJoinButton.click();
        assertThat(driver.findElement(By.className("title-auth")).getText()).isEqualTo("Join");
        driver.findElement(By.id("at-field-email")).sendKeys("demo@gmail.com");
        driver.findElement(By.id("at-field-password")).sendKeys("demo123");
        driver.findElement(By.id("at-field-password_again")).sendKeys("demo123");
        driver.findElement(By.className("btn-primary")).click();
    }

    @Test(testName = "ToDo list - duplicated used", priority = 1, enabled = true)
    public void toDoListJoinDuplicatedUser() throws Exception {

        WebDriver driver = DriverFactory.getDriver();
        driver.get(url);
        WebElement weJoinButton = new WebDriverWait(driver, 10)
                .until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath(".//*[@id='menu']/div[1]/a[2]"))));
        weJoinButton.click();
        WebElement title = new WebDriverWait(driver, 10)
                .until(ExpectedConditions.presenceOfElementLocated((By.className("title-auth"))));
        assertThat(title.getText()).isEqualTo("Join");
        WebElement email = driver.findElement(By.id("at-field-email"));
        WebElement password = driver.findElement(By.id("at-field-password"));
        WebElement password_again = driver.findElement(By.id("at-field-password_again"));

        email.clear();
        password.clear();
        password_again.clear();
        email.sendKeys("demo@gmail.com");
        password.sendKeys("demo123");
        password_again.sendKeys("demo123");
        driver.findElement(By.className("btn-primary")).click();

        WebElement duplicated_user = new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfElementLocated(By.className("list-errors")));
        assertThat(duplicated_user.getText()).isEqualToIgnoringCase("EMAIL ALREADY EXISTS.");
    }

    @Test(testName = "ToDo list - sign in", priority = 2, enabled = true)
    public void toDoListSignIn() throws Exception {

        WebDriver driver = DriverFactory.getDriver();
        driver.get(url);
        WebElement weSignInButton = new WebDriverWait(driver, 15)
                .until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath(".//*[@id='menu']/div[1]/a[1]"))));
        weSignInButton.click();
        WebElement elem = new WebDriverWait(driver, 15).until(ExpectedConditions.presenceOfElementLocated(By.className("title-auth")));
        assertThat(elem.getText()).isEqualTo("Sign In");
        driver.findElement(By.id("at-field-email")).sendKeys("demo@gmail.com");
        driver.findElement(By.id("at-field-password")).sendKeys("demo123");
        driver.findElement(By.className("btn-primary")).click();
        WebElement newList = new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(By.cssSelector("a.js-new-list.link-list-new")));
        newList.click();
        Thread.sleep(600);
        WebElement newListCreated = new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(By.cssSelector("a[title='List A']")));
        newListCreated.click();
        Thread.sleep(500);
        driver.findElement(By.xpath("//div[@id='content-container']/div/div/nav/div/h1")).click();
        driver.findElement(By.name("name")).clear();
        driver.findElement(By.name("name")).sendKeys("RENAMED LIST");
        driver.findElement(By.name("name")).submit();
        Thread.sleep(500);
        WebElement form = driver.findElement(By.xpath("//*[@id=\"content-container\"]/div/div/nav/form/input"));
        form.clear();
        form.sendKeys("DEMO");
        form.submit();

    }
}
