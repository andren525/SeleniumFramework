package automation.glue;


import automation.config.AutomationFrameworkConfiguration;
import automation.drivers.DriverSingleton;
import automation.pages.*;
import automation.utils.ConfigurationProperties;
import automation.utils.Constants;
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

    @Autowired
    ConfigurationProperties configurationProperties;

    @Before
    public void inizializeObject(){
        DriverSingleton.getInstance(configurationProperties.getBrowser());
        signInPage = new SignInPage();
        shopPage = new ShopPage();

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
        shopPage.addToCart();
    }

    @Then("an item is present in the cart")
    public void anItemIsPresentInTheCart() {
        assertEquals("1",shopPage.getNumberOfProducts());

    }
    @After
    public void closeinstance(){
        DriverSingleton.closeObjectInstance();
    }

}
