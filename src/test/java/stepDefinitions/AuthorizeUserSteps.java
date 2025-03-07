package stepDefinitions;

import global.AllGlobalValue;
import global.SessionsFilter;
import io.restassured.response.Response;
import io.restassured.http.ContentType;
import io.restassured.RestAssured;
import librarymanagement.apisystem.AuthorizeUser;
import org.json.JSONObject;
import org.testng.Assert;
import io.cucumber.java.en.*;

import static io.restassured.RestAssured.given;

public class AuthorizeUserSteps extends AllGlobalValue {
    AuthorizeUser authorizeUser = new AuthorizeUser();

    private JSONObject jsonObject = new JSONObject();

    @Given("I have valid user credentials")
    public void i_have_valid_user_credentials() {
        authorizeUser.validateValidUserCredentials(jsonObject,getUsername(),getPassword());
    }

    @Given("I have invalid user credentials")
    public void i_have_invalid_user_credentials() {
        authorizeUser.validateValidUserCredentials(jsonObject,getInvalidUsername(),getInvalidPassword());
    }



    @Then("I should receive a valid authentication token")
    public void i_should_receive_a_valid_authentication_token() {
      authorizeUser.validateTokenIsGenerated();
    }

    @Then("The user should be authorized successfully")
    public void the_user_should_be_authorized_successfully() {
        authorizeUser.validateThatUserIsAuthorized();
    }

    @Then("I should receive an error message {string}")
    public void i_should_receive_an_error_message(String expectedMessage) {
        Assert.assertEquals(response.getStatusCode(), 401, "Expected 401 Unauthorized response");
        String  responseBody  = response.getBody().asString();
        Assert.assertTrue(responseBody.contains("Invalid credentials"),expectedMessage);
    }

    @When("I send a POST request to login API {string}")
    public void iSendAPOSTRequestToLoginAPI(String credentials) {
        authorizeUser.sendApiByPost(credentials);
    }



    }

