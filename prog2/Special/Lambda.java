// Lambda -- Parse tree node strategy for printing the special form lambda

package Special;

import Tree.Closure;
import Tree.Environment;
import Tree.Node;
import Print.Printer;

public class Lambda extends Special {

	public void print(Node t, int n, boolean p) {
		Printer.printLambda(t, n, p);
	}

	public Node eval(Node t, Environment env) {
		System.out.println("Lambda");
		return new Closure(t, env);
	}
}
