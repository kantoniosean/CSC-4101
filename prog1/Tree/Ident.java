// Ident -- Parse tree node class for representing identifiers

package Tree;

public class Ident extends Node {
    private String name;

    public Ident(String n) {
        name = n;
    }

    public void print(int n) {
        for (int i = 0; i < n; i++)
            System.out.print(" ");

        System.out.println(name);
    }

    public String getName() { // added this method. not sure if we are allowed to though
        return name;
    }

    public boolean isSymbol() { // not sure if this is needed, but I figured we needed a way to decipher a
                                // regular ident to these special idents
        return true;
        /*
         * return name.equalsIgnoreCase("define") ||
         * name.equalsIgnoreCase("quote") ||
         * name.equalsIgnoreCase("begin") ||
         * name.equalsIgnoreCase("cond") ||
         * name.equalsIgnoreCase("if") ||
         * name.equalsIgnoreCase("lambda") ||
         * name.equalsIgnoreCase("let") ||
         * name.equalsIgnoreCase("set");
         */
    }
}