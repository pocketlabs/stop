// Generated from /Users/kyleshank/git/stop/StopTemplates.g4 by ANTLR 4.7.2
package org.stop_lang.templates.parser;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class StopTemplatesLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.7.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, STRING_LITERAL=7, NUMBER_LITERAL=8, 
		BOOL_LITERAL=9, TAB=10, NOT=11, DOT=12, NUMBER=13, COMPONENT_TYPE=14, 
		ID=15, REFERENCE=16, SKIP_=17, NEWLINE=18;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "STRING_LITERAL", "NUMBER_LITERAL", 
			"BOOL_LITERAL", "TAB", "NOT", "DOT", "NUMBER", "COMPONENT_TYPE", "ID", 
			"REFERENCE", "UPPERCASE_LETTER", "LOWERCASE_LETTER", "LETTER", "DIGIT", 
			"SPACES", "COMMENT", "LINE_JOINING", "SKIP_", "NEWLINE", "SHORT_STRING", 
			"LONG_STRING", "LONG_STRING_ITEM", "LONG_STRING_CHAR", "STRING_ESCAPE_SEQ"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'('", "','", "')'", "':'", "'['", "']'", null, null, null, "'\t'", 
			"'!'", "'.'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, "STRING_LITERAL", "NUMBER_LITERAL", 
			"BOOL_LITERAL", "TAB", "NOT", "DOT", "NUMBER", "COMPONENT_TYPE", "ID", 
			"REFERENCE", "SKIP_", "NEWLINE"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}


	  // A queue where extra tokens are pushed on (see the NEWLINE lexer rule).
	  private java.util.LinkedList<Token> tokens = new java.util.LinkedList<>();
	  // The stack that keeps track of the indentation level.
	  private java.util.Stack<Integer> indents = new java.util.Stack<>();
	  // The amount of opened braces, brackets and parenthesis.
	  private int opened = 0;
	  // The most recently produced token.
	  private Token lastToken = null;
	  @Override
	  public void emit(Token t) {
	    super.setToken(t);
	    tokens.offer(t);
	  }

	  @Override
	  public Token nextToken() {
	    // Check if the end-of-file is ahead and there are still some DEDENTS expected.
	    if (_input.LA(1) == EOF && !this.indents.isEmpty()) {
	      // Remove any trailing EOF tokens from our buffer.
	      for (int i = tokens.size() - 1; i >= 0; i--) {
	        if (tokens.get(i).getType() == EOF) {
	          tokens.remove(i);
	        }
	      }

	      // First emit an extra line break that serves as the end of the statement.
	      this.emit(commonToken(StopTemplatesParser.NEWLINE, "\n"));

	      // Now emit as much DEDENT tokens as needed.
	      while (!indents.isEmpty()) {
	        this.emit(createDedent());
	        indents.pop();
	      }

	      // Put the EOF back on the token stream.
	      this.emit(commonToken(StopTemplatesParser.EOF, "<EOF>"));
	    }

	    Token next = super.nextToken();

	    if (next.getChannel() == Token.DEFAULT_CHANNEL) {
	      // Keep track of the last token on the default channel.
	      this.lastToken = next;
	    }

	    return tokens.isEmpty() ? next : tokens.poll();
	  }

	  private Token createDedent() {
	    CommonToken dedent = commonToken(StopTemplatesParser.DEDENT, "");
	    dedent.setLine(this.lastToken.getLine());
	    return dedent;
	  }

	  private CommonToken commonToken(int type, String text) {
	    int stop = this.getCharIndex() - 1;
	    int start = text.isEmpty() ? stop : stop - text.length() + 1;
	    return new CommonToken(this._tokenFactorySourcePair, type, DEFAULT_TOKEN_CHANNEL, start, stop);
	  }

	  static int getIndentationCount(String spaces) {
	    int count = 0;
	    for (char ch : spaces.toCharArray()) {
	      switch (ch) {
	        case '\t':
	          count += 4 - (count % 4);
	          break;
	        default:
	          // A normal space char.
	          count++;
	      }
	    }

	    return count;
	  }

	  boolean atStartOfInput() {
	    return super.getCharPositionInLine() == 0 && super.getLine() == 1;
	  }


	public StopTemplatesLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "StopTemplates.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	@Override
	public void action(RuleContext _localctx, int ruleIndex, int actionIndex) {
		switch (ruleIndex) {
		case 24:
			NEWLINE_action((RuleContext)_localctx, actionIndex);
			break;
		}
	}
	private void NEWLINE_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 0:

			     String newLine = getText().replaceAll("[^\r\n\f]+", "");
			     String spaces = getText().replaceAll("[\r\n\f]+", "");
			     int next = _input.LA(1);
			     if (opened > 0 || next == '\r' || next == '\n' || next == '\f' || next == '#') {
			       // If we're inside a list or on a blank line, ignore all indents,
			       // dedents and line breaks.
			       skip();
			     }
			     else {
			       emit(commonToken(NEWLINE, newLine));
			       int indent = getIndentationCount(spaces);
			       int previous = indents.isEmpty() ? 0 : indents.peek();
			       if (indent == previous) {
			         // skip indents of the same size as the present indent-size
			         skip();
			       }
			       else if (((indent-previous) % 4) > 0){
			            emit(commonToken(Token.INVALID_TYPE, spaces));
			       }
			       else if (indent > previous) {
			         int diff = (indent - previous) / 4;
			         for (int i = 0; i < diff; i++){
			         indents.push(indent);
			         emit(commonToken(StopTemplatesParser.INDENT, spaces));
			         }
			       }
			       else {
			         // Possibly emit more than 1 DEDENT token.
			         while(!indents.isEmpty() && indents.peek() > indent) {
			           this.emit(createDedent());
			           indents.pop();
			         }
			       }
			     }
			   
			break;
		}
	}
	@Override
	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 24:
			return NEWLINE_sempred((RuleContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean NEWLINE_sempred(RuleContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return atStartOfInput();
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\24\u0106\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31"+
		"\t\31\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\3\2"+
		"\3\2\3\3\3\3\3\4\3\4\3\5\3\5\3\6\3\6\3\7\3\7\3\b\3\b\5\bN\n\b\3\t\3\t"+
		"\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\5\n[\n\n\3\13\3\13\3\f\3\f\3\r\3"+
		"\r\3\16\5\16d\n\16\3\16\3\16\6\16h\n\16\r\16\16\16i\3\16\6\16m\n\16\r"+
		"\16\16\16n\3\16\3\16\7\16s\n\16\f\16\16\16v\13\16\5\16x\n\16\5\16z\n\16"+
		"\3\17\3\17\3\17\7\17\177\n\17\f\17\16\17\u0082\13\17\3\20\3\20\3\20\7"+
		"\20\u0087\n\20\f\20\16\20\u008a\13\20\3\21\3\21\3\21\3\21\7\21\u0090\n"+
		"\21\f\21\16\21\u0093\13\21\3\22\3\22\3\23\3\23\3\24\3\24\3\25\3\25\3\26"+
		"\6\26\u009e\n\26\r\26\16\26\u009f\3\27\3\27\7\27\u00a4\n\27\f\27\16\27"+
		"\u00a7\13\27\3\30\3\30\5\30\u00ab\n\30\3\30\5\30\u00ae\n\30\3\30\3\30"+
		"\5\30\u00b2\n\30\3\31\3\31\3\31\5\31\u00b7\n\31\3\31\3\31\3\32\3\32\3"+
		"\32\5\32\u00be\n\32\3\32\3\32\5\32\u00c2\n\32\3\32\5\32\u00c5\n\32\5\32"+
		"\u00c7\n\32\3\32\3\32\3\33\3\33\3\33\7\33\u00ce\n\33\f\33\16\33\u00d1"+
		"\13\33\3\33\3\33\3\33\3\33\7\33\u00d7\n\33\f\33\16\33\u00da\13\33\3\33"+
		"\5\33\u00dd\n\33\3\34\3\34\3\34\3\34\3\34\7\34\u00e4\n\34\f\34\16\34\u00e7"+
		"\13\34\3\34\3\34\3\34\3\34\3\34\3\34\3\34\3\34\7\34\u00f1\n\34\f\34\16"+
		"\34\u00f4\13\34\3\34\3\34\3\34\5\34\u00f9\n\34\3\35\3\35\5\35\u00fd\n"+
		"\35\3\36\3\36\3\37\3\37\3\37\3\37\5\37\u0105\n\37\4\u00e5\u00f2\2 \3\3"+
		"\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21"+
		"!\22#\2%\2\'\2)\2+\2-\2/\2\61\23\63\24\65\2\67\29\2;\2=\2\3\2\13\3\2C"+
		"\\\3\2c|\5\2C\\aac|\3\2\62;\4\2\13\13\"\"\4\2\f\f\16\17\6\2\f\f\16\17"+
		"))^^\6\2\f\f\16\17$$^^\3\2^^\2\u011d\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2"+
		"\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23"+
		"\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2"+
		"\2\2\2\37\3\2\2\2\2!\3\2\2\2\2\61\3\2\2\2\2\63\3\2\2\2\3?\3\2\2\2\5A\3"+
		"\2\2\2\7C\3\2\2\2\tE\3\2\2\2\13G\3\2\2\2\rI\3\2\2\2\17M\3\2\2\2\21O\3"+
		"\2\2\2\23Z\3\2\2\2\25\\\3\2\2\2\27^\3\2\2\2\31`\3\2\2\2\33c\3\2\2\2\35"+
		"{\3\2\2\2\37\u0083\3\2\2\2!\u008b\3\2\2\2#\u0094\3\2\2\2%\u0096\3\2\2"+
		"\2\'\u0098\3\2\2\2)\u009a\3\2\2\2+\u009d\3\2\2\2-\u00a1\3\2\2\2/\u00a8"+
		"\3\2\2\2\61\u00b6\3\2\2\2\63\u00c6\3\2\2\2\65\u00dc\3\2\2\2\67\u00f8\3"+
		"\2\2\29\u00fc\3\2\2\2;\u00fe\3\2\2\2=\u0104\3\2\2\2?@\7*\2\2@\4\3\2\2"+
		"\2AB\7.\2\2B\6\3\2\2\2CD\7+\2\2D\b\3\2\2\2EF\7<\2\2F\n\3\2\2\2GH\7]\2"+
		"\2H\f\3\2\2\2IJ\7_\2\2J\16\3\2\2\2KN\5\65\33\2LN\5\67\34\2MK\3\2\2\2M"+
		"L\3\2\2\2N\20\3\2\2\2OP\5\33\16\2P\22\3\2\2\2QR\7v\2\2RS\7t\2\2ST\7w\2"+
		"\2T[\7g\2\2UV\7h\2\2VW\7c\2\2WX\7n\2\2XY\7u\2\2Y[\7g\2\2ZQ\3\2\2\2ZU\3"+
		"\2\2\2[\24\3\2\2\2\\]\7\13\2\2]\26\3\2\2\2^_\7#\2\2_\30\3\2\2\2`a\7\60"+
		"\2\2a\32\3\2\2\2bd\7/\2\2cb\3\2\2\2cd\3\2\2\2dy\3\2\2\2eg\7\60\2\2fh\5"+
		")\25\2gf\3\2\2\2hi\3\2\2\2ig\3\2\2\2ij\3\2\2\2jz\3\2\2\2km\5)\25\2lk\3"+
		"\2\2\2mn\3\2\2\2nl\3\2\2\2no\3\2\2\2ow\3\2\2\2pt\7\60\2\2qs\5)\25\2rq"+
		"\3\2\2\2sv\3\2\2\2tr\3\2\2\2tu\3\2\2\2ux\3\2\2\2vt\3\2\2\2wp\3\2\2\2w"+
		"x\3\2\2\2xz\3\2\2\2ye\3\2\2\2yl\3\2\2\2z\34\3\2\2\2{\u0080\5#\22\2|\177"+
		"\5\'\24\2}\177\5)\25\2~|\3\2\2\2~}\3\2\2\2\177\u0082\3\2\2\2\u0080~\3"+
		"\2\2\2\u0080\u0081\3\2\2\2\u0081\36\3\2\2\2\u0082\u0080\3\2\2\2\u0083"+
		"\u0088\5%\23\2\u0084\u0087\5\'\24\2\u0085\u0087\5)\25\2\u0086\u0084\3"+
		"\2\2\2\u0086\u0085\3\2\2\2\u0087\u008a\3\2\2\2\u0088\u0086\3\2\2\2\u0088"+
		"\u0089\3\2\2\2\u0089 \3\2\2\2\u008a\u0088\3\2\2\2\u008b\u0091\5%\23\2"+
		"\u008c\u0090\5\'\24\2\u008d\u0090\5)\25\2\u008e\u0090\5\31\r\2\u008f\u008c"+
		"\3\2\2\2\u008f\u008d\3\2\2\2\u008f\u008e\3\2\2\2\u0090\u0093\3\2\2\2\u0091"+
		"\u008f\3\2\2\2\u0091\u0092\3\2\2\2\u0092\"\3\2\2\2\u0093\u0091\3\2\2\2"+
		"\u0094\u0095\t\2\2\2\u0095$\3\2\2\2\u0096\u0097\t\3\2\2\u0097&\3\2\2\2"+
		"\u0098\u0099\t\4\2\2\u0099(\3\2\2\2\u009a\u009b\t\5\2\2\u009b*\3\2\2\2"+
		"\u009c\u009e\t\6\2\2\u009d\u009c\3\2\2\2\u009e\u009f\3\2\2\2\u009f\u009d"+
		"\3\2\2\2\u009f\u00a0\3\2\2\2\u00a0,\3\2\2\2\u00a1\u00a5\7%\2\2\u00a2\u00a4"+
		"\n\7\2\2\u00a3\u00a2\3\2\2\2\u00a4\u00a7\3\2\2\2\u00a5\u00a3\3\2\2\2\u00a5"+
		"\u00a6\3\2\2\2\u00a6.\3\2\2\2\u00a7\u00a5\3\2\2\2\u00a8\u00aa\7^\2\2\u00a9"+
		"\u00ab\5+\26\2\u00aa\u00a9\3\2\2\2\u00aa\u00ab\3\2\2\2\u00ab\u00b1\3\2"+
		"\2\2\u00ac\u00ae\7\17\2\2\u00ad\u00ac\3\2\2\2\u00ad\u00ae\3\2\2\2\u00ae"+
		"\u00af\3\2\2\2\u00af\u00b2\7\f\2\2\u00b0\u00b2\4\16\17\2\u00b1\u00ad\3"+
		"\2\2\2\u00b1\u00b0\3\2\2\2\u00b2\60\3\2\2\2\u00b3\u00b7\5+\26\2\u00b4"+
		"\u00b7\5-\27\2\u00b5\u00b7\5/\30\2\u00b6\u00b3\3\2\2\2\u00b6\u00b4\3\2"+
		"\2\2\u00b6\u00b5\3\2\2\2\u00b7\u00b8\3\2\2\2\u00b8\u00b9\b\31\2\2\u00b9"+
		"\62\3\2\2\2\u00ba\u00bb\6\32\2\2\u00bb\u00c7\5+\26\2\u00bc\u00be\7\17"+
		"\2\2\u00bd\u00bc\3\2\2\2\u00bd\u00be\3\2\2\2\u00be\u00bf\3\2\2\2\u00bf"+
		"\u00c2\7\f\2\2\u00c0\u00c2\4\16\17\2\u00c1\u00bd\3\2\2\2\u00c1\u00c0\3"+
		"\2\2\2\u00c2\u00c4\3\2\2\2\u00c3\u00c5\5+\26\2\u00c4\u00c3\3\2\2\2\u00c4"+
		"\u00c5\3\2\2\2\u00c5\u00c7\3\2\2\2\u00c6\u00ba\3\2\2\2\u00c6\u00c1\3\2"+
		"\2\2\u00c7\u00c8\3\2\2\2\u00c8\u00c9\b\32\3\2\u00c9\64\3\2\2\2\u00ca\u00cf"+
		"\7)\2\2\u00cb\u00ce\5=\37\2\u00cc\u00ce\n\b\2\2\u00cd\u00cb\3\2\2\2\u00cd"+
		"\u00cc\3\2\2\2\u00ce\u00d1\3\2\2\2\u00cf\u00cd\3\2\2\2\u00cf\u00d0\3\2"+
		"\2\2\u00d0\u00d2\3\2\2\2\u00d1\u00cf\3\2\2\2\u00d2\u00dd\7)\2\2\u00d3"+
		"\u00d8\7$\2\2\u00d4\u00d7\5=\37\2\u00d5\u00d7\n\t\2\2\u00d6\u00d4\3\2"+
		"\2\2\u00d6\u00d5\3\2\2\2\u00d7\u00da\3\2\2\2\u00d8\u00d6\3\2\2\2\u00d8"+
		"\u00d9\3\2\2\2\u00d9\u00db\3\2\2\2\u00da\u00d8\3\2\2\2\u00db\u00dd\7$"+
		"\2\2\u00dc\u00ca\3\2\2\2\u00dc\u00d3\3\2\2\2\u00dd\66\3\2\2\2\u00de\u00df"+
		"\7)\2\2\u00df\u00e0\7)\2\2\u00e0\u00e1\7)\2\2\u00e1\u00e5\3\2\2\2\u00e2"+
		"\u00e4\59\35\2\u00e3\u00e2\3\2\2\2\u00e4\u00e7\3\2\2\2\u00e5\u00e6\3\2"+
		"\2\2\u00e5\u00e3\3\2\2\2\u00e6\u00e8\3\2\2\2\u00e7\u00e5\3\2\2\2\u00e8"+
		"\u00e9\7)\2\2\u00e9\u00ea\7)\2\2\u00ea\u00f9\7)\2\2\u00eb\u00ec\7$\2\2"+
		"\u00ec\u00ed\7$\2\2\u00ed\u00ee\7$\2\2\u00ee\u00f2\3\2\2\2\u00ef\u00f1"+
		"\59\35\2\u00f0\u00ef\3\2\2\2\u00f1\u00f4\3\2\2\2\u00f2\u00f3\3\2\2\2\u00f2"+
		"\u00f0\3\2\2\2\u00f3\u00f5\3\2\2\2\u00f4\u00f2\3\2\2\2\u00f5\u00f6\7$"+
		"\2\2\u00f6\u00f7\7$\2\2\u00f7\u00f9\7$\2\2\u00f8\u00de\3\2\2\2\u00f8\u00eb"+
		"\3\2\2\2\u00f98\3\2\2\2\u00fa\u00fd\5;\36\2\u00fb\u00fd\5=\37\2\u00fc"+
		"\u00fa\3\2\2\2\u00fc\u00fb\3\2\2\2\u00fd:\3\2\2\2\u00fe\u00ff\n\n\2\2"+
		"\u00ff<\3\2\2\2\u0100\u0101\7^\2\2\u0101\u0105\13\2\2\2\u0102\u0103\7"+
		"^\2\2\u0103\u0105\5\63\32\2\u0104\u0100\3\2\2\2\u0104\u0102\3\2\2\2\u0105"+
		">\3\2\2\2%\2MZcintwy~\u0080\u0086\u0088\u008f\u0091\u009f\u00a5\u00aa"+
		"\u00ad\u00b1\u00b6\u00bd\u00c1\u00c4\u00c6\u00cd\u00cf\u00d6\u00d8\u00dc"+
		"\u00e5\u00f2\u00f8\u00fc\u0104\4\b\2\2\3\32\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}