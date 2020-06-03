package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class BasePage {

    protected WebDriver driver;

    public BasePage(WebDriver driver)
    {
        this.driver = driver;
    }

    public void type (WebElement element, String text)
    {
        type2(element, text, true);
    }

    public void type2(WebElement element, String text, Boolean clear) {
        if (text != null) {
            element.click();
            if (clear) {
                element.clear();
            }
            element.sendKeys(text);
        }
    }


    public boolean isElementPresent(By locator) {
        try {
            driver.findElement(locator);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public boolean isOnPage(WebElement element) {
        return isElementPresent(element);
    }

    public boolean isElementPresent (WebElement element)
    {
        try
        {
            element.isDisplayed();
            return true;
        }
        catch (NoSuchElementException e)
        {
            return false;
        }
    }

    public boolean isElementTextPresent(WebElement element, String text) {
        try {
            if (element.getText().equals(text)) {
                return true;
            }
            return false;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public String getTitle()
    {
        return driver.getTitle();
    }

    public static void waitUntilElementIsloaded(WebDriver driver, WebElement element, int time) {
        try {
            new WebDriverWait(driver, time).until(ExpectedConditions.visibilityOf(element));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void waitUntilElementIsClickable(WebDriver driver, WebElement element, int time) {
        try {
            new WebDriverWait(driver, time).until(ExpectedConditions.elementToBeClickable(element));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void waitUntilElementIsVisible(WebDriver driver, WebElement element, int time) {
        try {
            new WebDriverWait(driver, time).until(ExpectedConditions.visibilityOf(element));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
