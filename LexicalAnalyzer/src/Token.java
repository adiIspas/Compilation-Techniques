/**
 * Created by Adrian Ispas on 26.03.2017.
 */
public class Token {
    private Integer type;
    private Integer valueIndex;

    public Token(Integer type, Integer valueIndex){
        this.type  = type;
        this.valueIndex = valueIndex;
    }

    public Integer getType(){
        return this.type;
    }

    public Integer getValueIndex() {
        return this.valueIndex;
    }

    @Override
    public String toString() {
        return "Token{" +
                "type=" + type +
                ", value='" + valueIndex + '\'' +
                '}';
    }
}
