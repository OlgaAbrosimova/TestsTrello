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
    public void initTests() throws InterruptedException {
        driver.findElement(By.linkText("Log In")).click();
        Thread.sleep(5000);

        driver.findElement(By.id("user")).sendKeys(LOGIN);
        Thread.sleep(2000);
        driver.findElement(By.id("login")).click();
        Thread.sleep(10000);

        driver.findElement(By.id("password")).sendKeys(PASSWORD);
        Thread.sleep(2000);
        driver.findElement(By.id("login-submit")).click();
        Thread.sleep(30000);

        System.out.println("'Boards' button text: " + driver
                .findElement(By.xpath("//button[@data-test-id='header-boards-menu-button']/span[2]")).getText());
        Thread.sleep(5000);

        WebElement ourBoard = driver
                .findElement(By.xpath(boardLocator(BOARD_TITLE)));
        ourBoard.click();
        Thread.sleep(5000);
    }

    @Test
    public void createNewList() throws InterruptedException {
        List<WebElement> listLists = driver.findElements(By.cssSelector("div.list.js-list-content"));
        int beforeAdding = listLists.size();
        System.out.println("Lists before adding: " + beforeAdding);

        WebElement addListOption = driver.findElement(By.xpath("//span[@class='placeholder']"));
        addListOption.click();
        WebElement addTitleField = driver.findElement(By.xpath("//input[@placeholder = 'Enter list title...']"));

        addTitleField.click();
        addTitleField.sendKeys("Tasks in process");
        Thread.sleep(2000);

        WebElement addListButton = driver.findElement(By.xpath("//input[@type='submit']"));
        addListButton.click();
        Thread.sleep(5000);

        WebElement cancelEdit = driver.findElement(By.xpath("//a[@class='icon-lg icon-close dark-hover js-cancel-edit']"));
        cancelEdit.click();
        Thread.sleep(2000);

        listLists = driver.findElements(By.cssSelector("div.list.js-list-content"));
        int afterAdding = listLists.size();
        System.out.println("Lists after adding: " + afterAdding);
        Thread.sleep(5000);

        Assert.assertEquals(afterAdding, beforeAdding+1,
                "The quantity of lists before adding new list is not the same as the quantity after adding");
    }

    @Test
    public void createNewCard() throws InterruptedException {
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
                WebElement addTitleField = driver.findElement(By.xpath("//input[@placeholder = 'Enter list title...']"));

                addTitleField.click();
                addTitleField.sendKeys("Tasks in process");
                Thread.sleep(2000);

                WebElement addListButton = driver.findElement(By.xpath("//input[@type='submit']"));
                addListButton.click();
                Thread.sleep(5000);

                WebElement cancelEdit = driver.findElement(By.xpath("//a[@class='icon-lg icon-close dark-hover js-cancel-edit']"));
                cancelEdit.click();
                Thread.sleep(2000);
                System.out.println("Lists after adding: " + driver.
                        findElements(By.xpath("//div[@class = 'list js-list-content']")).size());
            }
        Thread.sleep(10000);

       WebElement addCardButton = driver.findElement(By.cssSelector("span.js-add-a-card"));
       WebElement addAnotherCardButton = driver.findElement(By.cssSelector("span.js-add-another-card"));
       if(addCardButton.isDisplayed()){
           addCardButton.click();
       }
       else addAnotherCardButton.click();
       Thread.sleep(5000);
       WebElement textCurrentCard = driver.findElement(By.cssSelector("textarea.list-card-composer-textarea"));
       textCurrentCard.click();
       textCurrentCard.sendKeys("New card");
       Thread.sleep(2000);
       WebElement submitCardButton = driver.findElement(By.xpath("//input[@type='submit'][@value = 'Add Card']"));
       submitCardButton.click();
       Thread.sleep(2000);
       WebElement cancelEditCardButton = driver.findElement(By.cssSelector("div.card-composer a.icon-close"));
       cancelEditCardButton.click();
       Thread.sleep(3000);

        listCards = driver.findElements(By.cssSelector("a.list-card.js-member-droppable.ui-droppable"));
        int afterAdding = listCards.size();
        System.out.println("Cards after adding: " + afterAdding);
        Thread.sleep(5000);

        Assert.assertEquals(afterAdding, beforeAdding+1,
                "The quantity of cards before adding new card is not the same as the quantity after adding");
    }

    private String boardLocator(String boardTitle) {
        return "//div[@title = '" + boardTitle + "']/../..";
    }
}
