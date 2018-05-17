package stepDefinitions;

import clients.BankAccountClient;
import clients.BankingTransactionClient;
import clients.UserClient;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.runtime.Utils;
import org.junit.Assert;

import javax.json.JsonObject;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static clients.UserClient.*;

/**
 * Created by Nader on 12/05/2018.
 */
public  class UserStepdef {
    private final Map<String, String> variableMap;

    public UserStepdef() {
       variableMap = new HashMap<String,String>();
    }

    @Then("^I should find a user with email address \"([^\"]*)\"$")
    public void iShouldFindAUserWithEmailAddress(String arg0) throws Throwable {
        String encodedEmail =  variableMap.get(arg0);
        List<String> users= UserClient.getAllUsers();
        boolean userExists= users.contains(encodedEmail);
        Assert.assertTrue(userExists);
    }

    @When("^I register a user with email address \"([^\"]*)\"$")
    public void iRegisterAUserWithEmaiAddress(String arg0) throws Throwable {
        createUser(arg0);
    }

    private void createUser(String arg0) throws UnsupportedEncodingException {
        String encodedEmail = utils.Core.GenerateRandomUUIDString();
        variableMap.put(arg0, encodedEmail);
        String email = UserClient.createUser(encodedEmail);
        Assert.assertEquals(encodedEmail,email);
    }

    @Given("^I have registered a user with email \"([^\"]*)\"$")
    public void iHaveRegisterdAUserWithEmail(String arg0) throws Throwable {
        createUser(arg0);
    }

    @When("^I register a bank account with account number \"([^\"]*)\" for user \"([^\"]*)\"$")
    public void iRegisterABankAccountWithAccountNumberForUser(String arg0, String arg1) throws Throwable {
        createBankAccount(arg0, arg1);
    }

    private void createBankAccount(String arg0, String arg1) throws UnsupportedEncodingException {
        String encodedAccountNumber = utils.Core.GenerateRandomUUIDString();
        variableMap.put(arg0, encodedAccountNumber);
        String encodedEmail =  variableMap.get(arg1);
        String accountNumber = BankAccountClient.createBankAccount(encodedAccountNumber,encodedEmail);
        Assert.assertEquals(encodedAccountNumber,accountNumber);
    }

    @Then("^I should find a bank account with account number \"([^\"]*)\" and balance (\\d+) for user \"([^\"]*)\"$")
    public void iShouldFindABankAccountWithAccountNumberAndBalanceForUser(String arg0, int arg1, String arg2) throws Throwable {
        String encodedAccountNumber =  variableMap.get(arg0);
        JsonObject bankAcccount= BankAccountClient.getBankAccount(encodedAccountNumber);

        Assert.assertEquals(arg1,bankAcccount.getInt("balance"));
        Assert.assertEquals(variableMap.get(arg2),bankAcccount.getString("user"));
    }

    @And("^I have registered a bank account with account number \"([^\"]*)\" for user \"([^\"]*)\"$")
    public void iHaveRegisterABankAccountWithAccountNumberForUser(String arg0, String arg1) throws Throwable {
        createBankAccount(arg0, arg1);
    }

    @When("^I deposit (\\d+) units in account \"([^\"]*)\"$")
    public void iDepositUnitsInAccount(int arg0, String arg1) throws Throwable {
        String encodedAccountNumber =  variableMap.get(arg1);
        String id = BankingTransactionClient.createDepositBankingTransaction(encodedAccountNumber,arg0);
        variableMap.put("depositId",id);
        Assert.assertTrue(id != null);
    }

    @And("^I should find a deposit transaction on credit account \"([^\"]*)\" with amount (\\d+)$")
    public void iShouldFindADepositTransactionOnCreditAccountWithAmount(String arg0, int arg1) throws Throwable {
        String encodedAccountNumber =  variableMap.get(arg0);
        String id = variableMap.get("depositId");
        JsonObject bt = BankingTransactionClient.getBankingTransactions(id);
        Assert.assertEquals("DEPOSIT", bt.getString("transactionType"));
        Assert.assertEquals(encodedAccountNumber, bt.getString("creditAccount"));
        Assert.assertEquals(arg1, bt.getInt("amount"));
    }

    @And("^I withdraw (\\d+) units from account \"([^\"]*)\"$")
    public void iWithdrawUnitsFromAccount(int arg0, String arg1) throws Throwable {
        String encodedAccountNumber =  variableMap.get(arg1);
        String id = BankingTransactionClient.createWithdrawBankingTransaction(encodedAccountNumber,arg0);
        variableMap.put("withdrawId",id);
        Assert.assertTrue(id != null);
    }

    @And("^I should find a withdraw transaction on credit account \"([^\"]*)\" with amount (\\d+)$")
    public void iShouldFindAWithdrawTransactionOnCreditAccountWithAmount(String arg0, int arg1) throws Throwable {
        String encodedAccountNumber =  variableMap.get(arg0);
        String id = variableMap.get("withdrawId");
        JsonObject bt = BankingTransactionClient.getBankingTransactions(id);
        Assert.assertEquals("WITHDRAW", bt.getString("transactionType"));
        Assert.assertEquals(encodedAccountNumber, bt.getString("debitAccount"));
        Assert.assertEquals(arg1, bt.getInt("amount"));
    }

    @And("^I Transfer (\\d+) units from account \"([^\"]*)\" to account \"([^\"]*)\"$")
    public void iTransferUnitsFromAccountToAccount(int arg0, String arg1, String arg2) throws Throwable {
        String fromAccount =  variableMap.get(arg1);
        String toAccount =  variableMap.get(arg2);
        String id = BankingTransactionClient.createTransferBankingTransaction(fromAccount,toAccount,arg0);
        variableMap.put("transferId",id);
        Assert.assertTrue(id != null);
    }

    @And("^I should find a transfer transaction from account \"([^\"]*)\" to account \"([^\"]*)\" with amount (\\d+)$")
    public void iShouldFindATransferTransactionFromAccountToAccountWithAmount(String arg0, String arg1, int arg2) throws Throwable {
        String fromAccount =  variableMap.get(arg0);
        String toAccount =  variableMap.get(arg1);
        String id = variableMap.get("transferId");
        JsonObject bt = BankingTransactionClient.getBankingTransactions(id);
        Assert.assertEquals("TRANSFER", bt.getString("transactionType"));
        Assert.assertEquals(fromAccount, bt.getString("debitAccount"));
        Assert.assertEquals(toAccount, bt.getString("creditAccount"));
        Assert.assertEquals(arg2, bt.getInt("amount"));
    }
}
