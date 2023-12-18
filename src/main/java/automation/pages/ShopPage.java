package automation.pages;

import automation.drivers.DriverSingleton;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Comparator;
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

    @FindBy(css = "#header_container > div.header_secondary_container > div > span > select")
    private WebElement orderSelector;

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

    public List<String> getTitles(){
        List<String> titles = new ArrayList<String>();
        List<WebElement> items = productContainer.findElements(By.className("inventory_item_name"));
        for (WebElement element : items ){
            titles.add(element.getText());
        }
        return titles;
    }

    public List<Double> getPrices(){
        List<String> prices = new ArrayList<String>();
        List<WebElement> items = productContainer.findElements(By.className("inventory_item_price"));
        List<Double> numbers = new ArrayList<Double>();
        for (WebElement element : items ){
            prices.add(element.getText());
        }
        for (String price : prices){
            numbers.add(Double.parseDouble(price.replace("$"," ").trim()));
        }
        return numbers;
    }

    public List<String> getOrderByTitles(String order){
        List<String> titles = new ArrayList<String>();
        titles = getTitles();
        return switch (order) {
            case "az" -> {
                titles.sort(Comparator.naturalOrder());
                yield titles;
            }
            case "za" -> {
                titles.sort(Comparator.reverseOrder());
                yield titles;
            }
            default -> null;
        };
    }
    public List<Double> getOrderByPrices(String order){
        List<Double> price = getPrices();
        return switch (order) {
            case "lohi" -> {
                price.sort(Comparator.naturalOrder());
                yield price;
            }
            case "hilo" -> {
                price.sort(Comparator.reverseOrder());
                yield price;
            }
            default -> null;
        };
    }


    public void selectorder(String order){
        Select select = new Select(orderSelector);
        select.selectByValue(order);
    }

    public String getNumberOfProducts (){
        try {
            return numberOfProducts.getText();
        } catch (Exception e) {
            return null;
        }
    }


}
