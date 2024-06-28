package testRunner;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
    features = "src/test/java/features",
    glue = {"stepDefinitions"},
    tags = "@SimpleAlert",
    plugin = {"json: test-report/json-report.json"},
    monochrome = true
)

public class ActivitiesRunner {
    //empty
}

