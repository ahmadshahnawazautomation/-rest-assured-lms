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

            // Checking if Authorization is not null and token is Null
            if((requestSpec.getHeaders().get("Authorization") !=null)&&(requestSpec.getHeaders().get("Authorization").getValue()).equalsIgnoreCase("Bearer null")){
                authorizeUser.authorizeUser();
                requestSpec.removeHeader("Authorization");
                requestSpec.header("Authorization", "Bearer " + token);
            }
                Response response = ctx.next(requestSpec, responseSpec);

            // Checking if response status code is 401 or 403
            if(response.getStatusCode()==401||response.getStatusCode()==403){
                authorizeUser.authorizeUser();
                requestSpec.removeHeader("Authorization");
                requestSpec.header("Authorization", "Bearer " + token);
                response = ctx.next(requestSpec, responseSpec);
            }
            return response;
        }

    }

