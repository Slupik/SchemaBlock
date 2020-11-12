package io.github.slupik.schemablock.view.dialog.factory

import de.tesis.dynaware.grapheditor.demo.GraphEditorDemo
import io.github.slupik.schemablock.view.dialog.controller.DialogController
import io.github.slupik.schemablock.view.dialog.data.UiBlockSettings
import javafx.fxml.FXMLLoader
import javafx.scene.Parent
import javafx.scene.Scene
import javafx.stage.Modality
import javafx.stage.Stage
import javafx.stage.StageStyle
import javax.inject.Inject

/**
 * All rights reserved & copyright ©
 */
abstract class DialogFactory<ControllerType : DialogController<InputType>, InputType: UiBlockSettings> @Inject constructor(
    xmlFileName: String,
    input: InputType
) {

    private var _dialogController: ControllerType? = null
    val dialogController: ControllerType?
        get() = _dialogController
    val stage = Stage()

    init {
        val fxmlLoader = FXMLLoader(javaClass.getResource(GraphEditorDemo.MAIN_RESOURCE_ROOT + "dialog/$xmlFileName"))
        val parent = fxmlLoader.load<Parent>()
        val scene = Scene(parent, 350.0, getMinimumHeight())
        scene.stylesheets.add(GraphEditorDemo::class.java.getResource(GraphEditorDemo.MAIN_STYLESHEET).toExternalForm())
        stage.minWidth = scene.width + 16
        stage.minHeight = scene.height + 40
        stage.initModality(Modality.APPLICATION_MODAL)
        stage.initStyle(StageStyle.UTILITY)
        stage.title = "Edycja bloku"
        stage.scene = scene
        additionalSetup(stage)
        _dialogController = fxmlLoader.getController<ControllerType>()
        _dialogController!!.injectStage(stage)
        _dialogController!!.loadModel(input)
    }

    protected open fun additionalSetup(stage: Stage) {

    }

    abstract fun getMinimumHeight(): Double

}