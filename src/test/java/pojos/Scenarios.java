package pojos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;
import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Scenarios {

    private List<Scenario> scenarios;

    public Scenarios() {

    }

    public Scenarios(List<Scenario> scenarios) {
        this.scenarios = scenarios;
    }

    public List<Scenario> getScenarios() {
        return scenarios;
    }

    public void setScenarios(List<Scenario> scenarios) {
        this.scenarios = scenarios;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Scenarios scenarios1 = (Scenarios) o;
        return Objects.equals(scenarios, scenarios1.scenarios);
    }

    @Override
    public int hashCode() {
        return Objects.hash(scenarios);
    }

    @Override
    public String toString() {
        return "Users{" +
                "users=" + scenarios +
                '}';
    }

}
