package io.github.slupik.schemablock.javafx.element.fx.arrow

import io.reactivex.Observable

/**
 * All rights reserved & copyright ©
 */
interface ArrowDrawer {

    fun drawMovableArrow(observableCoords: Observable<Pair<Point, Point>>) {
        drawMovableArrow(observableCoords, "")
    }
    fun drawMovableArrow(observableCoords: Observable<Pair<Point, Point>>, desc: String)

    fun draw(from: Point, to: Point) {
        draw(from, to, "")
    }
    fun draw(from: Point, to: Point, desc: String)

}