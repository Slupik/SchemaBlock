package io.github.slupik.schemablock.newparser.compilator.exception;

/**
 * All rights reserved & copyright ©
 */
public class ComExIllegalEscapeChar extends Throwable {
    public ComExIllegalEscapeChar(int line, int position, char escapeChar, char escapedChar) {
        super("Illegal escape char in line "+line+" at position "+position+". Tried to escape char "+escapedChar);
    }
}
