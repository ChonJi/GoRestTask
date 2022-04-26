package handlers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import pojos.Scenarios;
import pojos.User;

import java.io.File;
import java.io.IOException;

public class UserHandler {

    private ObjectMapper mapper = new ObjectMapper();
    private Scenarios scenarios;

    private Scenarios readFromJsonFile() {
        try {
            scenarios = mapper.readValue(new File("scenarios.json"), Scenarios.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return scenarios;
    }

    public User getUserByScenario(final String scenario) throws Error {
        scenarios = readFromJsonFile();
        return scenarios.getScenarios().stream().filter(s -> s.getScenario().equals(scenario)).findFirst().get().getUser();
    }

    public String toJson(final User user)  {
        var jsonString = "";
        try {
            jsonString = mapper.writeValueAsString(user);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return jsonString;
    }
}
