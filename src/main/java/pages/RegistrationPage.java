package pages;

import config.ReadConfigFile;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegistrationPage extends BasePage {

    public RegistrationPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver,this);
    }

    Actions action = new Actions(driver);

    @FindBy (xpath = "//span[@class='text-btn']")
    WebElement registrationLink;

    @FindBy (xpath = "//div[@class='lightbox-head']")
    WebElement registrationForm;

    @FindBy (xpath = "//div[@class='field']//input[@placeholder='שם פרטי']")
    WebElement inputName;

    @FindBy (xpath = "//div[@class='field']//input[@placeholder='מייל']")
    WebElement inputMail;

    @FindBy (xpath = "//div[@class='field']//input[@placeholder='סיסמה']")
    WebElement inputPassword;

    @FindBy (xpath = "//div[@class='field']//input[@placeholder='אימות סיסמה']")
    WebElement confirmPassword;

    @FindBy (xpath = "//button[@class='ui-btn orange large'][@type='submit']")
    WebElement submitRegistrationButton;

    public RegistrationPage isOnPage()
    {
        action.moveToElement(registrationLink).click().build().perform();
        waitUntilElementIsVisible(driver,registrationForm,10);
        isOnPage(registrationForm);
        return this;
    }

    public RegistrationPage enterUsername(String username)
    {
        action.moveToElement(inputName).build().perform();
        type(inputName,username);
        return this;
    }

    public RegistrationPage enterMail(String mail)
    {
        action.moveToElement(inputMail).build().perform();
        type(inputMail,mail);
        return this;
    }

    public RegistrationPage enterPassword(String pass)
    {
        action.moveToElement(inputPassword).build().perform();
        type(inputPassword,pass);
        return this;
    }

    public RegistrationPage confirmPassword(String pass)
    {
        action.moveToElement(confirmPassword).build().perform();
        type(confirmPassword,pass);
        return this;
    }

    public RegistrationPage submitRegistration()
    {
        waitUntilElementIsClickable(driver,submitRegistrationButton,10);
        submitRegistrationButton.click();
        return this;
    }
}
