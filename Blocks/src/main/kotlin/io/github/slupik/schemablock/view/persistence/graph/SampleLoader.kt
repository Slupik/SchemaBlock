package io.github.slupik.schemablock.view.persistence.graph

import de.tesis.dynaware.grapheditor.GraphEditor
import io.github.slupik.schemablock.view.persistence.GraphToDiagramConverter
import org.eclipse.emf.common.util.URI
import javax.inject.Inject

/**
 * All rights reserved & copyright ©
 */
private const val SAMPLE_FILE: String = "sample$FILE_EXTENSION"
private const val SAMPLE_FILE_LARGE = "sample-large$FILE_EXTENSION"

class SampleLoader @Inject constructor(
    private val graphLoader: GraphLoader,
    private val converter: GraphToDiagramConverter
) {

    fun loadSmallSample(graphEditor: GraphEditor) {
        load(graphEditor, SAMPLE_FILE)
    }

    fun loadBigSample(graphEditor: GraphEditor) {
        load(graphEditor, SAMPLE_FILE_LARGE)
    }

    private fun load(graphEditor: GraphEditor, path: String) {
        val samplePath = javaClass.getResource(path).toExternalForm()
        val fileUri = URI.createURI(samplePath)
        graphLoader.loadModel(graphEditor, fileUri)
        converter.convert(fileUri)
    }

}