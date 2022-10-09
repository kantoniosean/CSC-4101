// If -- Parse tree node strategy for printing the special form if

package Special;

import Tree.Node;

public class If extends Special {

    public void print(Node t, int n, boolean p) {
        System.out.print("if "); // needs to have first two list elements then indent. same with lambda
        if (t.isPair()) {
            Node a = t.getCdr();
            a.print(0, true);
        }
    }
}