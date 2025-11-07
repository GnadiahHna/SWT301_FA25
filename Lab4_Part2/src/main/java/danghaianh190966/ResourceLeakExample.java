package danghaianh190966;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ResourceLeakExample {

    private static final Logger LOGGER = Logger.getLogger(ResourceLeakExample.class.getName());

    public static void main(String[] args) {

        // ✅ Dùng try-with-resources để tránh Resource Leak (S2095)
        try (BufferedReader reader = new BufferedReader(new FileReader("example.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                LOGGER.info(line); // thay System.out.println (S106)
            }
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Error reading file: {0}", e.getMessage());
        }
    }
}


