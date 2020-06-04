package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PaymentPage extends BasePage
{
    public PaymentPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy (xpath = "//button[contains(text(),'PayPal')]")
    WebElement iconPayPal;

    public boolean isOnPage()
    {
        waitUntilElementIsVisible(driver, iconPayPal, 20);
        return isElementPresent(iconPayPal);
    }
}
