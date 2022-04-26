package tests;

import handlers.ReadProperties;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class BaseTest {

    private String baseUrl;
    private String bearerToken;

    @BeforeAll
    public void setUp() {
        ReadProperties properties = new ReadProperties();
        System.out.println(properties.getBaseUri());
        System.out.println(properties.getBearer());
    }
}
