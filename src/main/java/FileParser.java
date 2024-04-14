import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

import java.io.*;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/** Service-class that parses JSON into the Movie-entity **/
class FileParser {

    public static List<Movie> parse(String directory) throws IOException, RuntimeException {
        File dir = new File(directory);
        File[] files = dir.listFiles(file -> !file.isDirectory() && file.getName().endsWith(".json"));
        List<Movie> movies = new CopyOnWriteArrayList<>();
        if (!dir.exists() || files == null) {
            throw new RuntimeException("Error! Wrong or empty directory.");
        }
        ExecutorService executorService = Executors.newFixedThreadPool(files.length);
        Gson gson = new GsonBuilder().registerTypeAdapter(Set.class, new GenreAdapter()).create();
        for (File file : files) {
            executorService.submit(() -> {
                try (Reader reader = new BufferedReader(new FileReader(file))) {
                    Movie emptyObject = new Movie(null, 0, null, null);
                    Movie[] movieArr = gson.fromJson(reader, Movie[].class);
                    List<Movie> parsedMovies = Arrays.stream(movieArr)
                            .filter(movie -> !movie.equals(emptyObject))
                            .toList();
                    movies.addAll(parsedMovies);
                } catch (JsonIOException | JsonSyntaxException e) {
                    System.out.println("Error parsing entity from file: " + e.getMessage());
                } catch (IOException e) {
                    System.out.println("Something went wrong. " + e.getMessage());
                }
            });
        }
        executorService.shutdown();
        while (!executorService.isTerminated()) {
            try {
                executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
            } catch (InterruptedException e) {
                throw new RuntimeException("Runtime exception in threads. " + e);
            }
        }
        return movies;
    }
}
