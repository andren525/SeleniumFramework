package automation;

import io.cucumber.java.lv.Tad;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"pretty","html:target/cucumber-reports.html"},
        features = "src/main/resources/features/" ,
        glue = {"automation.glue"}

)
public class RunTests {

}
