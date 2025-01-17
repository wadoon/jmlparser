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

package com.github.javaparser.builders;

import static com.github.javaparser.StaticJavaParser.*;
import static com.github.javaparser.ast.Modifier.DefaultKeyword.*;
import static org.junit.jupiter.api.Assertions.*;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.*;
import com.github.javaparser.ast.type.ClassOrInterfaceType;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;

class NodeWithMembersBuildersTest {
    private final CompilationUnit cu = new CompilationUnit();
    private final ClassOrInterfaceDeclaration classDeclaration = cu.addClass("test");

    @Test
    void testAddField() {
        FieldDeclaration addField = classDeclaration.addField(int.class, "fieldName", PRIVATE);
        assertEquals(1, classDeclaration.getMembers().size());
        assertEquals(addField, classDeclaration.getMember(0));
        assertEquals("fieldName", addField.getVariable(0).getNameAsString());
    }

    @Test
    void testAddMethod() {
        MethodDeclaration addMethod = classDeclaration.addMethod("foo", PUBLIC);
        assertEquals(1, classDeclaration.getMembers().size());
        assertEquals(addMethod, classDeclaration.getMember(0));
        assertEquals("foo", addMethod.getNameAsString());
    }

    @Test
    void testAddCtor() {
        ConstructorDeclaration addCtor = classDeclaration.addConstructor(PUBLIC);
        assertEquals(1, classDeclaration.getMembers().size());
        assertEquals(addCtor, classDeclaration.getMember(0));
        assertEquals(classDeclaration.getName(), addCtor.getName());
    }

    @Test
    void testAddInitializers() {
        classDeclaration.addInitializer();
        assertEquals(1, classDeclaration.getMembers().size());
        assertEquals(InitializerDeclaration.class, classDeclaration.getMember(0).getClass());

        classDeclaration.addStaticInitializer();
        assertEquals(2, classDeclaration.getMembers().size());
        assertEquals(InitializerDeclaration.class, classDeclaration.getMember(0).getClass());
    }

    @Test
    void testGetMethodsWithName() {
        MethodDeclaration addMethod = classDeclaration.addMethod("foo", PUBLIC);
        MethodDeclaration addMethod2 = classDeclaration.addMethod("foo", PUBLIC).addParameter(int.class, "overload");
        List<MethodDeclaration> methodsByName = classDeclaration.getMethodsByName("foo");
        assertEquals(2, methodsByName.size());
        assertTrue(methodsByName.contains(addMethod));
        assertTrue(methodsByName.contains(addMethod2));
    }

    @Test
    void testGetMethods() {
        MethodDeclaration addMethod = classDeclaration.addMethod("foo", PUBLIC);
        MethodDeclaration addMethod2 = classDeclaration.addMethod("foo", PUBLIC).addParameter(int.class, "overload");

        List<MethodDeclaration> methods = classDeclaration.getMethods();

        assertEquals(2, methods.size());
        assertTrue(methods.contains(addMethod));
        assertTrue(methods.contains(addMethod2));
    }

    @Test
    void testGetMethodsWithParameterTypes() {
        MethodDeclaration mFoo = classDeclaration.addMethod("foo", PUBLIC); // foo()
        MethodDeclaration mFooInt = classDeclaration.addMethod("foo", PUBLIC).addParameter(int.class, "i"); // foo(int)
        ClassOrInterfaceType type = parseClassOrInterfaceType("List");
        type.setTypeArguments(parseClassOrInterfaceType("String"));
        MethodDeclaration mFooIntList = classDeclaration
                .addMethod("foo", PUBLIC)
                .addParameter(int.class, "i")
                .addParameter(type, "l"); // foo(int, List)
        MethodDeclaration mFooListInt = classDeclaration
                .addMethod("foo", PUBLIC)
                .addParameter(type, "l")
                .addParameter(int.class, "i"); // foo(List, int)
        MethodDeclaration mFoo2Int =
                classDeclaration.addMethod("foo2", PUBLIC).addParameter(int.class, "i"); // foo2(int)
        MethodDeclaration mFoo2IntInt = classDeclaration
                .addMethod("foo2", PUBLIC)
                .addParameter(int.class, "i")
                .addParameter(int.class, "j"); // foo2(int, int)

        List<MethodDeclaration> methodsWithNoParams =
                classDeclaration.getMethodsByParameterTypes(new Class[0]); // should return foo()
        assertEquals(1, methodsWithNoParams.size());
        assertTrue(methodsWithNoParams.contains(mFoo));

        List<MethodDeclaration> methodsWithIntParam =
                classDeclaration.getMethodsByParameterTypes(int.class); // should return foo(int) and foo2(int)
        assertEquals(2, methodsWithIntParam.size());
        assertTrue(methodsWithIntParam.contains(mFooInt));
        assertTrue(methodsWithIntParam.contains(mFoo2Int));

        List<MethodDeclaration> methodsWithListParam = classDeclaration.getMethodsByParameterTypes("List<String>");
        assertEquals(0, methodsWithListParam.size());

        List<MethodDeclaration> methodsWithIntAndListParams =
                classDeclaration.getMethodsByParameterTypes("int", "List<String>");
        assertEquals(1, methodsWithIntAndListParams.size());
        assertTrue(methodsWithIntAndListParams.contains(mFooIntList));

        List<MethodDeclaration> methodsWithListAndIntParams =
                classDeclaration.getMethodsByParameterTypes(List.class, int.class);
        assertEquals(1, methodsWithListAndIntParams.size());
        assertTrue(methodsWithListAndIntParams.contains(mFooListInt));

        List<MethodDeclaration> methodsWithIntAndIntParams =
                classDeclaration.getMethodsByParameterTypes(int.class, int.class);
        assertEquals(1, methodsWithListAndIntParams.size());
        assertTrue(methodsWithIntAndIntParams.contains(mFoo2IntInt));
    }

    @Test
    void testGetConstructors() {
        ConstructorDeclaration addConstructor = classDeclaration.addConstructor(PUBLIC);
        ConstructorDeclaration addConstructor2 =
                classDeclaration.addConstructor(PUBLIC).addParameter(int.class, "overload");

        List<ConstructorDeclaration> constructors = classDeclaration.getConstructors();

        assertEquals(2, constructors.size());
        assertTrue(constructors.contains(addConstructor));
        assertTrue(constructors.contains(addConstructor2));
    }

    @Test
    void testGetConstructorsWithParameterTypes() {
        ConstructorDeclaration c = classDeclaration.addConstructor(PUBLIC); // Foo()
        ConstructorDeclaration cInt = classDeclaration.addConstructor(PUBLIC).addParameter(int.class, "i"); // Foo(int)
        ClassOrInterfaceType type = parseClassOrInterfaceType("List");
        type.setTypeArguments(parseClassOrInterfaceType("String"));
        ConstructorDeclaration cIntList = classDeclaration
                .addConstructor(PUBLIC)
                .addParameter(int.class, "i")
                .addParameter(type, "l"); // Foo(int, List)
        ConstructorDeclaration cListInt = classDeclaration
                .addConstructor(PUBLIC)
                .addParameter(type, "l")
                .addParameter(int.class, "i"); // Foo(List, int)
        ConstructorDeclaration cIntInt = classDeclaration
                .addConstructor(PUBLIC)
                .addParameter(int.class, "i")
                .addParameter(int.class, "j"); // Foo(int, int)

        Optional<ConstructorDeclaration> constructorWithNoParams = classDeclaration.getDefaultConstructor();
        assertTrue(constructorWithNoParams.isPresent());
        assertSame(c, constructorWithNoParams.get());

        Optional<ConstructorDeclaration> constructorWithIntParam =
                classDeclaration.getConstructorByParameterTypes(int.class);
        assertTrue(constructorWithIntParam.isPresent());
        assertSame(cInt, constructorWithIntParam.get());

        Optional<ConstructorDeclaration> constructorWithListParam =
                classDeclaration.getConstructorByParameterTypes("List<String>");
        assertFalse(constructorWithListParam.isPresent());

        Optional<ConstructorDeclaration> constructorWithIntAndListParams =
                classDeclaration.getConstructorByParameterTypes("int", "List<String>");
        assertTrue(constructorWithIntAndListParams.isPresent());
        assertSame(cIntList, constructorWithIntAndListParams.get());

        Optional<ConstructorDeclaration> constructorWithListAndIntParams =
                classDeclaration.getConstructorByParameterTypes(List.class, int.class);
        assertTrue(constructorWithListAndIntParams.isPresent());
        assertSame(cListInt, constructorWithListAndIntParams.get());

        Optional<ConstructorDeclaration> constructorWithIntAndIntParams =
                classDeclaration.getConstructorByParameterTypes(int.class, int.class);
        assertTrue(constructorWithIntAndIntParams.isPresent());
        assertSame(cIntInt, constructorWithIntAndIntParams.get());
    }

    @Test
    void testGetFieldWithName() {
        FieldDeclaration addField = classDeclaration.addField(int.class, "fieldName", PRIVATE);
        classDeclaration.addField(float.class, "secondField", PRIVATE);
        FieldDeclaration fieldByName =
                classDeclaration.getFieldByName("fieldName").get();
        assertEquals(addField, fieldByName);
    }

    @Test
    void testGetFields() {
        FieldDeclaration firstField = classDeclaration.addField(int.class, "fieldName", PRIVATE);
        FieldDeclaration secondField = classDeclaration.addField(float.class, "secondField", PRIVATE);

        List<FieldDeclaration> fields = classDeclaration.getFields();

        assertTrue(fields.contains(firstField));
        assertTrue(fields.contains(secondField));
    }

    @Test
    void testAddPrivateFieldWithType() {
        CompilationUnit compilationUnit = new CompilationUnit();
        ClassOrInterfaceDeclaration classOrInterfaceDeclaration = compilationUnit.addClass("Person");
        classOrInterfaceDeclaration.addPrivateField(parseType("java.lang.String"), "name");

        assertNotNull(classOrInterfaceDeclaration.getFields());
        assertEquals(1, classOrInterfaceDeclaration.getFields().size());

        FieldDeclaration fieldDeclaration =
                classOrInterfaceDeclaration.getFields().get(0);
        assertEquals(PRIVATE, fieldDeclaration.getModifiers().iterator().next().getKeyword());
        assertEquals(
                "java.lang.String",
                fieldDeclaration.getVariables().get(0).getType().toString());
        assertEquals("name", fieldDeclaration.getVariables().get(0).getName().toString());
    }

    @Test
    void testAddPublicFieldWithType() {
        CompilationUnit compilationUnit = new CompilationUnit();
        ClassOrInterfaceDeclaration classOrInterfaceDeclaration = compilationUnit.addClass("Person");
        classOrInterfaceDeclaration.addPublicField(parseType("java.lang.String"), "name");

        assertNotNull(classOrInterfaceDeclaration.getFields());
        assertEquals(1, classOrInterfaceDeclaration.getFields().size());

        FieldDeclaration fieldDeclaration =
                classOrInterfaceDeclaration.getFields().get(0);
        assertEquals(PUBLIC, fieldDeclaration.getModifiers().iterator().next().getKeyword());
        assertEquals(
                "java.lang.String",
                fieldDeclaration.getVariables().get(0).getType().toString());
        assertEquals("name", fieldDeclaration.getVariables().get(0).getName().toString());
    }

    @Test
    void testAddProtectedFieldWithType() {
        CompilationUnit compilationUnit = new CompilationUnit();
        ClassOrInterfaceDeclaration classOrInterfaceDeclaration = compilationUnit.addClass("Person");
        classOrInterfaceDeclaration.addProtectedField(parseType("java.lang.String"), "name");

        assertNotNull(classOrInterfaceDeclaration.getFields());
        assertEquals(1, classOrInterfaceDeclaration.getFields().size());

        FieldDeclaration fieldDeclaration =
                classOrInterfaceDeclaration.getFields().get(0);
        assertEquals(
                PROTECTED, fieldDeclaration.getModifiers().iterator().next().getKeyword());
        assertEquals(
                "java.lang.String",
                fieldDeclaration.getVariables().get(0).getType().toString());
        assertEquals("name", fieldDeclaration.getVariables().get(0).getName().toString());
    }

    @Test
    void testClassWithInitializersWithString() {
        CompilationUnit compilationUnit = new CompilationUnit();
        ClassOrInterfaceDeclaration classOrInterfaceDeclaration = compilationUnit.addClass("Person");
        classOrInterfaceDeclaration.addFieldWithInitializer(
                "java.lang.String", "name", parseExpression("John"), PUBLIC);
        assertNotNull(classOrInterfaceDeclaration.getFields());
        assertEquals(1, classOrInterfaceDeclaration.getFields().size());

        FieldDeclaration fieldDeclaration =
                classOrInterfaceDeclaration.getFields().get(0);
        assertEquals(PUBLIC, fieldDeclaration.getModifiers().iterator().next().getKeyword());
        assertEquals(
                "java.lang.String",
                fieldDeclaration.getVariables().get(0).getType().toString());
        assertEquals("name", fieldDeclaration.getVariables().get(0).getName().toString());
        assertEquals(
                "John",
                fieldDeclaration.getVariables().get(0).getInitializer().get().toString());
    }

    @Test
    void testClassWithInitializersWithClass() {
        CompilationUnit compilationUnit = new CompilationUnit();
        ClassOrInterfaceDeclaration classOrInterfaceDeclaration = compilationUnit.addClass("Person");
        classOrInterfaceDeclaration.addFieldWithInitializer(
                List.class, "skills", parseExpression("new ArrayList()"), PUBLIC);
        assertNotNull(classOrInterfaceDeclaration.getFields());
        assertEquals(1, classOrInterfaceDeclaration.getFields().size());

        FieldDeclaration fieldDeclaration =
                classOrInterfaceDeclaration.getFields().get(0);
        assertEquals(PUBLIC, fieldDeclaration.getModifiers().iterator().next().getKeyword());
        assertEquals("List", fieldDeclaration.getVariables().get(0).getType().toString());
        assertEquals("skills", fieldDeclaration.getVariables().get(0).getName().toString());
        assertEquals(
                "new ArrayList()",
                fieldDeclaration.getVariables().get(0).getInitializer().get().toString());
    }
}
