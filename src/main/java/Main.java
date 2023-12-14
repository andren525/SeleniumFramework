/*import drivers.DriverSingleton;
import org.openqa.selenium.WebDriver;
import pages.*;
import utils.Constants;
import utils.FrameworkProperties;

import javax.swing.plaf.synth.SynthContext;
import java.text.CompactNumberFormat;

public class Main {
    public static void main(String[] args) {
        FrameworkProperties frameworkProperties = new FrameworkProperties();
        DriverSingleton driverSingleton = DriverSingleton.getInstance(frameworkProperties.getProperties(Constants.CHROME));
        WebDriver driver = DriverSingleton.getDriver();
        driver.get("https://www.saucedemo.com/");


        SignInPage loginPage = new SignInPage();
        ShopPage shop = new ShopPage();
        CartPage cartPage = new CartPage();
        CheckOutInfo checkOutInfoPage = new CheckOutInfo();
        CheckOutOverview checkOutOverviewPage = new CheckOutOverview();
        CheckOutComplete checkOutCompletePage = new CheckOutComplete();

        loginPage.logIn(frameworkProperties.getProperties("username"), frameworkProperties.getProperties("passwd"));

        if (shop.isLoaded())
            System.out.println("login passed");
        else
            System.out.println("login not passed");

        shop.addToCart();
        shop.proceedToCart();

        if (cartPage.isLoaded()){
            System.out.println("cart page loaded");
            cartPage.proceedToCheckOut();
        }
        else
            System.out.println("cart page not loaded");
        if(checkOutInfoPage.isLoaded()){
            System.out.println("form loaded");
            checkOutInfoPage.fillFormToContinue("enrico","andreozzi","12345");
        }else
            System.out.println("form not loaded");
        checkOutOverviewPage.checkoutSubmit();
        checkOutCompletePage.backToHome();

        DriverSingleton.closeObjectInstance();
    }
}
*/