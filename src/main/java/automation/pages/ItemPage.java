package automation.pages;

import automation.drivers.DriverSingleton;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ItemPage {
    private WebDriver driver;

    public ItemPage(){
        driver = DriverSingleton.getDriver();
        PageFactory.initElements(driver,this);
    }

    @FindBy(css = "#inventory_item_container > div > div > div.inventory_details_desc_container > div.inventory_details_name.large_size")
    private WebElement title;

    @FindBy(id = "add-to-cart-sauce-labs-backpack")
    private WebElement addtocartbutton;

    @FindBy(id="remove-sauce-labs-backpack")
    private WebElement removeFromCartButton;


    @FindBy(css = "#shopping_cart_container > a > span")
    private WebElement numberOfProducts;

    public void addtocart(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        wait.until(ExpectedConditions.elementToBeClickable(addtocartbutton));
        addtocartbutton.click();
    }
    public void removeFromCart(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        wait.until(ExpectedConditions.elementToBeClickable(removeFromCartButton));
        removeFromCartButton.click();
    }


    public String getNumberOfProducts (){
        try {
            return numberOfProducts.getText();
        } catch (Exception e) {
            return null;
        }
    }


}
