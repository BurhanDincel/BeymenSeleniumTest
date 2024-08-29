package com.beymen.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Random;

public class SearchResultsPage {
    private WebDriver driver;
    private By productList = By.className("o-searchSuggestion__searchResulProducts");


    public SearchResultsPage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isSearchResultsDisplayed() {
        return !driver.findElements(productList).isEmpty();
    }

    public void selectRandomProduct() {
        List<WebElement> products = driver.findElements(productList);
        Random random = new Random();
        int randomProductIndex = random.nextInt(products.size());
        products.get(randomProductIndex).click();
    }


}
