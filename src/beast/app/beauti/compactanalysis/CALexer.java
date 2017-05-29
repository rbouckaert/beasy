// Generated from CA.g4 by ANTLR 4.5
package beast.app.beauti.compactanalysis;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class CALexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.5", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, TEMPLATETOKEN=9, 
		IMPORTTOKEN=10, LINKTOKEN=11, UNLINKTOKEN=12, SETTOKEN=13, USETOKEN=14, 
		RENAMETOKEN=15, RMTOKEN=16, TAXONSETTOKEN=17, LINKTYPE=18, STRING=19, 
		WHITESPACE=20, COMMENT=21, LINE_COMMENT=22;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "T__7", "TEMPLATETOKEN", 
		"IMPORTTOKEN", "LINKTOKEN", "UNLINKTOKEN", "SETTOKEN", "USETOKEN", "RENAMETOKEN", 
		"RMTOKEN", "TAXONSETTOKEN", "LINKTYPE", "STRING", "WHITESPACE", "COMMENT", 
		"LINE_COMMENT"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "';'", "'='", "'('", "','", "')'", "'@'", "'['", "']'", "'template'", 
		"'import'", "'link'", "'unlink'", "'set'", "'use'", "'rename'", "'rm'", 
		"'taxonset'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, null, null, "TEMPLATETOKEN", 
		"IMPORTTOKEN", "LINKTOKEN", "UNLINKTOKEN", "SETTOKEN", "USETOKEN", "RENAMETOKEN", 
		"RMTOKEN", "TAXONSETTOKEN", "LINKTYPE", "STRING", "WHITESPACE", "COMMENT", 
		"LINE_COMMENT"
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


	public CALexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "CA.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2\30\u00c2\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\3\2\3\2\3\3\3"+
		"\3\3\4\3\4\3\5\3\5\3\6\3\6\3\7\3\7\3\b\3\b\3\t\3\t\3\n\3\n\3\n\3\n\3\n"+
		"\3\n\3\n\3\n\3\n\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\f\3\f\3\f\3\f\3"+
		"\f\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\16\3\16\3\16\3\16\3\17\3\17\3\17\3\17"+
		"\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\21\3\21\3\21\3\22\3\22\3\22\3\22"+
		"\3\22\3\22\3\22\3\22\3\22\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23"+
		"\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\5\23\u0089\n\23\3\24\6\24"+
		"\u008c\n\24\r\24\16\24\u008d\3\24\3\24\7\24\u0092\n\24\f\24\16\24\u0095"+
		"\13\24\3\24\3\24\3\24\7\24\u009a\n\24\f\24\16\24\u009d\13\24\3\24\5\24"+
		"\u00a0\n\24\3\25\6\25\u00a3\n\25\r\25\16\25\u00a4\3\25\3\25\3\26\3\26"+
		"\3\26\3\26\3\26\7\26\u00ae\n\26\f\26\16\26\u00b1\13\26\3\26\3\26\3\26"+
		"\3\26\3\26\3\27\3\27\3\27\3\27\7\27\u00bc\n\27\f\27\16\27\u00bf\13\27"+
		"\3\27\3\27\5\u0093\u009b\u00af\2\30\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n"+
		"\23\13\25\f\27\r\31\16\33\17\35\20\37\21!\22#\23%\24\'\25)\26+\27-\30"+
		"\3\2\5\t\2%(,-/<C\\aac|~~\5\2\13\f\16\17\"\"\4\2\f\f\17\17\u00cb\2\3\3"+
		"\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2"+
		"\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3"+
		"\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2"+
		"%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\3/\3\2\2\2\5\61"+
		"\3\2\2\2\7\63\3\2\2\2\t\65\3\2\2\2\13\67\3\2\2\2\r9\3\2\2\2\17;\3\2\2"+
		"\2\21=\3\2\2\2\23?\3\2\2\2\25H\3\2\2\2\27O\3\2\2\2\31T\3\2\2\2\33[\3\2"+
		"\2\2\35_\3\2\2\2\37c\3\2\2\2!j\3\2\2\2#m\3\2\2\2%\u0088\3\2\2\2\'\u009f"+
		"\3\2\2\2)\u00a2\3\2\2\2+\u00a8\3\2\2\2-\u00b7\3\2\2\2/\60\7=\2\2\60\4"+
		"\3\2\2\2\61\62\7?\2\2\62\6\3\2\2\2\63\64\7*\2\2\64\b\3\2\2\2\65\66\7."+
		"\2\2\66\n\3\2\2\2\678\7+\2\28\f\3\2\2\29:\7B\2\2:\16\3\2\2\2;<\7]\2\2"+
		"<\20\3\2\2\2=>\7_\2\2>\22\3\2\2\2?@\7v\2\2@A\7g\2\2AB\7o\2\2BC\7r\2\2"+
		"CD\7n\2\2DE\7c\2\2EF\7v\2\2FG\7g\2\2G\24\3\2\2\2HI\7k\2\2IJ\7o\2\2JK\7"+
		"r\2\2KL\7q\2\2LM\7t\2\2MN\7v\2\2N\26\3\2\2\2OP\7n\2\2PQ\7k\2\2QR\7p\2"+
		"\2RS\7m\2\2S\30\3\2\2\2TU\7w\2\2UV\7p\2\2VW\7n\2\2WX\7k\2\2XY\7p\2\2Y"+
		"Z\7m\2\2Z\32\3\2\2\2[\\\7u\2\2\\]\7g\2\2]^\7v\2\2^\34\3\2\2\2_`\7w\2\2"+
		"`a\7u\2\2ab\7g\2\2b\36\3\2\2\2cd\7t\2\2de\7g\2\2ef\7p\2\2fg\7c\2\2gh\7"+
		"o\2\2hi\7g\2\2i \3\2\2\2jk\7t\2\2kl\7o\2\2l\"\3\2\2\2mn\7v\2\2no\7c\2"+
		"\2op\7z\2\2pq\7q\2\2qr\7p\2\2rs\7u\2\2st\7g\2\2tu\7v\2\2u$\3\2\2\2vw\7"+
		"e\2\2wx\7n\2\2xy\7q\2\2yz\7e\2\2z\u0089\7m\2\2{|\7v\2\2|}\7t\2\2}~\7g"+
		"\2\2~\u0089\7g\2\2\177\u0080\7u\2\2\u0080\u0081\7k\2\2\u0081\u0082\7v"+
		"\2\2\u0082\u0083\7g\2\2\u0083\u0084\7o\2\2\u0084\u0085\7q\2\2\u0085\u0086"+
		"\7f\2\2\u0086\u0087\7g\2\2\u0087\u0089\7n\2\2\u0088v\3\2\2\2\u0088{\3"+
		"\2\2\2\u0088\177\3\2\2\2\u0089&\3\2\2\2\u008a\u008c\t\2\2\2\u008b\u008a"+
		"\3\2\2\2\u008c\u008d\3\2\2\2\u008d\u008b\3\2\2\2\u008d\u008e\3\2\2\2\u008e"+
		"\u00a0\3\2\2\2\u008f\u0093\7$\2\2\u0090\u0092\13\2\2\2\u0091\u0090\3\2"+
		"\2\2\u0092\u0095\3\2\2\2\u0093\u0094\3\2\2\2\u0093\u0091\3\2\2\2\u0094"+
		"\u0096\3\2\2\2\u0095\u0093\3\2\2\2\u0096\u00a0\7$\2\2\u0097\u009b\7)\2"+
		"\2\u0098\u009a\13\2\2\2\u0099\u0098\3\2\2\2\u009a\u009d\3\2\2\2\u009b"+
		"\u009c\3\2\2\2\u009b\u0099\3\2\2\2\u009c\u009e\3\2\2\2\u009d\u009b\3\2"+
		"\2\2\u009e\u00a0\7)\2\2\u009f\u008b\3\2\2\2\u009f\u008f\3\2\2\2\u009f"+
		"\u0097\3\2\2\2\u00a0(\3\2\2\2\u00a1\u00a3\t\3\2\2\u00a2\u00a1\3\2\2\2"+
		"\u00a3\u00a4\3\2\2\2\u00a4\u00a2\3\2\2\2\u00a4\u00a5\3\2\2\2\u00a5\u00a6"+
		"\3\2\2\2\u00a6\u00a7\b\25\2\2\u00a7*\3\2\2\2\u00a8\u00a9\7\61\2\2\u00a9"+
		"\u00aa\7,\2\2\u00aa\u00ab\3\2\2\2\u00ab\u00af\3\2\2\2\u00ac\u00ae\13\2"+
		"\2\2\u00ad\u00ac\3\2\2\2\u00ae\u00b1\3\2\2\2\u00af\u00b0\3\2\2\2\u00af"+
		"\u00ad\3\2\2\2\u00b0\u00b2\3\2\2\2\u00b1\u00af\3\2\2\2\u00b2\u00b3\7,"+
		"\2\2\u00b3\u00b4\7\61\2\2\u00b4\u00b5\3\2\2\2\u00b5\u00b6\b\26\3\2\u00b6"+
		",\3\2\2\2\u00b7\u00b8\7\61\2\2\u00b8\u00b9\7\61\2\2\u00b9\u00bd\3\2\2"+
		"\2\u00ba\u00bc\n\4\2\2\u00bb\u00ba\3\2\2\2\u00bc\u00bf\3\2\2\2\u00bd\u00bb"+
		"\3\2\2\2\u00bd\u00be\3\2\2\2\u00be\u00c0\3\2\2\2\u00bf\u00bd\3\2\2\2\u00c0"+
		"\u00c1\b\27\3\2\u00c1.\3\2\2\2\13\2\u0088\u008d\u0093\u009b\u009f\u00a4"+
		"\u00af\u00bd\4\b\2\2\2\3\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}