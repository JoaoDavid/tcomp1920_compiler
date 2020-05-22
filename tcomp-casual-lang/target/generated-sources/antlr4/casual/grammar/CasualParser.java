// Generated from casual\grammar\Casual.g4 by ANTLR 4.8
package casual.grammar;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class CasualParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.8", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		DECL=1, DEF=2, IF=3, ELSE=4, WHILE=5, RETURN=6, BOOL=7, INT=8, FLOAT=9, 
		STRING=10, L_RND_BR=11, R_RND_BR=12, L_SQR_BR=13, R_SQR_BR=14, L_CRL_BR=15, 
		R_CRL_BR=16, SEMICOLON=17, COLON=18, COMMA=19, ASSIGN=20, PLUS=21, MINUS=22, 
		TIMES=23, DIV=24, MOD=25, AND=26, OR=27, NOT=28, EQUAL=29, NOT_EQUAL=30, 
		GREATER_EQ=31, GREATER=32, LESS_EQ=33, LESS=34, ID=35, WS=36, COMMENT=37;
	public static final int
		RULE_program = 0, RULE_statement = 1, RULE_expr_stat = 2, RULE_return_stat = 3, 
		RULE_datatype = 4, RULE_var_decl_stat = 5, RULE_var_assign_stat = 6, RULE_var_type = 7, 
		RULE_if_stat = 8, RULE_else_block = 9, RULE_while_stat = 10, RULE_func_args = 11, 
		RULE_func_decl = 12, RULE_func_def = 13, RULE_func_inv = 14, RULE_expr = 15, 
		RULE_arr_r_value = 16, RULE_arr_l_value = 17, RULE_binary_ope = 18, RULE_unary_ope = 19;
	private static String[] makeRuleNames() {
		return new String[] {
			"program", "statement", "expr_stat", "return_stat", "datatype", "var_decl_stat", 
			"var_assign_stat", "var_type", "if_stat", "else_block", "while_stat", 
			"func_args", "func_decl", "func_def", "func_inv", "expr", "arr_r_value", 
			"arr_l_value", "binary_ope", "unary_ope"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'decl'", "'def'", "'if'", "'else'", "'while'", "'return'", null, 
			null, null, null, "'('", "')'", "'['", "']'", "'{'", "'}'", "';'", "':'", 
			"','", "'='", "'+'", "'-'", "'*'", "'/'", "'%'", "'&&'", "'||'", "'!'", 
			"'=='", "'!='", "'>='", "'>'", "'<='", "'<'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "DECL", "DEF", "IF", "ELSE", "WHILE", "RETURN", "BOOL", "INT", 
			"FLOAT", "STRING", "L_RND_BR", "R_RND_BR", "L_SQR_BR", "R_SQR_BR", "L_CRL_BR", 
			"R_CRL_BR", "SEMICOLON", "COLON", "COMMA", "ASSIGN", "PLUS", "MINUS", 
			"TIMES", "DIV", "MOD", "AND", "OR", "NOT", "EQUAL", "NOT_EQUAL", "GREATER_EQ", 
			"GREATER", "LESS_EQ", "LESS", "ID", "WS", "COMMENT"
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
	public String getGrammarFileName() { return "Casual.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public CasualParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class ProgramContext extends ParserRuleContext {
		public TerminalNode EOF() { return getToken(CasualParser.EOF, 0); }
		public List<Func_declContext> func_decl() {
			return getRuleContexts(Func_declContext.class);
		}
		public Func_declContext func_decl(int i) {
			return getRuleContext(Func_declContext.class,i);
		}
		public List<Func_defContext> func_def() {
			return getRuleContexts(Func_defContext.class);
		}
		public Func_defContext func_def(int i) {
			return getRuleContext(Func_defContext.class,i);
		}
		public ProgramContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_program; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CasualListener ) ((CasualListener)listener).enterProgram(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CasualListener ) ((CasualListener)listener).exitProgram(this);
		}
	}

	public final ProgramContext program() throws RecognitionException {
		ProgramContext _localctx = new ProgramContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_program);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(42); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				setState(42);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case DECL:
					{
					setState(40);
					func_decl();
					}
					break;
				case DEF:
					{
					setState(41);
					func_def();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(44); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==DECL || _la==DEF );
			setState(46);
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
		public If_statContext if_stat() {
			return getRuleContext(If_statContext.class,0);
		}
		public While_statContext while_stat() {
			return getRuleContext(While_statContext.class,0);
		}
		public Return_statContext return_stat() {
			return getRuleContext(Return_statContext.class,0);
		}
		public Var_decl_statContext var_decl_stat() {
			return getRuleContext(Var_decl_statContext.class,0);
		}
		public Var_assign_statContext var_assign_stat() {
			return getRuleContext(Var_assign_statContext.class,0);
		}
		public Expr_statContext expr_stat() {
			return getRuleContext(Expr_statContext.class,0);
		}
		public StatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_statement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CasualListener ) ((CasualListener)listener).enterStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CasualListener ) ((CasualListener)listener).exitStatement(this);
		}
	}

	public final StatementContext statement() throws RecognitionException {
		StatementContext _localctx = new StatementContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_statement);
		try {
			setState(54);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,2,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(48);
				if_stat();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(49);
				while_stat();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(50);
				return_stat();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(51);
				var_decl_stat();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(52);
				var_assign_stat();
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(53);
				expr_stat();
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

	public static class Expr_statContext extends ParserRuleContext {
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode SEMICOLON() { return getToken(CasualParser.SEMICOLON, 0); }
		public Expr_statContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr_stat; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CasualListener ) ((CasualListener)listener).enterExpr_stat(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CasualListener ) ((CasualListener)listener).exitExpr_stat(this);
		}
	}

	public final Expr_statContext expr_stat() throws RecognitionException {
		Expr_statContext _localctx = new Expr_statContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_expr_stat);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(56);
			expr(0);
			setState(57);
			match(SEMICOLON);
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

	public static class Return_statContext extends ParserRuleContext {
		public TerminalNode RETURN() { return getToken(CasualParser.RETURN, 0); }
		public TerminalNode SEMICOLON() { return getToken(CasualParser.SEMICOLON, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public Return_statContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_return_stat; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CasualListener ) ((CasualListener)listener).enterReturn_stat(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CasualListener ) ((CasualListener)listener).exitReturn_stat(this);
		}
	}

	public final Return_statContext return_stat() throws RecognitionException {
		Return_statContext _localctx = new Return_statContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_return_stat);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(59);
			match(RETURN);
			setState(61);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << BOOL) | (1L << INT) | (1L << FLOAT) | (1L << STRING) | (1L << L_RND_BR) | (1L << MINUS) | (1L << NOT) | (1L << ID))) != 0)) {
				{
				setState(60);
				expr(0);
				}
			}

			setState(63);
			match(SEMICOLON);
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

	public static class DatatypeContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(CasualParser.ID, 0); }
		public TerminalNode L_SQR_BR() { return getToken(CasualParser.L_SQR_BR, 0); }
		public DatatypeContext datatype() {
			return getRuleContext(DatatypeContext.class,0);
		}
		public TerminalNode R_SQR_BR() { return getToken(CasualParser.R_SQR_BR, 0); }
		public DatatypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_datatype; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CasualListener ) ((CasualListener)listener).enterDatatype(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CasualListener ) ((CasualListener)listener).exitDatatype(this);
		}
	}

	public final DatatypeContext datatype() throws RecognitionException {
		DatatypeContext _localctx = new DatatypeContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_datatype);
		try {
			setState(70);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ID:
				enterOuterAlt(_localctx, 1);
				{
				setState(65);
				match(ID);
				}
				break;
			case L_SQR_BR:
				enterOuterAlt(_localctx, 2);
				{
				setState(66);
				match(L_SQR_BR);
				setState(67);
				datatype();
				setState(68);
				match(R_SQR_BR);
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

	public static class Var_decl_statContext extends ParserRuleContext {
		public Var_typeContext var_type() {
			return getRuleContext(Var_typeContext.class,0);
		}
		public TerminalNode SEMICOLON() { return getToken(CasualParser.SEMICOLON, 0); }
		public TerminalNode ASSIGN() { return getToken(CasualParser.ASSIGN, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public Var_decl_statContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_var_decl_stat; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CasualListener ) ((CasualListener)listener).enterVar_decl_stat(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CasualListener ) ((CasualListener)listener).exitVar_decl_stat(this);
		}
	}

	public final Var_decl_statContext var_decl_stat() throws RecognitionException {
		Var_decl_statContext _localctx = new Var_decl_statContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_var_decl_stat);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(72);
			var_type();
			setState(75);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==ASSIGN) {
				{
				setState(73);
				match(ASSIGN);
				setState(74);
				expr(0);
				}
			}

			setState(77);
			match(SEMICOLON);
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

	public static class Var_assign_statContext extends ParserRuleContext {
		public TerminalNode ASSIGN() { return getToken(CasualParser.ASSIGN, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode SEMICOLON() { return getToken(CasualParser.SEMICOLON, 0); }
		public TerminalNode ID() { return getToken(CasualParser.ID, 0); }
		public Arr_l_valueContext arr_l_value() {
			return getRuleContext(Arr_l_valueContext.class,0);
		}
		public Var_assign_statContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_var_assign_stat; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CasualListener ) ((CasualListener)listener).enterVar_assign_stat(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CasualListener ) ((CasualListener)listener).exitVar_assign_stat(this);
		}
	}

	public final Var_assign_statContext var_assign_stat() throws RecognitionException {
		Var_assign_statContext _localctx = new Var_assign_statContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_var_assign_stat);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(81);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,6,_ctx) ) {
			case 1:
				{
				setState(79);
				match(ID);
				}
				break;
			case 2:
				{
				setState(80);
				arr_l_value();
				}
				break;
			}
			setState(83);
			match(ASSIGN);
			setState(84);
			expr(0);
			setState(85);
			match(SEMICOLON);
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

	public static class Var_typeContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(CasualParser.ID, 0); }
		public TerminalNode COLON() { return getToken(CasualParser.COLON, 0); }
		public DatatypeContext datatype() {
			return getRuleContext(DatatypeContext.class,0);
		}
		public Var_typeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_var_type; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CasualListener ) ((CasualListener)listener).enterVar_type(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CasualListener ) ((CasualListener)listener).exitVar_type(this);
		}
	}

	public final Var_typeContext var_type() throws RecognitionException {
		Var_typeContext _localctx = new Var_typeContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_var_type);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(87);
			match(ID);
			setState(88);
			match(COLON);
			setState(89);
			datatype();
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

	public static class If_statContext extends ParserRuleContext {
		public TerminalNode IF() { return getToken(CasualParser.IF, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode L_CRL_BR() { return getToken(CasualParser.L_CRL_BR, 0); }
		public TerminalNode R_CRL_BR() { return getToken(CasualParser.R_CRL_BR, 0); }
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public Else_blockContext else_block() {
			return getRuleContext(Else_blockContext.class,0);
		}
		public If_statContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_if_stat; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CasualListener ) ((CasualListener)listener).enterIf_stat(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CasualListener ) ((CasualListener)listener).exitIf_stat(this);
		}
	}

	public final If_statContext if_stat() throws RecognitionException {
		If_statContext _localctx = new If_statContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_if_stat);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(91);
			match(IF);
			setState(92);
			expr(0);
			setState(93);
			match(L_CRL_BR);
			setState(97);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << IF) | (1L << WHILE) | (1L << RETURN) | (1L << BOOL) | (1L << INT) | (1L << FLOAT) | (1L << STRING) | (1L << L_RND_BR) | (1L << MINUS) | (1L << NOT) | (1L << ID))) != 0)) {
				{
				{
				setState(94);
				statement();
				}
				}
				setState(99);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(100);
			match(R_CRL_BR);
			setState(102);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==ELSE) {
				{
				setState(101);
				else_block();
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

	public static class Else_blockContext extends ParserRuleContext {
		public TerminalNode ELSE() { return getToken(CasualParser.ELSE, 0); }
		public TerminalNode L_CRL_BR() { return getToken(CasualParser.L_CRL_BR, 0); }
		public TerminalNode R_CRL_BR() { return getToken(CasualParser.R_CRL_BR, 0); }
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public Else_blockContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_else_block; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CasualListener ) ((CasualListener)listener).enterElse_block(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CasualListener ) ((CasualListener)listener).exitElse_block(this);
		}
	}

	public final Else_blockContext else_block() throws RecognitionException {
		Else_blockContext _localctx = new Else_blockContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_else_block);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(104);
			match(ELSE);
			setState(105);
			match(L_CRL_BR);
			setState(109);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << IF) | (1L << WHILE) | (1L << RETURN) | (1L << BOOL) | (1L << INT) | (1L << FLOAT) | (1L << STRING) | (1L << L_RND_BR) | (1L << MINUS) | (1L << NOT) | (1L << ID))) != 0)) {
				{
				{
				setState(106);
				statement();
				}
				}
				setState(111);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(112);
			match(R_CRL_BR);
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

	public static class While_statContext extends ParserRuleContext {
		public TerminalNode WHILE() { return getToken(CasualParser.WHILE, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode L_CRL_BR() { return getToken(CasualParser.L_CRL_BR, 0); }
		public TerminalNode R_CRL_BR() { return getToken(CasualParser.R_CRL_BR, 0); }
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public While_statContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_while_stat; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CasualListener ) ((CasualListener)listener).enterWhile_stat(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CasualListener ) ((CasualListener)listener).exitWhile_stat(this);
		}
	}

	public final While_statContext while_stat() throws RecognitionException {
		While_statContext _localctx = new While_statContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_while_stat);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(114);
			match(WHILE);
			setState(115);
			expr(0);
			setState(116);
			match(L_CRL_BR);
			setState(120);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << IF) | (1L << WHILE) | (1L << RETURN) | (1L << BOOL) | (1L << INT) | (1L << FLOAT) | (1L << STRING) | (1L << L_RND_BR) | (1L << MINUS) | (1L << NOT) | (1L << ID))) != 0)) {
				{
				{
				setState(117);
				statement();
				}
				}
				setState(122);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(123);
			match(R_CRL_BR);
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

	public static class Func_argsContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(CasualParser.ID, 0); }
		public TerminalNode L_RND_BR() { return getToken(CasualParser.L_RND_BR, 0); }
		public TerminalNode R_RND_BR() { return getToken(CasualParser.R_RND_BR, 0); }
		public TerminalNode COLON() { return getToken(CasualParser.COLON, 0); }
		public DatatypeContext datatype() {
			return getRuleContext(DatatypeContext.class,0);
		}
		public List<Var_typeContext> var_type() {
			return getRuleContexts(Var_typeContext.class);
		}
		public Var_typeContext var_type(int i) {
			return getRuleContext(Var_typeContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(CasualParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(CasualParser.COMMA, i);
		}
		public Func_argsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_func_args; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CasualListener ) ((CasualListener)listener).enterFunc_args(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CasualListener ) ((CasualListener)listener).exitFunc_args(this);
		}
	}

	public final Func_argsContext func_args() throws RecognitionException {
		Func_argsContext _localctx = new Func_argsContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_func_args);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(125);
			match(ID);
			setState(126);
			match(L_RND_BR);
			setState(135);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==ID) {
				{
				setState(127);
				var_type();
				setState(132);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(128);
					match(COMMA);
					setState(129);
					var_type();
					}
					}
					setState(134);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(137);
			match(R_RND_BR);
			setState(138);
			match(COLON);
			setState(139);
			datatype();
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

	public static class Func_declContext extends ParserRuleContext {
		public TerminalNode DECL() { return getToken(CasualParser.DECL, 0); }
		public Func_argsContext func_args() {
			return getRuleContext(Func_argsContext.class,0);
		}
		public Func_declContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_func_decl; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CasualListener ) ((CasualListener)listener).enterFunc_decl(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CasualListener ) ((CasualListener)listener).exitFunc_decl(this);
		}
	}

	public final Func_declContext func_decl() throws RecognitionException {
		Func_declContext _localctx = new Func_declContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_func_decl);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(141);
			match(DECL);
			setState(142);
			func_args();
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

	public static class Func_defContext extends ParserRuleContext {
		public TerminalNode DEF() { return getToken(CasualParser.DEF, 0); }
		public Func_argsContext func_args() {
			return getRuleContext(Func_argsContext.class,0);
		}
		public TerminalNode L_CRL_BR() { return getToken(CasualParser.L_CRL_BR, 0); }
		public TerminalNode R_CRL_BR() { return getToken(CasualParser.R_CRL_BR, 0); }
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public Func_defContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_func_def; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CasualListener ) ((CasualListener)listener).enterFunc_def(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CasualListener ) ((CasualListener)listener).exitFunc_def(this);
		}
	}

	public final Func_defContext func_def() throws RecognitionException {
		Func_defContext _localctx = new Func_defContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_func_def);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(144);
			match(DEF);
			setState(145);
			func_args();
			setState(146);
			match(L_CRL_BR);
			setState(150);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << IF) | (1L << WHILE) | (1L << RETURN) | (1L << BOOL) | (1L << INT) | (1L << FLOAT) | (1L << STRING) | (1L << L_RND_BR) | (1L << MINUS) | (1L << NOT) | (1L << ID))) != 0)) {
				{
				{
				setState(147);
				statement();
				}
				}
				setState(152);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(153);
			match(R_CRL_BR);
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

	public static class Func_invContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(CasualParser.ID, 0); }
		public TerminalNode L_RND_BR() { return getToken(CasualParser.L_RND_BR, 0); }
		public TerminalNode R_RND_BR() { return getToken(CasualParser.R_RND_BR, 0); }
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(CasualParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(CasualParser.COMMA, i);
		}
		public Func_invContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_func_inv; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CasualListener ) ((CasualListener)listener).enterFunc_inv(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CasualListener ) ((CasualListener)listener).exitFunc_inv(this);
		}
	}

	public final Func_invContext func_inv() throws RecognitionException {
		Func_invContext _localctx = new Func_invContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_func_inv);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(155);
			match(ID);
			setState(156);
			match(L_RND_BR);
			setState(165);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << BOOL) | (1L << INT) | (1L << FLOAT) | (1L << STRING) | (1L << L_RND_BR) | (1L << MINUS) | (1L << NOT) | (1L << ID))) != 0)) {
				{
				setState(157);
				expr(0);
				setState(162);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(158);
					match(COMMA);
					setState(159);
					expr(0);
					}
					}
					setState(164);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(167);
			match(R_RND_BR);
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

	public static class ExprContext extends ParserRuleContext {
		public Unary_opeContext unary_ope() {
			return getRuleContext(Unary_opeContext.class,0);
		}
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public Func_invContext func_inv() {
			return getRuleContext(Func_invContext.class,0);
		}
		public Arr_r_valueContext arr_r_value() {
			return getRuleContext(Arr_r_valueContext.class,0);
		}
		public Arr_l_valueContext arr_l_value() {
			return getRuleContext(Arr_l_valueContext.class,0);
		}
		public TerminalNode BOOL() { return getToken(CasualParser.BOOL, 0); }
		public TerminalNode INT() { return getToken(CasualParser.INT, 0); }
		public TerminalNode FLOAT() { return getToken(CasualParser.FLOAT, 0); }
		public TerminalNode STRING() { return getToken(CasualParser.STRING, 0); }
		public TerminalNode ID() { return getToken(CasualParser.ID, 0); }
		public TerminalNode L_RND_BR() { return getToken(CasualParser.L_RND_BR, 0); }
		public TerminalNode R_RND_BR() { return getToken(CasualParser.R_RND_BR, 0); }
		public Binary_opeContext binary_ope() {
			return getRuleContext(Binary_opeContext.class,0);
		}
		public ExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CasualListener ) ((CasualListener)listener).enterExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CasualListener ) ((CasualListener)listener).exitExpr(this);
		}
	}

	public final ExprContext expr() throws RecognitionException {
		return expr(0);
	}

	private ExprContext expr(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ExprContext _localctx = new ExprContext(_ctx, _parentState);
		ExprContext _prevctx = _localctx;
		int _startState = 30;
		enterRecursionRule(_localctx, 30, RULE_expr, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(185);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,16,_ctx) ) {
			case 1:
				{
				setState(170);
				unary_ope();
				setState(171);
				expr(10);
				}
				break;
			case 2:
				{
				setState(173);
				func_inv();
				}
				break;
			case 3:
				{
				setState(174);
				arr_r_value();
				}
				break;
			case 4:
				{
				setState(175);
				arr_l_value();
				}
				break;
			case 5:
				{
				setState(176);
				match(BOOL);
				}
				break;
			case 6:
				{
				setState(177);
				match(INT);
				}
				break;
			case 7:
				{
				setState(178);
				match(FLOAT);
				}
				break;
			case 8:
				{
				setState(179);
				match(STRING);
				}
				break;
			case 9:
				{
				setState(180);
				match(ID);
				}
				break;
			case 10:
				{
				setState(181);
				match(L_RND_BR);
				setState(182);
				expr(0);
				setState(183);
				match(R_RND_BR);
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(193);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,17,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new ExprContext(_parentctx, _parentState);
					pushNewRecursionContext(_localctx, _startState, RULE_expr);
					setState(187);
					if (!(precpred(_ctx, 11))) throw new FailedPredicateException(this, "precpred(_ctx, 11)");
					setState(188);
					binary_ope();
					setState(189);
					expr(12);
					}
					} 
				}
				setState(195);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,17,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class Arr_r_valueContext extends ParserRuleContext {
		public Arr_l_valueContext arr_l_value() {
			return getRuleContext(Arr_l_valueContext.class,0);
		}
		public Func_invContext func_inv() {
			return getRuleContext(Func_invContext.class,0);
		}
		public List<TerminalNode> L_SQR_BR() { return getTokens(CasualParser.L_SQR_BR); }
		public TerminalNode L_SQR_BR(int i) {
			return getToken(CasualParser.L_SQR_BR, i);
		}
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public List<TerminalNode> R_SQR_BR() { return getTokens(CasualParser.R_SQR_BR); }
		public TerminalNode R_SQR_BR(int i) {
			return getToken(CasualParser.R_SQR_BR, i);
		}
		public Arr_r_valueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arr_r_value; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CasualListener ) ((CasualListener)listener).enterArr_r_value(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CasualListener ) ((CasualListener)listener).exitArr_r_value(this);
		}
	}

	public final Arr_r_valueContext arr_r_value() throws RecognitionException {
		Arr_r_valueContext _localctx = new Arr_r_valueContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_arr_r_value);
		try {
			int _alt;
			setState(206);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,19,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(196);
				arr_l_value();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				{
				setState(197);
				func_inv();
				setState(202); 
				_errHandler.sync(this);
				_alt = 1;
				do {
					switch (_alt) {
					case 1:
						{
						{
						setState(198);
						match(L_SQR_BR);
						setState(199);
						expr(0);
						setState(200);
						match(R_SQR_BR);
						}
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					setState(204); 
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,18,_ctx);
				} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
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

	public static class Arr_l_valueContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(CasualParser.ID, 0); }
		public List<TerminalNode> L_SQR_BR() { return getTokens(CasualParser.L_SQR_BR); }
		public TerminalNode L_SQR_BR(int i) {
			return getToken(CasualParser.L_SQR_BR, i);
		}
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public List<TerminalNode> R_SQR_BR() { return getTokens(CasualParser.R_SQR_BR); }
		public TerminalNode R_SQR_BR(int i) {
			return getToken(CasualParser.R_SQR_BR, i);
		}
		public Arr_l_valueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arr_l_value; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CasualListener ) ((CasualListener)listener).enterArr_l_value(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CasualListener ) ((CasualListener)listener).exitArr_l_value(this);
		}
	}

	public final Arr_l_valueContext arr_l_value() throws RecognitionException {
		Arr_l_valueContext _localctx = new Arr_l_valueContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_arr_l_value);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(208);
			match(ID);
			setState(213); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(209);
					match(L_SQR_BR);
					setState(210);
					expr(0);
					setState(211);
					match(R_SQR_BR);
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(215); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,20,_ctx);
			} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
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

	public static class Binary_opeContext extends ParserRuleContext {
		public TerminalNode AND() { return getToken(CasualParser.AND, 0); }
		public TerminalNode OR() { return getToken(CasualParser.OR, 0); }
		public TerminalNode EQUAL() { return getToken(CasualParser.EQUAL, 0); }
		public TerminalNode NOT_EQUAL() { return getToken(CasualParser.NOT_EQUAL, 0); }
		public TerminalNode GREATER_EQ() { return getToken(CasualParser.GREATER_EQ, 0); }
		public TerminalNode GREATER() { return getToken(CasualParser.GREATER, 0); }
		public TerminalNode LESS_EQ() { return getToken(CasualParser.LESS_EQ, 0); }
		public TerminalNode LESS() { return getToken(CasualParser.LESS, 0); }
		public TerminalNode PLUS() { return getToken(CasualParser.PLUS, 0); }
		public TerminalNode MINUS() { return getToken(CasualParser.MINUS, 0); }
		public TerminalNode TIMES() { return getToken(CasualParser.TIMES, 0); }
		public TerminalNode DIV() { return getToken(CasualParser.DIV, 0); }
		public TerminalNode MOD() { return getToken(CasualParser.MOD, 0); }
		public Binary_opeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_binary_ope; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CasualListener ) ((CasualListener)listener).enterBinary_ope(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CasualListener ) ((CasualListener)listener).exitBinary_ope(this);
		}
	}

	public final Binary_opeContext binary_ope() throws RecognitionException {
		Binary_opeContext _localctx = new Binary_opeContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_binary_ope);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(217);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << PLUS) | (1L << MINUS) | (1L << TIMES) | (1L << DIV) | (1L << MOD) | (1L << AND) | (1L << OR) | (1L << EQUAL) | (1L << NOT_EQUAL) | (1L << GREATER_EQ) | (1L << GREATER) | (1L << LESS_EQ) | (1L << LESS))) != 0)) ) {
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

	public static class Unary_opeContext extends ParserRuleContext {
		public TerminalNode NOT() { return getToken(CasualParser.NOT, 0); }
		public TerminalNode MINUS() { return getToken(CasualParser.MINUS, 0); }
		public Unary_opeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_unary_ope; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CasualListener ) ((CasualListener)listener).enterUnary_ope(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CasualListener ) ((CasualListener)listener).exitUnary_ope(this);
		}
	}

	public final Unary_opeContext unary_ope() throws RecognitionException {
		Unary_opeContext _localctx = new Unary_opeContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_unary_ope);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(219);
			_la = _input.LA(1);
			if ( !(_la==MINUS || _la==NOT) ) {
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

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 15:
			return expr_sempred((ExprContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean expr_sempred(ExprContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 11);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\'\u00e0\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\3\2\3\2\6\2-\n\2\r\2\16\2.\3\2\3\2\3\3"+
		"\3\3\3\3\3\3\3\3\3\3\5\39\n\3\3\4\3\4\3\4\3\5\3\5\5\5@\n\5\3\5\3\5\3\6"+
		"\3\6\3\6\3\6\3\6\5\6I\n\6\3\7\3\7\3\7\5\7N\n\7\3\7\3\7\3\b\3\b\5\bT\n"+
		"\b\3\b\3\b\3\b\3\b\3\t\3\t\3\t\3\t\3\n\3\n\3\n\3\n\7\nb\n\n\f\n\16\ne"+
		"\13\n\3\n\3\n\5\ni\n\n\3\13\3\13\3\13\7\13n\n\13\f\13\16\13q\13\13\3\13"+
		"\3\13\3\f\3\f\3\f\3\f\7\fy\n\f\f\f\16\f|\13\f\3\f\3\f\3\r\3\r\3\r\3\r"+
		"\3\r\7\r\u0085\n\r\f\r\16\r\u0088\13\r\5\r\u008a\n\r\3\r\3\r\3\r\3\r\3"+
		"\16\3\16\3\16\3\17\3\17\3\17\3\17\7\17\u0097\n\17\f\17\16\17\u009a\13"+
		"\17\3\17\3\17\3\20\3\20\3\20\3\20\3\20\7\20\u00a3\n\20\f\20\16\20\u00a6"+
		"\13\20\5\20\u00a8\n\20\3\20\3\20\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3"+
		"\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\5\21\u00bc\n\21\3\21\3\21"+
		"\3\21\3\21\7\21\u00c2\n\21\f\21\16\21\u00c5\13\21\3\22\3\22\3\22\3\22"+
		"\3\22\3\22\6\22\u00cd\n\22\r\22\16\22\u00ce\5\22\u00d1\n\22\3\23\3\23"+
		"\3\23\3\23\3\23\6\23\u00d8\n\23\r\23\16\23\u00d9\3\24\3\24\3\25\3\25\3"+
		"\25\2\3 \26\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \"$&(\2\4\4\2\27\35"+
		"\37$\4\2\30\30\36\36\2\u00ec\2,\3\2\2\2\48\3\2\2\2\6:\3\2\2\2\b=\3\2\2"+
		"\2\nH\3\2\2\2\fJ\3\2\2\2\16S\3\2\2\2\20Y\3\2\2\2\22]\3\2\2\2\24j\3\2\2"+
		"\2\26t\3\2\2\2\30\177\3\2\2\2\32\u008f\3\2\2\2\34\u0092\3\2\2\2\36\u009d"+
		"\3\2\2\2 \u00bb\3\2\2\2\"\u00d0\3\2\2\2$\u00d2\3\2\2\2&\u00db\3\2\2\2"+
		"(\u00dd\3\2\2\2*-\5\32\16\2+-\5\34\17\2,*\3\2\2\2,+\3\2\2\2-.\3\2\2\2"+
		".,\3\2\2\2./\3\2\2\2/\60\3\2\2\2\60\61\7\2\2\3\61\3\3\2\2\2\629\5\22\n"+
		"\2\639\5\26\f\2\649\5\b\5\2\659\5\f\7\2\669\5\16\b\2\679\5\6\4\28\62\3"+
		"\2\2\28\63\3\2\2\28\64\3\2\2\28\65\3\2\2\28\66\3\2\2\28\67\3\2\2\29\5"+
		"\3\2\2\2:;\5 \21\2;<\7\23\2\2<\7\3\2\2\2=?\7\b\2\2>@\5 \21\2?>\3\2\2\2"+
		"?@\3\2\2\2@A\3\2\2\2AB\7\23\2\2B\t\3\2\2\2CI\7%\2\2DE\7\17\2\2EF\5\n\6"+
		"\2FG\7\20\2\2GI\3\2\2\2HC\3\2\2\2HD\3\2\2\2I\13\3\2\2\2JM\5\20\t\2KL\7"+
		"\26\2\2LN\5 \21\2MK\3\2\2\2MN\3\2\2\2NO\3\2\2\2OP\7\23\2\2P\r\3\2\2\2"+
		"QT\7%\2\2RT\5$\23\2SQ\3\2\2\2SR\3\2\2\2TU\3\2\2\2UV\7\26\2\2VW\5 \21\2"+
		"WX\7\23\2\2X\17\3\2\2\2YZ\7%\2\2Z[\7\24\2\2[\\\5\n\6\2\\\21\3\2\2\2]^"+
		"\7\5\2\2^_\5 \21\2_c\7\21\2\2`b\5\4\3\2a`\3\2\2\2be\3\2\2\2ca\3\2\2\2"+
		"cd\3\2\2\2df\3\2\2\2ec\3\2\2\2fh\7\22\2\2gi\5\24\13\2hg\3\2\2\2hi\3\2"+
		"\2\2i\23\3\2\2\2jk\7\6\2\2ko\7\21\2\2ln\5\4\3\2ml\3\2\2\2nq\3\2\2\2om"+
		"\3\2\2\2op\3\2\2\2pr\3\2\2\2qo\3\2\2\2rs\7\22\2\2s\25\3\2\2\2tu\7\7\2"+
		"\2uv\5 \21\2vz\7\21\2\2wy\5\4\3\2xw\3\2\2\2y|\3\2\2\2zx\3\2\2\2z{\3\2"+
		"\2\2{}\3\2\2\2|z\3\2\2\2}~\7\22\2\2~\27\3\2\2\2\177\u0080\7%\2\2\u0080"+
		"\u0089\7\r\2\2\u0081\u0086\5\20\t\2\u0082\u0083\7\25\2\2\u0083\u0085\5"+
		"\20\t\2\u0084\u0082\3\2\2\2\u0085\u0088\3\2\2\2\u0086\u0084\3\2\2\2\u0086"+
		"\u0087\3\2\2\2\u0087\u008a\3\2\2\2\u0088\u0086\3\2\2\2\u0089\u0081\3\2"+
		"\2\2\u0089\u008a\3\2\2\2\u008a\u008b\3\2\2\2\u008b\u008c\7\16\2\2\u008c"+
		"\u008d\7\24\2\2\u008d\u008e\5\n\6\2\u008e\31\3\2\2\2\u008f\u0090\7\3\2"+
		"\2\u0090\u0091\5\30\r\2\u0091\33\3\2\2\2\u0092\u0093\7\4\2\2\u0093\u0094"+
		"\5\30\r\2\u0094\u0098\7\21\2\2\u0095\u0097\5\4\3\2\u0096\u0095\3\2\2\2"+
		"\u0097\u009a\3\2\2\2\u0098\u0096\3\2\2\2\u0098\u0099\3\2\2\2\u0099\u009b"+
		"\3\2\2\2\u009a\u0098\3\2\2\2\u009b\u009c\7\22\2\2\u009c\35\3\2\2\2\u009d"+
		"\u009e\7%\2\2\u009e\u00a7\7\r\2\2\u009f\u00a4\5 \21\2\u00a0\u00a1\7\25"+
		"\2\2\u00a1\u00a3\5 \21\2\u00a2\u00a0\3\2\2\2\u00a3\u00a6\3\2\2\2\u00a4"+
		"\u00a2\3\2\2\2\u00a4\u00a5\3\2\2\2\u00a5\u00a8\3\2\2\2\u00a6\u00a4\3\2"+
		"\2\2\u00a7\u009f\3\2\2\2\u00a7\u00a8\3\2\2\2\u00a8\u00a9\3\2\2\2\u00a9"+
		"\u00aa\7\16\2\2\u00aa\37\3\2\2\2\u00ab\u00ac\b\21\1\2\u00ac\u00ad\5(\25"+
		"\2\u00ad\u00ae\5 \21\f\u00ae\u00bc\3\2\2\2\u00af\u00bc\5\36\20\2\u00b0"+
		"\u00bc\5\"\22\2\u00b1\u00bc\5$\23\2\u00b2\u00bc\7\t\2\2\u00b3\u00bc\7"+
		"\n\2\2\u00b4\u00bc\7\13\2\2\u00b5\u00bc\7\f\2\2\u00b6\u00bc\7%\2\2\u00b7"+
		"\u00b8\7\r\2\2\u00b8\u00b9\5 \21\2\u00b9\u00ba\7\16\2\2\u00ba\u00bc\3"+
		"\2\2\2\u00bb\u00ab\3\2\2\2\u00bb\u00af\3\2\2\2\u00bb\u00b0\3\2\2\2\u00bb"+
		"\u00b1\3\2\2\2\u00bb\u00b2\3\2\2\2\u00bb\u00b3\3\2\2\2\u00bb\u00b4\3\2"+
		"\2\2\u00bb\u00b5\3\2\2\2\u00bb\u00b6\3\2\2\2\u00bb\u00b7\3\2\2\2\u00bc"+
		"\u00c3\3\2\2\2\u00bd\u00be\f\r\2\2\u00be\u00bf\5&\24\2\u00bf\u00c0\5 "+
		"\21\16\u00c0\u00c2\3\2\2\2\u00c1\u00bd\3\2\2\2\u00c2\u00c5\3\2\2\2\u00c3"+
		"\u00c1\3\2\2\2\u00c3\u00c4\3\2\2\2\u00c4!\3\2\2\2\u00c5\u00c3\3\2\2\2"+
		"\u00c6\u00d1\5$\23\2\u00c7\u00cc\5\36\20\2\u00c8\u00c9\7\17\2\2\u00c9"+
		"\u00ca\5 \21\2\u00ca\u00cb\7\20\2\2\u00cb\u00cd\3\2\2\2\u00cc\u00c8\3"+
		"\2\2\2\u00cd\u00ce\3\2\2\2\u00ce\u00cc\3\2\2\2\u00ce\u00cf\3\2\2\2\u00cf"+
		"\u00d1\3\2\2\2\u00d0\u00c6\3\2\2\2\u00d0\u00c7\3\2\2\2\u00d1#\3\2\2\2"+
		"\u00d2\u00d7\7%\2\2\u00d3\u00d4\7\17\2\2\u00d4\u00d5\5 \21\2\u00d5\u00d6"+
		"\7\20\2\2\u00d6\u00d8\3\2\2\2\u00d7\u00d3\3\2\2\2\u00d8\u00d9\3\2\2\2"+
		"\u00d9\u00d7\3\2\2\2\u00d9\u00da\3\2\2\2\u00da%\3\2\2\2\u00db\u00dc\t"+
		"\2\2\2\u00dc\'\3\2\2\2\u00dd\u00de\t\3\2\2\u00de)\3\2\2\2\27,.8?HMSch"+
		"oz\u0086\u0089\u0098\u00a4\u00a7\u00bb\u00c3\u00ce\u00d0\u00d9";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}