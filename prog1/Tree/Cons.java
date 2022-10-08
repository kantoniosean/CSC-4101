// Cons -- Parse tree node class for representing a Cons node

package Tree;

import Special.Begin;
import Special.Cond;
import Special.Define;
import Special.If;
import Special.Lambda;
import Special.Let;
import Special.Quote;
import Special.Regular;
import Special.Set;
import Special.Special;

public class Cons extends Node {
    private Node car;
    private Node cdr;
    private Special form;

    public Cons(Node a, Node d) {
        car = a;
        cdr = d;
        parseList();
    }

    // parseList() `parses' special forms, constructs an appropriate
    // object of a subclass of Special, and stores a pointer to that
    // object in variable form. It would be possible to fully parse
    // special forms at this point. Since this causes complications
    // when using (incorrect) programs as data, it is easiest to let
    // parseList only look at the car for selecting the appropriate
    // object from the Special hierarchy and to leave the rest of
    // parsing up to the interpreter.
    void parseList() {
        // if (car is not a symbol)
        // form = new Regular();
        // else
        // ...
        if (!car.isSymbol(car)) {
            form = new Regular();
        } else {
            // special case
            Ident c = (Ident) car;
            if (c.getName().equalsIgnoreCase("begin")) {
                form = new Begin();
            } else if (c.getName().equalsIgnoreCase("cond")) {
                form = new Cond();
            } else if (c.getName().equalsIgnoreCase("define")) {
                form = new Define();
            } else if (c.getName().equalsIgnoreCase("if")) {
                form = new If();
            } else if (c.getName().equalsIgnoreCase("lambda")) {
                form = new Lambda();
            } else if (c.getName().equalsIgnoreCase("let")) {
                form = new Let();
            } else if (c.getName().equalsIgnoreCase("quote") || c.getName().equalsIgnoreCase("'")) {
                form = new Quote();
            } else if (c.getName().equalsIgnoreCase("set")) {
                form = new Set();
            } else {
                form = new Regular();
            }
        }
    }

    @Override
    public void print(int n) {
        form.print(this, n, false);
    }

    public void print(int n, boolean p) {
        form.print(this, n, p);
    }

    public Node getCar() {
        return car;
    }

    public Node getCdr() {
        return cdr;
    }

    public void setCar(Node a) {
        car = a;
    }

    public void setCdr(Node d) {
        cdr = d;
    }

    public boolean isPair() {
        return true;
    }
}