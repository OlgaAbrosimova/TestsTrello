package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ProfileVisibilityTests extends TestBase {

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

        WebElement menuButton = driver.findElement(By.xpath("//button[@aria-label='Open Member Menu']"));
        menuButton.click();
        waitUntilElementIsClickable(By.xpath("//a[@data-test-id='header-member-menu-profile']"),10);

        WebElement profileAndVisibilityListItem = driver.findElement(By.xpath("//a[@data-test-id='header-member-menu-profile']"));
        profileAndVisibilityListItem.click();
        waitUntilElementIsVisible(By.xpath(nameLocator(NAME_TITLE)),10);
        waitUntilElementIsClickable(By.xpath("//a[@data-tab='profile']"),10);
    }

    @Test
    public void labelTextVerificationTest() {
        WebElement menuLabel = driver.findElement(By.xpath("//button[@aria-label='Open Member Menu']"));
        System.out.println("Menu button text: " + menuLabel.getText());
        waitUntilElementIsVisible(By.xpath(nameLocator(NAME_TITLE)),10);

        WebElement nameLabel = driver.findElement(By.xpath(nameLocator(NAME_TITLE)));
        System.out.println("Name icon text: " + nameLabel.getText());
        Assert.assertEquals(menuLabel.getText(),nameLabel.getText(),"'Open Menu Button' and 'Name Icon' have a different names");
    }

    @Test
    public void userNameVerificationTest() {
        WebElement userNameProfile = driver.findElement(By.xpath(userNameTitleLocator(USERNAME_TITLE)));
        System.out.println("User name Profile: "+userNameProfile.getText());
        waitUntilElementIsVisible(By.xpath("//input[@autocomplete='username']"),10);

        WebElement userName = driver.findElement(By.xpath("//input[@autocomplete='username']"));
        System.out.println("User name text: @"+ userName.getAttribute("value"));
        Assert.assertEquals(userNameProfile.getText(),"@"+userName.getAttribute("value"),"'User name Profile' and 'User name' have a different names");
    }

    private String nameLocator(String nameTitle) {
        return "//div[@title = '" + nameTitle + "']";
    }

    private String userNameTitleLocator(String userNameProfile) {
        return "//span[contains(text(), '" + USERNAME_TITLE + "')]/../span[2]";
    }


}
