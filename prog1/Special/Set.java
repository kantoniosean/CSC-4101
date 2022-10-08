// Set -- Parse tree node strategy for printing the special form set!

package Special;

import Tree.Node;

public class Set extends Special {

    public void print(Node t, int n, boolean p) {
        System.out.print("set!");
        if (t.isPair()) {
            Node a = t.getCdr();
            a.print(1, true);
            System.out.println();
        }
    }
}