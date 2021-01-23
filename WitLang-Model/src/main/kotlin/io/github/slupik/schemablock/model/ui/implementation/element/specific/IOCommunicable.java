package io.github.slupik.schemablock.model.ui.implementation.element.specific;

/**
 * All rights reserved & copyright ©
 */
public interface IOCommunicable {

    String getInput();

    void print(String value);

    void clear();

    void printAlgorithmError(String text);

    void printProgramError(String text);

    void stop();
}
