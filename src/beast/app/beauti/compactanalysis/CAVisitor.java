// Generated from CA.g4 by ANTLR 4.7
package beast.app.beauti.compactanalysis;
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
	 * Visit a parse tree produced by {@link CAParser#casentence}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCasentence(CAParser.CasentenceContext ctx);
	/**
	 * Visit a parse tree produced by {@link CAParser#cmd}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCmd(CAParser.CmdContext ctx);
	/**
	 * Visit a parse tree produced by {@link CAParser#template}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTemplate(CAParser.TemplateContext ctx);
	/**
	 * Visit a parse tree produced by {@link CAParser#templatename}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTemplatename(CAParser.TemplatenameContext ctx);
	/**
	 * Visit a parse tree produced by {@link CAParser#use}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUse(CAParser.UseContext ctx);
	/**
	 * Visit a parse tree produced by {@link CAParser#key}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitKey(CAParser.KeyContext ctx);
	/**
	 * Visit a parse tree produced by {@link CAParser#value}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitValue(CAParser.ValueContext ctx);
	/**
	 * Visit a parse tree produced by {@link CAParser#import_}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitImport_(CAParser.Import_Context ctx);
	/**
	 * Visit a parse tree produced by {@link CAParser#add}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAdd(CAParser.AddContext ctx);
	/**
	 * Visit a parse tree produced by {@link CAParser#argOrUse}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArgOrUse(CAParser.ArgOrUseContext ctx);
	/**
	 * Visit a parse tree produced by {@link CAParser#subtemplate}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSubtemplate(CAParser.SubtemplateContext ctx);
	/**
	 * Visit a parse tree produced by {@link CAParser#filename}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFilename(CAParser.FilenameContext ctx);
	/**
	 * Visit a parse tree produced by {@link CAParser#alignmentprovider}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAlignmentprovider(CAParser.AlignmentproviderContext ctx);
	/**
	 * Visit a parse tree produced by {@link CAParser#arg}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArg(CAParser.ArgContext ctx);
	/**
	 * Visit a parse tree produced by {@link CAParser#link}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLink(CAParser.LinkContext ctx);
	/**
	 * Visit a parse tree produced by {@link CAParser#linktype}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLinktype(CAParser.LinktypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link CAParser#sharetype}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSharetype(CAParser.SharetypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link CAParser#unlink}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnlink(CAParser.UnlinkContext ctx);
	/**
	 * Visit a parse tree produced by {@link CAParser#set}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSet(CAParser.SetContext ctx);
	/**
	 * Visit a parse tree produced by {@link CAParser#inputidentifier}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInputidentifier(CAParser.InputidentifierContext ctx);
	/**
	 * Visit a parse tree produced by {@link CAParser#elemntName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitElemntName(CAParser.ElemntNameContext ctx);
	/**
	 * Visit a parse tree produced by {@link CAParser#idPattern}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIdPattern(CAParser.IdPatternContext ctx);
	/**
	 * Visit a parse tree produced by {@link CAParser#partitionPattern}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPartitionPattern(CAParser.PartitionPatternContext ctx);
	/**
	 * Visit a parse tree produced by {@link CAParser#inputname}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInputname(CAParser.InputnameContext ctx);
	/**
	 * Visit a parse tree produced by {@link CAParser#rename}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRename(CAParser.RenameContext ctx);
	/**
	 * Visit a parse tree produced by {@link CAParser#oldName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOldName(CAParser.OldNameContext ctx);
	/**
	 * Visit a parse tree produced by {@link CAParser#newName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNewName(CAParser.NewNameContext ctx);
	/**
	 * Visit a parse tree produced by {@link CAParser#rm}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRm(CAParser.RmContext ctx);
	/**
	 * Visit a parse tree produced by {@link CAParser#mode_}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMode_(CAParser.Mode_Context ctx);
	/**
	 * Visit a parse tree produced by {@link CAParser#taxonset}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTaxonset(CAParser.TaxonsetContext ctx);
}