// Begin -- Parse tree node strategy for printing the special form begin

package Special;

import Tree.Environment;
import Tree.Node;
import Print.Printer;

public class Begin extends Special {

    public void print(Node t, int n, boolean p) {
        Printer.printBegin(t, n, p);
    }

    // Cons node wants to be evaluated and it was of form Begin.
    // Now, we recursively call eval on each element in the cons
    // and return the value of the car once the cdr is a Nil
    public Node eval(Node n, Environment env) {
        // if (n.getCdr().isNull()) { // special case, at EOL
        // return eval(n.getCar(), env);
        // }
        // eval(n.getCar(), env); // evaluate car
        // return eval(n.getCdr(), env); // recursively call rest of list and eval the
        // cdr
        return Util.begin(n, env);
    }
}
