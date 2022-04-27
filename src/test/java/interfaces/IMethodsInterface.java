package interfaces;

import io.restassured.path.json.JsonPath;
import pojos.User;

import static io.restassured.RestAssured.given;
import static tests.BaseTest.usersUrl;

public interface IMethodsInterface {

    /**
     * Builds post method with body as user and asserts response code
     * @param user
     * @param status
     * @return JsonPath
     */
    default JsonPath buildPostWithBodyAndStatusCodeAssertion(final User user, final int status) {
        return given()
                .body(user)
                .when()
                .post(usersUrl)
                .then()
                .statusCode(status)
                .extract()
                .response()
                .getBody()
                .jsonPath();
    }

    /**
     * Builds put method with body as user, extends base url with user id and asserts response code
     * @param user
     * @param status
     * @param userId
     * @return JsonPath
     */
    default JsonPath buildPutWithBodyAndStatusCodeAssertion(final User user, final int status, final int userId) {
        return given()
                .body(user)
                .when()
                .put(usersUrl + userId)
                .then()
                .statusCode(status)
                .extract()
                .response()
                .getBody()
                .jsonPath();
    }

    /**
     * Builds delete method extending base url with user id asserts response code
     * @param status
     * @param userId
     * @return JsonPath
     */
    default JsonPath buildDeleteAndStatusCodeAssertion(final int status, final int userId) {
        return given()
                .when()
                .delete(usersUrl + userId)
                .then()
                .statusCode(status)
                .extract()
                .response()
                .getBody()
                .jsonPath();
    }


}
