// If -- Parse tree node strategy for printing the special form if

package Special;

import Tree.Node;

public class If extends Special {

    public void print(Node t, int n, boolean p) {
        System.out.print("(if ");
        if (t.getCdr().getCdr().getCar() != null) {
            t.getCdr().getCdr().getCar().print(0, true);
        } else {
            System.out.print(")");
        }

        if (t.getCdr().getCar().isSymbol(t)) {
            t.getCdr().getCar().print(0, false);
        }
    }
}