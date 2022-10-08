// Quote -- Parse tree node strategy for printing the special form quote

package Special;

import Tree.Node;

public class Quote extends Special {

    public void print(Node t, int n, boolean p) {
        System.out.println("'");
        if (t.getCdr() != null) {
            System.out.print("");
        } else {
            t.getCdr().print(0, false);
        }
    }
}