package io.github.slupik.schemablock.javafx.dagger

import dagger.Binds
import dagger.Module
import io.github.slupik.schemablock.javafx.element.fx.port.connection.ChainedElementProvider
import io.github.slupik.schemablock.javafx.element.fx.port.connection.checker.ConnectionAvailabilityChecker
import io.github.slupik.schemablock.javafx.element.fx.port.connection.checker.OwnerAwareAvailabilityChecker
import io.github.slupik.schemablock.javafx.element.fx.port.connection.deleter.ConnectionDeleter
import io.github.slupik.schemablock.javafx.element.fx.port.connection.deleter.PortConnectionsDeleter
import io.github.slupik.schemablock.javafx.element.fx.port.connection.establishment.ConnectionEstablisher
import io.github.slupik.schemablock.javafx.element.fx.port.connection.establishment.DeletingConnectionEstablisher
import io.github.slupik.schemablock.javafx.element.fx.port.connection.storage.PortsConnectionProvider
import io.github.slupik.schemablock.javafx.element.fx.port.connection.storage.PortsConnectionsModifier
import io.github.slupik.schemablock.javafx.element.fx.port.connection.storage.PortsConnectionsStorage
import io.github.slupik.schemablock.javafx.element.fx.port.holder.PortsHolder
import io.github.slupik.schemablock.javafx.element.fx.port.holder.SheetPortsHolder

/**
 * All rights reserved & copyright ©
 */
@Module(
    includes = [GraphicElementsModule::class]
)
abstract class BlocksModule {

    @Binds
    abstract fun provideConnectionsModifier(storage: PortsConnectionsStorage): PortsConnectionsModifier

    @Binds
    abstract fun provideConnectionsProvider(storage: PortsConnectionsStorage): PortsConnectionProvider

    @Binds
    abstract fun provideChainedElementProvider(storage: PortsConnectionsStorage): ChainedElementProvider

    @Binds
    abstract fun provideConnectionEstablisher(establisher: DeletingConnectionEstablisher): ConnectionEstablisher

    @Binds
    abstract fun provideAvailabilityChecker(checker: OwnerAwareAvailabilityChecker): ConnectionAvailabilityChecker

    @Binds
    abstract fun provideConnectionDeleter(deleter: PortConnectionsDeleter): ConnectionDeleter

    @Binds
    abstract fun providePortsHolder(deleter: SheetPortsHolder): PortsHolder

}