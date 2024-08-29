package com.beymen.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CartPage {
    public WebDriver driver;
    public By cartProductPrice = By.className("priceBox__salePrice");
    public By increaseQuantityButton = By.id("quantitySelect0-key-0");
    public By removeProductButton = By.id("removeCartItemBtn0-key-0");

    String productPrice = "";
    String normalizedProductPrice = "";

    public CartPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getCartProductPrice() {
        productPrice = driver.findElement(cartProductPrice).getText();
        normalizedProductPrice = normalizePrice(productPrice);
        return normalizedProductPrice;

    }

    public void increaseProductQuantity() throws InterruptedException {
        driver.findElement(increaseQuantityButton).click();
        Thread.sleep(1000);
        WebElement firstOption = driver.findElement(By.xpath("//*[@id=\"quantitySelect0-key-0\"]/option[2]"));
        firstOption.click();
        Thread.sleep(2000);
    }

    public void removeProductFromCart() throws InterruptedException {
        driver.findElement(removeProductButton).click();
        Thread.sleep(2000);
    }

    public boolean isCartEmpty() {

        WebElement cartMessage = driver.findElement(By.xpath("//*[contains(text(), 'Sepetinizde Ürün Bulunmamaktadır')]"));

        if (cartMessage.isDisplayed()) {
            System.out.println("Sepet boş.");
            return true;
        } else {
            System.out.println("Sepet boş değil.");
            return false;
        }
    }

    public static String normalizePrice(String price) {
        price = price.replaceAll(" TL", "");
        String formattedPrice = price.replace(",00", "") + " TL";
        return formattedPrice;
    }
}
