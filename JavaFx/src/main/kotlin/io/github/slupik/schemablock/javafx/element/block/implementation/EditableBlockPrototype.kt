package io.github.slupik.schemablock.javafx.element.block.implementation

import io.github.slupik.schemablock.javafx.element.UiElementType
import io.github.slupik.schemablock.javafx.element.block.EditableBlock
import org.apache.commons.lang3.RandomStringUtils

/**
 * All rights reserved & copyright ©
 */
abstract class EditableBlockPrototype constructor(
        override val type: UiElementType,
        override val elementId: String = RandomStringUtils.random(16)
) : DescribedBlockPrototype(type, elementId), EditableBlock
