// If -- Parse tree node strategy for printing the special form if

package Special;

import Tree.Environment;
import Tree.Node;
import Print.Printer;

public class If extends Special {

    public void print(Node t, int n, boolean p) {
        Printer.printIf(t, n, p);
    }

    public Node eval(Node n, Environment env) {
        // TODO Auto-generated method stub
        return null;
    }
}
