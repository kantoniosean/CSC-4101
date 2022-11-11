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

    // The method apply() should be defined in class Node
    // to report an error. It should be overwritten only in classes
    // BuiltIn and Closure.
    public Node apply(Node args) {
        Closure c = (Closure) args.getCar();
        // should I use a try catch clause here in case the first arg was not a closure?
        Environment env = new Environment(c.getEnv()); // creates new frame with enclosing env c.getEnv()
        // Now we want to fill up the scope with the functions params
        Node params = args.getCdr(); // parameters to apply to the func
        Node id = null; // id to lookup in environment
        Node val = null; // value gained from id
        while (!params.isNull()) { // binds the parameters to the actual variable values in the enclosing env
            // car should be a param variable and we want to look for it in the surrounding
            // environments
            id = params.getCar();
            val = env.lookup(id);
            env.define(id, val); // if id was found, val was changed so we want to add that value to the user
                                 // func env
            params = params.getCdr();
        }

        Node func = c.getFun();
        while (!func.isNull()) { // func should be a cons node here with special form of lambda.

        }
        return eval(env);
    }
}
