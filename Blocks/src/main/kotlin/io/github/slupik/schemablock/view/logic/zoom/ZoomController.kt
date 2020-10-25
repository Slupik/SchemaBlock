package io.github.slupik.schemablock.view.logic.zoom

import de.tesis.dynaware.grapheditor.GraphEditorContainer
import javafx.scene.transform.Scale
import javax.inject.Inject


/**
 * All rights reserved & copyright ©
 */
class ZoomController @Inject constructor(
    private val graphEditorContainer: GraphEditorContainer
) : Zoomer {

    private var _currentZoomFactor = 1.0
    private var _scale = Scale(_currentZoomFactor, _currentZoomFactor, 0.0, 0.0)
    override val scale: Scale
        get() = _scale
    override val currentZoomFactor: Double
        get() = _currentZoomFactor

    init {
        scale.yProperty().bind(scale.xProperty())
    }

    /**
     * Sets a new zoom factor.
     * Note that everything will look crap if the zoom factor is non-integer.
     * @param factor the new zoom factor
     */
    override fun zoom(factor: Double) {
        val zoomFactorRatio = factor / _currentZoomFactor

        val currentCenterX = graphEditorContainer.windowXProperty().get()
        val currentCenterY = graphEditorContainer.windowYProperty().get()

        // Cache-while-panning is sometimes very sluggish when zoomed in.
        graphEditorContainer.isCacheWhilePanning = factor == 1.0

        _scale.x = factor
        graphEditorContainer.panTo(currentCenterX * zoomFactorRatio, currentCenterY * zoomFactorRatio)
        _currentZoomFactor = factor
    }

}