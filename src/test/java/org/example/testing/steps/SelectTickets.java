package org.example.testing.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class SelectTickets {


    WebDriver driver = new ChromeDriver();

    @Given("the user has already chosen a departure and arrival destination")
    public void the_user_has_already_chosen_a_departure_and_arrival_destination() {

        ChooseDestination chooseDestination = new ChooseDestination();
        chooseDestination.user_choose_destination_departure();
        chooseDestination.user_choose_destination_arrival();
    }

    @When("user click on passengers dropdown list")
    public void user_click_on_passengers_dropdown_list() throws InterruptedException {

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
    }

    @When("click on the plus button's adults")
    public void click_on_the_plus_button_s_adults() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @When("click on the plus button's children")
    public void click_on_the_plus_button_s_children() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @When("click on the Select dates button")
    public void click_on_the_select_dates_button() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Then("go to select dates page")
    public void go_to_select_dates_page() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

}
