// Regular -- Parse tree node stratagy for printing regular lists

package Special;

import Tree.Cons;
import Tree.Environment;
import Tree.Node;
import Print.Printer;

public class Regular extends Special {

    public void print(Node t, int n, boolean p) {
        Printer.printRegular(t, n, p);
    }

    public Node eval(Node n, Environment env) {
        // regular ident that's not a special case, so we want to lookup its value.
        // if it's a procedure (closure/built-in), we want to apply its args and eval
        // else return eval of the lookup node
        // n = (f x1 ... xn)
        Node head = n.getCar();
        if (head.isProcedure()) {
            Node val = env.lookup(head);
            // val = eval(val, env); // evaluate closure
            Node args = Util.mapeval(n.getCdr(), env);
            Cons c = new Cons(val, args);
            return val.apply(c);
        } else {
            // head = eval(head, env);
            if (n.getCdr().isNull())
                return head;
            else
                return eval(n.getCdr(), env);
        }
    }
}