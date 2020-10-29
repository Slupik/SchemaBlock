package io.github.slupik.schemablock.view.persistence

import com.google.gson.Gson
import io.github.slupik.schemablock.view.entity.Diagram
import io.github.slupik.schemablock.view.persistence.graph.GraphSaver
import java.io.File
import java.io.FileOutputStream
import java.nio.charset.Charset
import javax.inject.Inject


/**
 * All rights reserved & copyright ©
 */
class JsonDiagramSaver @Inject constructor(
    private val graphSaver: GraphSaver
) : DiagramSaver {

    override fun saveToFile(diagram: Diagram, file: File) {
        val content = stringify(diagram)
        if (content != null) {
            try {
                val outputStream = FileOutputStream(file)
                val strToBytes: ByteArray = content.toByteArray(Charset.forName("UTF-8"))
                outputStream.write(strToBytes)
                outputStream.close()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    override fun stringify(diagram: Diagram): String? {
        val graph = graphSaver.stringify(diagram.graph)
        if (graph != null) {
            val structure = SavedDiagramStructure(
                graph,
                diagram.additionalModel
            )
            return Gson().toJson(structure);
        }
        return null
    }

}