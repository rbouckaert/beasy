// Generated from CA.g4 by ANTLR 4.5
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
	static { RuntimeMetaData.checkVersion("4.5", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, TEMPLATETOKEN=9, 
		IMPORTTOKEN=10, LINKTOKEN=11, UNLINKTOKEN=12, SETTOKEN=13, USETOKEN=14, 
		ADDTOKEN=15, RENAMETOKEN=16, RMTOKEN=17, TAXONSETTOKEN=18, LINKTYPE=19, 
		STRING=20, WHITESPACE=21, COMMENT=22, LINE_COMMENT=23;
	public static final int
		RULE_casentence = 0, RULE_cmd = 1, RULE_template = 2, RULE_templatename = 3, 
		RULE_use = 4, RULE_key = 5, RULE_value = 6, RULE_import_ = 7, RULE_add = 8, 
		RULE_argOrUse = 9, RULE_subtemplate = 10, RULE_filename = 11, RULE_alignmentprovider = 12, 
		RULE_arg = 13, RULE_partitionpattern = 14, RULE_link = 15, RULE_unlink = 16, 
		RULE_set = 17, RULE_inputidentifier = 18, RULE_elemntName = 19, RULE_idPattern = 20, 
		RULE_inputname = 21, RULE_rename = 22, RULE_oldName = 23, RULE_newName = 24, 
		RULE_rm = 25, RULE_taxonset = 26;
	public static final String[] ruleNames = {
		"casentence", "cmd", "template", "templatename", "use", "key", "value", 
		"import_", "add", "argOrUse", "subtemplate", "filename", "alignmentprovider", 
		"arg", "partitionpattern", "link", "unlink", "set", "inputidentifier", 
		"elemntName", "idPattern", "inputname", "rename", "oldName", "newName", 
		"rm", "taxonset"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "';'", "'='", "'('", "','", "')'", "'@'", "'['", "']'", "'template'", 
		"'import'", "'link'", "'unlink'", "'set'", "'use'", "'add'", "'rename'", 
		"'rm'", "'taxonset'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, null, null, "TEMPLATETOKEN", 
		"IMPORTTOKEN", "LINKTOKEN", "UNLINKTOKEN", "SETTOKEN", "USETOKEN", "ADDTOKEN", 
		"RENAMETOKEN", "RMTOKEN", "TAXONSETTOKEN", "LINKTYPE", "STRING", "WHITESPACE", 
		"COMMENT", "LINE_COMMENT"
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
			setState(57);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__0) | (1L << TEMPLATETOKEN) | (1L << IMPORTTOKEN) | (1L << LINKTOKEN) | (1L << UNLINKTOKEN) | (1L << SETTOKEN) | (1L << USETOKEN) | (1L << ADDTOKEN) | (1L << RENAMETOKEN) | (1L << RMTOKEN) | (1L << TAXONSETTOKEN))) != 0)) {
				{
				{
				setState(54);
				cmd();
				}
				}
				setState(59);
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
			setState(70);
			switch (_input.LA(1)) {
			case TEMPLATETOKEN:
				{
				setState(60);
				template();
				}
				break;
			case IMPORTTOKEN:
				{
				setState(61);
				import_();
				}
				break;
			case USETOKEN:
				{
				setState(62);
				use();
				}
				break;
			case SETTOKEN:
				{
				setState(63);
				set();
				}
				break;
			case LINKTOKEN:
				{
				setState(64);
				link();
				}
				break;
			case UNLINKTOKEN:
				{
				setState(65);
				unlink();
				}
				break;
			case RENAMETOKEN:
				{
				setState(66);
				rename();
				}
				break;
			case ADDTOKEN:
				{
				setState(67);
				add();
				}
				break;
			case RMTOKEN:
				{
				setState(68);
				rm();
				}
				break;
			case TAXONSETTOKEN:
				{
				setState(69);
				taxonset();
				}
				break;
			case T__0:
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(72);
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
			setState(74);
			match(TEMPLATETOKEN);
			setState(75);
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
			setState(77);
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
			setState(79);
			match(USETOKEN);
			setState(83);
			switch ( getInterpreter().adaptivePredict(_input,2,_ctx) ) {
			case 1:
				{
				setState(80);
				inputidentifier();
				setState(81);
				match(T__1);
				}
				break;
			}
			setState(85);
			match(STRING);
			setState(102);
			_la = _input.LA(1);
			if (_la==T__2) {
				{
				setState(86);
				match(T__2);
				setState(87);
				key();
				setState(88);
				match(T__1);
				setState(89);
				value();
				setState(97);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__3) {
					{
					{
					setState(90);
					match(T__3);
					setState(91);
					key();
					setState(92);
					match(T__1);
					setState(93);
					value();
					}
					}
					setState(99);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(100);
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
			setState(104);
			_la = _input.LA(1);
			if ( !(_la==TAXONSETTOKEN || _la==STRING) ) {
			_errHandler.recoverInline(this);
			} else {
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
			setState(106);
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
			setState(108);
			match(IMPORTTOKEN);
			setState(110);
			switch ( getInterpreter().adaptivePredict(_input,5,_ctx) ) {
			case 1:
				{
				setState(109);
				alignmentprovider();
				}
				break;
			}
			setState(112);
			filename();
			setState(124);
			_la = _input.LA(1);
			if (_la==T__2) {
				{
				setState(113);
				match(T__2);
				setState(114);
				arg();
				setState(119);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__3) {
					{
					{
					setState(115);
					match(T__3);
					setState(116);
					arg();
					}
					}
					setState(121);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(122);
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
			setState(126);
			match(ADDTOKEN);
			setState(127);
			match(STRING);
			setState(139);
			_la = _input.LA(1);
			if (_la==T__2) {
				{
				setState(128);
				match(T__2);
				setState(129);
				argOrUse();
				setState(134);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__3) {
					{
					{
					setState(130);
					match(T__3);
					setState(131);
					argOrUse();
					}
					}
					setState(136);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(137);
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
			setState(143);
			switch ( getInterpreter().adaptivePredict(_input,10,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(141);
				value();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(142);
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
			setState(145);
			match(STRING);
			setState(146);
			match(T__2);
			setState(147);
			key();
			setState(148);
			match(T__1);
			setState(149);
			value();
			setState(157);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__3) {
				{
				{
				setState(150);
				match(T__3);
				setState(151);
				key();
				setState(152);
				match(T__1);
				setState(153);
				value();
				}
				}
				setState(159);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(160);
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
			setState(162);
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
			setState(164);
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
			setState(166);
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

	public static class PartitionpatternContext extends ParserRuleContext {
		public TerminalNode STRING() { return getToken(CAParser.STRING, 0); }
		public PartitionpatternContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_partitionpattern; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CAListener ) ((CAListener)listener).enterPartitionpattern(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CAListener ) ((CAListener)listener).exitPartitionpattern(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CAVisitor ) return ((CAVisitor<? extends T>)visitor).visitPartitionpattern(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PartitionpatternContext partitionpattern() throws RecognitionException {
		PartitionpatternContext _localctx = new PartitionpatternContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_partitionpattern);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(168);
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
		public TerminalNode LINKTYPE() { return getToken(CAParser.LINKTYPE, 0); }
		public PartitionpatternContext partitionpattern() {
			return getRuleContext(PartitionpatternContext.class,0);
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
		enterRule(_localctx, 30, RULE_link);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(170);
			match(LINKTOKEN);
			setState(171);
			match(LINKTYPE);
			setState(173);
			_la = _input.LA(1);
			if (_la==STRING) {
				{
				setState(172);
				partitionpattern();
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

	public static class UnlinkContext extends ParserRuleContext {
		public TerminalNode UNLINKTOKEN() { return getToken(CAParser.UNLINKTOKEN, 0); }
		public TerminalNode LINKTYPE() { return getToken(CAParser.LINKTYPE, 0); }
		public PartitionpatternContext partitionpattern() {
			return getRuleContext(PartitionpatternContext.class,0);
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
		enterRule(_localctx, 32, RULE_unlink);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(175);
			match(UNLINKTOKEN);
			setState(176);
			match(LINKTYPE);
			setState(178);
			_la = _input.LA(1);
			if (_la==STRING) {
				{
				setState(177);
				partitionpattern();
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
		enterRule(_localctx, 34, RULE_set);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(180);
			match(SETTOKEN);
			setState(181);
			inputidentifier();
			setState(182);
			match(T__1);
			setState(183);
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
		enterRule(_localctx, 36, RULE_inputidentifier);
		int _la;
		try {
			setState(193);
			switch (_input.LA(1)) {
			case T__6:
				enterOuterAlt(_localctx, 1);
				{
				setState(185);
				idPattern();
				}
				break;
			case STRING:
				enterOuterAlt(_localctx, 2);
				{
				setState(187);
				switch ( getInterpreter().adaptivePredict(_input,14,_ctx) ) {
				case 1:
					{
					setState(186);
					elemntName();
					}
					break;
				}
				setState(189);
				inputname();
				setState(191);
				_la = _input.LA(1);
				if (_la==T__6) {
					{
					setState(190);
					idPattern();
					}
				}

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
		enterRule(_localctx, 38, RULE_elemntName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(195);
			match(STRING);
			setState(196);
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
		enterRule(_localctx, 40, RULE_idPattern);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(198);
			match(T__6);
			setState(199);
			match(STRING);
			setState(200);
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
		enterRule(_localctx, 42, RULE_inputname);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(202);
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
		public TerminalNode LINKTYPE() { return getToken(CAParser.LINKTYPE, 0); }
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
		enterRule(_localctx, 44, RULE_rename);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(204);
			match(RENAMETOKEN);
			setState(205);
			match(LINKTYPE);
			setState(207);
			_la = _input.LA(1);
			if (_la==LINKTYPE || _la==STRING) {
				{
				setState(206);
				oldName();
				}
			}

			setState(209);
			match(T__1);
			setState(210);
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
		enterRule(_localctx, 46, RULE_oldName);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(212);
			_la = _input.LA(1);
			if ( !(_la==LINKTYPE || _la==STRING) ) {
			_errHandler.recoverInline(this);
			} else {
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
		enterRule(_localctx, 48, RULE_newName);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(214);
			_la = _input.LA(1);
			if ( !(_la==LINKTYPE || _la==STRING) ) {
			_errHandler.recoverInline(this);
			} else {
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
		enterRule(_localctx, 50, RULE_rm);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(216);
			match(RMTOKEN);
			setState(217);
			inputidentifier();
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
		enterRule(_localctx, 52, RULE_taxonset);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(219);
			match(TAXONSETTOKEN);
			setState(220);
			match(STRING);
			setState(221);
			match(T__1);
			setState(223); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(222);
				match(STRING);
				}
				}
				setState(225); 
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
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3\31\u00e6\4\2\t\2"+
		"\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\3\2\7\2:\n\2\f\2\16\2=\13\2\3\3\3\3\3\3"+
		"\3\3\3\3\3\3\3\3\3\3\3\3\3\3\5\3I\n\3\3\3\3\3\3\4\3\4\3\4\3\5\3\5\3\6"+
		"\3\6\3\6\3\6\5\6V\n\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\7\6b\n\6"+
		"\f\6\16\6e\13\6\3\6\3\6\5\6i\n\6\3\7\3\7\3\b\3\b\3\t\3\t\5\tq\n\t\3\t"+
		"\3\t\3\t\3\t\3\t\7\tx\n\t\f\t\16\t{\13\t\3\t\3\t\5\t\177\n\t\3\n\3\n\3"+
		"\n\3\n\3\n\3\n\7\n\u0087\n\n\f\n\16\n\u008a\13\n\3\n\3\n\5\n\u008e\n\n"+
		"\3\13\3\13\5\13\u0092\n\13\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\7\f"+
		"\u009e\n\f\f\f\16\f\u00a1\13\f\3\f\3\f\3\r\3\r\3\16\3\16\3\17\3\17\3\20"+
		"\3\20\3\21\3\21\3\21\5\21\u00b0\n\21\3\22\3\22\3\22\5\22\u00b5\n\22\3"+
		"\23\3\23\3\23\3\23\3\23\3\24\3\24\5\24\u00be\n\24\3\24\3\24\5\24\u00c2"+
		"\n\24\5\24\u00c4\n\24\3\25\3\25\3\25\3\26\3\26\3\26\3\26\3\27\3\27\3\30"+
		"\3\30\3\30\5\30\u00d2\n\30\3\30\3\30\3\30\3\31\3\31\3\32\3\32\3\33\3\33"+
		"\3\33\3\34\3\34\3\34\3\34\6\34\u00e2\n\34\r\34\16\34\u00e3\3\34\2\2\35"+
		"\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \"$&(*,.\60\62\64\66\2\4\4\2\24"+
		"\24\26\26\3\2\25\26\u00e6\2;\3\2\2\2\4H\3\2\2\2\6L\3\2\2\2\bO\3\2\2\2"+
		"\nQ\3\2\2\2\fj\3\2\2\2\16l\3\2\2\2\20n\3\2\2\2\22\u0080\3\2\2\2\24\u0091"+
		"\3\2\2\2\26\u0093\3\2\2\2\30\u00a4\3\2\2\2\32\u00a6\3\2\2\2\34\u00a8\3"+
		"\2\2\2\36\u00aa\3\2\2\2 \u00ac\3\2\2\2\"\u00b1\3\2\2\2$\u00b6\3\2\2\2"+
		"&\u00c3\3\2\2\2(\u00c5\3\2\2\2*\u00c8\3\2\2\2,\u00cc\3\2\2\2.\u00ce\3"+
		"\2\2\2\60\u00d6\3\2\2\2\62\u00d8\3\2\2\2\64\u00da\3\2\2\2\66\u00dd\3\2"+
		"\2\28:\5\4\3\298\3\2\2\2:=\3\2\2\2;9\3\2\2\2;<\3\2\2\2<\3\3\2\2\2=;\3"+
		"\2\2\2>I\5\6\4\2?I\5\20\t\2@I\5\n\6\2AI\5$\23\2BI\5 \21\2CI\5\"\22\2D"+
		"I\5.\30\2EI\5\22\n\2FI\5\64\33\2GI\5\66\34\2H>\3\2\2\2H?\3\2\2\2H@\3\2"+
		"\2\2HA\3\2\2\2HB\3\2\2\2HC\3\2\2\2HD\3\2\2\2HE\3\2\2\2HF\3\2\2\2HG\3\2"+
		"\2\2HI\3\2\2\2IJ\3\2\2\2JK\7\3\2\2K\5\3\2\2\2LM\7\13\2\2MN\5\b\5\2N\7"+
		"\3\2\2\2OP\7\26\2\2P\t\3\2\2\2QU\7\20\2\2RS\5&\24\2ST\7\4\2\2TV\3\2\2"+
		"\2UR\3\2\2\2UV\3\2\2\2VW\3\2\2\2Wh\7\26\2\2XY\7\5\2\2YZ\5\f\7\2Z[\7\4"+
		"\2\2[c\5\16\b\2\\]\7\6\2\2]^\5\f\7\2^_\7\4\2\2_`\5\16\b\2`b\3\2\2\2a\\"+
		"\3\2\2\2be\3\2\2\2ca\3\2\2\2cd\3\2\2\2df\3\2\2\2ec\3\2\2\2fg\7\7\2\2g"+
		"i\3\2\2\2hX\3\2\2\2hi\3\2\2\2i\13\3\2\2\2jk\t\2\2\2k\r\3\2\2\2lm\7\26"+
		"\2\2m\17\3\2\2\2np\7\f\2\2oq\5\32\16\2po\3\2\2\2pq\3\2\2\2qr\3\2\2\2r"+
		"~\5\30\r\2st\7\5\2\2ty\5\34\17\2uv\7\6\2\2vx\5\34\17\2wu\3\2\2\2x{\3\2"+
		"\2\2yw\3\2\2\2yz\3\2\2\2z|\3\2\2\2{y\3\2\2\2|}\7\7\2\2}\177\3\2\2\2~s"+
		"\3\2\2\2~\177\3\2\2\2\177\21\3\2\2\2\u0080\u0081\7\21\2\2\u0081\u008d"+
		"\7\26\2\2\u0082\u0083\7\5\2\2\u0083\u0088\5\24\13\2\u0084\u0085\7\6\2"+
		"\2\u0085\u0087\5\24\13\2\u0086\u0084\3\2\2\2\u0087\u008a\3\2\2\2\u0088"+
		"\u0086\3\2\2\2\u0088\u0089\3\2\2\2\u0089\u008b\3\2\2\2\u008a\u0088\3\2"+
		"\2\2\u008b\u008c\7\7\2\2\u008c\u008e\3\2\2\2\u008d\u0082\3\2\2\2\u008d"+
		"\u008e\3\2\2\2\u008e\23\3\2\2\2\u008f\u0092\5\16\b\2\u0090\u0092\5\26"+
		"\f\2\u0091\u008f\3\2\2\2\u0091\u0090\3\2\2\2\u0092\25\3\2\2\2\u0093\u0094"+
		"\7\26\2\2\u0094\u0095\7\5\2\2\u0095\u0096\5\f\7\2\u0096\u0097\7\4\2\2"+
		"\u0097\u009f\5\16\b\2\u0098\u0099\7\6\2\2\u0099\u009a\5\f\7\2\u009a\u009b"+
		"\7\4\2\2\u009b\u009c\5\16\b\2\u009c\u009e\3\2\2\2\u009d\u0098\3\2\2\2"+
		"\u009e\u00a1\3\2\2\2\u009f\u009d\3\2\2\2\u009f\u00a0\3\2\2\2\u00a0\u00a2"+
		"\3\2\2\2\u00a1\u009f\3\2\2\2\u00a2\u00a3\7\7\2\2\u00a3\27\3\2\2\2\u00a4"+
		"\u00a5\7\26\2\2\u00a5\31\3\2\2\2\u00a6\u00a7\7\26\2\2\u00a7\33\3\2\2\2"+
		"\u00a8\u00a9\7\26\2\2\u00a9\35\3\2\2\2\u00aa\u00ab\7\26\2\2\u00ab\37\3"+
		"\2\2\2\u00ac\u00ad\7\r\2\2\u00ad\u00af\7\25\2\2\u00ae\u00b0\5\36\20\2"+
		"\u00af\u00ae\3\2\2\2\u00af\u00b0\3\2\2\2\u00b0!\3\2\2\2\u00b1\u00b2\7"+
		"\16\2\2\u00b2\u00b4\7\25\2\2\u00b3\u00b5\5\36\20\2\u00b4\u00b3\3\2\2\2"+
		"\u00b4\u00b5\3\2\2\2\u00b5#\3\2\2\2\u00b6\u00b7\7\17\2\2\u00b7\u00b8\5"+
		"&\24\2\u00b8\u00b9\7\4\2\2\u00b9\u00ba\7\26\2\2\u00ba%\3\2\2\2\u00bb\u00c4"+
		"\5*\26\2\u00bc\u00be\5(\25\2\u00bd\u00bc\3\2\2\2\u00bd\u00be\3\2\2\2\u00be"+
		"\u00bf\3\2\2\2\u00bf\u00c1\5,\27\2\u00c0\u00c2\5*\26\2\u00c1\u00c0\3\2"+
		"\2\2\u00c1\u00c2\3\2\2\2\u00c2\u00c4\3\2\2\2\u00c3\u00bb\3\2\2\2\u00c3"+
		"\u00bd\3\2\2\2\u00c4\'\3\2\2\2\u00c5\u00c6\7\26\2\2\u00c6\u00c7\7\b\2"+
		"\2\u00c7)\3\2\2\2\u00c8\u00c9\7\t\2\2\u00c9\u00ca\7\26\2\2\u00ca\u00cb"+
		"\7\n\2\2\u00cb+\3\2\2\2\u00cc\u00cd\7\26\2\2\u00cd-\3\2\2\2\u00ce\u00cf"+
		"\7\22\2\2\u00cf\u00d1\7\25\2\2\u00d0\u00d2\5\60\31\2\u00d1\u00d0\3\2\2"+
		"\2\u00d1\u00d2\3\2\2\2\u00d2\u00d3\3\2\2\2\u00d3\u00d4\7\4\2\2\u00d4\u00d5"+
		"\5\62\32\2\u00d5/\3\2\2\2\u00d6\u00d7\t\3\2\2\u00d7\61\3\2\2\2\u00d8\u00d9"+
		"\t\3\2\2\u00d9\63\3\2\2\2\u00da\u00db\7\23\2\2\u00db\u00dc\5&\24\2\u00dc"+
		"\65\3\2\2\2\u00dd\u00de\7\24\2\2\u00de\u00df\7\26\2\2\u00df\u00e1\7\4"+
		"\2\2\u00e0\u00e2\7\26\2\2\u00e1\u00e0\3\2\2\2\u00e2\u00e3\3\2\2\2\u00e3"+
		"\u00e1\3\2\2\2\u00e3\u00e4\3\2\2\2\u00e4\67\3\2\2\2\25;HUchpy~\u0088\u008d"+
		"\u0091\u009f\u00af\u00b4\u00bd\u00c1\u00c3\u00d1\u00e3";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}