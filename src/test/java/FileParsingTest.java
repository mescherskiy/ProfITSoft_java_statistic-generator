import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class FileParsingTest {

    @Test
    void testParseWithNonexistentDirectory() {
        String directory = "non_existent_directory";

        RuntimeException exception =
                assertThrows(RuntimeException.class, () -> {
                    FileParser.parse(directory);
                }, "Wrong directory");

        assertEquals(
                "Error! Wrong or empty directory.",
                exception.getMessage()
        );
    }

    @Test
    void testParseWrongObjects() throws IOException {
        String directory = "src/main/resources/wrong_folder";

        List<Movie> movies = FileParser.parse(directory);

        assertNotNull(movies);
        assertTrue(movies.isEmpty());
//        assertTrue(movies.isEmpty());
    }

    @Test
    void testParseTwoCorrectObjectsAmongManyWrong() throws IOException {
        String directory = "src/main/resources/test_folder";

        List<Movie> movies = FileParser.parse(directory);

        assertEquals(2, movies.size());
    }

    @Test
    void testParse() throws IOException {
        String directory = "src/main/resources/normal_folder";

        List<Movie> movies = FileParser.parse(directory);

        assertNotNull(movies);
        assertFalse(movies.isEmpty());
    }
}
