package automation.glue;


import automation.config.AutomationFrameworkConfiguration;
import automation.drivers.DriverSingleton;
import automation.pages.*;
import automation.utils.ConfigurationProperties;
import automation.utils.Constants;
import automation.utils.Utils;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import javax.swing.*;
import java.io.DataInput;
import java.sql.Driver;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@CucumberContextConfiguration
@ContextConfiguration(classes = AutomationFrameworkConfiguration.class)
public class StepDefinition {
    private WebDriver driver;
    private SignInPage signInPage;
    private ShopPage shopPage;
    private ItemPage backpackPage;
    private CartPage cartPage;

    @Autowired
    ConfigurationProperties configurationProperties;

    @Before
    public void inizializeObject(){
        DriverSingleton.getInstance(configurationProperties.getBrowser());
        signInPage = new SignInPage();
        shopPage = new ShopPage();
        backpackPage = new ItemPage();
        cartPage = new CartPage();
    }

    @Given("^I go to the Website")
    public void i_go_to_the_Website(){
        driver = DriverSingleton.getDriver();
        driver.get(Constants.URL);
    }

    @When("I specify my username as {string} and my password as {string}")
    public void iSpecifyMyUsernameAsUsernameAndMyPasswordAsPassword(String username, String password) {
        signInPage.logIn(username, password);
    }

    @Then("^I can log into the Website")
    public void i_can_log_into_the_Website(){
        assertTrue(shopPage.isLoaded());
    }

    @Then("I expect validation message as {string} is displayed")
    public void iExpectValidationMessageAsMessageIsDisplayed(String message) {
        assertEquals(signInPage.getErrorMessage(),configurationProperties.getMessage(message));
    }

    @Given("I am logged with username as {string} and password as {string}")
    public void iAmLoggedWithUsernameAsStandard_userAndPassword(String username,String password) {
        driver = DriverSingleton.getDriver();
        driver.get(Constants.URL);
        signInPage.logIn(username, password);
    }

    @And("I enter the shop page")
    public void iEnterTheShopPage() {
        assertTrue(shopPage.isLoaded());
    }

    @When("I add an item to the cart")
    public void iAddAnItemToTheCart() {
        shopPage.addToCart("Sauce Labs Backpack");
    }

    @Then("an item is present in the cart")
    public void anItemIsPresentInTheCart() {
        assertEquals("1",shopPage.getNumberOfProducts());

    }
    @After
    public void closeinstance(){
        DriverSingleton.closeObjectInstance();
    }


    @When("I go to the item page")
    public void iGoToTheItemPage() {
        shopPage.goToItemPage("Sauce Labs Backpack");

    }

    @And("I add the item to the cart")
    public void iAddTheItemToTheCart() {
        backpackPage.addtocart();
    }

    @Then("cart is been updated")
    public void cartIsBeenUpdated() {
        assertEquals("1",backpackPage.getNumberOfProducts());

    }

    @When("I remove the item")
    public void iRemoveTheItem() {
        shopPage.removefromcart("Sauce Labs Backpack");
    }

    @Then("cart displays no items")
    public void cartDisplaysNoItems() {
        assertEquals(null,shopPage.getNumberOfProducts());

    }

    @And("I am on the cart page")
    public void iAmOnTheCartPage() {
        shopPage.proceedToCart();
        assertEquals(true,cartPage.isLoaded());
    }

    @When("I remove the item from the cart")
    public void iRemoveTheItemFromTheCart() {
        cartPage.removeBackpack();
    }

    @Then("There are no items displayed in the cart page")
    public void thereAreNoItemsDisplayedInTheCartPage() {
        assertEquals(null, cartPage.getNumberOfProducts());
    }

    @When("I remove the item from the item page")
    public void iRemoveTheItemFromTheItemPage() {
        backpackPage.removeFromCart();
    }

    @Then("there are no items displayed in the cart")
    public void thereAreNoItemsDisplayedInTheCart() {
        assertEquals(null,backpackPage.getNumberOfProducts());
    }
}
