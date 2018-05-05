package utils;

import java.util.UUID;

/**
 * Created by Nader on 03/05/2018.
 */
public class Core {

    public static String GenerateRandomUUIDString() {
        // Creating a random UUID (Universally unique identifier).
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }
}


