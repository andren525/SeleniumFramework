package automation.glue;


import automation.config.AutomationFrameworkConfiguration;
import automation.drivers.DriverSingleton;
import automation.pages.*;
import automation.utils.ConfigurationProperties;
import automation.utils.Constants;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;
import org.openqa.selenium.WebDriver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import javax.swing.*;
import java.sql.Driver;

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
    @When("^I specify my credentials and click Login")
    public void i_specify_my_credentials_and_click_Login(){
        signInPage.logIn(configurationProperties.getUsername(), configurationProperties.getPassword());
    }
    @Then("^I can log into the Website")
    public void i_can_log_into_the_Website(){
        assertTrue(shopPage.isLoaded());

    }
}
