// Closure.java -- the data structure for function closures

// Class Closure is used to represent the value of lambda expressions.
// It consists of the lambda expression itself, together with the
// environment in which the lambda expression was evaluated.

// The method apply() takes the environment out of the closure,
// adds a new frame for the function call, defines bindings for the
// parameters with the argument values in the new frame, and evaluates
// the function body.

package Tree;

public class Closure extends Node {
    private Node fun; // a lambda expression
    private Environment env; // the environment in which
                             // the function was defined

    public Closure(Node f, Environment e) {
        fun = f;
        env = e;
    }

    public Node getFun() {
        return fun;
    }

    public Environment getEnv() {
        return env;
    }

    public boolean isProcedure() {
        return true;
    }

    public void print(int n) {
        // there got to be a more efficient way to print n spaces
        for (int i = 0; i < n; i++)
            System.out.print(' ');
        System.out.println("#{Procedure");
        if (fun != null)
            fun.print(Math.abs(n) + 2);
        for (int i = 0; i < Math.abs(n); i++)
            System.out.print(' ');
        System.out.println(" }");
    }

    public Node eval(Node t, Environment env) {
        // Closure was identified after looking up ident in env so, we want to construct
        // an args Node
        // that we can pass to apply to apply funcs params to its arguments.
        // apply will bind values to param in env and then recursively call eval on each
        // node in the funcs body.
        // t should be a Closure, with car = lambda ... and cadr = env
        return this;
    }

    // The method apply() should be defined in class Node
    // to report an error. It should be overwritten only in classes
    // BuiltIn and Closure.
    public Node apply(Node args) { // args is a closure being passed from eval
        Closure c = (Closure) args.getCar();
        Environment env = new Environment(c.getEnv()); // creates new frame with enclosing env c.getEnv()
        // Now we want to fill up the scope with the functions params
        Node params = c.getFun().getCdr().getCar(); // parameters to apply to the func
        // if params is a symbol, apply list of values to param
        Node values = args.getCdr();
        while (!params.isNull()) {
            // args.cdr = (a1 ... an)
            if (params.isSymbol()) {
                values = new Cons(new Ident("quote"), new Cons(values, Nil.getInstance()));
                env.define(params, values.eval(env));
                break; // rest of values are in params list
            } else {
                if (!values.getCar().getCar().isSymbol())
                    env.define(params.getCar(), values.getCar());
                else
                    env.define(params.getCar(), values.getCar().eval(env));
            }
            params = params.getCdr();
            values = values.getCdr();
        }
        Node body = c.getFun().getCdr().getCdr();
        if (body.isPair() && body.getCdr().isNull())
            body = body.getCar();
        return body.eval(env);
        // (lambda (...) b1 ... bn)
        // c.fun.cdr = ((...) b1 ... bn).cdr = (b1 ... bn) which is the body of the func
    }
}
