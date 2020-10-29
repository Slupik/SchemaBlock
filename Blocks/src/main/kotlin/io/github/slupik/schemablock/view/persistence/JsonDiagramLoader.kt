package io.github.slupik.schemablock.view.persistence

import com.google.gson.Gson
import io.github.slupik.schemablock.view.entity.Diagram
import io.github.slupik.schemablock.view.persistence.graph.GraphLoader
import org.apache.commons.io.IOUtils
import org.eclipse.emf.common.util.URI
import java.io.File
import java.io.FileInputStream
import java.nio.charset.Charset
import javax.inject.Inject


/**
 * All rights reserved & copyright ©
 */
class JsonDiagramLoader @Inject constructor(
    private val graphLoader: GraphLoader
) : DiagramLoader {

    override fun loadDiagram(diagram: Diagram, file: File) {
        val data: String = IOUtils.toString(FileInputStream(file), Charset.forName("UTF-8"))
        loadDiagram(diagram, data)
    }

    override fun loadDiagram(diagram: Diagram, content: String) {
        val parsed = Gson().fromJson<SavedDiagramStructure>(content, SavedDiagramStructure::class.java)
        graphLoader.loadModel(diagram.graph, parsed.graphModel)
        diagram.additionalModel = parsed.additionalModel
    }

    override fun loadDiagram(diagram: Diagram, uri: URI) {
        TODO("Not yet implemented")
    }

}