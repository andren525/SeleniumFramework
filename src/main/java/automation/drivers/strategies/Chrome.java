package automation.drivers.strategies;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Chrome implements DriverStrategy{
    public WebDriver setStrategy(){
        System.setProperty("webdriver.chrome.driver", "src/main/resources/webdriver/chrome_120/chromedriver");
        ChromeOptions options = new ChromeOptions();
        //change of chrome binary directory
        options.setBinary("../selenium/chrome/linux-120/chrome-linux64/chrome");

        options.setExperimentalOption("useAutomationExtension", "false");
        options.addArguments("--no-sandbox");

        return new ChromeDriver(options);
    }

}
