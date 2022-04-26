package tests;

import handlers.ReadProperties;
import handlers.UserHandler;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;

import static io.restassured.RestAssured.requestSpecification;

public class BaseTest {

    /**
     * Basic hook for JUnit5 @BeforeAll runs every test class
     * catch during debugging that all tests after HealthCheck.class were failing on 400
     * as double headers were added to the requests - it's ugly I know and sorry about that, but don't want to waste the time
     */
    private static Boolean initialized = false;
    protected String usersUrl = "users";
    protected UserHandler userHandler = new UserHandler();

    /**
     * setup base request for the rest of the test cases
     * sets requestSpecification with baseUri, content-type, and pass bearer token to header
     */
    @BeforeAll
    public static void setUp() {
        if (!initialized) {
            initialized = true;
            ReadProperties properties = new ReadProperties();
            requestSpecification = new RequestSpecBuilder()
                    .setBaseUri(properties.getBaseUri())
                    .setContentType(ContentType.JSON)
                    .build()
                    .header("Authorization", properties.getBearer());
        }
    }
}
