package global;

import io.restassured.filter.Filter;
import io.restassured.filter.FilterContext;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;
import librarymanagement.apisystem.AuthorizeUser;
import static global.AllGlobalValue.token;


public class SessionsFilter implements Filter {
    AuthorizeUser authorizeUser = new AuthorizeUser();

    /**
     * * This method will validate the checking of Token is valid
     * @param requestSpec passes request spec to the method
     * @param responseSpec passes response spec to the method
     * @param ctx passes Filter Context  to the method
     * @return returns response
     */
        public Response filter(FilterableRequestSpecification requestSpec, FilterableResponseSpecification responseSpec, FilterContext ctx) {
            Response response;
            // Checking if Authorization is not null and token is Null
            if((requestSpec.getHeaders().get("Authorization") !=null)&&(requestSpec.getHeaders().get("Authorization").getValue()).equalsIgnoreCase("Bearer null")){
                authorizeUser.verifyUserAuthorizationWithToken();
                requestSpec.removeHeader("Authorization");
                requestSpec.header("Authorization", "Bearer " + token);
            }
            response = ctx.next(requestSpec, responseSpec);

            return response;
        }

    }

