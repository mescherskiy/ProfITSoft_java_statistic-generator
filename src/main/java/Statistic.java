import java.lang.reflect.Field;
import java.util.*;

/** Class to calculate values by attribute and return Map<Object, Integer> with statistic **/
class Statistic {

    public static Map<Object, Integer> generateStatistic(List<Movie> movies, String attribute) {
        List<Object> values = getAllValuesByField(movies, attribute);
        Map<Object, Integer> statistic = new HashMap<>();
        for (Object val: values) {
            statistic.put(val, statistic.getOrDefault(val, 0) + 1);
        }
        return sort(statistic);
    }

    /** Gather all values by the attribute-name (f.e. all genres or all years) **/
    private static List<Object> getAllValuesByField(List<Movie> movies, String attribute) {
        List<Object> values = new ArrayList<>();
        try {
            Field field = Movie.class.getDeclaredField(attribute);
            field.setAccessible(true);
            for (Movie movie: movies) {
                Object value = field.get(movie);
                if (value instanceof Set<?>) {
                    values.addAll((Set<?>) value);
                } else {
                    values.add(value.toString());
                }
            }
        } catch (NoSuchFieldException e) {
            System.out.println("Attribute not found in the entity");
        } catch (IllegalAccessException e) {
            System.out.println("Error accessing the field.");
        }
        return values;
    }

    /** Sort result by count DESC **/
    private static Map<Object, Integer> sort(Map<Object, Integer> map) {
        List<Map.Entry<Object, Integer>> list = new ArrayList<>(map.entrySet());
        list.sort((o1, o2) -> o2.getValue().compareTo(o1.getValue()));
        Map<Object, Integer> sortedMap = new LinkedHashMap<>();
        for (Map.Entry<Object, Integer> entry: list) {
            sortedMap.put(entry.getKey(), entry.getValue());
        }
        return sortedMap;
    }
}
