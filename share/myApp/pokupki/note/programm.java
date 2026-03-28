import java.io.*;
import java.util.regex.Pattern;
import java.util.regex.Matcher;


public class programm{

    public static void main(String args[]){
        String line, pattern;
        pattern = "D MainActivity";  // Для других телефонов
        // pattern = "D/MainActivity";     // Для lenovo
        // FileReader reader = new FileReader("logo.txt");
        try (BufferedReader reader = new BufferedReader(new FileReader("logo.txt"))) {
            while ((line = reader.readLine()) != null) {
                if(line.contains(pattern))
                    System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
