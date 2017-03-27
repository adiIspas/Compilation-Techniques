/**
 * Created by Adrian Ispas on 26.03.2017.
 */
public class Token {
    private Integer type;
    private String  value;

    public Token(Integer type, String value){
        this.type  = type;
        this.value = value;
    }

    public Integer getType(){
        return this.type;
    }

    @Override
    public String toString() {
        return "Token{" +
                "type=" + type +
                ", value='" + value + '\'' +
                '}';
    }
}
