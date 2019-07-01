// Generated from CA.g4 by ANTLR 4.7
package beast.app.beauti.compactanalysis;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class CAParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.7", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, TEMPLATETOKEN=11, IMPORTTOKEN=12, LINKTOKEN=13, UNLINKTOKEN=14, 
		SETTOKEN=15, USETOKEN=16, ADDTOKEN=17, RENAMETOKEN=18, RMTOKEN=19, TAXONSETTOKEN=20, 
		LINKTYPE=21, SHARETYPE=22, STRING=23, WHITESPACE=24, COMMENT=25, LINE_COMMENT=26;
	public static final int
		RULE_casentence = 0, RULE_cmd = 1, RULE_template = 2, RULE_templatename = 3, 
		RULE_use = 4, RULE_key = 5, RULE_value = 6, RULE_import_ = 7, RULE_add = 8, 
		RULE_argOrUse = 9, RULE_subtemplate = 10, RULE_filename = 11, RULE_alignmentprovider = 12, 
		RULE_arg = 13, RULE_link = 14, RULE_linktype = 15, RULE_sharetype = 16, 
		RULE_unlink = 17, RULE_set = 18, RULE_inputidentifier = 19, RULE_elemntName = 20, 
		RULE_idPattern = 21, RULE_partitionPattern = 22, RULE_inputname = 23, 
		RULE_rename = 24, RULE_oldName = 25, RULE_newName = 26, RULE_rm = 27, 
		RULE_taxonset = 28;
	public static final String[] ruleNames = {
		"casentence", "cmd", "template", "templatename", "use", "key", "value", 
		"import_", "add", "argOrUse", "subtemplate", "filename", "alignmentprovider", 
		"arg", "link", "linktype", "sharetype", "unlink", "set", "inputidentifier", 
		"elemntName", "idPattern", "partitionPattern", "inputname", "rename", 
		"oldName", "newName", "rm", "taxonset"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "';'", "'='", "'('", "','", "')'", "'@'", "'['", "']'", "'{'", "'}'", 
		"'template'", "'import'", "'link'", "'unlink'", "'set'", "'use'", "'add'", 
		"'rename'", "'rm'", "'taxonset'", null, "'param'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, null, null, null, null, "TEMPLATETOKEN", 
		"IMPORTTOKEN", "LINKTOKEN", "UNLINKTOKEN", "SETTOKEN", "USETOKEN", "ADDTOKEN", 
		"RENAMETOKEN", "RMTOKEN", "TAXONSETTOKEN", "LINKTYPE", "SHARETYPE", "STRING", 
		"WHITESPACE", "COMMENT", "LINE_COMMENT"
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

	@Override
	public String getGrammarFileName() { return "CA.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public CAParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class CasentenceContext extends ParserRuleContext {
		public List<CmdContext> cmd() {
			return getRuleContexts(CmdContext.class);
		}
		public CmdContext cmd(int i) {
			return getRuleContext(CmdContext.class,i);
		}
		public CasentenceContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_casentence; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CAListener ) ((CAListener)listener).enterCasentence(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CAListener ) ((CAListener)listener).exitCasentence(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CAVisitor ) return ((CAVisitor<? extends T>)visitor).visitCasentence(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CasentenceContext casentence() throws RecognitionException {
		CasentenceContext _localctx = new CasentenceContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_casentence);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(59); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(58);
				cmd();
				}
				}
				setState(61); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__0) | (1L << TEMPLATETOKEN) | (1L << IMPORTTOKEN) | (1L << LINKTOKEN) | (1L << UNLINKTOKEN) | (1L << SETTOKEN) | (1L << USETOKEN) | (1L << ADDTOKEN) | (1L << RENAMETOKEN) | (1L << RMTOKEN) | (1L << TAXONSETTOKEN))) != 0) );
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

	public static class CmdContext extends ParserRuleContext {
		public TemplateContext template() {
			return getRuleContext(TemplateContext.class,0);
		}
		public Import_Context import_() {
			return getRuleContext(Import_Context.class,0);
		}
		public UseContext use() {
			return getRuleContext(UseContext.class,0);
		}
		public SetContext set() {
			return getRuleContext(SetContext.class,0);
		}
		public LinkContext link() {
			return getRuleContext(LinkContext.class,0);
		}
		public UnlinkContext unlink() {
			return getRuleContext(UnlinkContext.class,0);
		}
		public RenameContext rename() {
			return getRuleContext(RenameContext.class,0);
		}
		public AddContext add() {
			return getRuleContext(AddContext.class,0);
		}
		public RmContext rm() {
			return getRuleContext(RmContext.class,0);
		}
		public TaxonsetContext taxonset() {
			return getRuleContext(TaxonsetContext.class,0);
		}
		public CmdContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cmd; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CAListener ) ((CAListener)listener).enterCmd(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CAListener ) ((CAListener)listener).exitCmd(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CAVisitor ) return ((CAVisitor<? extends T>)visitor).visitCmd(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CmdContext cmd() throws RecognitionException {
		CmdContext _localctx = new CmdContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_cmd);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(73);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case TEMPLATETOKEN:
				{
				setState(63);
				template();
				}
				break;
			case IMPORTTOKEN:
				{
				setState(64);
				import_();
				}
				break;
			case USETOKEN:
				{
				setState(65);
				use();
				}
				break;
			case SETTOKEN:
				{
				setState(66);
				set();
				}
				break;
			case LINKTOKEN:
				{
				setState(67);
				link();
				}
				break;
			case UNLINKTOKEN:
				{
				setState(68);
				unlink();
				}
				break;
			case RENAMETOKEN:
				{
				setState(69);
				rename();
				}
				break;
			case ADDTOKEN:
				{
				setState(70);
				add();
				}
				break;
			case RMTOKEN:
				{
				setState(71);
				rm();
				}
				break;
			case TAXONSETTOKEN:
				{
				setState(72);
				taxonset();
				}
				break;
			case T__0:
				break;
			default:
				break;
			}
			setState(75);
			match(T__0);
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

	public static class TemplateContext extends ParserRuleContext {
		public TerminalNode TEMPLATETOKEN() { return getToken(CAParser.TEMPLATETOKEN, 0); }
		public TemplatenameContext templatename() {
			return getRuleContext(TemplatenameContext.class,0);
		}
		public TemplateContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_template; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CAListener ) ((CAListener)listener).enterTemplate(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CAListener ) ((CAListener)listener).exitTemplate(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CAVisitor ) return ((CAVisitor<? extends T>)visitor).visitTemplate(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TemplateContext template() throws RecognitionException {
		TemplateContext _localctx = new TemplateContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_template);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(77);
			match(TEMPLATETOKEN);
			setState(78);
			templatename();
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

	public static class TemplatenameContext extends ParserRuleContext {
		public TerminalNode STRING() { return getToken(CAParser.STRING, 0); }
		public TemplatenameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_templatename; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CAListener ) ((CAListener)listener).enterTemplatename(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CAListener ) ((CAListener)listener).exitTemplatename(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CAVisitor ) return ((CAVisitor<? extends T>)visitor).visitTemplatename(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TemplatenameContext templatename() throws RecognitionException {
		TemplatenameContext _localctx = new TemplatenameContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_templatename);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(80);
			match(STRING);
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

	public static class UseContext extends ParserRuleContext {
		public TerminalNode USETOKEN() { return getToken(CAParser.USETOKEN, 0); }
		public TerminalNode STRING() { return getToken(CAParser.STRING, 0); }
		public InputidentifierContext inputidentifier() {
			return getRuleContext(InputidentifierContext.class,0);
		}
		public List<KeyContext> key() {
			return getRuleContexts(KeyContext.class);
		}
		public KeyContext key(int i) {
			return getRuleContext(KeyContext.class,i);
		}
		public List<ValueContext> value() {
			return getRuleContexts(ValueContext.class);
		}
		public ValueContext value(int i) {
			return getRuleContext(ValueContext.class,i);
		}
		public UseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_use; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CAListener ) ((CAListener)listener).enterUse(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CAListener ) ((CAListener)listener).exitUse(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CAVisitor ) return ((CAVisitor<? extends T>)visitor).visitUse(this);
			else return visitor.visitChildren(this);
		}
	}

	public final UseContext use() throws RecognitionException {
		UseContext _localctx = new UseContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_use);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(82);
			match(USETOKEN);
			setState(86);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,2,_ctx) ) {
			case 1:
				{
				setState(83);
				inputidentifier();
				setState(84);
				match(T__1);
				}
				break;
			}
			setState(88);
			match(STRING);
			setState(105);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__2) {
				{
				setState(89);
				match(T__2);
				setState(90);
				key();
				setState(91);
				match(T__1);
				setState(92);
				value();
				setState(100);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__3) {
					{
					{
					setState(93);
					match(T__3);
					setState(94);
					key();
					setState(95);
					match(T__1);
					setState(96);
					value();
					}
					}
					setState(102);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(103);
				match(T__4);
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

	public static class KeyContext extends ParserRuleContext {
		public TerminalNode STRING() { return getToken(CAParser.STRING, 0); }
		public TerminalNode TAXONSETTOKEN() { return getToken(CAParser.TAXONSETTOKEN, 0); }
		public KeyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_key; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CAListener ) ((CAListener)listener).enterKey(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CAListener ) ((CAListener)listener).exitKey(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CAVisitor ) return ((CAVisitor<? extends T>)visitor).visitKey(this);
			else return visitor.visitChildren(this);
		}
	}

	public final KeyContext key() throws RecognitionException {
		KeyContext _localctx = new KeyContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_key);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(107);
			_la = _input.LA(1);
			if ( !(_la==TAXONSETTOKEN || _la==STRING) ) {
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

	public static class ValueContext extends ParserRuleContext {
		public TerminalNode STRING() { return getToken(CAParser.STRING, 0); }
		public ValueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_value; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CAListener ) ((CAListener)listener).enterValue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CAListener ) ((CAListener)listener).exitValue(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CAVisitor ) return ((CAVisitor<? extends T>)visitor).visitValue(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ValueContext value() throws RecognitionException {
		ValueContext _localctx = new ValueContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_value);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(109);
			match(STRING);
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

	public static class Import_Context extends ParserRuleContext {
		public TerminalNode IMPORTTOKEN() { return getToken(CAParser.IMPORTTOKEN, 0); }
		public FilenameContext filename() {
			return getRuleContext(FilenameContext.class,0);
		}
		public AlignmentproviderContext alignmentprovider() {
			return getRuleContext(AlignmentproviderContext.class,0);
		}
		public List<ArgContext> arg() {
			return getRuleContexts(ArgContext.class);
		}
		public ArgContext arg(int i) {
			return getRuleContext(ArgContext.class,i);
		}
		public Import_Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_import_; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CAListener ) ((CAListener)listener).enterImport_(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CAListener ) ((CAListener)listener).exitImport_(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CAVisitor ) return ((CAVisitor<? extends T>)visitor).visitImport_(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Import_Context import_() throws RecognitionException {
		Import_Context _localctx = new Import_Context(_ctx, getState());
		enterRule(_localctx, 14, RULE_import_);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(111);
			match(IMPORTTOKEN);
			setState(113);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,5,_ctx) ) {
			case 1:
				{
				setState(112);
				alignmentprovider();
				}
				break;
			}
			setState(115);
			filename();
			setState(127);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__2) {
				{
				setState(116);
				match(T__2);
				setState(117);
				arg();
				setState(122);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__3) {
					{
					{
					setState(118);
					match(T__3);
					setState(119);
					arg();
					}
					}
					setState(124);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(125);
				match(T__4);
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

	public static class AddContext extends ParserRuleContext {
		public TerminalNode ADDTOKEN() { return getToken(CAParser.ADDTOKEN, 0); }
		public TerminalNode STRING() { return getToken(CAParser.STRING, 0); }
		public List<ArgOrUseContext> argOrUse() {
			return getRuleContexts(ArgOrUseContext.class);
		}
		public ArgOrUseContext argOrUse(int i) {
			return getRuleContext(ArgOrUseContext.class,i);
		}
		public AddContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_add; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CAListener ) ((CAListener)listener).enterAdd(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CAListener ) ((CAListener)listener).exitAdd(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CAVisitor ) return ((CAVisitor<? extends T>)visitor).visitAdd(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AddContext add() throws RecognitionException {
		AddContext _localctx = new AddContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_add);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(129);
			match(ADDTOKEN);
			setState(130);
			match(STRING);
			setState(142);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__2) {
				{
				setState(131);
				match(T__2);
				setState(132);
				argOrUse();
				setState(137);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__3) {
					{
					{
					setState(133);
					match(T__3);
					setState(134);
					argOrUse();
					}
					}
					setState(139);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(140);
				match(T__4);
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

	public static class ArgOrUseContext extends ParserRuleContext {
		public ValueContext value() {
			return getRuleContext(ValueContext.class,0);
		}
		public SubtemplateContext subtemplate() {
			return getRuleContext(SubtemplateContext.class,0);
		}
		public ArgOrUseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_argOrUse; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CAListener ) ((CAListener)listener).enterArgOrUse(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CAListener ) ((CAListener)listener).exitArgOrUse(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CAVisitor ) return ((CAVisitor<? extends T>)visitor).visitArgOrUse(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ArgOrUseContext argOrUse() throws RecognitionException {
		ArgOrUseContext _localctx = new ArgOrUseContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_argOrUse);
		try {
			setState(146);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,10,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(144);
				value();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(145);
				subtemplate();
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

	public static class SubtemplateContext extends ParserRuleContext {
		public TerminalNode STRING() { return getToken(CAParser.STRING, 0); }
		public List<KeyContext> key() {
			return getRuleContexts(KeyContext.class);
		}
		public KeyContext key(int i) {
			return getRuleContext(KeyContext.class,i);
		}
		public List<ValueContext> value() {
			return getRuleContexts(ValueContext.class);
		}
		public ValueContext value(int i) {
			return getRuleContext(ValueContext.class,i);
		}
		public SubtemplateContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_subtemplate; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CAListener ) ((CAListener)listener).enterSubtemplate(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CAListener ) ((CAListener)listener).exitSubtemplate(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CAVisitor ) return ((CAVisitor<? extends T>)visitor).visitSubtemplate(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SubtemplateContext subtemplate() throws RecognitionException {
		SubtemplateContext _localctx = new SubtemplateContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_subtemplate);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(148);
			match(STRING);
			setState(149);
			match(T__2);
			setState(150);
			key();
			setState(151);
			match(T__1);
			setState(152);
			value();
			setState(160);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__3) {
				{
				{
				setState(153);
				match(T__3);
				setState(154);
				key();
				setState(155);
				match(T__1);
				setState(156);
				value();
				}
				}
				setState(162);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(163);
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

	public static class FilenameContext extends ParserRuleContext {
		public TerminalNode STRING() { return getToken(CAParser.STRING, 0); }
		public FilenameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_filename; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CAListener ) ((CAListener)listener).enterFilename(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CAListener ) ((CAListener)listener).exitFilename(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CAVisitor ) return ((CAVisitor<? extends T>)visitor).visitFilename(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FilenameContext filename() throws RecognitionException {
		FilenameContext _localctx = new FilenameContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_filename);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(165);
			match(STRING);
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

	public static class AlignmentproviderContext extends ParserRuleContext {
		public TerminalNode STRING() { return getToken(CAParser.STRING, 0); }
		public AlignmentproviderContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_alignmentprovider; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CAListener ) ((CAListener)listener).enterAlignmentprovider(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CAListener ) ((CAListener)listener).exitAlignmentprovider(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CAVisitor ) return ((CAVisitor<? extends T>)visitor).visitAlignmentprovider(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AlignmentproviderContext alignmentprovider() throws RecognitionException {
		AlignmentproviderContext _localctx = new AlignmentproviderContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_alignmentprovider);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(167);
			match(STRING);
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

	public static class ArgContext extends ParserRuleContext {
		public TerminalNode STRING() { return getToken(CAParser.STRING, 0); }
		public ArgContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arg; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CAListener ) ((CAListener)listener).enterArg(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CAListener ) ((CAListener)listener).exitArg(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CAVisitor ) return ((CAVisitor<? extends T>)visitor).visitArg(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ArgContext arg() throws RecognitionException {
		ArgContext _localctx = new ArgContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_arg);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(169);
			match(STRING);
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

	public static class LinkContext extends ParserRuleContext {
		public TerminalNode LINKTOKEN() { return getToken(CAParser.LINKTOKEN, 0); }
		public LinktypeContext linktype() {
			return getRuleContext(LinktypeContext.class,0);
		}
		public PartitionPatternContext partitionPattern() {
			return getRuleContext(PartitionPatternContext.class,0);
		}
		public SharetypeContext sharetype() {
			return getRuleContext(SharetypeContext.class,0);
		}
		public InputidentifierContext inputidentifier() {
			return getRuleContext(InputidentifierContext.class,0);
		}
		public LinkContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_link; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CAListener ) ((CAListener)listener).enterLink(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CAListener ) ((CAListener)listener).exitLink(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CAVisitor ) return ((CAVisitor<? extends T>)visitor).visitLink(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LinkContext link() throws RecognitionException {
		LinkContext _localctx = new LinkContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_link);
		int _la;
		try {
			setState(180);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,13,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(171);
				match(LINKTOKEN);
				setState(172);
				linktype();
				setState(174);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__8) {
					{
					setState(173);
					partitionPattern();
					}
				}

				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(176);
				match(LINKTOKEN);
				setState(177);
				sharetype();
				setState(178);
				inputidentifier();
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

	public static class LinktypeContext extends ParserRuleContext {
		public TerminalNode LINKTYPE() { return getToken(CAParser.LINKTYPE, 0); }
		public LinktypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_linktype; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CAListener ) ((CAListener)listener).enterLinktype(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CAListener ) ((CAListener)listener).exitLinktype(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CAVisitor ) return ((CAVisitor<? extends T>)visitor).visitLinktype(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LinktypeContext linktype() throws RecognitionException {
		LinktypeContext _localctx = new LinktypeContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_linktype);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(182);
			match(LINKTYPE);
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

	public static class SharetypeContext extends ParserRuleContext {
		public TerminalNode SHARETYPE() { return getToken(CAParser.SHARETYPE, 0); }
		public SharetypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_sharetype; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CAListener ) ((CAListener)listener).enterSharetype(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CAListener ) ((CAListener)listener).exitSharetype(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CAVisitor ) return ((CAVisitor<? extends T>)visitor).visitSharetype(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SharetypeContext sharetype() throws RecognitionException {
		SharetypeContext _localctx = new SharetypeContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_sharetype);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(184);
			match(SHARETYPE);
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

	public static class UnlinkContext extends ParserRuleContext {
		public TerminalNode UNLINKTOKEN() { return getToken(CAParser.UNLINKTOKEN, 0); }
		public LinktypeContext linktype() {
			return getRuleContext(LinktypeContext.class,0);
		}
		public PartitionPatternContext partitionPattern() {
			return getRuleContext(PartitionPatternContext.class,0);
		}
		public SharetypeContext sharetype() {
			return getRuleContext(SharetypeContext.class,0);
		}
		public InputidentifierContext inputidentifier() {
			return getRuleContext(InputidentifierContext.class,0);
		}
		public UnlinkContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_unlink; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CAListener ) ((CAListener)listener).enterUnlink(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CAListener ) ((CAListener)listener).exitUnlink(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CAVisitor ) return ((CAVisitor<? extends T>)visitor).visitUnlink(this);
			else return visitor.visitChildren(this);
		}
	}

	public final UnlinkContext unlink() throws RecognitionException {
		UnlinkContext _localctx = new UnlinkContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_unlink);
		int _la;
		try {
			setState(195);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,15,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(186);
				match(UNLINKTOKEN);
				setState(187);
				linktype();
				setState(189);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__8) {
					{
					setState(188);
					partitionPattern();
					}
				}

				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(191);
				match(UNLINKTOKEN);
				setState(192);
				sharetype();
				setState(193);
				inputidentifier();
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

	public static class SetContext extends ParserRuleContext {
		public TerminalNode SETTOKEN() { return getToken(CAParser.SETTOKEN, 0); }
		public InputidentifierContext inputidentifier() {
			return getRuleContext(InputidentifierContext.class,0);
		}
		public TerminalNode STRING() { return getToken(CAParser.STRING, 0); }
		public SetContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_set; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CAListener ) ((CAListener)listener).enterSet(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CAListener ) ((CAListener)listener).exitSet(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CAVisitor ) return ((CAVisitor<? extends T>)visitor).visitSet(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SetContext set() throws RecognitionException {
		SetContext _localctx = new SetContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_set);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(197);
			match(SETTOKEN);
			setState(198);
			inputidentifier();
			setState(199);
			match(T__1);
			setState(200);
			match(STRING);
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

	public static class InputidentifierContext extends ParserRuleContext {
		public IdPatternContext idPattern() {
			return getRuleContext(IdPatternContext.class,0);
		}
		public InputnameContext inputname() {
			return getRuleContext(InputnameContext.class,0);
		}
		public ElemntNameContext elemntName() {
			return getRuleContext(ElemntNameContext.class,0);
		}
		public PartitionPatternContext partitionPattern() {
			return getRuleContext(PartitionPatternContext.class,0);
		}
		public InputidentifierContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_inputidentifier; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CAListener ) ((CAListener)listener).enterInputidentifier(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CAListener ) ((CAListener)listener).exitInputidentifier(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CAVisitor ) return ((CAVisitor<? extends T>)visitor).visitInputidentifier(this);
			else return visitor.visitChildren(this);
		}
	}

	public final InputidentifierContext inputidentifier() throws RecognitionException {
		InputidentifierContext _localctx = new InputidentifierContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_inputidentifier);
		int _la;
		try {
			setState(217);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,20,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(202);
				idPattern();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(204);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,16,_ctx) ) {
				case 1:
					{
					setState(203);
					elemntName();
					}
					break;
				}
				setState(206);
				inputname();
				setState(208);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__6) {
					{
					setState(207);
					idPattern();
					}
				}

				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(211);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,18,_ctx) ) {
				case 1:
					{
					setState(210);
					elemntName();
					}
					break;
				}
				setState(214);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==STRING) {
					{
					setState(213);
					inputname();
					}
				}

				setState(216);
				partitionPattern();
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

	public static class ElemntNameContext extends ParserRuleContext {
		public TerminalNode STRING() { return getToken(CAParser.STRING, 0); }
		public ElemntNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_elemntName; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CAListener ) ((CAListener)listener).enterElemntName(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CAListener ) ((CAListener)listener).exitElemntName(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CAVisitor ) return ((CAVisitor<? extends T>)visitor).visitElemntName(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ElemntNameContext elemntName() throws RecognitionException {
		ElemntNameContext _localctx = new ElemntNameContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_elemntName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(219);
			match(STRING);
			setState(220);
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

	public static class IdPatternContext extends ParserRuleContext {
		public TerminalNode STRING() { return getToken(CAParser.STRING, 0); }
		public IdPatternContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_idPattern; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CAListener ) ((CAListener)listener).enterIdPattern(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CAListener ) ((CAListener)listener).exitIdPattern(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CAVisitor ) return ((CAVisitor<? extends T>)visitor).visitIdPattern(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IdPatternContext idPattern() throws RecognitionException {
		IdPatternContext _localctx = new IdPatternContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_idPattern);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(222);
			match(T__6);
			setState(223);
			match(STRING);
			setState(224);
			match(T__7);
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

	public static class PartitionPatternContext extends ParserRuleContext {
		public List<TerminalNode> STRING() { return getTokens(CAParser.STRING); }
		public TerminalNode STRING(int i) {
			return getToken(CAParser.STRING, i);
		}
		public List<TerminalNode> LINKTYPE() { return getTokens(CAParser.LINKTYPE); }
		public TerminalNode LINKTYPE(int i) {
			return getToken(CAParser.LINKTYPE, i);
		}
		public PartitionPatternContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_partitionPattern; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CAListener ) ((CAListener)listener).enterPartitionPattern(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CAListener ) ((CAListener)listener).exitPartitionPattern(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CAVisitor ) return ((CAVisitor<? extends T>)visitor).visitPartitionPattern(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PartitionPatternContext partitionPattern() throws RecognitionException {
		PartitionPatternContext _localctx = new PartitionPatternContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_partitionPattern);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(226);
			match(T__8);
			setState(227);
			_la = _input.LA(1);
			if ( !(_la==LINKTYPE || _la==STRING) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			setState(232);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__3) {
				{
				{
				setState(228);
				match(T__3);
				setState(229);
				_la = _input.LA(1);
				if ( !(_la==LINKTYPE || _la==STRING) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				}
				}
				setState(234);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(235);
			match(T__9);
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

	public static class InputnameContext extends ParserRuleContext {
		public TerminalNode STRING() { return getToken(CAParser.STRING, 0); }
		public InputnameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_inputname; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CAListener ) ((CAListener)listener).enterInputname(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CAListener ) ((CAListener)listener).exitInputname(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CAVisitor ) return ((CAVisitor<? extends T>)visitor).visitInputname(this);
			else return visitor.visitChildren(this);
		}
	}

	public final InputnameContext inputname() throws RecognitionException {
		InputnameContext _localctx = new InputnameContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_inputname);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(237);
			match(STRING);
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

	public static class RenameContext extends ParserRuleContext {
		public TerminalNode RENAMETOKEN() { return getToken(CAParser.RENAMETOKEN, 0); }
		public LinktypeContext linktype() {
			return getRuleContext(LinktypeContext.class,0);
		}
		public NewNameContext newName() {
			return getRuleContext(NewNameContext.class,0);
		}
		public OldNameContext oldName() {
			return getRuleContext(OldNameContext.class,0);
		}
		public RenameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_rename; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CAListener ) ((CAListener)listener).enterRename(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CAListener ) ((CAListener)listener).exitRename(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CAVisitor ) return ((CAVisitor<? extends T>)visitor).visitRename(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RenameContext rename() throws RecognitionException {
		RenameContext _localctx = new RenameContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_rename);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(239);
			match(RENAMETOKEN);
			setState(240);
			linktype();
			setState(242);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==LINKTYPE || _la==STRING) {
				{
				setState(241);
				oldName();
				}
			}

			setState(244);
			match(T__1);
			setState(245);
			newName();
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

	public static class OldNameContext extends ParserRuleContext {
		public TerminalNode STRING() { return getToken(CAParser.STRING, 0); }
		public TerminalNode LINKTYPE() { return getToken(CAParser.LINKTYPE, 0); }
		public OldNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_oldName; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CAListener ) ((CAListener)listener).enterOldName(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CAListener ) ((CAListener)listener).exitOldName(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CAVisitor ) return ((CAVisitor<? extends T>)visitor).visitOldName(this);
			else return visitor.visitChildren(this);
		}
	}

	public final OldNameContext oldName() throws RecognitionException {
		OldNameContext _localctx = new OldNameContext(_ctx, getState());
		enterRule(_localctx, 50, RULE_oldName);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(247);
			_la = _input.LA(1);
			if ( !(_la==LINKTYPE || _la==STRING) ) {
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

	public static class NewNameContext extends ParserRuleContext {
		public TerminalNode STRING() { return getToken(CAParser.STRING, 0); }
		public TerminalNode LINKTYPE() { return getToken(CAParser.LINKTYPE, 0); }
		public NewNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_newName; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CAListener ) ((CAListener)listener).enterNewName(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CAListener ) ((CAListener)listener).exitNewName(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CAVisitor ) return ((CAVisitor<? extends T>)visitor).visitNewName(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NewNameContext newName() throws RecognitionException {
		NewNameContext _localctx = new NewNameContext(_ctx, getState());
		enterRule(_localctx, 52, RULE_newName);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(249);
			_la = _input.LA(1);
			if ( !(_la==LINKTYPE || _la==STRING) ) {
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

	public static class RmContext extends ParserRuleContext {
		public TerminalNode RMTOKEN() { return getToken(CAParser.RMTOKEN, 0); }
		public InputidentifierContext inputidentifier() {
			return getRuleContext(InputidentifierContext.class,0);
		}
		public PartitionPatternContext partitionPattern() {
			return getRuleContext(PartitionPatternContext.class,0);
		}
		public RmContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_rm; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CAListener ) ((CAListener)listener).enterRm(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CAListener ) ((CAListener)listener).exitRm(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CAVisitor ) return ((CAVisitor<? extends T>)visitor).visitRm(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RmContext rm() throws RecognitionException {
		RmContext _localctx = new RmContext(_ctx, getState());
		enterRule(_localctx, 54, RULE_rm);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(251);
			match(RMTOKEN);
			setState(254);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,23,_ctx) ) {
			case 1:
				{
				setState(252);
				inputidentifier();
				}
				break;
			case 2:
				{
				setState(253);
				partitionPattern();
				}
				break;
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

	public static class TaxonsetContext extends ParserRuleContext {
		public TerminalNode TAXONSETTOKEN() { return getToken(CAParser.TAXONSETTOKEN, 0); }
		public List<TerminalNode> STRING() { return getTokens(CAParser.STRING); }
		public TerminalNode STRING(int i) {
			return getToken(CAParser.STRING, i);
		}
		public TaxonsetContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_taxonset; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CAListener ) ((CAListener)listener).enterTaxonset(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CAListener ) ((CAListener)listener).exitTaxonset(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CAVisitor ) return ((CAVisitor<? extends T>)visitor).visitTaxonset(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TaxonsetContext taxonset() throws RecognitionException {
		TaxonsetContext _localctx = new TaxonsetContext(_ctx, getState());
		enterRule(_localctx, 56, RULE_taxonset);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(256);
			match(TAXONSETTOKEN);
			setState(257);
			match(STRING);
			setState(258);
			match(T__1);
			setState(260); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(259);
				match(STRING);
				}
				}
				setState(262); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==STRING );
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\34\u010b\4\2\t\2"+
		"\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\3\2\6\2>\n\2\r\2\16"+
		"\2?\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\5\3L\n\3\3\3\3\3\3\4\3\4\3"+
		"\4\3\5\3\5\3\6\3\6\3\6\3\6\5\6Y\n\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3"+
		"\6\3\6\7\6e\n\6\f\6\16\6h\13\6\3\6\3\6\5\6l\n\6\3\7\3\7\3\b\3\b\3\t\3"+
		"\t\5\tt\n\t\3\t\3\t\3\t\3\t\3\t\7\t{\n\t\f\t\16\t~\13\t\3\t\3\t\5\t\u0082"+
		"\n\t\3\n\3\n\3\n\3\n\3\n\3\n\7\n\u008a\n\n\f\n\16\n\u008d\13\n\3\n\3\n"+
		"\5\n\u0091\n\n\3\13\3\13\5\13\u0095\n\13\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3"+
		"\f\3\f\3\f\7\f\u00a1\n\f\f\f\16\f\u00a4\13\f\3\f\3\f\3\r\3\r\3\16\3\16"+
		"\3\17\3\17\3\20\3\20\3\20\5\20\u00b1\n\20\3\20\3\20\3\20\3\20\5\20\u00b7"+
		"\n\20\3\21\3\21\3\22\3\22\3\23\3\23\3\23\5\23\u00c0\n\23\3\23\3\23\3\23"+
		"\3\23\5\23\u00c6\n\23\3\24\3\24\3\24\3\24\3\24\3\25\3\25\5\25\u00cf\n"+
		"\25\3\25\3\25\5\25\u00d3\n\25\3\25\5\25\u00d6\n\25\3\25\5\25\u00d9\n\25"+
		"\3\25\5\25\u00dc\n\25\3\26\3\26\3\26\3\27\3\27\3\27\3\27\3\30\3\30\3\30"+
		"\3\30\7\30\u00e9\n\30\f\30\16\30\u00ec\13\30\3\30\3\30\3\31\3\31\3\32"+
		"\3\32\3\32\5\32\u00f5\n\32\3\32\3\32\3\32\3\33\3\33\3\34\3\34\3\35\3\35"+
		"\3\35\5\35\u0101\n\35\3\36\3\36\3\36\3\36\6\36\u0107\n\36\r\36\16\36\u0108"+
		"\3\36\2\2\37\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \"$&(*,.\60\62\64"+
		"\668:\2\4\4\2\26\26\31\31\4\2\27\27\31\31\2\u0110\2=\3\2\2\2\4K\3\2\2"+
		"\2\6O\3\2\2\2\bR\3\2\2\2\nT\3\2\2\2\fm\3\2\2\2\16o\3\2\2\2\20q\3\2\2\2"+
		"\22\u0083\3\2\2\2\24\u0094\3\2\2\2\26\u0096\3\2\2\2\30\u00a7\3\2\2\2\32"+
		"\u00a9\3\2\2\2\34\u00ab\3\2\2\2\36\u00b6\3\2\2\2 \u00b8\3\2\2\2\"\u00ba"+
		"\3\2\2\2$\u00c5\3\2\2\2&\u00c7\3\2\2\2(\u00db\3\2\2\2*\u00dd\3\2\2\2,"+
		"\u00e0\3\2\2\2.\u00e4\3\2\2\2\60\u00ef\3\2\2\2\62\u00f1\3\2\2\2\64\u00f9"+
		"\3\2\2\2\66\u00fb\3\2\2\28\u00fd\3\2\2\2:\u0102\3\2\2\2<>\5\4\3\2=<\3"+
		"\2\2\2>?\3\2\2\2?=\3\2\2\2?@\3\2\2\2@\3\3\2\2\2AL\5\6\4\2BL\5\20\t\2C"+
		"L\5\n\6\2DL\5&\24\2EL\5\36\20\2FL\5$\23\2GL\5\62\32\2HL\5\22\n\2IL\58"+
		"\35\2JL\5:\36\2KA\3\2\2\2KB\3\2\2\2KC\3\2\2\2KD\3\2\2\2KE\3\2\2\2KF\3"+
		"\2\2\2KG\3\2\2\2KH\3\2\2\2KI\3\2\2\2KJ\3\2\2\2KL\3\2\2\2LM\3\2\2\2MN\7"+
		"\3\2\2N\5\3\2\2\2OP\7\r\2\2PQ\5\b\5\2Q\7\3\2\2\2RS\7\31\2\2S\t\3\2\2\2"+
		"TX\7\22\2\2UV\5(\25\2VW\7\4\2\2WY\3\2\2\2XU\3\2\2\2XY\3\2\2\2YZ\3\2\2"+
		"\2Zk\7\31\2\2[\\\7\5\2\2\\]\5\f\7\2]^\7\4\2\2^f\5\16\b\2_`\7\6\2\2`a\5"+
		"\f\7\2ab\7\4\2\2bc\5\16\b\2ce\3\2\2\2d_\3\2\2\2eh\3\2\2\2fd\3\2\2\2fg"+
		"\3\2\2\2gi\3\2\2\2hf\3\2\2\2ij\7\7\2\2jl\3\2\2\2k[\3\2\2\2kl\3\2\2\2l"+
		"\13\3\2\2\2mn\t\2\2\2n\r\3\2\2\2op\7\31\2\2p\17\3\2\2\2qs\7\16\2\2rt\5"+
		"\32\16\2sr\3\2\2\2st\3\2\2\2tu\3\2\2\2u\u0081\5\30\r\2vw\7\5\2\2w|\5\34"+
		"\17\2xy\7\6\2\2y{\5\34\17\2zx\3\2\2\2{~\3\2\2\2|z\3\2\2\2|}\3\2\2\2}\177"+
		"\3\2\2\2~|\3\2\2\2\177\u0080\7\7\2\2\u0080\u0082\3\2\2\2\u0081v\3\2\2"+
		"\2\u0081\u0082\3\2\2\2\u0082\21\3\2\2\2\u0083\u0084\7\23\2\2\u0084\u0090"+
		"\7\31\2\2\u0085\u0086\7\5\2\2\u0086\u008b\5\24\13\2\u0087\u0088\7\6\2"+
		"\2\u0088\u008a\5\24\13\2\u0089\u0087\3\2\2\2\u008a\u008d\3\2\2\2\u008b"+
		"\u0089\3\2\2\2\u008b\u008c\3\2\2\2\u008c\u008e\3\2\2\2\u008d\u008b\3\2"+
		"\2\2\u008e\u008f\7\7\2\2\u008f\u0091\3\2\2\2\u0090\u0085\3\2\2\2\u0090"+
		"\u0091\3\2\2\2\u0091\23\3\2\2\2\u0092\u0095\5\16\b\2\u0093\u0095\5\26"+
		"\f\2\u0094\u0092\3\2\2\2\u0094\u0093\3\2\2\2\u0095\25\3\2\2\2\u0096\u0097"+
		"\7\31\2\2\u0097\u0098\7\5\2\2\u0098\u0099\5\f\7\2\u0099\u009a\7\4\2\2"+
		"\u009a\u00a2\5\16\b\2\u009b\u009c\7\6\2\2\u009c\u009d\5\f\7\2\u009d\u009e"+
		"\7\4\2\2\u009e\u009f\5\16\b\2\u009f\u00a1\3\2\2\2\u00a0\u009b\3\2\2\2"+
		"\u00a1\u00a4\3\2\2\2\u00a2\u00a0\3\2\2\2\u00a2\u00a3\3\2\2\2\u00a3\u00a5"+
		"\3\2\2\2\u00a4\u00a2\3\2\2\2\u00a5\u00a6\7\7\2\2\u00a6\27\3\2\2\2\u00a7"+
		"\u00a8\7\31\2\2\u00a8\31\3\2\2\2\u00a9\u00aa\7\31\2\2\u00aa\33\3\2\2\2"+
		"\u00ab\u00ac\7\31\2\2\u00ac\35\3\2\2\2\u00ad\u00ae\7\17\2\2\u00ae\u00b0"+
		"\5 \21\2\u00af\u00b1\5.\30\2\u00b0\u00af\3\2\2\2\u00b0\u00b1\3\2\2\2\u00b1"+
		"\u00b7\3\2\2\2\u00b2\u00b3\7\17\2\2\u00b3\u00b4\5\"\22\2\u00b4\u00b5\5"+
		"(\25\2\u00b5\u00b7\3\2\2\2\u00b6\u00ad\3\2\2\2\u00b6\u00b2\3\2\2\2\u00b7"+
		"\37\3\2\2\2\u00b8\u00b9\7\27\2\2\u00b9!\3\2\2\2\u00ba\u00bb\7\30\2\2\u00bb"+
		"#\3\2\2\2\u00bc\u00bd\7\20\2\2\u00bd\u00bf\5 \21\2\u00be\u00c0\5.\30\2"+
		"\u00bf\u00be\3\2\2\2\u00bf\u00c0\3\2\2\2\u00c0\u00c6\3\2\2\2\u00c1\u00c2"+
		"\7\20\2\2\u00c2\u00c3\5\"\22\2\u00c3\u00c4\5(\25\2\u00c4\u00c6\3\2\2\2"+
		"\u00c5\u00bc\3\2\2\2\u00c5\u00c1\3\2\2\2\u00c6%\3\2\2\2\u00c7\u00c8\7"+
		"\21\2\2\u00c8\u00c9\5(\25\2\u00c9\u00ca\7\4\2\2\u00ca\u00cb\7\31\2\2\u00cb"+
		"\'\3\2\2\2\u00cc\u00dc\5,\27\2\u00cd\u00cf\5*\26\2\u00ce\u00cd\3\2\2\2"+
		"\u00ce\u00cf\3\2\2\2\u00cf\u00d0\3\2\2\2\u00d0\u00d2\5\60\31\2\u00d1\u00d3"+
		"\5,\27\2\u00d2\u00d1\3\2\2\2\u00d2\u00d3\3\2\2\2\u00d3\u00dc\3\2\2\2\u00d4"+
		"\u00d6\5*\26\2\u00d5\u00d4\3\2\2\2\u00d5\u00d6\3\2\2\2\u00d6\u00d8\3\2"+
		"\2\2\u00d7\u00d9\5\60\31\2\u00d8\u00d7\3\2\2\2\u00d8\u00d9\3\2\2\2\u00d9"+
		"\u00da\3\2\2\2\u00da\u00dc\5.\30\2\u00db\u00cc\3\2\2\2\u00db\u00ce\3\2"+
		"\2\2\u00db\u00d5\3\2\2\2\u00dc)\3\2\2\2\u00dd\u00de\7\31\2\2\u00de\u00df"+
		"\7\b\2\2\u00df+\3\2\2\2\u00e0\u00e1\7\t\2\2\u00e1\u00e2\7\31\2\2\u00e2"+
		"\u00e3\7\n\2\2\u00e3-\3\2\2\2\u00e4\u00e5\7\13\2\2\u00e5\u00ea\t\3\2\2"+
		"\u00e6\u00e7\7\6\2\2\u00e7\u00e9\t\3\2\2\u00e8\u00e6\3\2\2\2\u00e9\u00ec"+
		"\3\2\2\2\u00ea\u00e8\3\2\2\2\u00ea\u00eb\3\2\2\2\u00eb\u00ed\3\2\2\2\u00ec"+
		"\u00ea\3\2\2\2\u00ed\u00ee\7\f\2\2\u00ee/\3\2\2\2\u00ef\u00f0\7\31\2\2"+
		"\u00f0\61\3\2\2\2\u00f1\u00f2\7\24\2\2\u00f2\u00f4\5 \21\2\u00f3\u00f5"+
		"\5\64\33\2\u00f4\u00f3\3\2\2\2\u00f4\u00f5\3\2\2\2\u00f5\u00f6\3\2\2\2"+
		"\u00f6\u00f7\7\4\2\2\u00f7\u00f8\5\66\34\2\u00f8\63\3\2\2\2\u00f9\u00fa"+
		"\t\3\2\2\u00fa\65\3\2\2\2\u00fb\u00fc\t\3\2\2\u00fc\67\3\2\2\2\u00fd\u0100"+
		"\7\25\2\2\u00fe\u0101\5(\25\2\u00ff\u0101\5.\30\2\u0100\u00fe\3\2\2\2"+
		"\u0100\u00ff\3\2\2\2\u01019\3\2\2\2\u0102\u0103\7\26\2\2\u0103\u0104\7"+
		"\31\2\2\u0104\u0106\7\4\2\2\u0105\u0107\7\31\2\2\u0106\u0105\3\2\2\2\u0107"+
		"\u0108\3\2\2\2\u0108\u0106\3\2\2\2\u0108\u0109\3\2\2\2\u0109;\3\2\2\2"+
		"\33?KXfks|\u0081\u008b\u0090\u0094\u00a2\u00b0\u00b6\u00bf\u00c5\u00ce"+
		"\u00d2\u00d5\u00d8\u00db\u00ea\u00f4\u0100\u0108";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}