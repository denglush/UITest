package com.autotest.demo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class FirstDemo {

    @Test
    public void test() {
        System.setProperty("webdriver.chrome.driver", "/Users/denglulu/Downloads/chromedriver");
//        WebDriver driver = new FirefoxDriver();


//        System.setProperty("webdriver.gecko.driver", "/Users/denglulu/Downloads/geckodriver 3");

        WebDriver driver = new ChromeDriver();
        driver.get("http://test3caiwu.back.so/");
        driver.findElement(By.id("loginform-username")).sendKeys("18721356120");
        driver.findElement(By.id("loginform-password")).sendKeys("123456");
        driver.findElement(By.name("login-button")).click();

    }

}