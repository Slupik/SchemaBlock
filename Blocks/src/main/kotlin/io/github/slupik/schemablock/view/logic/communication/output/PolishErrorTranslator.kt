package io.github.slupik.schemablock.view.logic.communication.output

import io.github.slupik.schemablock.execution.VariableNotFound
import io.github.slupik.schemablock.model.ui.error.AlgorithmException
import io.github.slupik.schemablock.model.ui.implementation.container.NextElementNotFound
import io.github.slupik.schemablock.newparser.compilator.exception.*
import io.github.slupik.schemablock.newparser.compilator.implementation.compilator.CompilationException
import io.github.slupik.schemablock.newparser.compilator.implementation.compilator.ExceptedTypeOfArray
import io.github.slupik.schemablock.newparser.compilator.implementation.compilator.NameForDeclarationCannotBeFound
import io.github.slupik.schemablock.newparser.executor.implementation.IllegalOperation
import io.github.slupik.schemablock.newparser.executor.implementation.UnknownOperation
import io.github.slupik.schemablock.newparser.function.exception.CannotParseData
import io.github.slupik.schemablock.newparser.function.exception.NoMatchingFunction
import io.github.slupik.schemablock.newparser.memory.ExceptedEndOfIndex
import io.github.slupik.schemablock.newparser.memory.UnexpectedCharBetweenIndexes
import io.github.slupik.schemablock.newparser.memory.VariableAlreadyDefined
import io.github.slupik.schemablock.newparser.memory.element.ExceptedArray
import io.github.slupik.schemablock.newparser.memory.element.ExceptedValue
import io.github.slupik.schemablock.newparser.memory.element.ValueType
import io.github.slupik.schemablock.newparser.utils.ValueTooBig
import io.github.slupik.schemablock.view.logic.execution.diagram.exception.DiagramException
import io.github.slupik.schemablock.view.logic.execution.diagram.exception.NextBlockNotFound
import io.github.slupik.schemablock.view.logic.execution.diagram.exception.StartBlockNotFound
import java.util.stream.Collectors
import javax.inject.Inject

/**
 * All rights reserved & copyright ©
 */
class PolishErrorTranslator @Inject constructor() : ErrorTranslator {

    override fun handles(error: Throwable): Boolean =
        error is DiagramException || error is CompilationException || error is AlgorithmException

    override fun translateError(error: Throwable): String =
        when (error) {
            is DiagramException -> {
                handleDiagramError(error)
            }
            is CompilationException -> {
                handleCompilationError(error)
            }
            is AlgorithmException -> {
                handleAlgorithmError(error)
            }
            else -> {
                "Nieznany błąd: ${error.message}"
            }
        }

    private fun handleAlgorithmError(error: AlgorithmException): String =
        when (error) {
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
            is VariableAlreadyDefined -> {
                "Próbowano utworzyć już istniejącą zmienną o nazwie '${error.name}'. Użyj innej nazwy lub po prostu przypisz wartość do już istniejącej zmiennej."
            }
            is ExceptedArray -> {
                "Oczekiwano tablicy ale otrzymano inny obiekt (prawdopodobnie typ prosty). Sprawdź czy w którymś miejscu nie jest zbyt dużo indeksów lub nie jest ich zdefiniowana zbyt mała liczba."
            }
            is ExceptedValue -> {
                "Oczekiwano wartości ale otrzymano inny obiekt (prawdopodobnie tablicę). Sprawdź czy w którymś miejscu nie jest zbyt mało indeksów lub nie jest ich zdefiniowana zbyt duża liczba."
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
        val tag = "[linia: ${error.line}, pozycja: ${error.position}] [Błąd kodu]: "
        return tag + when (error) {
            is MissingSemicolon -> {
                "Prawdopodobnie brakuje średnika w tym miejscu."
            }
            is SemicolonNotFound -> {
                "Brakuje średnika w tym miejscu."
            }
            is ExceptedTypeOfArray -> {
                "Po słowie kluczowym 'new' oczekiwano typu tablicy, ale nie znaleziono słowa kluczowego oznaczającego prawidłowy typ. Sprawdź składnię lub pisownię."
            }
            is NameForDeclarationCannotBeFound -> {
                "Brakuje nazwy zmiennej, przy jej tworzeniu."
            }
            is IllegalOperation -> {
                if (error.type2 == null) {
                    "Próbowano użyć operatora ${error.operation} dla niewłaściwego typu danych: ${error.type1.displayName}."
                } else {
                    "Próbowano użyć operatora ${error.operation} dla niewłaściwych typów danych: ${error.type1.displayName} oraz ${error.type2.displayName}."
                }
            }
            is UnknownOperation -> {
                "Nieznana operacja: ${error.symbol}"
            }
            is CannotParseData -> {
                "Nie można zapisać '${error.input}' jako typ '${error.type.displayName}'."
            }
            is NoMatchingFunction -> {
                ("Funkcja o nazwie: ${error.name} z typami argumentów: " +
                        error.argsTypes.stream()
                            .map { type: ValueType -> type.toString() }
                            .collect(Collectors.joining(", ", "", ""))
                        + " nie istnieje. Sprawdź kolejność, typy i liczbę argumentów.")
            }
            is ExceptedEndOfIndex -> {
                "Oczekiwano znak końca indeksu ale go nie otrzymano."
            }
            is UnexpectedCharBetweenIndexes -> {
                "Nieoczekiwany znak pomiędzy indeksami."
            }
            is ValueTooBig -> {
                "Wartość '${error.value}' jest zbyt duża by zapisać ją za pomocą zaimplementowanych typów liczbowych."
            }
            else -> {
                "Nieznany błąd: ${error.message}"
            }
        }
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
                "Nieznany błąd: ${error.message}"
            }
        }

}