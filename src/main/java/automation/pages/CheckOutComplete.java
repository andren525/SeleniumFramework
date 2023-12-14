package automation.pages;

import automation.drivers.DriverSingleton;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CheckOutComplete {
    private WebDriver driver;
    public CheckOutComplete (){
        driver = DriverSingleton.getDriver();
        PageFactory.initElements(driver, this);

    }
    @FindBy(id = "back-to-products")
    private WebElement backButton;

    public void backToHome(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(backButton));
        backButton.click();
    }
}
