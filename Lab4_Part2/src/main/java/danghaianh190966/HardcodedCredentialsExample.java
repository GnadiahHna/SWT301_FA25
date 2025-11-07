package danghaianh190966;
import java.util.Scanner;
import java.util.logging.Logger;

public class HardcodedCredentialsExample {
    private static final Logger LOGGER = Logger.getLogger(HardcodedCredentialsExample.class.getName());
    public static void main(String[] args) {
        LOGGER.info("Starting login process...");
        Scanner scanner = new Scanner(System.in);

        LOGGER.info("Enter username: ");
        String username = scanner.nextLine();

        LOGGER.info("Enter password: ");
        String password = scanner.nextLine();

        if ("admin".equals(username) && "123456".equals(password)) {
            LOGGER.info("Login successful!");
        } else {
            LOGGER.warning("Invalid credentials.");
        }

        scanner.close();
        LOGGER.info("Program finished.");
    }
}
