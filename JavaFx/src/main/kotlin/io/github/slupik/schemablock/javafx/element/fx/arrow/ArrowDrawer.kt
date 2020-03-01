package io.github.slupik.schemablock.javafx.element.fx.arrow

import io.reactivex.Observable

/**
 * All rights reserved & copyright ©
 */
interface ArrowDrawer {

    fun drawMovableArrow(observableCoords: Observable<Pair<Point, Point>>): Arrow =
        drawMovableArrow(observableCoords, "")
    fun drawMovableArrow(observableCoords: Observable<Pair<Point, Point>>, desc: String): Arrow

    fun draw(from: Point, to: Point): Arrow =
        draw(from, to, "")
    fun draw(from: Point, to: Point, desc: String): Arrow

}