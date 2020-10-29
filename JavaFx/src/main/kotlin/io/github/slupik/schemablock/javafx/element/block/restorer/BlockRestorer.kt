package io.github.slupik.schemablock.javafx.element.block.restorer

import io.github.slupik.schemablock.javafx.element.fx.schema.Schema

/**
 * All rights reserved & copyright ©
 */
interface BlockRestorer {

    fun restore(schema: Schema, blocksToRestore: List<String>)
    
}