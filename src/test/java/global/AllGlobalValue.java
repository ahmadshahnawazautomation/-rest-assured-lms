package global;

import io.restassured.response.Response;

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
}