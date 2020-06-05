package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.util.List;

public class OrderPage extends BasePage
{
    public OrderPage(WebDriver driver)
    {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    Actions action = new Actions(driver);

    /** Web Elements ---------------------- */

    @FindBy(xpath = "//body[@class='rtl desktop chrome ember-application hover']/div[@class='ember-view']/div[contains(@class,'main-container inner-page-offset')]/div[@class='application-main']/div[@class='wrapper']/ul[@class='ember-view purchase-steps']/li[2]/div[1]/div[2]")
    WebElement text2;

    @FindBy (className = "step-title highlighted")
    WebElement text2Color;

    @FindBy (xpath = "//body[@class='rtl desktop chrome ember-application hover']/div[@class='ember-view']/div[contains(@class,'main-container inner-page-offset')]/div[@class='application-main']/form[@class='ember-view']/div[@class='step-wrapper step-2-wrapper card-form-layout relative']/div[@class='wrapper relative top']/div[@class='row']/div[@class='data-col']/div[@class='step-form-wrapper relative']/div[@class='ember-view step-form']/div[@class='ember-view ui-card']/div[@class='ui-grid']/div[@class='mx2 md1']/label[1]/input[1]")
    WebElement receiverName;

    @FindBy (xpath = "//input[@name='fileUpload']")
    WebElement photoUpload;

    @FindBy (xpath = "//div[@class='ember-view step-form']//div[2]//div[1]//button[1]")
    WebElement emailEnvelope;

    @FindBy (xpath = "//h3[@class='section-title'][contains(text(),'?')]")
    WebElement mailForm;

    @FindBy (xpath = "//input[@placeholder='כתובת המייל של מקבל/ת המתנה']")
    WebElement mailField;

    @FindBy(xpath = "//button[@class='btn btn-theme btn-save']")
    WebElement submitMailButton;

    @FindBy (xpath = "//button[contains(@class,'ui-btn orange large')]")
    WebElement sendOrderButton;

    public static final By EVENT = By.className("chosen-single");

    /** Page Methods ---------------------- */

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
        action.sendKeys(Keys.ARROW_DOWN).click().build().perform();
        return this;
    }

    public OrderPage uploadPhoto(String photoUrl)
    {
        String projectPath = System.getProperty("user.dir");
        String imagePath = projectPath + photoUrl;
        action.moveToElement(photoUpload).build().perform();
        photoUpload.sendKeys(imagePath);

        return this;
    }

    public OrderPage sendGiftByMail(String emailAddress)
    {
        waitUntilElementIsClickable(driver, emailEnvelope, 10);
        emailEnvelope.click();

        waitUntilElementIsClickable(driver,mailForm,10);

        action.moveToElement(mailField).build().perform();
        type(mailField, emailAddress);

        waitUntilElementIsClickable(driver,submitMailButton,10);
        submitMailButton.click();

      return this;
    }

    public OrderPage sendOrderToReceiver()
    {
        waitUntilElementIsVisible(driver, sendOrderButton, 10);
        action.moveToElement(sendOrderButton).click().build().perform();

        return this;
    }





}
