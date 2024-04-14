import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

/** XML-file generator that uses Map<Object, Integer> with statistic  **/
class XMLGenerator {
    public static void generateXML(Map<Object, Integer> statistic, String attributeName) throws IOException {
        File xmlFile = new File("statistics_by_" + attributeName + ".xml");
        try (FileWriter writer = new FileWriter(xmlFile)) {
            writer.write("<statistics>\n");

            for (Map.Entry<Object, Integer> entry : statistic.entrySet()) {
                writer.write("  <item>\n");
                writer.write("    <value>" + entry.getKey() + "</value>\n");
                writer.write("    <count>" + entry.getValue() + "</count>\n");
                writer.write("  </item>\n");
            }

            writer.write("</statistics>");
        } catch (IOException e) {
            System.out.println("Error writing XML-file");
        }
    }
}

