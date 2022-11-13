// BuiltIn.java -- the data structure for function closures

// Class BuiltIn is used for representing the value of built-in functions
// such as +.  Populate the initial environment with
// (name, new BuiltIn(name)) pairs.

// The object-oriented style for implementing built-in functions would be
// to include the Java methods for implementing a Scheme built-in in the
// BuiltIn object.  This could be done by writing one subclass of class
// BuiltIn for each built-in function and implementing the method apply
// appropriately.  This requires a large number of classes, though.
// Another alternative is to program BuiltIn.apply() in a functional
// style by writing a large if-then-else chain that tests the name of
// of the function symbol.

package Tree;

import Parse.Scanner;
import Parse.Parser;
import java.io.FileInputStream;
import java.io.IOException;

public class BuiltIn extends Node {
    // TODO: For allowing the built-in functions to access the environment,
    // keep a copy of the Environment here and synchronize it with
    // class Scheme4101.

    private static Environment globalEnv = null;

    public static void setGlobalEnv(Environment env) {
        globalEnv = env;
    }

    private Node symbol;

    public BuiltIn(Node s) {
        symbol = s;
    }

    public Node getSymbol() {
        return symbol;
    }

    public boolean isProcedure() {
        return true;
    }

    public void print(int n) {
        // there got to be a more efficient way to print n spaces
        for (int i = 0; i < n; i++)
            System.out.print(' ');
        System.out.print("#{Built-in Procedure ");
        if (symbol != null)
            symbol.print(-Math.abs(n) - 1);
        System.out.print('}');
        if (n >= 0)
            System.out.println();
    }

    public void error() {
        System.out.println("Error in arguments :(");
    }

    // The method apply() should be defined in class Node
    // to report an error. It should be overwritten only in classes
    // BuiltIn and Closure.
    public Node apply(Node args) {
        Node first = args.getCdr().getCar();
        Node second = args.getCdr().getCdr();

        if (first.isNull()) {
            first = Nil.getInstance();
        }

        if (!second.isNull()) {
            second = second.getCar();
        } else {
            second = Nil.getInstance();

        }

        String name = symbol.getName();

        if (name == "symbol?") {
            return BooleanLit.getInstance(first.isSymbol());
        }

        else if (name == "number?") {
            if (first.isNumber()) {

                return BooleanLit.getInstance(true);
            }
        }

        else if (name == "b+") {
            if (first.isNumber() && second.isNumber()) {

                return new IntLit(first.getIntVal() + second.getIntVal());
            }

            else {
                System.out.println("HERE");
                error();
                return new StrLit(":(");
            }

        }

        else if (name == "b-") {
            if (first.isNumber() && second.isNumber()) {

                return new IntLit(first.getIntVal() - second.getIntVal());
            }

            else {
                error();
                return new StrLit(":(");
            }
        }

        else if (name == "b*") {
            if (first.isNumber() && second.isNumber()) {

                return new IntLit(first.getIntVal() * second.getIntVal());
            }

            else {
                error();
                return new StrLit(":(");
            }
        }

        else if (name == "b/") {
            if (first.isNumber() && second.isNumber()) {

                return new IntLit(first.getIntVal() / second.getIntVal());
            }

            else {
                error();
                return new StrLit(":(");
            }
        }

        else if (name == "b=") {
            if (first.isBoolean() && second.isBoolean()) {

                return BooleanLit.getInstance(first.getIntVal() == second.getIntVal());

            }

            else {
                error();
                return new StrLit(":(");
            }

        }

        else if (name == "b<") {
            if (first.isBoolean() && second.isBoolean()) {

                return BooleanLit.getInstance(first.getIntVal() < second.getIntVal());

            }

            else {
                error();
                return new StrLit(":(");
            }
        }

        else if (name == "car") {
            return first;
        }

        else if (name == "cdr") {
            return second;
        }

        else if (name == "cons") {
            return new Cons(first, second);
        }

        else if (name == "set-car!") {
            first.setCar(second);
        }

        else if (name == "set-cdr!") {
            first.setCdr(second);
        }

        else if (name == "pair?") {
            return BooleanLit.getInstance(first.isPair());
        }

        else if (name == "eq?") {
            if (first.isNull() && second.isNull()) {
                return BooleanLit.getInstance(true);
            }

            else if (first.isNumber() && second.isNumber()) {
                return BooleanLit.getInstance(first.getIntVal() == second.getIntVal());
            }

            else if (first.isSymbol() && second.isSymbol()) {
                return BooleanLit.getInstance(first.getName() == second.getName());
            }

            else if (first.isString() && second.isString()) {
                return BooleanLit.getInstance(first.getStrVal() == second.getStrVal());
            }

            else {
                return BooleanLit.getInstance(first.getStrVal() == second.getStrVal());
            }
        }

        else if (name == "procedure?") {
            return BooleanLit.getInstance(first.isProcedure());
        }

        else if (name == "read") {
            Parser parse = new Parser(new Scanner(System.in));
            Node parsedEx = parse.parseExp();
            return parsedEx;
        }

        else if (name == "write") {
            return new StrLit(" ");

        }

        else if (name == "display") {
            return first;
        }

        else if (name == "newline") {
            return new StrLit("\n");
        }

        else if (name == "eval") {
            return first;
        }

        else if (name == "apply") {
            return first.apply(second);
        }

        else if (name == "interaction-environment") {
            globalEnv.print(1);
        }

        if (name == "load") {
            if (!first.isString()) {
                System.err.println("Error: wrong type of argument");
                return Nil.getInstance();
            }

            String filename = first.getStrVal();
            try {
                Scanner scanner = new Scanner(new FileInputStream(filename));
                Parser parser = new Parser(scanner);

                Node root = parser.parseExp();
                while (root != null) {
                    root.eval(globalEnv);
                    root = parser.parseExp();
                }

            }

            catch (IOException e) {
                System.err.println("Could not find file " + filename);
            }

            return Nil.getInstance(); // or Unspecific.getInstance();
        }

        else {

            return Nil.getInstance();
        }
    }

    // The easiest way to implement BuiltIn.apply is as an
    // if-then-else chain testing for the different names of
    // the built-in functions. E.g., here's how load could
    // be implemented:

}
