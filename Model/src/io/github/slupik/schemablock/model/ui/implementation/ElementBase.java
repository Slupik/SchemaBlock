package io.github.slupik.schemablock.model.ui.implementation;

import io.github.slupik.schemablock.model.ui.abstraction.Element;
import io.github.slupik.schemablock.model.utils.RandomString;

/**
 * All rights reserved & copyright ©
 */
public abstract class ElementBase implements Element {
    @Override
    public String getId() {
        return RandomString.generate(32);
    }
}
