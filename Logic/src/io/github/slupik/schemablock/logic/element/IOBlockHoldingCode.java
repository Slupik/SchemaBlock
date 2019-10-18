package io.github.slupik.schemablock.logic.element;

import io.github.slupik.schemablock.entity.element.modifiable.IOBlock;
import io.github.slupik.schemablock.entity.element.state.ElementState;
import io.github.slupik.schemablock.entity.language.LanguageInterpreter;

/**
 * All rights reserved & copyright ©
 */
public class IOBlockHoldingCode extends ElementBasis implements IOBlock {

    private final LanguageInterpreter interpreter;
    private final String code;

    public IOBlockHoldingCode(LanguageInterpreter interpreter, String code){
        this.interpreter = interpreter;
        this.code = code;
    }

    @Override
    public String runForOutput() throws Exception {
        onStateChange(ElementState.RUNNING);
        try {
            String output = interpreter.getOutput(code);
            onStateChange(ElementState.STOPPED);
            return output;
        } catch (Exception e) {
            onStateChange(ElementState.ERROR);
            onStateChange(ElementState.STOPPED);
            throw e;
        }
    }

}
