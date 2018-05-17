package clients;

import javax.json.*;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * Created by Nader on 16/05/2018.
 */
public class BankAccountClient {
    private static final String RELATIVE_PATH = "bankaccounts";
    public static List<String> getAllBankAccounts() throws IOException, URISyntaxException {
        List<String> allBankAccounts = new ArrayList<String>();
        JsonArray result = RestClient.get(RELATIVE_PATH);
        result.forEach( u-> allBankAccounts.add(((JsonObject)u).getString("accountNummber")));
        return allBankAccounts;
    }

    public static JsonObject getBankAccount( String accountNumber) throws IOException, URISyntaxException {
        JsonObject result = null;
        JsonArray bankAccounts = RestClient.get(RELATIVE_PATH);
        for (JsonValue item:bankAccounts) {
            JsonObject bankAccount = (JsonObject) item;
            String actual = bankAccount.getString("accountNumber");
            if (!Objects.equals(actual, accountNumber)) {
                continue;
            }
            result=bankAccount;
        }
        return result;
    }

    public static String createBankAccount(String accountNumber, String user) throws UnsupportedEncodingException {
        JsonObjectBuilder builder = Json.createObjectBuilder();
        builder.add("accountNumber", accountNumber);
        builder.add("user", user);
        JsonObject result = RestClient.post(RELATIVE_PATH, builder.build());
        return result.getString("accountNumber");
    }
}
