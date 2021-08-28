import java.io.*;

public class FileManager {
    public static File openedFile = null;
    private FileManager(){

    }
    public static void saveOpenedFileWithText(String text){
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(openedFile));
            bufferedWriter.write(text);
            bufferedWriter.close();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    public static String openFile(String path){

        openedFile = new File(path);
        String total = "";

        BufferedReader bufferedReader;
        try {
            bufferedReader = new BufferedReader(new FileReader(openedFile));

            String line;
            while((line = bufferedReader.readLine()) != null){
                total += line + "\n";
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return total;
    }
}
