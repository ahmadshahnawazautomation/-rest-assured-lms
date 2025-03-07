package stepDefinitions;

import global.AllGlobalValue;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import librarymanagement.apisystem.Transactions;
import org.testng.Assert;

import static io.restassured.RestAssured.given;


public class TransactionsStepDefinitions extends AllGlobalValue {
    Transactions transactions = new Transactions();

    @When("I borrow the book {string}")
    public void i_borrow_the_book(String bookTitle) {
    transactions.borrowBooksByPost();
    }

/*
    @Then("I should receive a 201 status code")
    public void i_should_receive_a_201_status_code() {
        // Assert that status code is 201 for successful borrow
        Assert.assertEquals(response.getStatusCode(), 201, "Status code is " + response.getStatusCode());
    }
*/

    @Then("the response should contain a success message {string}")
    public void the_response_should_contain_a_success_message(String message) {
        // Validate the success message
      transactions.verifyBorrowedSuccessfullyMessage(message);
    }

    @Then("the token should be updated")
    public void the_token_should_be_updated() {
        // Get and update the token from the response
        String newToken = response.body().jsonPath().getString("token");
        if (newToken != null) {
            token = newToken;
            System.out.println("Updated token: " + newToken);
        }
    }

    // Scenario: Return books successfully
    @When("I return the book {string}")
    public void i_return_the_book(String bookTitle) {
    transactions.returnBooksByPost(bookTitle);
    }

    // Scenario: Validate borrowing history
    @When("I request my borrowing history")
    public void i_request_my_borrowing_history() {
   transactions.validateBorrowingHistoryByPost();
    }

    @Then("the response should contain the borrowing history")
    public void the_response_should_contain_the_borrowing_history() {
    transactions.verifyBorrowingHistory();
    }

    @Then("the borrowing history should not be empty")
    public void the_borrowing_history_should_not_be_empty() {
        transactions.verifyBorrowingHistoryNotEmpty();
    }
}
