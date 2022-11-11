// Cond -- Parse tree node strategy for printing the special form cond

package Special;

import Tree.Environment;
import Tree.Node;
import Print.Printer;

public class Cond extends Special {

    public void print(Node t, int n, boolean p) {
        Printer.printCond(t, n, p);
    }

    public Node eval(Node n, Environment env) {
        // TODO Auto-generated method stub
        return null;
    }
}
