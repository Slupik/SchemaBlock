package io.github.slupik.schemablock.logic.element;

import io.github.slupik.schemablock.entity.element.modifiable.OperationBlock;
import io.github.slupik.schemablock.entity.element.state.ElementState;
import io.github.slupik.schemablock.entity.language.LanguageInterpreter;

/**
 * All rights reserved & copyright ©
 */
public class OperationBlockHoldingCode extends ElementBasis implements OperationBlock {

    private final LanguageInterpreter interpreter;
    private final String code;

    public OperationBlockHoldingCode(LanguageInterpreter interpreter, String code){
        this.interpreter = interpreter;
        this.code = code;
    }

    @Override
    public void run() throws Exception {
        onStateChange(ElementState.RUNNING);
        try {
            interpreter.execute(code);
            onStateChange(ElementState.STOPPED);
        } catch (Exception e) {
            onStateChange(ElementState.ERROR);
            onStateChange(ElementState.STOPPED);
            throw e;
        }
    }

}
