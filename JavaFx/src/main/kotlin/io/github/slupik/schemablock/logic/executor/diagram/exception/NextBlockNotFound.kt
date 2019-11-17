package io.github.slupik.schemablock.logic.executor.diagram.exception

import io.github.slupik.schemablock.javafx.element.block.Block

/**
 * All rights reserved & copyright ©
 */
class NextBlockNotFound(val currentBlock: Block) : Exception(
    "Next block not found."
)