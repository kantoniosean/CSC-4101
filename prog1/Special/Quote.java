// Quote -- Parse tree node strategy for printing the special form quote

package Special;

import Tree.Node;

public class Quote extends Special {

    public void print(Node t, int n, boolean p) {
        Node a = t.getCdr();
        int i = 0;
        if (a != null) {
            if (t.getCar().getName().equalsIgnoreCase("quote")) {
                i = 1;
            } else {
                i = 2;
            }
        }
        if (i == 2) {
            a.print(0, true);
        } else if (i == 1) {
            a.print(0, false);
        }
    }
}