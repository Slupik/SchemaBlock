package io.github.slupik.schemablock.javafx.element.fx.port.connection.conditional

import io.reactivex.Single
import javafx.scene.control.ButtonType
import java.util.*

/**
 * All rights reserved & copyright ©
 */
interface ConditionalConnectionUtils {

    fun getValue(): Single<Optional<ButtonType>>

}