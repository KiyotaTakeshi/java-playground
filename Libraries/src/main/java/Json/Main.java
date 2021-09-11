package Json;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {

        // 読み取り
        File readFile = new File("src/main/java/employee.json");
        // System.out.println(readFile.exists());

        ObjectMapper mapper = new ObjectMapper();
        Employee employee = mapper.readValue(readFile, Employee.class);
        System.out.println(employee);

        // 書き込み
        File writeFile = new File("src/main/java/write_employee.json");
        List<Employee> employeeList = new ArrayList<>(
                Arrays.asList(
                        new Employee("yamamda", 22, Arrays.asList("AWS", "CKAD")),
                        new Employee("tanaka", 20, Arrays.asList("CCNP"))
                )
        );
        Employee test3 = new Employee("test3", 41);
        test3.setLicenses(Arrays.asList("Linac", "AWS"));
        employeeList.add(test3);
        mapper.writeValue(writeFile, employeeList);
    }
}
