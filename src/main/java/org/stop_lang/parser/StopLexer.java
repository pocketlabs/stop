// Generated from /Users/kyleshank/git/stop/Stop.g4 by ANTLR 4.7
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
	static { RuntimeMetaData.checkVersion("4.7", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, T__16=17, 
		T__17=18, T__18=19, T__19=20, T__20=21, T__21=22, T__22=23, T__23=24, 
		T__24=25, T__25=26, T__26=27, ASYNC=28, START=29, STOP=30, QUEUE=31, TRANSITION_OP=32, 
		RETURN_OP=33, ENQUEUE_OP=34, OPTIONAL=35, NUMBER=36, MODEL_TYPE=37, ID=38, 
		FILENAME=39, COMMENT=40, LINE_COMMENT=41, WS=42;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "T__7", "T__8", 
		"T__9", "T__10", "T__11", "T__12", "T__13", "T__14", "T__15", "T__16", 
		"T__17", "T__18", "T__19", "T__20", "T__21", "T__22", "T__23", "T__24", 
		"T__25", "T__26", "ASYNC", "START", "STOP", "QUEUE", "TRANSITION_OP", 
		"RETURN_OP", "ENQUEUE_OP", "OPTIONAL", "NUMBER", "MODEL_TYPE", "ID", "FILENAME", 
		"UPPERCASE_LETTER", "LOWERCASE_LETTER", "LETTER", "DIGIT", "COMMENT", 
		"LINE_COMMENT", "WS"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'include'", "'\"'", "'{'", "';'", "'}'", "'double'", "'float'", 
		"'int32'", "'int64'", "'uint32'", "'uint64'", "'sint32'", "'sint64'", 
		"'fixed32'", "'fixed64'", "'sfixed32'", "'sfixed64'", "'bool'", "'string'", 
		"'bytes'", "'timestamp'", "'enum'", "'['", "']'", "'throws'", "','", "'timeout'", 
		"'async'", "'start'", "'stop'", "'queue'", "'->'", "'<-'", "'>>'", "'optional'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, "ASYNC", "START", "STOP", "QUEUE", "TRANSITION_OP", 
		"RETURN_OP", "ENQUEUE_OP", "OPTIONAL", "NUMBER", "MODEL_TYPE", "ID", "FILENAME", 
		"COMMENT", "LINE_COMMENT", "WS"
	};
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2,\u0182\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t+\4"+
		",\t,\4-\t-\4.\t.\4/\t/\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\3\3\3\3\4\3\4"+
		"\3\5\3\5\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3\b\3\b\3"+
		"\t\3\t\3\t\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\n\3\13\3\13\3\13\3\13\3\13"+
		"\3\13\3\13\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\16"+
		"\3\16\3\16\3\16\3\16\3\16\3\16\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17"+
		"\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\21\3\21\3\21\3\21\3\21\3\21"+
		"\3\21\3\21\3\21\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\23\3\23"+
		"\3\23\3\23\3\23\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\25\3\25\3\25\3\25"+
		"\3\25\3\25\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\27\3\27"+
		"\3\27\3\27\3\27\3\30\3\30\3\31\3\31\3\32\3\32\3\32\3\32\3\32\3\32\3\32"+
		"\3\33\3\33\3\34\3\34\3\34\3\34\3\34\3\34\3\34\3\34\3\35\3\35\3\35\3\35"+
		"\3\35\3\35\3\36\3\36\3\36\3\36\3\36\3\36\3\37\3\37\3\37\3\37\3\37\3 \3"+
		" \3 \3 \3 \3 \3!\3!\3!\3\"\3\"\3\"\3#\3#\3#\3$\3$\3$\3$\3$\3$\3$\3$\3"+
		"$\3%\5%\u0127\n%\3%\3%\6%\u012b\n%\r%\16%\u012c\3%\6%\u0130\n%\r%\16%"+
		"\u0131\3%\3%\7%\u0136\n%\f%\16%\u0139\13%\5%\u013b\n%\5%\u013d\n%\3&\3"+
		"&\3&\7&\u0142\n&\f&\16&\u0145\13&\3\'\3\'\3\'\7\'\u014a\n\'\f\'\16\'\u014d"+
		"\13\'\3(\3(\3(\6(\u0152\n(\r(\16(\u0153\3)\3)\3*\3*\3+\3+\3,\3,\3-\3-"+
		"\3-\3-\7-\u0162\n-\f-\16-\u0165\13-\3-\3-\3-\3-\3-\3.\3.\3.\3.\7.\u0170"+
		"\n.\f.\16.\u0173\13.\3.\5.\u0176\n.\3.\3.\3.\3.\3/\6/\u017d\n/\r/\16/"+
		"\u017e\3/\3/\4\u0163\u0171\2\60\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23"+
		"\13\25\f\27\r\31\16\33\17\35\20\37\21!\22#\23%\24\'\25)\26+\27-\30/\31"+
		"\61\32\63\33\65\34\67\359\36;\37= ?!A\"C#E$G%I&K\'M(O)Q\2S\2U\2W\2Y*["+
		"+],\3\2\b\5\2/\61^^aa\3\2C\\\3\2c|\5\2C\\aac|\3\2\62;\5\2\13\f\17\17\""+
		"\"\2\u018e\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2"+
		"\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27"+
		"\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2"+
		"\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2"+
		"\2/\3\2\2\2\2\61\3\2\2\2\2\63\3\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2\29\3\2"+
		"\2\2\2;\3\2\2\2\2=\3\2\2\2\2?\3\2\2\2\2A\3\2\2\2\2C\3\2\2\2\2E\3\2\2\2"+
		"\2G\3\2\2\2\2I\3\2\2\2\2K\3\2\2\2\2M\3\2\2\2\2O\3\2\2\2\2Y\3\2\2\2\2["+
		"\3\2\2\2\2]\3\2\2\2\3_\3\2\2\2\5g\3\2\2\2\7i\3\2\2\2\tk\3\2\2\2\13m\3"+
		"\2\2\2\ro\3\2\2\2\17v\3\2\2\2\21|\3\2\2\2\23\u0082\3\2\2\2\25\u0088\3"+
		"\2\2\2\27\u008f\3\2\2\2\31\u0096\3\2\2\2\33\u009d\3\2\2\2\35\u00a4\3\2"+
		"\2\2\37\u00ac\3\2\2\2!\u00b4\3\2\2\2#\u00bd\3\2\2\2%\u00c6\3\2\2\2\'\u00cb"+
		"\3\2\2\2)\u00d2\3\2\2\2+\u00d8\3\2\2\2-\u00e2\3\2\2\2/\u00e7\3\2\2\2\61"+
		"\u00e9\3\2\2\2\63\u00eb\3\2\2\2\65\u00f2\3\2\2\2\67\u00f4\3\2\2\29\u00fc"+
		"\3\2\2\2;\u0102\3\2\2\2=\u0108\3\2\2\2?\u010d\3\2\2\2A\u0113\3\2\2\2C"+
		"\u0116\3\2\2\2E\u0119\3\2\2\2G\u011c\3\2\2\2I\u0126\3\2\2\2K\u013e\3\2"+
		"\2\2M\u0146\3\2\2\2O\u0151\3\2\2\2Q\u0155\3\2\2\2S\u0157\3\2\2\2U\u0159"+
		"\3\2\2\2W\u015b\3\2\2\2Y\u015d\3\2\2\2[\u016b\3\2\2\2]\u017c\3\2\2\2_"+
		"`\7k\2\2`a\7p\2\2ab\7e\2\2bc\7n\2\2cd\7w\2\2de\7f\2\2ef\7g\2\2f\4\3\2"+
		"\2\2gh\7$\2\2h\6\3\2\2\2ij\7}\2\2j\b\3\2\2\2kl\7=\2\2l\n\3\2\2\2mn\7\177"+
		"\2\2n\f\3\2\2\2op\7f\2\2pq\7q\2\2qr\7w\2\2rs\7d\2\2st\7n\2\2tu\7g\2\2"+
		"u\16\3\2\2\2vw\7h\2\2wx\7n\2\2xy\7q\2\2yz\7c\2\2z{\7v\2\2{\20\3\2\2\2"+
		"|}\7k\2\2}~\7p\2\2~\177\7v\2\2\177\u0080\7\65\2\2\u0080\u0081\7\64\2\2"+
		"\u0081\22\3\2\2\2\u0082\u0083\7k\2\2\u0083\u0084\7p\2\2\u0084\u0085\7"+
		"v\2\2\u0085\u0086\78\2\2\u0086\u0087\7\66\2\2\u0087\24\3\2\2\2\u0088\u0089"+
		"\7w\2\2\u0089\u008a\7k\2\2\u008a\u008b\7p\2\2\u008b\u008c\7v\2\2\u008c"+
		"\u008d\7\65\2\2\u008d\u008e\7\64\2\2\u008e\26\3\2\2\2\u008f\u0090\7w\2"+
		"\2\u0090\u0091\7k\2\2\u0091\u0092\7p\2\2\u0092\u0093\7v\2\2\u0093\u0094"+
		"\78\2\2\u0094\u0095\7\66\2\2\u0095\30\3\2\2\2\u0096\u0097\7u\2\2\u0097"+
		"\u0098\7k\2\2\u0098\u0099\7p\2\2\u0099\u009a\7v\2\2\u009a\u009b\7\65\2"+
		"\2\u009b\u009c\7\64\2\2\u009c\32\3\2\2\2\u009d\u009e\7u\2\2\u009e\u009f"+
		"\7k\2\2\u009f\u00a0\7p\2\2\u00a0\u00a1\7v\2\2\u00a1\u00a2\78\2\2\u00a2"+
		"\u00a3\7\66\2\2\u00a3\34\3\2\2\2\u00a4\u00a5\7h\2\2\u00a5\u00a6\7k\2\2"+
		"\u00a6\u00a7\7z\2\2\u00a7\u00a8\7g\2\2\u00a8\u00a9\7f\2\2\u00a9\u00aa"+
		"\7\65\2\2\u00aa\u00ab\7\64\2\2\u00ab\36\3\2\2\2\u00ac\u00ad\7h\2\2\u00ad"+
		"\u00ae\7k\2\2\u00ae\u00af\7z\2\2\u00af\u00b0\7g\2\2\u00b0\u00b1\7f\2\2"+
		"\u00b1\u00b2\78\2\2\u00b2\u00b3\7\66\2\2\u00b3 \3\2\2\2\u00b4\u00b5\7"+
		"u\2\2\u00b5\u00b6\7h\2\2\u00b6\u00b7\7k\2\2\u00b7\u00b8\7z\2\2\u00b8\u00b9"+
		"\7g\2\2\u00b9\u00ba\7f\2\2\u00ba\u00bb\7\65\2\2\u00bb\u00bc\7\64\2\2\u00bc"+
		"\"\3\2\2\2\u00bd\u00be\7u\2\2\u00be\u00bf\7h\2\2\u00bf\u00c0\7k\2\2\u00c0"+
		"\u00c1\7z\2\2\u00c1\u00c2\7g\2\2\u00c2\u00c3\7f\2\2\u00c3\u00c4\78\2\2"+
		"\u00c4\u00c5\7\66\2\2\u00c5$\3\2\2\2\u00c6\u00c7\7d\2\2\u00c7\u00c8\7"+
		"q\2\2\u00c8\u00c9\7q\2\2\u00c9\u00ca\7n\2\2\u00ca&\3\2\2\2\u00cb\u00cc"+
		"\7u\2\2\u00cc\u00cd\7v\2\2\u00cd\u00ce\7t\2\2\u00ce\u00cf\7k\2\2\u00cf"+
		"\u00d0\7p\2\2\u00d0\u00d1\7i\2\2\u00d1(\3\2\2\2\u00d2\u00d3\7d\2\2\u00d3"+
		"\u00d4\7{\2\2\u00d4\u00d5\7v\2\2\u00d5\u00d6\7g\2\2\u00d6\u00d7\7u\2\2"+
		"\u00d7*\3\2\2\2\u00d8\u00d9\7v\2\2\u00d9\u00da\7k\2\2\u00da\u00db\7o\2"+
		"\2\u00db\u00dc\7g\2\2\u00dc\u00dd\7u\2\2\u00dd\u00de\7v\2\2\u00de\u00df"+
		"\7c\2\2\u00df\u00e0\7o\2\2\u00e0\u00e1\7r\2\2\u00e1,\3\2\2\2\u00e2\u00e3"+
		"\7g\2\2\u00e3\u00e4\7p\2\2\u00e4\u00e5\7w\2\2\u00e5\u00e6\7o\2\2\u00e6"+
		".\3\2\2\2\u00e7\u00e8\7]\2\2\u00e8\60\3\2\2\2\u00e9\u00ea\7_\2\2\u00ea"+
		"\62\3\2\2\2\u00eb\u00ec\7v\2\2\u00ec\u00ed\7j\2\2\u00ed\u00ee\7t\2\2\u00ee"+
		"\u00ef\7q\2\2\u00ef\u00f0\7y\2\2\u00f0\u00f1\7u\2\2\u00f1\64\3\2\2\2\u00f2"+
		"\u00f3\7.\2\2\u00f3\66\3\2\2\2\u00f4\u00f5\7v\2\2\u00f5\u00f6\7k\2\2\u00f6"+
		"\u00f7\7o\2\2\u00f7\u00f8\7g\2\2\u00f8\u00f9\7q\2\2\u00f9\u00fa\7w\2\2"+
		"\u00fa\u00fb\7v\2\2\u00fb8\3\2\2\2\u00fc\u00fd\7c\2\2\u00fd\u00fe\7u\2"+
		"\2\u00fe\u00ff\7{\2\2\u00ff\u0100\7p\2\2\u0100\u0101\7e\2\2\u0101:\3\2"+
		"\2\2\u0102\u0103\7u\2\2\u0103\u0104\7v\2\2\u0104\u0105\7c\2\2\u0105\u0106"+
		"\7t\2\2\u0106\u0107\7v\2\2\u0107<\3\2\2\2\u0108\u0109\7u\2\2\u0109\u010a"+
		"\7v\2\2\u010a\u010b\7q\2\2\u010b\u010c\7r\2\2\u010c>\3\2\2\2\u010d\u010e"+
		"\7s\2\2\u010e\u010f\7w\2\2\u010f\u0110\7g\2\2\u0110\u0111\7w\2\2\u0111"+
		"\u0112\7g\2\2\u0112@\3\2\2\2\u0113\u0114\7/\2\2\u0114\u0115\7@\2\2\u0115"+
		"B\3\2\2\2\u0116\u0117\7>\2\2\u0117\u0118\7/\2\2\u0118D\3\2\2\2\u0119\u011a"+
		"\7@\2\2\u011a\u011b\7@\2\2\u011bF\3\2\2\2\u011c\u011d\7q\2\2\u011d\u011e"+
		"\7r\2\2\u011e\u011f\7v\2\2\u011f\u0120\7k\2\2\u0120\u0121\7q\2\2\u0121"+
		"\u0122\7p\2\2\u0122\u0123\7c\2\2\u0123\u0124\7n\2\2\u0124H\3\2\2\2\u0125"+
		"\u0127\7/\2\2\u0126\u0125\3\2\2\2\u0126\u0127\3\2\2\2\u0127\u013c\3\2"+
		"\2\2\u0128\u012a\7\60\2\2\u0129\u012b\5W,\2\u012a\u0129\3\2\2\2\u012b"+
		"\u012c\3\2\2\2\u012c\u012a\3\2\2\2\u012c\u012d\3\2\2\2\u012d\u013d\3\2"+
		"\2\2\u012e\u0130\5W,\2\u012f\u012e\3\2\2\2\u0130\u0131\3\2\2\2\u0131\u012f"+
		"\3\2\2\2\u0131\u0132\3\2\2\2\u0132\u013a\3\2\2\2\u0133\u0137\7\60\2\2"+
		"\u0134\u0136\5W,\2\u0135\u0134\3\2\2\2\u0136\u0139\3\2\2\2\u0137\u0135"+
		"\3\2\2\2\u0137\u0138\3\2\2\2\u0138\u013b\3\2\2\2\u0139\u0137\3\2\2\2\u013a"+
		"\u0133\3\2\2\2\u013a\u013b\3\2\2\2\u013b\u013d\3\2\2\2\u013c\u0128\3\2"+
		"\2\2\u013c\u012f\3\2\2\2\u013dJ\3\2\2\2\u013e\u0143\5Q)\2\u013f\u0142"+
		"\5U+\2\u0140\u0142\5W,\2\u0141\u013f\3\2\2\2\u0141\u0140\3\2\2\2\u0142"+
		"\u0145\3\2\2\2\u0143\u0141\3\2\2\2\u0143\u0144\3\2\2\2\u0144L\3\2\2\2"+
		"\u0145\u0143\3\2\2\2\u0146\u014b\5S*\2\u0147\u014a\5U+\2\u0148\u014a\5"+
		"W,\2\u0149\u0147\3\2\2\2\u0149\u0148\3\2\2\2\u014a\u014d\3\2\2\2\u014b"+
		"\u0149\3\2\2\2\u014b\u014c\3\2\2\2\u014cN\3\2\2\2\u014d\u014b\3\2\2\2"+
		"\u014e\u0152\5U+\2\u014f\u0152\5W,\2\u0150\u0152\t\2\2\2\u0151\u014e\3"+
		"\2\2\2\u0151\u014f\3\2\2\2\u0151\u0150\3\2\2\2\u0152\u0153\3\2\2\2\u0153"+
		"\u0151\3\2\2\2\u0153\u0154\3\2\2\2\u0154P\3\2\2\2\u0155\u0156\t\3\2\2"+
		"\u0156R\3\2\2\2\u0157\u0158\t\4\2\2\u0158T\3\2\2\2\u0159\u015a\t\5\2\2"+
		"\u015aV\3\2\2\2\u015b\u015c\t\6\2\2\u015cX\3\2\2\2\u015d\u015e\7\61\2"+
		"\2\u015e\u015f\7,\2\2\u015f\u0163\3\2\2\2\u0160\u0162\13\2\2\2\u0161\u0160"+
		"\3\2\2\2\u0162\u0165\3\2\2\2\u0163\u0164\3\2\2\2\u0163\u0161\3\2\2\2\u0164"+
		"\u0166\3\2\2\2\u0165\u0163\3\2\2\2\u0166\u0167\7,\2\2\u0167\u0168\7\61"+
		"\2\2\u0168\u0169\3\2\2\2\u0169\u016a\b-\2\2\u016aZ\3\2\2\2\u016b\u016c"+
		"\7\61\2\2\u016c\u016d\7\61\2\2\u016d\u0171\3\2\2\2\u016e\u0170\13\2\2"+
		"\2\u016f\u016e\3\2\2\2\u0170\u0173\3\2\2\2\u0171\u0172\3\2\2\2\u0171\u016f"+
		"\3\2\2\2\u0172\u0175\3\2\2\2\u0173\u0171\3\2\2\2\u0174\u0176\7\17\2\2"+
		"\u0175\u0174\3\2\2\2\u0175\u0176\3\2\2\2\u0176\u0177\3\2\2\2\u0177\u0178"+
		"\7\f\2\2\u0178\u0179\3\2\2\2\u0179\u017a\b.\2\2\u017a\\\3\2\2\2\u017b"+
		"\u017d\t\7\2\2\u017c\u017b\3\2\2\2\u017d\u017e\3\2\2\2\u017e\u017c\3\2"+
		"\2\2\u017e\u017f\3\2\2\2\u017f\u0180\3\2\2\2\u0180\u0181\b/\2\2\u0181"+
		"^\3\2\2\2\23\2\u0126\u012c\u0131\u0137\u013a\u013c\u0141\u0143\u0149\u014b"+
		"\u0151\u0153\u0163\u0171\u0175\u017e\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}