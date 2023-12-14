package automation.pages;

import automation.drivers.DriverSingleton;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CheckOutInfo {
    private WebDriver driver;
    public CheckOutInfo(){
        driver = DriverSingleton.getDriver();
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "#checkout_info_container > div > form > div.checkout_info")
    private WebElement checkoutForm;

    @FindBy(id = "first-name")
    private WebElement firstnameInput;

    @FindBy(id = "last-name")
    private WebElement lastnameInput;

    @FindBy(id = "postal-code")
    private WebElement postalcodeInput;

    @FindBy(id = "continue")
    private WebElement continueButton;

    public boolean isLoaded(){
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.visibilityOf(checkoutForm));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public void fillFormToContinue(String firstName, String lastName, String postalCode){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(continueButton));
        firstnameInput.sendKeys(firstName);
        lastnameInput.sendKeys(lastName);
        postalcodeInput.sendKeys(postalCode);
        continueButton.click();

    }

}
