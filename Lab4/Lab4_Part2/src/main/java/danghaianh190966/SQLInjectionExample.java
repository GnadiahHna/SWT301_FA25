package danghaianh190966;

import java.util.logging.Logger;

public class SQLInjectionExample {
    private static final Logger LOGGER = Logger.getLogger(SQLInjectionExample.class.getName());
    public static void main(String[] args) {
        String userInput = "' OR '1'='1";
        final String queryTemplate = "SELECT * FROM users WHERE username = ?";

        if (LOGGER.isLoggable(java.util.logging.Level.INFO)) {
            LOGGER.info(() -> String.format("Executing query template: %s with parameter: %s", queryTemplate, userInput));
        }
    }
}
