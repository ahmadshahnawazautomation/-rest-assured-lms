package librarymanagement.apisystem;

import global.AllGlobalValue;
import global.SessionsFilter;
import io.restassured.http.ContentType;
import io.restassured.response.ResponseBody;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class Transactions extends AllGlobalValue {

    @Test
    public void borrowBooksByPost() {

        // Setting up the objects
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("username",username);
        jsonObject.put("title","Little Blue Truck");

        //Getting response
        response = given().filter(new SessionsFilter()).contentType(ContentType.JSON).accept(ContentType.JSON)
                .header("Authorization", "Bearer " + token)
                .body(jsonObject).log().all()
                .when()
                .post(baseUrl + "/transactions/borrow");

        // Printing Response body to the console
        ResponseBody body = response.getBody();
        System.out.println("This is body "+body.asString());

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
    public void returnBooksByPost() {
        // Setting up the objects
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("username",username);
        jsonObject.put("title","Little Blue Truck");

            //Getting response
        response = given().filter(new SessionsFilter()).contentType(ContentType.JSON).accept(ContentType.JSON)
                .header("Authorization", "Bearer " + token)
                .body(jsonObject).log().all()
                .when()
                .post(baseUrl + "/transactions/return");

        // Printing Response body to the console
        ResponseBody body = response.getBody();
        System.out.println("This is body "+body.asString());

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
        jsonObject.put("username",username);

        //Getting response
        response = given().filter(new SessionsFilter()).contentType(ContentType.JSON).accept(ContentType.JSON)
                .header("Authorization", "Bearer " + token)
                .body(jsonObject).log().all()
                .when()
                .post(baseUrl + "/transactions/borrowing-history");
        ResponseBody body = response.getBody();
        System.out.println("This is body "+body.asString());

        // getting token from Response Body
        token = response.body().jsonPath().getString("token");

        // validating Token and printing
        String newToken = response.body().jsonPath().getString("token");
        if (newToken != null) {
            token = newToken;
            System.out.println(newToken);
        }

    }
}
