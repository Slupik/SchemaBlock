package io.github.slupik.schemablock.view.persistence

import io.github.slupik.schemablock.view.entity.Diagram
import java.io.File

/**
 * All rights reserved & copyright ©
 */
interface DiagramSaver {

    fun saveToFile(diagram: Diagram, file: File)
    fun stringify(diagram: Diagram): String?

}