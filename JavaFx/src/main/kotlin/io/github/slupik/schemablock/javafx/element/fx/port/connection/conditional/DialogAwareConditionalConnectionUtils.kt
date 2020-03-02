package io.github.slupik.schemablock.javafx.element.fx.port.connection.conditional

import io.reactivex.Single
import javafx.scene.control.Alert
import javafx.scene.control.ButtonBar
import javafx.scene.control.ButtonType
import java.util.*
import javax.inject.Inject


/**
 * All rights reserved & copyright ©
 */
class DialogAwareConditionalConnectionUtils @Inject constructor(

) : ConditionalConnectionUtils {

    override fun getValue(): Single<Optional<ButtonType>> {
        val alert = Alert(Alert.AlertType.CONFIRMATION)
        alert.title = "New connection with condition."
        alert.contentText = "What is value of this connection?"
        val okButton = ButtonType("True", ButtonBar.ButtonData.YES)
        val noButton = ButtonType("False", ButtonBar.ButtonData.NO)
        val cancelButton = ButtonType("Close", ButtonBar.ButtonData.CANCEL_CLOSE)
        alert.buttonTypes.setAll(okButton, noButton, cancelButton)
        return Single.just(alert.showAndWait())
    }

}