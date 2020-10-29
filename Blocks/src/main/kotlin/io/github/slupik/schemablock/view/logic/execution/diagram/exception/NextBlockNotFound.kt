package io.github.slupik.schemablock.view.logic.execution.diagram.exception

import de.tesis.dynaware.grapheditor.core.skins.defaults.node.Block

/**
 * All rights reserved & copyright ©
 */
class NextBlockNotFound(val currentBlock: Block) : Exception(
    "Next block not found."
)