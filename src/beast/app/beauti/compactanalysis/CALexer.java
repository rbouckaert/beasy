// Generated from CA.g4 by ANTLR 4.4
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
	static { RuntimeMetaData.checkVersion("4.4", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__7=1, T__6=2, T__5=3, T__4=4, T__3=5, T__2=6, T__1=7, T__0=8, TEMPLATETOKEN=9, 
		IMPORTTOKEN=10, LINKTOKEN=11, UNLINKTOKEN=12, SETTOKEN=13, USETOKEN=14, 
		ADDTOKEN=15, RENAMETOKEN=16, RMTOKEN=17, TAXONSETTOKEN=18, LINKTYPE=19, 
		STRING=20, WHITESPACE=21, COMMENT=22, LINE_COMMENT=23;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] tokenNames = {
		"'\\u0000'", "'\\u0001'", "'\\u0002'", "'\\u0003'", "'\\u0004'", "'\\u0005'", 
		"'\\u0006'", "'\\u0007'", "'\b'", "'\t'", "'\n'", "'\\u000B'", "'\f'", 
		"'\r'", "'\\u000E'", "'\\u000F'", "'\\u0010'", "'\\u0011'", "'\\u0012'", 
		"'\\u0013'", "'\\u0014'", "'\\u0015'", "'\\u0016'", "'\\u0017'"
	};
	public static final String[] ruleNames = {
		"T__7", "T__6", "T__5", "T__4", "T__3", "T__2", "T__1", "T__0", "TEMPLATETOKEN", 
		"IMPORTTOKEN", "LINKTOKEN", "UNLINKTOKEN", "SETTOKEN", "USETOKEN", "ADDTOKEN", 
		"RENAMETOKEN", "RMTOKEN", "TAXONSETTOKEN", "LINKTYPE", "STRING", "WHITESPACE", 
		"COMMENT", "LINE_COMMENT"
	};


	public CALexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "CA.g4"; }

	@Override
	public String[] getTokenNames() { return tokenNames; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2\31\u00c8\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\3\2"+
		"\3\2\3\3\3\3\3\4\3\4\3\5\3\5\3\6\3\6\3\7\3\7\3\b\3\b\3\t\3\t\3\n\3\n\3"+
		"\n\3\n\3\n\3\n\3\n\3\n\3\n\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\f\3\f"+
		"\3\f\3\f\3\f\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\16\3\16\3\16\3\16\3\17\3\17"+
		"\3\17\3\17\3\20\3\20\3\20\3\20\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\22"+
		"\3\22\3\22\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\24\3\24\3\24"+
		"\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24"+
		"\3\24\5\24\u008f\n\24\3\25\6\25\u0092\n\25\r\25\16\25\u0093\3\25\3\25"+
		"\7\25\u0098\n\25\f\25\16\25\u009b\13\25\3\25\3\25\3\25\7\25\u00a0\n\25"+
		"\f\25\16\25\u00a3\13\25\3\25\5\25\u00a6\n\25\3\26\6\26\u00a9\n\26\r\26"+
		"\16\26\u00aa\3\26\3\26\3\27\3\27\3\27\3\27\3\27\7\27\u00b4\n\27\f\27\16"+
		"\27\u00b7\13\27\3\27\3\27\3\27\3\27\3\27\3\30\3\30\3\30\3\30\7\30\u00c2"+
		"\n\30\f\30\16\30\u00c5\13\30\3\30\3\30\5\u0099\u00a1\u00b5\2\31\3\3\5"+
		"\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21"+
		"!\22#\23%\24\'\25)\26+\27-\30/\31\3\2\5\t\2%(,-/<C\\aac|~~\5\2\13\f\16"+
		"\17\"\"\4\2\f\f\17\17\u00d1\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3"+
		"\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2"+
		"\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37"+
		"\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3"+
		"\2\2\2\2-\3\2\2\2\2/\3\2\2\2\3\61\3\2\2\2\5\63\3\2\2\2\7\65\3\2\2\2\t"+
		"\67\3\2\2\2\139\3\2\2\2\r;\3\2\2\2\17=\3\2\2\2\21?\3\2\2\2\23A\3\2\2\2"+
		"\25J\3\2\2\2\27Q\3\2\2\2\31V\3\2\2\2\33]\3\2\2\2\35a\3\2\2\2\37e\3\2\2"+
		"\2!i\3\2\2\2#p\3\2\2\2%s\3\2\2\2\'\u008e\3\2\2\2)\u00a5\3\2\2\2+\u00a8"+
		"\3\2\2\2-\u00ae\3\2\2\2/\u00bd\3\2\2\2\61\62\7B\2\2\62\4\3\2\2\2\63\64"+
		"\7*\2\2\64\6\3\2\2\2\65\66\7+\2\2\66\b\3\2\2\2\678\7]\2\28\n\3\2\2\29"+
		":\7=\2\2:\f\3\2\2\2;<\7.\2\2<\16\3\2\2\2=>\7_\2\2>\20\3\2\2\2?@\7?\2\2"+
		"@\22\3\2\2\2AB\7v\2\2BC\7g\2\2CD\7o\2\2DE\7r\2\2EF\7n\2\2FG\7c\2\2GH\7"+
		"v\2\2HI\7g\2\2I\24\3\2\2\2JK\7k\2\2KL\7o\2\2LM\7r\2\2MN\7q\2\2NO\7t\2"+
		"\2OP\7v\2\2P\26\3\2\2\2QR\7n\2\2RS\7k\2\2ST\7p\2\2TU\7m\2\2U\30\3\2\2"+
		"\2VW\7w\2\2WX\7p\2\2XY\7n\2\2YZ\7k\2\2Z[\7p\2\2[\\\7m\2\2\\\32\3\2\2\2"+
		"]^\7u\2\2^_\7g\2\2_`\7v\2\2`\34\3\2\2\2ab\7w\2\2bc\7u\2\2cd\7g\2\2d\36"+
		"\3\2\2\2ef\7c\2\2fg\7f\2\2gh\7f\2\2h \3\2\2\2ij\7t\2\2jk\7g\2\2kl\7p\2"+
		"\2lm\7c\2\2mn\7o\2\2no\7g\2\2o\"\3\2\2\2pq\7t\2\2qr\7o\2\2r$\3\2\2\2s"+
		"t\7v\2\2tu\7c\2\2uv\7z\2\2vw\7q\2\2wx\7p\2\2xy\7u\2\2yz\7g\2\2z{\7v\2"+
		"\2{&\3\2\2\2|}\7e\2\2}~\7n\2\2~\177\7q\2\2\177\u0080\7e\2\2\u0080\u008f"+
		"\7m\2\2\u0081\u0082\7v\2\2\u0082\u0083\7t\2\2\u0083\u0084\7g\2\2\u0084"+
		"\u008f\7g\2\2\u0085\u0086\7u\2\2\u0086\u0087\7k\2\2\u0087\u0088\7v\2\2"+
		"\u0088\u0089\7g\2\2\u0089\u008a\7o\2\2\u008a\u008b\7q\2\2\u008b\u008c"+
		"\7f\2\2\u008c\u008d\7g\2\2\u008d\u008f\7n\2\2\u008e|\3\2\2\2\u008e\u0081"+
		"\3\2\2\2\u008e\u0085\3\2\2\2\u008f(\3\2\2\2\u0090\u0092\t\2\2\2\u0091"+
		"\u0090\3\2\2\2\u0092\u0093\3\2\2\2\u0093\u0091\3\2\2\2\u0093\u0094\3\2"+
		"\2\2\u0094\u00a6\3\2\2\2\u0095\u0099\7$\2\2\u0096\u0098\13\2\2\2\u0097"+
		"\u0096\3\2\2\2\u0098\u009b\3\2\2\2\u0099\u009a\3\2\2\2\u0099\u0097\3\2"+
		"\2\2\u009a\u009c\3\2\2\2\u009b\u0099\3\2\2\2\u009c\u00a6\7$\2\2\u009d"+
		"\u00a1\7)\2\2\u009e\u00a0\13\2\2\2\u009f\u009e\3\2\2\2\u00a0\u00a3\3\2"+
		"\2\2\u00a1\u00a2\3\2\2\2\u00a1\u009f\3\2\2\2\u00a2\u00a4\3\2\2\2\u00a3"+
		"\u00a1\3\2\2\2\u00a4\u00a6\7)\2\2\u00a5\u0091\3\2\2\2\u00a5\u0095\3\2"+
		"\2\2\u00a5\u009d\3\2\2\2\u00a6*\3\2\2\2\u00a7\u00a9\t\3\2\2\u00a8\u00a7"+
		"\3\2\2\2\u00a9\u00aa\3\2\2\2\u00aa\u00a8\3\2\2\2\u00aa\u00ab\3\2\2\2\u00ab"+
		"\u00ac\3\2\2\2\u00ac\u00ad\b\26\2\2\u00ad,\3\2\2\2\u00ae\u00af\7\61\2"+
		"\2\u00af\u00b0\7,\2\2\u00b0\u00b1\3\2\2\2\u00b1\u00b5\3\2\2\2\u00b2\u00b4"+
		"\13\2\2\2\u00b3\u00b2\3\2\2\2\u00b4\u00b7\3\2\2\2\u00b5\u00b6\3\2\2\2"+
		"\u00b5\u00b3\3\2\2\2\u00b6\u00b8\3\2\2\2\u00b7\u00b5\3\2\2\2\u00b8\u00b9"+
		"\7,\2\2\u00b9\u00ba\7\61\2\2\u00ba\u00bb\3\2\2\2\u00bb\u00bc\b\27\3\2"+
		"\u00bc.\3\2\2\2\u00bd\u00be\7\61\2\2\u00be\u00bf\7\61\2\2\u00bf\u00c3"+
		"\3\2\2\2\u00c0\u00c2\n\4\2\2\u00c1\u00c0\3\2\2\2\u00c2\u00c5\3\2\2\2\u00c3"+
		"\u00c1\3\2\2\2\u00c3\u00c4\3\2\2\2\u00c4\u00c6\3\2\2\2\u00c5\u00c3\3\2"+
		"\2\2\u00c6\u00c7\b\30\3\2\u00c7\60\3\2\2\2\13\2\u008e\u0093\u0099\u00a1"+
		"\u00a5\u00aa\u00b5\u00c3\4\b\2\2\2\3\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}