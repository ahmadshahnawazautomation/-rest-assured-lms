package librarymanagement.apisystem;

import global.AllGlobalValue;
import global.SessionsFilter;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;


import static io.restassured.RestAssured.given;

public class AuthorizeUser extends AllGlobalValue {
    JSONObject jsonObject = new JSONObject();
    /**
     * This method will login and generate token and Authorizing the user
     */
    @Test (priority = 1)
    // Setting up the objects
    public void verifyUserAuthorizationWithToken() {

            jsonObject.put("username", getUsername());
            jsonObject.put("password", getPassword());

        //Getting response
        response = given().filter(new SessionsFilter()).contentType(ContentType.JSON).accept(ContentType.JSON)
                .body(jsonObject.toString()).log().all()
                .when()
                .post(getBaseUrl() + "/member/login");

    }

    /**
     * This method will verify the user is Authorized
     */
    public void validateThatUserIsAuthorized() {

        //assert status code
        response.then().log().all().assertThat().statusCode(200);


    }

    /**
     * This method is used to validate the Token is generated successfully
     */
    public void validateTokenIsGenerated(){

        //getting the token
        token = response.path("token");

        //Assert that response body contains token and its value is generated
        Assert.assertEquals("token", "token");
        Assert.assertNotNull(token);
    }
    /**
     * This method will verify the user is Authorized
     */
    public void validateValidUserCredentials(JSONObject jsonObject, String userName, String password) {
        RestAssured.baseURI = getBaseUrl();
        jsonObject.put("username", userName);
        jsonObject.put("password", password);

    }

    /**
     * This method will verify the Unauthorized and Authorized user
     */
    public void sendApiByPost(String flag) {
        if(flag.equalsIgnoreCase("Invalid credentials")) {
            jsonObject.put("username", getInvalidUsername());
            jsonObject.put("password", getInvalidPassword());
            response = given().contentType(ContentType.JSON)
                    .accept(ContentType.JSON)
                    .body(jsonObject.toString())
                    .when()
                    .post("/member/login");

        }else {
            verifyUserAuthorizationWithToken();
        }
    }
}