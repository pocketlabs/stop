// Generated from /Users/kyleshank/git/stop/StopTemplates.g4 by ANTLR 4.7.2
package org.stop_lang.templates.parser;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class StopTemplatesParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.7.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, STRING_LITERAL=7, NUMBER_LITERAL=8, 
		BOOL_LITERAL=9, TAB=10, NOT=11, DOT=12, NUMBER=13, COMPONENT_TYPE=14, 
		ID=15, REFERENCE=16, SKIP_=17, NEWLINE=18, INDENT=19, DEDENT=20;
	public static final int
		RULE_file = 0, RULE_statement = 1, RULE_nested_statements = 2, RULE_component = 3, 
		RULE_component_parameter = 4, RULE_component_parameter_value_or_collection = 5, 
		RULE_component_parameter_value_collection = 6, RULE_component_parameter_value = 7, 
		RULE_collection = 8, RULE_conditional = 9;
	private static String[] makeRuleNames() {
		return new String[] {
			"file", "statement", "nested_statements", "component", "component_parameter", 
			"component_parameter_value_or_collection", "component_parameter_value_collection", 
			"component_parameter_value", "collection", "conditional"
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
			"REFERENCE", "SKIP_", "NEWLINE", "INDENT", "DEDENT"
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

	@Override
	public String getGrammarFileName() { return "StopTemplates.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public StopTemplatesParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class FileContext extends ParserRuleContext {
		public TerminalNode EOF() { return getToken(StopTemplatesParser.EOF, 0); }
		public List<TerminalNode> NEWLINE() { return getTokens(StopTemplatesParser.NEWLINE); }
		public TerminalNode NEWLINE(int i) {
			return getToken(StopTemplatesParser.NEWLINE, i);
		}
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public FileContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_file; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof StopTemplatesListener ) ((StopTemplatesListener)listener).enterFile(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof StopTemplatesListener ) ((StopTemplatesListener)listener).exitFile(this);
		}
	}

	public final FileContext file() throws RecognitionException {
		FileContext _localctx = new FileContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_file);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(24);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__4) | (1L << NOT) | (1L << COMPONENT_TYPE) | (1L << ID) | (1L << REFERENCE) | (1L << NEWLINE))) != 0)) {
				{
				setState(22);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case NEWLINE:
					{
					setState(20);
					match(NEWLINE);
					}
					break;
				case T__4:
				case NOT:
				case COMPONENT_TYPE:
				case ID:
				case REFERENCE:
					{
					setState(21);
					statement();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(26);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(27);
			match(EOF);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class StatementContext extends ParserRuleContext {
		public TerminalNode NEWLINE() { return getToken(StopTemplatesParser.NEWLINE, 0); }
		public ComponentContext component() {
			return getRuleContext(ComponentContext.class,0);
		}
		public CollectionContext collection() {
			return getRuleContext(CollectionContext.class,0);
		}
		public ConditionalContext conditional() {
			return getRuleContext(ConditionalContext.class,0);
		}
		public Nested_statementsContext nested_statements() {
			return getRuleContext(Nested_statementsContext.class,0);
		}
		public StatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_statement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof StopTemplatesListener ) ((StopTemplatesListener)listener).enterStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof StopTemplatesListener ) ((StopTemplatesListener)listener).exitStatement(this);
		}
	}

	public final StatementContext statement() throws RecognitionException {
		StatementContext _localctx = new StatementContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_statement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(32);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,2,_ctx) ) {
			case 1:
				{
				setState(29);
				component();
				}
				break;
			case 2:
				{
				setState(30);
				collection();
				}
				break;
			case 3:
				{
				setState(31);
				conditional();
				}
				break;
			}
			setState(34);
			match(NEWLINE);
			setState(36);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==INDENT) {
				{
				setState(35);
				nested_statements();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Nested_statementsContext extends ParserRuleContext {
		public TerminalNode INDENT() { return getToken(StopTemplatesParser.INDENT, 0); }
		public TerminalNode DEDENT() { return getToken(StopTemplatesParser.DEDENT, 0); }
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public Nested_statementsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_nested_statements; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof StopTemplatesListener ) ((StopTemplatesListener)listener).enterNested_statements(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof StopTemplatesListener ) ((StopTemplatesListener)listener).exitNested_statements(this);
		}
	}

	public final Nested_statementsContext nested_statements() throws RecognitionException {
		Nested_statementsContext _localctx = new Nested_statementsContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_nested_statements);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(38);
			match(INDENT);
			setState(40); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(39);
				statement();
				}
				}
				setState(42); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__4) | (1L << NOT) | (1L << COMPONENT_TYPE) | (1L << ID) | (1L << REFERENCE))) != 0) );
			setState(44);
			match(DEDENT);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ComponentContext extends ParserRuleContext {
		public TerminalNode COMPONENT_TYPE() { return getToken(StopTemplatesParser.COMPONENT_TYPE, 0); }
		public List<Component_parameterContext> component_parameter() {
			return getRuleContexts(Component_parameterContext.class);
		}
		public Component_parameterContext component_parameter(int i) {
			return getRuleContext(Component_parameterContext.class,i);
		}
		public ComponentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_component; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof StopTemplatesListener ) ((StopTemplatesListener)listener).enterComponent(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof StopTemplatesListener ) ((StopTemplatesListener)listener).exitComponent(this);
		}
	}

	public final ComponentContext component() throws RecognitionException {
		ComponentContext _localctx = new ComponentContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_component);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(46);
			match(COMPONENT_TYPE);
			setState(47);
			match(T__0);
			setState(58);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==ID) {
				{
				{
				setState(48);
				component_parameter();
				setState(53);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__1) {
					{
					{
					setState(49);
					match(T__1);
					setState(50);
					component_parameter();
					}
					}
					setState(55);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
				}
				setState(60);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(61);
			match(T__2);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Component_parameterContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(StopTemplatesParser.ID, 0); }
		public Component_parameter_value_or_collectionContext component_parameter_value_or_collection() {
			return getRuleContext(Component_parameter_value_or_collectionContext.class,0);
		}
		public Component_parameterContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_component_parameter; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof StopTemplatesListener ) ((StopTemplatesListener)listener).enterComponent_parameter(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof StopTemplatesListener ) ((StopTemplatesListener)listener).exitComponent_parameter(this);
		}
	}

	public final Component_parameterContext component_parameter() throws RecognitionException {
		Component_parameterContext _localctx = new Component_parameterContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_component_parameter);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(63);
			match(ID);
			setState(64);
			match(T__3);
			setState(65);
			component_parameter_value_or_collection();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Component_parameter_value_or_collectionContext extends ParserRuleContext {
		public Component_parameter_value_collectionContext component_parameter_value_collection() {
			return getRuleContext(Component_parameter_value_collectionContext.class,0);
		}
		public Component_parameter_valueContext component_parameter_value() {
			return getRuleContext(Component_parameter_valueContext.class,0);
		}
		public Component_parameter_value_or_collectionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_component_parameter_value_or_collection; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof StopTemplatesListener ) ((StopTemplatesListener)listener).enterComponent_parameter_value_or_collection(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof StopTemplatesListener ) ((StopTemplatesListener)listener).exitComponent_parameter_value_or_collection(this);
		}
	}

	public final Component_parameter_value_or_collectionContext component_parameter_value_or_collection() throws RecognitionException {
		Component_parameter_value_or_collectionContext _localctx = new Component_parameter_value_or_collectionContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_component_parameter_value_or_collection);
		try {
			setState(69);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__4:
				enterOuterAlt(_localctx, 1);
				{
				setState(67);
				component_parameter_value_collection();
				}
				break;
			case STRING_LITERAL:
			case NUMBER_LITERAL:
			case BOOL_LITERAL:
			case ID:
			case REFERENCE:
				enterOuterAlt(_localctx, 2);
				{
				setState(68);
				component_parameter_value();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Component_parameter_value_collectionContext extends ParserRuleContext {
		public List<Component_parameter_valueContext> component_parameter_value() {
			return getRuleContexts(Component_parameter_valueContext.class);
		}
		public Component_parameter_valueContext component_parameter_value(int i) {
			return getRuleContext(Component_parameter_valueContext.class,i);
		}
		public Component_parameter_value_collectionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_component_parameter_value_collection; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof StopTemplatesListener ) ((StopTemplatesListener)listener).enterComponent_parameter_value_collection(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof StopTemplatesListener ) ((StopTemplatesListener)listener).exitComponent_parameter_value_collection(this);
		}
	}

	public final Component_parameter_value_collectionContext component_parameter_value_collection() throws RecognitionException {
		Component_parameter_value_collectionContext _localctx = new Component_parameter_value_collectionContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_component_parameter_value_collection);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(71);
			match(T__4);
			setState(82);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << STRING_LITERAL) | (1L << NUMBER_LITERAL) | (1L << BOOL_LITERAL) | (1L << ID) | (1L << REFERENCE))) != 0)) {
				{
				{
				setState(72);
				component_parameter_value();
				setState(77);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__1) {
					{
					{
					setState(73);
					match(T__1);
					setState(74);
					component_parameter_value();
					}
					}
					setState(79);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
				}
				setState(84);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(85);
			match(T__5);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Component_parameter_valueContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(StopTemplatesParser.ID, 0); }
		public TerminalNode REFERENCE() { return getToken(StopTemplatesParser.REFERENCE, 0); }
		public TerminalNode STRING_LITERAL() { return getToken(StopTemplatesParser.STRING_LITERAL, 0); }
		public TerminalNode NUMBER_LITERAL() { return getToken(StopTemplatesParser.NUMBER_LITERAL, 0); }
		public TerminalNode BOOL_LITERAL() { return getToken(StopTemplatesParser.BOOL_LITERAL, 0); }
		public Component_parameter_valueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_component_parameter_value; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof StopTemplatesListener ) ((StopTemplatesListener)listener).enterComponent_parameter_value(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof StopTemplatesListener ) ((StopTemplatesListener)listener).exitComponent_parameter_value(this);
		}
	}

	public final Component_parameter_valueContext component_parameter_value() throws RecognitionException {
		Component_parameter_valueContext _localctx = new Component_parameter_valueContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_component_parameter_value);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(87);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << STRING_LITERAL) | (1L << NUMBER_LITERAL) | (1L << BOOL_LITERAL) | (1L << ID) | (1L << REFERENCE))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CollectionContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(StopTemplatesParser.ID, 0); }
		public TerminalNode REFERENCE() { return getToken(StopTemplatesParser.REFERENCE, 0); }
		public TerminalNode NOT() { return getToken(StopTemplatesParser.NOT, 0); }
		public CollectionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_collection; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof StopTemplatesListener ) ((StopTemplatesListener)listener).enterCollection(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof StopTemplatesListener ) ((StopTemplatesListener)listener).exitCollection(this);
		}
	}

	public final CollectionContext collection() throws RecognitionException {
		CollectionContext _localctx = new CollectionContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_collection);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(90);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==NOT) {
				{
				setState(89);
				match(NOT);
				}
			}

			setState(92);
			match(T__4);
			setState(93);
			_la = _input.LA(1);
			if ( !(_la==ID || _la==REFERENCE) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			setState(94);
			match(T__5);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ConditionalContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(StopTemplatesParser.ID, 0); }
		public TerminalNode REFERENCE() { return getToken(StopTemplatesParser.REFERENCE, 0); }
		public TerminalNode NOT() { return getToken(StopTemplatesParser.NOT, 0); }
		public ConditionalContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_conditional; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof StopTemplatesListener ) ((StopTemplatesListener)listener).enterConditional(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof StopTemplatesListener ) ((StopTemplatesListener)listener).exitConditional(this);
		}
	}

	public final ConditionalContext conditional() throws RecognitionException {
		ConditionalContext _localctx = new ConditionalContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_conditional);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(97);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==NOT) {
				{
				setState(96);
				match(NOT);
				}
			}

			setState(99);
			_la = _input.LA(1);
			if ( !(_la==ID || _la==REFERENCE) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\26h\4\2\t\2\4\3\t"+
		"\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t\13\3"+
		"\2\3\2\7\2\31\n\2\f\2\16\2\34\13\2\3\2\3\2\3\3\3\3\3\3\5\3#\n\3\3\3\3"+
		"\3\5\3\'\n\3\3\4\3\4\6\4+\n\4\r\4\16\4,\3\4\3\4\3\5\3\5\3\5\3\5\3\5\7"+
		"\5\66\n\5\f\5\16\59\13\5\7\5;\n\5\f\5\16\5>\13\5\3\5\3\5\3\6\3\6\3\6\3"+
		"\6\3\7\3\7\5\7H\n\7\3\b\3\b\3\b\3\b\7\bN\n\b\f\b\16\bQ\13\b\7\bS\n\b\f"+
		"\b\16\bV\13\b\3\b\3\b\3\t\3\t\3\n\5\n]\n\n\3\n\3\n\3\n\3\n\3\13\5\13d"+
		"\n\13\3\13\3\13\3\13\2\2\f\2\4\6\b\n\f\16\20\22\24\2\4\4\2\t\13\21\22"+
		"\3\2\21\22\2j\2\32\3\2\2\2\4\"\3\2\2\2\6(\3\2\2\2\b\60\3\2\2\2\nA\3\2"+
		"\2\2\fG\3\2\2\2\16I\3\2\2\2\20Y\3\2\2\2\22\\\3\2\2\2\24c\3\2\2\2\26\31"+
		"\7\24\2\2\27\31\5\4\3\2\30\26\3\2\2\2\30\27\3\2\2\2\31\34\3\2\2\2\32\30"+
		"\3\2\2\2\32\33\3\2\2\2\33\35\3\2\2\2\34\32\3\2\2\2\35\36\7\2\2\3\36\3"+
		"\3\2\2\2\37#\5\b\5\2 #\5\22\n\2!#\5\24\13\2\"\37\3\2\2\2\" \3\2\2\2\""+
		"!\3\2\2\2#$\3\2\2\2$&\7\24\2\2%\'\5\6\4\2&%\3\2\2\2&\'\3\2\2\2\'\5\3\2"+
		"\2\2(*\7\25\2\2)+\5\4\3\2*)\3\2\2\2+,\3\2\2\2,*\3\2\2\2,-\3\2\2\2-.\3"+
		"\2\2\2./\7\26\2\2/\7\3\2\2\2\60\61\7\20\2\2\61<\7\3\2\2\62\67\5\n\6\2"+
		"\63\64\7\4\2\2\64\66\5\n\6\2\65\63\3\2\2\2\669\3\2\2\2\67\65\3\2\2\2\67"+
		"8\3\2\2\28;\3\2\2\29\67\3\2\2\2:\62\3\2\2\2;>\3\2\2\2<:\3\2\2\2<=\3\2"+
		"\2\2=?\3\2\2\2><\3\2\2\2?@\7\5\2\2@\t\3\2\2\2AB\7\21\2\2BC\7\6\2\2CD\5"+
		"\f\7\2D\13\3\2\2\2EH\5\16\b\2FH\5\20\t\2GE\3\2\2\2GF\3\2\2\2H\r\3\2\2"+
		"\2IT\7\7\2\2JO\5\20\t\2KL\7\4\2\2LN\5\20\t\2MK\3\2\2\2NQ\3\2\2\2OM\3\2"+
		"\2\2OP\3\2\2\2PS\3\2\2\2QO\3\2\2\2RJ\3\2\2\2SV\3\2\2\2TR\3\2\2\2TU\3\2"+
		"\2\2UW\3\2\2\2VT\3\2\2\2WX\7\b\2\2X\17\3\2\2\2YZ\t\2\2\2Z\21\3\2\2\2["+
		"]\7\r\2\2\\[\3\2\2\2\\]\3\2\2\2]^\3\2\2\2^_\7\7\2\2_`\t\3\2\2`a\7\b\2"+
		"\2a\23\3\2\2\2bd\7\r\2\2cb\3\2\2\2cd\3\2\2\2de\3\2\2\2ef\t\3\2\2f\25\3"+
		"\2\2\2\16\30\32\"&,\67<GOT\\c";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}