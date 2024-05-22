import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        List<NewDocumentObjectString>list;
        Parser parser=new Parser();
        CreateFile createFile = new CreateFile();
        CrptApi honestSign=new CrptApi(TimeUnit.MINUTES,15);
        honestSign.start();
        list=parser.parse();
        honestSign.createDocumentTransactionProduct("kjbjwecwe","sdvsmhvhdv");
        list.add(honestSign.getNewDocumentObjectString());
        System.out.println(parser.getNewDocumentObjectString().toString());
        try {
            createFile.createFile(list);
        } catch (IOException e) {
            System.out.println("Ошибка записи в файл");;
        }
        //
//        try {
//            for (int i = 0; i < list.size(); i++) {
//                System.out.println(list.get(i).toString());
//                createFile.createFile(list.get(i),file);
//            }
//
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }


    }
}
