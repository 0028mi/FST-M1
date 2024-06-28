package testRunner;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
    features = "src/test/java/features",
    glue = {"stepDefinitions"},
    publish = true,
    plugin = {
    	    	"pretty",
    	    	"html:src/reports/HTML_Reort.html",
    	    	"json:src/reports/JSON_Report.json",
    	    	"junit:src/reports/XML_Report.xml"
    	    },
    monochrome = true
)

public class ActivitiesRunner {
    //empty
}

