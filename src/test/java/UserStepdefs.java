import clients.UserClient;
import cucumber.api.PendingException;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;

import java.util.List;

/**
 * Created by Nader on 12/05/2018.
 */

public class UserStepdefs {
    @When("^i register a user with emai address \"([^\"]*)\"$")
    public void iRegisterAUserWithEmaiAddress(String arg0) throws Throwable {
        // Write code here that turns the phrase above into concrete actions

    }

    @Then("^i should find a user with email address \"([^\"]*)\"$")
    public void iShouldFindAUserWithEmailAddress(String arg0) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        UserClient client = new UserClient();
        List<String> users= client.getAllUsers();
        Assert.assertTrue(users.contains(arg0));
    }
}
