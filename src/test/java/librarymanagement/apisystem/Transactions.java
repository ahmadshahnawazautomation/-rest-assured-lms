package librarymanagement.apisystem;

import global.AllGlobalValue;
import global.SessionsFilter;
import io.restassured.http.ContentType;
import io.restassured.response.ResponseBody;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class Transactions extends AllGlobalValue {

    @Test
    /*
     * This method will is for Borrowing the books by POST
     */
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

    }

    /**
     * This method will is for verify Borrowed Successful Message
     * @param message passes message to verify
     */
    public void verifyBorrowedSuccessfullyMessage(String message){

        String responseMessage = response.jsonPath().getString("message");
        Assert.assertNotNull(responseMessage, "Response message shouldn't be null");
        Assert.assertTrue(responseMessage.contains(message), "Success message should contain " + message);
    }

    @Test
    /*
     * This method will is for Return the books by POST
     * @param book passes string to method
     */
    public void returnBooksByPost(String book) {
        // Setting up the objects
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("username",getUsername());
        jsonObject.put("title",book);

            //Getting response
        response = given().filter(new SessionsFilter()).contentType(ContentType.JSON).accept(ContentType.JSON)
                .header("Authorization", "Bearer " + token)
                .body(jsonObject.toString()).log().all()
                .when()
                .post(getBaseUrl() + "/transactions/return");

        // Printing Response body to the console
        String  responseBody  = response.getBody().asString();
        System.out.println("This is body "+responseBody);
    }

    @Test
    /*
     * This method will is for verify Borrowing History By Post
     */
    public void validateBorrowingHistoryByPost() {

        // Setting up the objects
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("username", getUsername());

        //Getting response
        response = given().filter(new SessionsFilter()).contentType(ContentType.JSON).accept(ContentType.JSON)
                .header("Authorization", "Bearer " + token)
                .body(jsonObject.toString()).log().all()
                .when()
                .post(getBaseUrl() + "/transactions/borrowing-history");

        // Printing Response body to the console
        String responseBody = response.getBody().asString();
        System.out.println("This is body " + responseBody);
    }
    /**
     * This method will is for verify Borrowing History
     */
    public void verifyBorrowingHistory(){

        // Validate borrowing history is returned in response
        String history = response.jsonPath().getString("borrowingHistory");
        Assert.assertNotNull(history, "Borrowing history should not be null");
    }
    /**
     * This method will is for verify Borrowing History is not empty
     */
    public void verifyBorrowingHistoryNotEmpty(){
        // Validate the history is not empty
        String history = response.jsonPath().getString("borrowingHistory");
        Assert.assertFalse(history.isEmpty(), "Borrowing history shouldn't be empty");
    }

}
