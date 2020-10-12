package io.github.slupik.schemablock.view.dagger

import dagger.Binds
import dagger.Module
import io.github.slupik.schemablock.view.logic.ZoomController
import io.github.slupik.schemablock.view.logic.Zoomer
import io.github.slupik.schemablock.view.persistance.DefaultGraphSaver
import io.github.slupik.schemablock.view.persistance.FileChooser
import io.github.slupik.schemablock.view.persistance.FileGuiChooser
import io.github.slupik.schemablock.view.persistance.GraphSaver
import javax.inject.Singleton

/**
 * All rights reserved & copyright ©
 */
@Module
abstract class ElementsBindingModule {

    @Binds
    @Singleton
    abstract fun zoomer(zoomer: ZoomController): Zoomer

    @Binds
    @Singleton
    abstract fun graphSaver(graphSaver: DefaultGraphSaver): GraphSaver

    @Binds
    @Singleton
    abstract fun fileChooser(fileChooser: FileGuiChooser): FileChooser

}