package io.github.slupik.schemablock.javafx.element.fx.port.connection.event

import io.reactivex.Observable

/**
 * All rights reserved & copyright ©
 */
interface ConnectionEventsObservable {

    val emitter: Observable<ConnectionEvent>

    fun run()

}