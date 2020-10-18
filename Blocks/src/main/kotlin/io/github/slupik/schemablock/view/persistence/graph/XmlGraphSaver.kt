package io.github.slupik.schemablock.view.persistence.graph

import de.tesis.dynaware.grapheditor.GraphEditor
import de.tesis.dynaware.grapheditor.model.GModel
import org.eclipse.emf.common.util.URI
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl
import org.eclipse.emf.ecore.xmi.impl.XMLResourceImpl
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.IOException
import java.util.*
import javax.inject.Inject

/**
 * All rights reserved & copyright ©
 */
class XmlGraphSaver @Inject constructor() : GraphSaver {

    override fun saveToFile(graphEditor: GraphEditor, file: File) {
        if (graphEditor.model != null) {
            saveModel(file, graphEditor.model)
        }
    }

    override fun stringify(graphEditor: GraphEditor): String? {
        if (graphEditor.model != null) {
            val resource = XMLResourceImpl()
            val output = ByteArrayOutputStream();
            val model = graphEditor.model;

            val editingDomain = AdapterFactoryEditingDomain.getEditingDomainFor(model)
            resource.contents.add(model)
            try {
                resource.save(output, Collections.EMPTY_MAP)
                editingDomain.resourceSet.resources.clear()
                editingDomain.resourceSet.resources.add(resource)
            } catch (e: IOException) {
                e.printStackTrace()
            }

            return output.toString()
        } else {
            return null;
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