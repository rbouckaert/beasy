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
		MODETOKEN=21, LINKTYPE=22, SHARETYPE=23, MODENAME=24, STRING=25, WHITESPACE=26, 
		COMMENT=27, LINE_COMMENT=28;
	public static final int
		RULE_casentence = 0, RULE_cmd = 1, RULE_template = 2, RULE_templatename = 3, 
		RULE_use = 4, RULE_key = 5, RULE_value = 6, RULE_import_ = 7, RULE_add = 8, 
		RULE_argOrUse = 9, RULE_subtemplate = 10, RULE_filename = 11, RULE_alignmentprovider = 12, 
		RULE_arg = 13, RULE_link = 14, RULE_linktype = 15, RULE_sharetype = 16, 
		RULE_unlink = 17, RULE_set = 18, RULE_inputidentifier = 19, RULE_elemntName = 20, 
		RULE_idPattern = 21, RULE_partitionPattern = 22, RULE_inputname = 23, 
		RULE_rename = 24, RULE_oldName = 25, RULE_newName = 26, RULE_rm = 27, 
		RULE_mode_ = 28, RULE_taxonset = 29;
	public static final String[] ruleNames = {
		"casentence", "cmd", "template", "templatename", "use", "key", "value", 
		"import_", "add", "argOrUse", "subtemplate", "filename", "alignmentprovider", 
		"arg", "link", "linktype", "sharetype", "unlink", "set", "inputidentifier", 
		"elemntName", "idPattern", "partitionPattern", "inputname", "rename", 
		"oldName", "newName", "rm", "mode_", "taxonset"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "';'", "'='", "'('", "','", "')'", "'@'", "'['", "']'", "'{'", "'}'", 
		"'template'", "'import'", "'link'", "'unlink'", "'set'", "'use'", "'add'", 
		"'rename'", "'rm'", "'taxonset'", "'mode'", null, "'param'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, null, null, null, null, "TEMPLATETOKEN", 
		"IMPORTTOKEN", "LINKTOKEN", "UNLINKTOKEN", "SETTOKEN", "USETOKEN", "ADDTOKEN", 
		"RENAMETOKEN", "RMTOKEN", "TAXONSETTOKEN", "MODETOKEN", "LINKTYPE", "SHARETYPE", 
		"MODENAME", "STRING", "WHITESPACE", "COMMENT", "LINE_COMMENT"
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
			setState(61); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(60);
				cmd();
				}
				}
				setState(63); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__0) | (1L << TEMPLATETOKEN) | (1L << IMPORTTOKEN) | (1L << LINKTOKEN) | (1L << UNLINKTOKEN) | (1L << SETTOKEN) | (1L << USETOKEN) | (1L << ADDTOKEN) | (1L << RENAMETOKEN) | (1L << RMTOKEN) | (1L << TAXONSETTOKEN) | (1L << MODETOKEN))) != 0) );
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
		public Mode_Context mode_() {
			return getRuleContext(Mode_Context.class,0);
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
			setState(76);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case TEMPLATETOKEN:
				{
				setState(65);
				template();
				}
				break;
			case IMPORTTOKEN:
				{
				setState(66);
				import_();
				}
				break;
			case USETOKEN:
				{
				setState(67);
				use();
				}
				break;
			case SETTOKEN:
				{
				setState(68);
				set();
				}
				break;
			case LINKTOKEN:
				{
				setState(69);
				link();
				}
				break;
			case UNLINKTOKEN:
				{
				setState(70);
				unlink();
				}
				break;
			case RENAMETOKEN:
				{
				setState(71);
				rename();
				}
				break;
			case ADDTOKEN:
				{
				setState(72);
				add();
				}
				break;
			case RMTOKEN:
				{
				setState(73);
				rm();
				}
				break;
			case TAXONSETTOKEN:
				{
				setState(74);
				taxonset();
				}
				break;
			case MODETOKEN:
				{
				setState(75);
				mode_();
				}
				break;
			case T__0:
				break;
			default:
				break;
			}
			setState(78);
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
			setState(80);
			match(TEMPLATETOKEN);
			setState(81);
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
			setState(83);
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
			setState(85);
			match(USETOKEN);
			setState(89);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,2,_ctx) ) {
			case 1:
				{
				setState(86);
				inputidentifier();
				setState(87);
				match(T__1);
				}
				break;
			}
			setState(91);
			match(STRING);
			setState(108);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__2) {
				{
				setState(92);
				match(T__2);
				setState(93);
				key();
				setState(94);
				match(T__1);
				setState(95);
				value();
				setState(103);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__3) {
					{
					{
					setState(96);
					match(T__3);
					setState(97);
					key();
					setState(98);
					match(T__1);
					setState(99);
					value();
					}
					}
					setState(105);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(106);
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
			setState(110);
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
			setState(112);
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
			setState(114);
			match(IMPORTTOKEN);
			setState(116);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,5,_ctx) ) {
			case 1:
				{
				setState(115);
				alignmentprovider();
				}
				break;
			}
			setState(118);
			filename();
			setState(130);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__2) {
				{
				setState(119);
				match(T__2);
				setState(120);
				arg();
				setState(125);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__3) {
					{
					{
					setState(121);
					match(T__3);
					setState(122);
					arg();
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
			setState(132);
			match(ADDTOKEN);
			setState(133);
			match(STRING);
			setState(145);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__2) {
				{
				setState(134);
				match(T__2);
				setState(135);
				argOrUse();
				setState(140);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__3) {
					{
					{
					setState(136);
					match(T__3);
					setState(137);
					argOrUse();
					}
					}
					setState(142);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(143);
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
			setState(149);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,10,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(147);
				value();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(148);
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
			setState(151);
			match(STRING);
			setState(152);
			match(T__2);
			setState(153);
			key();
			setState(154);
			match(T__1);
			setState(155);
			value();
			setState(163);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__3) {
				{
				{
				setState(156);
				match(T__3);
				setState(157);
				key();
				setState(158);
				match(T__1);
				setState(159);
				value();
				}
				}
				setState(165);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(166);
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
			setState(170);
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
			setState(172);
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
			setState(183);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,13,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(174);
				match(LINKTOKEN);
				setState(175);
				linktype();
				setState(177);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__8) {
					{
					setState(176);
					partitionPattern();
					}
				}

				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(179);
				match(LINKTOKEN);
				setState(180);
				sharetype();
				setState(181);
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
			setState(185);
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
			setState(187);
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
			setState(198);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,15,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(189);
				match(UNLINKTOKEN);
				setState(190);
				linktype();
				setState(192);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__8) {
					{
					setState(191);
					partitionPattern();
					}
				}

				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(194);
				match(UNLINKTOKEN);
				setState(195);
				sharetype();
				setState(196);
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
			setState(200);
			match(SETTOKEN);
			setState(201);
			inputidentifier();
			setState(202);
			match(T__1);
			setState(203);
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
			setState(220);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,20,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(205);
				idPattern();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(207);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,16,_ctx) ) {
				case 1:
					{
					setState(206);
					elemntName();
					}
					break;
				}
				setState(209);
				inputname();
				setState(211);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__6) {
					{
					setState(210);
					idPattern();
					}
				}

				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(214);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,18,_ctx) ) {
				case 1:
					{
					setState(213);
					elemntName();
					}
					break;
				}
				setState(217);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==STRING) {
					{
					setState(216);
					inputname();
					}
				}

				setState(219);
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
			setState(222);
			match(STRING);
			setState(223);
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
			setState(225);
			match(T__6);
			setState(226);
			match(STRING);
			setState(227);
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
			setState(229);
			match(T__8);
			setState(230);
			_la = _input.LA(1);
			if ( !(_la==LINKTYPE || _la==STRING) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			setState(235);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__3) {
				{
				{
				setState(231);
				match(T__3);
				setState(232);
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
				setState(237);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(238);
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
			setState(240);
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
			setState(242);
			match(RENAMETOKEN);
			setState(243);
			linktype();
			setState(245);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==LINKTYPE || _la==STRING) {
				{
				setState(244);
				oldName();
				}
			}

			setState(247);
			match(T__1);
			setState(248);
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
			setState(250);
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
			setState(252);
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
			setState(254);
			match(RMTOKEN);
			setState(257);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,23,_ctx) ) {
			case 1:
				{
				setState(255);
				inputidentifier();
				}
				break;
			case 2:
				{
				setState(256);
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

	public static class Mode_Context extends ParserRuleContext {
		public TerminalNode MODETOKEN() { return getToken(CAParser.MODETOKEN, 0); }
		public TerminalNode MODENAME() { return getToken(CAParser.MODENAME, 0); }
		public TerminalNode STRING() { return getToken(CAParser.STRING, 0); }
		public Mode_Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_mode_; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CAListener ) ((CAListener)listener).enterMode_(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CAListener ) ((CAListener)listener).exitMode_(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CAVisitor ) return ((CAVisitor<? extends T>)visitor).visitMode_(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Mode_Context mode_() throws RecognitionException {
		Mode_Context _localctx = new Mode_Context(_ctx, getState());
		enterRule(_localctx, 56, RULE_mode_);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(259);
			match(MODETOKEN);
			setState(260);
			match(MODENAME);
			setState(261);
			match(T__1);
			setState(262);
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
		enterRule(_localctx, 58, RULE_taxonset);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(264);
			match(TAXONSETTOKEN);
			setState(265);
			match(STRING);
			setState(266);
			match(T__1);
			setState(268); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(267);
				match(STRING);
				}
				}
				setState(270); 
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\36\u0113\4\2\t\2"+
		"\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\3\2\6\2@"+
		"\n\2\r\2\16\2A\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\5\3O\n\3\3"+
		"\3\3\3\3\4\3\4\3\4\3\5\3\5\3\6\3\6\3\6\3\6\5\6\\\n\6\3\6\3\6\3\6\3\6\3"+
		"\6\3\6\3\6\3\6\3\6\3\6\7\6h\n\6\f\6\16\6k\13\6\3\6\3\6\5\6o\n\6\3\7\3"+
		"\7\3\b\3\b\3\t\3\t\5\tw\n\t\3\t\3\t\3\t\3\t\3\t\7\t~\n\t\f\t\16\t\u0081"+
		"\13\t\3\t\3\t\5\t\u0085\n\t\3\n\3\n\3\n\3\n\3\n\3\n\7\n\u008d\n\n\f\n"+
		"\16\n\u0090\13\n\3\n\3\n\5\n\u0094\n\n\3\13\3\13\5\13\u0098\n\13\3\f\3"+
		"\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\7\f\u00a4\n\f\f\f\16\f\u00a7\13\f\3"+
		"\f\3\f\3\r\3\r\3\16\3\16\3\17\3\17\3\20\3\20\3\20\5\20\u00b4\n\20\3\20"+
		"\3\20\3\20\3\20\5\20\u00ba\n\20\3\21\3\21\3\22\3\22\3\23\3\23\3\23\5\23"+
		"\u00c3\n\23\3\23\3\23\3\23\3\23\5\23\u00c9\n\23\3\24\3\24\3\24\3\24\3"+
		"\24\3\25\3\25\5\25\u00d2\n\25\3\25\3\25\5\25\u00d6\n\25\3\25\5\25\u00d9"+
		"\n\25\3\25\5\25\u00dc\n\25\3\25\5\25\u00df\n\25\3\26\3\26\3\26\3\27\3"+
		"\27\3\27\3\27\3\30\3\30\3\30\3\30\7\30\u00ec\n\30\f\30\16\30\u00ef\13"+
		"\30\3\30\3\30\3\31\3\31\3\32\3\32\3\32\5\32\u00f8\n\32\3\32\3\32\3\32"+
		"\3\33\3\33\3\34\3\34\3\35\3\35\3\35\5\35\u0104\n\35\3\36\3\36\3\36\3\36"+
		"\3\36\3\37\3\37\3\37\3\37\6\37\u010f\n\37\r\37\16\37\u0110\3\37\2\2 \2"+
		"\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \"$&(*,.\60\62\64\668:<\2\4\4\2"+
		"\26\26\33\33\4\2\30\30\33\33\2\u0118\2?\3\2\2\2\4N\3\2\2\2\6R\3\2\2\2"+
		"\bU\3\2\2\2\nW\3\2\2\2\fp\3\2\2\2\16r\3\2\2\2\20t\3\2\2\2\22\u0086\3\2"+
		"\2\2\24\u0097\3\2\2\2\26\u0099\3\2\2\2\30\u00aa\3\2\2\2\32\u00ac\3\2\2"+
		"\2\34\u00ae\3\2\2\2\36\u00b9\3\2\2\2 \u00bb\3\2\2\2\"\u00bd\3\2\2\2$\u00c8"+
		"\3\2\2\2&\u00ca\3\2\2\2(\u00de\3\2\2\2*\u00e0\3\2\2\2,\u00e3\3\2\2\2."+
		"\u00e7\3\2\2\2\60\u00f2\3\2\2\2\62\u00f4\3\2\2\2\64\u00fc\3\2\2\2\66\u00fe"+
		"\3\2\2\28\u0100\3\2\2\2:\u0105\3\2\2\2<\u010a\3\2\2\2>@\5\4\3\2?>\3\2"+
		"\2\2@A\3\2\2\2A?\3\2\2\2AB\3\2\2\2B\3\3\2\2\2CO\5\6\4\2DO\5\20\t\2EO\5"+
		"\n\6\2FO\5&\24\2GO\5\36\20\2HO\5$\23\2IO\5\62\32\2JO\5\22\n\2KO\58\35"+
		"\2LO\5<\37\2MO\5:\36\2NC\3\2\2\2ND\3\2\2\2NE\3\2\2\2NF\3\2\2\2NG\3\2\2"+
		"\2NH\3\2\2\2NI\3\2\2\2NJ\3\2\2\2NK\3\2\2\2NL\3\2\2\2NM\3\2\2\2NO\3\2\2"+
		"\2OP\3\2\2\2PQ\7\3\2\2Q\5\3\2\2\2RS\7\r\2\2ST\5\b\5\2T\7\3\2\2\2UV\7\33"+
		"\2\2V\t\3\2\2\2W[\7\22\2\2XY\5(\25\2YZ\7\4\2\2Z\\\3\2\2\2[X\3\2\2\2[\\"+
		"\3\2\2\2\\]\3\2\2\2]n\7\33\2\2^_\7\5\2\2_`\5\f\7\2`a\7\4\2\2ai\5\16\b"+
		"\2bc\7\6\2\2cd\5\f\7\2de\7\4\2\2ef\5\16\b\2fh\3\2\2\2gb\3\2\2\2hk\3\2"+
		"\2\2ig\3\2\2\2ij\3\2\2\2jl\3\2\2\2ki\3\2\2\2lm\7\7\2\2mo\3\2\2\2n^\3\2"+
		"\2\2no\3\2\2\2o\13\3\2\2\2pq\t\2\2\2q\r\3\2\2\2rs\7\33\2\2s\17\3\2\2\2"+
		"tv\7\16\2\2uw\5\32\16\2vu\3\2\2\2vw\3\2\2\2wx\3\2\2\2x\u0084\5\30\r\2"+
		"yz\7\5\2\2z\177\5\34\17\2{|\7\6\2\2|~\5\34\17\2}{\3\2\2\2~\u0081\3\2\2"+
		"\2\177}\3\2\2\2\177\u0080\3\2\2\2\u0080\u0082\3\2\2\2\u0081\177\3\2\2"+
		"\2\u0082\u0083\7\7\2\2\u0083\u0085\3\2\2\2\u0084y\3\2\2\2\u0084\u0085"+
		"\3\2\2\2\u0085\21\3\2\2\2\u0086\u0087\7\23\2\2\u0087\u0093\7\33\2\2\u0088"+
		"\u0089\7\5\2\2\u0089\u008e\5\24\13\2\u008a\u008b\7\6\2\2\u008b\u008d\5"+
		"\24\13\2\u008c\u008a\3\2\2\2\u008d\u0090\3\2\2\2\u008e\u008c\3\2\2\2\u008e"+
		"\u008f\3\2\2\2\u008f\u0091\3\2\2\2\u0090\u008e\3\2\2\2\u0091\u0092\7\7"+
		"\2\2\u0092\u0094\3\2\2\2\u0093\u0088\3\2\2\2\u0093\u0094\3\2\2\2\u0094"+
		"\23\3\2\2\2\u0095\u0098\5\16\b\2\u0096\u0098\5\26\f\2\u0097\u0095\3\2"+
		"\2\2\u0097\u0096\3\2\2\2\u0098\25\3\2\2\2\u0099\u009a\7\33\2\2\u009a\u009b"+
		"\7\5\2\2\u009b\u009c\5\f\7\2\u009c\u009d\7\4\2\2\u009d\u00a5\5\16\b\2"+
		"\u009e\u009f\7\6\2\2\u009f\u00a0\5\f\7\2\u00a0\u00a1\7\4\2\2\u00a1\u00a2"+
		"\5\16\b\2\u00a2\u00a4\3\2\2\2\u00a3\u009e\3\2\2\2\u00a4\u00a7\3\2\2\2"+
		"\u00a5\u00a3\3\2\2\2\u00a5\u00a6\3\2\2\2\u00a6\u00a8\3\2\2\2\u00a7\u00a5"+
		"\3\2\2\2\u00a8\u00a9\7\7\2\2\u00a9\27\3\2\2\2\u00aa\u00ab\7\33\2\2\u00ab"+
		"\31\3\2\2\2\u00ac\u00ad\7\33\2\2\u00ad\33\3\2\2\2\u00ae\u00af\7\33\2\2"+
		"\u00af\35\3\2\2\2\u00b0\u00b1\7\17\2\2\u00b1\u00b3\5 \21\2\u00b2\u00b4"+
		"\5.\30\2\u00b3\u00b2\3\2\2\2\u00b3\u00b4\3\2\2\2\u00b4\u00ba\3\2\2\2\u00b5"+
		"\u00b6\7\17\2\2\u00b6\u00b7\5\"\22\2\u00b7\u00b8\5(\25\2\u00b8\u00ba\3"+
		"\2\2\2\u00b9\u00b0\3\2\2\2\u00b9\u00b5\3\2\2\2\u00ba\37\3\2\2\2\u00bb"+
		"\u00bc\7\30\2\2\u00bc!\3\2\2\2\u00bd\u00be\7\31\2\2\u00be#\3\2\2\2\u00bf"+
		"\u00c0\7\20\2\2\u00c0\u00c2\5 \21\2\u00c1\u00c3\5.\30\2\u00c2\u00c1\3"+
		"\2\2\2\u00c2\u00c3\3\2\2\2\u00c3\u00c9\3\2\2\2\u00c4\u00c5\7\20\2\2\u00c5"+
		"\u00c6\5\"\22\2\u00c6\u00c7\5(\25\2\u00c7\u00c9\3\2\2\2\u00c8\u00bf\3"+
		"\2\2\2\u00c8\u00c4\3\2\2\2\u00c9%\3\2\2\2\u00ca\u00cb\7\21\2\2\u00cb\u00cc"+
		"\5(\25\2\u00cc\u00cd\7\4\2\2\u00cd\u00ce\7\33\2\2\u00ce\'\3\2\2\2\u00cf"+
		"\u00df\5,\27\2\u00d0\u00d2\5*\26\2\u00d1\u00d0\3\2\2\2\u00d1\u00d2\3\2"+
		"\2\2\u00d2\u00d3\3\2\2\2\u00d3\u00d5\5\60\31\2\u00d4\u00d6\5,\27\2\u00d5"+
		"\u00d4\3\2\2\2\u00d5\u00d6\3\2\2\2\u00d6\u00df\3\2\2\2\u00d7\u00d9\5*"+
		"\26\2\u00d8\u00d7\3\2\2\2\u00d8\u00d9\3\2\2\2\u00d9\u00db\3\2\2\2\u00da"+
		"\u00dc\5\60\31\2\u00db\u00da\3\2\2\2\u00db\u00dc\3\2\2\2\u00dc\u00dd\3"+
		"\2\2\2\u00dd\u00df\5.\30\2\u00de\u00cf\3\2\2\2\u00de\u00d1\3\2\2\2\u00de"+
		"\u00d8\3\2\2\2\u00df)\3\2\2\2\u00e0\u00e1\7\33\2\2\u00e1\u00e2\7\b\2\2"+
		"\u00e2+\3\2\2\2\u00e3\u00e4\7\t\2\2\u00e4\u00e5\7\33\2\2\u00e5\u00e6\7"+
		"\n\2\2\u00e6-\3\2\2\2\u00e7\u00e8\7\13\2\2\u00e8\u00ed\t\3\2\2\u00e9\u00ea"+
		"\7\6\2\2\u00ea\u00ec\t\3\2\2\u00eb\u00e9\3\2\2\2\u00ec\u00ef\3\2\2\2\u00ed"+
		"\u00eb\3\2\2\2\u00ed\u00ee\3\2\2\2\u00ee\u00f0\3\2\2\2\u00ef\u00ed\3\2"+
		"\2\2\u00f0\u00f1\7\f\2\2\u00f1/\3\2\2\2\u00f2\u00f3\7\33\2\2\u00f3\61"+
		"\3\2\2\2\u00f4\u00f5\7\24\2\2\u00f5\u00f7\5 \21\2\u00f6\u00f8\5\64\33"+
		"\2\u00f7\u00f6\3\2\2\2\u00f7\u00f8\3\2\2\2\u00f8\u00f9\3\2\2\2\u00f9\u00fa"+
		"\7\4\2\2\u00fa\u00fb\5\66\34\2\u00fb\63\3\2\2\2\u00fc\u00fd\t\3\2\2\u00fd"+
		"\65\3\2\2\2\u00fe\u00ff\t\3\2\2\u00ff\67\3\2\2\2\u0100\u0103\7\25\2\2"+
		"\u0101\u0104\5(\25\2\u0102\u0104\5.\30\2\u0103\u0101\3\2\2\2\u0103\u0102"+
		"\3\2\2\2\u01049\3\2\2\2\u0105\u0106\7\27\2\2\u0106\u0107\7\32\2\2\u0107"+
		"\u0108\7\4\2\2\u0108\u0109\7\33\2\2\u0109;\3\2\2\2\u010a\u010b\7\26\2"+
		"\2\u010b\u010c\7\33\2\2\u010c\u010e\7\4\2\2\u010d\u010f\7\33\2\2\u010e"+
		"\u010d\3\2\2\2\u010f\u0110\3\2\2\2\u0110\u010e\3\2\2\2\u0110\u0111\3\2"+
		"\2\2\u0111=\3\2\2\2\33AN[inv\177\u0084\u008e\u0093\u0097\u00a5\u00b3\u00b9"+
		"\u00c2\u00c8\u00d1\u00d5\u00d8\u00db\u00de\u00ed\u00f7\u0103\u0110";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}