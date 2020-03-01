package io.github.slupik.schemablock.javafx.dagger

import dagger.Binds
import dagger.Module
import io.github.slupik.schemablock.javafx.element.block.restorer.BlockJsonRestorer
import io.github.slupik.schemablock.javafx.element.block.restorer.BlockRestorer
import io.github.slupik.schemablock.javafx.element.block.stringifier.BlockJsonStringifier
import io.github.slupik.schemablock.javafx.element.block.stringifier.BlockStringifier
import io.github.slupik.schemablock.javafx.element.fx.arrow.ArrowDrawer
import io.github.slupik.schemablock.javafx.element.fx.arrow.SimpleArrowDrawer
import io.github.slupik.schemablock.javafx.element.fx.element.holder.BlocksHolder
import io.github.slupik.schemablock.javafx.element.fx.element.holder.SheetBlocksHolder
import io.github.slupik.schemablock.javafx.element.fx.port.connection.ChainedElementProvider
import io.github.slupik.schemablock.javafx.element.fx.port.connection.checker.ConnectionAvailabilityChecker
import io.github.slupik.schemablock.javafx.element.fx.port.connection.checker.OwnerAwareAvailabilityChecker
import io.github.slupik.schemablock.javafx.element.fx.port.connection.deleter.ConnectionDeleter
import io.github.slupik.schemablock.javafx.element.fx.port.connection.deleter.PortConnectionsDeleter
import io.github.slupik.schemablock.javafx.element.fx.port.connection.drawer.ArrowConnectionDrawer
import io.github.slupik.schemablock.javafx.element.fx.port.connection.drawer.ConnectionDrawer
import io.github.slupik.schemablock.javafx.element.fx.port.connection.establishment.ConnectionEstablisher
import io.github.slupik.schemablock.javafx.element.fx.port.connection.establishment.DeletingConnectionEstablisher
import io.github.slupik.schemablock.javafx.element.fx.port.connection.event.ConnectionEventsObservable
import io.github.slupik.schemablock.javafx.element.fx.port.connection.event.PortConnectionEventsObservable
import io.github.slupik.schemablock.javafx.element.fx.port.connection.restorer.ConnectionsJsonRestorer
import io.github.slupik.schemablock.javafx.element.fx.port.connection.restorer.ConnectionsRestorer
import io.github.slupik.schemablock.javafx.element.fx.port.connection.storage.PortsConnectionProvider
import io.github.slupik.schemablock.javafx.element.fx.port.connection.storage.PortsConnectionsModifier
import io.github.slupik.schemablock.javafx.element.fx.port.connection.storage.PortsConnectionsStorage
import io.github.slupik.schemablock.javafx.element.fx.port.connection.stringifier.PortConnectionJsonStringifier
import io.github.slupik.schemablock.javafx.element.fx.port.connection.stringifier.PortConnectionStringifier
import io.github.slupik.schemablock.javafx.element.fx.port.holder.PortsHolder
import io.github.slupik.schemablock.javafx.element.fx.port.holder.SheetPortsHolder
import io.github.slupik.schemablock.javafx.element.fx.port.restorer.PortJsonRestorer
import io.github.slupik.schemablock.javafx.element.fx.port.restorer.PortRestorer
import io.github.slupik.schemablock.javafx.element.fx.port.spawner.PortSpawner
import io.github.slupik.schemablock.javafx.element.fx.port.spawner.RoundedPortSpawner
import io.github.slupik.schemablock.javafx.element.fx.port.stringifier.PortJsonStringifier
import io.github.slupik.schemablock.javafx.element.fx.port.stringifier.PortStringifier
import io.github.slupik.schemablock.javafx.element.fx.schema.Schema
import io.github.slupik.schemablock.javafx.element.fx.schema.VisibleSchema
import io.github.slupik.schemablock.javafx.element.fx.schema.restorer.SchemaJsonRestorer
import io.github.slupik.schemablock.javafx.element.fx.schema.restorer.SchemaRestorer
import io.github.slupik.schemablock.javafx.element.fx.schema.stringifier.SchemaJsonStringifier
import io.github.slupik.schemablock.javafx.element.fx.schema.stringifier.SchemaStringifier
import io.github.slupik.schemablock.javafx.element.fx.sheet.spawner.ElementsSpawner
import io.github.slupik.schemablock.javafx.element.fx.sheet.spawner.StartElementSpawner
import io.github.slupik.schemablock.javafx.logic.execution.BlocksColorizer
import io.github.slupik.schemablock.javafx.logic.execution.DefaultBlocksColorizer
import javax.inject.Singleton

/**
 * All rights reserved & copyright ©
 */
@Module(
    includes = [GraphicElementsModule::class, BlocksProvidingModule::class]
)
abstract class BlocksBindingsModule {

    @Binds
    abstract fun provideConnectionsModifier(storage: PortsConnectionsStorage): PortsConnectionsModifier

    @Binds
    abstract fun provideConnectionsProvider(storage: PortsConnectionsStorage): PortsConnectionProvider

    @Binds
    abstract fun provideChainedElementProvider(storage: PortsConnectionsStorage): ChainedElementProvider

    @Binds
    @Singleton
    abstract fun provideConnectionEstablisher(establisher: DeletingConnectionEstablisher): ConnectionEstablisher

    @Binds
    @Singleton
    abstract fun provideAvailabilityChecker(checker: OwnerAwareAvailabilityChecker): ConnectionAvailabilityChecker

    @Binds
    @Singleton
    abstract fun provideConnectionDeleter(deleter: PortConnectionsDeleter): ConnectionDeleter

    @Binds
    @Singleton
    abstract fun providePortsHolder(holder: SheetPortsHolder): PortsHolder

    @Binds
    @Singleton
    abstract fun provideConnectionDrawer(drawer: ArrowConnectionDrawer): ConnectionDrawer

    @Binds
    @Singleton
    abstract fun provideArrowDrawer(drawer: SimpleArrowDrawer): ArrowDrawer

    @Binds
    @Singleton
    abstract fun provideConnectionEventsObservable(drawer: PortConnectionEventsObservable): ConnectionEventsObservable

    @Binds
    @Singleton
    abstract fun provideBlocksHolder(holder: SheetBlocksHolder): BlocksHolder

    @Binds
    @Singleton
    abstract fun provideBlocksColorizer(colorizer: DefaultBlocksColorizer): BlocksColorizer

    @Binds
    abstract fun providePortsStringifier(converter: PortJsonStringifier): PortStringifier

    @Binds
    abstract fun provideBlocksStringifier(converter: BlockJsonStringifier): BlockStringifier

    @Binds
    abstract fun providePortConnectionStringifier(converter: PortConnectionJsonStringifier): PortConnectionStringifier

    @Binds
    abstract fun provideSchemaStringifier(converter: SchemaJsonStringifier): SchemaStringifier

    @Binds
    abstract fun provideSchema(schema: VisibleSchema): Schema

    @Binds
    abstract fun provideSchemaRestorer(restorer: SchemaJsonRestorer): SchemaRestorer

    @Binds
    abstract fun provideBlockRestorer(restorer: BlockJsonRestorer): BlockRestorer

    @Binds
    abstract fun providePortSpawner(restorer: RoundedPortSpawner): PortSpawner

    @Binds
    abstract fun providePortRestorer(restorer: PortJsonRestorer): PortRestorer

    @Binds
    abstract fun provideElementsSpawner(spawner: StartElementSpawner): ElementsSpawner

    @Binds
    abstract fun provideConnectionsRestorer(restorer: ConnectionsJsonRestorer): ConnectionsRestorer

}