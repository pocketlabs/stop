// Generated from /Users/kyleshank/git/stop/Stop.g4 by ANTLR 4.7.2
package org.stop_lang.parser;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class StopLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.7.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, T__16=17, 
		T__17=18, T__18=19, T__19=20, T__20=21, T__21=22, T__22=23, T__23=24, 
		T__24=25, T__25=26, T__26=27, T__27=28, T__28=29, T__29=30, ASYNC=31, 
		START=32, STOP=33, QUEUE=34, TRANSITION_OP=35, RETURN_OP=36, ENQUEUE_OP=37, 
		OPTIONAL=38, DOT=39, NUMBER=40, MODEL_TYPE=41, ID=42, FILENAME=43, COMMENT=44, 
		LINE_COMMENT=45, WS=46;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "T__7", "T__8", 
			"T__9", "T__10", "T__11", "T__12", "T__13", "T__14", "T__15", "T__16", 
			"T__17", "T__18", "T__19", "T__20", "T__21", "T__22", "T__23", "T__24", 
			"T__25", "T__26", "T__27", "T__28", "T__29", "ASYNC", "START", "STOP", 
			"QUEUE", "TRANSITION_OP", "RETURN_OP", "ENQUEUE_OP", "OPTIONAL", "DOT", 
			"NUMBER", "MODEL_TYPE", "ID", "FILENAME", "UPPERCASE_LETTER", "LOWERCASE_LETTER", 
			"LETTER", "DIGIT", "COMMENT", "LINE_COMMENT", "WS"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'package'", "'include'", "'{'", "';'", "'}'", "'double'", "'float'", 
			"'int32'", "'int64'", "'uint32'", "'uint64'", "'sint32'", "'sint64'", 
			"'fixed32'", "'fixed64'", "'sfixed32'", "'sfixed64'", "'bool'", "'string'", 
			"'bytes'", "'timestamp'", "'enum'", "'('", "','", "')'", "':'", "'['", 
			"']'", "'throws'", "'timeout'", "'async'", "'start'", "'stop'", "'queue'", 
			"'->'", "'<-'", "'>>'", "'optional'", "'.'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, "ASYNC", "START", "STOP", "QUEUE", 
			"TRANSITION_OP", "RETURN_OP", "ENQUEUE_OP", "OPTIONAL", "DOT", "NUMBER", 
			"MODEL_TYPE", "ID", "FILENAME", "COMMENT", "LINE_COMMENT", "WS"
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


	public StopLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "Stop.g4"; }

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

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\60\u019c\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31"+
		"\t\31\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t"+
		" \4!\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t"+
		"+\4,\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\4\63\t\63\3\2"+
		"\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\4\3\4\3"+
		"\5\3\5\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3\b\3\b\3\t"+
		"\3\t\3\t\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\n\3\13\3\13\3\13\3\13\3\13"+
		"\3\13\3\13\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\16"+
		"\3\16\3\16\3\16\3\16\3\16\3\16\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17"+
		"\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\21\3\21\3\21\3\21\3\21\3\21"+
		"\3\21\3\21\3\21\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\23\3\23"+
		"\3\23\3\23\3\23\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\25\3\25\3\25\3\25"+
		"\3\25\3\25\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\27\3\27"+
		"\3\27\3\27\3\27\3\30\3\30\3\31\3\31\3\32\3\32\3\33\3\33\3\34\3\34\3\35"+
		"\3\35\3\36\3\36\3\36\3\36\3\36\3\36\3\36\3\37\3\37\3\37\3\37\3\37\3\37"+
		"\3\37\3\37\3 \3 \3 \3 \3 \3 \3!\3!\3!\3!\3!\3!\3\"\3\"\3\"\3\"\3\"\3#"+
		"\3#\3#\3#\3#\3#\3$\3$\3$\3%\3%\3%\3&\3&\3&\3\'\3\'\3\'\3\'\3\'\3\'\3\'"+
		"\3\'\3\'\3(\3(\3)\5)\u013d\n)\3)\3)\6)\u0141\n)\r)\16)\u0142\3)\6)\u0146"+
		"\n)\r)\16)\u0147\3)\3)\7)\u014c\n)\f)\16)\u014f\13)\5)\u0151\n)\5)\u0153"+
		"\n)\3*\3*\3*\7*\u0158\n*\f*\16*\u015b\13*\3+\3+\3+\7+\u0160\n+\f+\16+"+
		"\u0163\13+\3,\3,\3,\3,\3,\6,\u016a\n,\r,\16,\u016b\3,\3,\3-\3-\3.\3.\3"+
		"/\3/\3\60\3\60\3\61\3\61\3\61\3\61\7\61\u017c\n\61\f\61\16\61\u017f\13"+
		"\61\3\61\3\61\3\61\3\61\3\61\3\62\3\62\3\62\3\62\7\62\u018a\n\62\f\62"+
		"\16\62\u018d\13\62\3\62\5\62\u0190\n\62\3\62\3\62\3\62\3\62\3\63\6\63"+
		"\u0197\n\63\r\63\16\63\u0198\3\63\3\63\4\u017d\u018b\2\64\3\3\5\4\7\5"+
		"\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21!\22#\23"+
		"%\24\'\25)\26+\27-\30/\31\61\32\63\33\65\34\67\359\36;\37= ?!A\"C#E$G"+
		"%I&K\'M(O)Q*S+U,W-Y\2[\2]\2_\2a.c/e\60\3\2\b\6\2//\61\61^^aa\3\2C\\\3"+
		"\2c|\5\2C\\aac|\3\2\62;\5\2\13\f\17\17\"\"\2\u01a9\2\3\3\2\2\2\2\5\3\2"+
		"\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21"+
		"\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2"+
		"\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3"+
		"\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\2\63\3"+
		"\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2\29\3\2\2\2\2;\3\2\2\2\2=\3\2\2\2\2?\3"+
		"\2\2\2\2A\3\2\2\2\2C\3\2\2\2\2E\3\2\2\2\2G\3\2\2\2\2I\3\2\2\2\2K\3\2\2"+
		"\2\2M\3\2\2\2\2O\3\2\2\2\2Q\3\2\2\2\2S\3\2\2\2\2U\3\2\2\2\2W\3\2\2\2\2"+
		"a\3\2\2\2\2c\3\2\2\2\2e\3\2\2\2\3g\3\2\2\2\5o\3\2\2\2\7w\3\2\2\2\ty\3"+
		"\2\2\2\13{\3\2\2\2\r}\3\2\2\2\17\u0084\3\2\2\2\21\u008a\3\2\2\2\23\u0090"+
		"\3\2\2\2\25\u0096\3\2\2\2\27\u009d\3\2\2\2\31\u00a4\3\2\2\2\33\u00ab\3"+
		"\2\2\2\35\u00b2\3\2\2\2\37\u00ba\3\2\2\2!\u00c2\3\2\2\2#\u00cb\3\2\2\2"+
		"%\u00d4\3\2\2\2\'\u00d9\3\2\2\2)\u00e0\3\2\2\2+\u00e6\3\2\2\2-\u00f0\3"+
		"\2\2\2/\u00f5\3\2\2\2\61\u00f7\3\2\2\2\63\u00f9\3\2\2\2\65\u00fb\3\2\2"+
		"\2\67\u00fd\3\2\2\29\u00ff\3\2\2\2;\u0101\3\2\2\2=\u0108\3\2\2\2?\u0110"+
		"\3\2\2\2A\u0116\3\2\2\2C\u011c\3\2\2\2E\u0121\3\2\2\2G\u0127\3\2\2\2I"+
		"\u012a\3\2\2\2K\u012d\3\2\2\2M\u0130\3\2\2\2O\u0139\3\2\2\2Q\u013c\3\2"+
		"\2\2S\u0154\3\2\2\2U\u015c\3\2\2\2W\u0164\3\2\2\2Y\u016f\3\2\2\2[\u0171"+
		"\3\2\2\2]\u0173\3\2\2\2_\u0175\3\2\2\2a\u0177\3\2\2\2c\u0185\3\2\2\2e"+
		"\u0196\3\2\2\2gh\7r\2\2hi\7c\2\2ij\7e\2\2jk\7m\2\2kl\7c\2\2lm\7i\2\2m"+
		"n\7g\2\2n\4\3\2\2\2op\7k\2\2pq\7p\2\2qr\7e\2\2rs\7n\2\2st\7w\2\2tu\7f"+
		"\2\2uv\7g\2\2v\6\3\2\2\2wx\7}\2\2x\b\3\2\2\2yz\7=\2\2z\n\3\2\2\2{|\7\177"+
		"\2\2|\f\3\2\2\2}~\7f\2\2~\177\7q\2\2\177\u0080\7w\2\2\u0080\u0081\7d\2"+
		"\2\u0081\u0082\7n\2\2\u0082\u0083\7g\2\2\u0083\16\3\2\2\2\u0084\u0085"+
		"\7h\2\2\u0085\u0086\7n\2\2\u0086\u0087\7q\2\2\u0087\u0088\7c\2\2\u0088"+
		"\u0089\7v\2\2\u0089\20\3\2\2\2\u008a\u008b\7k\2\2\u008b\u008c\7p\2\2\u008c"+
		"\u008d\7v\2\2\u008d\u008e\7\65\2\2\u008e\u008f\7\64\2\2\u008f\22\3\2\2"+
		"\2\u0090\u0091\7k\2\2\u0091\u0092\7p\2\2\u0092\u0093\7v\2\2\u0093\u0094"+
		"\78\2\2\u0094\u0095\7\66\2\2\u0095\24\3\2\2\2\u0096\u0097\7w\2\2\u0097"+
		"\u0098\7k\2\2\u0098\u0099\7p\2\2\u0099\u009a\7v\2\2\u009a\u009b\7\65\2"+
		"\2\u009b\u009c\7\64\2\2\u009c\26\3\2\2\2\u009d\u009e\7w\2\2\u009e\u009f"+
		"\7k\2\2\u009f\u00a0\7p\2\2\u00a0\u00a1\7v\2\2\u00a1\u00a2\78\2\2\u00a2"+
		"\u00a3\7\66\2\2\u00a3\30\3\2\2\2\u00a4\u00a5\7u\2\2\u00a5\u00a6\7k\2\2"+
		"\u00a6\u00a7\7p\2\2\u00a7\u00a8\7v\2\2\u00a8\u00a9\7\65\2\2\u00a9\u00aa"+
		"\7\64\2\2\u00aa\32\3\2\2\2\u00ab\u00ac\7u\2\2\u00ac\u00ad\7k\2\2\u00ad"+
		"\u00ae\7p\2\2\u00ae\u00af\7v\2\2\u00af\u00b0\78\2\2\u00b0\u00b1\7\66\2"+
		"\2\u00b1\34\3\2\2\2\u00b2\u00b3\7h\2\2\u00b3\u00b4\7k\2\2\u00b4\u00b5"+
		"\7z\2\2\u00b5\u00b6\7g\2\2\u00b6\u00b7\7f\2\2\u00b7\u00b8\7\65\2\2\u00b8"+
		"\u00b9\7\64\2\2\u00b9\36\3\2\2\2\u00ba\u00bb\7h\2\2\u00bb\u00bc\7k\2\2"+
		"\u00bc\u00bd\7z\2\2\u00bd\u00be\7g\2\2\u00be\u00bf\7f\2\2\u00bf\u00c0"+
		"\78\2\2\u00c0\u00c1\7\66\2\2\u00c1 \3\2\2\2\u00c2\u00c3\7u\2\2\u00c3\u00c4"+
		"\7h\2\2\u00c4\u00c5\7k\2\2\u00c5\u00c6\7z\2\2\u00c6\u00c7\7g\2\2\u00c7"+
		"\u00c8\7f\2\2\u00c8\u00c9\7\65\2\2\u00c9\u00ca\7\64\2\2\u00ca\"\3\2\2"+
		"\2\u00cb\u00cc\7u\2\2\u00cc\u00cd\7h\2\2\u00cd\u00ce\7k\2\2\u00ce\u00cf"+
		"\7z\2\2\u00cf\u00d0\7g\2\2\u00d0\u00d1\7f\2\2\u00d1\u00d2\78\2\2\u00d2"+
		"\u00d3\7\66\2\2\u00d3$\3\2\2\2\u00d4\u00d5\7d\2\2\u00d5\u00d6\7q\2\2\u00d6"+
		"\u00d7\7q\2\2\u00d7\u00d8\7n\2\2\u00d8&\3\2\2\2\u00d9\u00da\7u\2\2\u00da"+
		"\u00db\7v\2\2\u00db\u00dc\7t\2\2\u00dc\u00dd\7k\2\2\u00dd\u00de\7p\2\2"+
		"\u00de\u00df\7i\2\2\u00df(\3\2\2\2\u00e0\u00e1\7d\2\2\u00e1\u00e2\7{\2"+
		"\2\u00e2\u00e3\7v\2\2\u00e3\u00e4\7g\2\2\u00e4\u00e5\7u\2\2\u00e5*\3\2"+
		"\2\2\u00e6\u00e7\7v\2\2\u00e7\u00e8\7k\2\2\u00e8\u00e9\7o\2\2\u00e9\u00ea"+
		"\7g\2\2\u00ea\u00eb\7u\2\2\u00eb\u00ec\7v\2\2\u00ec\u00ed\7c\2\2\u00ed"+
		"\u00ee\7o\2\2\u00ee\u00ef\7r\2\2\u00ef,\3\2\2\2\u00f0\u00f1\7g\2\2\u00f1"+
		"\u00f2\7p\2\2\u00f2\u00f3\7w\2\2\u00f3\u00f4\7o\2\2\u00f4.\3\2\2\2\u00f5"+
		"\u00f6\7*\2\2\u00f6\60\3\2\2\2\u00f7\u00f8\7.\2\2\u00f8\62\3\2\2\2\u00f9"+
		"\u00fa\7+\2\2\u00fa\64\3\2\2\2\u00fb\u00fc\7<\2\2\u00fc\66\3\2\2\2\u00fd"+
		"\u00fe\7]\2\2\u00fe8\3\2\2\2\u00ff\u0100\7_\2\2\u0100:\3\2\2\2\u0101\u0102"+
		"\7v\2\2\u0102\u0103\7j\2\2\u0103\u0104\7t\2\2\u0104\u0105\7q\2\2\u0105"+
		"\u0106\7y\2\2\u0106\u0107\7u\2\2\u0107<\3\2\2\2\u0108\u0109\7v\2\2\u0109"+
		"\u010a\7k\2\2\u010a\u010b\7o\2\2\u010b\u010c\7g\2\2\u010c\u010d\7q\2\2"+
		"\u010d\u010e\7w\2\2\u010e\u010f\7v\2\2\u010f>\3\2\2\2\u0110\u0111\7c\2"+
		"\2\u0111\u0112\7u\2\2\u0112\u0113\7{\2\2\u0113\u0114\7p\2\2\u0114\u0115"+
		"\7e\2\2\u0115@\3\2\2\2\u0116\u0117\7u\2\2\u0117\u0118\7v\2\2\u0118\u0119"+
		"\7c\2\2\u0119\u011a\7t\2\2\u011a\u011b\7v\2\2\u011bB\3\2\2\2\u011c\u011d"+
		"\7u\2\2\u011d\u011e\7v\2\2\u011e\u011f\7q\2\2\u011f\u0120\7r\2\2\u0120"+
		"D\3\2\2\2\u0121\u0122\7s\2\2\u0122\u0123\7w\2\2\u0123\u0124\7g\2\2\u0124"+
		"\u0125\7w\2\2\u0125\u0126\7g\2\2\u0126F\3\2\2\2\u0127\u0128\7/\2\2\u0128"+
		"\u0129\7@\2\2\u0129H\3\2\2\2\u012a\u012b\7>\2\2\u012b\u012c\7/\2\2\u012c"+
		"J\3\2\2\2\u012d\u012e\7@\2\2\u012e\u012f\7@\2\2\u012fL\3\2\2\2\u0130\u0131"+
		"\7q\2\2\u0131\u0132\7r\2\2\u0132\u0133\7v\2\2\u0133\u0134\7k\2\2\u0134"+
		"\u0135\7q\2\2\u0135\u0136\7p\2\2\u0136\u0137\7c\2\2\u0137\u0138\7n\2\2"+
		"\u0138N\3\2\2\2\u0139\u013a\7\60\2\2\u013aP\3\2\2\2\u013b\u013d\7/\2\2"+
		"\u013c\u013b\3\2\2\2\u013c\u013d\3\2\2\2\u013d\u0152\3\2\2\2\u013e\u0140"+
		"\5O(\2\u013f\u0141\5_\60\2\u0140\u013f\3\2\2\2\u0141\u0142\3\2\2\2\u0142"+
		"\u0140\3\2\2\2\u0142\u0143\3\2\2\2\u0143\u0153\3\2\2\2\u0144\u0146\5_"+
		"\60\2\u0145\u0144\3\2\2\2\u0146\u0147\3\2\2\2\u0147\u0145\3\2\2\2\u0147"+
		"\u0148\3\2\2\2\u0148\u0150\3\2\2\2\u0149\u014d\5O(\2\u014a\u014c\5_\60"+
		"\2\u014b\u014a\3\2\2\2\u014c\u014f\3\2\2\2\u014d\u014b\3\2\2\2\u014d\u014e"+
		"\3\2\2\2\u014e\u0151\3\2\2\2\u014f\u014d\3\2\2\2\u0150\u0149\3\2\2\2\u0150"+
		"\u0151\3\2\2\2\u0151\u0153\3\2\2\2\u0152\u013e\3\2\2\2\u0152\u0145\3\2"+
		"\2\2\u0153R\3\2\2\2\u0154\u0159\5Y-\2\u0155\u0158\5]/\2\u0156\u0158\5"+
		"_\60\2\u0157\u0155\3\2\2\2\u0157\u0156\3\2\2\2\u0158\u015b\3\2\2\2\u0159"+
		"\u0157\3\2\2\2\u0159\u015a\3\2\2\2\u015aT\3\2\2\2\u015b\u0159\3\2\2\2"+
		"\u015c\u0161\5[.\2\u015d\u0160\5]/\2\u015e\u0160\5_\60\2\u015f\u015d\3"+
		"\2\2\2\u015f\u015e\3\2\2\2\u0160\u0163\3\2\2\2\u0161\u015f\3\2\2\2\u0161"+
		"\u0162\3\2\2\2\u0162V\3\2\2\2\u0163\u0161\3\2\2\2\u0164\u0169\7$\2\2\u0165"+
		"\u016a\5]/\2\u0166\u016a\5_\60\2\u0167\u016a\5O(\2\u0168\u016a\t\2\2\2"+
		"\u0169\u0165\3\2\2\2\u0169\u0166\3\2\2\2\u0169\u0167\3\2\2\2\u0169\u0168"+
		"\3\2\2\2\u016a\u016b\3\2\2\2\u016b\u0169\3\2\2\2\u016b\u016c\3\2\2\2\u016c"+
		"\u016d\3\2\2\2\u016d\u016e\7$\2\2\u016eX\3\2\2\2\u016f\u0170\t\3\2\2\u0170"+
		"Z\3\2\2\2\u0171\u0172\t\4\2\2\u0172\\\3\2\2\2\u0173\u0174\t\5\2\2\u0174"+
		"^\3\2\2\2\u0175\u0176\t\6\2\2\u0176`\3\2\2\2\u0177\u0178\7\61\2\2\u0178"+
		"\u0179\7,\2\2\u0179\u017d\3\2\2\2\u017a\u017c\13\2\2\2\u017b\u017a\3\2"+
		"\2\2\u017c\u017f\3\2\2\2\u017d\u017e\3\2\2\2\u017d\u017b\3\2\2\2\u017e"+
		"\u0180\3\2\2\2\u017f\u017d\3\2\2\2\u0180\u0181\7,\2\2\u0181\u0182\7\61"+
		"\2\2\u0182\u0183\3\2\2\2\u0183\u0184\b\61\2\2\u0184b\3\2\2\2\u0185\u0186"+
		"\7\61\2\2\u0186\u0187\7\61\2\2\u0187\u018b\3\2\2\2\u0188\u018a\13\2\2"+
		"\2\u0189\u0188\3\2\2\2\u018a\u018d\3\2\2\2\u018b\u018c\3\2\2\2\u018b\u0189"+
		"\3\2\2\2\u018c\u018f\3\2\2\2\u018d\u018b\3\2\2\2\u018e\u0190\7\17\2\2"+
		"\u018f\u018e\3\2\2\2\u018f\u0190\3\2\2\2\u0190\u0191\3\2\2\2\u0191\u0192"+
		"\7\f\2\2\u0192\u0193\3\2\2\2\u0193\u0194\b\62\2\2\u0194d\3\2\2\2\u0195"+
		"\u0197\t\7\2\2\u0196\u0195\3\2\2\2\u0197\u0198\3\2\2\2\u0198\u0196\3\2"+
		"\2\2\u0198\u0199\3\2\2\2\u0199\u019a\3\2\2\2\u019a\u019b\b\63\2\2\u019b"+
		"f\3\2\2\2\23\2\u013c\u0142\u0147\u014d\u0150\u0152\u0157\u0159\u015f\u0161"+
		"\u0169\u016b\u017d\u018b\u018f\u0198\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}