// Generated from CA.g4 by ANTLR 4.5
package beast.app.beauti.compactanalysis;
import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link CAParser}.
 */
public interface CAListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link CAParser#casentence}.
	 * @param ctx the parse tree
	 */
	void enterCasentence(CAParser.CasentenceContext ctx);
	/**
	 * Exit a parse tree produced by {@link CAParser#casentence}.
	 * @param ctx the parse tree
	 */
	void exitCasentence(CAParser.CasentenceContext ctx);
	/**
	 * Enter a parse tree produced by {@link CAParser#cmd}.
	 * @param ctx the parse tree
	 */
	void enterCmd(CAParser.CmdContext ctx);
	/**
	 * Exit a parse tree produced by {@link CAParser#cmd}.
	 * @param ctx the parse tree
	 */
	void exitCmd(CAParser.CmdContext ctx);
	/**
	 * Enter a parse tree produced by {@link CAParser#template}.
	 * @param ctx the parse tree
	 */
	void enterTemplate(CAParser.TemplateContext ctx);
	/**
	 * Exit a parse tree produced by {@link CAParser#template}.
	 * @param ctx the parse tree
	 */
	void exitTemplate(CAParser.TemplateContext ctx);
	/**
	 * Enter a parse tree produced by {@link CAParser#templatename}.
	 * @param ctx the parse tree
	 */
	void enterTemplatename(CAParser.TemplatenameContext ctx);
	/**
	 * Exit a parse tree produced by {@link CAParser#templatename}.
	 * @param ctx the parse tree
	 */
	void exitTemplatename(CAParser.TemplatenameContext ctx);
	/**
	 * Enter a parse tree produced by {@link CAParser#use}.
	 * @param ctx the parse tree
	 */
	void enterUse(CAParser.UseContext ctx);
	/**
	 * Exit a parse tree produced by {@link CAParser#use}.
	 * @param ctx the parse tree
	 */
	void exitUse(CAParser.UseContext ctx);
	/**
	 * Enter a parse tree produced by {@link CAParser#key}.
	 * @param ctx the parse tree
	 */
	void enterKey(CAParser.KeyContext ctx);
	/**
	 * Exit a parse tree produced by {@link CAParser#key}.
	 * @param ctx the parse tree
	 */
	void exitKey(CAParser.KeyContext ctx);
	/**
	 * Enter a parse tree produced by {@link CAParser#value}.
	 * @param ctx the parse tree
	 */
	void enterValue(CAParser.ValueContext ctx);
	/**
	 * Exit a parse tree produced by {@link CAParser#value}.
	 * @param ctx the parse tree
	 */
	void exitValue(CAParser.ValueContext ctx);
	/**
	 * Enter a parse tree produced by {@link CAParser#import_}.
	 * @param ctx the parse tree
	 */
	void enterImport_(CAParser.Import_Context ctx);
	/**
	 * Exit a parse tree produced by {@link CAParser#import_}.
	 * @param ctx the parse tree
	 */
	void exitImport_(CAParser.Import_Context ctx);
	/**
	 * Enter a parse tree produced by {@link CAParser#add}.
	 * @param ctx the parse tree
	 */
	void enterAdd(CAParser.AddContext ctx);
	/**
	 * Exit a parse tree produced by {@link CAParser#add}.
	 * @param ctx the parse tree
	 */
	void exitAdd(CAParser.AddContext ctx);
	/**
	 * Enter a parse tree produced by {@link CAParser#argOrUse}.
	 * @param ctx the parse tree
	 */
	void enterArgOrUse(CAParser.ArgOrUseContext ctx);
	/**
	 * Exit a parse tree produced by {@link CAParser#argOrUse}.
	 * @param ctx the parse tree
	 */
	void exitArgOrUse(CAParser.ArgOrUseContext ctx);
	/**
	 * Enter a parse tree produced by {@link CAParser#subtemplate}.
	 * @param ctx the parse tree
	 */
	void enterSubtemplate(CAParser.SubtemplateContext ctx);
	/**
	 * Exit a parse tree produced by {@link CAParser#subtemplate}.
	 * @param ctx the parse tree
	 */
	void exitSubtemplate(CAParser.SubtemplateContext ctx);
	/**
	 * Enter a parse tree produced by {@link CAParser#filename}.
	 * @param ctx the parse tree
	 */
	void enterFilename(CAParser.FilenameContext ctx);
	/**
	 * Exit a parse tree produced by {@link CAParser#filename}.
	 * @param ctx the parse tree
	 */
	void exitFilename(CAParser.FilenameContext ctx);
	/**
	 * Enter a parse tree produced by {@link CAParser#alignmentprovider}.
	 * @param ctx the parse tree
	 */
	void enterAlignmentprovider(CAParser.AlignmentproviderContext ctx);
	/**
	 * Exit a parse tree produced by {@link CAParser#alignmentprovider}.
	 * @param ctx the parse tree
	 */
	void exitAlignmentprovider(CAParser.AlignmentproviderContext ctx);
	/**
	 * Enter a parse tree produced by {@link CAParser#arg}.
	 * @param ctx the parse tree
	 */
	void enterArg(CAParser.ArgContext ctx);
	/**
	 * Exit a parse tree produced by {@link CAParser#arg}.
	 * @param ctx the parse tree
	 */
	void exitArg(CAParser.ArgContext ctx);
	/**
	 * Enter a parse tree produced by {@link CAParser#partitionpattern}.
	 * @param ctx the parse tree
	 */
	void enterPartitionpattern(CAParser.PartitionpatternContext ctx);
	/**
	 * Exit a parse tree produced by {@link CAParser#partitionpattern}.
	 * @param ctx the parse tree
	 */
	void exitPartitionpattern(CAParser.PartitionpatternContext ctx);
	/**
	 * Enter a parse tree produced by {@link CAParser#link}.
	 * @param ctx the parse tree
	 */
	void enterLink(CAParser.LinkContext ctx);
	/**
	 * Exit a parse tree produced by {@link CAParser#link}.
	 * @param ctx the parse tree
	 */
	void exitLink(CAParser.LinkContext ctx);
	/**
	 * Enter a parse tree produced by {@link CAParser#unlink}.
	 * @param ctx the parse tree
	 */
	void enterUnlink(CAParser.UnlinkContext ctx);
	/**
	 * Exit a parse tree produced by {@link CAParser#unlink}.
	 * @param ctx the parse tree
	 */
	void exitUnlink(CAParser.UnlinkContext ctx);
	/**
	 * Enter a parse tree produced by {@link CAParser#set}.
	 * @param ctx the parse tree
	 */
	void enterSet(CAParser.SetContext ctx);
	/**
	 * Exit a parse tree produced by {@link CAParser#set}.
	 * @param ctx the parse tree
	 */
	void exitSet(CAParser.SetContext ctx);
	/**
	 * Enter a parse tree produced by {@link CAParser#inputidentifier}.
	 * @param ctx the parse tree
	 */
	void enterInputidentifier(CAParser.InputidentifierContext ctx);
	/**
	 * Exit a parse tree produced by {@link CAParser#inputidentifier}.
	 * @param ctx the parse tree
	 */
	void exitInputidentifier(CAParser.InputidentifierContext ctx);
	/**
	 * Enter a parse tree produced by {@link CAParser#elemntName}.
	 * @param ctx the parse tree
	 */
	void enterElemntName(CAParser.ElemntNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link CAParser#elemntName}.
	 * @param ctx the parse tree
	 */
	void exitElemntName(CAParser.ElemntNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link CAParser#idPattern}.
	 * @param ctx the parse tree
	 */
	void enterIdPattern(CAParser.IdPatternContext ctx);
	/**
	 * Exit a parse tree produced by {@link CAParser#idPattern}.
	 * @param ctx the parse tree
	 */
	void exitIdPattern(CAParser.IdPatternContext ctx);
	/**
	 * Enter a parse tree produced by {@link CAParser#inputname}.
	 * @param ctx the parse tree
	 */
	void enterInputname(CAParser.InputnameContext ctx);
	/**
	 * Exit a parse tree produced by {@link CAParser#inputname}.
	 * @param ctx the parse tree
	 */
	void exitInputname(CAParser.InputnameContext ctx);
	/**
	 * Enter a parse tree produced by {@link CAParser#rename}.
	 * @param ctx the parse tree
	 */
	void enterRename(CAParser.RenameContext ctx);
	/**
	 * Exit a parse tree produced by {@link CAParser#rename}.
	 * @param ctx the parse tree
	 */
	void exitRename(CAParser.RenameContext ctx);
	/**
	 * Enter a parse tree produced by {@link CAParser#oldName}.
	 * @param ctx the parse tree
	 */
	void enterOldName(CAParser.OldNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link CAParser#oldName}.
	 * @param ctx the parse tree
	 */
	void exitOldName(CAParser.OldNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link CAParser#newName}.
	 * @param ctx the parse tree
	 */
	void enterNewName(CAParser.NewNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link CAParser#newName}.
	 * @param ctx the parse tree
	 */
	void exitNewName(CAParser.NewNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link CAParser#rm}.
	 * @param ctx the parse tree
	 */
	void enterRm(CAParser.RmContext ctx);
	/**
	 * Exit a parse tree produced by {@link CAParser#rm}.
	 * @param ctx the parse tree
	 */
	void exitRm(CAParser.RmContext ctx);
	/**
	 * Enter a parse tree produced by {@link CAParser#taxonset}.
	 * @param ctx the parse tree
	 */
	void enterTaxonset(CAParser.TaxonsetContext ctx);
	/**
	 * Exit a parse tree produced by {@link CAParser#taxonset}.
	 * @param ctx the parse tree
	 */
	void exitTaxonset(CAParser.TaxonsetContext ctx);
}