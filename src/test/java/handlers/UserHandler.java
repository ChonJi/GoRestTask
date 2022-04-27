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

    /**
     * Reads from scenarios.json file
     * @return Scenarios pojo
     */
    private Scenarios readFromJsonFile() {
        try {
            scenarios = mapper.readValue(new File("scenarios.json"), Scenarios.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return scenarios;
    }

    /**
     * Returns User pojo matching given scenario
     * @param scenario
     * @return User pojo
     * @throws Error on no given scenario
     */
    public User getUserByScenario(final String scenario) throws Error {
        scenarios = readFromJsonFile();
        return scenarios.getScenarios().stream().filter(s -> s.getScenario().equals(scenario)).findFirst().get().getUser();
    }

    /**
     * Reads user and return as string json
     * @param user
     * @return String jsonString
     */
    @Deprecated
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
