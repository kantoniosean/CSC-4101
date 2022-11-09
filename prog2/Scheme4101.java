// Scheme4101 -- The main program of the Scheme interpreter.

import Parse.Scanner;
import Parse.Parser;
import Tokens.Token;
import Tokens.TokenType;
import Tree.BuiltIn;
import Tree.Environment;
import Tree.Ident;
import Tree.Node;

public class Scheme4101 {

	private static Environment env = null;

	private static final String prompt = "Scheme4101> ";
	private static final String prompt1 = "> ";

	private static final String ini_file = "ini.scm";

	public static void main(String argv[]) {

		// Create scanner that reads from standard input
		Scanner scanner = new Scanner(System.in);

		if (argv.length > 1 ||
				(argv.length == 1 && !argv[0].equals("-d"))) {
			System.err.println("Usage: java Scheme4101 [-d]");
			System.exit(1);
		}

		// If command line option -d is provided, debug the scanner
		if (argv.length == 1 && argv[0].equals("-d")) {

			Token tok = scanner.getNextToken();
			while (tok != null) {
				TokenType tt = tok.getType();

				System.out.print(tt.name());
				if (tt == TokenType.INT)
					System.out.println(", intVal = " + tok.getIntVal());
				else if (tt == TokenType.STRING)
					System.out.println(", strVal = " + tok.getStrVal());
				else if (tt == TokenType.IDENT)
					System.out.println(", name = " + tok.getName());
				else
					System.out.println();

				tok = scanner.getNextToken();
			}
			System.exit(0);
		}

		// Create parser
		Parser parser = new Parser(scanner);
		Node root;

		// TODO: Create and populate the built-in environment and
		// create the top-level environment

		env = new Environment();
		BuiltIn.setGlobalEnv(env);
		//
		// populate the environment with BuiltIns and the code from ini.scm

		Node id = new Ident("symbol?");
		env.define(id, new BuiltIn(id));

		Node number = new Ident("number?");
		env.define(id, new BuiltIn(number));

		Node bplus = new Ident("b+");
		env.define(id, new BuiltIn(bplus));

		Node bminus = new Ident("b-");
		env.define(id, new BuiltIn(bminus));

		Node bmult = new Ident("b*");
		env.define(id, new BuiltIn(bmult));

		Node bdiv = new Ident("b/");
		env.define(id, new BuiltIn(bdiv));

		Node bequ = new Ident("b=");
		env.define(id, new BuiltIn(bequ));

		Node bless = new Ident("b<");
		env.define(id, new BuiltIn(bless));

		Node car = new Ident("car");
		env.define(id, new BuiltIn(car));

		Node cdr = new Ident("cdr");
		env.define(id, new BuiltIn(cdr));

		Node cons = new Ident("cons");
		env.define(id, new BuiltIn(cons));

		Node setCar = new Ident("set-car!");
		env.define(id, new BuiltIn(setCar));

		Node setCdr = new Ident("set-cdr!");
		env.define(id, new BuiltIn(setCdr));

		Node Null = new Ident("null?");
		env.define(id, new BuiltIn(Null));

		Node pair = new Ident("pair?");
		env.define(id, new BuiltIn(pair));

		Node eq = new Ident("eq?");
		env.define(id, new BuiltIn(eq));

		Node procedure = new Ident("procedure?");
		env.define(id, new BuiltIn(procedure));

		Node read = new Ident("read");
		env.define(id, new BuiltIn(read));

		Node write = new Ident("write");
		env.define(id, new BuiltIn(write));

		Node disp = new Ident("display");
		env.define(id, new BuiltIn(disp));

		Node newLine = new Ident("newline");
		env.define(id, new BuiltIn(newLine));

		Node eval = new Ident("eval");
		env.define(id, new BuiltIn(eval));

		Node apply = new Ident("apply");
		env.define(id, new BuiltIn(apply));

		Node interactEnv = new Ident("interaction-environment");
		env.define(id, new BuiltIn(interactEnv));

		Node load = new Ident("load");
		env.define(id, new BuiltIn(load));

		/*
		 * BuiltIn's to add before scanning ini.scm
		 * symbol?
		 * number?
		 * b+, b-, etc.
		 * car, cdr, set-car!, set-cdr!, null?, cons, pair?, eq?
		 * procedure?
		 * read/write/display/newline
		 * eval, apply, interaction-environment
		 * load
		 */

		// apply() takes "Node args" as an argument (which would be a Cons node)
		// and the car or first argument should be a closure that contains the enclosing
		// environment and
		// a lambda expression if needed.
		// BuiltIn.apply(Closure, ...)

		env = new Environment(env);
		BuiltIn.setGlobalEnv(env);

		// Read-eval-print loop

		// TODO: print prompt and evaluate the expression
		root = parser.parseExp();
		while (root != null) {
			root.print(0);
			root = parser.parseExp();
		}
		System.exit(0);
	}
}
