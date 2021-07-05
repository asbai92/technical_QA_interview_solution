package com.capitoleconsulting.technicalqainterview.testengine.example.controllers;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class UiController {
    private WebDriver driver;

    /**
     * Constructor
     */
    public UiController() {
        WebDriverManager.chromedriver().setup();
    }

    public void open(String url) {
        this.driver = new ChromeDriver();
        driver.get(url);
    }
}
