package tests;

import org.apache.http.HttpStatus;
import org.junit.jupiter.api.*;
import pojos.User;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserTest extends BaseTest {

    private int minimumUsersExpected = 10;

    @Test
    @Order(1)
    public void shouldGetAllExistingUsers() {
        var response = when().get(usersUrl);
        assertEquals(response.getStatusCode(), HttpStatus.SC_OK);
        User[] users = response.as(User[].class);
        assertTrue(users.length > minimumUsersExpected);
        for (User user : users) {
            assertNotNull(user.getId());
            assertNotNull(user.getName());
            assertNotNull(user.getEmail());
            assertNotNull(user.getGender());
            assertNotNull(user.getStatus());
        }
    }

    @Test
    @Order(2)
    public void shouldRespondWithUnprocessableEntityOnNoUserName() {
        User testUser = userHandler.getUserByScenario("no name user");
        System.out.println(userHandler.toJson(testUser));
        var body = given()
                .body(userHandler.toJson(testUser))
                .when()
                .post(usersUrl)
                .then()
                .statusCode(HttpStatus.SC_UNPROCESSABLE_ENTITY)
                .extract()
                .response()
                .getBody()
                .jsonPath();
        assertEquals(body.get("[0].field"), "name");
        assertEquals(body.get("[0].message"), "can't be blank");
    }

}
