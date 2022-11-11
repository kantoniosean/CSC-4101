// Let -- Parse tree node strategy for printing the special form let

package Special;

import Tree.Environment;
import Tree.Node;
import Print.Printer;

public class Let extends Special {

    public void print(Node t, int n, boolean p) {
        Printer.printLet(t, n, p);
    }

    public Node eval(Node n, Environment env) {
        // TODO Auto-generated method stub
        return null;
    }
}
