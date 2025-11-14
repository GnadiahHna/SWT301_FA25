package com.bookstore.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AdminPage extends BasePage {

    @FindBy(linkText = "Dashboard")
    private WebElement dashboardLink;

    @FindBy(linkText = "Books")
    private WebElement booksLink;

    @FindBy(linkText = "Categories")
    private WebElement categoriesLink;

    @FindBy(linkText = "Orders")
    private WebElement ordersLink;

    @FindBy(linkText = "Users")
    private WebElement usersLink;

    @FindBy(id = "addBookButton")
    private WebElement addBookButton;

    @FindBy(id = "bookSearch")
    private WebElement bookSearchInput;

    @FindBy(xpath = "//a[contains(text(),'Books')]")
    private WebElement booksLinkXPath;

    @FindBy(css = "a[href*='/books']")
    private WebElement booksLinkHref;

    public AdminPage(WebDriver driver) {
        super(driver);
    }

    public void navigateToDashboard() {
        safeClick(dashboardLink);
    }

    public void navigateToBooks() {
        waitForPageLoad();

        try {
            // Thử tìm bằng link text trước
            if (booksLink.isDisplayed() && booksLink.isEnabled()) {
                safeClick(booksLink);
                return;
            }
        } catch (Exception e) {
            // Continue to fallback methods
        }

        try {
            // Fallback 1: tìm bằng XPath
            if (booksLinkXPath.isDisplayed() && booksLinkXPath.isEnabled()) {
                safeClick(booksLinkXPath);
                return;
            }
        } catch (Exception e) {
            // Continue to next fallback
        }

        try {
            // Fallback 2: tìm bằng CSS selector
            if (booksLinkHref.isDisplayed() && booksLinkHref.isEnabled()) {
                safeClick(booksLinkHref);
                return;
            }
        } catch (Exception e) {
            // Continue to next fallback
        }

        try {
            // Fallback 3: tìm động
            WebElement dynamicBooksLink = driver.findElement(By.xpath("//a[contains(translate(text(), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'books')]"));
            safeClick(dynamicBooksLink);
        } catch (Exception e) {
            throw new RuntimeException("Could not find Books link on admin page. Current URL: " + driver.getCurrentUrl());
        }
    }

    public void navigateToCategories() {
        safeClick(categoriesLink);
    }

    public void navigateToOrders() {
        safeClick(ordersLink);
    }

    public void navigateToUsers() {
        safeClick(usersLink);
    }

    public void clickAddBook() {
        safeClick(addBookButton);
    }

    public void searchBooks(String searchTerm) {
        sendKeysToElement(bookSearchInput, searchTerm);
    }

    public boolean isAdminPanelDisplayed() {
        try {
            return driver.getCurrentUrl().contains("/admin") ||
                    driver.getTitle().toLowerCase().contains("admin") ||
                    isElementPresent(By.xpath("//*[contains(text(), 'Admin')]")) ||
                    isElementPresent(By.xpath("//*[contains(text(), 'Dashboard')]"));
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isBooksLinkPresent() {
        try {
            return booksLink.isDisplayed() ||
                    booksLinkXPath.isDisplayed() ||
                    booksLinkHref.isDisplayed() ||
                    isElementPresent(By.linkText("Books")) ||
                    isElementPresent(By.xpath("//a[contains(text(),'Books')]"));
        } catch (Exception e) {
            return false;
        }
    }
}