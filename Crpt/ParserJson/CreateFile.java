import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class CreateFile {
    public static void createFile(List<NewDocumentObjectString> list) throws IOException {
        File file = new File("document.json");
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(file,list);
        if (file.length()==0){
            System.out.println("файл пустой");
        }
        else{
            System.out.println("откройте файл document.json");
        }

    }
}
