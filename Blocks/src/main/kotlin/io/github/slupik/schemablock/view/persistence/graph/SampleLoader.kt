package io.github.slupik.schemablock.view.persistence.graph

import io.github.slupik.schemablock.view.entity.Diagram
import io.github.slupik.schemablock.view.persistence.JsonDiagramLoader
import java.io.File
import java.net.URI
import javax.inject.Inject

/**
 * All rights reserved & copyright ©
 */
private const val PATH = "/io/github/slupik/schemablock/view/persistence/graph/"
private const val ABSOLUTE_VALUE_FILE: String = "absolute-value$FILE_EXTENSION"
private const val SUMMING_ARRAY_FILE = "summing-array$FILE_EXTENSION"
private const val HERON_FILE = "heron$FILE_EXTENSION"
private const val EQUATION_FILE = "equation$FILE_EXTENSION"
private const val PARADOX_FILE = "paradox$FILE_EXTENSION"

class SampleLoader @Inject constructor(
    private val loader: JsonDiagramLoader
) {

    fun loadAbsoluteValueSample(diagram: Diagram) {
        load(diagram, ABSOLUTE_VALUE_FILE)
    }

    fun loadArraySumSample(diagram: Diagram) {
        load(diagram, SUMMING_ARRAY_FILE)
    }

    fun loadHeronSample(diagram: Diagram) {
        load(diagram, HERON_FILE)
    }

    fun loadEquationSample(diagram: Diagram) {
        load(diagram, EQUATION_FILE)
    }

    fun loadParadoxSample(diagram: Diagram) {
        load(diagram, PARADOX_FILE)
    }

    private fun load(diagram: Diagram, fileName: String) {
        val file = File(URI.create(javaClass.getResource(PATH + fileName).toExternalForm()))
        loader.loadDiagram(
            diagram,
            file
        )
    }

}