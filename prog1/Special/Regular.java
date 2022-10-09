// Regular -- Parse tree node stratagy for printing regular lists

package Special;

import Tree.Nil;
import Tree.Node;

public class Regular extends Special {

    public void print(Node t, int n, boolean p) {

        if (t.isBoolean(t)) {
            t.print(n);
        } else if (t.isNumber()) {
            t.print(n);
        } else if (t.isString()) {
            t.print(n + 2);
        } else if (t.isSymbol()) {
            t.print(n);
        } else if (t.isPair()) {
            if (!p) {
                for (int i = 0; i < n; i++) {
                    System.out.print("  ");
                }
                System.out.print("(");
                t.getCar().print(0, true);
                n = n + 2;
            } else {
                t.getCar().print(n);
            }
            // car is printed at this point.
            // if cdr is Nil, n should be 0
            // Cons --> (car) define
            // --> (cdr) Cons --> (car) x
            // --> (cdr) Cons --> (car) Cons
            // --> (cdr) Nil
            // (define is printed, now call cdr print (Cons)
            // x -- Cons
            // (define x
            // cdr is Cons. spaces = 0.
            int spaces = 0;
            t = t.getCdr();
            // System.out.print(" " + t.getClass() + " ");
            if (!(t instanceof Nil))
                spaces = 1;
            if (t != null) {
                t.print(spaces, p);
            }
        } else if (t.isNull(t)) {
            t.print(n, p);
        }
    }
}