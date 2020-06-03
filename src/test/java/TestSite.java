import config.ReadConfigFile;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import pageManager.PageObjectManager;
import pages.*;
import pages.testDataCreator.TestDataCreator;

import java.io.IOException;

public class TestSite extends BaseTest {

    // Pages initialization
    private PageObjectManager pageManager;
    private MainPage mainPage;
    private RegistrationPage registration;
    private LoginPage loginPage;
    private GiftPage giftPage;
    private OrderPage orderPage;

    // Creating variables with values from config file for testing
    String mail = ReadConfigFile.getProperties("mail");
    String password = ReadConfigFile.getProperties("password");

    String validUser = TestDataCreator.getUser();
    String validMail = TestDataCreator.getMail();
    String validPassword = TestDataCreator.getPassword();


    public TestSite() throws IOException {
    }

    @Before
    public void initPages() throws IOException {

        // Initialization of the helper page classes

        pageManager = new PageObjectManager(getWebDriver());
        mainPage = pageManager.getMainPage();
        registration = pageManager.getRegistrationPage();
        loginPage = pageManager.getLoginPage();
        giftPage = pageManager.getGiftPage();
        orderPage = pageManager.getOrderPage();
    }

    @Test
    public void userRegistration()
    {
        /* Positive Test : Successful new User registration with valid values.
            1. Verifying that is on the Main Page
            2. Click on Registration Button
            3. Verifying that is on Registration Form
            4. Enter valid values to the form
            5. Submit User registration
            6. Verifying that move on Main Page in LoginUser status
         */

        mainPage.isOnPage()
                .clickOnRegistrationButton();
        registration.isOnPage()
                    .enterUsername(validUser)
                    .enterMail(validMail)
                    .enterPassword(validPassword)
                    .confirmPassword(validPassword)
                    .submitRegistration();
        Assert.assertTrue("You're submitted like User ", mainPage.isOnUserAccountPage());
    }

    @Test
    public void invalidUserLogin()
    {
        /*
            Negative Test : Successful verification impossibilities of Login with wrong values
            1. Verifying that Main Page status is not private. If private, need to log out from it.
            2. Click on Login button
            3. Verifying that is on Login Form
            4. Skip input values to the fields and press submit Login button
            5. Verifying Error message getting
         */

        if (mainPage.isOnUserAccountPage())
        {
            mainPage.userLogout();
        }
        mainPage.isOnPage()
                .clickOnRegistrationButton();
        loginPage.isOnLoginForm()
                .submitLogin();
        Assert.assertTrue(loginPage.isLoginErrorMessagePresent());
    }


    @Test
    public void orderCreation()
    {
        /*
            Positive test : Verify successful order creation
            1. Verify that logged as registered User
            2. Choose price, area and category of Order from drop-down menus
            3. Push submit button


         */

        if (!mainPage.isOnUserAccountPage())
        {
            mainPage.clickOnRegistrationButton();
            loginPage.isOnLoginForm()
                    .enterMail(mail)
                    .enterPassword(password)
                    .submitLogin();
        }
        if (mainPage.isOnUserAccountPage())
        {
            System.out.println(" You present on Login Main Page");

        mainPage.dropDownMenu()
                .clickOn_FindGift_Button();
        giftPage.returnURL();
        Assert.assertTrue("Successful redirect on the page for Gift choose",giftPage.isOnPage());
        giftPage.selectGiftAdv();
        Assert.assertTrue("Successful redirect to the Order Page", orderPage.isOnPage());


        }

    }





}
