package io.github.slupik.schemablock.view.persistance

import de.tesis.dynaware.grapheditor.GraphEditor
import java.io.File

/**
 * All rights reserved & copyright ©
 */
interface GraphSaver  {

    fun saveToFile(graphEditor: GraphEditor, file: File)
    fun stringify(graphEditor: GraphEditor): String?

}