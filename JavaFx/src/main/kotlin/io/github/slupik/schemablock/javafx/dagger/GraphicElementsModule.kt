package io.github.slupik.schemablock.javafx.dagger

import dagger.Module
import dagger.Provides
import javafx.scene.layout.Pane

/**
 * All rights reserved & copyright ©
 */
@Module
class GraphicElementsModule constructor(
    private val sheet: Pane
) {

    @Provides
    @Sheet
    fun provideSheet(): Pane =
        sheet

}