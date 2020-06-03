package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

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

    @FindBy (className = "step-title highlighted")
    WebElement text2Color;

    @FindBy (xpath = "//body[@class='rtl desktop chrome ember-application hover']/div[@class='ember-view']/div[contains(@class,'main-container inner-page-offset')]/div[@class='application-main']/form[@class='ember-view']/div[@class='step-wrapper step-2-wrapper card-form-layout relative']/div[@class='wrapper relative top']/div[@class='row']/div[@class='data-col']/div[@class='step-form-wrapper relative']/div[@class='ember-view step-form']/div[@class='ember-view ui-card']/div[@class='ui-grid']/div[@class='mx2 md1']/label[1]/input[1]")
    WebElement receiverName;

    public static final By EVENT = By.className("chosen-single");

    @FindBy (xpath = "//li[@class='active-result highlighted' and @data-option-array-index='3']")
    WebElement eventBirthday;

    @FindBy (xpath = "//div[@class='chosen-container chosen-container-single ember-view ember-chosenselect form-control chosen-rtl chosen-rtl chosen-container-active chosen-with-drop']//input")
    WebElement inputTextEvent;

    public boolean isOnPage()
    {
        waitUntilElementIsVisible(driver, text2, 10);
        return isElementPresent(text2);
    }

    public OrderPage textColor()
    {
        System.out.println("Color of the text is " + text2Color.getCssValue("color"));
        return this;
    }

    public OrderPage inputReceiverName(String name)
    {
        waitUntilElementIsloaded(driver,receiverName,10);
        type(receiverName, name);
        return this;
    }

    public OrderPage pickEvent()
    {
        List<WebElement> dropDownList = driver.findElements(EVENT);
        WebElement event = dropDownList.get(6);

        action.moveToElement(event).build().perform();
        event.click();
        action.moveToElement(inputTextEvent).build().perform();
        type(inputTextEvent,"לידה");

//        waitUntilElementIsloaded(driver,eventBirthday,20);
//        type(eventBirthday,"לידה");

        return this;
    }



}
