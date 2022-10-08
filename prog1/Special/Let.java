// Let -- Parse tree node strategy for printing the special form let

package Special;

import Tree.Node;

public class Let extends Special {

    public void print(Node t, int n, boolean p) {
        System.out.print("let\n  ");
        if (t.isPair()) {
            Node a = t.getCdr();
            a.print(2, true); // regular print ignores n, so fix if I have time (indentation error)
            System.out.println();
        }
    }
}