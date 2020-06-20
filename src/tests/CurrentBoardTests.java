package tests;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class CurrentBoardTests extends TestBase {

    @BeforeMethod
    public void initTests() {
        driver.findElement(By.linkText("Log In")).click();
        waitUntilElementIsClickable(By.id("login"),10);

        driver.findElement(By.id("user")).sendKeys(LOGIN);
        waitUntilAttributeValueIs(By.id("login"),"value","Log in with Atlassian",10);
        driver.findElement(By.id("login")).click();
        waitUntilElementIsClickable(By.id("login-submit"),15);

        driver.findElement(By.id("password")).sendKeys(PASSWORD);
        driver.findElement(By.id("login-submit")).click();
        waitUntilElementIsClickable(By.xpath("//button[@data-test-id='header-boards-menu-button']/span[2]"),40);

        WebElement ourBoard = driver.findElement(By.xpath(boardLocator(BOARD_TITLE)));
        ourBoard.click();
        waitUntilElementIsVisible(By.xpath("//span[contains(text(),'QA Haifa56')]"),10);
        waitUntilElementIsClickable(By.xpath("//span[@class='placeholder']"),10);
    }

    @Test
    public void createNewList() {
        List<WebElement> listLists = driver.findElements(By.cssSelector("div.list.js-list-content"));
        int beforeAdding = listLists.size();
        System.out.println("Lists before adding: " + beforeAdding);

        WebElement addListOption = driver.findElement(By.xpath("//span[@class='placeholder']"));
        addListOption.click();
        waitUntilElementIsVisible(By.xpath("//input[@placeholder='Enter list title...']"),10);
        WebElement addTitleField = driver.findElement(By.xpath("//input[@placeholder = 'Enter list title...']"));

        addTitleField.click();
        addTitleField.sendKeys("Tasks in process");
        waitUntilElementIsClickable(By.xpath("//input[@type='submit']"),10);

        WebElement addListButton = driver.findElement(By.xpath("//input[@type='submit']"));
        addListButton.click();
        waitUntilElementIsClickable(By.xpath("//a[@class='icon-lg icon-close dark-hover js-cancel-edit']"),10);

        WebElement cancelEdit = driver.findElement(By.xpath("//a[@class='icon-lg icon-close dark-hover js-cancel-edit']"));
        cancelEdit.click();

        listLists = driver.findElements(By.cssSelector("div.list.js-list-content"));
        int afterAdding = listLists.size();
        System.out.println("Lists after adding: " + afterAdding);
        Assert.assertEquals(afterAdding, beforeAdding+1,
                "The quantity of lists before adding new list is not the same as the quantity after adding");
    }

    @Test
    public void createNewCard() {
        List<WebElement> listCards = driver.findElements(By.cssSelector("a.list-card.js-member-droppable.ui-droppable"));
        int beforeAdding = listCards.size();
        System.out.println("Cards before adding: " + beforeAdding);

        Boolean existList = false;
        if (driver.findElement(By
                .xpath("//span[@class='placeholder']")).getText().contains("another"))
        {
            existList = true;
        }
            if (!existList){
                System.out.println("Lists before adding: " + driver.
                        findElements(By.xpath("//div[@class = 'list js-list-content']")).size());
                WebElement addListOption = driver.findElement(By.xpath("//span[@class='placeholder']"));
                addListOption.click();
                waitUntilElementIsVisible(By.xpath("//input[@placeholder='Enter list title...']"),10);
                WebElement addTitleField = driver.findElement(By.xpath("//input[@placeholder = 'Enter list title...']"));

                addTitleField.click();
                addTitleField.sendKeys("Tasks in process");
                waitUntilElementIsClickable(By.xpath("//input[@type='submit']"),10);

                WebElement addListButton = driver.findElement(By.xpath("//input[@type='submit']"));
                addListButton.click();
                waitUntilElementIsClickable(By.xpath("//a[@class='icon-lg icon-close dark-hover js-cancel-edit']"),10);

                WebElement cancelEdit = driver.findElement(By.xpath("//a[@class='icon-lg icon-close dark-hover js-cancel-edit']"));
                cancelEdit.click();
                System.out.println("Lists after adding: " + driver.
                        findElements(By.xpath("//div[@class = 'list js-list-content']")).size());
            }
       waitUntilElementIsVisible(By.xpath("//div[@class='list-header-target js-editing-target']") ,10);

       WebElement addCardButton = driver.findElement(By.cssSelector("span.js-add-a-card"));
       WebElement addAnotherCardButton = driver.findElement(By.cssSelector("span.js-add-another-card"));
       if(addCardButton.isDisplayed()){
           addCardButton.click();
       }
       else addAnotherCardButton.click();
       waitUntilElementIsVisible(By.cssSelector("textarea.list-card-composer-textarea"),10);

       WebElement textCurrentCard = driver.findElement(By.cssSelector("textarea.list-card-composer-textarea"));
       textCurrentCard.sendKeys("New card");
       waitUntilElementIsClickable(By.xpath("//input[@type='submit'][@value = 'Add Card']"),10);

       WebElement submitCardButton = driver.findElement(By.xpath("//input[@type='submit'][@value = 'Add Card']"));
       submitCardButton.click();
       waitUntilElementIsClickable(By.cssSelector("div.card-composer a.icon-close"),10);

       WebElement cancelEditCardButton = driver.findElement(By.cssSelector("div.card-composer a.icon-close"));
       cancelEditCardButton.click();

       listCards = driver.findElements(By.cssSelector("a.list-card.js-member-droppable.ui-droppable"));
       int afterAdding = listCards.size();
       System.out.println("Cards after adding: " + afterAdding);
       Assert.assertEquals(afterAdding, beforeAdding+1,
                "The quantity of cards before adding new card is not the same as the quantity after adding");
    }

    private String boardLocator(String boardTitle) {
        return "//div[@title = '" + boardTitle + "']/../..";
    }
}
