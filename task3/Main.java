import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

public class Main {

    static Values values;

    public static void main(String[] args) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        Tests tests = mapper.readValue(Paths.get(args[0]).toFile(), Tests.class);
        values = mapper.readValue(Paths.get(args[1]).toFile(), Values.class);
        for (Test test : tests.tests) {
            String result = testResult(test);
            if (result != null) {
                test.value = result;
            }
            deep(test.values);
        }
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        ObjectWriter writer = mapper.writer(new DefaultPrettyPrinter());
        writer.writeValue(new File(args[2]), tests);
    }

    private static void deep(List<Test> testValues) {
        if (testValues == null) {
            return;
        }
        for (Test test : testValues) {
            String result = testResult(test);
            if (result != null) {
                test.value = result;
            }
            if (test.values != null) {
                deep(test.values);
            }
        }
    }

    private static String testResult(Test test) {
        if (test.value == null) {
            return null;
        }
        for (Value value : values.values) {
            if (value.id == test.id) {
                return value.value;
            }
        }
        return null;
    }
}
