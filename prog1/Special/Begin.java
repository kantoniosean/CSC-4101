// Begin -- Parse tree node strategy for printing the special form begin

package Special;

import Tree.Node;

public class Begin extends Special {

    public void print(Node t, int n, boolean p) {
        System.out.print("begin\n  ");
        if (t.isPair()) {
            Node a = t.getCdr();
            a.print(2, true);
            System.out.println();
        }
    }
}