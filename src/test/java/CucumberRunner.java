import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

/**
 * Created by Nader on 12/05/2018.
 */

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/java/features",glue = {"src/test/java/UserStepdefs.java"},plugin = {"pretty"})
public class CucumberRunner {
}

