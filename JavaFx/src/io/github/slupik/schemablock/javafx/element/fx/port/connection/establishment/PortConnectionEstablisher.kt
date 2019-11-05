package io.github.slupik.schemablock.javafx.element.fx.port.connection.establishment

import io.github.slupik.schemablock.javafx.element.fx.port.connection.PortConnectionConfiguration
import io.reactivex.Observable

/**
 * All rights reserved & copyright ©
 */

interface PortConnectionEstablisher {

    val establishments: Observable<PortConnectionConfiguration>

    fun establishConnection(configuration: PortConnectionConfiguration)

}