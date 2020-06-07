import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import config.ReadConfigFile;
import driver.Driver;
import org.apache.commons.io.FileUtils;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.fail;

public class BaseTest {

    static WebDriver driver;
    static  String browser;
    static String url;
    static String projectPath = System.getProperty("user.dir");

    /** Extent Report -----------------*/
    static ExtentHtmlReporter htmlReporter;
    static ExtentReports extent;
    static ExtentTest logger;

    public static WebDriver getWebDriver() throws IOException {

        browser = ReadConfigFile.getProperties("browser");
        driver = Driver.getDriver(browser);
        return driver;
    }

    @BeforeClass
    public static void setUp() throws IOException {

        htmlReporter = new ExtentHtmlReporter(projectPath + "/utils/TestReport.html");

        // Create an Object of Extent Reports
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
        extent.setSystemInfo("https://buyme.co.il/","Buy Me Site");
        extent.setSystemInfo("Environment","Testing");
        extent.setSystemInfo("Tester", "Irina");

        // Set configuration for Report
        htmlReporter.config().setDocumentTitle("Report of the Buy Me Site testing result.");
        htmlReporter.config().setReportName("Buy Me site - Sanity Testing");
        htmlReporter.config().setTheme(Theme.DARK);

        logger = extent.createTest("BaseTest" , "Parent Class for all test Classes in this project.");
        logger.log(Status.INFO, "Execute @BeforeClass");

        boolean driverEstablish = false;
        try {
            url = ReadConfigFile.getProperties("url");
            getWebDriver();
            driver.get(url);
            driverEstablish = true;

            } catch (Exception e) {
            e.printStackTrace();
            fail("Cannot connect WebDriver");
            logger.log(Status.FATAL, "ERROR! Driver connection failed! " + e.getMessage());
            driverEstablish = false;

        } finally {
            if (driverEstablish)
            {
                logger.log(Status.PASS, "Driver established successfully!");
            }
        }
    }

    @AfterClass
    public static void tearDown()
    {
        logger.log(Status.INFO, "Execute @AfterClass");
        Driver.closeDriver();

        if (extent != null)
        {
            extent.flush();
        }

    }

    // This method is to capture the Screenshot and return the path of the screenshot

    public static String takeScreenShot(WebDriver driver, String screenshotName) throws IOException {
        String dateName = new SimpleDateFormat("yyyy.MM.dd_hh.mm.ss").format(new Date());
        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);

        // after execution, you could see a folder "FailedTestsScreenshot"
        String destination = projectPath + "/utils/Screenshots/" + screenshotName + dateName + ".png";
        File finalDestination = new File(destination);
        FileUtils.copyFile(source, finalDestination);

        return destination;
    }
}
