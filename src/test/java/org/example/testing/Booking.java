package org.example.testing;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

public class Booking {

    WebDriver driver;

    @BeforeTest
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

        driver.findElement(By.xpath("//a[@href='https://www.flytap.com/pt-mz/']")).click();
        //*[@id="rso"]/div[1]/div/div/a
    }

    @Test
    public void checkTitle() {
        //openBrowser();

        String expectedTitle = "FlyTAP – O site oficial da TAP | TAP Air Portugal | TAP Air Portugal";
        String actualTitle = driver.getTitle();

        Assert.assertEquals(actualTitle, expectedTitle);

        driver.close();
    }

    @Test
    public void book() throws InterruptedException {
       //openBrowser();

        SoftAssert softAssert = new SoftAssert();
        WebElement h1Element = driver.findElement(By.xpath("//h1"));
        String h1Expected = "Reservar um voo";
        softAssert.assertEquals(h1Element, h1Expected);

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
        Thread.sleep(2000);
        //add child
        driver.findElement(By.xpath("//button[@class='counter-input__add'][@aria-label='Add Crianças']")).click();
        Thread.sleep(2000);
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
        List<WebElement> days = driver.findElements(By.xpath("(//div[@class='graph__month-bars '])[1]/div"));
        days.get(8).click();

        Thread.sleep(10000);
        try{
            WebElement arrivalMonth = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//ul[@class='month-list'])[2]/li[@class='month-entry  '][6]")));
            arrivalMonth.click();
        }catch (StaleElementReferenceException e){
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//ul[@class='month-list'])[2]/li[@class='month-entry  '][6]"))).click();
        }
        List<WebElement> arrivalDays = driver.findElements(By.xpath("(//div[@class='graph__month-bars '])[2]/div"));
        arrivalDays.get(9).click();

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//button[@class='flight__cabin ng-star-inserted'])[1]"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//button[@class='button button-accent button--icon button--xtrasmall'])[3]"))).click();

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//button[@class='flight__cabin ng-star-inserted'])[1]"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//button[@class='button button-accent button--icon button--xtrasmall'])[3]"))).click();

        js.executeScript("window.scrollBy(0, 1000)");

        driver.findElement(By.xpath("//button[@class='button button-accent button--big button--fat button--mobile-full']")).click();
    }

}
