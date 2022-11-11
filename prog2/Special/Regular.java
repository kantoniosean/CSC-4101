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
        // regular ident that's not a special case, so we want to lookup its value.
        // if it's a procedure (closure/built-in), we want to apply its args and eval
        // else return eval of the lookup node
        return null;
    }
}