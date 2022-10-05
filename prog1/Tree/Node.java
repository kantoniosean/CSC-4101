// Node -- Super class for parse tree node objects

package Tree;

import Tokens.Token;
import Tokens.TokenType;
import Tree.Cons;
import Tree.Nil;
import Tree.Node;
import Tree.BooleanLit;
import Tree.Ident;
import Tree.IntLit;
import Tree.StrLit;

public class Node {
    // The argument of print(int) is the number of characters to indent.
    // Every subclass of Node must implement print(int).
    public void print(int n) {
    }

    // The first argument of print(int, boolean) is the number of characters
    // to indent. It is interpreted the same as for print(int).
    // The second argument is only useful for lists (nodes of classes
    // Cons or Nil). For all other subclasses of Node, the boolean
    // argument is ignored. Therefore, print(n,p) defaults to print(n)
    // for all classes other than Cons and Nil.
    // For classes Cons and Nil, print(n,TRUE) means that the open
    // parenthesis was printed already by the caller.
    // Only classes Cons and Nil override print(int,boolean).
    // For correctly indenting special forms, you might need to pass
    // additional information to print. What additional information
    // you pass is up to you. If you only need one more bit, you can
    // encode that in the sign bit of n. If you need additional parameters,
    // make sure that you define the method print in all the appropriate
    // subclasses of Node as well.
    public void print(int n, boolean p) {
        print(n);
    }

    // For parsing Cons nodes, for printing trees, and later for
    // evaluating them, we need some helper functions that test
    // the type of a node and that extract some information.

    // TODO: implement these in the appropriate subclasses to return true.
    public boolean isBoolean(Token k) {
        String bool = k.getStrVal();
        if(bool == "?" || bool == "equ" || bool == "equ?") {
            return true;
        }
        return false;
    }

    public boolean isNumber(Token i) {
        String num = i.getStrVal();
        for(int j = 0; j < num.length(); j++) {
            if(Character.isDigit(num.charAt(j)) == true) {
                return true;
            }
        }
        return false;
    }

    public boolean isString(Token t) {
        String str = t.getStrVal();
        if(str.length() > 1) {
            return true;
        }
        
        return false;
    }

    public boolean isSymbol(Token f) {
        String symbol = f.getStrVal();
        if(symbol == "'") {           //neeed to look up other scheme symbols
            return true;
        }
        return false;
    }

    public boolean isNull(Token n) {
        if (n == null) {
            return true;
        }
        return false;
    }

    public boolean isPair() {

        return false;
    }

    // TODO: Report an error in these default methods and implement them
    // in class Cons. After setCar, a Cons cell needs to be `parsed' again
    // using parseList.

    public Node getCar() {
        return null;
    }

    public Node getCdr() {
        return null;
    }

    public void setCar(Node a) {
    }

    public void setCdr(Node d) {
    }
}