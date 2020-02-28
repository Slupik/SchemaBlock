package io.github.slupik.schemablock.javafx.element.fx.port.connection.event

import io.github.slupik.schemablock.javafx.dagger.JavaFxSheet
import io.github.slupik.schemablock.javafx.element.fx.port.element.Port
import io.github.slupik.schemablock.javafx.element.fx.port.holder.PortsHolder
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import javafx.geometry.Point2D
import javafx.scene.input.MouseEvent
import javafx.scene.layout.Pane
import javax.inject.Inject

/**
 * All rights reserved & copyright ©
 */
class PortConnectionEventsObservable @Inject constructor(
    @JavaFxSheet private val sheet: Pane,
    private val holder: PortsHolder
) : ConnectionEventsObservable {

    private val subject: PublishSubject<ConnectionEvent> = PublishSubject.create()
    override val emitter: Observable<ConnectionEvent> = subject

    private var sourceOfConnection: Port? = null
    private val hoveredPorts: MutableList<Port> = mutableListOf()

    override fun run() {
        holder.additions.subscribe {
            bindEvents(it.first)
        }

        sheet.addEventFilter(MouseEvent.MOUSE_MOVED) { event ->
            onMouseChangePosition(event.x, event.y)
        }
        sheet.addEventFilter(MouseEvent.MOUSE_DRAGGED) { event ->
            onMouseChangePosition(event.x, event.y)
        }
        sheet.addEventFilter(MouseEvent.MOUSE_RELEASED) {
            onMouseReleased(it.x, it.y)
        }
    }

    private fun onMouseReleased(eventX: Double, eventY: Double) {
        val source = sourceOfConnection
        if (source != null) {
            stopSearching(source)

            val target = holder.ports.keys.firstOrNull { it.containsGlobalPoint(eventX, eventY) }
            if(target!=null) {
                tryConnection(source, target)
            }
        }
    }

    private fun onMouseChangePosition(eventX: Double, eventY: Double) {
        for(pair in holder.ports) {
            val port = pair.key
            val graphic = port.graphic
            val x = eventX - graphic.layoutX
            val y = eventY - graphic.layoutY

            if(hoveredPorts.contains(port) && !graphic.contains(Point2D(x, y))) {
                hoveredPorts.remove(port)
                stopCheckingPort(port)
            }
            if(!hoveredPorts.contains(port) && graphic.contains(Point2D(x, y))) {
                hoveredPorts.add(port)
                val source = sourceOfConnection
                if (source == null) {
                    checkConnectionReadiness(port)
                } else {
                    checkConnectionPossibility(source, port)
                }
            }
        }

    }

    private fun bindEvents(port: Port) {
        port.owner.background.addEventFilter(MouseEvent.MOUSE_RELEASED) {
            if (it.target == port.owner.background) {
                stopSearching(port)
            }
        }
        port.mask.addEventFilter(MouseEvent.MOUSE_EXITED) {
            port.markAsNeutral()
        }
        port.mask.addEventFilter(MouseEvent.MOUSE_PRESSED) {
            sourceOfConnection = port
            startSearchingConnection(port)
        }
    }

    private fun checkConnectionPossibility(
        source: Port,
        target: Port
    ) {
        subject.onNext(
            CheckConnectionPossibilityEvent(
                source = source,
                target = target
            )
        )
    }

    private fun checkConnectionReadiness(port: Port) {
        subject.onNext(
            CheckConnectionReadinessEvent(
                subject = port
            )
        )
    }

    private fun stopCheckingPort(port: Port) {
        subject.onNext(
            StopCheckingPortEvent(
                subject = port
            )
        )
    }

    private fun startSearchingConnection(port: Port) {
        subject.onNext(
            StartSearchingConnectionEvent(
                port
            )
        )
    }

    private fun tryConnection(source: Port, target: Port) {
        sourceOfConnection = null

        subject.onNext(
            TryConnectionEvent(
                source = source,
                target = target
            )
        )
    }

    private fun stopSearching(port: Port) {
        sourceOfConnection = null

        subject.onNext(
            StopSearchingConnectionEvent(
                subject = port
            )
        )
    }

}

private fun Port.containsGlobalPoint(globalX: Double, globalY: Double): Boolean {
    val x = globalX - this.graphic.layoutX
    val y = globalY - this.graphic.layoutY
    return this.graphic.contains(x, y)
}
