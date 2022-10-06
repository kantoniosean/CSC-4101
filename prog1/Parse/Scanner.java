// Scanner -- The lexical analyzer for the Scheme printer and interpreter.

package Parse;

import java.io.IOException;
import java.io.InputStream;
import java.io.PushbackInputStream;

import Tokens.Token;
import Tokens.TokenType;
import Tokens.IdentToken;
import Tokens.IntToken;
import Tokens.StrToken;

public class Scanner {
	private PushbackInputStream in;

	// Maximum length of strings and identifers
	private int BUFSIZE = 1000;
	private byte[] buf = new byte[BUFSIZE];

	public Scanner(InputStream i) {
		in = new PushbackInputStream(i);
	}

	public Token getNextToken() {
		int ch;
		buf = new byte[BUFSIZE];

		try {
			// It would be more efficient if we'd maintain our own
			// input buffer and read characters out of that
			// buffer, but reading individual characters from the
			// input stream is easier.
			ch = in.read();

			if (ch == ' ' || ch == '\t' || ch == '\r' || ch == '\f' || ch == '\n') {
				// ignore
				return getNextToken();
			} else if (ch == ';') { // comment, skip everything until new line.
				while (ch != '\n') {
					if (ch == -1)
						return null;
					ch = in.read();
				}
				return getNextToken();
			}

			// Return null on EOF
			if (ch == -1)
				return null;

			// Special characters
			else if (ch == '\'')
				return new Token(TokenType.QUOTE);
			else if (ch == '(')
				return new Token(TokenType.LPAREN);
			else if (ch == ')')
				return new Token(TokenType.RPAREN);
			else if (ch == '.')
				// We ignore the special identifier `...'.
				return new Token(TokenType.DOT);

			// Boolean constants
			else if (ch == '#') {
				ch = in.read();

				if (ch == 't')
					return new Token(TokenType.TRUE);
				else if (ch == 'f')
					return new Token(TokenType.FALSE);
				else if (ch == -1) {
					System.err.println("Unexpected EOF following #");
					return null;
				} else {
					System.err.println("Illegal character '" +
							(char) ch + "' following #");
					return getNextToken();
				}
			}
			// String constants
			else if (ch == '"') {
				int i = 0;
				ch = in.read();
				while (ch != '"') {
					if (ch == -1)
						return null;
					buf[i++] = (byte) ch;
					ch = in.read();
				}
				return new StrToken(new String(buf));
			}

			// Integer constants
			else if (ch >= '0' && ch <= '9') {
				int i = ch - '0';
				ch = in.read();
				while (ch >= '0' && ch <= '9') {
					if (ch == -1)
						return null;
					int j = ch - '0';
					i = (i * 10) + j;
					ch = in.read();
				}
				// Put the character after the integer back into the input
				in.unread(ch);
				return new IntToken(i);
			}

			// Identifiers
			else if (isIdent(ch)
			/*
			 * or ch is some other valid first character for an identifier
			 * + - * / . < = > ! ? : $ % _ & ~ ^
			 */) {
				// Put the character after the identifier back into the input
				// in.unread(ch);
				int i = 0;
				while (isIdent(ch)) {
					if (ch == -1)
						return null;
					buf[i++] = (byte) ch;
					ch = in.read();
				}
				in.unread(ch);
				return new IdentToken(new String(buf));
			}

			// Illegal character
			else {
				System.err.println("Illegal input character '" + (char) ch + '\'');
				return getNextToken();
			}
		} catch (IOException e) {
			System.err.println("IOException: " + e.getMessage());
			return null;
		}
	}

	private boolean isIdent(int ch) {
		return (ch >= 'A' && ch <= 'Z') || ch == '!'
				|| (ch >= '$' && ch <= '&') || (ch >= '+' && ch <= '/' && ch != 44)
				|| (ch >= ':' && ch <= '?' && ch != ';') || ch == '~'
				|| ch == '_' || ch == '^' || (ch >= 'a' && ch <= 'z');
	}
}
