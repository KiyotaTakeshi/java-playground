package Json;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

// json にクラスへと mapping しない property がある場合
@JsonIgnoreProperties(ignoreUnknown = true)
public class Employee {

    // getter,setter や他の jackson の annotation がない場合はつける
    // @JsonProperty
    private String name;

    private Integer age;

    private List<String> licenses;

    public Employee() {
    }

    public Employee(String name, Integer age, List<String> licenses) {
        this.name = name;
        this.age = age;
        this.licenses = licenses;
    }

    public Employee(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    // com.fasterxml.jackson.annotation が
    // import されていない場合(annotation をつけていない場合)は、
    // getter or setter が必要
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public List<String> getLicenses() {
        return licenses;
    }

    public void setLicenses(List<String> licenses) {
        this.licenses = licenses;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", licenses=" + licenses +
                '}';
    }
}
