package io.github.slupik.schemablock.view.dagger

import dagger.Binds
import dagger.Module
import io.github.slupik.schemablock.view.logic.ZoomController
import io.github.slupik.schemablock.view.logic.Zoomer
import javax.inject.Singleton

/**
 * All rights reserved & copyright ©
 */
@Module
abstract class ElementsBindingModule {

    @Binds
    @Singleton
    abstract fun zoomer(zoomer: ZoomController): Zoomer

}