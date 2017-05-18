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
		C_COMMENT=1, SINGLELINE_COMMENT=2, SEMI=3, COMMA=4, OPENP=5, CLOSEP=6, 
		EQ=7, TEMPLATETOKEN=8, IMPORTTOKEN=9, PARTITIONTOKEN=10, LINKTOKEN=11, 
		UNLINKTOKEN=12, SETTOKEN=13, USETOKEN=14, LINKTYPE=15, STRING=16, WHITESPACE=17;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"C_COMMENT", "SINGLELINE_COMMENT", "SEMI", "COMMA", "OPENP", "CLOSEP", 
		"EQ", "TEMPLATETOKEN", "IMPORTTOKEN", "PARTITIONTOKEN", "LINKTOKEN", "UNLINKTOKEN", 
		"SETTOKEN", "USETOKEN", "LINKTYPE", "STRING", "WHITESPACE"
	};

	private static final String[] _LITERAL_NAMES = {
		null, null, null, "';'", "','", "'('", "')'", "'='", "'template'", "'import'", 
		"'partition'", "'link'", "'unlink'", "'set'", "'use'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, "C_COMMENT", "SINGLELINE_COMMENT", "SEMI", "COMMA", "OPENP", "CLOSEP", 
		"EQ", "TEMPLATETOKEN", "IMPORTTOKEN", "PARTITIONTOKEN", "LINKTOKEN", "UNLINKTOKEN", 
		"SETTOKEN", "USETOKEN", "LINKTYPE", "STRING", "WHITESPACE"
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
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2\23\u00a0\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\3\2\3\2\3\2\3\2\3\2\7\2+\n\2\f\2\16\2.\13\2\3\2\3\2\3\2\3\3\3\3"+
		"\3\3\3\3\7\3\67\n\3\f\3\16\3:\13\3\3\4\3\4\3\5\3\5\3\6\3\6\3\7\3\7\3\b"+
		"\3\b\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3"+
		"\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\f\3\f\3\f\3\f\3\f\3"+
		"\r\3\r\3\r\3\r\3\r\3\r\3\r\3\16\3\16\3\16\3\16\3\17\3\17\3\17\3\17\3\20"+
		"\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\5\20\u0081"+
		"\n\20\3\21\6\21\u0084\n\21\r\21\16\21\u0085\3\21\3\21\7\21\u008a\n\21"+
		"\f\21\16\21\u008d\13\21\3\21\3\21\3\21\7\21\u0092\n\21\f\21\16\21\u0095"+
		"\13\21\3\21\5\21\u0098\n\21\3\22\6\22\u009b\n\22\r\22\16\22\u009c\3\22"+
		"\3\22\5,\u008b\u0093\2\23\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25"+
		"\f\27\r\31\16\33\17\35\20\37\21!\22#\23\3\2\5\4\2\f\f\17\17\n\2%%\'(,"+
		"-/;C\\aac|~~\5\2\13\f\16\17\"\"\u00a9\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2"+
		"\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2"+
		"\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3"+
		"\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\3%\3\2\2\2\5\62\3\2\2\2\7;\3"+
		"\2\2\2\t=\3\2\2\2\13?\3\2\2\2\rA\3\2\2\2\17C\3\2\2\2\21E\3\2\2\2\23N\3"+
		"\2\2\2\25U\3\2\2\2\27_\3\2\2\2\31d\3\2\2\2\33k\3\2\2\2\35o\3\2\2\2\37"+
		"\u0080\3\2\2\2!\u0097\3\2\2\2#\u009a\3\2\2\2%&\7\61\2\2&\'\7,\2\2\'(\3"+
		"\2\2\2(,\3\2\2\2)+\13\2\2\2*)\3\2\2\2+.\3\2\2\2,-\3\2\2\2,*\3\2\2\2-/"+
		"\3\2\2\2.,\3\2\2\2/\60\7,\2\2\60\61\7\61\2\2\61\4\3\2\2\2\62\63\7\61\2"+
		"\2\63\64\7\61\2\2\648\3\2\2\2\65\67\n\2\2\2\66\65\3\2\2\2\67:\3\2\2\2"+
		"8\66\3\2\2\289\3\2\2\29\6\3\2\2\2:8\3\2\2\2;<\7=\2\2<\b\3\2\2\2=>\7.\2"+
		"\2>\n\3\2\2\2?@\7*\2\2@\f\3\2\2\2AB\7+\2\2B\16\3\2\2\2CD\7?\2\2D\20\3"+
		"\2\2\2EF\7v\2\2FG\7g\2\2GH\7o\2\2HI\7r\2\2IJ\7n\2\2JK\7c\2\2KL\7v\2\2"+
		"LM\7g\2\2M\22\3\2\2\2NO\7k\2\2OP\7o\2\2PQ\7r\2\2QR\7q\2\2RS\7t\2\2ST\7"+
		"v\2\2T\24\3\2\2\2UV\7r\2\2VW\7c\2\2WX\7t\2\2XY\7v\2\2YZ\7k\2\2Z[\7v\2"+
		"\2[\\\7k\2\2\\]\7q\2\2]^\7p\2\2^\26\3\2\2\2_`\7n\2\2`a\7k\2\2ab\7p\2\2"+
		"bc\7m\2\2c\30\3\2\2\2de\7w\2\2ef\7p\2\2fg\7n\2\2gh\7k\2\2hi\7p\2\2ij\7"+
		"m\2\2j\32\3\2\2\2kl\7u\2\2lm\7g\2\2mn\7v\2\2n\34\3\2\2\2op\7w\2\2pq\7"+
		"u\2\2qr\7g\2\2r\36\3\2\2\2st\7e\2\2tu\7n\2\2uv\7q\2\2vw\7e\2\2w\u0081"+
		"\7m\2\2xy\7v\2\2yz\7t\2\2z{\7g\2\2{\u0081\7g\2\2|}\7u\2\2}~\7k\2\2~\177"+
		"\7v\2\2\177\u0081\7g\2\2\u0080s\3\2\2\2\u0080x\3\2\2\2\u0080|\3\2\2\2"+
		"\u0081 \3\2\2\2\u0082\u0084\t\3\2\2\u0083\u0082\3\2\2\2\u0084\u0085\3"+
		"\2\2\2\u0085\u0083\3\2\2\2\u0085\u0086\3\2\2\2\u0086\u0098\3\2\2\2\u0087"+
		"\u008b\7$\2\2\u0088\u008a\13\2\2\2\u0089\u0088\3\2\2\2\u008a\u008d\3\2"+
		"\2\2\u008b\u008c\3\2\2\2\u008b\u0089\3\2\2\2\u008c\u008e\3\2\2\2\u008d"+
		"\u008b\3\2\2\2\u008e\u0098\7$\2\2\u008f\u0093\7)\2\2\u0090\u0092\13\2"+
		"\2\2\u0091\u0090\3\2\2\2\u0092\u0095\3\2\2\2\u0093\u0094\3\2\2\2\u0093"+
		"\u0091\3\2\2\2\u0094\u0096\3\2\2\2\u0095\u0093\3\2\2\2\u0096\u0098\7)"+
		"\2\2\u0097\u0083\3\2\2\2\u0097\u0087\3\2\2\2\u0097\u008f\3\2\2\2\u0098"+
		"\"\3\2\2\2\u0099\u009b\t\4\2\2\u009a\u0099\3\2\2\2\u009b\u009c\3\2\2\2"+
		"\u009c\u009a\3\2\2\2\u009c\u009d\3\2\2\2\u009d\u009e\3\2\2\2\u009e\u009f"+
		"\b\22\2\2\u009f$\3\2\2\2\13\2,8\u0080\u0085\u008b\u0093\u0097\u009c\3"+
		"\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}