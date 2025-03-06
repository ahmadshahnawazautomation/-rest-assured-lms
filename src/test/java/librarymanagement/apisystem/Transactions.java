package librarymanagement.apisystem;

import global.AllGlobalValue;
import global.SessionsFilter;
import io.restassured.http.ContentType;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class Transactions extends AllGlobalValue {

    @Test
    public void borrowBooksByPost() {

        // Setting up the objects
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("username",getUsername());
        jsonObject.put("title","Little Blue Truck");

        //Getting response
        response = given().filter(new SessionsFilter()).contentType(ContentType.JSON).accept(ContentType.JSON)
                .header("Authorization", "Bearer " + token)
                .body(jsonObject.toString()).log().all()
                .when()
                .post(getBaseUrl() + "/transactions/borrow");

        // Printing Response body to the console
        String  responseBody  = response.getBody().asString();
        System.out.println("This is body "+responseBody);

        // getting token from Response Body
        token = response.body().jsonPath().getString("token");

        //checking request is 201
        Assert.assertEquals(response.getStatusCode(), 201, "Status code is "+response.getStatusCode());

        //Validating response contains expected fields
        String message = response.jsonPath().getString("message");
        Assert.assertNotNull(message, "Response message shouldn't be null");

        //Validating response message on successful request
        Assert.assertTrue(message.contains("borrowed successfully"), "success message Successful");

        // validating Token and printing
        String newToken = response.body().jsonPath().getString("token");
        if (newToken != null) {
            token = newToken;
            System.out.println(newToken);
        }
    }

    @Test
    public void returnBooksByPost() {
        // Setting up the objects
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("username",getUsername());
        jsonObject.put("title","Little Blue Truck");

        //Getting response
        response = given().filter(new SessionsFilter()).contentType(ContentType.JSON).accept(ContentType.JSON)
                .header("Authorization", "Bearer " + token)
                .body(jsonObject.toString()).log().all()
                .when()
                .post(getBaseUrl() + "/transactions/return");

        // Printing Response body to the console
        String  responseBody  = response.getBody().asString();
        System.out.println("This is body "+responseBody);

        // checking request is 200
        Assert.assertEquals(response.getStatusCode(), 200, "Status code is "+response.getStatusCode());

        //Validating response contains expected fields
        String message = response.jsonPath().getString("message");
        Assert.assertNotNull(message, "Response message shouldn't be null");

        //Validating response message on successful request
        Assert.assertTrue(message.contains("returned successfully"), "success message Successful");

        // getting token from Response Body
        token = response.body().jsonPath().getString("token");

        // validating Token and printing
        String newToken = response.body().jsonPath().getString("token");
        if (newToken != null) {
            token = newToken;
            System.out.println(newToken);
        }
    }

    @Test
    public void validateBorrowingHistoryByPost() {

        // Setting up the objects
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("username",getUsername());

        //Getting response
        response = given().filter(new SessionsFilter()).contentType(ContentType.JSON).accept(ContentType.JSON)
                .header("Authorization", "Bearer " + token)
                .body(jsonObject.toString()).log().all()
                .when()
                .post(getBaseUrl() + "/transactions/borrowing-history");

        // Printing Response body to the console
        String  responseBody  = response.getBody().asString();
        System.out.println("This is body "+responseBody);

        // getting token from Response Body
        token = response.body().jsonPath().getString("token");

        // Verify HTTP status code is 200
        Assert.assertEquals(response.getStatusCode(), 200, "Status code is "+response.getStatusCode());

        //Validating borrowing history
        String history = response.jsonPath().getString("borrowingHistory");
        Assert.assertNotNull(history, "Borrowing history should not be null");

        // Validating that at least one book is listed in history
        Assert.assertFalse(history.isEmpty(), "Borrowing history shouldn't be empty");

    }
}