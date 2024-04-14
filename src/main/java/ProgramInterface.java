import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/** Interface class with all the logic of the app **/
class ProgramInterface {
    public static void run(String directory, String attribute) {

        if (!isAttributeExists(attribute)) {
            exit();
        }

        try {
            List<Movie> movies = FileParser.parse(directory);
            Map<Object, Integer> statistic = Statistic.generateStatistic(movies, attribute);
            XMLGenerator.generateXML(statistic, attribute);
            System.out.println("XML-file successfully generated");
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            exit();
        } catch (IOException e) {
            System.out.println("Error generating XML-file");
            exit();
        } catch (RuntimeException e) {
            System.out.println("Error! Wrong directory. " + e);
            exit();
        }
    }

/** Check if attribute exists in the Movie-entity **/
    private static boolean isAttributeExists(String attr) {
        try {
            Movie.class.getDeclaredField(attr);
            return true;
        } catch (NoSuchFieldException e) {
            System.out.println("Attribute not found in the entity Movie.");
            return false;
        }
    }

    /** Close the application **/
    private static void exit() {
        System.exit(1);
    }
}
