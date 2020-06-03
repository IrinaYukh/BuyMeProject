package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrderPage extends BasePage
{
    public OrderPage(WebDriver driver)
    {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    Actions action = new Actions(driver);

    @FindBy(xpath = "//body[@class='rtl desktop chrome ember-application hover']/div[@class='ember-view']/div[contains(@class,'main-container inner-page-offset')]/div[@class='application-main']/div[@class='wrapper']/ul[@class='ember-view purchase-steps']/li[2]/div[1]/div[2]")
    WebElement text2;

    public boolean isOnPage()
    {
        waitUntilElementIsVisible(driver, text2, 10);
        return isElementPresent(text2);
    }



}
