import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import java.nio.file.*;
import java.io.IOException;
import java.util.List;

public class Lesson8UnitTest {

    Path matchesDir = Paths.get("src", "res", "matches");
    Path dataFile = matchesDir.resolve("data.txt");

    @BeforeEach
    public void setUp() throws IOException {
        // Clean up before running each test
        if (Files.exists(dataFile))
		{			
			Files.delete(dataFile);
		}
		
        if (Files.exists(matchesDir)) 
		{
			Files.delete(matchesDir);
		}
		
        Main.main(null); // Run the main method to generate output
    }

    @Test
    public void testDirectoryCreation() {
        assertTrue(Files.exists(matchesDir), "matches directory should be created");
    }

    @Test
    public void testDataFileCreation() {
        assertTrue(Files.exists(dataFile), "data.txt file should be created");
    }

    @Test
    public void testCountryNamesLongerThan10Characters() throws IOException {
        List<String> lines = Files.readAllLines(dataFile);
        String expectedHeader = "Country names longer than 10 characters:";
        assertTrue(lines.contains(expectedHeader), "Header for long country names should be present");

        // Checking a sample country from the list
        assertTrue(lines.contains("Afghanistan"), "Afghanistan should be listed as a country longer than 10 characters");
    }

    @Test
    public void testCountryNamesStartingWithA() throws IOException {
        List<String> lines = Files.readAllLines(dataFile);
        String expectedHeader = "Country names starting with 'A':";
        assertTrue(lines.contains(expectedHeader), "Header for countries starting with 'A' should be present");

        // Checking a sample country from the list
        assertTrue(lines.contains("Albania"), "Albania should be listed as a country starting with 'A'");
    }

    @AfterEach
    public void tearDown() throws IOException {
        // Clean up after tests
        Files.deleteIfExists(dataFile);
        Files.deleteIfExists(matchesDir);
    }
}
