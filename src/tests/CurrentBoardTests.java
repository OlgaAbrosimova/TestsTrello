package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CurrentBoardTests {
    public WebDriver driver;

    @BeforeMethod
    public void initTests() throws InterruptedException {
        driver = new ChromeDriver();
        driver.get("https://trello.com/");
        Thread.sleep(10000);
        driver.findElement(By.linkText("Log In")).click();
        Thread.sleep(5000);
        driver.findElement(By.id("user")).sendKeys("gftov01@gmail.com");
        Thread.sleep(5000);
        driver.findElement(By.id("login")).click();
        Thread.sleep(5000);
        driver.findElement(By.id("password")).sendKeys("Victor100578@");
        Thread.sleep(5000);
        driver.findElement(By.id("login-submit")).click();
        Thread.sleep(30000);
        driver.findElement(By.xpath("//div[@class='board-tile-details-name']//div[contains(text(),'QA Haifa56')]")).click();
        Thread.sleep(5000);
    }

    @Test
    public void createNewList() throws InterruptedException {
        int numberOfLists = driver.findElements(By.cssSelector("div.list-header-target.js-editing-target")).size();
        System.out.println("Number of lists: " + numberOfLists);
        Thread.sleep(5000);
        driver.findElement(By.xpath("//span[@class='placeholder']")).click();
        Thread.sleep(5000);
        driver.findElement(By.cssSelector("input.list-name-input")).sendKeys("Tasks in process");
        Thread.sleep(5000);
        driver.findElement(By.xpath("//input[@class='primary mod-list-add-button js-save-edit']")).click();
        Thread.sleep(5000);
        int numberOfListsAfterAdd = driver.findElements(By.cssSelector("div.list-header-target.js-editing-target")).size();
        System.out.println("Number of lists after adding: " + numberOfListsAfterAdd);
        Thread.sleep(5000);
        if (numberOfListsAfterAdd == (numberOfLists + 1)){
            System.out.println("Test passed - number of lists increased by 1");
        }else {
            System.out.println("Test failed");
        }

    }

    @Test
    public void createNewCard() throws InterruptedException {
        int numberOfLists = driver.findElements(By.cssSelector("div.list-header-target.js-editing-target")).size();
        if (numberOfLists == 0){
            createNewList();
        }
        int numberOfCards = driver.findElements(By.cssSelector("a.list-card.js-member-droppable.ui-droppable")).size();
        System.out.println("Number of cards: " + numberOfCards);
        Thread.sleep(5000);
        if (numberOfCards == 0){
            driver.findElement(By.cssSelector("span.js-add-a-card")).click();
        } else {
            driver.findElement(By.cssSelector("span.js-add-another-card")).click();
        }
        Thread.sleep(5000);
        driver.findElement(By.cssSelector("textarea.list-card-composer-textarea.js-card-title")).sendKeys("Testing list and card");
        Thread.sleep(5000);
        driver.findElement(By.cssSelector("input.primary.confirm.mod-compact.js-add-card")).click();
        Thread.sleep(5000);
        int numberOfCardsAfterAdd = driver.findElements(By.cssSelector("a.list-card.js-member-droppable.ui-droppable")).size();
        System.out.println("Number of cards after adding: " + numberOfCardsAfterAdd);
        Thread.sleep(5000);
        if (numberOfCardsAfterAdd == (numberOfCards + 1)){
            System.out.println("Test passed - number of cards increased by 1");
        }else {
            System.out.println("Test failed");
        }
    }

    @AfterMethod
    public void tearDown(){
        System.out.println("Title: " + driver.getTitle());
        driver.quit();
    }
}
