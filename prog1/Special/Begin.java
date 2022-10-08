// Begin -- Parse tree node strategy for printing the special form begin

package Special;

import Tree.Node;

public class Begin extends Special {

    @Override
    public void print(Node t, int n, boolean p) {
        System.out.print("(begin \n");
        if (t.getCdr() != null) {
            t.getCdr().print(n + 2, p);
        } else {
            System.out.print(")");
        }

    }
}