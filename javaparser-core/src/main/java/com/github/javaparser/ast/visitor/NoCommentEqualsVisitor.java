/*
 * Copyright (C) 2007-2010 Júlio Vilmar Gesser.
 * Copyright (C) 2011, 2013-2023 The JavaParser Team.
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
import com.github.javaparser.ast.jml.JmlImportDeclaration;
import com.github.javaparser.ast.jml.body.*;
import com.github.javaparser.ast.jml.clauses.*;
import com.github.javaparser.ast.jml.doc.JmlDoc;
import com.github.javaparser.ast.jml.doc.JmlDocDeclaration;
import com.github.javaparser.ast.jml.doc.JmlDocStmt;
import com.github.javaparser.ast.jml.doc.JmlDocType;
import com.github.javaparser.ast.jml.expr.*;
import com.github.javaparser.ast.jml.stmt.*;
import com.github.javaparser.ast.modules.*;
import com.github.javaparser.ast.stmt.*;
import com.github.javaparser.ast.type.*;

import java.util.Optional;

public class NoCommentEqualsVisitor implements GenericVisitor<Boolean, Visitable> {

    private static final NoCommentEqualsVisitor SINGLETON = new NoCommentEqualsVisitor();

    public static boolean equals(final Node n, final Node n2) {
        return SINGLETON.nodeEquals(n, n2);
    }

    private <N extends Node> boolean nodesEquals(NodeList<N> n, NodeList<N> n2) {
        if (n == n2) {
            return true;
        }
        if (n == null || n2 == null) {
            return false;
        }
        if (n.size() != n2.size()) {
            return false;
        }
        for (int i = 0; i < n.size(); i++) {
            if (!nodeEquals(n.get(i), n2.get(i))) {
                return false;
            }
        }
        return true;
    }

    private <T extends Node> boolean nodeEquals(final T n, final T n2) {
        if (n == n2) {
            return true;
        }
        if (n == null || n2 == null) {
            return false;
        }
        if (n.getClass() != n2.getClass()) {
            return false;
        }
        return n.accept(this, n2);
    }

    private <T extends Node> boolean nodeEquals(final Optional<T> n, final Optional<T> n2) {
        return nodeEquals(n.orElse(null), n2.orElse(null));
    }

    private <T extends Node> boolean nodesEquals(final Optional<NodeList<T>> n, final Optional<NodeList<T>> n2) {
        return nodesEquals(n.orElse(null), n2.orElse(null));
    }

    private boolean objEquals(final Object n, final Object n2) {
        if (n == n2) {
            return true;
        }
        if (n == null || n2 == null) {
            return false;
        }
        return n.equals(n2);
    }

    @Override
    public Boolean visit(final CompilationUnit n, final Visitable arg) {
        final CompilationUnit n2 = (CompilationUnit) arg;
        if (!nodesEquals(n.getImports(), n2.getImports()))
            return false;
        if (!nodeEquals(n.getModule(), n2.getModule()))
            return false;
        if (!nodeEquals(n.getPackageDeclaration(), n2.getPackageDeclaration()))
            return false;
        if (!nodesEquals(n.getTypes(), n2.getTypes()))
            return false;
        return true;
    }

    @Override
    public Boolean visit(final PackageDeclaration n, final Visitable arg) {
        final PackageDeclaration n2 = (PackageDeclaration) arg;
        if (!nodesEquals(n.getAnnotations(), n2.getAnnotations()))
            return false;
        if (!nodeEquals(n.getName(), n2.getName()))
            return false;
        return true;
    }

    @Override
    public Boolean visit(final TypeParameter n, final Visitable arg) {
        final TypeParameter n2 = (TypeParameter) arg;
        if (!nodeEquals(n.getName(), n2.getName()))
            return false;
        if (!nodesEquals(n.getTypeBound(), n2.getTypeBound()))
            return false;
        if (!nodesEquals(n.getAnnotations(), n2.getAnnotations()))
            return false;
        return true;
    }

    @Override
    public Boolean visit(final LineComment n, final Visitable arg) {
        return true;
    }

    @Override
    public Boolean visit(final BlockComment n, final Visitable arg) {
        return true;
    }

    @Override
    public Boolean visit(final ClassOrInterfaceDeclaration n, final Visitable arg) {
        final ClassOrInterfaceDeclaration n2 = (ClassOrInterfaceDeclaration) arg;
        if (!nodesEquals(n.getExtendedTypes(), n2.getExtendedTypes()))
            return false;
        if (!nodesEquals(n.getImplementedTypes(), n2.getImplementedTypes()))
            return false;
        if (!objEquals(n.isInterface(), n2.isInterface()))
            return false;
        if (!nodesEquals(n.getTypeParameters(), n2.getTypeParameters()))
            return false;
        if (!nodesEquals(n.getMembers(), n2.getMembers()))
            return false;
        if (!nodesEquals(n.getModifiers(), n2.getModifiers()))
            return false;
        if (!nodeEquals(n.getName(), n2.getName()))
            return false;
        if (!nodesEquals(n.getAnnotations(), n2.getAnnotations()))
            return false;
        return true;
    }

    @Override
    public Boolean visit(final EnumDeclaration n, final Visitable arg) {
        final EnumDeclaration n2 = (EnumDeclaration) arg;
        if (!nodesEquals(n.getEntries(), n2.getEntries()))
            return false;
        if (!nodesEquals(n.getImplementedTypes(), n2.getImplementedTypes()))
            return false;
        if (!nodesEquals(n.getMembers(), n2.getMembers()))
            return false;
        if (!nodesEquals(n.getModifiers(), n2.getModifiers()))
            return false;
        if (!nodeEquals(n.getName(), n2.getName()))
            return false;
        if (!nodesEquals(n.getAnnotations(), n2.getAnnotations()))
            return false;
        return true;
    }

    @Override
    public Boolean visit(final EnumConstantDeclaration n, final Visitable arg) {
        final EnumConstantDeclaration n2 = (EnumConstantDeclaration) arg;
        if (!nodesEquals(n.getArguments(), n2.getArguments()))
            return false;
        if (!nodesEquals(n.getClassBody(), n2.getClassBody()))
            return false;
        if (!nodeEquals(n.getName(), n2.getName()))
            return false;
        if (!nodesEquals(n.getAnnotations(), n2.getAnnotations()))
            return false;
        return true;
    }

    @Override
    public Boolean visit(final AnnotationDeclaration n, final Visitable arg) {
        final AnnotationDeclaration n2 = (AnnotationDeclaration) arg;
        if (!nodesEquals(n.getMembers(), n2.getMembers()))
            return false;
        if (!nodesEquals(n.getModifiers(), n2.getModifiers()))
            return false;
        if (!nodeEquals(n.getName(), n2.getName()))
            return false;
        if (!nodesEquals(n.getAnnotations(), n2.getAnnotations()))
            return false;
        return true;
    }

    @Override
    public Boolean visit(final AnnotationMemberDeclaration n, final Visitable arg) {
        final AnnotationMemberDeclaration n2 = (AnnotationMemberDeclaration) arg;
        if (!nodeEquals(n.getDefaultValue(), n2.getDefaultValue()))
            return false;
        if (!nodesEquals(n.getModifiers(), n2.getModifiers()))
            return false;
        if (!nodeEquals(n.getName(), n2.getName()))
            return false;
        if (!nodeEquals(n.getType(), n2.getType()))
            return false;
        if (!nodesEquals(n.getAnnotations(), n2.getAnnotations()))
            return false;
        return true;
    }

    @Override
    public Boolean visit(final FieldDeclaration n, final Visitable arg) {
        final FieldDeclaration n2 = (FieldDeclaration) arg;
        if (!nodesEquals(n.getModifiers(), n2.getModifiers()))
            return false;
        if (!nodesEquals(n.getVariables(), n2.getVariables()))
            return false;
        if (!nodesEquals(n.getAnnotations(), n2.getAnnotations()))
            return false;
        return true;
    }

    @Override
    public Boolean visit(final VariableDeclarator n, final Visitable arg) {
        final VariableDeclarator n2 = (VariableDeclarator) arg;
        if (!nodeEquals(n.getInitializer(), n2.getInitializer()))
            return false;
        if (!nodeEquals(n.getName(), n2.getName()))
            return false;
        if (!nodeEquals(n.getType(), n2.getType()))
            return false;
        return true;
    }

    @Override
    public Boolean visit(final ConstructorDeclaration n, final Visitable arg) {
        final ConstructorDeclaration n2 = (ConstructorDeclaration) arg;
        if (!nodeEquals(n.getBody(), n2.getBody()))
            return false;
        if (!nodesEquals(n.getContracts(), n2.getContracts()))
            return false;
        if (!nodesEquals(n.getModifiers(), n2.getModifiers()))
            return false;
        if (!nodeEquals(n.getName(), n2.getName()))
            return false;
        if (!nodesEquals(n.getParameters(), n2.getParameters()))
            return false;
        if (!nodeEquals(n.getReceiverParameter(), n2.getReceiverParameter()))
            return false;
        if (!nodesEquals(n.getThrownExceptions(), n2.getThrownExceptions()))
            return false;
        if (!nodesEquals(n.getTypeParameters(), n2.getTypeParameters()))
            return false;
        if (!nodesEquals(n.getAnnotations(), n2.getAnnotations()))
            return false;
        return true;
    }

    @Override
    public Boolean visit(final MethodDeclaration n, final Visitable arg) {
        final MethodDeclaration n2 = (MethodDeclaration) arg;
        if (!nodeEquals(n.getBody(), n2.getBody()))
            return false;
        if (!nodeEquals(n.getType(), n2.getType()))
            return false;
        if (!nodesEquals(n.getContracts(), n2.getContracts()))
            return false;
        if (!nodesEquals(n.getModifiers(), n2.getModifiers()))
            return false;
        if (!nodeEquals(n.getName(), n2.getName()))
            return false;
        if (!nodesEquals(n.getParameters(), n2.getParameters()))
            return false;
        if (!nodeEquals(n.getReceiverParameter(), n2.getReceiverParameter()))
            return false;
        if (!nodesEquals(n.getThrownExceptions(), n2.getThrownExceptions()))
            return false;
        if (!nodesEquals(n.getTypeParameters(), n2.getTypeParameters()))
            return false;
        if (!nodesEquals(n.getAnnotations(), n2.getAnnotations()))
            return false;
        return true;
    }

    @Override
    public Boolean visit(final Parameter n, final Visitable arg) {
        final Parameter n2 = (Parameter) arg;
        if (!nodesEquals(n.getAnnotations(), n2.getAnnotations()))
            return false;
        if (!objEquals(n.isVarArgs(), n2.isVarArgs()))
            return false;
        if (!nodesEquals(n.getModifiers(), n2.getModifiers()))
            return false;
        if (!nodeEquals(n.getName(), n2.getName()))
            return false;
        if (!nodeEquals(n.getType(), n2.getType()))
            return false;
        if (!nodesEquals(n.getVarArgsAnnotations(), n2.getVarArgsAnnotations()))
            return false;
        return true;
    }

    @Override
    public Boolean visit(final InitializerDeclaration n, final Visitable arg) {
        final InitializerDeclaration n2 = (InitializerDeclaration) arg;
        if (!nodeEquals(n.getBody(), n2.getBody()))
            return false;
        if (!objEquals(n.isStatic(), n2.isStatic()))
            return false;
        if (!nodesEquals(n.getAnnotations(), n2.getAnnotations()))
            return false;
        return true;
    }

    @Override
    public Boolean visit(final JavadocComment n, final Visitable arg) {
        return true;
    }

    @Override
    public Boolean visit(final ClassOrInterfaceType n, final Visitable arg) {
        final ClassOrInterfaceType n2 = (ClassOrInterfaceType) arg;
        if (!nodeEquals(n.getName(), n2.getName()))
            return false;
        if (!nodeEquals(n.getScope(), n2.getScope()))
            return false;
        if (!nodesEquals(n.getTypeArguments(), n2.getTypeArguments()))
            return false;
        if (!nodesEquals(n.getAnnotations(), n2.getAnnotations()))
            return false;
        return true;
    }

    @Override
    public Boolean visit(final PrimitiveType n, final Visitable arg) {
        final PrimitiveType n2 = (PrimitiveType) arg;
        if (!objEquals(n.getType(), n2.getType()))
            return false;
        if (!nodesEquals(n.getAnnotations(), n2.getAnnotations()))
            return false;
        return true;
    }

    @Override
    public Boolean visit(final ArrayType n, final Visitable arg) {
        final ArrayType n2 = (ArrayType) arg;
        if (!nodeEquals(n.getComponentType(), n2.getComponentType()))
            return false;
        if (!objEquals(n.getOrigin(), n2.getOrigin()))
            return false;
        if (!nodesEquals(n.getAnnotations(), n2.getAnnotations()))
            return false;
        return true;
    }

    @Override
    public Boolean visit(final ArrayCreationLevel n, final Visitable arg) {
        final ArrayCreationLevel n2 = (ArrayCreationLevel) arg;
        if (!nodesEquals(n.getAnnotations(), n2.getAnnotations()))
            return false;
        if (!nodeEquals(n.getDimension(), n2.getDimension()))
            return false;
        return true;
    }

    @Override
    public Boolean visit(final IntersectionType n, final Visitable arg) {
        final IntersectionType n2 = (IntersectionType) arg;
        if (!nodesEquals(n.getElements(), n2.getElements()))
            return false;
        if (!nodesEquals(n.getAnnotations(), n2.getAnnotations()))
            return false;
        return true;
    }

    @Override
    public Boolean visit(final UnionType n, final Visitable arg) {
        final UnionType n2 = (UnionType) arg;
        if (!nodesEquals(n.getElements(), n2.getElements()))
            return false;
        if (!nodesEquals(n.getAnnotations(), n2.getAnnotations()))
            return false;
        return true;
    }

    @Override
    public Boolean visit(final VoidType n, final Visitable arg) {
        final VoidType n2 = (VoidType) arg;
        if (!nodesEquals(n.getAnnotations(), n2.getAnnotations()))
            return false;
        return true;
    }

    @Override
    public Boolean visit(final WildcardType n, final Visitable arg) {
        final WildcardType n2 = (WildcardType) arg;
        if (!nodeEquals(n.getExtendedType(), n2.getExtendedType()))
            return false;
        if (!nodeEquals(n.getSuperType(), n2.getSuperType()))
            return false;
        if (!nodesEquals(n.getAnnotations(), n2.getAnnotations()))
            return false;
        return true;
    }

    @Override
    public Boolean visit(final UnknownType n, final Visitable arg) {
        final UnknownType n2 = (UnknownType) arg;
        if (!nodesEquals(n.getAnnotations(), n2.getAnnotations()))
            return false;
        return true;
    }

    @Override
    public Boolean visit(final ArrayAccessExpr n, final Visitable arg) {
        final ArrayAccessExpr n2 = (ArrayAccessExpr) arg;
        if (!nodeEquals(n.getIndex(), n2.getIndex()))
            return false;
        if (!nodeEquals(n.getName(), n2.getName()))
            return false;
        return true;
    }

    @Override
    public Boolean visit(final ArrayCreationExpr n, final Visitable arg) {
        final ArrayCreationExpr n2 = (ArrayCreationExpr) arg;
        if (!nodeEquals(n.getElementType(), n2.getElementType()))
            return false;
        if (!nodeEquals(n.getInitializer(), n2.getInitializer()))
            return false;
        if (!nodesEquals(n.getLevels(), n2.getLevels()))
            return false;
        return true;
    }

    @Override
    public Boolean visit(final ArrayInitializerExpr n, final Visitable arg) {
        final ArrayInitializerExpr n2 = (ArrayInitializerExpr) arg;
        if (!nodesEquals(n.getValues(), n2.getValues()))
            return false;
        return true;
    }

    @Override
    public Boolean visit(final AssignExpr n, final Visitable arg) {
        final AssignExpr n2 = (AssignExpr) arg;
        if (!objEquals(n.getOperator(), n2.getOperator()))
            return false;
        if (!nodeEquals(n.getTarget(), n2.getTarget()))
            return false;
        if (!nodeEquals(n.getValue(), n2.getValue()))
            return false;
        return true;
    }

    @Override
    public Boolean visit(final BinaryExpr n, final Visitable arg) {
        final BinaryExpr n2 = (BinaryExpr) arg;
        if (!nodeEquals(n.getLeft(), n2.getLeft()))
            return false;
        if (!objEquals(n.getOperator(), n2.getOperator()))
            return false;
        if (!nodeEquals(n.getRight(), n2.getRight()))
            return false;
        return true;
    }

    @Override
    public Boolean visit(final CastExpr n, final Visitable arg) {
        final CastExpr n2 = (CastExpr) arg;
        if (!nodeEquals(n.getExpression(), n2.getExpression()))
            return false;
        if (!nodeEquals(n.getType(), n2.getType()))
            return false;
        return true;
    }

    @Override
    public Boolean visit(final ClassExpr n, final Visitable arg) {
        final ClassExpr n2 = (ClassExpr) arg;
        if (!nodeEquals(n.getType(), n2.getType()))
            return false;
        return true;
    }

    @Override
    public Boolean visit(final ConditionalExpr n, final Visitable arg) {
        final ConditionalExpr n2 = (ConditionalExpr) arg;
        if (!nodeEquals(n.getCondition(), n2.getCondition()))
            return false;
        if (!nodeEquals(n.getElseExpr(), n2.getElseExpr()))
            return false;
        if (!nodeEquals(n.getThenExpr(), n2.getThenExpr()))
            return false;
        return true;
    }

    @Override
    public Boolean visit(final EnclosedExpr n, final Visitable arg) {
        final EnclosedExpr n2 = (EnclosedExpr) arg;
        if (!nodeEquals(n.getInner(), n2.getInner()))
            return false;
        return true;
    }

    @Override
    public Boolean visit(final FieldAccessExpr n, final Visitable arg) {
        final FieldAccessExpr n2 = (FieldAccessExpr) arg;
        if (!nodeEquals(n.getName(), n2.getName()))
            return false;
        if (!nodeEquals(n.getScope(), n2.getScope()))
            return false;
        if (!nodesEquals(n.getTypeArguments(), n2.getTypeArguments()))
            return false;
        return true;
    }

    @Override
    public Boolean visit(final InstanceOfExpr n, final Visitable arg) {
        final InstanceOfExpr n2 = (InstanceOfExpr) arg;
        if (!nodeEquals(n.getExpression(), n2.getExpression()))
            return false;
        if (!nodeEquals(n.getPattern(), n2.getPattern()))
            return false;
        if (!nodeEquals(n.getType(), n2.getType()))
            return false;
        return true;
    }

    @Override
    public Boolean visit(final StringLiteralExpr n, final Visitable arg) {
        final StringLiteralExpr n2 = (StringLiteralExpr) arg;
        if (!objEquals(n.getValue(), n2.getValue()))
            return false;
        return true;
    }

    @Override
    public Boolean visit(final IntegerLiteralExpr n, final Visitable arg) {
        final IntegerLiteralExpr n2 = (IntegerLiteralExpr) arg;
        if (!objEquals(n.getValue(), n2.getValue()))
            return false;
        return true;
    }

    @Override
    public Boolean visit(final LongLiteralExpr n, final Visitable arg) {
        final LongLiteralExpr n2 = (LongLiteralExpr) arg;
        if (!objEquals(n.getValue(), n2.getValue()))
            return false;
        return true;
    }

    @Override
    public Boolean visit(final CharLiteralExpr n, final Visitable arg) {
        final CharLiteralExpr n2 = (CharLiteralExpr) arg;
        if (!objEquals(n.getValue(), n2.getValue()))
            return false;
        return true;
    }

    @Override
    public Boolean visit(final DoubleLiteralExpr n, final Visitable arg) {
        final DoubleLiteralExpr n2 = (DoubleLiteralExpr) arg;
        if (!objEquals(n.getValue(), n2.getValue()))
            return false;
        return true;
    }

    @Override
    public Boolean visit(final BooleanLiteralExpr n, final Visitable arg) {
        final BooleanLiteralExpr n2 = (BooleanLiteralExpr) arg;
        if (!objEquals(n.isValue(), n2.isValue()))
            return false;
        return true;
    }

    @Override
    public Boolean visit(final NullLiteralExpr n, final Visitable arg) {
        return true;
    }

    @Override
    public Boolean visit(final MethodCallExpr n, final Visitable arg) {
        final MethodCallExpr n2 = (MethodCallExpr) arg;
        if (!nodesEquals(n.getArguments(), n2.getArguments()))
            return false;
        if (!nodeEquals(n.getName(), n2.getName()))
            return false;
        if (!nodeEquals(n.getScope(), n2.getScope()))
            return false;
        if (!nodesEquals(n.getTypeArguments(), n2.getTypeArguments()))
            return false;
        return true;
    }

    @Override
    public Boolean visit(final NameExpr n, final Visitable arg) {
        final NameExpr n2 = (NameExpr) arg;
        if (!nodeEquals(n.getName(), n2.getName()))
            return false;
        return true;
    }

    @Override
    public Boolean visit(final ObjectCreationExpr n, final Visitable arg) {
        final ObjectCreationExpr n2 = (ObjectCreationExpr) arg;
        if (!nodesEquals(n.getAnonymousClassBody(), n2.getAnonymousClassBody()))
            return false;
        if (!nodesEquals(n.getArguments(), n2.getArguments()))
            return false;
        if (!nodeEquals(n.getScope(), n2.getScope()))
            return false;
        if (!nodeEquals(n.getType(), n2.getType()))
            return false;
        if (!nodesEquals(n.getTypeArguments(), n2.getTypeArguments()))
            return false;
        return true;
    }

    @Override
    public Boolean visit(final Name n, final Visitable arg) {
        final Name n2 = (Name) arg;
        if (!objEquals(n.getIdentifier(), n2.getIdentifier()))
            return false;
        if (!nodeEquals(n.getQualifier(), n2.getQualifier()))
            return false;
        return true;
    }

    @Override
    public Boolean visit(final SimpleName n, final Visitable arg) {
        final SimpleName n2 = (SimpleName) arg;
        if (!objEquals(n.getIdentifier(), n2.getIdentifier()))
            return false;
        return true;
    }

    @Override
    public Boolean visit(final ThisExpr n, final Visitable arg) {
        final ThisExpr n2 = (ThisExpr) arg;
        if (!nodeEquals(n.getTypeName(), n2.getTypeName()))
            return false;
        return true;
    }

    @Override
    public Boolean visit(final SuperExpr n, final Visitable arg) {
        final SuperExpr n2 = (SuperExpr) arg;
        if (!nodeEquals(n.getTypeName(), n2.getTypeName()))
            return false;
        return true;
    }

    @Override
    public Boolean visit(final UnaryExpr n, final Visitable arg) {
        final UnaryExpr n2 = (UnaryExpr) arg;
        if (!nodeEquals(n.getExpression(), n2.getExpression()))
            return false;
        if (!objEquals(n.getOperator(), n2.getOperator()))
            return false;
        return true;
    }

    @Override
    public Boolean visit(final VariableDeclarationExpr n, final Visitable arg) {
        final VariableDeclarationExpr n2 = (VariableDeclarationExpr) arg;
        if (!nodesEquals(n.getAnnotations(), n2.getAnnotations()))
            return false;
        if (!nodesEquals(n.getModifiers(), n2.getModifiers()))
            return false;
        if (!nodesEquals(n.getVariables(), n2.getVariables()))
            return false;
        return true;
    }

    @Override
    public Boolean visit(final MarkerAnnotationExpr n, final Visitable arg) {
        final MarkerAnnotationExpr n2 = (MarkerAnnotationExpr) arg;
        if (!nodeEquals(n.getName(), n2.getName()))
            return false;
        return true;
    }

    @Override
    public Boolean visit(final SingleMemberAnnotationExpr n, final Visitable arg) {
        final SingleMemberAnnotationExpr n2 = (SingleMemberAnnotationExpr) arg;
        if (!nodeEquals(n.getMemberValue(), n2.getMemberValue()))
            return false;
        if (!nodeEquals(n.getName(), n2.getName()))
            return false;
        return true;
    }

    @Override
    public Boolean visit(final NormalAnnotationExpr n, final Visitable arg) {
        final NormalAnnotationExpr n2 = (NormalAnnotationExpr) arg;
        if (!nodesEquals(n.getPairs(), n2.getPairs()))
            return false;
        if (!nodeEquals(n.getName(), n2.getName()))
            return false;
        return true;
    }

    @Override
    public Boolean visit(final MemberValuePair n, final Visitable arg) {
        final MemberValuePair n2 = (MemberValuePair) arg;
        if (!nodeEquals(n.getName(), n2.getName()))
            return false;
        if (!nodeEquals(n.getValue(), n2.getValue()))
            return false;
        return true;
    }

    @Override
    public Boolean visit(final ExplicitConstructorInvocationStmt n, final Visitable arg) {
        final ExplicitConstructorInvocationStmt n2 = (ExplicitConstructorInvocationStmt) arg;
        if (!nodesEquals(n.getArguments(), n2.getArguments()))
            return false;
        if (!nodeEquals(n.getExpression(), n2.getExpression()))
            return false;
        if (!objEquals(n.isThis(), n2.isThis()))
            return false;
        if (!nodesEquals(n.getTypeArguments(), n2.getTypeArguments()))
            return false;
        return true;
    }

    @Override
    public Boolean visit(final LocalClassDeclarationStmt n, final Visitable arg) {
        final LocalClassDeclarationStmt n2 = (LocalClassDeclarationStmt) arg;
        if (!nodeEquals(n.getClassDeclaration(), n2.getClassDeclaration()))
            return false;
        return true;
    }

    @Override
    public Boolean visit(final LocalRecordDeclarationStmt n, final Visitable arg) {
        final LocalRecordDeclarationStmt n2 = (LocalRecordDeclarationStmt) arg;
        if (!nodeEquals(n.getRecordDeclaration(), n2.getRecordDeclaration()))
            return false;
        return true;
    }

    @Override
    public Boolean visit(final AssertStmt n, final Visitable arg) {
        final AssertStmt n2 = (AssertStmt) arg;
        if (!nodeEquals(n.getCheck(), n2.getCheck()))
            return false;
        if (!nodeEquals(n.getMessage(), n2.getMessage()))
            return false;
        return true;
    }

    @Override
    public Boolean visit(final BlockStmt n, final Visitable arg) {
        final BlockStmt n2 = (BlockStmt) arg;
        if (!nodesEquals(n.getContracts(), n2.getContracts()))
            return false;
        if (!nodesEquals(n.getStatements(), n2.getStatements()))
            return false;
        return true;
    }

    @Override
    public Boolean visit(final LabeledStmt n, final Visitable arg) {
        final LabeledStmt n2 = (LabeledStmt) arg;
        if (!nodeEquals(n.getLabel(), n2.getLabel()))
            return false;
        if (!nodeEquals(n.getStatement(), n2.getStatement()))
            return false;
        return true;
    }

    @Override
    public Boolean visit(final EmptyStmt n, final Visitable arg) {
        return true;
    }

    @Override
    public Boolean visit(final ExpressionStmt n, final Visitable arg) {
        final ExpressionStmt n2 = (ExpressionStmt) arg;
        if (!nodeEquals(n.getExpression(), n2.getExpression()))
            return false;
        return true;
    }

    @Override
    public Boolean visit(final SwitchStmt n, final Visitable arg) {
        final SwitchStmt n2 = (SwitchStmt) arg;
        if (!nodesEquals(n.getEntries(), n2.getEntries()))
            return false;
        if (!nodeEquals(n.getSelector(), n2.getSelector()))
            return false;
        return true;
    }

    @Override
    public Boolean visit(final SwitchEntry n, final Visitable arg) {
        final SwitchEntry n2 = (SwitchEntry) arg;
        if (!nodesEquals(n.getLabels(), n2.getLabels()))
            return false;
        if (!nodesEquals(n.getStatements(), n2.getStatements()))
            return false;
        if (!objEquals(n.getType(), n2.getType()))
            return false;
        return true;
    }

    @Override
    public Boolean visit(final BreakStmt n, final Visitable arg) {
        final BreakStmt n2 = (BreakStmt) arg;
        if (!nodeEquals(n.getLabel(), n2.getLabel()))
            return false;
        return true;
    }

    @Override
    public Boolean visit(final ReturnStmt n, final Visitable arg) {
        final ReturnStmt n2 = (ReturnStmt) arg;
        if (!nodeEquals(n.getExpression(), n2.getExpression()))
            return false;
        return true;
    }

    @Override
    public Boolean visit(final IfStmt n, final Visitable arg) {
        final IfStmt n2 = (IfStmt) arg;
        if (!nodeEquals(n.getCondition(), n2.getCondition()))
            return false;
        if (!nodeEquals(n.getElseStmt(), n2.getElseStmt()))
            return false;
        if (!nodeEquals(n.getThenStmt(), n2.getThenStmt()))
            return false;
        return true;
    }

    @Override
    public Boolean visit(final WhileStmt n, final Visitable arg) {
        final WhileStmt n2 = (WhileStmt) arg;
        if (!nodeEquals(n.getBody(), n2.getBody()))
            return false;
        if (!nodeEquals(n.getCondition(), n2.getCondition()))
            return false;
        if (!nodesEquals(n.getContracts(), n2.getContracts()))
            return false;
        return true;
    }

    @Override
    public Boolean visit(final ContinueStmt n, final Visitable arg) {
        final ContinueStmt n2 = (ContinueStmt) arg;
        if (!nodeEquals(n.getLabel(), n2.getLabel()))
            return false;
        return true;
    }

    @Override
    public Boolean visit(final DoStmt n, final Visitable arg) {
        final DoStmt n2 = (DoStmt) arg;
        if (!nodeEquals(n.getBody(), n2.getBody()))
            return false;
        if (!nodeEquals(n.getCondition(), n2.getCondition()))
            return false;
        if (!nodesEquals(n.getContracts(), n2.getContracts()))
            return false;
        return true;
    }

    @Override
    public Boolean visit(final ForEachStmt n, final Visitable arg) {
        final ForEachStmt n2 = (ForEachStmt) arg;
        if (!nodeEquals(n.getBody(), n2.getBody()))
            return false;
        if (!nodesEquals(n.getContracts(), n2.getContracts()))
            return false;
        if (!nodeEquals(n.getIterable(), n2.getIterable()))
            return false;
        if (!nodeEquals(n.getVariable(), n2.getVariable()))
            return false;
        return true;
    }

    @Override
    public Boolean visit(final ForStmt n, final Visitable arg) {
        final ForStmt n2 = (ForStmt) arg;
        if (!nodeEquals(n.getBody(), n2.getBody()))
            return false;
        if (!nodeEquals(n.getCompare(), n2.getCompare()))
            return false;
        if (!nodesEquals(n.getContracts(), n2.getContracts()))
            return false;
        if (!nodesEquals(n.getInitialization(), n2.getInitialization()))
            return false;
        if (!nodesEquals(n.getUpdate(), n2.getUpdate()))
            return false;
        return true;
    }

    @Override
    public Boolean visit(final ThrowStmt n, final Visitable arg) {
        final ThrowStmt n2 = (ThrowStmt) arg;
        if (!nodeEquals(n.getExpression(), n2.getExpression()))
            return false;
        return true;
    }

    @Override
    public Boolean visit(final SynchronizedStmt n, final Visitable arg) {
        final SynchronizedStmt n2 = (SynchronizedStmt) arg;
        if (!nodeEquals(n.getBody(), n2.getBody()))
            return false;
        if (!nodeEquals(n.getExpression(), n2.getExpression()))
            return false;
        return true;
    }

    @Override
    public Boolean visit(final TryStmt n, final Visitable arg) {
        final TryStmt n2 = (TryStmt) arg;
        if (!nodesEquals(n.getCatchClauses(), n2.getCatchClauses()))
            return false;
        if (!nodeEquals(n.getFinallyBlock(), n2.getFinallyBlock()))
            return false;
        if (!nodesEquals(n.getResources(), n2.getResources()))
            return false;
        if (!nodeEquals(n.getTryBlock(), n2.getTryBlock()))
            return false;
        return true;
    }

    @Override
    public Boolean visit(final CatchClause n, final Visitable arg) {
        final CatchClause n2 = (CatchClause) arg;
        if (!nodeEquals(n.getBody(), n2.getBody()))
            return false;
        if (!nodeEquals(n.getParameter(), n2.getParameter()))
            return false;
        return true;
    }

    @Override
    public Boolean visit(final LambdaExpr n, final Visitable arg) {
        final LambdaExpr n2 = (LambdaExpr) arg;
        if (!nodeEquals(n.getBody(), n2.getBody()))
            return false;
        if (!objEquals(n.isEnclosingParameters(), n2.isEnclosingParameters()))
            return false;
        if (!nodesEquals(n.getParameters(), n2.getParameters()))
            return false;
        return true;
    }

    @Override
    public Boolean visit(final MethodReferenceExpr n, final Visitable arg) {
        final MethodReferenceExpr n2 = (MethodReferenceExpr) arg;
        if (!objEquals(n.getIdentifier(), n2.getIdentifier()))
            return false;
        if (!nodeEquals(n.getScope(), n2.getScope()))
            return false;
        if (!nodesEquals(n.getTypeArguments(), n2.getTypeArguments()))
            return false;
        return true;
    }

    @Override
    public Boolean visit(final TypeExpr n, final Visitable arg) {
        final TypeExpr n2 = (TypeExpr) arg;
        if (!nodeEquals(n.getType(), n2.getType()))
            return false;
        return true;
    }

    @Override
    public Boolean visit(final ImportDeclaration n, final Visitable arg) {
        final ImportDeclaration n2 = (ImportDeclaration) arg;
        if (!objEquals(n.isAsterisk(), n2.isAsterisk()))
            return false;
        if (!objEquals(n.isJmlModel(), n2.isJmlModel()))
            return false;
        if (!objEquals(n.isStatic(), n2.isStatic()))
            return false;
        if (!nodeEquals(n.getName(), n2.getName()))
            return false;
        return true;
    }

    @Override
    public Boolean visit(NodeList n, Visitable arg) {
        return nodesEquals((NodeList<Node>) n, (NodeList<Node>) arg);
    }

    @Override
    public Boolean visit(final ModuleDeclaration n, final Visitable arg) {
        final ModuleDeclaration n2 = (ModuleDeclaration) arg;
        if (!nodesEquals(n.getAnnotations(), n2.getAnnotations()))
            return false;
        if (!nodesEquals(n.getDirectives(), n2.getDirectives()))
            return false;
        if (!objEquals(n.isOpen(), n2.isOpen()))
            return false;
        if (!nodeEquals(n.getName(), n2.getName()))
            return false;
        return true;
    }

    @Override
    public Boolean visit(final ModuleRequiresDirective n, final Visitable arg) {
        final ModuleRequiresDirective n2 = (ModuleRequiresDirective) arg;
        if (!nodesEquals(n.getModifiers(), n2.getModifiers()))
            return false;
        if (!nodeEquals(n.getName(), n2.getName()))
            return false;
        return true;
    }

    @Override()
    public Boolean visit(final ModuleExportsDirective n, final Visitable arg) {
        final ModuleExportsDirective n2 = (ModuleExportsDirective) arg;
        if (!nodesEquals(n.getModuleNames(), n2.getModuleNames()))
            return false;
        if (!nodeEquals(n.getName(), n2.getName()))
            return false;
        return true;
    }

    @Override()
    public Boolean visit(final ModuleProvidesDirective n, final Visitable arg) {
        final ModuleProvidesDirective n2 = (ModuleProvidesDirective) arg;
        if (!nodeEquals(n.getName(), n2.getName()))
            return false;
        if (!nodesEquals(n.getWith(), n2.getWith()))
            return false;
        return true;
    }

    @Override()
    public Boolean visit(final ModuleUsesDirective n, final Visitable arg) {
        final ModuleUsesDirective n2 = (ModuleUsesDirective) arg;
        if (!nodeEquals(n.getName(), n2.getName()))
            return false;
        return true;
    }

    @Override
    public Boolean visit(final ModuleOpensDirective n, final Visitable arg) {
        final ModuleOpensDirective n2 = (ModuleOpensDirective) arg;
        if (!nodesEquals(n.getModuleNames(), n2.getModuleNames()))
            return false;
        if (!nodeEquals(n.getName(), n2.getName()))
            return false;
        return true;
    }

    @Override
    public Boolean visit(final UnparsableStmt n, final Visitable arg) {
        return true;
    }

    @Override
    public Boolean visit(final ReceiverParameter n, final Visitable arg) {
        final ReceiverParameter n2 = (ReceiverParameter) arg;
        if (!nodesEquals(n.getAnnotations(), n2.getAnnotations()))
            return false;
        if (!nodeEquals(n.getName(), n2.getName()))
            return false;
        if (!nodeEquals(n.getType(), n2.getType()))
            return false;
        return true;
    }

    @Override
    public Boolean visit(final VarType n, final Visitable arg) {
        final VarType n2 = (VarType) arg;
        if (!nodesEquals(n.getAnnotations(), n2.getAnnotations()))
            return false;
        return true;
    }

    @Override
    public Boolean visit(final Modifier n, final Visitable arg) {
        final Modifier n2 = (Modifier) arg;
        if (!objEquals(n.getKeyword(), n2.getKeyword()))
            return false;
        return true;
    }

    @Override
    public Boolean visit(final SwitchExpr n, final Visitable arg) {
        final SwitchExpr n2 = (SwitchExpr) arg;
        if (!nodesEquals(n.getEntries(), n2.getEntries()))
            return false;
        if (!nodeEquals(n.getSelector(), n2.getSelector()))
            return false;
        return true;
    }

    @Override
    public Boolean visit(final YieldStmt n, final Visitable arg) {
        final YieldStmt n2 = (YieldStmt) arg;
        if (!nodeEquals(n.getExpression(), n2.getExpression()))
            return false;
        return true;
    }

    @Override
    public Boolean visit(final TextBlockLiteralExpr n, final Visitable arg) {
        final TextBlockLiteralExpr n2 = (TextBlockLiteralExpr) arg;
        if (!objEquals(n.getValue(), n2.getValue()))
            return false;
        return true;
    }

    @Override
    public Boolean visit(final PatternExpr n, final Visitable arg) {
        final PatternExpr n2 = (PatternExpr) arg;
        if (!nodesEquals(n.getModifiers(), n2.getModifiers()))
            return false;
        if (!nodeEquals(n.getName(), n2.getName()))
            return false;
        if (!nodeEquals(n.getType(), n2.getType()))
            return false;
        return true;
    }

    @Override
    public Boolean visit(final JmlAccessibleClause n, final Visitable arg) {
        final JmlAccessibleClause n2 = (JmlAccessibleClause) arg;
        if (!nodesEquals(n.getExpressions(), n2.getExpressions()))
            return false;
        if (!nodesEquals(n.getHeaps(), n2.getHeaps()))
            return false;
        if (!nodeEquals(n.getMeasuredBy(), n2.getMeasuredBy()))
            return false;
        return objEquals(n.getKind(), n2.getKind());
    }

    @Override
    public Boolean visit(final JmlClauseLabel n, final Visitable arg) {
        final JmlClauseLabel n2 = (JmlClauseLabel) arg;
        if (!nodeEquals(n.getExpr(), n2.getExpr()))
            return false;
        if (!objEquals(n.getKind(), n2.getKind()))
            return false;
        if (!nodeEquals(n.getLabel(), n2.getLabel()))
            return false;
        if (!nodeEquals(n.getName(), n2.getName()))
            return false;
        return true;
    }

    @Override
    public Boolean visit(final JmlExpressionStmt n, final Visitable arg) {
        final JmlExpressionStmt n2 = (JmlExpressionStmt) arg;
        if (!nodeEquals(n.getExpression(), n2.getExpression()))
            return false;
        if (!nodesEquals(n.getJmlTags(), n2.getJmlTags()))
            return false;
        if (!objEquals(n.getKind(), n2.getKind()))
            return false;
        return true;
    }

    @Override
    public Boolean visit(final JmlQuantifiedExpr n, final Visitable arg) {
        final JmlQuantifiedExpr n2 = (JmlQuantifiedExpr) arg;
        if (!objEquals(n.getBinder(), n2.getBinder()))
            return false;
        if (!nodesEquals(n.getExpressions(), n2.getExpressions()))
            return false;
        if (!nodesEquals(n.getVariables(), n2.getVariables()))
            return false;
        return true;
    }

    @Override
    public Boolean visit(final JmlLabelExpr n, final Visitable arg) {
        final JmlLabelExpr n2 = (JmlLabelExpr) arg;
        if (!nodeEquals(n.getExpression(), n2.getExpression()))
            return false;
        if (!objEquals(n.getKind(), n2.getKind()))
            return false;
        if (!nodeEquals(n.getLabel(), n2.getLabel()))
            return false;
        return true;
    }

    @Override
    public Boolean visit(final JmlLetExpr n, final Visitable arg) {
        final JmlLetExpr n2 = (JmlLetExpr) arg;
        if (!nodeEquals(n.getBody(), n2.getBody()))
            return false;
        if (!nodeEquals(n.getVariables(), n2.getVariables()))
            return false;
        return true;
    }

    @Override
    public Boolean visit(final JmlMultiCompareExpr n, final Visitable arg) {
        final JmlMultiCompareExpr n2 = (JmlMultiCompareExpr) arg;
        if (!nodesEquals(n.getExpressions(), n2.getExpressions()))
            return false;
        if (!objEquals(n.getOperators(), n2.getOperators()))
            return false;
        return true;
    }

    @Override
    public Boolean visit(final JmlSimpleExprClause n, final Visitable arg) {
        final JmlSimpleExprClause n2 = (JmlSimpleExprClause) arg;
        if (!nodeEquals(n.getExpression(), n2.getExpression()))
            return false;
        if (!nodesEquals(n.getHeaps(), n2.getHeaps()))
            return false;
        if (!objEquals(n.getKind(), n2.getKind()))
            return false;
        if (!nodeEquals(n.getName(), n2.getName()))
            return false;
        return true;
    }

    @Override
    public Boolean visit(final JmlSignalsClause n, final Visitable arg) {
        final JmlSignalsClause n2 = (JmlSignalsClause) arg;
        if (!nodeEquals(n.getExpression(), n2.getExpression()))
            return false;
        if (!nodeEquals(n.getParameter(), n2.getParameter()))
            return false;
        if (!nodeEquals(n.getName(), n2.getName()))
            return false;
        return true;
    }

    @Override
    public Boolean visit(final JmlSignalsOnlyClause n, final Visitable arg) {
        final JmlSignalsOnlyClause n2 = (JmlSignalsOnlyClause) arg;
        if (!nodesEquals(n.getTypes(), n2.getTypes()))
            return false;
        if (!nodeEquals(n.getName(), n2.getName()))
            return false;
        return true;
    }

    @Override
    public Boolean visit(final JmlUnreachableStmt n, final Visitable arg) {
        final JmlUnreachableStmt n2 = (JmlUnreachableStmt) arg;
        if (!nodesEquals(n.getJmlTags(), n2.getJmlTags()))
            return false;
        return true;
    }

    @Override
    public Boolean visit(final JmlCallableClause n, final Visitable arg) {
        final JmlCallableClause n2 = (JmlCallableClause) arg;
        if (!nodeEquals(n.getName(), n2.getName()))
            return false;
        return true;
    }

    @Override
    public Boolean visit(final JmlCapturesClause n, final Visitable arg) {
        final JmlCapturesClause n2 = (JmlCapturesClause) arg;
        return objEquals(n.getKind(), n2.getKind());
    }

    @Override
    public Boolean visit(final JmlForallClause n, final Visitable arg) {
        final JmlForallClause n2 = (JmlForallClause) arg;
        if (!nodesEquals(n.getBoundedVariables(), n2.getBoundedVariables()))
            return false;
        if (!nodeEquals(n.getName(), n2.getName()))
            return false;
        return true;
    }

    @Override
    public Boolean visit(final JmlRefiningStmt n, final Visitable arg) {
        final JmlRefiningStmt n2 = (JmlRefiningStmt) arg;
        if (!nodesEquals(n.getJmlTags(), n2.getJmlTags()))
            return false;
        return true;
    }

    @Override
    public Boolean visit(final JmlClauseIf n, final Visitable arg) {
        final JmlClauseIf n2 = (JmlClauseIf) arg;
        return objEquals(n.getKind(), n2.getKind());
    }

    @Override
    public Boolean visit(final JmlClassExprDeclaration n, final Visitable arg) {
        final JmlClassExprDeclaration n2 = (JmlClassExprDeclaration) arg;
        if (!nodeEquals(n.getInvariant(), n2.getInvariant()))
            return false;
        if (!nodesEquals(n.getJmlTags(), n2.getJmlTags()))
            return false;
        if (!nodeEquals(n.getKind(), n2.getKind()))
            return false;
        if (!nodesEquals(n.getModifiers(), n2.getModifiers()))
            return false;
        if (!nodeEquals(n.getName(), n2.getName()))
            return false;
        if (!nodesEquals(n.getAnnotations(), n2.getAnnotations()))
            return false;
        return true;
    }

    @Override
    public Boolean visit(final JmlClassAccessibleDeclaration n, final Visitable arg) {
        final JmlClassAccessibleDeclaration n2 = (JmlClassAccessibleDeclaration) arg;
        if (!nodesEquals(n.getExpressions(), n2.getExpressions()))
            return false;
        if (!nodesEquals(n.getJmlTags(), n2.getJmlTags()))
            return false;
        if (!nodeEquals(n.getMeasuredBy(), n2.getMeasuredBy()))
            return false;
        if (!nodesEquals(n.getModifiers(), n2.getModifiers()))
            return false;
        if (!nodeEquals(n.getVariable(), n2.getVariable()))
            return false;
        if (!nodesEquals(n.getAnnotations(), n2.getAnnotations()))
            return false;
        return true;
    }

    @Override
    public Boolean visit(final JmlRepresentsDeclaration n, final Visitable arg) {
        final JmlRepresentsDeclaration n2 = (JmlRepresentsDeclaration) arg;
        if (!nodeEquals(n.getExpr(), n2.getExpr()))
            return false;
        if (!nodesEquals(n.getJmlTags(), n2.getJmlTags()))
            return false;
        if (!nodesEquals(n.getModifiers(), n2.getModifiers()))
            return false;
        if (!nodeEquals(n.getName(), n2.getName()))
            return false;
        if (!nodesEquals(n.getAnnotations(), n2.getAnnotations()))
            return false;
        return true;
    }

    @Override
    public Boolean visit(final JmlContract n, final Visitable arg) {
        final JmlContract n2 = (JmlContract) arg;
        if (!objEquals(n.getBehavior(), n2.getBehavior()))
            return false;
        if (!nodesEquals(n.getClauses(), n2.getClauses()))
            return false;
        if (!nodesEquals(n.getJmlTags(), n2.getJmlTags()))
            return false;
        if (!nodesEquals(n.getModifiers(), n2.getModifiers()))
            return false;
        if (!nodeEquals(n.getName(), n2.getName()))
            return false;
        if (!nodesEquals(n.getSubContracts(), n2.getSubContracts()))
            return false;
        if (!objEquals(n.getType(), n2.getType()))
            return false;
        return true;
    }

    @Override
    public Boolean visit(final JmlBodyDeclaration n, final Visitable arg) {
        final JmlBodyDeclaration n2 = (JmlBodyDeclaration) arg;
        if (!nodesEquals(n.getElements(), n2.getElements()))
            return false;
        if (!nodesEquals(n.getJmlTags(), n2.getJmlTags()))
            return false;
        if (!objEquals(n.isSingleLine(), n2.isSingleLine()))
            return false;
        if (!nodesEquals(n.getAnnotations(), n2.getAnnotations()))
            return false;
        return true;
    }

    @Override
    public Boolean visit(final JmlSetComprehension n, final Visitable arg) {
        final JmlSetComprehension n2 = (JmlSetComprehension) arg;
        if (!nodeEquals(n.getBinding(), n2.getBinding()))
            return false;
        if (!nodeEquals(n.getPredicate(), n2.getPredicate()))
            return false;
        return true;
    }

    @Override
    public Boolean visit(final JmlGhostStmt n, final Visitable arg) {
        final JmlGhostStmt n2 = (JmlGhostStmt) arg;
        if (!nodesEquals(n.getJmlTags(), n2.getJmlTags()))
            return false;
        if (!nodeEquals(n.getStatement(), n2.getStatement()))
            return false;
        return true;
    }

    @Override
    public Boolean visit(final RecordDeclaration n, final Visitable arg) {
        final RecordDeclaration n2 = (RecordDeclaration) arg;
        if (!nodesEquals(n.getImplementedTypes(), n2.getImplementedTypes()))
            return false;
        if (!nodesEquals(n.getParameters(), n2.getParameters()))
            return false;
        if (!nodeEquals(n.getReceiverParameter(), n2.getReceiverParameter()))
            return false;
        if (!nodesEquals(n.getTypeParameters(), n2.getTypeParameters()))
            return false;
        if (!nodesEquals(n.getMembers(), n2.getMembers()))
            return false;
        if (!nodesEquals(n.getModifiers(), n2.getModifiers()))
            return false;
        if (!nodeEquals(n.getName(), n2.getName()))
            return false;
        if (!nodesEquals(n.getAnnotations(), n2.getAnnotations()))
            return false;
        return true;
    }

    @Override
    public Boolean visit(final CompactConstructorDeclaration n, final Visitable arg) {
        final CompactConstructorDeclaration n2 = (CompactConstructorDeclaration) arg;
        if (!nodeEquals(n.getBody(), n2.getBody()))
            return false;
        if (!nodesEquals(n.getModifiers(), n2.getModifiers()))
            return false;
        if (!nodeEquals(n.getName(), n2.getName()))
            return false;
        if (!nodesEquals(n.getThrownExceptions(), n2.getThrownExceptions()))
            return false;
        if (!nodesEquals(n.getTypeParameters(), n2.getTypeParameters()))
            return false;
        if (!nodesEquals(n.getAnnotations(), n2.getAnnotations()))
            return false;
        return true;
    }

    @Override
    public Boolean visit(final JmlMethodDeclaration n, final Visitable arg) {
        final JmlMethodDeclaration n2 = (JmlMethodDeclaration) arg;
        if (!nodeEquals(n.getContract(), n2.getContract()))
            return false;
        if (!nodesEquals(n.getJmlTags(), n2.getJmlTags()))
            return false;
        if (!nodeEquals(n.getMethodDeclaration(), n2.getMethodDeclaration()))
            return false;
        if (!nodesEquals(n.getAnnotations(), n2.getAnnotations()))
            return false;
        return true;
    }

    @Override
    public Boolean visit(final JmlBinaryInfixExpr n, final Visitable arg) {
        final JmlBinaryInfixExpr n2 = (JmlBinaryInfixExpr) arg;
        if (!nodeEquals(n.getLeft(), n2.getLeft()))
            return false;
        if (!nodeEquals(n.getOperator(), n2.getOperator()))
            return false;
        if (!nodeEquals(n.getRight(), n2.getRight()))
            return false;
        return true;
    }

    @Override
    public Boolean visit(final JmlDocDeclaration n, final Visitable arg) {
        final JmlDocDeclaration n2 = (JmlDocDeclaration) arg;
        if (!nodesEquals(n.getJmlComments(), n2.getJmlComments()))
            return false;
        if (!nodesEquals(n.getAnnotations(), n2.getAnnotations()))
            return false;
        return true;
    }

    @Override
    public Boolean visit(final JmlDocStmt n, final Visitable arg) {
        final JmlDocStmt n2 = (JmlDocStmt) arg;
        if (!nodesEquals(n.getJmlComments(), n2.getJmlComments()))
            return false;
        return true;
    }

    @Override
    public Boolean visit(final JmlImportDeclaration n, final Visitable arg) {
        final JmlImportDeclaration n2 = (JmlImportDeclaration) arg;
        if (!objEquals(n.isAsterisk(), n2.isAsterisk()))
            return false;
        if (!objEquals(n.isStatic(), n2.isStatic()))
            return false;
        if (!nodesEquals(n.getJmlTags(), n2.getJmlTags()))
            return false;
        return nodeEquals(n.getName(), n2.getName());
    }

    @Override
    public Boolean visit(final JmlDoc n, final Visitable arg) {
        final JmlDoc n2 = (JmlDoc) arg;
        if (!objEquals(n.getContent(), n2.getContent()))
            return false;
        return true;
    }

    @Override
    public Boolean visit(final JmlDocType n, final Visitable arg) {
        final JmlDocType n2 = (JmlDocType) arg;
        if (!nodesEquals(n.getJmlComments(), n2.getJmlComments()))
            return false;
        if (!nodesEquals(n.getMembers(), n2.getMembers()))
            return false;
        if (!nodesEquals(n.getModifiers(), n2.getModifiers()))
            return false;
        if (!nodeEquals(n.getName(), n2.getName()))
            return false;
        if (!nodesEquals(n.getAnnotations(), n2.getAnnotations()))
            return false;
        return true;
    }

    @Override
    public Boolean visit(final JmlFieldDeclaration n, final Visitable arg) {
        final JmlFieldDeclaration n2 = (JmlFieldDeclaration) arg;
        if (!nodeEquals(n.getDecl(), n2.getDecl()))
            return false;
        if (!nodesEquals(n.getJmlTags(), n2.getJmlTags()))
            return false;
        if (!nodesEquals(n.getAnnotations(), n2.getAnnotations()))
            return false;
        return true;
    }

    @Override
    public Boolean visit(final JmlOldClause n, final Visitable arg) {
        final JmlOldClause n2 = (JmlOldClause) arg;
        if (!nodeEquals(n.getDeclarations(), n2.getDeclarations()))
            return false;
        if (!nodeEquals(n.getName(), n2.getName()))
            return false;
        return true;
    }

    @Override
    public Boolean visit(final JmlTypeExpr n, final Visitable arg) {
        final JmlTypeExpr n2 = (JmlTypeExpr) arg;
        if (!nodeEquals(n.getType(), n2.getType()))
            return false;
        return true;
    }

    @Override
    public Boolean visit(final JmlMultiExprClause n, final Visitable arg) {
        final JmlMultiExprClause n2 = (JmlMultiExprClause) arg;
        if (!nodesEquals(n.getExpressions(), n2.getExpressions()))
            return false;
        if (!nodesEquals(n.getHeaps(), n2.getHeaps()))
            return false;
        if (!objEquals(n.getKind(), n2.getKind()))
            return false;
        if (!nodeEquals(n.getName(), n2.getName()))
            return false;
        return true;
    }

    @Override
    public Boolean visit(final JmlBeginStmt n, final Visitable arg) {
        final JmlBeginStmt n2 = (JmlBeginStmt) arg;
        if (!nodesEquals(n.getJmlTags(), n2.getJmlTags()))
            return false;
        return true;
    }

    @Override
    public Boolean visit(final JmlEndStmt n, final Visitable arg) {
        final JmlEndStmt n2 = (JmlEndStmt) arg;
        if (!nodesEquals(n.getJmlTags(), n2.getJmlTags()))
            return false;
        return true;
    }

    @Override
    public Boolean visit(final JmlLabelStmt n, final Visitable arg) {
        final JmlLabelStmt n2 = (JmlLabelStmt) arg;
        if (!nodesEquals(n.getJmlTags(), n2.getJmlTags()))
            return false;
        if (!nodeEquals(n.getLabel(), n2.getLabel()))
            return false;
        return true;
    }
}
