import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.HashSet;
import java.util.Set;

/** Adapter for GSON so that it can parse Set<Genre> in Movie-entity **/
class GenreAdapter implements JsonDeserializer<Set<Genre>> {

    @Override
    public Set<Genre> deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        Set<Genre> genres = new HashSet<>();
        String[] genreNames = json.getAsString().split(",");
        for (String genreName : genreNames) {
            genres.add(Genre.valueOf(genreName.trim().toUpperCase()));
        }
        return genres;
    }
}