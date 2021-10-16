package com.readinglist.readinglist;

import com.denglitong.readinglist.ReadinglistApplication;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.TimeUnit;

/**
 * ChromeBrowser - ChromeDriver - Selenium - SpringTest
 * ChromeDriver download: https://chromedriver.chromium.org/downloads
 *
 * @author litong.deng@foxmail.com
 * @date 2021/10/16
 */
@RunWith(SpringRunner.class)
@SpringBootTest(
        classes = ReadinglistApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SimpleWebTest_Selenium {

    private static ChromeDriver browser;

    @LocalServerPort
    private int randomPort;

    @BeforeClass
    public static void openBrowser() {
        System.setProperty("webdriver.chrome.driver", "/Users/leon/Downloads/packages/chromedriver");
        browser = new ChromeDriver();
        browser.manage()
                .timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @AfterClass
    public static void closeBrowser() {
        // 关闭浏览器
        browser.quit();
    }

    @Test
    @WithUserDetails("denglitong")
    public void addBookToEmptyList() {
        String baseUrl = "http://localhost:" + randomPort + "/readingList/dlt";
        browser.get(baseUrl);

        Assert.assertEquals("You have no books in your book list",
                browser.findElement(By.tagName("div")).getText());

        // 填充表单并发送
        browser.findElement(By.name("title")).sendKeys("BOOK TITLE");
        browser.findElement(By.name("author")).sendKeys("BOOK AUTHOR");
        browser.findElement(By.name("isbn")).sendKeys("1234567890");
        browser.findElement(By.name("description")).sendKeys("DESCRIPTION");
        browser.findElement(By.name("form")).submit();

        WebElement dl = browser.findElement(By.cssSelector("dt.bookHeadLine"));
        Assert.assertEquals("BOOK TITLE by BOOK AUTHOR (ISBN: 1234567890)",
                dl.getText());
        WebElement dt = browser.findElement(By.cssSelector("dd.bookDescription"));
        Assert.assertEquals("DESCRIPTION", dt.getText());
    }
}
