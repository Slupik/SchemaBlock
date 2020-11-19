package io.github.slupik.schemablock.view.dialog.factory

import io.github.slupik.schemablock.view.dialog.controller.ConditionDialogController
import io.github.slupik.schemablock.view.dialog.controller.IoDialogController
import io.github.slupik.schemablock.view.dialog.controller.OperationsDialogController
import io.github.slupik.schemablock.view.dialog.data.CodeAndDescription
import io.github.slupik.schemablock.view.dialog.data.DescriptionAndIO


/**
 * All rights reserved & copyright ©
 */
object DialogFactoryFacade {

    fun buildWithDescAndContent(input: CodeAndDescription): OperationsDialogController {
        val factory = OperationsDialogFactory("OperationsDialog.fxml", input)
        factory.stage.showAndWait()
        return factory.dialogController!!
    }

    fun buildWithDescAndShortContent(input: CodeAndDescription): ConditionDialogController {
        val factory = ConditionDialogFactory("ConditionDialog.fxml", input)
        factory.stage.showAndWait()
        return factory.dialogController!!
    }

    fun buildIO(input: DescriptionAndIO): IoDialogController {
        val factory = IoDialogFactory("IoDialog.fxml", input)
        factory.stage.showAndWait()
        return factory.dialogController!!
    }
//
//    fun buildIO(input: DescriptionAndIO): Dialog<UiBlockSettings> {
//        val dialog = Dialog<UiBlockSettings>()
//        dialog.title = "Edycja bloku"
//
//        val saveButtonType = ButtonType("Zapisz", ButtonBar.ButtonData.OK_DONE)
//        dialog.dialogPane.buttonTypes.addAll(saveButtonType, ButtonType.CANCEL)
//
//        val verContainer = VBox()
//        verContainer.padding = Insets(20.0, 50.0, 10.0, 10.0)
//
//        val horContainer = HBox()
//
//        val title = TextField(input.description)
//        title.promptText = "Krótki tytuł"
//
//        val titleLbl = Label("Tytuł:  ")
//        titleLbl.padding = Insets(8.0, 0.0, 8.0, 0.0)
//
//        horContainer.children.add(titleLbl)
//        horContainer.children.add(title)
//        horContainer.alignment = Pos.CENTER_LEFT
//        verContainer.children.add(horContainer)
//
//        val sepBetweenTitleAndContent = Separator()
//        sepBetweenTitleAndContent.orientation = Orientation.HORIZONTAL
//        sepBetweenTitleAndContent.padding = Insets(8.0, 0.0, 8.0, 0.0)
//        verContainer.children.add(sepBetweenTitleAndContent)
//
//        val options = ArrayList<IODialogPart>()
//
//        val contentLabel = Label("Zawartość:")
//        verContainer.children.add(contentLabel)
//
//        val scrollingContent = ScrollPane()
//        scrollingContent.prefHeight = 200.0
//        scrollingContent.prefWidth = 324.0
//        verContainer.children.add(scrollingContent)
//        val optionsContainer = VBox()
//        scrollingContent.content = optionsContainer
//
//        for (value in input.operations) {
//            val option = IODialogPart()
//            option.load(value.code, value.input)
//            option.setRemover {
//                optionsContainer.children.remove(option)
//                options.remove(option)
//            }
//            options.add(option)
//            optionsContainer.children.add(option)
//        }
//
//        val loginButton = dialog.dialogPane.lookupButton(saveButtonType)
//        loginButton.isDisable = input.description.isEmpty()
//
//        title.textProperty()
//            .addListener { _, _, newValue -> loginButton.isDisable = newValue.trim { it <= ' ' }.isEmpty() }
//
//        val btnAddOption = Button("Dodaj")
//        btnAddOption.setOnAction {
//            val option = IODialogPart()
//            option.load("", true)
//            option.setRemover {
//                optionsContainer.children.remove(option)
//                options.remove(option)
//            }
//            options.add(option)
//            optionsContainer.children.add(option)
//        }
//        verContainer.children.add(btnAddOption)
//        VBox.setMargin(btnAddOption, Insets(16.0, 0.0, 16.0, 0.0))
//
//        dialog.dialogPane.content = verContainer
//
//        Platform.runLater { title.requestFocus() }
//
//        dialog.setResultConverter { dialogButton ->
//            if (dialogButton == saveButtonType) {
//                DescriptionAndIO(
//                    title.text,
//                    options.map {
//                        it.asField
//                    }
//                )
//            } else null
//        }
//
//        optionsContainer.children.addListener(object : ListChangeListener<Node> {
//
//            private var ignore = false
//
//            override fun onChanged(c: ListChangeListener.Change<out Node>) {
//                if (ignore) {
//                    return
//                }
//                ignore = true
//                Platform.runLater {
//                    val newHeight = (50 * optionsContainer.children.size).toDouble()
//                    optionsContainer.minHeight = newHeight
//                    ignore = false
//                }
//            }
//        })
//
//        return dialog
//    }

}