package org.example.testing.steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.testng.asserts.SoftAssert;

import java.time.Duration;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

public class ChooseDestination {

    WebDriver driver;

    @Given("^user is on homepage$")
    public void user_is_on_homepage () {

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

    @When("^user choose destination departure$")
    public void user_choose_destination_departure () {

        SoftAssert softAssert = new SoftAssert();
        WebElement h1Element = driver.findElement(By.xpath("//h1"));
        String h1Expected = "Reservar um voo";
        softAssert.assertEquals(h1Element, h1Expected);

        driver.findElement(By.id("flight-search-from"))
                .sendKeys("Lisboa" + Keys.ENTER);
    }

    @And("^user choose destination arrival$")
    public void user_choose_destination_arrival () {

        driver.findElement(By.id("flight-search-to"))
                .sendKeys("São Vicente" + Keys.ENTER);
    }

    @Then("^a dropdown list appears$")
    public void is_displayed_a_drop_down_list () {
        System.out.println("then");
    }

}
