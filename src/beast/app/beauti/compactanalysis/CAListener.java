// Generated from CA.g4 by ANTLR 4.4
package beast.app.beauti.compactanalysis;
import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link CAParser}.
 */
public interface CAListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link CAParser#template}.
	 * @param ctx the parse tree
	 */
	void enterTemplate(@NotNull CAParser.TemplateContext ctx);
	/**
	 * Exit a parse tree produced by {@link CAParser#template}.
	 * @param ctx the parse tree
	 */
	void exitTemplate(@NotNull CAParser.TemplateContext ctx);
	/**
	 * Enter a parse tree produced by {@link CAParser#taxonset}.
	 * @param ctx the parse tree
	 */
	void enterTaxonset(@NotNull CAParser.TaxonsetContext ctx);
	/**
	 * Exit a parse tree produced by {@link CAParser#taxonset}.
	 * @param ctx the parse tree
	 */
	void exitTaxonset(@NotNull CAParser.TaxonsetContext ctx);
	/**
	 * Enter a parse tree produced by {@link CAParser#use}.
	 * @param ctx the parse tree
	 */
	void enterUse(@NotNull CAParser.UseContext ctx);
	/**
	 * Exit a parse tree produced by {@link CAParser#use}.
	 * @param ctx the parse tree
	 */
	void exitUse(@NotNull CAParser.UseContext ctx);
	/**
	 * Enter a parse tree produced by {@link CAParser#link}.
	 * @param ctx the parse tree
	 */
	void enterLink(@NotNull CAParser.LinkContext ctx);
	/**
	 * Exit a parse tree produced by {@link CAParser#link}.
	 * @param ctx the parse tree
	 */
	void exitLink(@NotNull CAParser.LinkContext ctx);
	/**
	 * Enter a parse tree produced by {@link CAParser#inputidentifier}.
	 * @param ctx the parse tree
	 */
	void enterInputidentifier(@NotNull CAParser.InputidentifierContext ctx);
	/**
	 * Exit a parse tree produced by {@link CAParser#inputidentifier}.
	 * @param ctx the parse tree
	 */
	void exitInputidentifier(@NotNull CAParser.InputidentifierContext ctx);
	/**
	 * Enter a parse tree produced by {@link CAParser#alignmentprovider}.
	 * @param ctx the parse tree
	 */
	void enterAlignmentprovider(@NotNull CAParser.AlignmentproviderContext ctx);
	/**
	 * Exit a parse tree produced by {@link CAParser#alignmentprovider}.
	 * @param ctx the parse tree
	 */
	void exitAlignmentprovider(@NotNull CAParser.AlignmentproviderContext ctx);
	/**
	 * Enter a parse tree produced by {@link CAParser#import_}.
	 * @param ctx the parse tree
	 */
	void enterImport_(@NotNull CAParser.Import_Context ctx);
	/**
	 * Exit a parse tree produced by {@link CAParser#import_}.
	 * @param ctx the parse tree
	 */
	void exitImport_(@NotNull CAParser.Import_Context ctx);
	/**
	 * Enter a parse tree produced by {@link CAParser#arg}.
	 * @param ctx the parse tree
	 */
	void enterArg(@NotNull CAParser.ArgContext ctx);
	/**
	 * Exit a parse tree produced by {@link CAParser#arg}.
	 * @param ctx the parse tree
	 */
	void exitArg(@NotNull CAParser.ArgContext ctx);
	/**
	 * Enter a parse tree produced by {@link CAParser#oldName}.
	 * @param ctx the parse tree
	 */
	void enterOldName(@NotNull CAParser.OldNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link CAParser#oldName}.
	 * @param ctx the parse tree
	 */
	void exitOldName(@NotNull CAParser.OldNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link CAParser#casentence}.
	 * @param ctx the parse tree
	 */
	void enterCasentence(@NotNull CAParser.CasentenceContext ctx);
	/**
	 * Exit a parse tree produced by {@link CAParser#casentence}.
	 * @param ctx the parse tree
	 */
	void exitCasentence(@NotNull CAParser.CasentenceContext ctx);
	/**
	 * Enter a parse tree produced by {@link CAParser#templatename}.
	 * @param ctx the parse tree
	 */
	void enterTemplatename(@NotNull CAParser.TemplatenameContext ctx);
	/**
	 * Exit a parse tree produced by {@link CAParser#templatename}.
	 * @param ctx the parse tree
	 */
	void exitTemplatename(@NotNull CAParser.TemplatenameContext ctx);
	/**
	 * Enter a parse tree produced by {@link CAParser#partitionpattern}.
	 * @param ctx the parse tree
	 */
	void enterPartitionpattern(@NotNull CAParser.PartitionpatternContext ctx);
	/**
	 * Exit a parse tree produced by {@link CAParser#partitionpattern}.
	 * @param ctx the parse tree
	 */
	void exitPartitionpattern(@NotNull CAParser.PartitionpatternContext ctx);
	/**
	 * Enter a parse tree produced by {@link CAParser#inputname}.
	 * @param ctx the parse tree
	 */
	void enterInputname(@NotNull CAParser.InputnameContext ctx);
	/**
	 * Exit a parse tree produced by {@link CAParser#inputname}.
	 * @param ctx the parse tree
	 */
	void exitInputname(@NotNull CAParser.InputnameContext ctx);
	/**
	 * Enter a parse tree produced by {@link CAParser#value}.
	 * @param ctx the parse tree
	 */
	void enterValue(@NotNull CAParser.ValueContext ctx);
	/**
	 * Exit a parse tree produced by {@link CAParser#value}.
	 * @param ctx the parse tree
	 */
	void exitValue(@NotNull CAParser.ValueContext ctx);
	/**
	 * Enter a parse tree produced by {@link CAParser#key}.
	 * @param ctx the parse tree
	 */
	void enterKey(@NotNull CAParser.KeyContext ctx);
	/**
	 * Exit a parse tree produced by {@link CAParser#key}.
	 * @param ctx the parse tree
	 */
	void exitKey(@NotNull CAParser.KeyContext ctx);
	/**
	 * Enter a parse tree produced by {@link CAParser#add}.
	 * @param ctx the parse tree
	 */
	void enterAdd(@NotNull CAParser.AddContext ctx);
	/**
	 * Exit a parse tree produced by {@link CAParser#add}.
	 * @param ctx the parse tree
	 */
	void exitAdd(@NotNull CAParser.AddContext ctx);
	/**
	 * Enter a parse tree produced by {@link CAParser#set}.
	 * @param ctx the parse tree
	 */
	void enterSet(@NotNull CAParser.SetContext ctx);
	/**
	 * Exit a parse tree produced by {@link CAParser#set}.
	 * @param ctx the parse tree
	 */
	void exitSet(@NotNull CAParser.SetContext ctx);
	/**
	 * Enter a parse tree produced by {@link CAParser#idPattern}.
	 * @param ctx the parse tree
	 */
	void enterIdPattern(@NotNull CAParser.IdPatternContext ctx);
	/**
	 * Exit a parse tree produced by {@link CAParser#idPattern}.
	 * @param ctx the parse tree
	 */
	void exitIdPattern(@NotNull CAParser.IdPatternContext ctx);
	/**
	 * Enter a parse tree produced by {@link CAParser#filename}.
	 * @param ctx the parse tree
	 */
	void enterFilename(@NotNull CAParser.FilenameContext ctx);
	/**
	 * Exit a parse tree produced by {@link CAParser#filename}.
	 * @param ctx the parse tree
	 */
	void exitFilename(@NotNull CAParser.FilenameContext ctx);
	/**
	 * Enter a parse tree produced by {@link CAParser#elemntName}.
	 * @param ctx the parse tree
	 */
	void enterElemntName(@NotNull CAParser.ElemntNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link CAParser#elemntName}.
	 * @param ctx the parse tree
	 */
	void exitElemntName(@NotNull CAParser.ElemntNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link CAParser#newName}.
	 * @param ctx the parse tree
	 */
	void enterNewName(@NotNull CAParser.NewNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link CAParser#newName}.
	 * @param ctx the parse tree
	 */
	void exitNewName(@NotNull CAParser.NewNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link CAParser#unlink}.
	 * @param ctx the parse tree
	 */
	void enterUnlink(@NotNull CAParser.UnlinkContext ctx);
	/**
	 * Exit a parse tree produced by {@link CAParser#unlink}.
	 * @param ctx the parse tree
	 */
	void exitUnlink(@NotNull CAParser.UnlinkContext ctx);
	/**
	 * Enter a parse tree produced by {@link CAParser#rename}.
	 * @param ctx the parse tree
	 */
	void enterRename(@NotNull CAParser.RenameContext ctx);
	/**
	 * Exit a parse tree produced by {@link CAParser#rename}.
	 * @param ctx the parse tree
	 */
	void exitRename(@NotNull CAParser.RenameContext ctx);
	/**
	 * Enter a parse tree produced by {@link CAParser#cmd}.
	 * @param ctx the parse tree
	 */
	void enterCmd(@NotNull CAParser.CmdContext ctx);
	/**
	 * Exit a parse tree produced by {@link CAParser#cmd}.
	 * @param ctx the parse tree
	 */
	void exitCmd(@NotNull CAParser.CmdContext ctx);
	/**
	 * Enter a parse tree produced by {@link CAParser#rm}.
	 * @param ctx the parse tree
	 */
	void enterRm(@NotNull CAParser.RmContext ctx);
	/**
	 * Exit a parse tree produced by {@link CAParser#rm}.
	 * @param ctx the parse tree
	 */
	void exitRm(@NotNull CAParser.RmContext ctx);
}