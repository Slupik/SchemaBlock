package io.github.slupik.schemablock.javafx.element.fx.sheet

import io.github.slupik.schemablock.javafx.element.fx.element.holder.BlocksHolder
import io.github.slupik.schemablock.javafx.element.fx.port.holder.PortsHolder
import javafx.scene.layout.Pane

/**
 * All rights reserved & copyright ©
 */
object SheetFactory {

    fun make(portsHolder: PortsHolder, blocksHolder: BlocksHolder, elementsContainer: Pane): Sheet {
        return BasicFeaturedSheet(
            elementsContainer,
            PortsAddingSheet(
                elementsContainer,
                portsHolder,
                ElementsSyncingSheet(
                    VisibleSheet(elementsContainer),
                    blocksHolder
                )
            )
        )
    }

}