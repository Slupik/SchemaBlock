package io.github.slupik.schemablock.logic.executor.dagger

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