package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class GiftPage extends BasePage
{

    public GiftPage(WebDriver driver)
    {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    Actions action = new Actions(driver);

    @FindBy (xpath = "//header[@class='m-page-header second-header']")
    WebElement giftPageHeader;

    @FindBy (xpath = "//head//link[2]")
    WebElement linkURL;

    @FindBy (xpath = "//body[@class='rtl desktop chrome ember-application hover']/div[@class='ember-view']/div[contains(@class,'main-container inner-page-offset')]/div[@class='application-main']/div[@class='wrapper']/div[@class='card-items']/div[1]/a[1]/div[1]")
    WebElement giftScreen;


    public boolean isOnPage()
    {
        waitUntilElementIsloaded(driver,giftPageHeader,20);
        return isElementPresent(giftPageHeader);
    }


    public GiftPage returnURL()
    {
        System.out.println("Link of current page is " + linkURL.getText());
        return this;
    }

    public GiftPage selectGiftAdv()
    {
        action.moveToElement(giftScreen).build().perform();
        giftScreen.click();
        return this;
    }









}
