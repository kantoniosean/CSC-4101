// Regular -- Parse tree node stratagy for printing regular lists

package Special;

import Tree.BooleanLit;
import Tree.Ident;
import Tree.IntLit;
import Tree.Nil;
import Tree.Node;
import Tree.StrLit;

public class Regular extends Special {

    public void print(Node t, int n, boolean p) {
        if (t.isBoolean(t)) {
            System.out.println("GOT HERE b");
            BooleanLit a = (BooleanLit) t;
            a.print(n);
        } else if (t.isNumber()) {
            System.out.println("GOT HERE 1");
            IntLit a = (IntLit) t;
            a.print(n);
        } else if (t.isString()) {
            System.out.println("GOT HERE s");
            StrLit a = (StrLit) t;
            a.print(n);
        } else if (t.isSymbol(t)) {
            System.out.println("GOT HERE i");
            Ident a = (Ident) t;
            a.print(n);
        } else if (t.isPair()) {
            System.out.println("GOT HERE (");
            Node a = t.getCar();
            if (!p)
                System.out.print("(");
            a.print(n);
        } else if (t.isNull(t)) {
            System.out.println("GOT HERE )");
            Nil a = (Nil) t;
            a.print(n, p);
        }
    }
}