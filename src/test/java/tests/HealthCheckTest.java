package tests;

import org.apache.http.HttpStatus;
import org.junit.jupiter.api.*;
import java.util.logging.Logger;
import static io.restassured.RestAssured.when;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class HealthCheckTest extends BaseTest {

     private static final Logger LOGGER = Logger.getLogger(HealthCheckTest.class.getName());

    @Test
    @Order(0)
    public void healthCheck() throws Error {
        try {
            when()
                    .get(usersUrl)
                    .then()
                    .statusCode(HttpStatus.SC_OK);
        } catch (AssertionError e) {
            LOGGER.severe("HEALTH HECK FAILED!!! : test execution has been stopped!!!");
            System.exit(-1);
        }
    }

}
