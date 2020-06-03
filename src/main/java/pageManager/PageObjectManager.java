package pageManager;

import org.openqa.selenium.WebDriver;
import pages.BasePage;
import pages.LoginPage;
import pages.MainPage;
import pages.RegistrationPage;

public class PageObjectManager extends BasePage {

    private WebDriver driver;
    private MainPage mainPage;
    private RegistrationPage registration;
    private LoginPage loginPage;

    public PageObjectManager(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    public MainPage getMainPage()
    {
        return (mainPage == null) ? mainPage = new MainPage(driver) : mainPage ;
    }
    public RegistrationPage getRegistrationPage()
    {
        return (registration == null) ? registration = new RegistrationPage(driver) : registration;
    }
    public LoginPage getLoginPage()
    {
        return  (loginPage == null) ? loginPage = new LoginPage(driver) : loginPage;
    }


}
