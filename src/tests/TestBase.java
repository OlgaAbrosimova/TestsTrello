package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
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
    public void initTestsSuit() throws InterruptedException {
        driver = new ChromeDriver();
        driver.get("https://trello.com/");
        Thread.sleep(8000);

    }

    @AfterMethod
    public void tearDown(){
        driver.quit();
    }
}
