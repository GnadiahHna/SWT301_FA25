package danghaianh190966;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PathTraversalExample {
    private static final Logger LOGGER = Logger.getLogger(PathTraversalExample.class.getName());
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        LOGGER.info("Enter file name: ");
        String fileName = scanner.nextLine();
        File file = new File(fileName);
        try {
            String canonicalPath = file.getCanonicalPath();
            if (!canonicalPath.startsWith(new File(".").getCanonicalPath())) {
                throw new SecurityException("Invalid file path access attempt!");
            }

            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    LOGGER.info(line);
                }
            }

        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Error reading file: {0}", e.getMessage());
        } catch (SecurityException e) {
            LOGGER.log(Level.WARNING, "Security issue: {0}", e.getMessage());
        } finally {
            scanner.close();
        }
    }
}
