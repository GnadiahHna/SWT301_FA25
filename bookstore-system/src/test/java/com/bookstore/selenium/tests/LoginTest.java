package com.bookstore.selenium.tests;

import com.bookstore.selenium.pages.LoginPage;
import com.bookstore.selenium.utils.DriverFactory;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginTest {

    @Test
    public void testAdminLogin() {
        WebDriver driver = null;
        try {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--headless=new");
            driver = new ChromeDriver(options);
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

            LoginPage loginPage = new LoginPage(driver);

            System.out.println("🌐 Navigating to login page...");
            driver.get("http://localhost:8080/login");

            System.out.println("🔐 Attempting login...");
            // Thay bằng credentials thực tế của bạn
            loginPage.login("admin", "admin123");

            String currentUrl = driver.getCurrentUrl();
            System.out.println("✅ After login - URL: " + currentUrl);

            // Kiểm tra đã login thành công (không còn ở trang login)
            boolean loginSuccess = !currentUrl.contains("/login");
            assertTrue(loginSuccess, "Should be redirected from login page after successful login");

        } finally {
            if (driver != null) {
                driver.quit();
            }
        }
    }
}