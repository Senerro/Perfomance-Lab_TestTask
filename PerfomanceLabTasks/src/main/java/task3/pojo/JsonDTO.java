package task3.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import java.util.AbstractList;
import java.util.Objects;

public class JsonDTO {
    private int id;
    @JsonInclude(Include.NON_NULL)
    private String title;
    @JsonInclude(Include.NON_NULL)
    private String value;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private AbstractList<JsonDTO> values;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public AbstractList<JsonDTO> getValues() {
        return values;
    }

    public void setValues(AbstractList<JsonDTO> values) {
        this.values = values;
    }

    public void merge(JsonDTO obj) {
        this.value = obj.value;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        JsonDTO testPOJO = (JsonDTO) object;
        return id == testPOJO.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
