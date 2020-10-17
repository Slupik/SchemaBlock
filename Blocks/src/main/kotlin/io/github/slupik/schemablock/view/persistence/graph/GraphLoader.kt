package io.github.slupik.schemablock.view.persistence.graph

import de.tesis.dynaware.grapheditor.GraphEditor
import org.eclipse.emf.common.util.URI
import java.io.File

/**
 * All rights reserved & copyright ©
 */
interface GraphLoader {

    fun loadModel(graphEditor: GraphEditor, file: File)
    fun loadModel(graphEditor: GraphEditor, content: String)
    fun loadModel(graphEditor: GraphEditor, uri: URI)

}