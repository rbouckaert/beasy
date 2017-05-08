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
		SEMI=1, COMMA=2, OPENP=3, CLOSEP=4, EQ=5, TEMPLATETOKEN=6, IMPORTTOKEN=7, 
		PARTITIONTOKEN=8, LINKTOKEN=9, UNLINKTOKEN=10, SETTOKEN=11, SUBTOKEN=12, 
		LINKTYPE=13, STRING=14, WHITESPACE=15;
	public static final int
		RULE_casentence = 0, RULE_template = 1, RULE_templatename = 2, RULE_subtemplate = 3, 
		RULE_idpattern = 4, RULE_key = 5, RULE_value = 6, RULE_import_ = 7, RULE_filename = 8, 
		RULE_alignmentprovider = 9, RULE_arg = 10, RULE_partitionpattern = 11, 
		RULE_link = 12, RULE_unlink = 13, RULE_set = 14;
	public static final String[] ruleNames = {
		"casentence", "template", "templatename", "subtemplate", "idpattern", 
		"key", "value", "import_", "filename", "alignmentprovider", "arg", "partitionpattern", 
		"link", "unlink", "set"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "';'", "','", "'('", "')'", "'='", "'template'", "'import'", "'partition'", 
		"'link'", "'unlink'", "'set'", "'sub'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, "SEMI", "COMMA", "OPENP", "CLOSEP", "EQ", "TEMPLATETOKEN", "IMPORTTOKEN", 
		"PARTITIONTOKEN", "LINKTOKEN", "UNLINKTOKEN", "SETTOKEN", "SUBTOKEN", 
		"LINKTYPE", "STRING", "WHITESPACE"
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
		public List<TerminalNode> SEMI() { return getTokens(CAParser.SEMI); }
		public TerminalNode SEMI(int i) {
			return getToken(CAParser.SEMI, i);
		}
		public List<TemplateContext> template() {
			return getRuleContexts(TemplateContext.class);
		}
		public TemplateContext template(int i) {
			return getRuleContext(TemplateContext.class,i);
		}
		public List<SubtemplateContext> subtemplate() {
			return getRuleContexts(SubtemplateContext.class);
		}
		public SubtemplateContext subtemplate(int i) {
			return getRuleContext(SubtemplateContext.class,i);
		}
		public List<Import_Context> import_() {
			return getRuleContexts(Import_Context.class);
		}
		public Import_Context import_(int i) {
			return getRuleContext(Import_Context.class,i);
		}
		public List<LinkContext> link() {
			return getRuleContexts(LinkContext.class);
		}
		public LinkContext link(int i) {
			return getRuleContext(LinkContext.class,i);
		}
		public List<UnlinkContext> unlink() {
			return getRuleContexts(UnlinkContext.class);
		}
		public UnlinkContext unlink(int i) {
			return getRuleContext(UnlinkContext.class,i);
		}
		public List<SetContext> set() {
			return getRuleContexts(SetContext.class);
		}
		public SetContext set(int i) {
			return getRuleContext(SetContext.class,i);
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
			setState(42);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << TEMPLATETOKEN) | (1L << IMPORTTOKEN) | (1L << LINKTOKEN) | (1L << UNLINKTOKEN) | (1L << SETTOKEN) | (1L << SUBTOKEN))) != 0)) {
				{
				{
				setState(36);
				switch (_input.LA(1)) {
				case TEMPLATETOKEN:
					{
					setState(30);
					template();
					}
					break;
				case SUBTOKEN:
					{
					setState(31);
					subtemplate();
					}
					break;
				case IMPORTTOKEN:
					{
					setState(32);
					import_();
					}
					break;
				case LINKTOKEN:
					{
					setState(33);
					link();
					}
					break;
				case UNLINKTOKEN:
					{
					setState(34);
					unlink();
					}
					break;
				case SETTOKEN:
					{
					setState(35);
					set();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(38);
				match(SEMI);
				}
				}
				setState(44);
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
		enterRule(_localctx, 2, RULE_template);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(45);
			match(TEMPLATETOKEN);
			setState(46);
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
		enterRule(_localctx, 4, RULE_templatename);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(48);
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

	public static class SubtemplateContext extends ParserRuleContext {
		public TerminalNode SUBTOKEN() { return getToken(CAParser.SUBTOKEN, 0); }
		public TerminalNode STRING() { return getToken(CAParser.STRING, 0); }
		public IdpatternContext idpattern() {
			return getRuleContext(IdpatternContext.class,0);
		}
		public List<TerminalNode> EQ() { return getTokens(CAParser.EQ); }
		public TerminalNode EQ(int i) {
			return getToken(CAParser.EQ, i);
		}
		public TerminalNode OPENP() { return getToken(CAParser.OPENP, 0); }
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
		public TerminalNode CLOSEP() { return getToken(CAParser.CLOSEP, 0); }
		public List<TerminalNode> COMMA() { return getTokens(CAParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(CAParser.COMMA, i);
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
		enterRule(_localctx, 6, RULE_subtemplate);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(50);
			match(SUBTOKEN);
			setState(54);
			switch ( getInterpreter().adaptivePredict(_input,2,_ctx) ) {
			case 1:
				{
				setState(51);
				idpattern();
				setState(52);
				match(EQ);
				}
				break;
			}
			setState(56);
			match(STRING);
			setState(73);
			_la = _input.LA(1);
			if (_la==OPENP) {
				{
				setState(57);
				match(OPENP);
				setState(58);
				key();
				setState(59);
				match(EQ);
				setState(60);
				value();
				setState(68);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(61);
					match(COMMA);
					setState(62);
					key();
					setState(63);
					match(EQ);
					setState(64);
					value();
					}
					}
					setState(70);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(71);
				match(CLOSEP);
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

	public static class IdpatternContext extends ParserRuleContext {
		public TerminalNode STRING() { return getToken(CAParser.STRING, 0); }
		public IdpatternContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_idpattern; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CAListener ) ((CAListener)listener).enterIdpattern(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CAListener ) ((CAListener)listener).exitIdpattern(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CAVisitor ) return ((CAVisitor<? extends T>)visitor).visitIdpattern(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IdpatternContext idpattern() throws RecognitionException {
		IdpatternContext _localctx = new IdpatternContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_idpattern);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(75);
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

	public static class KeyContext extends ParserRuleContext {
		public TerminalNode STRING() { return getToken(CAParser.STRING, 0); }
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
			setState(79);
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
		public TerminalNode OPENP() { return getToken(CAParser.OPENP, 0); }
		public List<ArgContext> arg() {
			return getRuleContexts(ArgContext.class);
		}
		public ArgContext arg(int i) {
			return getRuleContext(ArgContext.class,i);
		}
		public TerminalNode CLOSEP() { return getToken(CAParser.CLOSEP, 0); }
		public List<TerminalNode> COMMA() { return getTokens(CAParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(CAParser.COMMA, i);
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
			setState(81);
			match(IMPORTTOKEN);
			setState(83);
			switch ( getInterpreter().adaptivePredict(_input,5,_ctx) ) {
			case 1:
				{
				setState(82);
				alignmentprovider();
				}
				break;
			}
			setState(85);
			filename();
			setState(97);
			_la = _input.LA(1);
			if (_la==OPENP) {
				{
				setState(86);
				match(OPENP);
				setState(87);
				arg();
				setState(92);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(88);
					match(COMMA);
					setState(89);
					arg();
					}
					}
					setState(94);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(95);
				match(CLOSEP);
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
		enterRule(_localctx, 16, RULE_filename);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(99);
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
		enterRule(_localctx, 18, RULE_alignmentprovider);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(101);
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
		enterRule(_localctx, 20, RULE_arg);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(103);
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
		enterRule(_localctx, 22, RULE_partitionpattern);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(105);
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
		enterRule(_localctx, 24, RULE_link);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(107);
			match(LINKTOKEN);
			setState(108);
			match(LINKTYPE);
			setState(110);
			_la = _input.LA(1);
			if (_la==STRING) {
				{
				setState(109);
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
		enterRule(_localctx, 26, RULE_unlink);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(112);
			match(UNLINKTOKEN);
			setState(113);
			match(LINKTYPE);
			setState(115);
			_la = _input.LA(1);
			if (_la==STRING) {
				{
				setState(114);
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
		public List<TerminalNode> STRING() { return getTokens(CAParser.STRING); }
		public TerminalNode STRING(int i) {
			return getToken(CAParser.STRING, i);
		}
		public TerminalNode EQ() { return getToken(CAParser.EQ, 0); }
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
		enterRule(_localctx, 28, RULE_set);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(117);
			match(SETTOKEN);
			setState(118);
			match(STRING);
			setState(119);
			match(EQ);
			setState(120);
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

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3\21}\4\2\t\2\4\3\t"+
		"\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t\13\4"+
		"\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\3\2\3\2\3\2\3\2\3\2\3\2\5"+
		"\2\'\n\2\3\2\3\2\7\2+\n\2\f\2\16\2.\13\2\3\3\3\3\3\3\3\4\3\4\3\5\3\5\3"+
		"\5\3\5\5\59\n\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\7\5E\n\5\f\5\16"+
		"\5H\13\5\3\5\3\5\5\5L\n\5\3\6\3\6\3\7\3\7\3\b\3\b\3\t\3\t\5\tV\n\t\3\t"+
		"\3\t\3\t\3\t\3\t\7\t]\n\t\f\t\16\t`\13\t\3\t\3\t\5\td\n\t\3\n\3\n\3\13"+
		"\3\13\3\f\3\f\3\r\3\r\3\16\3\16\3\16\5\16q\n\16\3\17\3\17\3\17\5\17v\n"+
		"\17\3\20\3\20\3\20\3\20\3\20\3\20\2\2\21\2\4\6\b\n\f\16\20\22\24\26\30"+
		"\32\34\36\2\2{\2,\3\2\2\2\4/\3\2\2\2\6\62\3\2\2\2\b\64\3\2\2\2\nM\3\2"+
		"\2\2\fO\3\2\2\2\16Q\3\2\2\2\20S\3\2\2\2\22e\3\2\2\2\24g\3\2\2\2\26i\3"+
		"\2\2\2\30k\3\2\2\2\32m\3\2\2\2\34r\3\2\2\2\36w\3\2\2\2 \'\5\4\3\2!\'\5"+
		"\b\5\2\"\'\5\20\t\2#\'\5\32\16\2$\'\5\34\17\2%\'\5\36\20\2& \3\2\2\2&"+
		"!\3\2\2\2&\"\3\2\2\2&#\3\2\2\2&$\3\2\2\2&%\3\2\2\2\'(\3\2\2\2()\7\3\2"+
		"\2)+\3\2\2\2*&\3\2\2\2+.\3\2\2\2,*\3\2\2\2,-\3\2\2\2-\3\3\2\2\2.,\3\2"+
		"\2\2/\60\7\b\2\2\60\61\5\6\4\2\61\5\3\2\2\2\62\63\7\20\2\2\63\7\3\2\2"+
		"\2\648\7\16\2\2\65\66\5\n\6\2\66\67\7\7\2\2\679\3\2\2\28\65\3\2\2\289"+
		"\3\2\2\29:\3\2\2\2:K\7\20\2\2;<\7\5\2\2<=\5\f\7\2=>\7\7\2\2>F\5\16\b\2"+
		"?@\7\4\2\2@A\5\f\7\2AB\7\7\2\2BC\5\16\b\2CE\3\2\2\2D?\3\2\2\2EH\3\2\2"+
		"\2FD\3\2\2\2FG\3\2\2\2GI\3\2\2\2HF\3\2\2\2IJ\7\6\2\2JL\3\2\2\2K;\3\2\2"+
		"\2KL\3\2\2\2L\t\3\2\2\2MN\7\20\2\2N\13\3\2\2\2OP\7\20\2\2P\r\3\2\2\2Q"+
		"R\7\20\2\2R\17\3\2\2\2SU\7\t\2\2TV\5\24\13\2UT\3\2\2\2UV\3\2\2\2VW\3\2"+
		"\2\2Wc\5\22\n\2XY\7\5\2\2Y^\5\26\f\2Z[\7\4\2\2[]\5\26\f\2\\Z\3\2\2\2]"+
		"`\3\2\2\2^\\\3\2\2\2^_\3\2\2\2_a\3\2\2\2`^\3\2\2\2ab\7\6\2\2bd\3\2\2\2"+
		"cX\3\2\2\2cd\3\2\2\2d\21\3\2\2\2ef\7\20\2\2f\23\3\2\2\2gh\7\20\2\2h\25"+
		"\3\2\2\2ij\7\20\2\2j\27\3\2\2\2kl\7\20\2\2l\31\3\2\2\2mn\7\13\2\2np\7"+
		"\17\2\2oq\5\30\r\2po\3\2\2\2pq\3\2\2\2q\33\3\2\2\2rs\7\f\2\2su\7\17\2"+
		"\2tv\5\30\r\2ut\3\2\2\2uv\3\2\2\2v\35\3\2\2\2wx\7\r\2\2xy\7\20\2\2yz\7"+
		"\7\2\2z{\7\20\2\2{\37\3\2\2\2\f&,8FKU^cpu";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}