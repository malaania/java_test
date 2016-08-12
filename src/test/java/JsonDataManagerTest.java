import com.managers.JsonDataManager;
import com.model.City;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.List;

import static org.junit.Assert.*;

public class JsonDataManagerTest {

    @Test
    public void shouldGetJson() throws IOException {
        List<City> places = JsonDataManager.getCityData("Berlin");

        assertNotNull(places);
        assertFalse(places.isEmpty());

        for (City city : places) {
            assertTrue(city.getName().contains("Berlin"));
        }
    }

    @Test
    public void shouldGetNothing() throws IOException {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        List<City> places = JsonDataManager.getCityData("non_exisiting_city");
        String expectedMessage = "Place non_exisiting_city was not found.\r\n";

        assertNull(places);
        assertEquals(expectedMessage, outContent.toString());
    }

    @Test
    public void shouldGetNotFound() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        String json = JsonDataManager.fetchJson("http://api.goeuro.com/api/v2/position/suggest/");
        String expectedMessage = "404 : Not Found\r\n";

        assertNull(json);
        assertEquals(expectedMessage, outContent.toString());
    }


}
