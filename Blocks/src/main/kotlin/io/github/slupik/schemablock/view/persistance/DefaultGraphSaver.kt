package io.github.slupik.schemablock.view.persistance

import de.tesis.dynaware.grapheditor.GraphEditor
import de.tesis.dynaware.grapheditor.model.GModel
import org.eclipse.emf.common.util.URI
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain
import java.io.File
import java.io.IOException
import java.util.*
import javax.inject.Inject

/**
 * All rights reserved & copyright ©
 */
class DefaultGraphSaver @Inject constructor() : GraphSaver {

    override fun saveToFile(graphEditor: GraphEditor, file: File) {
        if (graphEditor.model != null) {
            saveModel(file, graphEditor.model)
        }
    }

    private fun saveModel(file: File, model: GModel) {
        val fileUri = convertToUriWithExtension(file)
        val editingDomain = AdapterFactoryEditingDomain.getEditingDomainFor(model)
        val resourceFactory = XMIResourceFactoryImpl()
        val resource = resourceFactory.createResource(fileUri)
        resource.contents.add(model)
        try {
            resource.save(Collections.EMPTY_MAP)
            editingDomain.resourceSet.resources.clear()
            editingDomain.resourceSet.resources.add(resource)
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    private fun convertToUriWithExtension(file: File): URI {
        var absolutePath = file.absolutePath
        if (!absolutePath.endsWith(FILE_EXTENSION)) {
            absolutePath += FILE_EXTENSION
        }
        return URI.createFileURI(absolutePath);
    }

}