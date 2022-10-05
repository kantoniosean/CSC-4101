// Token -- Super class for Token objects.

package Tokens;

public class Token<T> {
    private TokenType tt;
    private T data;

    public Token(TokenType t, T data) {
        tt = t;
        this.data = data;
    }

    public String toString() {
        return "" + this.data;
    }
    public final TokenType getType() {
        return tt;
    }

    public int getIntVal() {
        String num = data.toString();
        return Integer.parseInt(num);

    }

    public String getStrVal() {
        String str = data.toString();
        return str;
    }

    public String getName() {
        String name = data.toString();
        return name;
    }
}
