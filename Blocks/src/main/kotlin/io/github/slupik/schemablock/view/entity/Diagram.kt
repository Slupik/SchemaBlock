package io.github.slupik.schemablock.view.entity

import de.tesis.dynaware.grapheditor.GraphEditor

/**
 * All rights reserved & copyright ©
 */
interface Diagram {

    val graph: GraphEditor
    var additionalModel: AdditionalBlockModel

}