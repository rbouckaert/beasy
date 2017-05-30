// Generated from CA.g4 by ANTLR 4.4
package beast.app.beauti.compactanalysis;
import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link CAParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface CAVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link CAParser#template}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTemplate(@NotNull CAParser.TemplateContext ctx);
	/**
	 * Visit a parse tree produced by {@link CAParser#taxonset}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTaxonset(@NotNull CAParser.TaxonsetContext ctx);
	/**
	 * Visit a parse tree produced by {@link CAParser#use}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUse(@NotNull CAParser.UseContext ctx);
	/**
	 * Visit a parse tree produced by {@link CAParser#link}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLink(@NotNull CAParser.LinkContext ctx);
	/**
	 * Visit a parse tree produced by {@link CAParser#inputidentifier}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInputidentifier(@NotNull CAParser.InputidentifierContext ctx);
	/**
	 * Visit a parse tree produced by {@link CAParser#alignmentprovider}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAlignmentprovider(@NotNull CAParser.AlignmentproviderContext ctx);
	/**
	 * Visit a parse tree produced by {@link CAParser#import_}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitImport_(@NotNull CAParser.Import_Context ctx);
	/**
	 * Visit a parse tree produced by {@link CAParser#arg}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArg(@NotNull CAParser.ArgContext ctx);
	/**
	 * Visit a parse tree produced by {@link CAParser#oldName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOldName(@NotNull CAParser.OldNameContext ctx);
	/**
	 * Visit a parse tree produced by {@link CAParser#casentence}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCasentence(@NotNull CAParser.CasentenceContext ctx);
	/**
	 * Visit a parse tree produced by {@link CAParser#templatename}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTemplatename(@NotNull CAParser.TemplatenameContext ctx);
	/**
	 * Visit a parse tree produced by {@link CAParser#partitionpattern}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPartitionpattern(@NotNull CAParser.PartitionpatternContext ctx);
	/**
	 * Visit a parse tree produced by {@link CAParser#inputname}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInputname(@NotNull CAParser.InputnameContext ctx);
	/**
	 * Visit a parse tree produced by {@link CAParser#value}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitValue(@NotNull CAParser.ValueContext ctx);
	/**
	 * Visit a parse tree produced by {@link CAParser#key}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitKey(@NotNull CAParser.KeyContext ctx);
	/**
	 * Visit a parse tree produced by {@link CAParser#add}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAdd(@NotNull CAParser.AddContext ctx);
	/**
	 * Visit a parse tree produced by {@link CAParser#set}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSet(@NotNull CAParser.SetContext ctx);
	/**
	 * Visit a parse tree produced by {@link CAParser#idPattern}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIdPattern(@NotNull CAParser.IdPatternContext ctx);
	/**
	 * Visit a parse tree produced by {@link CAParser#filename}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFilename(@NotNull CAParser.FilenameContext ctx);
	/**
	 * Visit a parse tree produced by {@link CAParser#elemntName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitElemntName(@NotNull CAParser.ElemntNameContext ctx);
	/**
	 * Visit a parse tree produced by {@link CAParser#newName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNewName(@NotNull CAParser.NewNameContext ctx);
	/**
	 * Visit a parse tree produced by {@link CAParser#unlink}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnlink(@NotNull CAParser.UnlinkContext ctx);
	/**
	 * Visit a parse tree produced by {@link CAParser#rename}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRename(@NotNull CAParser.RenameContext ctx);
	/**
	 * Visit a parse tree produced by {@link CAParser#cmd}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCmd(@NotNull CAParser.CmdContext ctx);
	/**
	 * Visit a parse tree produced by {@link CAParser#rm}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRm(@NotNull CAParser.RmContext ctx);
}