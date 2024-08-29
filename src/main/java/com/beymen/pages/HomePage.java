package com.beymen.pages;

import org.openqa.selenium.*;
import com.beymen.utils.ExcelUtils;


public class HomePage {
    public WebDriver driver;
    public static By searchBox = By.className("o-header__search--input"); // Arama kutucuğu
    public static By textDeletionButton = By.xpath("//button[normalize-space()='Sil']//*[name()='svg']");
    public By genderManButton = By.id("genderManButton"); // Erkek cinsiyet seçme butonu
    public By acceptAllCookiesButton = By.id("onetrust-accept-btn-handler"); // Tüm Çerezleri Kabul Et seçme butonu
    public By ignoreTheNotificationsButton = By.className("dn-slide-deny-btn"); // Bilgilendirmeleri Reddet seçme butonu

    public String searchTerm1 = "";
    public String searchTerm2 = "";

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public void navigateToHomePage() throws InterruptedException {
        driver.get("https://www.beymen.com");
        Thread.sleep(4000);
    }

    public boolean isHomePageDisplayed() {
        return driver.getTitle().contains("Beymen");
    }

    public void clickGenderManButton() throws InterruptedException {
        driver.findElement(genderManButton).click();
        Thread.sleep(4000);
    }

    public void clickAcceptAllCookiesButton() throws InterruptedException {
        driver.findElement(acceptAllCookiesButton).click();
        Thread.sleep(4000);
    }

    public void clickIgnoreTheNotificationsButton() throws InterruptedException {
        driver.findElement(ignoreTheNotificationsButton).click();
        Thread.sleep(3000);
    }

    public void clickTextDeletionButton() throws InterruptedException {
        driver.findElement(textDeletionButton).click();
        Thread.sleep(3000);
    }

    public void enterSearchText(String text) throws InterruptedException {
        driver.findElement(searchBox).sendKeys(text);
        Thread.sleep(3000);
    }

    public void pressEnterToSearch() throws InterruptedException {
        driver.findElement(searchBox).sendKeys(Keys.ENTER);
        Thread.sleep(3000);
    }

    public void testSearchWithExcelData() throws InterruptedException {
        // Excel dosyasını yükle
        ExcelUtils.loadExcel("C:\\Users\\Administrator\\Desktop\\TestiniumProject\\Beymen.xlsx", 0);

        // 1. satır, 1. sütundaki veriyi al ve arama kutusuna yaz
        searchTerm1 = ExcelUtils.getCellData(0, 0);
        System.out.println("searchTerm1 = " + searchTerm1);
        Thread.sleep(1000);
        enterSearchText(searchTerm1);
        Thread.sleep(3000);

        // Arama kutusunu temizle ve 1. satır, 2. sütundaki veriyi yaz
        clickTextDeletionButton();
        Thread.sleep(1000);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("location.reload()");
        searchTerm2 = ExcelUtils.getCellData(0, 1);
        System.out.println("searchTerm2 = " + searchTerm2);
        Thread.sleep(1000);
        enterSearchText(searchTerm2);
        Thread.sleep(1000);
        pressEnterToSearch();
        Thread.sleep(3000);

        // Excel dosyasını kapat
        ExcelUtils.closeExcel();
    }
}
