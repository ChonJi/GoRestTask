package handlers;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ReadProperties {

    private Properties properties;

    public ReadProperties() {
        properties = new Properties();
        try (final InputStream inputStream = ReadProperties.class.getClassLoader().getResourceAsStream("config.properties")){
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getBaseUri() {
        return properties.getProperty("baseUri");
    }

    public String getBearer() {
        return properties.getProperty("bearer");
    }
}
