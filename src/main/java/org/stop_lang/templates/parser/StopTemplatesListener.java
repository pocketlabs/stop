// Generated from /Users/kyleshank/git/stop/StopTemplates.g4 by ANTLR 4.7.2
package org.stop_lang.templates.parser;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link StopTemplatesParser}.
 */
public interface StopTemplatesListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link StopTemplatesParser#file}.
	 * @param ctx the parse tree
	 */
	void enterFile(StopTemplatesParser.FileContext ctx);
	/**
	 * Exit a parse tree produced by {@link StopTemplatesParser#file}.
	 * @param ctx the parse tree
	 */
	void exitFile(StopTemplatesParser.FileContext ctx);
	/**
	 * Enter a parse tree produced by {@link StopTemplatesParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterStatement(StopTemplatesParser.StatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link StopTemplatesParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitStatement(StopTemplatesParser.StatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link StopTemplatesParser#nested_statements}.
	 * @param ctx the parse tree
	 */
	void enterNested_statements(StopTemplatesParser.Nested_statementsContext ctx);
	/**
	 * Exit a parse tree produced by {@link StopTemplatesParser#nested_statements}.
	 * @param ctx the parse tree
	 */
	void exitNested_statements(StopTemplatesParser.Nested_statementsContext ctx);
	/**
	 * Enter a parse tree produced by {@link StopTemplatesParser#component}.
	 * @param ctx the parse tree
	 */
	void enterComponent(StopTemplatesParser.ComponentContext ctx);
	/**
	 * Exit a parse tree produced by {@link StopTemplatesParser#component}.
	 * @param ctx the parse tree
	 */
	void exitComponent(StopTemplatesParser.ComponentContext ctx);
	/**
	 * Enter a parse tree produced by {@link StopTemplatesParser#component_parameter}.
	 * @param ctx the parse tree
	 */
	void enterComponent_parameter(StopTemplatesParser.Component_parameterContext ctx);
	/**
	 * Exit a parse tree produced by {@link StopTemplatesParser#component_parameter}.
	 * @param ctx the parse tree
	 */
	void exitComponent_parameter(StopTemplatesParser.Component_parameterContext ctx);
	/**
	 * Enter a parse tree produced by {@link StopTemplatesParser#component_parameter_value_or_collection}.
	 * @param ctx the parse tree
	 */
	void enterComponent_parameter_value_or_collection(StopTemplatesParser.Component_parameter_value_or_collectionContext ctx);
	/**
	 * Exit a parse tree produced by {@link StopTemplatesParser#component_parameter_value_or_collection}.
	 * @param ctx the parse tree
	 */
	void exitComponent_parameter_value_or_collection(StopTemplatesParser.Component_parameter_value_or_collectionContext ctx);
	/**
	 * Enter a parse tree produced by {@link StopTemplatesParser#component_parameter_value_collection}.
	 * @param ctx the parse tree
	 */
	void enterComponent_parameter_value_collection(StopTemplatesParser.Component_parameter_value_collectionContext ctx);
	/**
	 * Exit a parse tree produced by {@link StopTemplatesParser#component_parameter_value_collection}.
	 * @param ctx the parse tree
	 */
	void exitComponent_parameter_value_collection(StopTemplatesParser.Component_parameter_value_collectionContext ctx);
	/**
	 * Enter a parse tree produced by {@link StopTemplatesParser#component_parameter_value}.
	 * @param ctx the parse tree
	 */
	void enterComponent_parameter_value(StopTemplatesParser.Component_parameter_valueContext ctx);
	/**
	 * Exit a parse tree produced by {@link StopTemplatesParser#component_parameter_value}.
	 * @param ctx the parse tree
	 */
	void exitComponent_parameter_value(StopTemplatesParser.Component_parameter_valueContext ctx);
	/**
	 * Enter a parse tree produced by {@link StopTemplatesParser#collection}.
	 * @param ctx the parse tree
	 */
	void enterCollection(StopTemplatesParser.CollectionContext ctx);
	/**
	 * Exit a parse tree produced by {@link StopTemplatesParser#collection}.
	 * @param ctx the parse tree
	 */
	void exitCollection(StopTemplatesParser.CollectionContext ctx);
	/**
	 * Enter a parse tree produced by {@link StopTemplatesParser#conditional}.
	 * @param ctx the parse tree
	 */
	void enterConditional(StopTemplatesParser.ConditionalContext ctx);
	/**
	 * Exit a parse tree produced by {@link StopTemplatesParser#conditional}.
	 * @param ctx the parse tree
	 */
	void exitConditional(StopTemplatesParser.ConditionalContext ctx);
}