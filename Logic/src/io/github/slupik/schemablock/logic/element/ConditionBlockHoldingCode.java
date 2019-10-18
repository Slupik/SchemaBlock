package io.github.slupik.schemablock.logic.element;

import io.github.slupik.schemablock.entity.element.modifiable.ConditionBlock;
import io.github.slupik.schemablock.entity.element.state.ElementState;
import io.github.slupik.schemablock.entity.language.LanguageInterpreter;

/**
 * All rights reserved & copyright ©
 */
public class ConditionBlockHoldingCode extends ElementBasis implements ConditionBlock {

    private final LanguageInterpreter interpreter;
    private final String code;

    public ConditionBlockHoldingCode(LanguageInterpreter interpreter, String code){
        this.interpreter = interpreter;
        this.code = code;
    }

    @Override
    public boolean runForResult() throws Exception {
        onStateChange(ElementState.RUNNING);
        try {
            boolean result = interpreter.getResult(code);
            onStateChange(ElementState.STOPPED);
            return result;
        } catch (Exception e) {
            onStateChange(ElementState.ERROR);
            onStateChange(ElementState.STOPPED);
            throw e;
        }
    }

}
