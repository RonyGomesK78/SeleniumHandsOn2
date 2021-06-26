package org.example.testing;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

public class Booking {

    WebDriver driver;

    public void openBrowser() {

        WebDriverManager.chromedriver().setup();

        driver = new ChromeDriver();

        driver.manage().window().maximize();

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        Wait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(10))
                .pollingEvery(Duration.ofSeconds(2))
                .ignoring(NoSuchElementException.class);
        driver.get("https://www.google.com");

        driver.findElement(By.xpath("/html/body/div[1]/div[3]/form/div[1]/div[1]/div[1]/div/div[2]/input"))
                .sendKeys("https://www.flytap.com/pt-cv/" + Keys.ENTER);

        driver.findElement(By.xpath("//a[@href='https://www.flytap.com/pt-ar/']")).click();
        //*[@id="rso"]/div[1]/div/div/a
    }

    @Test
    public void checkTitle() {
        openBrowser();

        String expectedTitle = "FlyTAP – O site oficial da TAP | TAP Air Portugal | TAP Air Portugal";
        String actualTitle = driver.getTitle();

        Assert.assertEquals(actualTitle, expectedTitle);

        driver.close();
    }

    @Test
    public void book() {
        openBrowser();

        driver.findElement(By.id("flight-search-from"))
                .sendKeys("Lisboa" + Keys.ENTER);
        driver.findElement(By.id("flight-search-to"))
                .sendKeys("São Vicente" + Keys.ENTER);

        driver.findElement(By.xpath("//div[@class='passengers-input']/span[@class='passengers-select__cta']")).click();

        //scroll to see the entire dropdownList
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0, 300)");

        //add adult
        driver.findElement(By.xpath("//button[@class='counter-input__add'][@aria-label='Add Adultos']")).click();

        //add child
        driver.findElement(By.xpath("//button[@class='counter-input__add'][@aria-label='Add Crianças']")).click();

        //select date
        driver.findElement(By.xpath("//button[@aria-label='Prosseguir para a página seguinte para selecionar as datas']"))
                .click();


        Wait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(20))
                .pollingEvery(Duration.ofSeconds(2))
                .ignoring(NoSuchElementException.class);
        //get months <li>
        List<WebElement> months = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//li[@class='month-entry  ']")));
        months.get(3).click();

        //get days <div>
        List<WebElement> days = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[@class='graph__day-bar-filling']")));
        days.get(8).click();


    }

}
