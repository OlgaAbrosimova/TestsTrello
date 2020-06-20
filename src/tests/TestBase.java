package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class TestBase {
    public WebDriver driver;
    public static final String BOARD_TITLE = "QA Haifa56";
    public static final String LOGIN = "gftov01@gmail.com";
    public static final String PASSWORD = "Victor100578@";
    public static final String NAME_TITLE = "Oльга Абросимова (olgaabrosimova1)";
    public static final String USERNAME_TITLE = "Oльга Абросимова";


    @BeforeMethod
    public void initTestsSuit() {
        driver = new ChromeDriver();
        driver.get("https://trello.com/");
        waitUntilElementIsClickable(By.linkText("Log In"),20);
    }


    @AfterMethod
    public void tearDown(){
        driver.quit();
    }

    public void waitUntilElementIsClickable(By locator, int time) {
        try {
            new WebDriverWait(driver,time).until(ExpectedConditions.elementToBeClickable(locator));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void waitUntilElementIsVisible(By locator, int time) {
        try {
            new WebDriverWait(driver,time).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void waitUntilAttributeValueIs(By locator, String attribute, String value, int time) {
        try {
            new WebDriverWait(driver,time).until(ExpectedConditions.attributeToBe(locator,attribute,value));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
