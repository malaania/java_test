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
        assertNull(places);
        String expectedMessage = "Place non_exisiting_city was not found.\r\n";
        assertEquals(expectedMessage, outContent.toString());
    }


}
