import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Adrian Ispas on 26.03.2017.
 */
public class RunApp {
    public static void main(String args[]) throws IOException {
//        DFA dfa = new DFA();
//        System.out.println(dfa.getTransition("comm",'\n'));

        PrintWriter writeToFile = new PrintWriter("output\\tokens.txt", "UTF-8");

        // Read C code
        String FILENAME = "input\\c_code.txt";

        Integer currentValue;
        FileReader fr = new FileReader(FILENAME);

        char[] code = new char[500];
        int index = 0;
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

            writeToFile.println(token);
        }

        writeToFile.println(token);
        writeToFile.close();
    }
}
