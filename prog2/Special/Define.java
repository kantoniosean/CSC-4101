// Define -- Parse tree node strategy for printing the special form define

package Special;

import Tree.Environment;
import Tree.Node;
import Print.Printer;

public class Define extends Special {

	public void print(Node t, int n, boolean p) {
		Printer.printDefine(t, n, p);
	}

	public Node eval(Node n, Environment env) {
		// (define x ...) or (define (foo x) ...)
		if (n.getCdr().getCar().isPair()) { // if variable being defined is a list (function)
			// apply param to func and create a new frame
		} else { // define new variable in given env

		}
		return null;
	}
}
