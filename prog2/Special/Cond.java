// Cond -- Parse tree node strategy for printing the special form cond

package Special;

import Tree.BooleanLit;
import Tree.Environment;
import Tree.Node;
import Print.Printer;

public class Cond extends Special {

    public void print(Node t, int n, boolean p) {
        Printer.printCond(t, n, p);
    }

    public Node eval(Node n, Environment env) {
        // (cond ((= x y) 1)
        // (else 3)
        // )
        Node block = n.getCdr().getCar();
        while (!block.getCar().getName().equalsIgnoreCase("else")) {
            Node func = env.lookup(block.getCar().getCar()); // lookup "=" in environment
            if (func.isProcedure() &&
                    block.getCar().eval(env) == BooleanLit.getInstance(true)) { // if lookup got a closure/built-in,
                                                                                // apply the params to it.
                return block.getCdr().getCar().eval(env);
            }
        }
        return block.getCdr().getCar().eval(env);
    }
}
