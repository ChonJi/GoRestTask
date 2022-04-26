package handlers;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONWrappedObject;
import netscape.javascript.JSObject;
import pojos.User;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class UserHandler {

    private static User user;

    public static User readFromJsonFile() {
        try  {
            ObjectMapper mapper = new ObjectMapper();
            user = mapper.readValue(new File("user.json"), User.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return user;
    }

    public static void main(String[] args) throws IOException {

        user = readFromJsonFile();
        System.out.println(user.getName());

    }
}
