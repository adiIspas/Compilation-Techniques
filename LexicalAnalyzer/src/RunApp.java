import java.io.IOException;

/**
 * Created by Adrian Ispas on 26.03.2017.
 */
public class RunApp {
    public static void main(String args[]) throws IOException {
//        String code = "abc=(12+x30)+1;  y =abc;";
        String code = "ab12/*x12*";
        Scanner scanner = new Scanner(code);

        Token token;
        while((token = scanner.getToken()).getType() != -1){

            if(token.getType() == 0)
                break;

            System.out.println(token);
        }

        System.out.println(token);

    }
}
