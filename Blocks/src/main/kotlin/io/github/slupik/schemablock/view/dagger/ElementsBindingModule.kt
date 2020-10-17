package io.github.slupik.schemablock.view.dagger

import dagger.Binds
import dagger.Module
import io.github.slupik.schemablock.view.logic.ZoomController
import io.github.slupik.schemablock.view.logic.Zoomer
import io.github.slupik.schemablock.view.persistence.graph.*
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
    abstract fun graphLoader(graphLoader: DefaultGraphLoader): GraphLoader

    @Binds
    @Singleton
    abstract fun fileChooser(fileChooser: FileGuiChooser): FileChooser

}