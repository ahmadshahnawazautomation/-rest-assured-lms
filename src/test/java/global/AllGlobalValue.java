package global;

import io.restassured.response.Response;
import java.util.List;

public class AllGlobalValue {

    /**
     * All global params for the test
     */
    public static Response response;
    public static String token;



    //Getters for username
    public String getUsername() {
        return "testuser";
    }
    //Getters for password
    public String getPassword() {
        return "Secpass";
    }

    //Getters for baseUrl
    public String getBaseUrl() {
        return "https://librarymanagementapisystem.onrender.com";

        }

    //Getters for username
    public String getInvalidUsername() {
        return "testuser1";
    }
    //Getters for password
    public String getInvalidPassword() {
        return "Secpass1";
}
}
