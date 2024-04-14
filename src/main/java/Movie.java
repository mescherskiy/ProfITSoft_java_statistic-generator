import java.lang.reflect.Field;
import java.util.Objects;
import java.util.Set;

/** Main entity **/
class Movie {
    private final String title;
    private final int year;
    private final Set<Genre> genre;
    private final String director;


    public Movie(String title, int year, Set<Genre> genre, String director) {
        this.title = title;
        this.year = year;
        this.genre = genre;
        this.director = director;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Movie movie = (Movie) o;

        if (year != movie.year) return false;
        if (!Objects.equals(title, movie.title)) return false;
        if (!Objects.equals(genre, movie.genre)) return false;
        return Objects.equals(director, movie.director);
    }

    @Override
    public int hashCode() {
        int result = title != null ? title.hashCode() : 0;
        result = 31 * result + year;
        result = 31 * result + (genre != null ? genre.hashCode() : 0);
        result = 31 * result + (director != null ? director.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "title='" + title + '\'' +
                ", year=" + year +
                ", genre=" + genre +
                ", director='" + director + '\'' +
                '}';
    }
}
