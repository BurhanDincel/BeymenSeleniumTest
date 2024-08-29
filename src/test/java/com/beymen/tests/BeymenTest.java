package com.beymen.tests;

import com.beymen.pages.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import java.io.FileWriter;
import java.io.IOException;

public class BeymenTest {
    WebDriver driver;
    private HomePage homePage;
    private SearchResultsPage searchResultsPage;
    private ProductsPage productPage;
    private CartPage cartPage;


    @BeforeAll
    public static void setupAll() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().deleteAllCookies();
        homePage = new HomePage(driver);
        searchResultsPage = new SearchResultsPage(driver);
        productPage = new ProductsPage(driver);
        cartPage = new CartPage(driver);

        driver.manage().window().maximize();
    }

    @AfterEach
    void tearDown() {
        driver.quit();
    }

    @Test
    public void testBeymenScenario() throws IOException, InterruptedException {
        final Logger logger = LogManager.getLogger(BeymenTest.class);

        logger.info("Ana sayfa açılıyor.");
        homePage.navigateToHomePage();
        if (homePage.isHomePageDisplayed()) {
            logger.info("Ana sayfa başarıyla açıldı.");
        } else {
            logger.error("Ana sayfa açılmadı!");
        }
        Assertions.assertTrue(homePage.isHomePageDisplayed(), "Ana sayfa açılmadı!");

        homePage.clickAcceptAllCookiesButton();
        logger.info("Çerez seçimi için 'Tüm Çerezleri Kabul Et' seçenek butonuna tıklandı.");

        homePage.clickGenderManButton();
        logger.info("Cinsiyet seçimi için 'Erkek' seçenek butonuna tıklandı.");

        homePage.clickIgnoreTheNotificationsButton();
        logger.info("Bilgilendirme için 'Hayır,Teşekkürler' seçenek butonuna tıklandı.");

        logger.info("Arama kutucuğuna veri giriliyor.");
        homePage.testSearchWithExcelData();

        logger.info("Arama sonuçları kontrol ediliyor.");
        Assertions.assertTrue(searchResultsPage.isSearchResultsDisplayed(), "Arama sonuçları görüntülenemedi!");

        logger.info("Rastgele bir ürün seçiliyor.");
        searchResultsPage.selectRandomProduct();

        String productName = productPage.getProductName();
        String productPrice = productPage.getProductPrice();
        logger.info("Seçilen ürün: " + productName + ", Fiyat: " + productPrice);

        FileWriter writer = new FileWriter("productInfo.txt");
        writer.write("Ürün Adı: " + productName + "\n");
        writer.write("Ürün Fiyatı: " + productPrice + "\n");
        writer.close();

        logger.info("Ürün sayfasına gidiliyor.");
        productPage.clickToPhoto();

        logger.info("Rastgele bir beden seçiliyor.");
        productPage.selectRandomProductSize();

        logger.info("Ürün sepete ekleniyor.");
        productPage.addToCart();

        logger.info("Sepete gidiliyor.");
        productPage.clickGoToCart();

        logger.info("Farklı dönen fiyat formatı düzeltiliyor");


        String cartPagePrice = cartPage.getCartProductPrice();
        Assertions.assertEquals(productPrice, cartPagePrice, "Ürün fiyatları eşleşmiyor!");

        logger.info("Ürün fiyatları doğrulandı. productPrice: " + productPrice + ", cartPagePrice: " + cartPagePrice);

        cartPage.increaseProductQuantity();
        logger.info("Ürün adedi artırıldı.");

        cartPage.removeProductFromCart();
        Assertions.assertTrue(cartPage.isCartEmpty(), "Sepet boş değil!");
        logger.info("Sepet boşaltıldı.");
    }
}
