package io.github.slupik.schemablock.entity.language;

/**
 * All rights reserved & copyright ©
 */
public interface LanguageInterpreter {

    void execute(String code) throws Exception;

    String getOutput(String code) throws Exception;

    boolean getResult(String code) throws Exception;

}
