package io.github.slupik.schemablock.javafx.element.fx.arrow

import io.github.slupik.schemablock.javafx.dagger.Sheet
import io.reactivex.Observable
import io.reactivex.rxkotlin.subscribeBy
import javafx.scene.layout.Pane
import javax.inject.Inject

/**
 * All rights reserved & copyright ©
 */
class SimpleArrowDrawer @Inject constructor(
    @Sheet private val sheet: Pane
): ArrowDrawer {

    override fun drawMovableArrow(observableCoords: Observable<Pair<Point, Point>>, desc: String) {
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
    }

    override fun draw(from: Point, to: Point, desc: String) {
        val arrow = Arrow()
        arrow.setDesc(desc)
        sheet.children.add(arrow)
        arrow.setStart(from.x, from.y)
        arrow.setEnd(to.x, to.y)
    }

}