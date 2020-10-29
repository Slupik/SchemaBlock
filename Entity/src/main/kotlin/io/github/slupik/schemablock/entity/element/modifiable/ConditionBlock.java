package io.github.slupik.schemablock.entity.element.modifiable;

import io.github.slupik.schemablock.entity.element.ModifiableBlock;

/**
 * All rights reserved & copyright ©
 */
public interface ConditionBlock extends ModifiableBlock {

    boolean runForResult() throws Exception;

}
