package io.github.slupik.schemablock.newparser.bytecode;

/**
 * All rights reserved & copyright ©
 */
public interface ByteCommand {
    ByteCommandType getCommandType();
    VariableType getVarType();
    String getVarName();
    int getHowManyValues();

    int getLine();
    int getPosition();
}
