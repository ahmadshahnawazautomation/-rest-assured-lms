package stepDefinitions;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Given;
import librarymanagement.apisystem.AuthorizeUser;
import librarymanagement.apisystem.Books;
import org.testng.Assert;

import java.util.List;

public class BooksStepDefinitions extends Books {


    @When("I request to get all books")
    public void iRequestToGetAllBooks() {
        getAllBooks();
    }

    @Then("I should receive a {int} status code")
    public void iShouldReceiveAStatusCode(int statusCode) {
        Assert.assertEquals(response.getStatusCode(), statusCode, "Expected status code");
    }

    @Then("the response body should not be empty")
    public void theResponseBodyShouldNotBeEmpty() {
        Assert.assertTrue(response.body().asString().length() > 0, "Response body should not be empty");
    }

    @Then("the total available copies should be greater than zero")
    public void theTotalAvailableCopiesShouldBeGreaterThanZero() {
        checkBooksAvailabilityGreaterThanZero();
    }

    @When("I request to get books by title {string}")
    public void iRequestToGetBooksByTitle(String title) {
        getBooksWithTitle(title);
    }

    @Then("the response body should contain {string}")
    public void theResponseBodyShouldContain(String text) {
        Assert.assertTrue(response.body().asString().contains(text), "Response body should contain " + text);
    }

    @When("I request to get books by author {string}")
    public void iRequestToGetBooksByAuthor(String author) {
        getBooksWithAuthor(author);
    }
}
