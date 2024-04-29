package task3.pojo;

import java.util.ArrayList;

public class TestsJson {
    public ArrayList<JsonDTO> tests = new ArrayList<>();

    public TestsJson() {
    }
    public TestsJson(ArrayList<JsonDTO> tests) {
        this.tests = tests;
    }

    public ArrayList<JsonDTO> getTests() {
        return tests;
    }

    public void setTests(ArrayList<JsonDTO> tests) {
        this.tests = tests;
    }
}
