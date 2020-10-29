package io.github.slupik.schemablock.javafx.element.block.size

import javafx.scene.control.Label
import javafx.scene.text.Font
import javafx.scene.text.FontPosture
import javafx.scene.text.Text

/**
 * All rights reserved & copyright ©
 */
private const val MAX_FONT_SIZE = 15.0

internal class TextSizeProvider constructor(
    val label: Label
) {

    internal fun getFontForContainerSize(width: Double, height: Double): Font {
        var font = getFontFittedForWidth(width)

        if (isFontHigherThanAllowed(font, height)) {
            font = getFontFittedForHeight(height)
        }

        return if(font.size > MAX_FONT_SIZE) getMaxFont() else font
    }

    private fun getMaxFont(): Font =
        Font.font(label.font.family, FontPosture.findByName(label.font.style), MAX_FONT_SIZE)

    private fun isFontHigherThanAllowed(font: Font, maxHeight: Double): Boolean {
        val text = getText()
        val textArea = Text(text)
        textArea.font = font

        return textArea.boundsInLocal.height > maxHeight
    }

    private fun getFontFittedForHeight(height: Double): Font {
        val unitHeight = getUnitHeight()
        return Font.font(label.font.family, FontPosture.findByName(label.font.style), height / unitHeight)
    }

    private fun getUnitHeight(): Double {
        val text = getUnitText()
        return text.boundsInLocal.height
    }

    private fun getFontFittedForWidth(width: Double): Font {
        val unitWidth = getUnitWidth()
        return Font.font(label.font.family, FontPosture.findByName(label.font.style), width / unitWidth)
    }

    private fun getUnitWidth(): Double {
        val text = getUnitText()
        return text.boundsInLocal.width
    }

    private fun getUnitText(): Text {
        val unitFont = getUnitFont()
        val content = getText()

        val text = Text(content)
        text.font = unitFont

        return text
    }

    private fun getText(): String? = label.text

    private fun getUnitFont(): Font? =
        Font.font(label.font.family, FontPosture.findByName(label.font.style), 1.0)

}