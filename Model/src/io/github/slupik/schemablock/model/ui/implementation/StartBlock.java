package io.github.slupik.schemablock.model.ui.implementation;

import com.google.gson.Gson;
import io.github.slupik.schemablock.model.ui.abstraction.Element;
import io.github.slupik.schemablock.model.ui.abstraction.ElementType;
import io.github.slupik.schemablock.model.ui.parser.ElementPOJO;

/**
 * All rights reserved & copyright ©
 */
public class StartBlock implements Element {

    @Override
    public ElementType getType() {
        return ElementType.START;
    }

    @Override
    public void run() {

    }

    @Override
    public String stringify() {
        ElementPOJO pojo = new ElementPOJO();
        pojo.elementType = getType();
        return new Gson().toJson(pojo);
    }

    @Override
    public void load(String data) {
//        ElementPOJO pojo = new Gson().fromJson(data, ElementPOJO.class);
    }
}
