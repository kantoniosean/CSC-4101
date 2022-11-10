// Regular -- Parse tree node stratagy for printing regular lists

package Special;

import Tree.Environment;
import Tree.Node;
import Print.Printer;

public class Regular extends Special {

    public void print(Node t, int n, boolean p) {
        Printer.printRegular(t, n, p);
    }

    public Node eval(Node n, Environment env) {
        // TODO Auto-generated method stub
        return null;
    }
}
