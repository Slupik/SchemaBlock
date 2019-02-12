package io.github.slupik.schemablock.newparser.compilator.implementation.compilator;

/**
 * All rights reserved & copyright ©
 */
public class NameForDeclarationCannotBeFound extends CompilationException {

    public NameForDeclarationCannotBeFound(int line, int pos) {
        super("Cannot find name of created variable", line, pos);
    }
}
