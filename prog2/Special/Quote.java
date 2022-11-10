// Quote -- Parse tree node strategy for printing the special form quote

package Special;

import Tree.Environment;
import Tree.Node;
import Print.Printer;

public class Quote extends Special {

    public void print(Node t, int n, boolean p) {
        Printer.printQuote(t, n, p);
    }

    public Node eval(Node n, Environment env) {
        // TODO Auto-generated method stub
        return null;
    }
}
