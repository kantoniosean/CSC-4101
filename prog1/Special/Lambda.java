// Lambda -- Parse tree node strategy for printing the special form lambda

package Special;

import Tree.Node;

public class Lambda extends Special {

    public void print(Node t, int n, boolean p) {
        System.out.print("lambda ");
        if (t.isPair()) {
            Node a = t.getCdr();
            a.print(0, true);
        }
    }
}