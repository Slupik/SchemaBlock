package io.github.slupik.schemablock.javafx.element.fx.port.connection.drawer

import io.github.slupik.schemablock.javafx.element.fx.arrow.ArrowDrawer
import io.github.slupik.schemablock.javafx.element.fx.arrow.Point
import io.github.slupik.schemablock.javafx.element.fx.port.connection.ConditionalPortsConnection
import io.github.slupik.schemablock.javafx.element.fx.port.connection.PortConnectionConfiguration
import io.github.slupik.schemablock.javafx.element.fx.port.connection.StandardPortsConnection
import io.github.slupik.schemablock.javafx.element.fx.port.element.Port
import io.reactivex.subjects.PublishSubject

/**
 * All rights reserved & copyright ©
 */
object MovableArrowDrawer {

    fun create(drawer: ArrowDrawer, configuration: PortConnectionConfiguration) {
        val source = configuration.source
        val target = configuration.target

        val emitter: PublishSubject<Pair<Point, Point>> = PublishSubject.create()

        when (configuration) {
            is StandardPortsConnection -> {
                drawer.drawMovableArrow(
                    emitter
                )
            }
            is ConditionalPortsConnection -> {
                val text = if (configuration.value) "T" else "F"
                drawer.drawMovableArrow(
                    emitter,
                    text
                )
            }
        }

        remitLocation(emitter, source, target)

        source.graphic.layoutXProperty().addListener { _, _, _ ->
            remitLocation(emitter, source, target)
        }
        source.graphic.layoutYProperty().addListener { _, _, _ ->
            remitLocation(emitter, source, target)
        }
        target.graphic.layoutXProperty().addListener { _, _, _ ->
            remitLocation(emitter, source, target)
        }
        target.graphic.layoutYProperty().addListener { _, _, _ ->
            remitLocation(emitter, source, target)
        }
    }

    private fun remitLocation(emitter: PublishSubject<Pair<Point, Point>>, source: Port, target: Port) {
        emitter.onNext(
            Pair(
                source.getLocation(),
                target.getLocation()
            )
        )
    }

}

private fun Port.getLocation(): Point =
    Point(this.graphic.layoutX, this.graphic.layoutY)