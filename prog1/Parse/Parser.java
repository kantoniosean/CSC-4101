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
// => ( exp next
// => ( IDENT next
// => ( IDENT rest
// => ( IDENT exp next
// => ( IDENT IDENT next
// => ( IDENT IDENT rest
// => ( IDENT IDENT exp next
// => ( IDENT IDENT ' exp next
// => ( IDENT IDENT ' ( rest next
// => ( IDENT IDENT ' ( exp next next
// => ( IDENT IDENT ' ( 1 next next
// => ( IDENT IDENT ' ( 1 rest next
// => ( IDENT IDENT ' ( 1 exp next next
// => ( IDENT IDENT ' ( 1 2 next next
// => ( IDENT IDENT ' ( 1 2 rest next
// => ( IDENT IDENT ' ( 1 2 exp next next
// => ( IDENT IDENT ' ( 1 2 3 next next
// => ( IDENT IDENT ' ( 1 2 3 rest next
// => ( IDENT IDENT ' ( 1 2 3 ) next
// => ( IDENT IDENT ' ( 1 2 3 ) rest
// => ( IDENT IDENT ' ( 1 2 3 ) )
// NOT SURE IF ABOVE IS CORRECT

// new Cons(new Ident("quote"), new Cons(parseExp(), new Nil().getInstance()))

package Parse;

import Tokens.Token;
import Tokens.TokenType;
import Tree.Cons;
import Tree.Nil;
import Tree.Node;
import Tree.BooleanLit;
import Tree.Ident;
import Tree.IntLit;
import Tree.StrLit;

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
            return new Cons(BooleanLit.getInstance(false), new Cons(parseRest(), Nil.getInstance()));
            // start of a regular "list", I made the root node look like,
            // (Cons)
            // / \
            // (Bool) (Cons)
            // for now because I'm thinking I can make the bool node false and that tells my
            // Cons node's print method to print a '('
            // i.e. print(n, bool)
            // could also be
            // return new Cons(parseRest(), Nil.getInstance());
        } else if (tt == TokenType.DOT) {
            return new Cons(new Ident("dot"), new Cons(parseExp(), Nil.getInstance())); // dot is found, we want to
                                                                                        // parse next expression and
                                                                                        // then
            // close with ) (car . cdr)
            // . is found, next we parseExp() (the rest of the list) and close the paren
            // (Nil)
            // Cons ---> exp
            // ---> ()
        } else if (tt == TokenType.FALSE) {
            return BooleanLit.getInstance(false);
        } else if (tt == TokenType.TRUE) {
            return BooleanLit.getInstance(true);
        } else if (tt == TokenType.IDENT) {
            return new Ident(tok.getName());
        } else if (tt == TokenType.INT) {
            return new IntLit(tok.getIntVal());
        } else if (tt == TokenType.QUOTE) {
            return new Cons(new Ident("quote"), new Cons(parseExp(), Nil.getInstance()));
        } else if (tt == TokenType.RPAREN) {
            return Nil.getInstance();
        } else if (tt == TokenType.STRING) {
            return new StrLit(tok.getStrVal());
        } else {
            return null;
        }
    }

    protected Node parseRest() {
        // TODO: write code for parsing rest
        // needs to know if token is a closing parentheses, else it is an "exp next"
        // parse
        // returns either a closing paren node (NIL) or parseExp() parseNext()
        // if (RPAREN) return Nil.getInstance();
        // else return new Cons(parseExp(), new Cons(parseNext(), Nil.getInstance()))
        Token tok = scanner.getNextToken();
        TokenType tt = tok.getType();
        if (tt == TokenType.RPAREN) {
            return Nil.getInstance();
        }
        return parseNext(null);
    }

    private Node parseNext(Token tok) {
        return null;
    }

    // TODO: Add any additional methods you might need.
}