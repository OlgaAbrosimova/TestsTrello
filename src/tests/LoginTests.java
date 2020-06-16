package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTests extends TestBase {

    @Test
    public void loginTestPositive () throws InterruptedException {
        driver.findElement(By.linkText("Log In")).click();
        Thread.sleep(5000);

        WebElement loginField = driver.findElement(By.id("user"));
        loginField.sendKeys(LOGIN);
        Thread.sleep(2000);
        WebElement loginButton = driver.findElement(By.id("login"));
        loginButton.click();
        Thread.sleep(10000);

        WebElement passwordField = driver.findElement(By.id("password"));
        passwordField.sendKeys(PASSWORD);
        WebElement loginSubmitButton = driver.findElement(By.id("login-submit"));
        loginSubmitButton.click();
        Thread.sleep(35000);

        WebElement boardIcon = driver.findElement(By.xpath("//button[@data-test-id='header-boards-menu-button']/span[2]"));
        System.out.println("'Boards' button text: " + boardIcon.getText());
        Thread.sleep(5000);
        Assert.assertEquals("Boards", boardIcon.getText(),"Text on the boardIcon is not 'Boards'");
    }

    @Test
    public void NegativeLoginIncorrect() throws InterruptedException {
        driver.findElement(By.linkText("Log In")).click();
        Thread.sleep(5000);

        WebElement loginField = driver.findElement(By.id("user"));
        loginField.sendKeys("gftov0122334556@gmail.com");
        Thread.sleep(7000);

        WebElement loginButton = driver.findElement(By.id("login"));
        loginButton.click();
        Thread.sleep(10000);

        WebElement errorMessage = driver.findElement(By.xpath("//div[@id = 'error']/p"));
        System.out.println("Error message: " + errorMessage.getText());
        Thread.sleep(5000);

        Assert.assertEquals("There isn't an account for this email",errorMessage.getText(),"Error text is not 'There isn't an account for this email'");
    }
    @Test
    public void NegativePasswordIncorrect() throws InterruptedException {
        driver.findElement(By.linkText("Log In")).click();
        Thread.sleep(5000);

        WebElement loginField = driver.findElement(By.id("user"));
        loginField.sendKeys(LOGIN);
        Thread.sleep(5000);

        WebElement loginButton = driver.findElement(By.id("login"));
        loginButton.click();
        Thread.sleep(5000);

        WebElement passwordField = driver.findElement(By.id("password"));
        passwordField.sendKeys("Victor");
        Thread.sleep(5000);

        WebElement loginSubmitButton = driver.findElement(By.id("login-submit"));
        loginSubmitButton.click();
        Thread.sleep(8000);

        WebElement errorMessage = driver.findElement(By.cssSelector("#login-error>span"));
        System.out.println("Error message: " + errorMessage.getText());
        Thread.sleep(5000);

        Assert.assertEquals("Incorrect email address and / or password.\n" +
                "Do you need help logging in?",errorMessage.getText(),"Error text is not 'Incorrect email address and / or password.Do you need help logging in?'");
    }
    @Test
    public void loginNegativeNoLoginNoPassword() throws InterruptedException {
        driver.findElement(By.linkText("Log In")).click();
        Thread.sleep(5000);

        driver.findElement(By.id("login")).click();
        Thread.sleep(5000);

        WebElement errorMessage = driver.findElement(By.cssSelector("#error>p"));
        System.out.println("Error message: " + errorMessage.getText());
        Thread.sleep(5000);

        Assert.assertEquals("Missing email",errorMessage.getText(),"Error text is not 'Missing email'");
    }


}
