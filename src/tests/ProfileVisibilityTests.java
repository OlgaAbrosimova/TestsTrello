package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ProfileVisibilityTests extends TestBase {

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
        Thread.sleep(25000);

        System.out.println("'Boards' button text: " + driver
                .findElement(By.xpath("//button[@data-test-id='header-boards-menu-button']/span[2]")).getText());
        Thread.sleep(3000);

        WebElement menuButton = driver.findElement(By.xpath("//button[@aria-label='Open Member Menu']"));
        menuButton.click();
        Thread.sleep(2000);

        WebElement profileAndVisibilityListItem = driver.findElement(By.xpath("//a[@data-test-id='header-member-menu-profile']"));
        profileAndVisibilityListItem.click();
        Thread.sleep(5000);
    }
    @Test
    public void labelTextVerificationTest() throws InterruptedException {
        WebElement menuLabel = driver.findElement(By.xpath("//button[@aria-label='Open Member Menu']"));
        System.out.println("Menu button text: " + menuLabel.getText());
        Thread.sleep(5000);

        WebElement nameLabel = driver.findElement(By.xpath(nameLocator(NAME_TITLE)));
        System.out.println("Name icon text: " + nameLabel.getText());
        Thread.sleep(5000);

        Assert.assertEquals(menuLabel.getText(),nameLabel.getText(),"'Open Menu Button' and 'Name Icon' have a different names");
    }

    @Test
    public void userNameVerificationTest() throws InterruptedException {
        WebElement userNameProfile = driver.findElement(By.xpath(userNameTitleLocator(USERNAME_TITLE)));
        System.out.println("User name Profile: "+userNameProfile.getText());
        Thread.sleep(3000);

        WebElement userName = driver.findElement(By.name("username"));
        System.out.println("User name text: @"+ userName.getAttribute("value"));
        Thread.sleep(3000);

        Assert.assertEquals(userNameProfile.getText(),"@"+userName.getAttribute("value"),"'User name Profile' and 'User name' have a different names");
    }

    private String nameLocator(String nameTitle) {
        return "//div[@title = '" + nameTitle + "']";
    }

    private String userNameTitleLocator(String userNameProfile) {
        return "//span[contains(text(), '" + USERNAME_TITLE + "')]/../span[2]";
    }


}
