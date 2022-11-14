// Scheme4101 -- The main program of the Scheme interpreter.

import Parse.Scanner;
import Parse.Parser;
import Tokens.Token;
import Tokens.TokenType;
import Tree.BuiltIn;
import Tree.Cons;
import Tree.Environment;
import Tree.Ident;
import Tree.Nil;
import Tree.Node;
import Tree.StrLit;

public class Scheme4101 {

	private static Environment env = null;

	// private static final String prompt1 = "Scheme4101> ";
	private static final String prompt = "> ";

	// private static final String ini_file = "ini.scm";

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

		env = new Environment();
		BuiltIn.setGlobalEnv(env);
		//
		// populate the environment with BuiltIns and the code from ini.scm

		Node id = new Ident("symbol?");
		env.define(id, new BuiltIn(id));

		id = new Ident("number?");
		env.define(id, new BuiltIn(id));

		id = new Ident("b+");
		env.define(id, new BuiltIn(id));

		id = new Ident("b-");
		env.define(id, new BuiltIn(id));

		id = new Ident("b*");
		env.define(id, new BuiltIn(id));

		id = new Ident("b/");
		env.define(id, new BuiltIn(id));

		id = new Ident("b=");
		env.define(id, new BuiltIn(id));

		id = new Ident("b<");
		env.define(id, new BuiltIn(id));

		id = new Ident("car");
		env.define(id, new BuiltIn(id));

		id = new Ident("cdr");
		env.define(id, new BuiltIn(id));

		id = new Ident("cons");
		env.define(id, new BuiltIn(id));

		id = new Ident("set-car!");
		env.define(id, new BuiltIn(id));

		id = new Ident("set-cdr!");
		env.define(id, new BuiltIn(id));

		id = new Ident("null?");
		env.define(id, new BuiltIn(id));

		id = new Ident("pair?");
		env.define(id, new BuiltIn(id));

		id = new Ident("eq?");
		env.define(id, new BuiltIn(id));

		id = new Ident("procedure?");
		env.define(id, new BuiltIn(id));

		id = new Ident("read");
		env.define(id, new BuiltIn(id));

		id = new Ident("write");
		env.define(id, new BuiltIn(id));

		id = new Ident("display");
		env.define(id, new BuiltIn(id));

		id = new Ident("newline");
		env.define(id, new BuiltIn(id));

		id = new Ident("eval");
		env.define(id, new BuiltIn(id));

		id = new Ident("apply");
		env.define(id, new BuiltIn(id));

		id = new Ident("interaction-environment");
		env.define(id, new BuiltIn(id));

		id = new Ident("load");
		env.define(id, new BuiltIn(id));

		// initial built-ins are defined, now go through ini.scm using load and eval
		// each define
		Cons load = new Cons(new Ident("load"), new Cons(new StrLit("ini.scm"), Nil.getInstance()));
		load.eval(env);

		env = new Environment(env);
		BuiltIn.setGlobalEnv(env);

		// Read-eval-print loop
		System.out.print(prompt);
		root = parser.parseExp();
		while (root != null) {
			root.eval(env).print(0);
			System.out.print(prompt);
			root = parser.parseExp();
		}
		System.exit(0);
	}
}
