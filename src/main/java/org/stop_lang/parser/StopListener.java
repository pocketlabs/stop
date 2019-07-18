// Generated from /Users/kyleshank/git/stop/Stop.g4 by ANTLR 4.7.2
package org.stop_lang.parser;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link StopParser}.
 */
public interface StopListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link StopParser#file}.
	 * @param ctx the parse tree
	 */
	void enterFile(StopParser.FileContext ctx);
	/**
	 * Exit a parse tree produced by {@link StopParser#file}.
	 * @param ctx the parse tree
	 */
	void exitFile(StopParser.FileContext ctx);
	/**
	 * Enter a parse tree produced by {@link StopParser#packageName}.
	 * @param ctx the parse tree
	 */
	void enterPackageName(StopParser.PackageNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link StopParser#packageName}.
	 * @param ctx the parse tree
	 */
	void exitPackageName(StopParser.PackageNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link StopParser#packageDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterPackageDeclaration(StopParser.PackageDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link StopParser#packageDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitPackageDeclaration(StopParser.PackageDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link StopParser#include}.
	 * @param ctx the parse tree
	 */
	void enterInclude(StopParser.IncludeContext ctx);
	/**
	 * Exit a parse tree produced by {@link StopParser#include}.
	 * @param ctx the parse tree
	 */
	void exitInclude(StopParser.IncludeContext ctx);
	/**
	 * Enter a parse tree produced by {@link StopParser#model}.
	 * @param ctx the parse tree
	 */
	void enterModel(StopParser.ModelContext ctx);
	/**
	 * Exit a parse tree produced by {@link StopParser#model}.
	 * @param ctx the parse tree
	 */
	void exitModel(StopParser.ModelContext ctx);
	/**
	 * Enter a parse tree produced by {@link StopParser#block}.
	 * @param ctx the parse tree
	 */
	void enterBlock(StopParser.BlockContext ctx);
	/**
	 * Exit a parse tree produced by {@link StopParser#block}.
	 * @param ctx the parse tree
	 */
	void exitBlock(StopParser.BlockContext ctx);
	/**
	 * Enter a parse tree produced by {@link StopParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterStatement(StopParser.StatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link StopParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitStatement(StopParser.StatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link StopParser#block_with_timeout}.
	 * @param ctx the parse tree
	 */
	void enterBlock_with_timeout(StopParser.Block_with_timeoutContext ctx);
	/**
	 * Exit a parse tree produced by {@link StopParser#block_with_timeout}.
	 * @param ctx the parse tree
	 */
	void exitBlock_with_timeout(StopParser.Block_with_timeoutContext ctx);
	/**
	 * Enter a parse tree produced by {@link StopParser#block_with_timeout_statement}.
	 * @param ctx the parse tree
	 */
	void enterBlock_with_timeout_statement(StopParser.Block_with_timeout_statementContext ctx);
	/**
	 * Exit a parse tree produced by {@link StopParser#block_with_timeout_statement}.
	 * @param ctx the parse tree
	 */
	void exitBlock_with_timeout_statement(StopParser.Block_with_timeout_statementContext ctx);
	/**
	 * Enter a parse tree produced by {@link StopParser#return_block}.
	 * @param ctx the parse tree
	 */
	void enterReturn_block(StopParser.Return_blockContext ctx);
	/**
	 * Exit a parse tree produced by {@link StopParser#return_block}.
	 * @param ctx the parse tree
	 */
	void exitReturn_block(StopParser.Return_blockContext ctx);
	/**
	 * Enter a parse tree produced by {@link StopParser#return_block_statement}.
	 * @param ctx the parse tree
	 */
	void enterReturn_block_statement(StopParser.Return_block_statementContext ctx);
	/**
	 * Exit a parse tree produced by {@link StopParser#return_block_statement}.
	 * @param ctx the parse tree
	 */
	void exitReturn_block_statement(StopParser.Return_block_statementContext ctx);
	/**
	 * Enter a parse tree produced by {@link StopParser#return_block_with_timeout}.
	 * @param ctx the parse tree
	 */
	void enterReturn_block_with_timeout(StopParser.Return_block_with_timeoutContext ctx);
	/**
	 * Exit a parse tree produced by {@link StopParser#return_block_with_timeout}.
	 * @param ctx the parse tree
	 */
	void exitReturn_block_with_timeout(StopParser.Return_block_with_timeoutContext ctx);
	/**
	 * Enter a parse tree produced by {@link StopParser#return_block_with_timeout_statement}.
	 * @param ctx the parse tree
	 */
	void enterReturn_block_with_timeout_statement(StopParser.Return_block_with_timeout_statementContext ctx);
	/**
	 * Exit a parse tree produced by {@link StopParser#return_block_with_timeout_statement}.
	 * @param ctx the parse tree
	 */
	void exitReturn_block_with_timeout_statement(StopParser.Return_block_with_timeout_statementContext ctx);
	/**
	 * Enter a parse tree produced by {@link StopParser#scalar_type}.
	 * @param ctx the parse tree
	 */
	void enterScalar_type(StopParser.Scalar_typeContext ctx);
	/**
	 * Exit a parse tree produced by {@link StopParser#scalar_type}.
	 * @param ctx the parse tree
	 */
	void exitScalar_type(StopParser.Scalar_typeContext ctx);
	/**
	 * Enter a parse tree produced by {@link StopParser#model_type}.
	 * @param ctx the parse tree
	 */
	void enterModel_type(StopParser.Model_typeContext ctx);
	/**
	 * Exit a parse tree produced by {@link StopParser#model_type}.
	 * @param ctx the parse tree
	 */
	void exitModel_type(StopParser.Model_typeContext ctx);
	/**
	 * Enter a parse tree produced by {@link StopParser#enum_type}.
	 * @param ctx the parse tree
	 */
	void enterEnum_type(StopParser.Enum_typeContext ctx);
	/**
	 * Exit a parse tree produced by {@link StopParser#enum_type}.
	 * @param ctx the parse tree
	 */
	void exitEnum_type(StopParser.Enum_typeContext ctx);
	/**
	 * Enter a parse tree produced by {@link StopParser#enum_value}.
	 * @param ctx the parse tree
	 */
	void enterEnum_value(StopParser.Enum_valueContext ctx);
	/**
	 * Exit a parse tree produced by {@link StopParser#enum_value}.
	 * @param ctx the parse tree
	 */
	void exitEnum_value(StopParser.Enum_valueContext ctx);
	/**
	 * Enter a parse tree produced by {@link StopParser#enumeration}.
	 * @param ctx the parse tree
	 */
	void enterEnumeration(StopParser.EnumerationContext ctx);
	/**
	 * Exit a parse tree produced by {@link StopParser#enumeration}.
	 * @param ctx the parse tree
	 */
	void exitEnumeration(StopParser.EnumerationContext ctx);
	/**
	 * Enter a parse tree produced by {@link StopParser#field}.
	 * @param ctx the parse tree
	 */
	void enterField(StopParser.FieldContext ctx);
	/**
	 * Exit a parse tree produced by {@link StopParser#field}.
	 * @param ctx the parse tree
	 */
	void exitField(StopParser.FieldContext ctx);
	/**
	 * Enter a parse tree produced by {@link StopParser#async_source}.
	 * @param ctx the parse tree
	 */
	void enterAsync_source(StopParser.Async_sourceContext ctx);
	/**
	 * Exit a parse tree produced by {@link StopParser#async_source}.
	 * @param ctx the parse tree
	 */
	void exitAsync_source(StopParser.Async_sourceContext ctx);
	/**
	 * Enter a parse tree produced by {@link StopParser#async_source_mapping}.
	 * @param ctx the parse tree
	 */
	void enterAsync_source_mapping(StopParser.Async_source_mappingContext ctx);
	/**
	 * Exit a parse tree produced by {@link StopParser#async_source_mapping}.
	 * @param ctx the parse tree
	 */
	void exitAsync_source_mapping(StopParser.Async_source_mappingContext ctx);
	/**
	 * Enter a parse tree produced by {@link StopParser#async_source_mapping_parameter}.
	 * @param ctx the parse tree
	 */
	void enterAsync_source_mapping_parameter(StopParser.Async_source_mapping_parameterContext ctx);
	/**
	 * Exit a parse tree produced by {@link StopParser#async_source_mapping_parameter}.
	 * @param ctx the parse tree
	 */
	void exitAsync_source_mapping_parameter(StopParser.Async_source_mapping_parameterContext ctx);
	/**
	 * Enter a parse tree produced by {@link StopParser#reference}.
	 * @param ctx the parse tree
	 */
	void enterReference(StopParser.ReferenceContext ctx);
	/**
	 * Exit a parse tree produced by {@link StopParser#reference}.
	 * @param ctx the parse tree
	 */
	void exitReference(StopParser.ReferenceContext ctx);
	/**
	 * Enter a parse tree produced by {@link StopParser#async_source_mapping_parameter_rename}.
	 * @param ctx the parse tree
	 */
	void enterAsync_source_mapping_parameter_rename(StopParser.Async_source_mapping_parameter_renameContext ctx);
	/**
	 * Exit a parse tree produced by {@link StopParser#async_source_mapping_parameter_rename}.
	 * @param ctx the parse tree
	 */
	void exitAsync_source_mapping_parameter_rename(StopParser.Async_source_mapping_parameter_renameContext ctx);
	/**
	 * Enter a parse tree produced by {@link StopParser#type}.
	 * @param ctx the parse tree
	 */
	void enterType(StopParser.TypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link StopParser#type}.
	 * @param ctx the parse tree
	 */
	void exitType(StopParser.TypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link StopParser#collection}.
	 * @param ctx the parse tree
	 */
	void enterCollection(StopParser.CollectionContext ctx);
	/**
	 * Exit a parse tree produced by {@link StopParser#collection}.
	 * @param ctx the parse tree
	 */
	void exitCollection(StopParser.CollectionContext ctx);
	/**
	 * Enter a parse tree produced by {@link StopParser#return_type}.
	 * @param ctx the parse tree
	 */
	void enterReturn_type(StopParser.Return_typeContext ctx);
	/**
	 * Exit a parse tree produced by {@link StopParser#return_type}.
	 * @param ctx the parse tree
	 */
	void exitReturn_type(StopParser.Return_typeContext ctx);
	/**
	 * Enter a parse tree produced by {@link StopParser#throw_type}.
	 * @param ctx the parse tree
	 */
	void enterThrow_type(StopParser.Throw_typeContext ctx);
	/**
	 * Exit a parse tree produced by {@link StopParser#throw_type}.
	 * @param ctx the parse tree
	 */
	void exitThrow_type(StopParser.Throw_typeContext ctx);
	/**
	 * Enter a parse tree produced by {@link StopParser#throw_parameter}.
	 * @param ctx the parse tree
	 */
	void enterThrow_parameter(StopParser.Throw_parameterContext ctx);
	/**
	 * Exit a parse tree produced by {@link StopParser#throw_parameter}.
	 * @param ctx the parse tree
	 */
	void exitThrow_parameter(StopParser.Throw_parameterContext ctx);
	/**
	 * Enter a parse tree produced by {@link StopParser#timeout}.
	 * @param ctx the parse tree
	 */
	void enterTimeout(StopParser.TimeoutContext ctx);
	/**
	 * Exit a parse tree produced by {@link StopParser#timeout}.
	 * @param ctx the parse tree
	 */
	void exitTimeout(StopParser.TimeoutContext ctx);
	/**
	 * Enter a parse tree produced by {@link StopParser#transition}.
	 * @param ctx the parse tree
	 */
	void enterTransition(StopParser.TransitionContext ctx);
	/**
	 * Exit a parse tree produced by {@link StopParser#transition}.
	 * @param ctx the parse tree
	 */
	void exitTransition(StopParser.TransitionContext ctx);
	/**
	 * Enter a parse tree produced by {@link StopParser#enqueue}.
	 * @param ctx the parse tree
	 */
	void enterEnqueue(StopParser.EnqueueContext ctx);
	/**
	 * Exit a parse tree produced by {@link StopParser#enqueue}.
	 * @param ctx the parse tree
	 */
	void exitEnqueue(StopParser.EnqueueContext ctx);
}