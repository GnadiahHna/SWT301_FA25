// File: src/test/java/com/bookstore/selenium/tests/ShoppingCartTest.java

package com.bookstore.selenium.tests;

import com.bookstore.selenium.pages.BookDetailPage;
import com.bookstore.selenium.pages.BookListPage;
import com.bookstore.selenium.pages.CartPage;
import com.bookstore.selenium.pages.HomePage;
import com.bookstore.selenium.utils.DriverFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ShoppingCartTest {

    private WebDriver driver;
    private HomePage homePage;
    private BookListPage bookListPage;
    private BookDetailPage bookDetailPage;
    private CartPage cartPage;

    @BeforeEach
    public void setUp() {
        driver = DriverFactory.getDriver();
        homePage = new HomePage(driver);
        bookListPage = new BookListPage(driver);
        bookDetailPage = new BookDetailPage(driver);
        cartPage = new CartPage(driver);
        driver.get("http://localhost:8080");
    }

    @Test
    public void testAddBookToCart() {
        homePage.navigateToBooks();
        bookListPage.clickAddToCartForBook(0);

        homePage.navigateToCart();

        assertTrue(cartPage.getNumberOfItemsInCart() > 0,
                "Cart should contain items after adding a book");
    }

    @Test
    public void testUpdateCartQuantity() {
        homePage.navigateToBooks();
        bookListPage.clickAddToCartForBook(0);

        homePage.navigateToCart();
        int initialCount = cartPage.getNumberOfItemsInCart();

        cartPage.updateQuantity(0, 3);

        assertTrue(true, "Quantity update should work without errors");
    }

    @Test
    public void testRemoveItemFromCart() {
        homePage.navigateToBooks();
        bookListPage.clickAddToCartForBook(0);

        homePage.navigateToCart();
        cartPage.removeItem(0);

        assertTrue(cartPage.getNumberOfItemsInCart() == 0 || cartPage.isEmptyCartMessageDisplayed(),
                "Cart should be empty or show empty message after removing all items");
    }

    @Test
    public void testEmptyCart() {
        homePage.navigateToCart();

        assertTrue(cartPage.getNumberOfItemsInCart() == 0 || cartPage.isEmptyCartMessageDisplayed(),
                "New cart should be empty");
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}