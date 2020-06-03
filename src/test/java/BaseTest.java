import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import config.ReadConfigFile;
import driver.Driver;
import extentReports.MyReporter;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.openqa.selenium.WebDriver;

import java.io.IOException;

public class BaseTest {

    static WebDriver driver;
    static  String browser;
    static String url;

    static ExtentReports reports = MyReporter.getReports();
    static ExtentTest test;

    public static WebDriver getWebDriver() throws IOException {

        browser = ReadConfigFile.getProperties("browser");
        driver = Driver.getDriver(browser);
        return driver;
    }

    @BeforeClass
    public static void setUp() throws IOException {

        url = ReadConfigFile.getProperties("url");
        getWebDriver();
        driver.get(url);
//        driver.get("https://buyme.co.il/");


        // ExtentReports customization
        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir")+"src/reports/extent.html");

        // attach reporter
        reports = new ExtentReports();
        reports.attachReporter(htmlReporter);
        test = reports.createTest("BuyMeTest","Sample description");

//        // add custom system info
//        reports.setSystemInfo("BaseTest","Sanity");
//        reports.setSystemInfo("Tester","Irina");

        // log results
        test.log(Status.INFO, "@BeforeClass");
    }

    @AfterClass
    public static void tearDown()
    {
        Driver.closeDriver();
        test.log(Status.INFO,"@AfterClass");
        reports.flush();
    }
}
