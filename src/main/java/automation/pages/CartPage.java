package automation.pages;

import automation.drivers.DriverSingleton;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CartPage {
    private WebDriver driver;

    public CartPage(){
        driver = DriverSingleton.getDriver();
        PageFactory.initElements(driver,this);
    }

    @FindBy(id = "checkout")
    private WebElement checkOutButton;

    @FindBy(css = "#header_container > div.header_secondary_container > span")
    private WebElement title;

    @FindBy(css = "#shopping_cart_container > a > span")
    private WebElement numberOfProducts;

    @FindBy(id = "remove-sauce-labs-backpack")
    private WebElement removeBackpackButton;

    public boolean isLoaded(){
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.elementToBeClickable(checkOutButton));

        } catch (Exception e) {
            return false;
        }
        return true;
    }
    public void proceedToCheckOut(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(checkOutButton));
        checkOutButton.click();
    }
    public void removeBackpack(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(removeBackpackButton));
        removeBackpackButton.click();

    }
    public String getNumberOfProducts (){

        try {
            return numberOfProducts.getText();
        } catch (Exception e) {
            return null;
        }
    }

}
