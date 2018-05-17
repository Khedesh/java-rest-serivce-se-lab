package clients;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import javax.json.*;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by Nader on 16/05/2018.
 */
public class BankingTransactionClient {
    private static final String RELATIVE_PATH = "bankingtransactions";
    public static List<String> getAllBankingTransactions() throws IOException, URISyntaxException {
        List<String> allBankingTransactions = new ArrayList<String>();
        JsonArray result = RestClient.get(RELATIVE_PATH);
        result.forEach( u-> allBankingTransactions.add(((JsonObject)u).getString("id")));
        return allBankingTransactions;
    }

    public static JsonObject getBankingTransactions(String id) throws IOException, URISyntaxException {
        return RestClient.get(RELATIVE_PATH+"/"+id);
    }

    public static String createDepositBankingTransaction(String creditAccount, int amount) throws UnsupportedEncodingException {
        JsonObjectBuilder builder = Json.createObjectBuilder();
        builder.add("amount", amount);
        builder.add("creditAccount", creditAccount);
        builder.add("transactionType","DEPOSIT");
        JsonObject result = RestClient.post(RELATIVE_PATH, builder.build());
        return result.getString("id");
    }

    public static String createWithdrawBankingTransaction(String debitAccount, int amount) throws UnsupportedEncodingException {
        JsonObjectBuilder builder = Json.createObjectBuilder();
        builder.add("amount", amount);
        builder.add("debitAccount", debitAccount);
        builder.add("transactionType","WITHDRAW");
        JsonObject result = RestClient.post(RELATIVE_PATH, builder.build());
        return result.getString("id");
    }
    public static String createTransferBankingTransaction(String fromAccount, String toAccount, int amount) throws UnsupportedEncodingException {
        JsonObjectBuilder builder = Json.createObjectBuilder();
        builder.add("amount", amount);
        builder.add("debitAccount", fromAccount);
        builder.add("creditAccount", toAccount);
        builder.add("transactionType","TRANSFER");
        JsonObject result = RestClient.post(RELATIVE_PATH, builder.build());
        return result.getString("id");
    }
}
