package io.github.slupik.schemablock.view.logic

import javafx.scene.transform.Scale

/**
 * All rights reserved & copyright ©
 */
interface Zoomer {
    val scale: Scale
    val currentZoomFactor: Double

    fun zoom(factor: Double)

}