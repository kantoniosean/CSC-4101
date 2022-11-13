// Set -- Parse tree node strategy for printing the special form set!

package Special;

import Tree.Environment;
import Tree.Nil;
import Tree.Node;
import Print.Printer;

public class Set extends Special {

    public void print(Node t, int n, boolean p) {
        Printer.printSet(t, n, p);
    }

    public Node eval(Node n, Environment env) {
        // (set! x e)
        Node val = n.getCdr().getCdr().getCar();
        val = val.eval(env); // evaluate e, returns e'
        Node x = n.getCdr().getCar();
        if (env.lookup(x).isNull()) {
            System.err.println("Error: variable has not been defined prior to set");
            return Nil.getInstance();
        }
        env.assign(x, val);
        return val;
    }
}
