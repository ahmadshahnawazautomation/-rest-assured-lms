package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

@CucumberOptions(
        features = "src/test/resources/features",
        glue = "stepDefinitions",
        plugin = {"pretty", "html:target/Reports/APITest_cucumber-reports.html"},
        monochrome = true,
        tags=   "@Smoke"
)
public class TestRunner extends AbstractTestNGCucumberTests {

}
