import org.apache.http.HttpEntity;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Parser {
    public NewDocumentObjectString newDocumentObjectString;
    private List<NewDocumentObjectString> newDocumentObjectStringList = new ArrayList<>();
    private List<String> keys = new ArrayList<>();
    private static final String DATA_FILE="data.json";
    public String getJsonFile() {
        StringBuilder builder = new StringBuilder();
        try {
            List<String> lines = Files.readAllLines(Paths.get(DATA_FILE));
            lines.forEach(line -> builder.append(line));

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return builder.toString();
    }
    public List<NewDocumentObjectString> parse() {

        NewDocumentObjectString newDocumentObjectString2 = null;
        try (FileReader reader = new FileReader(DATA_FILE)) {
            JSONParser parser = new JSONParser();
            JSONObject object = (JSONObject) parser.parse(getJsonFile());
            JSONObject descr = (JSONObject) object.get("description");
            String participantInn = (String) descr.get("participantInn");
            String docId = (String) object.get("doc_id");
             newDocumentObjectString = new NewDocumentObjectString(descr, participantInn);
             newDocumentObjectStringList.add(newDocumentObjectString);
             newDocumentObjectString  = new NewDocumentObjectString("doc_id", docId);
              newDocumentObjectStringList.add(newDocumentObjectString);
        } catch (Exception e) {
            System.out.println("Ошибка парсинга");
        }
        return newDocumentObjectStringList;
    }

    public NewDocumentObjectString getNewDocumentObjectString() {
        return newDocumentObjectString;
    }
}
