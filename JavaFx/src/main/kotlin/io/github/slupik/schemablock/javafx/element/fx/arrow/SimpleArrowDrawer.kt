package io.github.slupik.schemablock.javafx.element.fx.arrow

import io.github.slupik.schemablock.javafx.dagger.JavaFxSheet
import io.reactivex.Observable
import io.reactivex.rxkotlin.subscribeBy
import javafx.scene.layout.Pane
import javax.inject.Inject

/**
 * All rights reserved & copyright ©
 */
class SimpleArrowDrawer @Inject constructor(
    @JavaFxSheet private val sheet: Pane
): ArrowDrawer {

    override fun drawMovableArrow(observableCoords: Observable<Pair<Point, Point>>, desc: String): Arrow {
        val arrow = Arrow()
        arrow.setDesc(desc)
        sheet.children.add(arrow)
        observableCoords.subscribeBy(
            onNext = {
                val from = it.first
                arrow.setStart(from.x, from.y)

                val to = it.second
                arrow.setEnd(to.x, to.y)
            },
            onComplete = {
                sheet.children.remove(arrow)
            },
            onError = {
                sheet.children.remove(arrow)
            }
        )
        return arrow
    }

    override fun draw(from: Point, to: Point, desc: String): Arrow {
        val arrow = Arrow()
        arrow.setDesc(desc)
        sheet.children.add(arrow)
        arrow.setStart(from.x, from.y)
        arrow.setEnd(to.x, to.y)
        return arrow
    }

}