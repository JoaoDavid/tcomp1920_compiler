// Generated from casual\grammar\Casual.g4 by ANTLR 4.8
package casual.grammar;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link CasualParser}.
 */
public interface CasualListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link CasualParser#program}.
	 * @param ctx the parse tree
	 */
	void enterProgram(CasualParser.ProgramContext ctx);
	/**
	 * Exit a parse tree produced by {@link CasualParser#program}.
	 * @param ctx the parse tree
	 */
	void exitProgram(CasualParser.ProgramContext ctx);
	/**
	 * Enter a parse tree produced by {@link CasualParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterStatement(CasualParser.StatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link CasualParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitStatement(CasualParser.StatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link CasualParser#expr_stat}.
	 * @param ctx the parse tree
	 */
	void enterExpr_stat(CasualParser.Expr_statContext ctx);
	/**
	 * Exit a parse tree produced by {@link CasualParser#expr_stat}.
	 * @param ctx the parse tree
	 */
	void exitExpr_stat(CasualParser.Expr_statContext ctx);
	/**
	 * Enter a parse tree produced by {@link CasualParser#return_stat}.
	 * @param ctx the parse tree
	 */
	void enterReturn_stat(CasualParser.Return_statContext ctx);
	/**
	 * Exit a parse tree produced by {@link CasualParser#return_stat}.
	 * @param ctx the parse tree
	 */
	void exitReturn_stat(CasualParser.Return_statContext ctx);
	/**
	 * Enter a parse tree produced by {@link CasualParser#datatype}.
	 * @param ctx the parse tree
	 */
	void enterDatatype(CasualParser.DatatypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link CasualParser#datatype}.
	 * @param ctx the parse tree
	 */
	void exitDatatype(CasualParser.DatatypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link CasualParser#var_decl_stat}.
	 * @param ctx the parse tree
	 */
	void enterVar_decl_stat(CasualParser.Var_decl_statContext ctx);
	/**
	 * Exit a parse tree produced by {@link CasualParser#var_decl_stat}.
	 * @param ctx the parse tree
	 */
	void exitVar_decl_stat(CasualParser.Var_decl_statContext ctx);
	/**
	 * Enter a parse tree produced by {@link CasualParser#var_assign_stat}.
	 * @param ctx the parse tree
	 */
	void enterVar_assign_stat(CasualParser.Var_assign_statContext ctx);
	/**
	 * Exit a parse tree produced by {@link CasualParser#var_assign_stat}.
	 * @param ctx the parse tree
	 */
	void exitVar_assign_stat(CasualParser.Var_assign_statContext ctx);
	/**
	 * Enter a parse tree produced by {@link CasualParser#var_type}.
	 * @param ctx the parse tree
	 */
	void enterVar_type(CasualParser.Var_typeContext ctx);
	/**
	 * Exit a parse tree produced by {@link CasualParser#var_type}.
	 * @param ctx the parse tree
	 */
	void exitVar_type(CasualParser.Var_typeContext ctx);
	/**
	 * Enter a parse tree produced by {@link CasualParser#if_stat}.
	 * @param ctx the parse tree
	 */
	void enterIf_stat(CasualParser.If_statContext ctx);
	/**
	 * Exit a parse tree produced by {@link CasualParser#if_stat}.
	 * @param ctx the parse tree
	 */
	void exitIf_stat(CasualParser.If_statContext ctx);
	/**
	 * Enter a parse tree produced by {@link CasualParser#else_block}.
	 * @param ctx the parse tree
	 */
	void enterElse_block(CasualParser.Else_blockContext ctx);
	/**
	 * Exit a parse tree produced by {@link CasualParser#else_block}.
	 * @param ctx the parse tree
	 */
	void exitElse_block(CasualParser.Else_blockContext ctx);
	/**
	 * Enter a parse tree produced by {@link CasualParser#while_stat}.
	 * @param ctx the parse tree
	 */
	void enterWhile_stat(CasualParser.While_statContext ctx);
	/**
	 * Exit a parse tree produced by {@link CasualParser#while_stat}.
	 * @param ctx the parse tree
	 */
	void exitWhile_stat(CasualParser.While_statContext ctx);
	/**
	 * Enter a parse tree produced by {@link CasualParser#func_args}.
	 * @param ctx the parse tree
	 */
	void enterFunc_args(CasualParser.Func_argsContext ctx);
	/**
	 * Exit a parse tree produced by {@link CasualParser#func_args}.
	 * @param ctx the parse tree
	 */
	void exitFunc_args(CasualParser.Func_argsContext ctx);
	/**
	 * Enter a parse tree produced by {@link CasualParser#func_decl}.
	 * @param ctx the parse tree
	 */
	void enterFunc_decl(CasualParser.Func_declContext ctx);
	/**
	 * Exit a parse tree produced by {@link CasualParser#func_decl}.
	 * @param ctx the parse tree
	 */
	void exitFunc_decl(CasualParser.Func_declContext ctx);
	/**
	 * Enter a parse tree produced by {@link CasualParser#func_def}.
	 * @param ctx the parse tree
	 */
	void enterFunc_def(CasualParser.Func_defContext ctx);
	/**
	 * Exit a parse tree produced by {@link CasualParser#func_def}.
	 * @param ctx the parse tree
	 */
	void exitFunc_def(CasualParser.Func_defContext ctx);
	/**
	 * Enter a parse tree produced by {@link CasualParser#func_inv}.
	 * @param ctx the parse tree
	 */
	void enterFunc_inv(CasualParser.Func_invContext ctx);
	/**
	 * Exit a parse tree produced by {@link CasualParser#func_inv}.
	 * @param ctx the parse tree
	 */
	void exitFunc_inv(CasualParser.Func_invContext ctx);
	/**
	 * Enter a parse tree produced by {@link CasualParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExpr(CasualParser.ExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link CasualParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExpr(CasualParser.ExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link CasualParser#arr_r_value}.
	 * @param ctx the parse tree
	 */
	void enterArr_r_value(CasualParser.Arr_r_valueContext ctx);
	/**
	 * Exit a parse tree produced by {@link CasualParser#arr_r_value}.
	 * @param ctx the parse tree
	 */
	void exitArr_r_value(CasualParser.Arr_r_valueContext ctx);
	/**
	 * Enter a parse tree produced by {@link CasualParser#arr_l_value}.
	 * @param ctx the parse tree
	 */
	void enterArr_l_value(CasualParser.Arr_l_valueContext ctx);
	/**
	 * Exit a parse tree produced by {@link CasualParser#arr_l_value}.
	 * @param ctx the parse tree
	 */
	void exitArr_l_value(CasualParser.Arr_l_valueContext ctx);
	/**
	 * Enter a parse tree produced by {@link CasualParser#binary_ope}.
	 * @param ctx the parse tree
	 */
	void enterBinary_ope(CasualParser.Binary_opeContext ctx);
	/**
	 * Exit a parse tree produced by {@link CasualParser#binary_ope}.
	 * @param ctx the parse tree
	 */
	void exitBinary_ope(CasualParser.Binary_opeContext ctx);
	/**
	 * Enter a parse tree produced by {@link CasualParser#unary_ope}.
	 * @param ctx the parse tree
	 */
	void enterUnary_ope(CasualParser.Unary_opeContext ctx);
	/**
	 * Exit a parse tree produced by {@link CasualParser#unary_ope}.
	 * @param ctx the parse tree
	 */
	void exitUnary_ope(CasualParser.Unary_opeContext ctx);
}