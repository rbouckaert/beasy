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
		IMPORTTOKEN=10, PARTITIONTOKEN=11, LINKTOKEN=12, UNLINKTOKEN=13, SETTOKEN=14, 
		USETOKEN=15, LINKTYPE=16, STRING=17, WHITESPACE=18, COMMENT=19, LINE_COMMENT=20;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "T__7", "TEMPLATETOKEN", 
		"IMPORTTOKEN", "PARTITIONTOKEN", "LINKTOKEN", "UNLINKTOKEN", "SETTOKEN", 
		"USETOKEN", "LINKTYPE", "STRING", "WHITESPACE", "COMMENT", "LINE_COMMENT"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "';'", "'='", "'('", "','", "')'", "'@'", "'['", "']'", "'template'", 
		"'import'", "'partition'", "'link'", "'unlink'", "'set'", "'use'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, null, null, "TEMPLATETOKEN", 
		"IMPORTTOKEN", "PARTITIONTOKEN", "LINKTOKEN", "UNLINKTOKEN", "SETTOKEN", 
		"USETOKEN", "LINKTYPE", "STRING", "WHITESPACE", "COMMENT", "LINE_COMMENT"
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
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2\26\u00b5\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\3\2\3\2\3\3\3\3\3\4\3\4\3\5\3\5\3"+
		"\6\3\6\3\7\3\7\3\b\3\b\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\13"+
		"\3\13\3\13\3\13\3\13\3\13\3\13\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f"+
		"\3\r\3\r\3\r\3\r\3\r\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\17\3\17\3\17"+
		"\3\17\3\20\3\20\3\20\3\20\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21"+
		"\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\5\21|\n\21\3\22\6\22\177"+
		"\n\22\r\22\16\22\u0080\3\22\3\22\7\22\u0085\n\22\f\22\16\22\u0088\13\22"+
		"\3\22\3\22\3\22\7\22\u008d\n\22\f\22\16\22\u0090\13\22\3\22\5\22\u0093"+
		"\n\22\3\23\6\23\u0096\n\23\r\23\16\23\u0097\3\23\3\23\3\24\3\24\3\24\3"+
		"\24\3\24\7\24\u00a1\n\24\f\24\16\24\u00a4\13\24\3\24\3\24\3\24\3\24\3"+
		"\24\3\25\3\25\3\25\3\25\7\25\u00af\n\25\f\25\16\25\u00b2\13\25\3\25\3"+
		"\25\5\u0086\u008e\u00a2\2\26\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13"+
		"\25\f\27\r\31\16\33\17\35\20\37\21!\22#\23%\24\'\25)\26\3\2\5\t\2%(,-"+
		"/<C\\aac|~~\5\2\13\f\16\17\"\"\4\2\f\f\17\17\u00be\2\3\3\2\2\2\2\5\3\2"+
		"\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21"+
		"\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2"+
		"\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3"+
		"\2\2\2\2)\3\2\2\2\3+\3\2\2\2\5-\3\2\2\2\7/\3\2\2\2\t\61\3\2\2\2\13\63"+
		"\3\2\2\2\r\65\3\2\2\2\17\67\3\2\2\2\219\3\2\2\2\23;\3\2\2\2\25D\3\2\2"+
		"\2\27K\3\2\2\2\31U\3\2\2\2\33Z\3\2\2\2\35a\3\2\2\2\37e\3\2\2\2!{\3\2\2"+
		"\2#\u0092\3\2\2\2%\u0095\3\2\2\2\'\u009b\3\2\2\2)\u00aa\3\2\2\2+,\7=\2"+
		"\2,\4\3\2\2\2-.\7?\2\2.\6\3\2\2\2/\60\7*\2\2\60\b\3\2\2\2\61\62\7.\2\2"+
		"\62\n\3\2\2\2\63\64\7+\2\2\64\f\3\2\2\2\65\66\7B\2\2\66\16\3\2\2\2\67"+
		"8\7]\2\28\20\3\2\2\29:\7_\2\2:\22\3\2\2\2;<\7v\2\2<=\7g\2\2=>\7o\2\2>"+
		"?\7r\2\2?@\7n\2\2@A\7c\2\2AB\7v\2\2BC\7g\2\2C\24\3\2\2\2DE\7k\2\2EF\7"+
		"o\2\2FG\7r\2\2GH\7q\2\2HI\7t\2\2IJ\7v\2\2J\26\3\2\2\2KL\7r\2\2LM\7c\2"+
		"\2MN\7t\2\2NO\7v\2\2OP\7k\2\2PQ\7v\2\2QR\7k\2\2RS\7q\2\2ST\7p\2\2T\30"+
		"\3\2\2\2UV\7n\2\2VW\7k\2\2WX\7p\2\2XY\7m\2\2Y\32\3\2\2\2Z[\7w\2\2[\\\7"+
		"p\2\2\\]\7n\2\2]^\7k\2\2^_\7p\2\2_`\7m\2\2`\34\3\2\2\2ab\7u\2\2bc\7g\2"+
		"\2cd\7v\2\2d\36\3\2\2\2ef\7w\2\2fg\7u\2\2gh\7g\2\2h \3\2\2\2ij\7e\2\2"+
		"jk\7n\2\2kl\7q\2\2lm\7e\2\2m|\7m\2\2no\7v\2\2op\7t\2\2pq\7g\2\2q|\7g\2"+
		"\2rs\7u\2\2st\7k\2\2tu\7v\2\2uv\7g\2\2vw\7o\2\2wx\7q\2\2xy\7f\2\2yz\7"+
		"g\2\2z|\7n\2\2{i\3\2\2\2{n\3\2\2\2{r\3\2\2\2|\"\3\2\2\2}\177\t\2\2\2~"+
		"}\3\2\2\2\177\u0080\3\2\2\2\u0080~\3\2\2\2\u0080\u0081\3\2\2\2\u0081\u0093"+
		"\3\2\2\2\u0082\u0086\7$\2\2\u0083\u0085\13\2\2\2\u0084\u0083\3\2\2\2\u0085"+
		"\u0088\3\2\2\2\u0086\u0087\3\2\2\2\u0086\u0084\3\2\2\2\u0087\u0089\3\2"+
		"\2\2\u0088\u0086\3\2\2\2\u0089\u0093\7$\2\2\u008a\u008e\7)\2\2\u008b\u008d"+
		"\13\2\2\2\u008c\u008b\3\2\2\2\u008d\u0090\3\2\2\2\u008e\u008f\3\2\2\2"+
		"\u008e\u008c\3\2\2\2\u008f\u0091\3\2\2\2\u0090\u008e\3\2\2\2\u0091\u0093"+
		"\7)\2\2\u0092~\3\2\2\2\u0092\u0082\3\2\2\2\u0092\u008a\3\2\2\2\u0093$"+
		"\3\2\2\2\u0094\u0096\t\3\2\2\u0095\u0094\3\2\2\2\u0096\u0097\3\2\2\2\u0097"+
		"\u0095\3\2\2\2\u0097\u0098\3\2\2\2\u0098\u0099\3\2\2\2\u0099\u009a\b\23"+
		"\2\2\u009a&\3\2\2\2\u009b\u009c\7\61\2\2\u009c\u009d\7,\2\2\u009d\u009e"+
		"\3\2\2\2\u009e\u00a2\3\2\2\2\u009f\u00a1\13\2\2\2\u00a0\u009f\3\2\2\2"+
		"\u00a1\u00a4\3\2\2\2\u00a2\u00a3\3\2\2\2\u00a2\u00a0\3\2\2\2\u00a3\u00a5"+
		"\3\2\2\2\u00a4\u00a2\3\2\2\2\u00a5\u00a6\7,\2\2\u00a6\u00a7\7\61\2\2\u00a7"+
		"\u00a8\3\2\2\2\u00a8\u00a9\b\24\3\2\u00a9(\3\2\2\2\u00aa\u00ab\7\61\2"+
		"\2\u00ab\u00ac\7\61\2\2\u00ac\u00b0\3\2\2\2\u00ad\u00af\n\4\2\2\u00ae"+
		"\u00ad\3\2\2\2\u00af\u00b2\3\2\2\2\u00b0\u00ae\3\2\2\2\u00b0\u00b1\3\2"+
		"\2\2\u00b1\u00b3\3\2\2\2\u00b2\u00b0\3\2\2\2\u00b3\u00b4\b\25\3\2\u00b4"+
		"*\3\2\2\2\13\2{\u0080\u0086\u008e\u0092\u0097\u00a2\u00b0\4\b\2\2\2\3"+
		"\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}