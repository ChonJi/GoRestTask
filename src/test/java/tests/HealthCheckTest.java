package tests;

import org.apache.http.HttpStatus;
import org.junit.jupiter.api.*;

import static io.restassured.RestAssured.when;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class HealthCheckTest extends BaseTest {

    @Test
    @Order(0)
    public void healthCheck() {
        when()
                .get(usersUrl)
                .then()
                .statusCode(HttpStatus.SC_OK);

    }

}
