package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTests extends TestBase {

    @Test
    public void loginTestPositive () {
        driver.findElement(By.linkText("Log In")).click();
        waitUntilElementIsClickable(By.id("login"),10);

        WebElement loginField = driver.findElement(By.id("user"));
        loginField.sendKeys(LOGIN);
        waitUntilAttributeValueIs(By.id("login"),"value","Log in with Atlassian",10);

        WebElement loginButton = driver.findElement(By.id("login"));
        loginButton.click();
        waitUntilElementIsClickable(By.id("login-submit"),15);

        WebElement passwordField = driver.findElement(By.id("password"));
        passwordField.sendKeys(PASSWORD);
        WebElement loginSubmitButton = driver.findElement(By.id("login-submit"));
        loginSubmitButton.click();
        waitUntilElementIsClickable(By.xpath("//button[@data-test-id='header-boards-menu-button']/span[2]"),40);

        WebElement boardIcon = driver.findElement(By.xpath("//button[@data-test-id='header-boards-menu-button']/span[2]"));
        Assert.assertEquals("Boards", boardIcon.getText(),"Text on the boardIcon is not 'Boards'");
    }



    @Test
    public void NegativeLoginIncorrect() {
        driver.findElement(By.linkText("Log In")).click();
        waitUntilElementIsClickable(By.id("login"),10);

        WebElement loginField = driver.findElement(By.id("user"));
        loginField.sendKeys("gftov0122334556@gmail.com");
        waitUntilAttributeValueIs(By.id("login"),"value","Log in",10);

        WebElement loginButton = driver.findElement(By.id("login"));
        loginButton.click();
        waitUntilElementIsVisible(By.xpath("//div[@id = 'error']/p"),20);

        WebElement errorMessage = driver.findElement(By.xpath("//div[@id = 'error']/p"));
        System.out.println("Error message: " + errorMessage.getText());
        Assert.assertEquals("There isn't an account for this email",errorMessage.getText(),"Error text is not 'There isn't an account for this email'");
    }
    @Test
    public void NegativePasswordIncorrect() {
        driver.findElement(By.linkText("Log In")).click();
        waitUntilElementIsClickable(By.id("login"),10);

        WebElement loginField = driver.findElement(By.id("user"));
        loginField.sendKeys(LOGIN);
        waitUntilAttributeValueIs(By.id("login"),"value","Log in with Atlassian",10);

        WebElement loginButton = driver.findElement(By.id("login"));
        loginButton.click();
        waitUntilElementIsClickable(By.id("login-submit"),15);

        WebElement passwordField = driver.findElement(By.id("password"));
        passwordField.sendKeys("Victor");

        WebElement loginSubmitButton = driver.findElement(By.id("login-submit"));
        loginSubmitButton.click();
        waitUntilElementIsVisible(By.cssSelector("#login-error>span"),10);

        WebElement errorMessage = driver.findElement(By.cssSelector("#login-error>span"));
        System.out.println("Error message: " + errorMessage.getText());
        Assert.assertTrue(errorMessage.getText().contains("Incorrect email address and / or password."),"There is no error message or the text of the message is not correct");
    }
    @Test
    public void loginNegativeNoLoginNoPassword() {
        driver.findElement(By.linkText("Log In")).click();
        waitUntilElementIsClickable(By.id("login"),10);

        driver.findElement(By.id("login")).click();
        waitUntilElementIsVisible(By.cssSelector("#error>p"),10);

        WebElement errorMessage = driver.findElement(By.cssSelector("#error>p"));
        System.out.println("Error message: " + errorMessage.getText());
        Assert.assertTrue(errorMessage.getText().equals("Missing email"));
    }
}
