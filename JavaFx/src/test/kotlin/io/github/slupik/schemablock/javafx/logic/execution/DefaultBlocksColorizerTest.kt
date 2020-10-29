package io.github.slupik.schemablock.javafx.logic.execution

import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import io.github.slupik.schemablock.javafx.element.block.implementation.OperationsUiBlock
import io.github.slupik.schemablock.logic.executor.block.Void
import io.github.slupik.schemablock.logic.executor.diagram.*
import io.reactivex.subjects.PublishSubject
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

/**
 * All rights reserved & copyright ©
 */
internal class DefaultBlocksColorizerTest {

    private lateinit var sut: DefaultBlocksColorizer

    companion object {
        @BeforeAll
        @JvmStatic
        fun init() {
            MockitoAnnotations.initMocks(this)
        }
    }

    @BeforeEach
    fun initSut() {
        sut = DefaultBlocksColorizer()
    }

    @Test
    fun `mark block as executed when it is executed`() {
        val block = Mockito.mock(OperationsUiBlock::class.java)
        val emitter = PublishSubject.create<ExecutionEvent>()

        sut.inject(emitter)

        emitter.onNext(
            ExecutionStart
        )

        emitter.onNext(
            PreExecutionEvent(
                block
            )
        )

        verify(block, times(1)).markAsExecuting()
    }

    @Test
    fun `unmark block as executed`() {
        val blockA = Mockito.mock(OperationsUiBlock::class.java)
        val blockB = Mockito.mock(OperationsUiBlock::class.java)
        val emitter = PublishSubject.create<ExecutionEvent>()

        sut.inject(emitter)

        emitter.onNext(
            ExecutionStart
        )

        emitter.onNext(
            PreExecutionEvent(
                blockA
            )
        )
        emitter.onNext(
            PostExecutionEvent(
                blockA,
                Void
            )
        )

        emitter.onNext(
            PreExecutionEvent(
                blockB
            )
        )

        verify(blockA, times(1)).markAsExecuting()
        verify(blockA, times(1)).markAsStop()
        verify(blockA, times(0)).markAsError()
        verify(blockB, times(1)).markAsExecuting()
        verify(blockB, times(0)).markAsStop()
        verify(blockB, times(0)).markAsError()
    }

    @Test
    fun `mark invalid blocks on error`() {
        val blockA = Mockito.mock(OperationsUiBlock::class.java)
        val blockB = Mockito.mock(OperationsUiBlock::class.java)
        val emitter = PublishSubject.create<ExecutionEvent>()

        sut.inject(emitter)

        emitter.onNext(
            ExecutionStart
        )

        emitter.onNext(
            PreExecutionEvent(
                blockA
            )
        )
        emitter.onNext(
            PostExecutionEvent(
                blockA,
                Void
            )
        )

        emitter.onNext(
            PreExecutionEvent(
                blockB
            )
        )

        emitter.onError(Throwable())

        verify(blockA, times(1)).markAsExecuting()
        verify(blockA, times(1)).markAsStop()
        verify(blockA, times(0)).markAsError()
        verify(blockB, times(1)).markAsExecuting()
        verify(blockB, times(0)).markAsStop()
        verify(blockB, times(1)).markAsError()
    }

    @Test
    fun `mark invalid blocks on error event`() {
        val blockA = Mockito.mock(OperationsUiBlock::class.java)
        val blockB = Mockito.mock(OperationsUiBlock::class.java)
        val emitter = PublishSubject.create<ExecutionEvent>()

        sut.inject(emitter)

        emitter.onNext(
            ExecutionStart
        )

        emitter.onNext(
            PreExecutionEvent(
                blockA
            )
        )
        emitter.onNext(
            PostExecutionEvent(
                blockA,
                Void
            )
        )

        emitter.onNext(
            PreExecutionEvent(
                blockB
            )
        )

        emitter.onNext(ErrorEvent(Throwable()))

        verify(blockA, times(1)).markAsExecuting()
        verify(blockA, times(1)).markAsStop()
        verify(blockA, times(0)).markAsError()
        verify(blockB, times(1)).markAsExecuting()
        verify(blockB, times(0)).markAsStop()
        verify(blockB, times(1)).markAsError()
    }

    @Test
    fun `clear marks on execution end`() {
        val blockA = Mockito.mock(OperationsUiBlock::class.java)
        val blockB = Mockito.mock(OperationsUiBlock::class.java)
        val emitter = PublishSubject.create<ExecutionEvent>()

        sut.inject(emitter)

        emitter.onNext(
            ExecutionStart
        )

        emitter.onNext(
            PreExecutionEvent(
                blockA
            )
        )
        emitter.onNext(
            PostExecutionEvent(
                blockA,
                Void
            )
        )

        emitter.onNext(
            PreExecutionEvent(
                blockB
            )
        )
        emitter.onNext(
            PostExecutionEvent(
                blockB,
                Void
            )
        )

        emitter.onNext(ExecutionEnd)

        verify(blockA, times(1)).markAsExecuting()
        verify(blockA, times(1)).markAsStop()
        verify(blockA, times(0)).markAsError()
        verify(blockB, times(1)).markAsExecuting()
        verify(blockB, times(1)).markAsStop()
        verify(blockB, times(0)).markAsError()
    }

    @Test
    fun `clear errors after new execution`() {
        val blockA = Mockito.mock(OperationsUiBlock::class.java)
        val blockB = Mockito.mock(OperationsUiBlock::class.java)
        val emitter = PublishSubject.create<ExecutionEvent>()

        sut.inject(emitter)

        emitter.onNext(
            ExecutionStart
        )

        emitter.onNext(
            PreExecutionEvent(
                blockA
            )
        )
        emitter.onNext(
            PostExecutionEvent(
                blockA,
                Void
            )
        )

        emitter.onNext(
            PreExecutionEvent(
                blockB
            )
        )
        emitter.onNext(ErrorEvent(Throwable()))

        emitter.onNext(
            ExecutionEnd
        )
        emitter.onNext(
            ExecutionStart
        )

        verify(blockA, times(1)).markAsExecuting()
        verify(blockA, times(1)).markAsStop()
        verify(blockA, times(0)).markAsError()
        verify(blockB, times(1)).markAsExecuting()
        verify(blockB, times(1)).markAsStop()
        verify(blockB, times(1)).markAsError()
    }

}