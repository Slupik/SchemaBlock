package io.github.slupik.schemablock.model.ui.parser;

import com.google.gson.Gson;
import io.github.slupik.schemablock.model.ui.abstraction.element.Element;
import io.github.slupik.schemablock.model.ui.implementation.element.specific.*;
import io.github.slupik.schemablock.model.ui.newparser.HeapController;
import io.github.slupik.schemablock.newparser.executor.Executor;

/**
 * All rights reserved & copyright ©
 */
public class ElementParser {

    private final Executor executor;
    private final HeapController heap;

    public ElementParser(Executor executor, HeapController heap) {
        this.executor = executor;
        this.heap = heap;
    }

    public Element parse(String data) throws BlockParserException {
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
                element = new OperationBlock(executor);
                break;
            }
            case CONDITION: {
                element = new ConditionBlock(executor);
                break;
            }
            case COMMUNICATION: {
                element = new IOBlock(executor, heap);
                break;
            }
            default: {
                throw new BlockParserException("Not found block for type " + pojo.elementType);
            }
        }
        element.load(data);
        return element;
    }

}
