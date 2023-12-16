package automation.pages;

import automation.drivers.DriverSingleton;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.validation.beanvalidation.SpringValidatorAdapter;

import java.time.Duration;
import java.util.List;

public class ShopPage {
    private WebDriver driver;

    public ShopPage(){
        driver = DriverSingleton.getDriver();
        PageFactory.initElements(driver,this);
    }


    @FindBy(id="remove-sauce-labs-backpack")
    private WebElement removefromCartButton;


    @FindBy(css = "#shopping_cart_container > a > span")
    private WebElement numberOfProducts;

    @FindBy(css= "#shopping_cart_container > a")
    private WebElement cartButton;

    // WebElement for the element that contains all the shopping items in the shop page
    @FindBy(css = "#inventory_container > div")
    private WebElement productContainer;



    //this method check if the shop page is loaded
    public boolean isLoaded(){
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
            wait.until(ExpectedConditions.visibilityOf(productContainer));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    public void addToCart(String title){
        WebElement element = getelementbytitle(title);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        WebElement item = element.findElement(By.className("btn_primary"));
        wait.until(ExpectedConditions.elementToBeClickable(item));
        item.click();

        /*if (numberOfProducts.getText().equals("1"))
            System.out.println("The cart has been updated");
        else
            System.out.println("The cart has not been updated");*/
    }
    public void removefromcart(String title){
        WebElement element = getelementbytitle(title);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        WebElement item = element.findElement(By.className("btn_secondary"));
        wait.until(ExpectedConditions.elementToBeClickable(item));
        item.click();
    }

    public void proceedToCart(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(cartButton));
        cartButton.click();

    }

    public WebElement getelementbytitle(String title){
        List<WebElement> items = productContainer.findElements(By.className("inventory_item_description"));
        WebElement item;
        item = null;
        for(WebElement element : items){
            if (title.equals(element.findElement(By.tagName("a")).getText()))
                item = element;
        }
        return item;
    }

    public void goToItemPage(String title){
        WebElement backpackitem = getelementbytitle(title).findElement(By.tagName("a"));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(backpackitem));
        backpackitem.click();
    }

    public String getNumberOfProducts (){
        try {
            return numberOfProducts.getText();
        } catch (Exception e) {
            return null;
        }
    }


}
