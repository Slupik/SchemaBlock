package io.github.slupik.schemablock.view.persistence.graph

import io.github.slupik.schemablock.view.dagger.WindowProvider
import io.github.slupik.schemablock.view.persistence.FileChooser
import java.io.File
import javax.inject.Inject
import javafx.stage.FileChooser as JavaFxFileChooser

/**
 * All rights reserved & copyright ©
 */
private const val CHOOSER_TEXT = "Graph Model Files (*$FILE_EXTENSION)"

class FileGuiChooser @Inject constructor(
    private val window: WindowProvider
) : FileChooser {

    private val initialDirectory: File? = null

    override fun choseForSave(): File?  = getFileChooser().showSaveDialog(window.provide())

    override fun choseForOpen(): File? = getFileChooser().showOpenDialog(window.provide())

    private fun getFileChooser(): JavaFxFileChooser {
        val fileChooser = JavaFxFileChooser()
        val filter = JavaFxFileChooser.ExtensionFilter(
            CHOOSER_TEXT,
            "*$FILE_EXTENSION"
        )
        fileChooser.extensionFilters.add(filter)
        if (initialDirectory != null && initialDirectory.exists()) {
            fileChooser.initialDirectory = initialDirectory
        }
        return fileChooser;
    }

}