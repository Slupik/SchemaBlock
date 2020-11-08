package io.github.slupik.schemablock.view.logic.communication.output

import io.github.slupik.schemablock.execution.VariableNotFound
import io.github.slupik.schemablock.model.ui.error.AlgorithmException
import io.github.slupik.schemablock.model.ui.implementation.container.NextElementNotFound
import io.github.slupik.schemablock.newparser.compilator.exception.IncompatibleArrayException
import io.github.slupik.schemablock.newparser.compilator.exception.IncompatibleTypeException
import io.github.slupik.schemablock.newparser.compilator.exception.IndexOutOfBoundsException
import io.github.slupik.schemablock.newparser.compilator.implementation.compilator.CompilationException
import io.github.slupik.schemablock.view.logic.execution.diagram.exception.DiagramException
import io.github.slupik.schemablock.view.logic.execution.diagram.exception.NextBlockNotFound
import io.github.slupik.schemablock.view.logic.execution.diagram.exception.StartBlockNotFound

/**
 * All rights reserved & copyright ©
 */
class PolishErrorTranslator : ErrorTranslator {
    override fun translateError(error: Throwable): String {
        if (error is DiagramException) {
            return handleDiagramError(error)
        }
        if (error is AlgorithmException) {
            return handleAlgorithmError(error)
        }
        TODO("Not yet implemented")
    }

    private fun handleAlgorithmError(error: AlgorithmException): String =
        when (error) {
            is CompilationException -> {
                handleCompilationError(error)
            }
            is IncompatibleArrayException -> {
                "Oczekiwano tablicy o rozmiarze ${error.excepted} ale otrzymano taką o rozmiarze ${error.actual}. Sprawdź czy masz uzupełnione prawidłowo indeksy oraz czy tablice są zdefiniowane według prawidłowych rozmiarów."
            }
            is IncompatibleTypeException -> {
                "Niezgodne typy obiektów. Oczekiwano typu '${error.excepted}' ale otrzymano '${error.actual}'"
            }
            is IndexOutOfBoundsException -> {
                "Próbowano pobrać z tablicy element o indeksie ${error.index}, ale tablica ta ma tylko ${error.length} elementów, a ich numeracja zaczyna się od 0."
            }
            is VariableNotFound -> {
                "Zmienna '${error.varName}' nie istnieje."
            }
            is NextElementNotFound -> {
                "Wystąpił wewnętrzny błąd modelu danych algorytmu. Nie udało się odnaleźć następnego elementu do wykonania, mimo, że znaleziono odpowiedni blok. Spróbuj ponownie połączyć elementy."
            }
            is UnknownError -> {
                "Wystąpił nieznany błąd przy uruchamianiu kodu. Sprawdź składnię poleceń. Być może gdzieś brakuje średnika (;)."
            }
            else -> {
                "Nieznany typ błędu o kodzie '${error.message}' oraz treści oryginalnej: ${error.message}"
            }
        }

    private fun handleCompilationError(error: CompilationException): String {
        TODO("Not yet implemented")
    }

    private fun handleDiagramError(error: DiagramException): String =
        when (error) {
            is StartBlockNotFound -> {
                "Nie znaleziono bloku startu."
            }
            is NextBlockNotFound -> {
                "Nie znaleziono następnego bloku po bloku nazwanym jako: ${error.currentBlock.description}"
            }
            else -> {
                error.message
            }
        }

}