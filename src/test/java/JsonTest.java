import com.fasterxml.jackson.databind.ObjectMapper;
import model.TestData;
import org.junit.jupiter.api.Test;
import java.io.InputStreamReader;
import java.io.Reader;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


public class JsonTest {
    private ClassLoader cl = JsonTest.class.getClassLoader();

    @Test
    void jsonFileParsingTest() throws Exception {
        try (Reader reader = new InputStreamReader(
                cl.getResourceAsStream("TestJSON.json")
        )) {
            ObjectMapper mapper = new ObjectMapper();
            TestData items = mapper.readValue(reader, TestData.class);

            assertThat(items.getItems().get(0).getName()).isEqualTo("Apple");
            assertThat(items.getItems().get(0).getQuantity()).isEqualTo(10);

            assertThat(items.getItems().get(1).getName()).isEqualTo("Banana");
            assertThat(items.getItems().get(1).getQuantity()).isEqualTo(5);

            assertThat(items.getItems().get(2).getName()).isEqualTo("Orange");
            assertThat(items.getItems().get(2).getQuantity()).isEqualTo(7);


        }
    }
}