// Quote -- Parse tree node strategy for printing the special form quote

package Special;

import Tree.Node;

public class Quote extends Special {

    public void print(Node t, int n, boolean p) {
        System.out.print(" '");
        Node a = t.getCdr();
        if (a != null)
            a.print(0, true);
    }
}