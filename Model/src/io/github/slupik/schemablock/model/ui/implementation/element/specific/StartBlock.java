package io.github.slupik.schemablock.model.ui.implementation.element.specific;

import com.google.gson.Gson;
import io.github.slupik.schemablock.model.ui.abstraction.ElementType;
import io.github.slupik.schemablock.model.ui.abstraction.element.StartElement;
import io.github.slupik.schemablock.model.ui.implementation.element.ElementBase;
import io.github.slupik.schemablock.model.ui.parser.BlockParserException;
import io.github.slupik.schemablock.model.ui.parser.ElementPOJO;

/**
 * All rights reserved & copyright ©
 */
public class StartBlock extends ElementBase implements StartElement {

    private String nextElement = "";

    @Override
    public ElementType getType() {
        return ElementType.START;
    }

    @Override
    public void setNextElement(String elementId) {
        nextElement = elementId;
    }

    @Override
    public String getNextElement() {
        return nextElement;
    }

    @Override
    public void removeNextElement(String elementId) {
        if(nextElement.equals(elementId)) {
            nextElement = "";
        }
    }

    @Override
    public void run() {
        onStart();
        tryRun(nextElement);
        onStop();
    }

    @Override
    public String stringify() {
        ElementPOJO pojo = new ElementPOJO();
        pojo.elementType = getType();
        pojo.nextBlocks = new String[1];
        pojo.nextBlocks[0] = nextElement;
        pojo.id = getId();
        return new Gson().toJson(pojo);
    }

    @Override
    protected void load(ElementPOJO pojo) throws BlockParserException {
        nextElement = pojo.nextBlocks[0];
        id = pojo.id;
    }
}
