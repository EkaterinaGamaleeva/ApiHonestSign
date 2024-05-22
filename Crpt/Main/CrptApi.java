import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import javax.naming.LimitExceededException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpRequest;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;

public class CrptApi extends Thread {
    private int requestLimit = 10;
    private final TimeUnit timeUnit;
    public int countRequest;
    private static Parser parser= new Parser();
    private static CreateFile createFile= new CreateFile();
    public static NewDocumentObjectString newDocumentObjectString;
    private static final String ROOT_URL = "https://ismp.crpt.ru/api/v3/lk/documents/create";
    public CrptApi(TimeUnit timeUnit, int requestLimit) {

        this.requestLimit = requestLimit;
        this.timeUnit=timeUnit;
  }


    public synchronized FileReader createDocumentTransactionProduct(Object document, String signature) throws InterruptedException, IOException {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost(ROOT_URL);
        System.out.println("Поток запущен");
        countRequest++;
        try {
            if (requestLimit < 0) {
                Thread.sleep(timeUnit.ordinal());
                throw new LimitExceededException("Передан не верный лимит");
            }
            if (countRequest>=requestLimit){
                Thread.sleep(timeUnit.ordinal());
                throw new LimitExceededException("Привышен лимит запросов");
            }

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://ismp.crpt.ru/api/v3/lk/documents/create"))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofFile(Paths.get("data.json")))
                    .build();
            httpClient.execute(post);
            httpClient.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        newDocumentObjectString=new NewDocumentObjectString(document,signature);

        return  new FileReader("document.json");
    }

    public static NewDocumentObjectString getNewDocumentObjectString() {
        return newDocumentObjectString;
    }
}
