package io.github.slupik.schemablock.model.ui.implementation.element.specific;

import com.google.gson.Gson;
import io.github.slupik.schemablock.model.ui.abstraction.element.Element;
import io.github.slupik.schemablock.model.ui.abstraction.ElementType;
import io.github.slupik.schemablock.model.ui.implementation.element.ElementBase;
import io.github.slupik.schemablock.model.ui.parser.BlockParserException;
import io.github.slupik.schemablock.model.ui.parser.ElementPOJO;

/**
 * All rights reserved & copyright ©
 */
public class StopBlock extends ElementBase implements Element {

    @Override
    public ElementType getType() {
        return ElementType.STOP;
    }

    @Override
    public void run() {
        onStart();
        onStop();
    }

    @Override
    public String stringify() {
        ElementPOJO pojo = new ElementPOJO();
        pojo.elementType = getType();
        pojo.id = getId();
        return new Gson().toJson(pojo);
    }

    @Override
    protected void load(ElementPOJO pojo) throws BlockParserException {
        id = pojo.id;
    }
}
