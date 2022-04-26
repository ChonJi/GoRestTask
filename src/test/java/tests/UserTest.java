package tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UserTest extends BaseTest {

    @Test
    public void shouldPassOnConfigCheck() {
        Assertions.assertTrue(true);
    }

}
