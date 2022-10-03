// Parser -- the parser for the Scheme printer and interpreter
//
// Defines
//
//   class Parser;
//
// Parses the language
//
//   exp  ->  ( rest
//         |  #f
//         |  #t
//         |  ' exp
//         |  integer_constant
//         |  string_constant
//         |  identifier
//    rest -> )
//         |  exp next
//    next -> rest
//         |  . exp )
//
// and builds a parse tree.  Lists of the form (rest) are further
// `parsed' into regular lists and special forms in the constructor
// for the parse tree node class Cons.  See Cons.parseList() for
// more information.
//
// The parser is implemented as an LL(0) recursive descent parser.
// I.e., parseExp() expects that the first token of an exp has not
// been read yet.  If parseRest() reads the first token of an exp
// before calling parseExp(), that token must be put back so that
// it can be reread by parseExp() or an alternative version of
// parseExp() must be called.
//
// If EOF is reached (i.e., if the scanner returns a NULL) token,
// the parser returns a NULL tree.  In case of a parse error, the
// parser discards the offending token (which probably was a DOT
// or an RPAREN) and attempts to continue parsing with the next token.

// (define x '(1 2 3))
// exp => ( rest
// => ( exp )
// => ( define )

package Parse;

import Tokens.Token;
import Tokens.TokenType;
import Tree.Node;

public class Parser {
    private Scanner scanner;

    public Parser(Scanner s) {
        scanner = s;
    }

    public Node parseExp() { // does not have lookahead
        // TODO: write code for parsing an exp
        return parseExp(scanner.getNextToken());
    }

    private Node parseExp(Token tok) { // has lookahead (tok argument is lookahead)
        TokenType tt = tok.getType();
        if (tt == TokenType.LPAREN) {
            // return
        }
        return null;
    }

    protected Node parseRest() {
        // TODO: write code for parsing rest
        return null;
    }

    private Node parseNext(Token tok) {
        return null;
    }

    // TODO: Add any additional methods you might need.
}