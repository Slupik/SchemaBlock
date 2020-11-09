package io.github.slupik.schemablock.view.dialog

import io.github.slupik.schemablock.view.dialog.data.CodeAndDescription
import io.github.slupik.schemablock.view.dialog.data.DescriptionAndIO
import io.github.slupik.schemablock.view.dialog.data.UiBlockSettings
import javafx.application.Platform
import javafx.collections.ListChangeListener
import javafx.geometry.Insets
import javafx.geometry.Orientation
import javafx.geometry.Pos
import javafx.scene.Node
import javafx.scene.control.*
import javafx.scene.control.ButtonType
import javafx.scene.layout.HBox
import javafx.scene.layout.VBox
import java.util.*


/**
 * All rights reserved & copyright ©
 */
object DialogFactory {

    fun buildWithDescAndContent(input: CodeAndDescription): Dialog<UiBlockSettings> {
        val dialog = Dialog<UiBlockSettings>()
        dialog.title = "Edycja bloku"

        val saveButtonType = ButtonType("Zapisz", ButtonBar.ButtonData.OK_DONE)
        val cancelButtonType = ButtonType("Anuluj", ButtonBar.ButtonData.CANCEL_CLOSE)
        dialog.dialogPane.buttonTypes.addAll(cancelButtonType, saveButtonType)

        val verContainer = VBox()
        verContainer.padding = Insets(8.0, 16.0, 8.0, 8.0)

        val descriptionLabel = Label("Nazwa")
        descriptionLabel.padding = Insets(0.0, 0.0, 8.0, 0.0)
        verContainer.children.add(descriptionLabel)

        val description = TextField(input.description)
        description.promptText = "Krótka nazwa"
        verContainer.children.add(description)

        val contentLabel = Label("Zawartość")
        verContainer.children.add(contentLabel)
        contentLabel.padding = Insets(8.0, 0.0, 8.0, 0.0)

        val content = TextArea(input.code)
        content.promptText = "Kod"
        content.prefWidth = 400.0
        content.prefHeight = 400.0
        verContainer.children.add(content)

        val btnSave = dialog.dialogPane.lookupButton(saveButtonType)
        btnSave.isDisable = input.description.isEmpty()

        description.textProperty()
            .addListener { _, _, newValue -> btnSave.isDisable = newValue.trim { it <= ' ' }.isEmpty() }

        dialog.dialogPane.content = verContainer

        Platform.runLater { description.requestFocus() }

        dialog.setResultConverter { dialogButton ->
            if (dialogButton == saveButtonType) {
                CodeAndDescription(
                    description.text,
                    content.text
                )
            } else null
        }

        return dialog
    }

    fun buildWithDescAndShortContent(input: CodeAndDescription): Dialog<UiBlockSettings> {
        val dialog = Dialog<UiBlockSettings>()
        dialog.title = "Edycja bloku"

        val saveButtonType = ButtonType("Zapisz", ButtonBar.ButtonData.OK_DONE)
        dialog.dialogPane.buttonTypes.addAll(saveButtonType, ButtonType.CANCEL)

        val verContainer = VBox()
        verContainer.padding = Insets(20.0, 150.0, 10.0, 10.0)

        val horContainer = HBox()

        val title = TextField(input.description)
        title.promptText = "Krótki tytuł"

        horContainer.children.add(Label("Tytuł:  "))
        horContainer.children.add(title)
        horContainer.alignment = Pos.CENTER_LEFT
        verContainer.children.add(horContainer)

        val contentLabel = Label("Zdanie logiczne:")
        verContainer.children.add(contentLabel)
        contentLabel.padding = Insets(8.0, 0.0, 8.0, 0.0)
        val content = TextField(input.code)
        content.promptText = "Wyrażenie jeżeli..."
        content.prefWidth = 400.0
        verContainer.children.add(content)

        val loginButton = dialog.dialogPane.lookupButton(saveButtonType)
        loginButton.isDisable = input.description.isEmpty()

        title.textProperty()
            .addListener { _, _, newValue -> loginButton.isDisable = newValue.trim { it <= ' ' }.isEmpty() }

        dialog.dialogPane.content = verContainer

        Platform.runLater { title.requestFocus() }

        dialog.setResultConverter { dialogButton ->
            if (dialogButton == saveButtonType) {
                CodeAndDescription(
                    title.text,
                    content.text
                )
            } else null
        }

        return dialog
    }

    fun buildIO(input: DescriptionAndIO): Dialog<UiBlockSettings> {
        val dialog = Dialog<UiBlockSettings>()
        dialog.title = "Edycja bloku"

        val saveButtonType = ButtonType("Zapisz", ButtonBar.ButtonData.OK_DONE)
        dialog.dialogPane.buttonTypes.addAll(saveButtonType, ButtonType.CANCEL)

        val verContainer = VBox()
        verContainer.padding = Insets(20.0, 50.0, 10.0, 10.0)

        val horContainer = HBox()

        val title = TextField(input.description)
        title.promptText = "Krótki tytuł"

        val titleLbl = Label("Tytuł:  ")
        titleLbl.padding = Insets(8.0, 0.0, 8.0, 0.0)

        horContainer.children.add(titleLbl)
        horContainer.children.add(title)
        horContainer.alignment = Pos.CENTER_LEFT
        verContainer.children.add(horContainer)

        val sepBetweenTitleAndContent = Separator()
        sepBetweenTitleAndContent.orientation = Orientation.HORIZONTAL
        sepBetweenTitleAndContent.padding = Insets(8.0, 0.0, 8.0, 0.0)
        verContainer.children.add(sepBetweenTitleAndContent)

        val options = ArrayList<IODialogPart>()

        val contentLabel = Label("Zawartość:")
        verContainer.children.add(contentLabel)

        val scrollingContent = ScrollPane()
        scrollingContent.prefHeight = 200.0
        scrollingContent.prefWidth = 324.0
        verContainer.children.add(scrollingContent)
        val optionsContainer = VBox()
        scrollingContent.content = optionsContainer

        for (value in input.operations) {
            val option = IODialogPart()
            option.load(value.code, value.input)
            option.setRemover {
                optionsContainer.children.remove(option)
                options.remove(option)
            }
            options.add(option)
            optionsContainer.children.add(option)
        }

        val loginButton = dialog.dialogPane.lookupButton(saveButtonType)
        loginButton.isDisable = input.description.isEmpty()

        title.textProperty()
            .addListener { _, _, newValue -> loginButton.isDisable = newValue.trim { it <= ' ' }.isEmpty() }

        val btnAddOption = Button("Dodaj")
        btnAddOption.setOnAction {
            val option = IODialogPart()
            option.load("", true)
            option.setRemover {
                optionsContainer.children.remove(option)
                options.remove(option)
            }
            options.add(option)
            optionsContainer.children.add(option)
        }
        verContainer.children.add(btnAddOption)
        VBox.setMargin(btnAddOption, Insets(16.0, 0.0, 16.0, 0.0))

        dialog.dialogPane.content = verContainer

        Platform.runLater { title.requestFocus() }

        dialog.setResultConverter { dialogButton ->
            if (dialogButton == saveButtonType) {
                DescriptionAndIO(
                    title.text,
                    options.map {
                        it.asField
                    }
                )
            } else null
        }

        optionsContainer.children.addListener(object : ListChangeListener<Node> {

            private var ignore = false

            override fun onChanged(c: ListChangeListener.Change<out Node>) {
                if (ignore) {
                    return
                }
                ignore = true
                Platform.runLater {
                    val newHeight = (50 * optionsContainer.children.size).toDouble()
                    optionsContainer.minHeight = newHeight
                    ignore = false
                }
            }
        })

        return dialog
    }

}