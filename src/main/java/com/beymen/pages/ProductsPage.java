package com.beymen.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Random;


public class ProductsPage {
    public WebDriver driver;
    public By productName = By.className("o-productCard__content--desc");
    public By productPrice = By.className("m-productCard__newPrice");
    public By productSize = By.className("m-variation__item");
    public By goToCartButton = By.xpath("/html/body/header/div/div/div[3]/div/a[3]");
    public By productPhotoButton = By.className("o-productCard__figure");
    public By addToCartButton = By.id("addBasket");


    public ProductsPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getProductName() {
        return driver.findElement(productName).getText();
    }

    public String getProductPrice() {
        return driver.findElement(productPrice).getText();
    }

    public void clickToPhoto() {
        driver.findElement(productPhotoButton).click();
    }

    public void selectRandomProductSize() {
        List<WebElement> allSizes = driver.findElements(productSize);

        List<WebElement> availableSizes = allSizes.stream()
                .filter(WebElement::isEnabled)
                .filter(size -> !size.getAttribute("class").contains("disabled"))
                .toList();

        Random random = new Random();
        WebElement randomSize = availableSizes.get(random.nextInt(availableSizes.size()));
        randomSize.click();
    }

    public void addToCart() throws InterruptedException {
        driver.findElement(addToCartButton).click();
        Thread.sleep(5000);
    }

    public void clickGoToCart() throws InterruptedException {
        driver.findElement(goToCartButton).click();
        Thread.sleep(5000);
    }
}