// Define -- Parse tree node strategy for printing the special form define

package Special;

import Tree.Closure;
import Tree.Cons;
import Tree.Environment;
import Tree.Ident;
import Tree.Node;
import Print.Printer;

public class Define extends Special {

	public void print(Node t, int n, boolean p) {
		Printer.printDefine(t, n, p);
	}

	public Node eval(Node n, Environment env) {
		// (define x ...) or (define (foo x) ...)
		Node func = null;
		if (n.getCdr().getCar().isPair()) { // if variable being defined is a list (function)
			// (define (foo x) ...) can be redefined as (define x (lambda (params) ...))
			// so store func name (id) and value will be a lambda expression (cons node with
			// special form lambda)
			// in Java, we want to store this as a closure with the function name as the id
			func = n.getCdr().getCar(); // (foo x)
			// val = closure(lambda, env)
			// (lambda (x1 ... xn) y1 ... yn)
			// Say we input:
			// > (define (foo x y) (set! x 3) (+ x y)) = Node n
			// n.cadr = (foo x y), car is id, cdr is args (x y)
			// n.cadr.cdr = arguments to evaluate = (y1 ... yn)
			Node args = func.getCdr();
			Cons lambda = new Cons(new Ident("lambda"), new Cons(args, n.getCdr().getCdr()));
			Closure f = new Closure(lambda, env);
			env.define(func.getCar(), f);
			return f;
		} else { // define new variable in given env
			Node id = n.getCdr().getCar();
			env.define(id, n.getCdr().getCdr().getCar()); // define takes care of looking at scope
			return null;
		}
	}
}
