package io.github.slupik.schemablock.view.dagger

import dagger.Binds
import dagger.Module
import io.github.slupik.schemablock.view.entity.DefaultDiagram
import io.github.slupik.schemablock.view.entity.Diagram
import io.github.slupik.schemablock.view.logic.communication.output.ErrorTranslator
import io.github.slupik.schemablock.view.logic.communication.output.PolishErrorTranslator
import io.github.slupik.schemablock.view.logic.printer.GraphPrinter
import io.github.slupik.schemablock.view.logic.printer.JavaFxGraphPrinter
import io.github.slupik.schemablock.view.logic.zoom.ZoomController
import io.github.slupik.schemablock.view.logic.zoom.Zoomer
import io.github.slupik.schemablock.view.persistence.*
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
    abstract fun diagramSaver(diagramSaver: JsonDiagramSaver): DiagramSaver

    @Binds
    @Singleton
    abstract fun diagramLoader(diagramLoader: JsonDiagramLoader): DiagramLoader

    @Binds
    @Singleton
    abstract fun graphSaver(graphSaver: XmlGraphSaver): GraphSaver

    @Binds
    @Singleton
    abstract fun graphLoader(graphLoader: XmlGraphLoader): GraphLoader

    @Binds
    @Singleton
    abstract fun diagram(diagram: DefaultDiagram): Diagram

    @Binds
    @Singleton
    abstract fun fileChooser(fileChooser: FileGuiChooser): FileChooser

    @Binds
    @Singleton
    abstract fun translator(translator: PolishErrorTranslator): ErrorTranslator

    @Binds
    @Singleton
    abstract fun printer(printer: JavaFxGraphPrinter): GraphPrinter

}