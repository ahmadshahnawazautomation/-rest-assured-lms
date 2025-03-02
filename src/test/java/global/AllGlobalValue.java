package global;

import io.restassured.response.Response;
import java.util.List;

public class AllGlobalValue {

    /**
     * All global params for the test
     */
    public static Response response;
    public static String username = "testuser";
    public static String password="Secpass";
    public static String token;
    public static String baseUrl = "https://librarymanagementapisystem.onrender.com";

}
