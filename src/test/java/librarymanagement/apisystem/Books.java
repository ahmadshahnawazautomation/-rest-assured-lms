package librarymanagement.apisystem;

import global.AllGlobalValue;
import global.SessionsFilter;
import io.restassured.http.ContentType;
import io.restassured.response.ResponseBody;
import org.testng.Assert;


import java.util.List;

import static io.restassured.RestAssured.given;

public class Books extends AllGlobalValue {



    public void getAllBooks() {
        response = given()
                .filter(new SessionsFilter())
                .header("authorization", "Bearer " + token)
                .accept(ContentType.JSON)
                .contentType("application/json")
                .and()
                .log().all()
                .when()
                .get(baseUrl + "/books/all");

        // Pretty print the response body
        response.body().prettyPrint();

        // Checking the response code is 200 (OK)
        Assert.assertEquals(response.getStatusCode(), 200, "Fetching all books");

        // Checking the response body is not empty
        Assert.assertTrue(response.body().asString().length() > 0, "Empty response Body");

        // Checking the book Availability
        try {
            // List of Available books via array
            List<Integer> availableCopiesList = response.jsonPath().getList("availableCopies", Integer.class);


            // Checking at lest one copy of books is available
            int totalAvailableCopies = availableCopiesList.stream().mapToInt(Integer::intValue).sum();

            // Asserting that copy of books is more than 0
            Assert.assertTrue(totalAvailableCopies > 0, "Total book availability should be greater than zero");

        } catch (Exception e) {
            // Exception handling
            Assert.fail("Error extracting or summing available copies: " + e.getMessage());
        }

        //Printing Token & Response Body
        System.out.println("This is Token- " + token);
        ResponseBody body = response.getBody();
        System.out.println("This is body " + body.asString());
    }


    public void getBooksWithTitle(String title) {
        title = "Little Blue Truck";
        response = given()
                .filter(new SessionsFilter())
                .header("authorization", "Bearer " + token)
                .accept(ContentType.JSON)
                .contentType("application/json")
                .and()
                .log().all()
                .when()
                .get(baseUrl + "/books/title/" + title);

        // Pretty print the response body
        response.body().prettyPrint();

        // Check if the response code is 200 (OK)
        Assert.assertEquals(response.getStatusCode(), 200, "Fetching book by title on successful request");

        // checking the response body contains the book with the provided title
        Assert.assertTrue(response.body().asString().contains(title), "Response contains " + title);

        //Printing Token & Response Body
        System.out.println("This is Token- " + token);
        ResponseBody body = response.getBody();
        System.out.println("This is body " + body.asString());
    }

    public void getBooksWithAuthor(String author) {
        author = "Alice Schertle";
        response = given()
                .filter(new SessionsFilter())
                .header("authorization", "Bearer " + token)
                .accept(ContentType.JSON)
                .contentType("application/json")
                .and()
                .log().all()
                .when()
                .get(baseUrl + "/books/author/" + author);

        // Pretty print the response body
        response.body().prettyPrint();

        // Checking the response code is 200 (OK)
        Assert.assertEquals(response.getStatusCode(), 200, "Expected status code 200 for fetching books by author");

        // Checking the response body contains the book by author
        Assert.assertTrue(response.body().asString().contains(author), "Response body should contain the author " + author);

        //Printing Token & Response Body
        System.out.println("This is Token- " + token);
        ResponseBody body = response.getBody();
        System.out.println("This is body " + body.asString());
    }
}
