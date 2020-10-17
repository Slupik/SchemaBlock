package io.github.slupik.schemablock.view.persistence.graph

import de.tesis.dynaware.grapheditor.GraphEditor
import de.tesis.dynaware.grapheditor.model.GModel
import org.eclipse.emf.common.util.URI
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl
import org.eclipse.emf.ecore.xmi.impl.XMLResourceImpl
import java.io.ByteArrayInputStream
import java.io.File
import java.io.IOException
import java.util.*
import javax.inject.Inject


/**
 * All rights reserved & copyright ©
 */
class DefaultGraphLoader @Inject constructor(): GraphLoader {

    override fun loadModel(graphEditor: GraphEditor, file: File) {
        val fileUri = URI.createFileURI(file.absolutePath)
        loadModel(graphEditor, fileUri)
    }

    override fun loadModel(graphEditor: GraphEditor, uri: URI) {
        val resourceFactory = XMIResourceFactoryImpl()
        val resource = resourceFactory.createResource(uri)

        try {
            resource.load(Collections.EMPTY_MAP)
        } catch (e: IOException) {
            e.printStackTrace()
        }

        if (!resource.contents.isEmpty() && resource.contents[0] is GModel) {
            val model = resource.contents[0] as GModel
            graphEditor.model = model
        }
    }

    override fun loadModel(graphEditor: GraphEditor, content: String) {
        val resource = XMLResourceImpl()

        try {
            resource.load(ByteArrayInputStream(content.toByteArray()), Collections.EMPTY_MAP)
        } catch (e: IOException) {
            e.printStackTrace()
        }

        if (!resource.contents.isEmpty() && resource.contents[0] is GModel) {
            val model = resource.contents[0] as GModel
            graphEditor.model = model
        }
    }

}