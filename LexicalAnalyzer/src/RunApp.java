import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Adrian Ispas on 26.03.2017.
 */
public class RunApp {
    public static void main(String args[]) throws IOException {

        PrintWriter writeToFile = new PrintWriter("output\\tokens.txt", "UTF-8");

        // Read C code
        String FILENAME = "input\\c_code.txt";

        String currentLine;
        String code = "";
        BufferedReader br = new BufferedReader(new FileReader(FILENAME));

        while ((currentLine = br.readLine()) != null) {
           code += currentLine;
        }

        Scanner scanner = new Scanner(code);
        Token token;

        while((token = scanner.getToken()).getType() != -1){

            if(token.getType() == 0)
                break;

            writeToFile.println(token);
        }

        writeToFile.println(token);
        writeToFile.close();
    }
}
