package io.github.slupik.schemablock.newparser.compilator.implementation.compilator;

import io.github.slupik.schemablock.model.ui.error.AlgorithmErrorType;

/**
 * All rights reserved & copyright ©
 */
public class NameForDeclarationCannotBeFound extends CompilationException {

    public NameForDeclarationCannotBeFound(int line, int pos) {
        super("Cannot find name of created variable", line, pos);
    }

    @Override
    public AlgorithmErrorType getType() {
        return AlgorithmErrorType.NAME_OF_VARIABLE_NOT_FOUND_DURING_DECLARATION;
    }

}
