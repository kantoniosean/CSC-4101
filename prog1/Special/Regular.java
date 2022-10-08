// Regular -- Parse tree node stratagy for printing regular lists

package Special;

import Tree.Node;

public class Regular extends Special {

    public void print(Node t, int n, boolean p) {
        if (t.isBoolean(t)) {
            t.print(n);
        } else if (t.isNumber()) {
            t.print(n);
        } else if (t.isString()) {
            t.print(n);
        } else if (t.isSymbol(t)) {
            t.print(n);
        } else if (t.isPair()) {
            if (!p) {
                System.out.print("(");
                t.getCar().print(0, true);
            } else {
                t.getCar().print(n);
            }

            int spaces = 0;
            t = t.getCdr();
            if (t.getCar() != null && !t.getCar().isPair() && !t.getCdr().isNull(t.getCdr()))
                spaces = 1;
            if (t != null) {
                t.print(spaces, p);
            }
        } else if (t.isNull(t)) {
            t.print(n, p);
        }
    }
}