package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends BasePage

{
    public LoginPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy (xpath = "//div[@class='lightbox-head']")
    WebElement loginForm;

    @FindBy (xpath = "//div[@class='field']//input[@placeholder='מייל']")
    WebElement inputMail;

    @FindBy (xpath = "//div[@class='field last']//input[@placeholder='סיסמה']")
    WebElement inputPassword;

    @FindBy (xpath = "//button[@class='ui-btn orange large']")
    WebElement submitLoginButton;

    @FindBy (xpath = "//div[@class='field']//li[@class='parsley-required'][contains(text(),'!')]")
    WebElement loginErrorMessage;



    public LoginPage isOnLoginForm()
    {
        waitUntilElementIsloaded(driver, loginForm, 10);
        isElementPresent(loginForm);
        return this;
    }

    public LoginPage enterMail(String mail)
    {
        waitUntilElementIsloaded(driver, inputMail, 10);
        type(inputMail, mail);
        return this;
    }

    public LoginPage enterPassword(String password)
    {
        waitUntilElementIsloaded(driver, inputPassword, 10);
        type(inputPassword, password);
        return this;
    }

    public LoginPage submitLogin()
    {
        waitUntilElementIsClickable(driver,submitLoginButton,10);
        submitLoginButton.click();
        return this;
    }

    public boolean isLoginErrorMessagePresent()
    {
        waitUntilElementIsVisible(driver,loginErrorMessage,10);
        return isElementPresent(loginErrorMessage);
    }
}
