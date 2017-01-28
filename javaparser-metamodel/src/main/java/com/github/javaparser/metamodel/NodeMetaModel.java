package com.github.javaparser.metamodel;

import java.util.Optional;

public class NodeMetaModel extends BaseNodeMetaModel {

    NodeMetaModel(JavaParserMetaModel parent, Optional<BaseNodeMetaModel> superBaseNodeMetaModel) {
        super(superBaseNodeMetaModel, parent, com.github.javaparser.ast.Node.class, "Node", "com.github.javaparser.ast", true, false);
    }

    public PropertyMetaModel commentPropertyMetaModel;
}

