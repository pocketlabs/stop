// Generated from /Users/kyleshank/git/stop/Stop.g4 by ANTLR 4.7.2
package org.stop_lang.parser;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class StopParser extends Parser {
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
	public static final int
		RULE_file = 0, RULE_include = 1, RULE_model = 2, RULE_block = 3, RULE_statement = 4, 
		RULE_block_with_timeout = 5, RULE_block_with_timeout_statement = 6, RULE_return_block = 7, 
		RULE_return_block_statement = 8, RULE_return_block_with_timeout = 9, RULE_return_block_with_timeout_statement = 10, 
		RULE_scalar_type = 11, RULE_model_type = 12, RULE_enum_type = 13, RULE_enum_value = 14, 
		RULE_enumeration = 15, RULE_field = 16, RULE_async_source = 17, RULE_async_source_mapping = 18, 
		RULE_async_source_mapping_parameter = 19, RULE_async_source_mapping_parameter_rename = 20, 
		RULE_type = 21, RULE_collection = 22, RULE_return_type = 23, RULE_throw_type = 24, 
		RULE_throw_parameter = 25, RULE_timeout = 26, RULE_transition = 27, RULE_enqueue = 28;
	private static String[] makeRuleNames() {
		return new String[] {
			"file", "include", "model", "block", "statement", "block_with_timeout", 
			"block_with_timeout_statement", "return_block", "return_block_statement", 
			"return_block_with_timeout", "return_block_with_timeout_statement", "scalar_type", 
			"model_type", "enum_type", "enum_value", "enumeration", "field", "async_source", 
			"async_source_mapping", "async_source_mapping_parameter", "async_source_mapping_parameter_rename", 
			"type", "collection", "return_type", "throw_type", "throw_parameter", 
			"timeout", "transition", "enqueue"
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

	@Override
	public String getGrammarFileName() { return "Stop.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public StopParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class FileContext extends ParserRuleContext {
		public List<IncludeContext> include() {
			return getRuleContexts(IncludeContext.class);
		}
		public IncludeContext include(int i) {
			return getRuleContext(IncludeContext.class,i);
		}
		public List<ModelContext> model() {
			return getRuleContexts(ModelContext.class);
		}
		public ModelContext model(int i) {
			return getRuleContext(ModelContext.class,i);
		}
		public FileContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_file; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof StopListener ) ((StopListener)listener).enterFile(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof StopListener ) ((StopListener)listener).exitFile(this);
		}
	}

	public final FileContext file() throws RecognitionException {
		FileContext _localctx = new FileContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_file);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(60); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				setState(60);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case T__0:
					{
					setState(58);
					include();
					}
					break;
				case ASYNC:
				case START:
				case STOP:
				case QUEUE:
				case MODEL_TYPE:
					{
					setState(59);
					model();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(62); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__0) | (1L << ASYNC) | (1L << START) | (1L << STOP) | (1L << QUEUE) | (1L << MODEL_TYPE))) != 0) );
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

	public static class IncludeContext extends ParserRuleContext {
		public TerminalNode FILENAME() { return getToken(StopParser.FILENAME, 0); }
		public IncludeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_include; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof StopListener ) ((StopListener)listener).enterInclude(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof StopListener ) ((StopListener)listener).exitInclude(this);
		}
	}

	public final IncludeContext include() throws RecognitionException {
		IncludeContext _localctx = new IncludeContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_include);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(64);
			match(T__0);
			setState(65);
			match(T__1);
			setState(66);
			match(FILENAME);
			setState(67);
			match(T__1);
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

	public static class ModelContext extends ParserRuleContext {
		public TerminalNode MODEL_TYPE() { return getToken(StopParser.MODEL_TYPE, 0); }
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public TerminalNode START() { return getToken(StopParser.START, 0); }
		public Throw_typeContext throw_type() {
			return getRuleContext(Throw_typeContext.class,0);
		}
		public TerminalNode STOP() { return getToken(StopParser.STOP, 0); }
		public TerminalNode QUEUE() { return getToken(StopParser.QUEUE, 0); }
		public TerminalNode ASYNC() { return getToken(StopParser.ASYNC, 0); }
		public Block_with_timeoutContext block_with_timeout() {
			return getRuleContext(Block_with_timeoutContext.class,0);
		}
		public TerminalNode RETURN_OP() { return getToken(StopParser.RETURN_OP, 0); }
		public Return_typeContext return_type() {
			return getRuleContext(Return_typeContext.class,0);
		}
		public Return_block_with_timeoutContext return_block_with_timeout() {
			return getRuleContext(Return_block_with_timeoutContext.class,0);
		}
		public Return_blockContext return_block() {
			return getRuleContext(Return_blockContext.class,0);
		}
		public ModelContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_model; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof StopListener ) ((StopListener)listener).enterModel(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof StopListener ) ((StopListener)listener).exitModel(this);
		}
	}

	public final ModelContext model() throws RecognitionException {
		ModelContext _localctx = new ModelContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_model);
		int _la;
		try {
			setState(116);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,11,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(70);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==START) {
					{
					setState(69);
					match(START);
					}
				}

				setState(72);
				match(MODEL_TYPE);
				setState(74);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__28) {
					{
					setState(73);
					throw_type();
					}
				}

				setState(76);
				block();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(78);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==STOP) {
					{
					setState(77);
					match(STOP);
					}
				}

				setState(80);
				match(MODEL_TYPE);
				setState(82);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__28) {
					{
					setState(81);
					throw_type();
					}
				}

				setState(84);
				block();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(86);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==QUEUE) {
					{
					setState(85);
					match(QUEUE);
					}
				}

				setState(88);
				match(MODEL_TYPE);
				setState(90);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__28) {
					{
					setState(89);
					throw_type();
					}
				}

				setState(92);
				block();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(93);
				match(ASYNC);
				setState(94);
				match(MODEL_TYPE);
				setState(96);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__28) {
					{
					setState(95);
					throw_type();
					}
				}

				setState(98);
				block_with_timeout();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(99);
				match(ASYNC);
				setState(100);
				match(MODEL_TYPE);
				setState(101);
				match(RETURN_OP);
				setState(102);
				return_type();
				setState(104);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__28) {
					{
					setState(103);
					throw_type();
					}
				}

				setState(106);
				return_block_with_timeout();
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(108);
				match(MODEL_TYPE);
				setState(109);
				match(RETURN_OP);
				setState(110);
				return_type();
				setState(112);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__28) {
					{
					setState(111);
					throw_type();
					}
				}

				setState(114);
				return_block();
				}
				break;
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

	public static class BlockContext extends ParserRuleContext {
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public BlockContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_block; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof StopListener ) ((StopListener)listener).enterBlock(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof StopListener ) ((StopListener)listener).exitBlock(this);
		}
	}

	public final BlockContext block() throws RecognitionException {
		BlockContext _localctx = new BlockContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_block);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(118);
			match(T__2);
			setState(125);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__5) | (1L << T__6) | (1L << T__7) | (1L << T__8) | (1L << T__9) | (1L << T__10) | (1L << T__11) | (1L << T__12) | (1L << T__13) | (1L << T__14) | (1L << T__15) | (1L << T__16) | (1L << T__17) | (1L << T__18) | (1L << T__19) | (1L << T__20) | (1L << T__21) | (1L << T__26) | (1L << TRANSITION_OP) | (1L << ENQUEUE_OP) | (1L << OPTIONAL) | (1L << MODEL_TYPE))) != 0)) {
				{
				{
				setState(119);
				statement();
				setState(121);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__3) {
					{
					setState(120);
					match(T__3);
					}
				}

				}
				}
				setState(127);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(128);
			match(T__4);
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
		public EnumerationContext enumeration() {
			return getRuleContext(EnumerationContext.class,0);
		}
		public FieldContext field() {
			return getRuleContext(FieldContext.class,0);
		}
		public TransitionContext transition() {
			return getRuleContext(TransitionContext.class,0);
		}
		public EnqueueContext enqueue() {
			return getRuleContext(EnqueueContext.class,0);
		}
		public StatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_statement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof StopListener ) ((StopListener)listener).enterStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof StopListener ) ((StopListener)listener).exitStatement(this);
		}
	}

	public final StatementContext statement() throws RecognitionException {
		StatementContext _localctx = new StatementContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_statement);
		try {
			setState(134);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__21:
				enterOuterAlt(_localctx, 1);
				{
				setState(130);
				enumeration();
				}
				break;
			case T__5:
			case T__6:
			case T__7:
			case T__8:
			case T__9:
			case T__10:
			case T__11:
			case T__12:
			case T__13:
			case T__14:
			case T__15:
			case T__16:
			case T__17:
			case T__18:
			case T__19:
			case T__20:
			case T__26:
			case OPTIONAL:
			case MODEL_TYPE:
				enterOuterAlt(_localctx, 2);
				{
				setState(131);
				field();
				}
				break;
			case TRANSITION_OP:
				enterOuterAlt(_localctx, 3);
				{
				setState(132);
				transition();
				}
				break;
			case ENQUEUE_OP:
				enterOuterAlt(_localctx, 4);
				{
				setState(133);
				enqueue();
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

	public static class Block_with_timeoutContext extends ParserRuleContext {
		public List<Block_with_timeout_statementContext> block_with_timeout_statement() {
			return getRuleContexts(Block_with_timeout_statementContext.class);
		}
		public Block_with_timeout_statementContext block_with_timeout_statement(int i) {
			return getRuleContext(Block_with_timeout_statementContext.class,i);
		}
		public Block_with_timeoutContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_block_with_timeout; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof StopListener ) ((StopListener)listener).enterBlock_with_timeout(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof StopListener ) ((StopListener)listener).exitBlock_with_timeout(this);
		}
	}

	public final Block_with_timeoutContext block_with_timeout() throws RecognitionException {
		Block_with_timeoutContext _localctx = new Block_with_timeoutContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_block_with_timeout);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(136);
			match(T__2);
			setState(143);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__5) | (1L << T__6) | (1L << T__7) | (1L << T__8) | (1L << T__9) | (1L << T__10) | (1L << T__11) | (1L << T__12) | (1L << T__13) | (1L << T__14) | (1L << T__15) | (1L << T__16) | (1L << T__17) | (1L << T__18) | (1L << T__19) | (1L << T__20) | (1L << T__21) | (1L << T__26) | (1L << T__29) | (1L << TRANSITION_OP) | (1L << ENQUEUE_OP) | (1L << OPTIONAL) | (1L << MODEL_TYPE))) != 0)) {
				{
				{
				setState(137);
				block_with_timeout_statement();
				setState(139);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__3) {
					{
					setState(138);
					match(T__3);
					}
				}

				}
				}
				setState(145);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(146);
			match(T__4);
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

	public static class Block_with_timeout_statementContext extends ParserRuleContext {
		public EnumerationContext enumeration() {
			return getRuleContext(EnumerationContext.class,0);
		}
		public FieldContext field() {
			return getRuleContext(FieldContext.class,0);
		}
		public TransitionContext transition() {
			return getRuleContext(TransitionContext.class,0);
		}
		public TimeoutContext timeout() {
			return getRuleContext(TimeoutContext.class,0);
		}
		public EnqueueContext enqueue() {
			return getRuleContext(EnqueueContext.class,0);
		}
		public Block_with_timeout_statementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_block_with_timeout_statement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof StopListener ) ((StopListener)listener).enterBlock_with_timeout_statement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof StopListener ) ((StopListener)listener).exitBlock_with_timeout_statement(this);
		}
	}

	public final Block_with_timeout_statementContext block_with_timeout_statement() throws RecognitionException {
		Block_with_timeout_statementContext _localctx = new Block_with_timeout_statementContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_block_with_timeout_statement);
		try {
			setState(153);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__21:
				enterOuterAlt(_localctx, 1);
				{
				setState(148);
				enumeration();
				}
				break;
			case T__5:
			case T__6:
			case T__7:
			case T__8:
			case T__9:
			case T__10:
			case T__11:
			case T__12:
			case T__13:
			case T__14:
			case T__15:
			case T__16:
			case T__17:
			case T__18:
			case T__19:
			case T__20:
			case T__26:
			case OPTIONAL:
			case MODEL_TYPE:
				enterOuterAlt(_localctx, 2);
				{
				setState(149);
				field();
				}
				break;
			case TRANSITION_OP:
				enterOuterAlt(_localctx, 3);
				{
				setState(150);
				transition();
				}
				break;
			case T__29:
				enterOuterAlt(_localctx, 4);
				{
				setState(151);
				timeout();
				}
				break;
			case ENQUEUE_OP:
				enterOuterAlt(_localctx, 5);
				{
				setState(152);
				enqueue();
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

	public static class Return_blockContext extends ParserRuleContext {
		public List<Return_block_statementContext> return_block_statement() {
			return getRuleContexts(Return_block_statementContext.class);
		}
		public Return_block_statementContext return_block_statement(int i) {
			return getRuleContext(Return_block_statementContext.class,i);
		}
		public Return_blockContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_return_block; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof StopListener ) ((StopListener)listener).enterReturn_block(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof StopListener ) ((StopListener)listener).exitReturn_block(this);
		}
	}

	public final Return_blockContext return_block() throws RecognitionException {
		Return_blockContext _localctx = new Return_blockContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_return_block);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(155);
			match(T__2);
			setState(162);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__5) | (1L << T__6) | (1L << T__7) | (1L << T__8) | (1L << T__9) | (1L << T__10) | (1L << T__11) | (1L << T__12) | (1L << T__13) | (1L << T__14) | (1L << T__15) | (1L << T__16) | (1L << T__17) | (1L << T__18) | (1L << T__19) | (1L << T__20) | (1L << T__21) | (1L << T__26) | (1L << OPTIONAL) | (1L << MODEL_TYPE))) != 0)) {
				{
				{
				setState(156);
				return_block_statement();
				setState(158);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__3) {
					{
					setState(157);
					match(T__3);
					}
				}

				}
				}
				setState(164);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(165);
			match(T__4);
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

	public static class Return_block_statementContext extends ParserRuleContext {
		public EnumerationContext enumeration() {
			return getRuleContext(EnumerationContext.class,0);
		}
		public FieldContext field() {
			return getRuleContext(FieldContext.class,0);
		}
		public Return_block_statementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_return_block_statement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof StopListener ) ((StopListener)listener).enterReturn_block_statement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof StopListener ) ((StopListener)listener).exitReturn_block_statement(this);
		}
	}

	public final Return_block_statementContext return_block_statement() throws RecognitionException {
		Return_block_statementContext _localctx = new Return_block_statementContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_return_block_statement);
		try {
			setState(169);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__21:
				enterOuterAlt(_localctx, 1);
				{
				setState(167);
				enumeration();
				}
				break;
			case T__5:
			case T__6:
			case T__7:
			case T__8:
			case T__9:
			case T__10:
			case T__11:
			case T__12:
			case T__13:
			case T__14:
			case T__15:
			case T__16:
			case T__17:
			case T__18:
			case T__19:
			case T__20:
			case T__26:
			case OPTIONAL:
			case MODEL_TYPE:
				enterOuterAlt(_localctx, 2);
				{
				setState(168);
				field();
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

	public static class Return_block_with_timeoutContext extends ParserRuleContext {
		public List<Return_block_with_timeout_statementContext> return_block_with_timeout_statement() {
			return getRuleContexts(Return_block_with_timeout_statementContext.class);
		}
		public Return_block_with_timeout_statementContext return_block_with_timeout_statement(int i) {
			return getRuleContext(Return_block_with_timeout_statementContext.class,i);
		}
		public Return_block_with_timeoutContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_return_block_with_timeout; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof StopListener ) ((StopListener)listener).enterReturn_block_with_timeout(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof StopListener ) ((StopListener)listener).exitReturn_block_with_timeout(this);
		}
	}

	public final Return_block_with_timeoutContext return_block_with_timeout() throws RecognitionException {
		Return_block_with_timeoutContext _localctx = new Return_block_with_timeoutContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_return_block_with_timeout);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(171);
			match(T__2);
			setState(178);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__5) | (1L << T__6) | (1L << T__7) | (1L << T__8) | (1L << T__9) | (1L << T__10) | (1L << T__11) | (1L << T__12) | (1L << T__13) | (1L << T__14) | (1L << T__15) | (1L << T__16) | (1L << T__17) | (1L << T__18) | (1L << T__19) | (1L << T__20) | (1L << T__21) | (1L << T__26) | (1L << T__29) | (1L << OPTIONAL) | (1L << MODEL_TYPE))) != 0)) {
				{
				{
				setState(172);
				return_block_with_timeout_statement();
				setState(174);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__3) {
					{
					setState(173);
					match(T__3);
					}
				}

				}
				}
				setState(180);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(181);
			match(T__4);
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

	public static class Return_block_with_timeout_statementContext extends ParserRuleContext {
		public EnumerationContext enumeration() {
			return getRuleContext(EnumerationContext.class,0);
		}
		public FieldContext field() {
			return getRuleContext(FieldContext.class,0);
		}
		public TimeoutContext timeout() {
			return getRuleContext(TimeoutContext.class,0);
		}
		public Return_block_with_timeout_statementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_return_block_with_timeout_statement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof StopListener ) ((StopListener)listener).enterReturn_block_with_timeout_statement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof StopListener ) ((StopListener)listener).exitReturn_block_with_timeout_statement(this);
		}
	}

	public final Return_block_with_timeout_statementContext return_block_with_timeout_statement() throws RecognitionException {
		Return_block_with_timeout_statementContext _localctx = new Return_block_with_timeout_statementContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_return_block_with_timeout_statement);
		try {
			setState(186);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__21:
				enterOuterAlt(_localctx, 1);
				{
				setState(183);
				enumeration();
				}
				break;
			case T__5:
			case T__6:
			case T__7:
			case T__8:
			case T__9:
			case T__10:
			case T__11:
			case T__12:
			case T__13:
			case T__14:
			case T__15:
			case T__16:
			case T__17:
			case T__18:
			case T__19:
			case T__20:
			case T__26:
			case OPTIONAL:
			case MODEL_TYPE:
				enterOuterAlt(_localctx, 2);
				{
				setState(184);
				field();
				}
				break;
			case T__29:
				enterOuterAlt(_localctx, 3);
				{
				setState(185);
				timeout();
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

	public static class Scalar_typeContext extends ParserRuleContext {
		public Scalar_typeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_scalar_type; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof StopListener ) ((StopListener)listener).enterScalar_type(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof StopListener ) ((StopListener)listener).exitScalar_type(this);
		}
	}

	public final Scalar_typeContext scalar_type() throws RecognitionException {
		Scalar_typeContext _localctx = new Scalar_typeContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_scalar_type);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(188);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__5) | (1L << T__6) | (1L << T__7) | (1L << T__8) | (1L << T__9) | (1L << T__10) | (1L << T__11) | (1L << T__12) | (1L << T__13) | (1L << T__14) | (1L << T__15) | (1L << T__16) | (1L << T__17) | (1L << T__18) | (1L << T__19) | (1L << T__20))) != 0)) ) {
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

	public static class Model_typeContext extends ParserRuleContext {
		public TerminalNode MODEL_TYPE() { return getToken(StopParser.MODEL_TYPE, 0); }
		public Model_typeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_model_type; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof StopListener ) ((StopListener)listener).enterModel_type(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof StopListener ) ((StopListener)listener).exitModel_type(this);
		}
	}

	public final Model_typeContext model_type() throws RecognitionException {
		Model_typeContext _localctx = new Model_typeContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_model_type);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(190);
			match(MODEL_TYPE);
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

	public static class Enum_typeContext extends ParserRuleContext {
		public TerminalNode MODEL_TYPE() { return getToken(StopParser.MODEL_TYPE, 0); }
		public Enum_typeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_enum_type; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof StopListener ) ((StopListener)listener).enterEnum_type(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof StopListener ) ((StopListener)listener).exitEnum_type(this);
		}
	}

	public final Enum_typeContext enum_type() throws RecognitionException {
		Enum_typeContext _localctx = new Enum_typeContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_enum_type);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(192);
			match(MODEL_TYPE);
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

	public static class Enum_valueContext extends ParserRuleContext {
		public TerminalNode MODEL_TYPE() { return getToken(StopParser.MODEL_TYPE, 0); }
		public Enum_valueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_enum_value; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof StopListener ) ((StopListener)listener).enterEnum_value(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof StopListener ) ((StopListener)listener).exitEnum_value(this);
		}
	}

	public final Enum_valueContext enum_value() throws RecognitionException {
		Enum_valueContext _localctx = new Enum_valueContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_enum_value);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(194);
			match(MODEL_TYPE);
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

	public static class EnumerationContext extends ParserRuleContext {
		public Enum_typeContext enum_type() {
			return getRuleContext(Enum_typeContext.class,0);
		}
		public List<Enum_valueContext> enum_value() {
			return getRuleContexts(Enum_valueContext.class);
		}
		public Enum_valueContext enum_value(int i) {
			return getRuleContext(Enum_valueContext.class,i);
		}
		public EnumerationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_enumeration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof StopListener ) ((StopListener)listener).enterEnumeration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof StopListener ) ((StopListener)listener).exitEnumeration(this);
		}
	}

	public final EnumerationContext enumeration() throws RecognitionException {
		EnumerationContext _localctx = new EnumerationContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_enumeration);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(196);
			match(T__21);
			setState(197);
			enum_type();
			setState(198);
			match(T__2);
			setState(200); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(199);
				enum_value();
				}
				}
				setState(202); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==MODEL_TYPE );
			setState(204);
			match(T__4);
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

	public static class FieldContext extends ParserRuleContext {
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public TerminalNode ID() { return getToken(StopParser.ID, 0); }
		public TerminalNode OPTIONAL() { return getToken(StopParser.OPTIONAL, 0); }
		public Async_sourceContext async_source() {
			return getRuleContext(Async_sourceContext.class,0);
		}
		public CollectionContext collection() {
			return getRuleContext(CollectionContext.class,0);
		}
		public FieldContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_field; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof StopListener ) ((StopListener)listener).enterField(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof StopListener ) ((StopListener)listener).exitField(this);
		}
	}

	public final FieldContext field() throws RecognitionException {
		FieldContext _localctx = new FieldContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_field);
		int _la;
		try {
			setState(222);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,29,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(207);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==OPTIONAL) {
					{
					setState(206);
					match(OPTIONAL);
					}
				}

				setState(209);
				type();
				setState(210);
				match(ID);
				setState(212);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==RETURN_OP) {
					{
					setState(211);
					async_source();
					}
				}

				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(215);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==OPTIONAL) {
					{
					setState(214);
					match(OPTIONAL);
					}
				}

				setState(217);
				collection();
				setState(218);
				match(ID);
				setState(220);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==RETURN_OP) {
					{
					setState(219);
					async_source();
					}
				}

				}
				break;
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

	public static class Async_sourceContext extends ParserRuleContext {
		public TerminalNode RETURN_OP() { return getToken(StopParser.RETURN_OP, 0); }
		public TerminalNode MODEL_TYPE() { return getToken(StopParser.MODEL_TYPE, 0); }
		public Async_source_mappingContext async_source_mapping() {
			return getRuleContext(Async_source_mappingContext.class,0);
		}
		public Async_sourceContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_async_source; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof StopListener ) ((StopListener)listener).enterAsync_source(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof StopListener ) ((StopListener)listener).exitAsync_source(this);
		}
	}

	public final Async_sourceContext async_source() throws RecognitionException {
		Async_sourceContext _localctx = new Async_sourceContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_async_source);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(224);
			match(RETURN_OP);
			setState(225);
			match(MODEL_TYPE);
			setState(227);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__22) {
				{
				setState(226);
				async_source_mapping();
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

	public static class Async_source_mappingContext extends ParserRuleContext {
		public List<Async_source_mapping_parameterContext> async_source_mapping_parameter() {
			return getRuleContexts(Async_source_mapping_parameterContext.class);
		}
		public Async_source_mapping_parameterContext async_source_mapping_parameter(int i) {
			return getRuleContext(Async_source_mapping_parameterContext.class,i);
		}
		public Async_source_mappingContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_async_source_mapping; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof StopListener ) ((StopListener)listener).enterAsync_source_mapping(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof StopListener ) ((StopListener)listener).exitAsync_source_mapping(this);
		}
	}

	public final Async_source_mappingContext async_source_mapping() throws RecognitionException {
		Async_source_mappingContext _localctx = new Async_source_mappingContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_async_source_mapping);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(229);
			match(T__22);
			setState(230);
			async_source_mapping_parameter();
			setState(235);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__23) {
				{
				{
				setState(231);
				match(T__23);
				setState(232);
				async_source_mapping_parameter();
				}
				}
				setState(237);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(238);
			match(T__24);
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

	public static class Async_source_mapping_parameterContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(StopParser.ID, 0); }
		public Async_source_mapping_parameter_renameContext async_source_mapping_parameter_rename() {
			return getRuleContext(Async_source_mapping_parameter_renameContext.class,0);
		}
		public Async_source_mapping_parameterContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_async_source_mapping_parameter; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof StopListener ) ((StopListener)listener).enterAsync_source_mapping_parameter(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof StopListener ) ((StopListener)listener).exitAsync_source_mapping_parameter(this);
		}
	}

	public final Async_source_mapping_parameterContext async_source_mapping_parameter() throws RecognitionException {
		Async_source_mapping_parameterContext _localctx = new Async_source_mapping_parameterContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_async_source_mapping_parameter);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(240);
			match(ID);
			setState(241);
			match(T__25);
			setState(242);
			async_source_mapping_parameter_rename();
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

	public static class Async_source_mapping_parameter_renameContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(StopParser.ID, 0); }
		public TerminalNode REFERENCE() { return getToken(StopParser.REFERENCE, 0); }
		public Async_source_mapping_parameter_renameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_async_source_mapping_parameter_rename; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof StopListener ) ((StopListener)listener).enterAsync_source_mapping_parameter_rename(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof StopListener ) ((StopListener)listener).exitAsync_source_mapping_parameter_rename(this);
		}
	}

	public final Async_source_mapping_parameter_renameContext async_source_mapping_parameter_rename() throws RecognitionException {
		Async_source_mapping_parameter_renameContext _localctx = new Async_source_mapping_parameter_renameContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_async_source_mapping_parameter_rename);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(244);
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

	public static class TypeContext extends ParserRuleContext {
		public Scalar_typeContext scalar_type() {
			return getRuleContext(Scalar_typeContext.class,0);
		}
		public Model_typeContext model_type() {
			return getRuleContext(Model_typeContext.class,0);
		}
		public TypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_type; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof StopListener ) ((StopListener)listener).enterType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof StopListener ) ((StopListener)listener).exitType(this);
		}
	}

	public final TypeContext type() throws RecognitionException {
		TypeContext _localctx = new TypeContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_type);
		try {
			setState(248);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__5:
			case T__6:
			case T__7:
			case T__8:
			case T__9:
			case T__10:
			case T__11:
			case T__12:
			case T__13:
			case T__14:
			case T__15:
			case T__16:
			case T__17:
			case T__18:
			case T__19:
			case T__20:
				enterOuterAlt(_localctx, 1);
				{
				setState(246);
				scalar_type();
				}
				break;
			case MODEL_TYPE:
				enterOuterAlt(_localctx, 2);
				{
				setState(247);
				model_type();
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

	public static class CollectionContext extends ParserRuleContext {
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public CollectionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_collection; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof StopListener ) ((StopListener)listener).enterCollection(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof StopListener ) ((StopListener)listener).exitCollection(this);
		}
	}

	public final CollectionContext collection() throws RecognitionException {
		CollectionContext _localctx = new CollectionContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_collection);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(250);
			match(T__26);
			setState(251);
			type();
			setState(252);
			match(T__27);
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

	public static class Return_typeContext extends ParserRuleContext {
		public CollectionContext collection() {
			return getRuleContext(CollectionContext.class,0);
		}
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public Return_typeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_return_type; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof StopListener ) ((StopListener)listener).enterReturn_type(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof StopListener ) ((StopListener)listener).exitReturn_type(this);
		}
	}

	public final Return_typeContext return_type() throws RecognitionException {
		Return_typeContext _localctx = new Return_typeContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_return_type);
		try {
			setState(256);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__26:
				enterOuterAlt(_localctx, 1);
				{
				setState(254);
				collection();
				}
				break;
			case T__5:
			case T__6:
			case T__7:
			case T__8:
			case T__9:
			case T__10:
			case T__11:
			case T__12:
			case T__13:
			case T__14:
			case T__15:
			case T__16:
			case T__17:
			case T__18:
			case T__19:
			case T__20:
			case MODEL_TYPE:
				enterOuterAlt(_localctx, 2);
				{
				setState(255);
				type();
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

	public static class Throw_typeContext extends ParserRuleContext {
		public List<Throw_parameterContext> throw_parameter() {
			return getRuleContexts(Throw_parameterContext.class);
		}
		public Throw_parameterContext throw_parameter(int i) {
			return getRuleContext(Throw_parameterContext.class,i);
		}
		public Throw_typeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_throw_type; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof StopListener ) ((StopListener)listener).enterThrow_type(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof StopListener ) ((StopListener)listener).exitThrow_type(this);
		}
	}

	public final Throw_typeContext throw_type() throws RecognitionException {
		Throw_typeContext _localctx = new Throw_typeContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_throw_type);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(258);
			match(T__28);
			setState(259);
			throw_parameter();
			setState(264);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__23) {
				{
				{
				setState(260);
				match(T__23);
				setState(261);
				throw_parameter();
				}
				}
				setState(266);
				_errHandler.sync(this);
				_la = _input.LA(1);
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

	public static class Throw_parameterContext extends ParserRuleContext {
		public TerminalNode MODEL_TYPE() { return getToken(StopParser.MODEL_TYPE, 0); }
		public Throw_parameterContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_throw_parameter; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof StopListener ) ((StopListener)listener).enterThrow_parameter(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof StopListener ) ((StopListener)listener).exitThrow_parameter(this);
		}
	}

	public final Throw_parameterContext throw_parameter() throws RecognitionException {
		Throw_parameterContext _localctx = new Throw_parameterContext(_ctx, getState());
		enterRule(_localctx, 50, RULE_throw_parameter);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(267);
			match(MODEL_TYPE);
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

	public static class TimeoutContext extends ParserRuleContext {
		public TerminalNode NUMBER() { return getToken(StopParser.NUMBER, 0); }
		public TransitionContext transition() {
			return getRuleContext(TransitionContext.class,0);
		}
		public TimeoutContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_timeout; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof StopListener ) ((StopListener)listener).enterTimeout(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof StopListener ) ((StopListener)listener).exitTimeout(this);
		}
	}

	public final TimeoutContext timeout() throws RecognitionException {
		TimeoutContext _localctx = new TimeoutContext(_ctx, getState());
		enterRule(_localctx, 52, RULE_timeout);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(269);
			match(T__29);
			setState(270);
			match(NUMBER);
			setState(271);
			transition();
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

	public static class TransitionContext extends ParserRuleContext {
		public TerminalNode TRANSITION_OP() { return getToken(StopParser.TRANSITION_OP, 0); }
		public TerminalNode MODEL_TYPE() { return getToken(StopParser.MODEL_TYPE, 0); }
		public TransitionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_transition; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof StopListener ) ((StopListener)listener).enterTransition(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof StopListener ) ((StopListener)listener).exitTransition(this);
		}
	}

	public final TransitionContext transition() throws RecognitionException {
		TransitionContext _localctx = new TransitionContext(_ctx, getState());
		enterRule(_localctx, 54, RULE_transition);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(273);
			match(TRANSITION_OP);
			setState(274);
			match(MODEL_TYPE);
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

	public static class EnqueueContext extends ParserRuleContext {
		public TerminalNode ENQUEUE_OP() { return getToken(StopParser.ENQUEUE_OP, 0); }
		public TerminalNode MODEL_TYPE() { return getToken(StopParser.MODEL_TYPE, 0); }
		public EnqueueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_enqueue; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof StopListener ) ((StopListener)listener).enterEnqueue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof StopListener ) ((StopListener)listener).exitEnqueue(this);
		}
	}

	public final EnqueueContext enqueue() throws RecognitionException {
		EnqueueContext _localctx = new EnqueueContext(_ctx, getState());
		enterRule(_localctx, 56, RULE_enqueue);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(276);
			match(ENQUEUE_OP);
			setState(277);
			match(MODEL_TYPE);
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\61\u011a\4\2\t\2"+
		"\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\3\2\3\2\6\2?\n\2\r"+
		"\2\16\2@\3\3\3\3\3\3\3\3\3\3\3\4\5\4I\n\4\3\4\3\4\5\4M\n\4\3\4\3\4\5\4"+
		"Q\n\4\3\4\3\4\5\4U\n\4\3\4\3\4\5\4Y\n\4\3\4\3\4\5\4]\n\4\3\4\3\4\3\4\3"+
		"\4\5\4c\n\4\3\4\3\4\3\4\3\4\3\4\3\4\5\4k\n\4\3\4\3\4\3\4\3\4\3\4\3\4\5"+
		"\4s\n\4\3\4\3\4\5\4w\n\4\3\5\3\5\3\5\5\5|\n\5\7\5~\n\5\f\5\16\5\u0081"+
		"\13\5\3\5\3\5\3\6\3\6\3\6\3\6\5\6\u0089\n\6\3\7\3\7\3\7\5\7\u008e\n\7"+
		"\7\7\u0090\n\7\f\7\16\7\u0093\13\7\3\7\3\7\3\b\3\b\3\b\3\b\3\b\5\b\u009c"+
		"\n\b\3\t\3\t\3\t\5\t\u00a1\n\t\7\t\u00a3\n\t\f\t\16\t\u00a6\13\t\3\t\3"+
		"\t\3\n\3\n\5\n\u00ac\n\n\3\13\3\13\3\13\5\13\u00b1\n\13\7\13\u00b3\n\13"+
		"\f\13\16\13\u00b6\13\13\3\13\3\13\3\f\3\f\3\f\5\f\u00bd\n\f\3\r\3\r\3"+
		"\16\3\16\3\17\3\17\3\20\3\20\3\21\3\21\3\21\3\21\6\21\u00cb\n\21\r\21"+
		"\16\21\u00cc\3\21\3\21\3\22\5\22\u00d2\n\22\3\22\3\22\3\22\5\22\u00d7"+
		"\n\22\3\22\5\22\u00da\n\22\3\22\3\22\3\22\5\22\u00df\n\22\5\22\u00e1\n"+
		"\22\3\23\3\23\3\23\5\23\u00e6\n\23\3\24\3\24\3\24\3\24\7\24\u00ec\n\24"+
		"\f\24\16\24\u00ef\13\24\3\24\3\24\3\25\3\25\3\25\3\25\3\26\3\26\3\27\3"+
		"\27\5\27\u00fb\n\27\3\30\3\30\3\30\3\30\3\31\3\31\5\31\u0103\n\31\3\32"+
		"\3\32\3\32\3\32\7\32\u0109\n\32\f\32\16\32\u010c\13\32\3\33\3\33\3\34"+
		"\3\34\3\34\3\34\3\35\3\35\3\35\3\36\3\36\3\36\3\36\2\2\37\2\4\6\b\n\f"+
		"\16\20\22\24\26\30\32\34\36 \"$&(*,.\60\62\64\668:\2\4\3\2\b\27\3\2,-"+
		"\2\u0129\2>\3\2\2\2\4B\3\2\2\2\6v\3\2\2\2\bx\3\2\2\2\n\u0088\3\2\2\2\f"+
		"\u008a\3\2\2\2\16\u009b\3\2\2\2\20\u009d\3\2\2\2\22\u00ab\3\2\2\2\24\u00ad"+
		"\3\2\2\2\26\u00bc\3\2\2\2\30\u00be\3\2\2\2\32\u00c0\3\2\2\2\34\u00c2\3"+
		"\2\2\2\36\u00c4\3\2\2\2 \u00c6\3\2\2\2\"\u00e0\3\2\2\2$\u00e2\3\2\2\2"+
		"&\u00e7\3\2\2\2(\u00f2\3\2\2\2*\u00f6\3\2\2\2,\u00fa\3\2\2\2.\u00fc\3"+
		"\2\2\2\60\u0102\3\2\2\2\62\u0104\3\2\2\2\64\u010d\3\2\2\2\66\u010f\3\2"+
		"\2\28\u0113\3\2\2\2:\u0116\3\2\2\2<?\5\4\3\2=?\5\6\4\2><\3\2\2\2>=\3\2"+
		"\2\2?@\3\2\2\2@>\3\2\2\2@A\3\2\2\2A\3\3\2\2\2BC\7\3\2\2CD\7\4\2\2DE\7"+
		".\2\2EF\7\4\2\2F\5\3\2\2\2GI\7\"\2\2HG\3\2\2\2HI\3\2\2\2IJ\3\2\2\2JL\7"+
		"+\2\2KM\5\62\32\2LK\3\2\2\2LM\3\2\2\2MN\3\2\2\2Nw\5\b\5\2OQ\7#\2\2PO\3"+
		"\2\2\2PQ\3\2\2\2QR\3\2\2\2RT\7+\2\2SU\5\62\32\2TS\3\2\2\2TU\3\2\2\2UV"+
		"\3\2\2\2Vw\5\b\5\2WY\7$\2\2XW\3\2\2\2XY\3\2\2\2YZ\3\2\2\2Z\\\7+\2\2[]"+
		"\5\62\32\2\\[\3\2\2\2\\]\3\2\2\2]^\3\2\2\2^w\5\b\5\2_`\7!\2\2`b\7+\2\2"+
		"ac\5\62\32\2ba\3\2\2\2bc\3\2\2\2cd\3\2\2\2dw\5\f\7\2ef\7!\2\2fg\7+\2\2"+
		"gh\7&\2\2hj\5\60\31\2ik\5\62\32\2ji\3\2\2\2jk\3\2\2\2kl\3\2\2\2lm\5\24"+
		"\13\2mw\3\2\2\2no\7+\2\2op\7&\2\2pr\5\60\31\2qs\5\62\32\2rq\3\2\2\2rs"+
		"\3\2\2\2st\3\2\2\2tu\5\20\t\2uw\3\2\2\2vH\3\2\2\2vP\3\2\2\2vX\3\2\2\2"+
		"v_\3\2\2\2ve\3\2\2\2vn\3\2\2\2w\7\3\2\2\2x\177\7\5\2\2y{\5\n\6\2z|\7\6"+
		"\2\2{z\3\2\2\2{|\3\2\2\2|~\3\2\2\2}y\3\2\2\2~\u0081\3\2\2\2\177}\3\2\2"+
		"\2\177\u0080\3\2\2\2\u0080\u0082\3\2\2\2\u0081\177\3\2\2\2\u0082\u0083"+
		"\7\7\2\2\u0083\t\3\2\2\2\u0084\u0089\5 \21\2\u0085\u0089\5\"\22\2\u0086"+
		"\u0089\58\35\2\u0087\u0089\5:\36\2\u0088\u0084\3\2\2\2\u0088\u0085\3\2"+
		"\2\2\u0088\u0086\3\2\2\2\u0088\u0087\3\2\2\2\u0089\13\3\2\2\2\u008a\u0091"+
		"\7\5\2\2\u008b\u008d\5\16\b\2\u008c\u008e\7\6\2\2\u008d\u008c\3\2\2\2"+
		"\u008d\u008e\3\2\2\2\u008e\u0090\3\2\2\2\u008f\u008b\3\2\2\2\u0090\u0093"+
		"\3\2\2\2\u0091\u008f\3\2\2\2\u0091\u0092\3\2\2\2\u0092\u0094\3\2\2\2\u0093"+
		"\u0091\3\2\2\2\u0094\u0095\7\7\2\2\u0095\r\3\2\2\2\u0096\u009c\5 \21\2"+
		"\u0097\u009c\5\"\22\2\u0098\u009c\58\35\2\u0099\u009c\5\66\34\2\u009a"+
		"\u009c\5:\36\2\u009b\u0096\3\2\2\2\u009b\u0097\3\2\2\2\u009b\u0098\3\2"+
		"\2\2\u009b\u0099\3\2\2\2\u009b\u009a\3\2\2\2\u009c\17\3\2\2\2\u009d\u00a4"+
		"\7\5\2\2\u009e\u00a0\5\22\n\2\u009f\u00a1\7\6\2\2\u00a0\u009f\3\2\2\2"+
		"\u00a0\u00a1\3\2\2\2\u00a1\u00a3\3\2\2\2\u00a2\u009e\3\2\2\2\u00a3\u00a6"+
		"\3\2\2\2\u00a4\u00a2\3\2\2\2\u00a4\u00a5\3\2\2\2\u00a5\u00a7\3\2\2\2\u00a6"+
		"\u00a4\3\2\2\2\u00a7\u00a8\7\7\2\2\u00a8\21\3\2\2\2\u00a9\u00ac\5 \21"+
		"\2\u00aa\u00ac\5\"\22\2\u00ab\u00a9\3\2\2\2\u00ab\u00aa\3\2\2\2\u00ac"+
		"\23\3\2\2\2\u00ad\u00b4\7\5\2\2\u00ae\u00b0\5\26\f\2\u00af\u00b1\7\6\2"+
		"\2\u00b0\u00af\3\2\2\2\u00b0\u00b1\3\2\2\2\u00b1\u00b3\3\2\2\2\u00b2\u00ae"+
		"\3\2\2\2\u00b3\u00b6\3\2\2\2\u00b4\u00b2\3\2\2\2\u00b4\u00b5\3\2\2\2\u00b5"+
		"\u00b7\3\2\2\2\u00b6\u00b4\3\2\2\2\u00b7\u00b8\7\7\2\2\u00b8\25\3\2\2"+
		"\2\u00b9\u00bd\5 \21\2\u00ba\u00bd\5\"\22\2\u00bb\u00bd\5\66\34\2\u00bc"+
		"\u00b9\3\2\2\2\u00bc\u00ba\3\2\2\2\u00bc\u00bb\3\2\2\2\u00bd\27\3\2\2"+
		"\2\u00be\u00bf\t\2\2\2\u00bf\31\3\2\2\2\u00c0\u00c1\7+\2\2\u00c1\33\3"+
		"\2\2\2\u00c2\u00c3\7+\2\2\u00c3\35\3\2\2\2\u00c4\u00c5\7+\2\2\u00c5\37"+
		"\3\2\2\2\u00c6\u00c7\7\30\2\2\u00c7\u00c8\5\34\17\2\u00c8\u00ca\7\5\2"+
		"\2\u00c9\u00cb\5\36\20\2\u00ca\u00c9\3\2\2\2\u00cb\u00cc\3\2\2\2\u00cc"+
		"\u00ca\3\2\2\2\u00cc\u00cd\3\2\2\2\u00cd\u00ce\3\2\2\2\u00ce\u00cf\7\7"+
		"\2\2\u00cf!\3\2\2\2\u00d0\u00d2\7(\2\2\u00d1\u00d0\3\2\2\2\u00d1\u00d2"+
		"\3\2\2\2\u00d2\u00d3\3\2\2\2\u00d3\u00d4\5,\27\2\u00d4\u00d6\7,\2\2\u00d5"+
		"\u00d7\5$\23\2\u00d6\u00d5\3\2\2\2\u00d6\u00d7\3\2\2\2\u00d7\u00e1\3\2"+
		"\2\2\u00d8\u00da\7(\2\2\u00d9\u00d8\3\2\2\2\u00d9\u00da\3\2\2\2\u00da"+
		"\u00db\3\2\2\2\u00db\u00dc\5.\30\2\u00dc\u00de\7,\2\2\u00dd\u00df\5$\23"+
		"\2\u00de\u00dd\3\2\2\2\u00de\u00df\3\2\2\2\u00df\u00e1\3\2\2\2\u00e0\u00d1"+
		"\3\2\2\2\u00e0\u00d9\3\2\2\2\u00e1#\3\2\2\2\u00e2\u00e3\7&\2\2\u00e3\u00e5"+
		"\7+\2\2\u00e4\u00e6\5&\24\2\u00e5\u00e4\3\2\2\2\u00e5\u00e6\3\2\2\2\u00e6"+
		"%\3\2\2\2\u00e7\u00e8\7\31\2\2\u00e8\u00ed\5(\25\2\u00e9\u00ea\7\32\2"+
		"\2\u00ea\u00ec\5(\25\2\u00eb\u00e9\3\2\2\2\u00ec\u00ef\3\2\2\2\u00ed\u00eb"+
		"\3\2\2\2\u00ed\u00ee\3\2\2\2\u00ee\u00f0\3\2\2\2\u00ef\u00ed\3\2\2\2\u00f0"+
		"\u00f1\7\33\2\2\u00f1\'\3\2\2\2\u00f2\u00f3\7,\2\2\u00f3\u00f4\7\34\2"+
		"\2\u00f4\u00f5\5*\26\2\u00f5)\3\2\2\2\u00f6\u00f7\t\3\2\2\u00f7+\3\2\2"+
		"\2\u00f8\u00fb\5\30\r\2\u00f9\u00fb\5\32\16\2\u00fa\u00f8\3\2\2\2\u00fa"+
		"\u00f9\3\2\2\2\u00fb-\3\2\2\2\u00fc\u00fd\7\35\2\2\u00fd\u00fe\5,\27\2"+
		"\u00fe\u00ff\7\36\2\2\u00ff/\3\2\2\2\u0100\u0103\5.\30\2\u0101\u0103\5"+
		",\27\2\u0102\u0100\3\2\2\2\u0102\u0101\3\2\2\2\u0103\61\3\2\2\2\u0104"+
		"\u0105\7\37\2\2\u0105\u010a\5\64\33\2\u0106\u0107\7\32\2\2\u0107\u0109"+
		"\5\64\33\2\u0108\u0106\3\2\2\2\u0109\u010c\3\2\2\2\u010a\u0108\3\2\2\2"+
		"\u010a\u010b\3\2\2\2\u010b\63\3\2\2\2\u010c\u010a\3\2\2\2\u010d\u010e"+
		"\7+\2\2\u010e\65\3\2\2\2\u010f\u0110\7 \2\2\u0110\u0111\7*\2\2\u0111\u0112"+
		"\58\35\2\u0112\67\3\2\2\2\u0113\u0114\7%\2\2\u0114\u0115\7+\2\2\u0115"+
		"9\3\2\2\2\u0116\u0117\7\'\2\2\u0117\u0118\7+\2\2\u0118;\3\2\2\2%>@HLP"+
		"TX\\bjrv{\177\u0088\u008d\u0091\u009b\u00a0\u00a4\u00ab\u00b0\u00b4\u00bc"+
		"\u00cc\u00d1\u00d6\u00d9\u00de\u00e0\u00e5\u00ed\u00fa\u0102\u010a";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}