package io.github.slupik.schemablock.view.persistence

import io.github.slupik.schemablock.view.entity.Diagram
import org.eclipse.emf.common.util.URI
import java.io.File

/**
 * All rights reserved & copyright ©
 */
interface DiagramLoader {

    fun loadDiagram(diagram: Diagram, file: File)
    fun loadDiagram(diagram: Diagram, content: String)
    fun loadDiagram(diagram: Diagram, uri: URI)

}