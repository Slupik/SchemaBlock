package io.github.slupik.schemablock.view.logic.execution.dagger

import javax.inject.Qualifier

/**
 * All rights reserved & copyright ©
 */
@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class OneTime

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class Continuous

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class Async

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class AtomicMemory