// Cond -- Parse tree node strategy for printing the special form cond

package Special;

import Tree.Node;

public class Cond extends Special {

    public void print(Node t, int n, boolean p) {
        System.out.print("cond\n  ");
        if (t.isPair()) {
            Node a = t.getCdr();
            a.print(2, true);
            System.out.println();
        }
    }
}