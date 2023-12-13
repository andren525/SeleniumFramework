package pages;

import drivers.DriverSingleton;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ShopPage {
    private WebDriver driver;

    public ShopPage(){
        driver = DriverSingleton.getDriver();
        PageFactory.initElements(driver,this);
    }


    @FindBy(id = "add-to-cart-sauce-labs-backpack")
    private WebElement addToCartButton;

    @FindBy(css = "#shopping_cart_container > a > span")
    private WebElement numberOfProducts;

    @FindBy(css= "#shopping_cart_container > a")
    private WebElement cartButton;

    // WebElement for the element that contains all the shopping items in the shop page
    @FindBy(id="inventory_container")
    private WebElement productContainer;

    //this method check if the shop page is loaded
    public boolean isLoaded(){
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.visibilityOf(productContainer));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    public void addToCart(){
        addToCartButton.click();

        if (numberOfProducts.getText().equals("1"))
            System.out.println("The cart has been updated");
        else
            System.out.println("The cart has not been updated");
    }

    public void proceedToCart(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(cartButton));
        cartButton.click();

    }


}
