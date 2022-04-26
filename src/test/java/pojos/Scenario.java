package pojos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Scenario {

    private String scenario;
    private User user;

    public Scenario() {

    }

    public Scenario(String scenario, User user) {
        this.scenario = scenario;
        this.user = user;
    }

    public String getScenario() {
        return scenario;
    }

    public void setScenario(String scenario) {
        this.scenario = scenario;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Scenario scenario1 = (Scenario) o;
        return Objects.equals(scenario, scenario1.scenario) && Objects.equals(user, scenario1.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(scenario, user);
    }

    @Override
    public String toString() {
        return "Scenario{" +
                "scenario='" + scenario + '\'' +
                ", user=" + user +
                '}';
    }
}
