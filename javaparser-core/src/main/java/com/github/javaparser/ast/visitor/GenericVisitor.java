/*
 * Copyright (C) 2007-2010 Júlio Vilmar Gesser.
 * Copyright (C) 2011, 2013-2024 The JavaParser Team.
 *
 * This file is part of JavaParser.
 *
 * JavaParser can be used either under the terms of
 * a) the GNU Lesser General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 * b) the terms of the Apache License
 *
 * You should have received a copy of both licenses in LICENCE.LGPL and
 * LICENCE.APACHE. Please refer to those files for details.
 *
 * JavaParser is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 */
package com.github.javaparser.ast.visitor;

import com.github.javaparser.ast.*;
import com.github.javaparser.ast.body.*;
import com.github.javaparser.ast.comments.BlockComment;
import com.github.javaparser.ast.comments.JavadocComment;
import com.github.javaparser.ast.comments.LineComment;
import com.github.javaparser.ast.expr.*;
import com.github.javaparser.ast.jml.doc.JmlDoc;
import com.github.javaparser.ast.jml.doc.JmlDocDeclaration;
import com.github.javaparser.ast.jml.doc.JmlDocStmt;
import com.github.javaparser.ast.jml.doc.JmlDocType;
import com.github.javaparser.ast.jml.expr.*;
import com.github.javaparser.ast.jml.stmt.*;
import com.github.javaparser.ast.jml.clauses.*;
import com.github.javaparser.ast.jml.body.*;
import com.github.javaparser.ast.modules.*;
import com.github.javaparser.ast.stmt.*;
import com.github.javaparser.ast.type.*;

/**
 * A visitor that has a return value.
 *
 * @author Julio Vilmar Gesser
 */
public interface GenericVisitor<R, A> {

    // - Compilation Unit ----------------------------------
    R visit(CompilationUnit n, A arg);

    R visit(PackageDeclaration n, A arg);

    R visit(TypeParameter n, A arg);

    R visit(LineComment n, A arg);

    R visit(BlockComment n, A arg);

    // - Body ----------------------------------------------
    R visit(ClassOrInterfaceDeclaration n, A arg);

    R visit(RecordDeclaration n, A arg);

    R visit(CompactConstructorDeclaration n, A arg);

    R visit(EnumDeclaration n, A arg);

    R visit(EnumConstantDeclaration n, A arg);

    R visit(AnnotationDeclaration n, A arg);

    R visit(AnnotationMemberDeclaration n, A arg);

    R visit(FieldDeclaration n, A arg);

    R visit(VariableDeclarator n, A arg);

    R visit(ConstructorDeclaration n, A arg);

    R visit(MethodDeclaration n, A arg);

    R visit(Parameter n, A arg);

    R visit(InitializerDeclaration n, A arg);

    R visit(JavadocComment n, A arg);

    // - Type ----------------------------------------------
    R visit(ClassOrInterfaceType n, A arg);

    R visit(PrimitiveType n, A arg);

    R visit(ArrayType n, A arg);

    R visit(ArrayCreationLevel n, A arg);

    R visit(IntersectionType n, A arg);

    R visit(UnionType n, A arg);

    R visit(VoidType n, A arg);

    R visit(WildcardType n, A arg);

    R visit(UnknownType n, A arg);

    // - Expression ----------------------------------------
    R visit(ArrayAccessExpr n, A arg);

    R visit(ArrayCreationExpr n, A arg);

    R visit(ArrayInitializerExpr n, A arg);

    R visit(AssignExpr n, A arg);

    R visit(BinaryExpr n, A arg);

    R visit(CastExpr n, A arg);

    R visit(ClassExpr n, A arg);

    R visit(ConditionalExpr n, A arg);

    R visit(EnclosedExpr n, A arg);

    R visit(FieldAccessExpr n, A arg);

    R visit(InstanceOfExpr n, A arg);

    R visit(StringLiteralExpr n, A arg);

    R visit(IntegerLiteralExpr n, A arg);

    R visit(LongLiteralExpr n, A arg);

    R visit(CharLiteralExpr n, A arg);

    R visit(DoubleLiteralExpr n, A arg);

    R visit(BooleanLiteralExpr n, A arg);

    R visit(NullLiteralExpr n, A arg);

    R visit(MethodCallExpr n, A arg);

    R visit(NameExpr n, A arg);

    R visit(ObjectCreationExpr n, A arg);

    R visit(ThisExpr n, A arg);

    R visit(SuperExpr n, A arg);

    R visit(UnaryExpr n, A arg);

    R visit(VariableDeclarationExpr n, A arg);

    R visit(MarkerAnnotationExpr n, A arg);

    R visit(SingleMemberAnnotationExpr n, A arg);

    R visit(NormalAnnotationExpr n, A arg);

    R visit(MemberValuePair n, A arg);

    // - Statements ----------------------------------------
    R visit(ExplicitConstructorInvocationStmt n, A arg);

    R visit(LocalClassDeclarationStmt n, A arg);

    R visit(LocalRecordDeclarationStmt n, A arg);

    R visit(AssertStmt n, A arg);

    R visit(BlockStmt n, A arg);

    R visit(LabeledStmt n, A arg);

    R visit(EmptyStmt n, A arg);

    R visit(ExpressionStmt n, A arg);

    R visit(SwitchStmt n, A arg);

    R visit(SwitchEntry n, A arg);

    R visit(BreakStmt n, A arg);

    R visit(ReturnStmt n, A arg);

    R visit(IfStmt n, A arg);

    R visit(WhileStmt n, A arg);

    R visit(ContinueStmt n, A arg);

    R visit(DoStmt n, A arg);

    R visit(ForEachStmt n, A arg);

    R visit(ForStmt n, A arg);

    R visit(ThrowStmt n, A arg);

    R visit(SynchronizedStmt n, A arg);

    R visit(TryStmt n, A arg);

    R visit(CatchClause n, A arg);

    R visit(LambdaExpr n, A arg);

    R visit(MethodReferenceExpr n, A arg);

    R visit(TypeExpr n, A arg);

    R visit(NodeList n, A arg);

    R visit(Name n, A arg);

    R visit(SimpleName n, A arg);

    R visit(ImportDeclaration n, A arg);

    R visit(ModuleDeclaration n, A arg);

    R visit(ModuleRequiresDirective n, A arg);

    R visit(ModuleExportsDirective n, A arg);

    R visit(ModuleProvidesDirective n, A arg);

    R visit(ModuleUsesDirective n, A arg);

    R visit(ModuleOpensDirective n, A arg);

    R visit(UnparsableStmt n, A arg);

    R visit(ReceiverParameter n, A arg);

    R visit(VarType n, A arg);

    R visit(Modifier n, A arg);

    R visit(SwitchExpr n, A arg);

    R visit(YieldStmt n, A arg);

    R visit(TextBlockLiteralExpr n, A arg);

    R visit(TypePatternExpr n, A arg);

    R visit(RecordPatternExpr n, A arg);

    R visit(JmlQuantifiedExpr n, A arg);

    R visit(JmlClauseLabel n, A arg);

    R visit(JmlExpressionStmt n, A arg);

    R visit(JmlLabelExpr n, A arg);

    R visit(JmlLetExpr n, A arg);

    R visit(JmlMultiCompareExpr n, A arg);

    R visit(JmlSimpleExprClause n, A arg);

    R visit(JmlSignalsClause n, A arg);

    R visit(JmlSignalsOnlyClause n, A arg);

    R visit(JmlUnreachableStmt n, A arg);

    R visit(JmlCallableClause n, A arg);

    R visit(JmlForallClause n, A arg);

    R visit(JmlRefiningStmt n, A arg);

    R visit(JmlClauseIf n, A arg);

    R visit(JmlClassExprDeclaration n, A arg);

    R visit(JmlClassAccessibleDeclaration n, A arg);

    R visit(JmlRepresentsDeclaration n, A arg);

    R visit(JmlContract n, A arg);

    R visit(JmlSetComprehensionExpr n, A arg);

    R visit(JmlGhostStmt n, A arg);

    R visit(JmlMethodDeclaration n, A arg);

    R visit(JmlBinaryInfixExpr n, A arg);

    R visit(JmlDocDeclaration n, A arg);

    R visit(JmlDocStmt n, A arg);

    R visit(JmlDoc n, A arg);

    R visit(JmlDocType n, A arg);

    R visit(JmlFieldDeclaration n, A arg);

    R visit(JmlOldClause n, A arg);

    R visit(JmlTypeExpr n, A arg);

    R visit(JmlMultiExprClause n, A arg);

    R visit(JmlBeginStmt n, A arg);

    R visit(JmlEndStmt n, A arg);

    R visit(JmlLabelStmt n, A arg);

    R visit(JmlMethodSignature n, A arg);
}
