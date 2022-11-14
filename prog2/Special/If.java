// If -- Parse tree node strategy for printing the special form if

package Special;

import Tree.BooleanLit;
import Tree.Environment;
import Tree.Nil;
import Tree.Node;
import Print.Printer;

public class If extends Special {

    public void print(Node t, int n, boolean p) {
        Printer.printIf(t, n, p);
    }

    public Node eval(Node n, Environment env) {
        // form of: (if (= x y) a b)
        // we evaluate (= x y) by looking up the variables in our given environment
        // could possibly do
        // lookup(n.cdr.car.car, env) which would return BuiltIn("=")
        // we do isProcedure on Node returned from lookup to make sure it's a func.
        // then, we apply the parameters to the func to get a return value of #t or #f.
        // if it's true we execute a, else we execute b
        Node curr = n.getCdr(); // ((= x y) a b)
        Node func = env.lookup(curr.getCar().getCar()); // lookup "=" in environment
        if (func.isProcedure()) { // if lookup got a closure/built-in, apply the params to it.
            if (curr.getCar().eval(env) == BooleanLit.getInstance(true))
                return curr.getCdr().getCar().eval(env);
            else
                return curr.getCdr().getCdr().getCar().eval(env);

        } // else throw an error?

        return Nil.getInstance();
    }
}
