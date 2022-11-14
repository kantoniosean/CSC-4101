// IntLit -- Parse tree node class for representing integer literals

package Tree;

import Print.Printer;

public class IntLit extends Node {
	private int intVal;

	public IntLit(int i) {
		intVal = i;
	}

	public void print(int n) {
		Printer.printIntLit(n, intVal);
	}

	public int getIntVal() {
		return intVal;
	}

	public boolean isNumber() {
		return true;
	}

	public Node eval(Environment env) {
		return this;
	}
}
