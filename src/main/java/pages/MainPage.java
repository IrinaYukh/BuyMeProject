package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class MainPage extends BasePage {

    Actions action = new Actions(driver);
    @FindBy (xpath = "//header[@class='m-page-header']//li[3]")
    WebElement loginRegistrationButton;
    @FindBy (xpath = "//header[@class='m-page-header']//span[2]")
    WebElement registrationButton;
    @FindBy (xpath = "//li[@class='ember-view dropdown']//span[contains(text(),'החשבון שלי')]")
    WebElement userAccountButton;
    @FindBy (xpath = "//a[@class='dropdown-item']//span")
    WebElement exitFromAccount;
    @FindBy (xpath = "//span//img[@class='arrow']")
    WebElement userAccountPageElement;
    @FindBy (xpath = "//div[@class='ember-view header-search-bar home']")
    WebElement dropdownMenuArea;

// ----------------------------------------
    @FindBy (xpath = "//div[@class='chosen-drop']//li[contains(text(),'99')]")
    WebElement selectPrice;
    @FindBy (xpath = "//form[@class='form ember-view']//span[contains(text(),'סכום')]")
    WebElement priceMenu;
    @FindBy (xpath = "//a[@class='chosen-single']/span[@xpath='1']")
    WebElement areaMenu;
    @FindBy (xpath = "//div[@class='chosen-drop']/ul/li[contains(text(),'מרכז')]")
    WebElement selectArea;
    @FindBy (xpath = "/html[1]/body[1]/div[2]/div[1]/header[1]/div[3]/div[1]/form[1]/div[1]/a[1]/span[1]")
    WebElement price;
// -------------------------------------------

    @FindBy (xpath = "//a[@class='ui-btn search ember-view']")
    WebElement findGiftButton;



    public MainPage(WebDriver driver)
    {
        super(driver);
        PageFactory.initElements(driver,this);
    }

    public MainPage isOnPage()
    {
        waitUntilElementIsVisible(driver,loginRegistrationButton,10);
        isElementPresent(loginRegistrationButton);
        return this;
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
        action.moveToElement(userAccountButton).build().perform();
        userAccountButton.click();
        action.moveToElement(exitFromAccount).build().perform();
        exitFromAccount.click();
        return this;
    }

    public MainPage dropDownMenu()
    {
        
        return this;
    }

    public MainPage pickPrice()
    {
//        action.moveToElement(priceMenu).click().build().perform();
//
//    //    WebElement pick = driver.findElement(By.xpath("//div[@class='chosen-drop']//li[contains(text(),'" + price + "')]"));
//        action.moveToElement(selectPrice).build().perform();
//        System.out.println(selectPrice.getText());
//        //selectPrice.click();

       waitUntilElementIsVisible(driver,price,20);
//       price.click();

        List<WebElement> allPrices = driver.findElements(By.xpath("/html[1]/body[1]/div[2]/div[1]/header[1]/div[3]/div[1]/form[1]/div[1]/div[1]/ul[1]/li"));

        System.out.println( allPrices.get(3).getText());
        allPrices.get(3).click();

        return this;
    }

    public MainPage pickArea()
    {
        waitUntilElementIsVisible(driver,dropdownMenuArea,20);
        action.moveToElement(dropdownMenuArea).build().perform();

        waitUntilElementIsVisible(driver, areaMenu, 20);
        areaMenu.click();
        selectArea.click();
        return this;
    }

    public MainPage clickOn_FindGift_Button()
    {
        waitUntilElementIsClickable(driver,findGiftButton, 10);
        action.moveToElement(findGiftButton).click().build().perform();
        return this;
    }

}
