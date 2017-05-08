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
		SEMI=1, COMMA=2, OPENP=3, CLOSEP=4, EQ=5, TEMPLATETOKEN=6, IMPORTTOKEN=7, 
		PARTITIONTOKEN=8, LINKTOKEN=9, UNLINKTOKEN=10, SETTOKEN=11, SUBTOKEN=12, 
		LINKTYPE=13, STRING=14, WHITESPACE=15;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"SEMI", "COMMA", "OPENP", "CLOSEP", "EQ", "TEMPLATETOKEN", "IMPORTTOKEN", 
		"PARTITIONTOKEN", "LINKTOKEN", "UNLINKTOKEN", "SETTOKEN", "SUBTOKEN", 
		"LINKTYPE", "STRING", "WHITESPACE"
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
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2\21\u0086\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\3\2\3\2\3\3\3\3"+
		"\3\4\3\4\3\5\3\5\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\b\3\b\3"+
		"\b\3\b\3\b\3\b\3\b\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\n\3\n\3\n"+
		"\3\n\3\n\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\f\3\f\3\f\3\f\3\r\3\r\3"+
		"\r\3\r\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16"+
		"\5\16g\n\16\3\17\6\17j\n\17\r\17\16\17k\3\17\3\17\7\17p\n\17\f\17\16\17"+
		"s\13\17\3\17\3\17\3\17\7\17x\n\17\f\17\16\17{\13\17\3\17\5\17~\n\17\3"+
		"\20\6\20\u0081\n\20\r\20\16\20\u0082\3\20\3\20\4qy\2\21\3\3\5\4\7\5\t"+
		"\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21\3\2\4\n"+
		"\2%%\'(,-/;C\\aac|~~\5\2\13\f\16\17\"\"\u008d\2\3\3\2\2\2\2\5\3\2\2\2"+
		"\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3"+
		"\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2"+
		"\2\2\35\3\2\2\2\2\37\3\2\2\2\3!\3\2\2\2\5#\3\2\2\2\7%\3\2\2\2\t\'\3\2"+
		"\2\2\13)\3\2\2\2\r+\3\2\2\2\17\64\3\2\2\2\21;\3\2\2\2\23E\3\2\2\2\25J"+
		"\3\2\2\2\27Q\3\2\2\2\31U\3\2\2\2\33f\3\2\2\2\35}\3\2\2\2\37\u0080\3\2"+
		"\2\2!\"\7=\2\2\"\4\3\2\2\2#$\7.\2\2$\6\3\2\2\2%&\7*\2\2&\b\3\2\2\2\'("+
		"\7+\2\2(\n\3\2\2\2)*\7?\2\2*\f\3\2\2\2+,\7v\2\2,-\7g\2\2-.\7o\2\2./\7"+
		"r\2\2/\60\7n\2\2\60\61\7c\2\2\61\62\7v\2\2\62\63\7g\2\2\63\16\3\2\2\2"+
		"\64\65\7k\2\2\65\66\7o\2\2\66\67\7r\2\2\678\7q\2\289\7t\2\29:\7v\2\2:"+
		"\20\3\2\2\2;<\7r\2\2<=\7c\2\2=>\7t\2\2>?\7v\2\2?@\7k\2\2@A\7v\2\2AB\7"+
		"k\2\2BC\7q\2\2CD\7p\2\2D\22\3\2\2\2EF\7n\2\2FG\7k\2\2GH\7p\2\2HI\7m\2"+
		"\2I\24\3\2\2\2JK\7w\2\2KL\7p\2\2LM\7n\2\2MN\7k\2\2NO\7p\2\2OP\7m\2\2P"+
		"\26\3\2\2\2QR\7u\2\2RS\7g\2\2ST\7v\2\2T\30\3\2\2\2UV\7u\2\2VW\7w\2\2W"+
		"X\7d\2\2X\32\3\2\2\2YZ\7e\2\2Z[\7n\2\2[\\\7q\2\2\\]\7e\2\2]g\7m\2\2^_"+
		"\7v\2\2_`\7t\2\2`a\7g\2\2ag\7g\2\2bc\7u\2\2cd\7k\2\2de\7v\2\2eg\7g\2\2"+
		"fY\3\2\2\2f^\3\2\2\2fb\3\2\2\2g\34\3\2\2\2hj\t\2\2\2ih\3\2\2\2jk\3\2\2"+
		"\2ki\3\2\2\2kl\3\2\2\2l~\3\2\2\2mq\7$\2\2np\13\2\2\2on\3\2\2\2ps\3\2\2"+
		"\2qr\3\2\2\2qo\3\2\2\2rt\3\2\2\2sq\3\2\2\2t~\7$\2\2uy\7)\2\2vx\13\2\2"+
		"\2wv\3\2\2\2x{\3\2\2\2yz\3\2\2\2yw\3\2\2\2z|\3\2\2\2{y\3\2\2\2|~\7)\2"+
		"\2}i\3\2\2\2}m\3\2\2\2}u\3\2\2\2~\36\3\2\2\2\177\u0081\t\3\2\2\u0080\177"+
		"\3\2\2\2\u0081\u0082\3\2\2\2\u0082\u0080\3\2\2\2\u0082\u0083\3\2\2\2\u0083"+
		"\u0084\3\2\2\2\u0084\u0085\b\20\2\2\u0085 \3\2\2\2\t\2fkqy}\u0082\3\b"+
		"\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}