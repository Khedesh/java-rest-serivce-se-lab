package controllers;

import javax.json.JsonArray;
import javax.json.JsonObject;

/**
 * Created by Nader on 04/05/2018.
 */
public interface IBankingTransactionFacadeService {
    JsonArray getAll();
    JsonObject create(JsonObject jsonObject);

    JsonObject get(String id);
}
