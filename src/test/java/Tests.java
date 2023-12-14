import drivers.DriverSingleton;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import pages.*;
import utils.Constants;
import utils.FrameworkProperties;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Tests {
    static FrameworkProperties frameworkProperties;
    static WebDriver driver;
    static SignInPage signInPage;
    static ShopPage shopPage;
    static CartPage cartPage;
    static CheckOutInfo checkOutInfoPage;
    static CheckOutOverview checkOutOverviewPage;
    static CheckOutComplete checkOutCompletePage;


    @BeforeClass
    public static void initializeObjects(){
        frameworkProperties = new FrameworkProperties();
        DriverSingleton.getInstance(frameworkProperties.getProperties(Constants.BROWSER));
        driver = DriverSingleton.getDriver();
        signInPage = new SignInPage();
        shopPage =new ShopPage();
        cartPage = new CartPage();
        checkOutInfoPage = new CheckOutInfo();
        checkOutOverviewPage = new CheckOutOverview();
        checkOutCompletePage = new CheckOutComplete();
    }
    @Test
    public void TestAuthentication(){
        driver.get(Constants.URL);
        signInPage.logIn(frameworkProperties.getProperties(Constants.USERNAME), frameworkProperties.getProperties(Constants.PASSWORD));
        assertTrue(shopPage.isLoaded());
    }
    @Test
    public void TestCart(){
        driver.get(Constants.URL);
        signInPage.logIn(frameworkProperties.getProperties(Constants.USERNAME), frameworkProperties.getProperties(Constants.PASSWORD));
        shopPage.addToCart();
        assertEquals(shopPage.getNumberOfProducts(),"1");
    }
    @Test
    public void TestFullBuyingProcess(){
        driver.get(Constants.URL);
        signInPage.logIn(frameworkProperties.getProperties(Constants.USERNAME), frameworkProperties.getProperties(Constants.PASSWORD));
        shopPage.addToCart();
        shopPage.proceedToCart();
        cartPage.proceedToCheckOut();
        checkOutInfoPage.fillFormToContinue(frameworkProperties.getProperties(Constants.NAME),frameworkProperties.getProperties(Constants.LAST_NAME),frameworkProperties.getProperties(Constants.POSTAL_CODE));
        checkOutOverviewPage.checkoutSubmit();
        checkOutCompletePage.backToHome();
        assertTrue(shopPage.isLoaded());

    }
    @Test
    public void TestLockedAuth(){
        driver.get(Constants.URL);
        signInPage.logIn(frameworkProperties.getProperties(Constants.LOCKED_USERNAME),frameworkProperties.getProperties(Constants.PASSWORD));
        assertEquals(signInPage.getErrorMessage(), frameworkProperties.getProperties(Constants.LOCKED_MESSAGE));
    }
    @Test
    public void TestWrongCredential(){
        driver.get(Constants.URL);
        signInPage.logIn(frameworkProperties.getProperties(Constants.WRONG_USER),frameworkProperties.getProperties(Constants.PASSWORD));
        assertEquals(signInPage.getErrorMessage(), frameworkProperties.getProperties(Constants.WRONG_USER_MESSAGE));
    }
    @AfterClass
    public static void closeDriver(){
        driver.close();
    }

}
