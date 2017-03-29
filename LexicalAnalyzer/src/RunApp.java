import java.io.File;
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
        Long numberOfChars = (new File(FILENAME)).length();
        char[] code = new char[Integer.parseInt(numberOfChars.toString())];
        FileReader fr = new FileReader(FILENAME);

        int index = 0;
        Integer currentValue;

        while ((currentValue = fr.read()) != -1) {
            char[] temp = Character.toChars(currentValue);
            for(int i = 0; i < temp.length; i++){
                code[index++] = temp[i];
            }
        }

        Scanner scanner = new Scanner(code);
        Token token;
        
        while((token = scanner.getToken()).getType() != -1){

            if(token.getType() == 0)
                break;

            writeToFile.println(showToken(token.getType(),scanner.getTokenByIndex(token.getValueIndex())));
        }

        writeToFile.println("Final token{type = " + token.getType() + " | value = " +
                scanner.getTokenByIndex(token.getValueIndex()) + " }");
        writeToFile.close();
    }

    private static String showToken(Integer type, String value){
        return "Token{type = " + type + " | " + "value = " + value + " }";
    }
}