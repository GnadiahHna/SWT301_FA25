import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertTrue;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DisplayName("Login Tests with Parameterized Data")
public class LoginTest {
    static WebDriver driver;
    static WebDriverWait wait;

    @BeforeAll
    static void setUp() {
        WebDriverManager.chromedriver().setup();

        // Cấu hình Chrome
        ChromeOptions options = new ChromeOptions();
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("profile.managed_default_content_settings.javascript", 2); // Tắt JavaScript
        options.setExperimentalOption("prefs", prefs);
        options.addArguments("--incognito");

        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().window().maximize();
    }

    @Order(1)
    @ParameterizedTest(name = "Test Login - Username: {0}, Password: {1}, Expected: {2}")
    @CsvSource({
            "tomsmith, SuperSecretPassword!, success",     // đúng
            "wronguser, SuperSecretPassword!, error",       // sai username
            "tomsmith, wrongpassword, error",               // sai password
            "'', '', error"                                 // trống
    })
    @DisplayName("Multiple login attempts using @CsvSource")
    void testLoginWithMultipleParameters(String username, String password, String expectedResult) {
        driver.get("https://the-internet.herokuapp.com/login");

        // nhập dữ liệu
        driver.findElement(By.id("username")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.cssSelector("button[type='submit']")).click();

        // xác định locator cho message
        By messageLocator = expectedResult.equals("success")
                ? By.cssSelector(".flash.success")
                : By.cssSelector(".flash.error");

        // chờ hiển thị
        WebElement message = wait.until(
                ExpectedConditions.visibilityOfElementLocated(messageLocator)
        );

        if (expectedResult.equals("success")) {
            assertTrue(message.getText().contains("You logged into a secure area!"));
        } else {
            assertTrue(message.getText().toLowerCase().contains("invalid"));
        }
    }

    @AfterAll
    static void tearDown() {
        driver.quit();
    }

    @ParameterizedTest(name = "Test login with: {0} / {1}")
    @CsvFileSource(resources = "/login-data.csv", numLinesToSkip = 1)
    @DisplayName("Login with data from external CSV file")
    void testLoginWithCSV(String username, String password, String expectedResult) {
        driver.get("https://the-internet.herokuapp.com/login");
        // Chuyển null thành chuỗi rỗng nếu cần
        username = (username == null) ? "" : username.trim();
        password = (password == null) ? "" : password.trim();

        driver.findElement(By.id("username")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.cssSelector("button[type='submit']")).click();

        By messageLocator = expectedResult.equals("success")
                ? By.cssSelector(".flash.success")
                : By.cssSelector(".flash.error");

        WebElement message = wait.until(ExpectedConditions.visibilityOfElementLocated(messageLocator));

        if (expectedResult.equals("success")) {
            assertTrue(message.getText().contains("You logged into a secure area!"));
        } else {
            assertTrue(message.getText().toLowerCase().contains("invalid"));
        }
    }


}
