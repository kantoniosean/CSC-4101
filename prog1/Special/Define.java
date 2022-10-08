// Define -- Parse tree node strategy for printing the special form define

package Special;

import Tree.Node;

public class Define extends Special {

    public void print(Node t, int n, boolean p) {
        System.out.print("(define ");

        if (t.getCar() != null) {
            t.getCar().print(2, true);
        } else {
            System.out.print(")");
        }
    }
}