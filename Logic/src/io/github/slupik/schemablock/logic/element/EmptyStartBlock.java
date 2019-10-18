package io.github.slupik.schemablock.logic.element;

import io.github.slupik.schemablock.entity.element.constant.StartBlock;
import io.github.slupik.schemablock.entity.element.state.ElementState;

/**
 * All rights reserved & copyright ©
 */
public class EmptyStartBlock extends ElementBasis implements StartBlock {

    @Override
    public void run() {
        onStateChange(ElementState.RUNNING);
        onStateChange(ElementState.STOPPED);
    }

}
