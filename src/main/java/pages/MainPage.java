package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class MainPage extends BasePage {

    Actions action = new Actions(driver);

    /** Web Elements ---------------------- */

    public static final By DROP_MENU = By.className("chosen-single");

    @FindBy (xpath = "//header[@class='m-page-header']//li[3]")
    WebElement loginRegistrationButton;
    @FindBy (xpath = "//header[@class='m-page-header']//span[2]")
    WebElement registrationButton;
    @FindBy (xpath = "//li[contains(@class,'ember-view dropdown')]")
    WebElement userAccountButton;
    @FindBy (xpath = "//a[@class='dropdown-item']//span")
    WebElement exitFromAccount;
    @FindBy (xpath = "//span//img[@class='arrow']")
    WebElement userAccountPageElement;

    @FindBy (xpath = "/html[1]/body[1]/div[1]/div[1]/header[1]/div[3]/div[1]/form[1]/div[1]/div[1]/ul[1]/li[3]")
    WebElement price;
    @FindBy (xpath = "/html[1]/body[1]/div[1]/div[1]/header[1]/div[3]/div[1]/form[1]/div[2]/div[1]/ul[1]/li[3]")
    WebElement area;
    @FindBy (xpath = "/html[1]/body[1]/div[1]/div[1]/header[1]/div[3]/div[1]/form[1]/div[3]/div[1]/ul[1]/li[2]")
    WebElement category;
    @FindBy (xpath = "//a[@class='ui-btn search ember-view']")
    WebElement findGiftButton;

    /** Page Methods ------------------*/

    public MainPage(WebDriver driver)
    {
        super(driver);
        PageFactory.initElements(driver,this);
    }

    public boolean isOnPage()
    {
        waitUntilElementIsVisible(driver,loginRegistrationButton,10);
        return isElementPresent(loginRegistrationButton);
    }

    public MainPage clickOnRegistrationButton()
    {
        action.moveToElement(registrationButton).click().build().perform();
        return this;
    }

    public boolean isOnUserAccountPage()
    {
        waitUntilElementIsVisible(driver,userAccountPageElement,10);
        return isElementPresent(userAccountPageElement);
    }

    public MainPage userLogout ()
    {
        waitUntilElementIsloaded(driver,userAccountButton,20);
        action.moveToElement(userAccountButton).build().perform();
//        userAccountButton.click();
        action.moveToElement(exitFromAccount).build().perform();
        exitFromAccount.click();
        return this;
    }

    public MainPage dropDownMenu()
    {
        List<WebElement> dropDownList = driver.findElements(DROP_MENU);
        WebElement priceMenu = dropDownList.get(0);
        WebElement areaMenu = dropDownList.get(1);
        WebElement categoryMenu = dropDownList.get(2);

        WebDriverWait wait = new WebDriverWait(driver,3);
        priceMenu.click();
        waitUntilElementIsloaded(driver,price,20);
        price.click();

        areaMenu.click();
        waitUntilElementIsloaded(driver,area,20);
        area.click();

        categoryMenu.click();
        waitUntilElementIsloaded(driver, category, 20);
        category.click();

        return this;
    }


    public MainPage clickOn_FindGift_Button()
    {
        waitUntilElementIsClickable(driver,findGiftButton, 10);
        action.moveToElement(findGiftButton).click().build().perform();
        return this;
    }

}
