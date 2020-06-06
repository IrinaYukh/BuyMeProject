import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
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
    private PaymentPage paymentPage;

    /** Extent Report -----------------*/
    static ExtentTest logger;


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
        paymentPage = pageManager.getPaymentPage();


    }

    @Test
    public void a_userRegistration()
    {
        /* Positive Test : Successful new User registration with valid values.
            1. Verifying that is on the Main Page
            2. Click on Registration Button
            3. Verifying that is on Registration Form
            4. Enter valid values to the form
            5. Submit User registration
            6. Verifying that move on Main Page in LoginUser status
         */

        logger = extent.createTest("Test 1. Positive : User registration with valid values.");

        boolean execute = false;

        try {
            mainPage.isOnPage();
            mainPage.clickOnRegistrationButton();
            logger.log(Status.INFO, "Moving to new User Registration form");
            registration.isOnPage()
                    .enterUsername(validUser)
                    .enterMail(validMail)
                    .enterPassword(validPassword)
                    .confirmPassword(validPassword)
                    .submitRegistration();
            logger.log(Status.INFO, "New User Registration Form filled with valid values");
            Assert.assertTrue("You're submitted like User ", mainPage.isOnUserAccountPage());
            logger.log(Status.INFO, "Successful new User registration was passed");
            mainPage.userLogout()
                    .isOnPage();
            logger.log(Status.INFO, "Log out from User Account");
            execute = true;
        }
            catch (Exception e)
        {
            e.printStackTrace();
            logger.log(Status.FAIL, "Can't successful create registration on Buy Me" + e.getMessage());
            execute = false;

        } finally {
            if (execute)
            {
                logger.log(Status.PASS, "Successful new User registration with valid values.");
            }
        }

    }

    @Test
    public void b_invalidUserLogin()
    {
        /*
            Negative Test : Successful verification impossibilities of Login with wrong values
            1. Verifying that Main Page status is not private.
            2. Click on Login button
            3. Verifying that is on Login Form
            4. Skip input values to the fields and press submit Login button
            5. Verifying Error message getting
         */
        logger = extent.createTest("Test 2. Negative : User Login test with invalid values.");

        boolean execute = false;

        try {
                mainPage.isOnPage();
                mainPage.clickOnRegistrationButton();
                loginPage.isOnLoginForm();
                logger.log(Status.INFO, "Moving to User Login form");
                if (loginPage.isOnRegistrationForm()) {
                    loginPage.clickLoginButton();
                }
                logger.log(Status.INFO,"Verifying that is on Login Form");
                loginPage.submitLogin();
                logger.log(Status.INFO, "Skip input values to the fields and press submit Login button");
                Assert.assertTrue(loginPage.isLoginErrorMessagePresent());
                logger.log(Status.INFO, "Verifying Error message getting");

                // post-condition Close Login Form window
                loginPage.closeLoginPage();
                Assert.assertTrue("Login Form closed successfully!", mainPage.isOnPage());
                logger.log(Status.INFO, "Closing Login Form window. Verifying returning to the Main Page.");
                execute = true;

            }
            catch (Exception e)
            {
                e.printStackTrace();
                logger.log(Status.FAIL, "ERROR! User Login test with invalid values failed!" + e.getMessage());
                execute = false;
            }
            finally
            {
                if (execute)
                {
                    logger.log(Status.PASS, "Successful User Login test with invalid values.");
                }
            }
    }


    @Test
    public void c_orderCreation() throws IOException {
        /*
            Positive test : Verify successful order creation
            1. Verify that logged as registered User
            2. Choose price, area and category of Order from drop-down menus
            3. Push submit button
            4. Choose the gift item.
            5. Customize Receiver name, pick the event, write the blessing, upload picture.
            6. Choose method of the gift sending (mail / SMS). Enter email address / phone number for SMS.
            7. Submit your choice.
         */

        logger = extent.createTest("Test 3. Positive : Order creation.");

        boolean created = false;
        logger.pass("Order", MediaEntityBuilder.createScreenCaptureFromPath(takeScreenShot(driver,"order")).build());


        try {
                if (!mainPage.isOnUserAccountPage()) {
                    mainPage.clickOnRegistrationButton();
                    loginPage.isOnLoginForm()
                            .enterMail(mail)
                            .enterPassword(password)
                            .submitLogin();
                    logger.log(Status.INFO, "Login on the User account Main page");
                }
                if (mainPage.isOnUserAccountPage()) {
                    System.out.println(" You present on Login Main Page");

                    mainPage.dropDownMenu()
                            .clickOn_FindGift_Button();
                    Assert.assertTrue("Successful redirect on the page for Gift choose", giftPage.isOnPage());
                    logger.log(Status.INFO, "Choosing options from drop-down menu and pushing submit button");
                    giftPage.selectGiftAdv();
                    logger.log(Status.INFO, " Select item for gift order.");
                    Assert.assertTrue("Successful redirect to the Order Page", orderPage.isOnPage());
                    orderPage.inputReceiverName("John")
                            .pickEvent()
                            .uploadPhoto("/src/configFiles/birthday.jpeg")
                            .sendGiftByMail("john@mail.com");
                    logger.log(Status.INFO, "Customize data for gift Receiver.");
                    logger.pass("Order", MediaEntityBuilder.createScreenCaptureFromPath(takeScreenShot(driver,"order")).build());

                    orderPage.sendOrderToReceiver();
                    logger.log(Status.INFO,"Submit all data for Order.");
                    Assert.assertTrue("Successful order creation", paymentPage.isOnPage());
                    created = true;
                }
            } catch (Exception e)
                {
                   e.printStackTrace();
                   logger.log(Status.FAIL, "ERROR! Order didn't created" + e.getMessage());
                   created = false;
                }
            finally {
                    if (created)
                    {
                        logger.log(Status.PASS, " Order created successfully");
                    }
        }

    }



}
