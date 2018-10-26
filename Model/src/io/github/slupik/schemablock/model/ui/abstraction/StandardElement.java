package io.github.slupik.schemablock.model.ui.abstraction;

import io.github.slupik.schemablock.model.control.ElementInput;
import io.github.slupik.schemablock.model.control.ElementOutput;

/**
 * All rights reserved & copyright ©
 */
public interface StandardElement extends Element {
    void setContent(String content);
    void setInput(ElementInput input);
    void setOutput(ElementOutput output);
}
