package handlers;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ReadProperties {

    private Properties properties;

    /**
     * Read properties from config.properties and allows for changing parameters using terminal
     */
    public ReadProperties() {
        properties = new Properties();
        try (final InputStream inputStream = ReadProperties.class.getClassLoader().getResourceAsStream("config.properties")){
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Gets base uri of the project
     * @return String baseUri
     */
    public String getBaseUri() {
        return properties.getProperty("baseUri");
    }

    /**
     * Gets bearer token
     * @return String bearerToken
     */
    public String getBearer() {
        return properties.getProperty("bearer");
    }
}
