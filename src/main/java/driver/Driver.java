package driver;

import config.ReadConfigFile;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class Driver {

    static WebDriver driver = null;

    public static WebDriver getDriver(String browser) throws IOException {
        String projectPath = System.getProperty("user.dir");

        // Getting values from config file

        int impWait = Integer.parseInt(ReadConfigFile.getProperties("impWait"));

        if (browser.equals("chrome"))
        {
            System.setProperty("webdriver.chrome.driver", projectPath+"/src/webdrivers/chromedriver");
            if (driver == null)
            {
                driver = new ChromeDriver();
                driver.manage().window().maximize();
                driver.manage().deleteAllCookies();
                driver.manage().timeouts().implicitlyWait(impWait, TimeUnit.SECONDS);
            }
        }
        else if (browser.equals("firefox"))
        {
            System.setProperty("webdriver.gecko.driver", projectPath+"src/webdrivers/geckodriver");
            if (driver == null)
            {
                driver = new FirefoxDriver();
                driver.manage().window().maximize();
                driver.manage().timeouts().implicitlyWait(impWait, TimeUnit.SECONDS);
            }
        }
        else
        {
            System.out.println("Error! Can't open browser");
        }

        return driver;
    }

    public static void closeDriver()
    {
        if (driver != null)
        {
            driver.quit();
        }
    }
}
