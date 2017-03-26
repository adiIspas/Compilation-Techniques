import java.io.IOException;

/**
 * Created by Adrian Ispas on 26.03.2017.
 */
public class RunApp {
    public static void main(String args[]) throws IOException {
        String code = "abc=(12+x30)+1;  y =abc";
        Scanner scanner = new Scanner(code);

        Token token = scanner.getToken();
        while(token != null){
            System.out.println(token);
            token = scanner.getToken();
        }


    }
}
