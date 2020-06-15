package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTests {
    public WebDriver driver;

    @BeforeMethod
    public void initAppl() throws InterruptedException {
        driver = new ChromeDriver();
        driver.get("https://trello.com/");
        Thread.sleep(5000);
        WebElement loginLink = driver.findElement(By.linkText("Log In"));
        loginLink.click();
        Thread.sleep(5000);
    }

    @Test
    public void loginPositive () throws InterruptedException {
        WebElement loginField = driver.findElement(By.id("user"));
        loginField.sendKeys("gftov01@gmail.com");
        Thread.sleep(5000);
        WebElement loginButton = driver.findElement(By.id("login"));
        loginButton.click();
        Thread.sleep(5000);
        WebElement passwordField = driver.findElement(By.id("password"));
        passwordField.sendKeys("Victor100578@");
        Thread.sleep(5000);
        WebElement loginSubmitButton = driver.findElement(By.id("login-submit"));
        loginSubmitButton.click();
        Thread.sleep(20000);
        System.out.println("'Boards' button text: " + driver.findElement(By.xpath("//button[@data-test-id='header-boards-menu-button']/span[2]")).getText());
        Thread.sleep(5000);
    }

    @Test
    public void LoginNegativeNotExistEmail() throws InterruptedException {
        WebElement loginField = driver.findElement(By.id("user"));
        loginField.sendKeys("gftov0122334556@gmail.com");
        Thread.sleep(5000);
        WebElement loginButton = driver.findElement(By.id("login"));
        loginButton.click();
        Thread.sleep(8000);
        System.out.println("Error message: " + driver.findElement(By.cssSelector("#error>p")).getText());
        Thread.sleep(5000);
    }
    @Test
    public void LoginNegativeIncorrectPassword() throws InterruptedException {
        WebElement loginField = driver.findElement(By.id("user"));
        loginField.sendKeys("gftov01@gmail.com");
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
        System.out.println("Error message: " + driver.findElement(By.cssSelector("#login-error>span")).getText());
        Thread.sleep(5000);
    }
    @Test
    public void loginNegativeNoLoginNoPassword() throws InterruptedException {
        driver.findElement(By.id("login")).click();
        Thread.sleep(5000);
        System.out.println("Error message: " + driver.findElement(By.cssSelector("#error>p")).getText());
        Thread.sleep(5000);
    }

    @AfterMethod
    public void tearDown(){
        System.out.println("Title: " + driver.getTitle());
        driver.quit();
    }
}
