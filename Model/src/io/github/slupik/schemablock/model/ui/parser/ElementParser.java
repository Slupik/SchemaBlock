package io.github.slupik.schemablock.model.ui.parser;

import com.google.gson.Gson;
import io.github.slupik.schemablock.model.ui.abstraction.element.Element;
import io.github.slupik.schemablock.model.ui.implementation.element.specific.*;

/**
 * All rights reserved & copyright ©
 */
public class ElementParser {
    public static Element parse(String data) throws BlockParserException {
        ElementPOJO pojo = new Gson().fromJson(data, ElementPOJO.class);
        Element element;
        switch (pojo.elementType) {
            case START: {
                element = new StartBlock();
                break;
            }
            case STOP: {
                element = new StopBlock();
                break;
            }
            case CALCULATION: {
                element = new CalculationBlock();
                break;
            }
            case CONDITION: {
                element = new ConditionBlock();
                break;
            }
            case COMMUNICATION: {
                element = new CommunicationBlock();
                break;
            }
            default: {
                throw new BlockParserException("Not found block for type "+pojo.elementType);
            }
        }
        element.load(data);
        return element;
    }
}
