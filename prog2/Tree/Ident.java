// Ident -- Parse tree node class for representing identifiers

package Tree;

import Print.Printer;

public class Ident extends Node {
    private String name;

    public Ident(String n) {
        name = n;
    }

    public void print(int n) {
        Printer.printIdent(n, name);
    }

    public boolean isSymbol() {
        return true;
    }

    public String getName() {
        return name;
    }

    public Node eval(Environment env) {
        System.out.println("Ident");
        return env.lookup(this);
    }
}
