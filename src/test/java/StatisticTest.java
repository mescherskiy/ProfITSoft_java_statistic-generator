import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class StatisticTest {

    @Test
    void testGenerateStatisticWithEmptyList() {
        List<Movie> movies = Collections.emptyList();
        String attribute = "year";
        Map<Object, Integer> statistic = Statistic.generateStatistic(movies, attribute);
        assertTrue(statistic.isEmpty());
    }

    @Test
    void testGenerateStatisticWithNullList() {
        List<Movie> movies = null;
        String attribute = "year";
        assertThrows(NullPointerException.class, () -> {
            Statistic.generateStatistic(movies, attribute);
        });
    }

    @Test
    void testGenerateStatisticWithNonexistentAttribute() {
        List<Movie> movies = Arrays.asList(
                new Movie("Title1", 2001, Set.of(Genre.ACTION), "Director1"),
                new Movie("Title2", 2002, Set.of(Genre.CRIME, Genre.ROMANCE), "Director2")
        );
        String attribute = "nonexistentAttribute";

        Map<Object, Integer> statistic = Statistic.generateStatistic(movies, attribute);

        assertTrue(statistic.isEmpty());
    }

    @Test
    void testGenerateStatisticWithValidAttribute() {
        List<Movie> movies = Arrays.asList(
                new Movie("Title1", 2001, Set.of(Genre.ACTION), "Director1"),
                new Movie("Title2", 2002, Set.of(Genre.ACTION, Genre.ROMANCE), "Director2"),
                new Movie("Title3", 2003, Set.of(Genre.ACTION, Genre.ROMANCE, Genre.DOCUMENTARY), "Director3")
        );
        String attribute = "genre";
        Map<Object, Integer> statistic = Statistic.generateStatistic(movies, attribute);
        assertEquals(3, statistic.size());
        assertEquals(2, statistic.get(Genre.ROMANCE));
        assertEquals(1, statistic.get(Genre.DOCUMENTARY));
    }

}
