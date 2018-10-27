package io.github.slupik.schemablock.model.ui.parser;

import io.github.slupik.schemablock.model.ui.abstraction.element.Element;
import io.github.slupik.schemablock.model.ui.implementation.element.specific.*;

/**
 * All rights reserved & copyright ©
 */
public class ElementParser {
    public static Element parse(ElementPOJO pojo) throws BlockParserException {
        switch (pojo.elementType) {
            case START: {
                return new StartBlock();
            }
            case STOP: {
                return new StopBlock();
            }
            case CALCULATION: {
                return new CalculationBlock();
            }
            case CONDITION: {
                return new ConditionBlock();
            }
            case COMMUNICATION: {
                return new CommunicationBlock();
            }
            default: {
                throw new BlockParserException("Not found block for type "+pojo.elementType);
            }
        }
    }
}
