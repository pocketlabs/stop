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
		OPTIONAL=38, DOT=39, NUMBER=40, MODEL_TYPE=41, ID=42, REFERENCE=43, FILENAME=44, 
		COMMENT=45, LINE_COMMENT=46, WS=47;
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
			"NUMBER", "MODEL_TYPE", "ID", "REFERENCE", "FILENAME", "UPPERCASE_LETTER", 
			"LOWERCASE_LETTER", "LETTER", "DIGIT", "COMMENT", "LINE_COMMENT", "WS"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'include'", "'\"'", "'{'", "';'", "'}'", "'double'", "'float'", 
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
			"MODEL_TYPE", "ID", "REFERENCE", "FILENAME", "COMMENT", "LINE_COMMENT", 
			"WS"
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\61\u019e\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31"+
		"\t\31\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t"+
		" \4!\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t"+
		"+\4,\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\4\63\t\63\4\64"+
		"\t\64\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\3\3\3\3\4\3\4\3\5\3\5\3\6\3\6"+
		"\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3\b\3\b\3\t\3\t\3\t\3\t\3"+
		"\t\3\t\3\n\3\n\3\n\3\n\3\n\3\n\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\f"+
		"\3\f\3\f\3\f\3\f\3\f\3\f\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\16\3\16\3\16\3"+
		"\16\3\16\3\16\3\16\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\20\3\20\3"+
		"\20\3\20\3\20\3\20\3\20\3\20\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3"+
		"\21\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\23\3\23\3\23\3\23\3"+
		"\23\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\25\3\25\3\25\3\25\3\25\3\25\3"+
		"\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\27\3\27\3\27\3\27\3"+
		"\27\3\30\3\30\3\31\3\31\3\32\3\32\3\33\3\33\3\34\3\34\3\35\3\35\3\36\3"+
		"\36\3\36\3\36\3\36\3\36\3\36\3\37\3\37\3\37\3\37\3\37\3\37\3\37\3\37\3"+
		" \3 \3 \3 \3 \3 \3!\3!\3!\3!\3!\3!\3\"\3\"\3\"\3\"\3\"\3#\3#\3#\3#\3#"+
		"\3#\3$\3$\3$\3%\3%\3%\3&\3&\3&\3\'\3\'\3\'\3\'\3\'\3\'\3\'\3\'\3\'\3("+
		"\3(\3)\5)\u0139\n)\3)\3)\6)\u013d\n)\r)\16)\u013e\3)\6)\u0142\n)\r)\16"+
		")\u0143\3)\3)\7)\u0148\n)\f)\16)\u014b\13)\5)\u014d\n)\5)\u014f\n)\3*"+
		"\3*\3*\7*\u0154\n*\f*\16*\u0157\13*\3+\3+\3+\7+\u015c\n+\f+\16+\u015f"+
		"\13+\3,\3,\3,\3,\7,\u0165\n,\f,\16,\u0168\13,\3-\3-\3-\3-\6-\u016e\n-"+
		"\r-\16-\u016f\3.\3.\3/\3/\3\60\3\60\3\61\3\61\3\62\3\62\3\62\3\62\7\62"+
		"\u017e\n\62\f\62\16\62\u0181\13\62\3\62\3\62\3\62\3\62\3\62\3\63\3\63"+
		"\3\63\3\63\7\63\u018c\n\63\f\63\16\63\u018f\13\63\3\63\5\63\u0192\n\63"+
		"\3\63\3\63\3\63\3\63\3\64\6\64\u0199\n\64\r\64\16\64\u019a\3\64\3\64\4"+
		"\u017f\u018d\2\65\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31"+
		"\16\33\17\35\20\37\21!\22#\23%\24\'\25)\26+\27-\30/\31\61\32\63\33\65"+
		"\34\67\359\36;\37= ?!A\"C#E$G%I&K\'M(O)Q*S+U,W-Y.[\2]\2_\2a\2c/e\60g\61"+
		"\3\2\b\6\2//\61\61^^aa\3\2C\\\3\2c|\5\2C\\aac|\3\2\62;\5\2\13\f\17\17"+
		"\"\"\2\u01ae\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2"+
		"\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2"+
		"\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2"+
		"\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2"+
		"\2\2/\3\2\2\2\2\61\3\2\2\2\2\63\3\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2\29\3"+
		"\2\2\2\2;\3\2\2\2\2=\3\2\2\2\2?\3\2\2\2\2A\3\2\2\2\2C\3\2\2\2\2E\3\2\2"+
		"\2\2G\3\2\2\2\2I\3\2\2\2\2K\3\2\2\2\2M\3\2\2\2\2O\3\2\2\2\2Q\3\2\2\2\2"+
		"S\3\2\2\2\2U\3\2\2\2\2W\3\2\2\2\2Y\3\2\2\2\2c\3\2\2\2\2e\3\2\2\2\2g\3"+
		"\2\2\2\3i\3\2\2\2\5q\3\2\2\2\7s\3\2\2\2\tu\3\2\2\2\13w\3\2\2\2\ry\3\2"+
		"\2\2\17\u0080\3\2\2\2\21\u0086\3\2\2\2\23\u008c\3\2\2\2\25\u0092\3\2\2"+
		"\2\27\u0099\3\2\2\2\31\u00a0\3\2\2\2\33\u00a7\3\2\2\2\35\u00ae\3\2\2\2"+
		"\37\u00b6\3\2\2\2!\u00be\3\2\2\2#\u00c7\3\2\2\2%\u00d0\3\2\2\2\'\u00d5"+
		"\3\2\2\2)\u00dc\3\2\2\2+\u00e2\3\2\2\2-\u00ec\3\2\2\2/\u00f1\3\2\2\2\61"+
		"\u00f3\3\2\2\2\63\u00f5\3\2\2\2\65\u00f7\3\2\2\2\67\u00f9\3\2\2\29\u00fb"+
		"\3\2\2\2;\u00fd\3\2\2\2=\u0104\3\2\2\2?\u010c\3\2\2\2A\u0112\3\2\2\2C"+
		"\u0118\3\2\2\2E\u011d\3\2\2\2G\u0123\3\2\2\2I\u0126\3\2\2\2K\u0129\3\2"+
		"\2\2M\u012c\3\2\2\2O\u0135\3\2\2\2Q\u0138\3\2\2\2S\u0150\3\2\2\2U\u0158"+
		"\3\2\2\2W\u0160\3\2\2\2Y\u016d\3\2\2\2[\u0171\3\2\2\2]\u0173\3\2\2\2_"+
		"\u0175\3\2\2\2a\u0177\3\2\2\2c\u0179\3\2\2\2e\u0187\3\2\2\2g\u0198\3\2"+
		"\2\2ij\7k\2\2jk\7p\2\2kl\7e\2\2lm\7n\2\2mn\7w\2\2no\7f\2\2op\7g\2\2p\4"+
		"\3\2\2\2qr\7$\2\2r\6\3\2\2\2st\7}\2\2t\b\3\2\2\2uv\7=\2\2v\n\3\2\2\2w"+
		"x\7\177\2\2x\f\3\2\2\2yz\7f\2\2z{\7q\2\2{|\7w\2\2|}\7d\2\2}~\7n\2\2~\177"+
		"\7g\2\2\177\16\3\2\2\2\u0080\u0081\7h\2\2\u0081\u0082\7n\2\2\u0082\u0083"+
		"\7q\2\2\u0083\u0084\7c\2\2\u0084\u0085\7v\2\2\u0085\20\3\2\2\2\u0086\u0087"+
		"\7k\2\2\u0087\u0088\7p\2\2\u0088\u0089\7v\2\2\u0089\u008a\7\65\2\2\u008a"+
		"\u008b\7\64\2\2\u008b\22\3\2\2\2\u008c\u008d\7k\2\2\u008d\u008e\7p\2\2"+
		"\u008e\u008f\7v\2\2\u008f\u0090\78\2\2\u0090\u0091\7\66\2\2\u0091\24\3"+
		"\2\2\2\u0092\u0093\7w\2\2\u0093\u0094\7k\2\2\u0094\u0095\7p\2\2\u0095"+
		"\u0096\7v\2\2\u0096\u0097\7\65\2\2\u0097\u0098\7\64\2\2\u0098\26\3\2\2"+
		"\2\u0099\u009a\7w\2\2\u009a\u009b\7k\2\2\u009b\u009c\7p\2\2\u009c\u009d"+
		"\7v\2\2\u009d\u009e\78\2\2\u009e\u009f\7\66\2\2\u009f\30\3\2\2\2\u00a0"+
		"\u00a1\7u\2\2\u00a1\u00a2\7k\2\2\u00a2\u00a3\7p\2\2\u00a3\u00a4\7v\2\2"+
		"\u00a4\u00a5\7\65\2\2\u00a5\u00a6\7\64\2\2\u00a6\32\3\2\2\2\u00a7\u00a8"+
		"\7u\2\2\u00a8\u00a9\7k\2\2\u00a9\u00aa\7p\2\2\u00aa\u00ab\7v\2\2\u00ab"+
		"\u00ac\78\2\2\u00ac\u00ad\7\66\2\2\u00ad\34\3\2\2\2\u00ae\u00af\7h\2\2"+
		"\u00af\u00b0\7k\2\2\u00b0\u00b1\7z\2\2\u00b1\u00b2\7g\2\2\u00b2\u00b3"+
		"\7f\2\2\u00b3\u00b4\7\65\2\2\u00b4\u00b5\7\64\2\2\u00b5\36\3\2\2\2\u00b6"+
		"\u00b7\7h\2\2\u00b7\u00b8\7k\2\2\u00b8\u00b9\7z\2\2\u00b9\u00ba\7g\2\2"+
		"\u00ba\u00bb\7f\2\2\u00bb\u00bc\78\2\2\u00bc\u00bd\7\66\2\2\u00bd \3\2"+
		"\2\2\u00be\u00bf\7u\2\2\u00bf\u00c0\7h\2\2\u00c0\u00c1\7k\2\2\u00c1\u00c2"+
		"\7z\2\2\u00c2\u00c3\7g\2\2\u00c3\u00c4\7f\2\2\u00c4\u00c5\7\65\2\2\u00c5"+
		"\u00c6\7\64\2\2\u00c6\"\3\2\2\2\u00c7\u00c8\7u\2\2\u00c8\u00c9\7h\2\2"+
		"\u00c9\u00ca\7k\2\2\u00ca\u00cb\7z\2\2\u00cb\u00cc\7g\2\2\u00cc\u00cd"+
		"\7f\2\2\u00cd\u00ce\78\2\2\u00ce\u00cf\7\66\2\2\u00cf$\3\2\2\2\u00d0\u00d1"+
		"\7d\2\2\u00d1\u00d2\7q\2\2\u00d2\u00d3\7q\2\2\u00d3\u00d4\7n\2\2\u00d4"+
		"&\3\2\2\2\u00d5\u00d6\7u\2\2\u00d6\u00d7\7v\2\2\u00d7\u00d8\7t\2\2\u00d8"+
		"\u00d9\7k\2\2\u00d9\u00da\7p\2\2\u00da\u00db\7i\2\2\u00db(\3\2\2\2\u00dc"+
		"\u00dd\7d\2\2\u00dd\u00de\7{\2\2\u00de\u00df\7v\2\2\u00df\u00e0\7g\2\2"+
		"\u00e0\u00e1\7u\2\2\u00e1*\3\2\2\2\u00e2\u00e3\7v\2\2\u00e3\u00e4\7k\2"+
		"\2\u00e4\u00e5\7o\2\2\u00e5\u00e6\7g\2\2\u00e6\u00e7\7u\2\2\u00e7\u00e8"+
		"\7v\2\2\u00e8\u00e9\7c\2\2\u00e9\u00ea\7o\2\2\u00ea\u00eb\7r\2\2\u00eb"+
		",\3\2\2\2\u00ec\u00ed\7g\2\2\u00ed\u00ee\7p\2\2\u00ee\u00ef\7w\2\2\u00ef"+
		"\u00f0\7o\2\2\u00f0.\3\2\2\2\u00f1\u00f2\7*\2\2\u00f2\60\3\2\2\2\u00f3"+
		"\u00f4\7.\2\2\u00f4\62\3\2\2\2\u00f5\u00f6\7+\2\2\u00f6\64\3\2\2\2\u00f7"+
		"\u00f8\7<\2\2\u00f8\66\3\2\2\2\u00f9\u00fa\7]\2\2\u00fa8\3\2\2\2\u00fb"+
		"\u00fc\7_\2\2\u00fc:\3\2\2\2\u00fd\u00fe\7v\2\2\u00fe\u00ff\7j\2\2\u00ff"+
		"\u0100\7t\2\2\u0100\u0101\7q\2\2\u0101\u0102\7y\2\2\u0102\u0103\7u\2\2"+
		"\u0103<\3\2\2\2\u0104\u0105\7v\2\2\u0105\u0106\7k\2\2\u0106\u0107\7o\2"+
		"\2\u0107\u0108\7g\2\2\u0108\u0109\7q\2\2\u0109\u010a\7w\2\2\u010a\u010b"+
		"\7v\2\2\u010b>\3\2\2\2\u010c\u010d\7c\2\2\u010d\u010e\7u\2\2\u010e\u010f"+
		"\7{\2\2\u010f\u0110\7p\2\2\u0110\u0111\7e\2\2\u0111@\3\2\2\2\u0112\u0113"+
		"\7u\2\2\u0113\u0114\7v\2\2\u0114\u0115\7c\2\2\u0115\u0116\7t\2\2\u0116"+
		"\u0117\7v\2\2\u0117B\3\2\2\2\u0118\u0119\7u\2\2\u0119\u011a\7v\2\2\u011a"+
		"\u011b\7q\2\2\u011b\u011c\7r\2\2\u011cD\3\2\2\2\u011d\u011e\7s\2\2\u011e"+
		"\u011f\7w\2\2\u011f\u0120\7g\2\2\u0120\u0121\7w\2\2\u0121\u0122\7g\2\2"+
		"\u0122F\3\2\2\2\u0123\u0124\7/\2\2\u0124\u0125\7@\2\2\u0125H\3\2\2\2\u0126"+
		"\u0127\7>\2\2\u0127\u0128\7/\2\2\u0128J\3\2\2\2\u0129\u012a\7@\2\2\u012a"+
		"\u012b\7@\2\2\u012bL\3\2\2\2\u012c\u012d\7q\2\2\u012d\u012e\7r\2\2\u012e"+
		"\u012f\7v\2\2\u012f\u0130\7k\2\2\u0130\u0131\7q\2\2\u0131\u0132\7p\2\2"+
		"\u0132\u0133\7c\2\2\u0133\u0134\7n\2\2\u0134N\3\2\2\2\u0135\u0136\7\60"+
		"\2\2\u0136P\3\2\2\2\u0137\u0139\7/\2\2\u0138\u0137\3\2\2\2\u0138\u0139"+
		"\3\2\2\2\u0139\u014e\3\2\2\2\u013a\u013c\5O(\2\u013b\u013d\5a\61\2\u013c"+
		"\u013b\3\2\2\2\u013d\u013e\3\2\2\2\u013e\u013c\3\2\2\2\u013e\u013f\3\2"+
		"\2\2\u013f\u014f\3\2\2\2\u0140\u0142\5a\61\2\u0141\u0140\3\2\2\2\u0142"+
		"\u0143\3\2\2\2\u0143\u0141\3\2\2\2\u0143\u0144\3\2\2\2\u0144\u014c\3\2"+
		"\2\2\u0145\u0149\5O(\2\u0146\u0148\5a\61\2\u0147\u0146\3\2\2\2\u0148\u014b"+
		"\3\2\2\2\u0149\u0147\3\2\2\2\u0149\u014a\3\2\2\2\u014a\u014d\3\2\2\2\u014b"+
		"\u0149\3\2\2\2\u014c\u0145\3\2\2\2\u014c\u014d\3\2\2\2\u014d\u014f\3\2"+
		"\2\2\u014e\u013a\3\2\2\2\u014e\u0141\3\2\2\2\u014fR\3\2\2\2\u0150\u0155"+
		"\5[.\2\u0151\u0154\5_\60\2\u0152\u0154\5a\61\2\u0153\u0151\3\2\2\2\u0153"+
		"\u0152\3\2\2\2\u0154\u0157\3\2\2\2\u0155\u0153\3\2\2\2\u0155\u0156\3\2"+
		"\2\2\u0156T\3\2\2\2\u0157\u0155\3\2\2\2\u0158\u015d\5]/\2\u0159\u015c"+
		"\5_\60\2\u015a\u015c\5a\61\2\u015b\u0159\3\2\2\2\u015b\u015a\3\2\2\2\u015c"+
		"\u015f\3\2\2\2\u015d\u015b\3\2\2\2\u015d\u015e\3\2\2\2\u015eV\3\2\2\2"+
		"\u015f\u015d\3\2\2\2\u0160\u0166\5]/\2\u0161\u0165\5_\60\2\u0162\u0165"+
		"\5a\61\2\u0163\u0165\5O(\2\u0164\u0161\3\2\2\2\u0164\u0162\3\2\2\2\u0164"+
		"\u0163\3\2\2\2\u0165\u0168\3\2\2\2\u0166\u0164\3\2\2\2\u0166\u0167\3\2"+
		"\2\2\u0167X\3\2\2\2\u0168\u0166\3\2\2\2\u0169\u016e\5_\60\2\u016a\u016e"+
		"\5a\61\2\u016b\u016e\5O(\2\u016c\u016e\t\2\2\2\u016d\u0169\3\2\2\2\u016d"+
		"\u016a\3\2\2\2\u016d\u016b\3\2\2\2\u016d\u016c\3\2\2\2\u016e\u016f\3\2"+
		"\2\2\u016f\u016d\3\2\2\2\u016f\u0170\3\2\2\2\u0170Z\3\2\2\2\u0171\u0172"+
		"\t\3\2\2\u0172\\\3\2\2\2\u0173\u0174\t\4\2\2\u0174^\3\2\2\2\u0175\u0176"+
		"\t\5\2\2\u0176`\3\2\2\2\u0177\u0178\t\6\2\2\u0178b\3\2\2\2\u0179\u017a"+
		"\7\61\2\2\u017a\u017b\7,\2\2\u017b\u017f\3\2\2\2\u017c\u017e\13\2\2\2"+
		"\u017d\u017c\3\2\2\2\u017e\u0181\3\2\2\2\u017f\u0180\3\2\2\2\u017f\u017d"+
		"\3\2\2\2\u0180\u0182\3\2\2\2\u0181\u017f\3\2\2\2\u0182\u0183\7,\2\2\u0183"+
		"\u0184\7\61\2\2\u0184\u0185\3\2\2\2\u0185\u0186\b\62\2\2\u0186d\3\2\2"+
		"\2\u0187\u0188\7\61\2\2\u0188\u0189\7\61\2\2\u0189\u018d\3\2\2\2\u018a"+
		"\u018c\13\2\2\2\u018b\u018a\3\2\2\2\u018c\u018f\3\2\2\2\u018d\u018e\3"+
		"\2\2\2\u018d\u018b\3\2\2\2\u018e\u0191\3\2\2\2\u018f\u018d\3\2\2\2\u0190"+
		"\u0192\7\17\2\2\u0191\u0190\3\2\2\2\u0191\u0192\3\2\2\2\u0192\u0193\3"+
		"\2\2\2\u0193\u0194\7\f\2\2\u0194\u0195\3\2\2\2\u0195\u0196\b\63\2\2\u0196"+
		"f\3\2\2\2\u0197\u0199\t\7\2\2\u0198\u0197\3\2\2\2\u0199\u019a\3\2\2\2"+
		"\u019a\u0198\3\2\2\2\u019a\u019b\3\2\2\2\u019b\u019c\3\2\2\2\u019c\u019d"+
		"\b\64\2\2\u019dh\3\2\2\2\25\2\u0138\u013e\u0143\u0149\u014c\u014e\u0153"+
		"\u0155\u015b\u015d\u0164\u0166\u016d\u016f\u017f\u018d\u0191\u019a\3\b"+
		"\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}