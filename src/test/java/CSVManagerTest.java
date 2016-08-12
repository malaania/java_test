import managers.CSVManager;
import model.City;
import org.junit.*;
import org.junit.rules.TemporaryFolder;
import org.mockito.Mockito;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

public class CSVManagerTest {

    @Rule
    public TemporaryFolder temporaryFolder = new TemporaryFolder();


    public ByteArrayOutputStream outContent;

    @Before
    public void setUpStreams() {
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
    }


    @Test
    public void shouldWriteCitiesToFile() throws IOException {

        List<String> cityData = new ArrayList<>();
        cityData.add("376217");
        cityData.add("Berlin");
        cityData.add("location");
        cityData.add("52.52437");
        cityData.add("13.41053");

        City city = Mockito.mock(City.class);
        when(city.prepareCsvEntry()).thenReturn(cityData);
        List<City> testList = new ArrayList<>();
        testList.add(city);

        File file = temporaryFolder.newFolder("tests_cities_to_file");

        String path = file.getAbsolutePath();
        String actualPath = CSVManager.writeCitiesToCSV(testList, "Berlin", path);
        File result = new File(actualPath);
        assertTrue(result.exists());

        String cityEntry = "376217, Berlin, location, 52.52437, 13.41053";
        BufferedReader br = new BufferedReader(new FileReader(actualPath));
        String line = br.readLine();
        br.close();
        assertEquals(cityEntry, line);
    }

    @Test
    public void shouldHandleNoName() throws IOException {
        File file = temporaryFolder.newFolder("tests_no_city_name");
        List<City> cities = new ArrayList<City>();
        cities.add(new City());
        String actualPath = CSVManager.writeCitiesToCSV(cities, "", file.getPath());
        assertNull(actualPath);
        assertEquals("File with no name cannot be saved.\r\n", outContent.toString());
    }

    @Test
    public void shouldHandleNoPath() throws IOException {
        List<City> cities = new ArrayList<City>();
        cities.add(new City());
        String actualPath = CSVManager.writeCitiesToCSV(cities, "Berlin", "");
        assertNull(actualPath);
        assertEquals(" directory does not exist.\r\n", outContent.toString());
    }

    @Test
    public void shouldHandleEmptyList() throws IOException {
        File file = temporaryFolder.newFolder("tests_empty_array");
        String actualPath = CSVManager.writeCitiesToCSV(new ArrayList<City>(), "Berlin", file.getPath());
        assertNull(actualPath);
        assertEquals("No entries to add to a file.\r\n", outContent.toString());
    }


}
