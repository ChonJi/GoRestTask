package tests;

import interfaces.IMethodsInterface;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.*;
import pojos.User;

import java.util.ArrayList;
import java.util.Arrays;

import static io.restassured.RestAssured.when;
import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserTest extends BaseTest implements IMethodsInterface {

    @Test
    @Order(1)
    public void shouldResponseWithOkOnGetAllExistingUsers() {
        var response = when().get(usersUrl);
        assertEquals(response.getStatusCode(), HttpStatus.SC_OK);
        User[] users = response.as(User[].class);
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
    public void shouldRespondWithUnprocessableEntityOnNoBody() {
        User testUser = userHandler.getUserByScenario("empty body");
        var responseJson = buildPostWithBodyAndStatusCodeAssertion(testUser, HttpStatus.SC_UNPROCESSABLE_ENTITY);
        assertEquals(testUser.getExpectedResponse(), responseJson.get("$").toString());
    }

    @Test
    @Order(3)
    public void shouldRespondWithUnprocessableEntityOnNoUserName() {
        User testUser = userHandler.getUserByScenario("no name user");
        var responseJson = buildPostWithBodyAndStatusCodeAssertion(testUser, HttpStatus.SC_UNPROCESSABLE_ENTITY);
        assertEquals(testUser.getExpectedResponse(), responseJson.get("$").toString());
    }

    @Test
    @Order(4)
    public void shouldRespondWithUnprocessableEntityOnNoStatus() {
        User testUser = userHandler.getUserByScenario("no status");
        var responseJson = buildPostWithBodyAndStatusCodeAssertion(testUser, HttpStatus.SC_UNPROCESSABLE_ENTITY);
        assertEquals(testUser.getExpectedResponse(), responseJson.get("$").toString());
    }

    @Test
    @Order(5)
    public void shouldRespondWithUnprocessableEntityOnBrokenEmail() {
        User testUser = userHandler.getUserByScenario("validate email");
        var responseJson = buildPostWithBodyAndStatusCodeAssertion(testUser, HttpStatus.SC_UNPROCESSABLE_ENTITY);
        assertEquals(testUser.getExpectedResponse(), responseJson.get("$").toString());
    }

    @Test
    @Order(6)
    public void shouldRespondWithUnprocessableEntityOnBrokenStatus() {
        User testUser = userHandler.getUserByScenario("validate status");
        var responseJson = buildPostWithBodyAndStatusCodeAssertion(testUser, HttpStatus.SC_UNPROCESSABLE_ENTITY);
        assertEquals(testUser.getExpectedResponse(), responseJson.get("$").toString());
    }

    @Test
    @Order(7)
    public void shouldRespondWithCreatedOnValidUser() {
        User testUser = userHandler.getUserByScenario("valid user");
        var responseJson = buildPostWithBodyAndStatusCodeAssertion(testUser, HttpStatus.SC_CREATED);
        //Capture id number for future actions
        capturedId = responseJson.get("id");
        assertTrue(responseJson.get("$").toString().contains(testUser.getExpectedResponse()));
    }

    @Test
    @Order(8)
    public void shouldGetAllExistingUsersAndFoundCreatedUser() {
        User testUser = userHandler.getUserByScenario("valid user");
        var response = when().get(usersUrl);
        assertEquals(response.getStatusCode(), HttpStatus.SC_OK);
        var users = new ArrayList<>(Arrays.asList(response.as(User[].class)));
        assertTrue(users
                .stream()
                .anyMatch(u -> u.getId().equals(capturedId)
                        && u.getName().equals(testUser.getName())
                        && u.getGender().equals(testUser.getGender())
                        && u.getEmail().equals(testUser.getEmail())
                        && u.getStatus().equals(testUser.getStatus())));
    }

    @Test
    @Order(9)
    public void shouldRespondUnprocessableEntityOnDuplicatedUser() {
        User testUser = userHandler.getUserByScenario("duplicated user");
        var responseJson = buildPostWithBodyAndStatusCodeAssertion(testUser, HttpStatus.SC_UNPROCESSABLE_ENTITY);
        assertEquals(testUser.getExpectedResponse(), responseJson.get("$").toString());
    }

    @Test
    @Order(10)
    public void shouldRespondWithNotFoundUpdatingNotExistingUser() {
        User testUser = userHandler.getUserByScenario("not existing user");
        var responseJson = buildPutWithBodyAndStatusCodeAssertion(testUser, HttpStatus.SC_NOT_FOUND, testUser.getId());
        assertEquals(testUser.getExpectedResponse(), responseJson.get("$").toString());
    }

    @Test
    @Order(11)
    public void shouldRespondWithOkUpdatingExistingUser() {
        User testUser = userHandler.getUserByScenario("update user");
        var responseJson = buildPutWithBodyAndStatusCodeAssertion(testUser, HttpStatus.SC_OK, capturedId);
        assertEquals(testUser.getExpectedResponse() + String.format("%s}", capturedId), responseJson.get("$").toString());
    }

    @Test
    @Order(12)
    public void shouldRespondNoContentOnDeletingExistingUser() {
        buildDeleteAndStatusCodeAssertion(HttpStatus.SC_NO_CONTENT, capturedId);
    }

    @Test
    @Order(13)
    public void shouldGetAllExistingUsersAndNotFoundUserAfterDeletion() {
        User testUser = userHandler.getUserByScenario("update user");
        var response = when().get(usersUrl);
        assertEquals(response.getStatusCode(), HttpStatus.SC_OK);
        var users = new ArrayList<>(Arrays.asList(response.as(User[].class)));
        assertFalse(users
                .stream()
                .anyMatch(u -> u.getId().equals(capturedId)
                        && u.getName().equals(testUser.getName())
                        && u.getGender().equals(testUser.getGender())
                        && u.getEmail().equals(testUser.getEmail())
                        && u.getStatus().equals(testUser.getStatus())));
    }

}
