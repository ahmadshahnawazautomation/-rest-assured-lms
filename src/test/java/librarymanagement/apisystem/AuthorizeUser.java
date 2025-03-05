package librarymanagement.apisystem;

import global.AllGlobalValue;
import global.SessionsFilter;
import io.restassured.http.ContentType;
import io.restassured.response.ResponseBody;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;


import static io.restassured.RestAssured.given;

public class AuthorizeUser extends AllGlobalValue {

    /**
     * This method will login and generate token and Authorizing the user
     */
    @Test (priority = 1)
    public void verifyUserAuthorizationWithToken() {
        // Setting up the objects
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("username",getUsername());
        jsonObject.put("password",getPassword());

        //Getting response
        response = given().filter(new SessionsFilter()).contentType(ContentType.JSON).accept(ContentType.JSON)
                .body(jsonObject.toString()).log().all()
                .when()
                    .post(getBaseUrl() + "/member/login");

        // Printing Response body to the console
        String  responseBody  = response.getBody().asString();
        System.out.println("This is body "+responseBody);

        // getting token from Response Body
        token = response.body().jsonPath().getString("token");

        // Asserting the Generated Token
        Assert.assertNotNull(token, token+" :Token is not null");
        Assert.assertFalse(token.isEmpty(), token + " :Token shouldn't be empty");
    }

    /**
     * This method will verify the user is Authorized
     */
    @Test (priority = 2)
    public void validateThatUserIsAuthorized() {

        //assert status code
        response.then().log().all().assertThat().statusCode(200);

        // Holding Response to the string
        String message = response.jsonPath().getString("message");

        // Asserting message
        Assert.assertNotNull(message, "Response " +message+ " shouldn't be null");
        Assert.assertTrue(message.contains("Success") || message.contains("Authorized"),
                "Authorization is successful");

        // If the response contains a userId or similar key, validate it's not null
        String userId = response.jsonPath().getString("userId");
        Assert.assertNotNull(userId, userId +" shouldn't be null After Authorization ");
        }

    /**
     * This method is used to validate the Token is generated successfully
     */
    @Test (priority = 3)
    public void validateTokenIsGenerated(){

        //get the token and assign it to a global variable
        token = response.path("token");

        // Printing Response body to the console
        String  responseBody  = response.getBody().asString();
        System.out.println("This is body "+responseBody);

        //Assert that response body contains token and its value is generated
        Assert.assertEquals("token", "token");
        Assert.assertNotNull(token);
    }

    }

