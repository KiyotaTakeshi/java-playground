package CSV;

import org.supercsv.cellprocessor.FmtDate;
import org.supercsv.cellprocessor.Optional;
import org.supercsv.cellprocessor.ParseDate;
import org.supercsv.cellprocessor.ParseInt;
import org.supercsv.cellprocessor.constraint.NotNull;
import org.supercsv.cellprocessor.constraint.StrRegEx;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.io.CsvBeanReader;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanReader;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;

public class Main {

    public static void main(String[] args) throws ParseException {

        CellProcessor[] readProcessors = new CellProcessor[]{
                new NotNull(), // name
                new ParseInt(new NotNull()), // age
                new ParseDate("yyyy/MM/dd"), // birth
                new StrRegEx("[a-z0-9\\._]+@[a-z0-9\\.]+"), // email
                new Optional() // note
        };

        // 読み込み
        Path path = Paths.get("src/main/java/employee.csv");
        try (ICsvBeanReader beanReader = new CsvBeanReader(Files.newBufferedReader(path),
                CsvPreference.STANDARD_PREFERENCE)) {
            String[] header = beanReader.getHeader(true);
            Employee employee;
            while ((employee = beanReader.read(Employee.class, header, readProcessors)) != null) {
                System.out.println(employee);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 書き込み
        CellProcessor[] writeProcessors = new CellProcessor[]{
                new NotNull(), // name
                new NotNull(), // age
                new FmtDate("yyyy/MM/dd"), // birth
                new NotNull(), // email
                new Optional() // note
        };

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        List<Employee> employeeList = new ArrayList<>(
                Arrays.asList(
                        new Employee("test", 22, sdf.parse("1999/12/12"), "test@example.com"),
                        new Employee("test2", 20, sdf.parse("2001/1/30"), "test2@example.com")
                )
        );
        Employee test3 = new Employee("test3", 20, sdf.parse("2001/1/30"), "test2@example.com");
        test3.setNote("created by java code");
        employeeList.add(test3);

        String[] header = {"name", "age", "birth", "email", "note"};
        Path path2 = Paths.get("src/main/java/write_employee.csv");
        try (ICsvBeanWriter beanWriter = new CsvBeanWriter(Files.newBufferedWriter(path2),
                CsvPreference.STANDARD_PREFERENCE)) {
            beanWriter.writeHeader(header);
            for (Employee employee : employeeList) {
                beanWriter.write(employee, header, writeProcessors);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
