package io.github.slupik.schemablock.javafx.element.fx.port.connection.drawer

import io.github.slupik.schemablock.javafx.dagger.JavaFxSheet
import io.github.slupik.schemablock.javafx.element.fx.arrow.Arrow
import io.github.slupik.schemablock.javafx.element.fx.arrow.ArrowDrawer
import io.github.slupik.schemablock.javafx.element.fx.arrow.Point
import io.github.slupik.schemablock.javafx.element.fx.port.connection.ConditionalPortsConnection
import io.github.slupik.schemablock.javafx.element.fx.port.connection.PortConnectionConfiguration
import io.github.slupik.schemablock.javafx.element.fx.port.connection.StandardPortsConnection
import io.github.slupik.schemablock.javafx.element.fx.port.connection.establishment.ConnectionEstablisher
import io.github.slupik.schemablock.javafx.element.fx.port.connection.event.*
import io.github.slupik.schemablock.javafx.element.fx.port.connection.storage.ConnectionStorageKey
import io.github.slupik.schemablock.javafx.element.fx.port.connection.storage.PortConnectionsHolder
import io.github.slupik.schemablock.javafx.element.fx.port.element.Port
import io.github.slupik.schemablock.javafx.element.fx.port.holder.PortAccessibility
import io.github.slupik.schemablock.javafx.element.fx.port.holder.PortsHolder
import io.reactivex.subjects.PublishSubject
import javafx.scene.input.MouseEvent
import javafx.scene.layout.Pane
import javax.inject.Inject

/**
 * All rights reserved & copyright ©
 */
class ArrowConnectionDrawer @Inject constructor(
    private val portsHolder: PortsHolder,
    private val establisher: ConnectionEstablisher,
    private val connectionsHolder: PortConnectionsHolder,
    private val arrowDrawer: ArrowDrawer,
    private val observableConnectionEvents: ConnectionEventsObservable,
    @JavaFxSheet private val container: Pane
) : ConnectionDrawer {

    private val arrows = hashMapOf<PortConnectionConfiguration, Arrow>()
    private var arrowSource: Port? = null
    private var arrowCoords: PublishSubject<Pair<Point, Point>>? = null

    override fun run() {
        observableConnectionEvents.run()
        observableConnectionEvents.emitter.subscribe {
            when (it) {
                is StartSearchingConnectionEvent -> {
                    val accessibility = portsHolder.getAccessibilityFor(it.source)
                    if (accessibility != null) {
                        onStartSearching(it.source, accessibility)
                    }
                }
                is StopSearchingConnectionEvent -> {
                    onStopSearching(it.subject)
                }
                is StopCheckingPortEvent -> {
                    it.subject.markAsNeutral()
                }
                is CheckConnectionReadinessEvent -> {
                    val accessibility = portsHolder.getAccessibilityFor(it.subject)
                    if (accessibility != null) {
                        onCheckConnectionReadiness(it.subject, accessibility)
                    }
                }
                is CheckConnectionPossibilityEvent -> {
                    val accessibility = portsHolder.getAccessibilityFor(it.target)
                    if (accessibility != null) {
                        onCheckConnectionPossibility(it.source, it.target, accessibility)
                    }
                }
                is TryConnectionEvent -> {
                    val accessibility = portsHolder.getAccessibilityFor(it.source)
                    if (accessibility != null) {
                        onTryConnection(it.source, it.target, accessibility)
                    }
                }
            }
        }
        connectionsHolder.establishments.subscribe {
            drawConnection(it)
        }
        connectionsHolder.deletions.subscribe {
            eraseConnection(it.first)
        }
        container.addEventFilter(MouseEvent.MOUSE_DRAGGED) {
            val source = arrowSource
            val coords = arrowCoords
            if (source != null && coords != null) {
                coords.onNext(
                    Pair(
                        Point(source.graphic.layoutX, source.graphic.layoutY),
                        Point(it.x, it.y)
                    )
                )
            }
        }
    }

    private fun eraseConnection(key: ConnectionStorageKey) {
        arrows.filterKeys {
            it.source.elementId == key.sourcePortId
        }.forEach { (key, arrow) ->
            arrow.delete()
            arrows.remove(key)
        }
    }

    private fun drawConnection(configuration: PortConnectionConfiguration?) {
        configuration?.let {
            arrows.put(
                it,
                MovableArrowDrawer.create(drawer = arrowDrawer, configuration = it)
            )
        }
    }

    private fun onStartSearching(
        source: Port,
        accessibility: PortAccessibility
    ) {
        if (accessibility.source) {
            val observableCoords: PublishSubject<Pair<Point, Point>> = PublishSubject.create()

            arrowDrawer.drawMovableArrow(observableCoords)
            observableCoords.onNext(
                Pair(
                    Point(source.graphic.layoutX, source.graphic.layoutY),
                    Point(source.graphic.layoutX, source.graphic.layoutY)
                )
            )

            arrowCoords = observableCoords
            arrowSource = source
        }
    }

    private fun onStopSearching(subject: Port) {
        arrowCoords?.onComplete()
        arrowCoords = null
        arrowSource = null
    }

    private fun onCheckConnectionReadiness(port: Port, accessibility: PortAccessibility) {
        if (accessibility.source) {
            port.markAsActive()
        } else {
            port.markAsDisabled()
        }
    }

    private fun onCheckConnectionPossibility(source: Port, target: Port, targetAccessibility: PortAccessibility) {
        if (targetAccessibility.target) {
            target.markAsActive()
        } else {
            target.markAsDisabled()
        }
    }

    private fun onTryConnection(source: Port, target: Port, sourceAccessibility: PortAccessibility) {
        source.markAsNeutral()
        target.markAsNeutral()

        val configuration =
            if (sourceAccessibility == PortAccessibility.CONDITIONAL_INPUT) {
                ConditionalPortsConnection(
                    source,
                    target,
                    true
                )
            } else {
                StandardPortsConnection(
                    source,
                    target
                )
            }

        onStopSearching(source)
        establisher.establishConnection(configuration)
    }

}
