// Let -- Parse tree node strategy for printing the special form let

package Special;

import Tree.Node;

public class Let extends Special {

    public void print(Node t, int n, boolean p) {
        System.out.print("(let \n");

        if (t.getCar() != null) {
            t.getCar().print(2, true);
        } else {
            System.out.print(")");
        }

    }
}