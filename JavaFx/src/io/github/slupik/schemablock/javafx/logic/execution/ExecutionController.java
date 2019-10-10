package io.github.slupik.schemablock.javafx.logic.execution;

import io.github.slupik.schemablock.both.execution.ExecutionFlowController;
import io.github.slupik.schemablock.both.execution.VariableNotFound;
import io.github.slupik.schemablock.javafx.element.fx.sheet.ElementNotFound;
import io.github.slupik.schemablock.model.ui.abstraction.container.ElementContainer;
import io.github.slupik.schemablock.model.ui.error.AlgorithmException;
import io.github.slupik.schemablock.model.ui.implementation.container.ExecutionCallback;
import io.github.slupik.schemablock.model.ui.implementation.container.NextElementNotFound;
import io.github.slupik.schemablock.model.ui.implementation.element.specific.IOCommunicable;
import io.github.slupik.schemablock.newparser.compilator.exception.*;
import io.github.slupik.schemablock.newparser.compilator.exception.IndexOutOfBoundsException;
import io.github.slupik.schemablock.newparser.compilator.implementation.compilator.CompilationException;
import io.github.slupik.schemablock.newparser.compilator.implementation.compilator.ExceptedTypeOfArray;
import io.github.slupik.schemablock.newparser.compilator.implementation.compilator.NameForDeclarationCannotBeFound;
import io.github.slupik.schemablock.newparser.executor.implementation.IllegalOperation;
import io.github.slupik.schemablock.newparser.executor.implementation.UnknownOperation;
import io.github.slupik.schemablock.newparser.function.exception.NoMatchingFunction;
import io.github.slupik.schemablock.newparser.memory.VariableAlreadyDefined;
import io.github.slupik.schemablock.newparser.utils.ValueTooBig;
import javafx.scene.control.Button;

import java.util.stream.Collectors;


/**
 * All rights reserved & copyright ©
 */
public class ExecutionController implements ExecutionFlowController {

    private final IOCommunicable communicator;
    private final ElementContainer container;
    private final Button btnPlay;
    private boolean debugMode;
    private ExecutionCallback callback;

    public ExecutionController(IOCommunicable communicator, ElementContainer container, Button btnPlay) {
        this.communicator = communicator;
        this.container = container;
        this.btnPlay = btnPlay;
        setup();
    }

    private void setup() {
        btnPlay.setDisable(true);
    }

    public void execute(boolean debugMode, ExecutionCallback callback) {
        this.debugMode = debugMode;
        this.callback = callback;
        new Thread(() -> {
            communicator.clear();
            container.run(callback);
        }).start();
    }

    @Override
    public void run(Runnable callback) {
        if (debugMode) {
            btnPlay.setDisable(false);
            btnPlay.setOnAction(event -> {
                btnPlay.setDisable(true);
                callback.run();
            });
        } else {
            callback.run();
        }
    }

    @Override
    public void onStart() {
        btnPlay.setDisable(true);
    }

    @Override
    public void onStop() {

    }

    @Override
    public void onException(Throwable exception) {
        exception.printStackTrace();
        if (exception instanceof AlgorithmException) {
            AlgorithmException ae = ((AlgorithmException) exception);
            switch (ae.getType()) {
                case INCOMPATIBLE_TYPE:
                    IncompatibleTypeException incompatibleTypeException = ((IncompatibleTypeException) exception);
                    communicator.printAlgorithmError(
                            "Nie można zmiennej o typie " + incompatibleTypeException.excepted + " przypisać wartość o typie " + incompatibleTypeException.actual
                    );
                    break;
                case WRONG_ARRAY_DIMENSION:
                    IncompatibleArrayException incompatibleArrayException = ((IncompatibleArrayException) exception);
                    communicator.printAlgorithmError(
                            "Oczekiwano tablicy o " + incompatibleArrayException.getExcepted() + " wymiarach, a otrzymano " + incompatibleArrayException.getActual()
                    );
                    break;
                case NUMBER_TOO_BIG:
                    ValueTooBig valueTooBig = ((ValueTooBig) exception);
                    communicator.printAlgorithmError(
                            "Podana liczba jest za duża by ją przechować jako liczbę: " + valueTooBig.value
                    );
                    break;
                case TYPE_OF_ARRAY_AFTER_KEYWORD_NEW_NOT_FOUND:
                    ExceptedTypeOfArray exceptedTypeOfArray = ((ExceptedTypeOfArray) exception);
                    communicator.printAlgorithmError(
                            getMessagePrefix(exceptedTypeOfArray) +
                                    "Oczekiwano podania typu po słownie kluczowym 'new' ale go nie otrzymano."
                    );
                    break;
                case UNKNOWN_OPERATION:
                    UnknownOperation unknownOperation = ((UnknownOperation) exception);
                    communicator.printAlgorithmError(
                            "Otrzymano nieznany typ operacji oznaczony symbolem: " + unknownOperation.symbol
                    );
                    break;
                case VARIABLE_IS_ALREADY_DEFINED:
                    VariableAlreadyDefined variableAlreadyDefined = ((VariableAlreadyDefined) exception);
                    communicator.printAlgorithmError(
                            "Próbowano ponownie zadeklarować zmienną o nazwie: " + variableAlreadyDefined.name
                    );
                    break;
                case NEXT_ELEMENT_NOT_FOUND: {
                    if (((NextElementNotFound) ae).getElementId().length() == 0) {
                        communicator.printAlgorithmError("Następny element nie został określony. Przerwano wykonywanie algorytmu.");
                        break;
                    }
                }
                case VARIABLE_NOT_FOUND:
                    VariableNotFound variableNotFound = ((VariableNotFound) exception);
                    communicator.printAlgorithmError(
                            "Próbowano użyć niezadeklarowanej zmiennej: " + variableNotFound.varName
                    );
                    break;
                case ELEMENT_NOT_FOUND:
                    ElementNotFound elementNotFound = ((ElementNotFound) exception);
                    communicator.printAlgorithmError(
                            "(Błąd wewnętrzny programu) Nie znaleziono elementu o id: " + elementNotFound.id
                    );
                    break;
                case ILLEGAL_CHAR_EXCEPTION:
                    ComExIllegalEscapeChar escapeCharException = ((ComExIllegalEscapeChar) exception);
                    communicator.printAlgorithmError(
                            getMessagePrefix(escapeCharException) +
                                    "Próbowano użyć znaku ucieczki na niedozwolonym znaku."
                    );
                    break;
                case FUNCTION_NOT_EXISTS:
                    NoMatchingFunction noMatchingFunction = ((NoMatchingFunction) exception);
                    communicator.printAlgorithmError(
                            "Funkcja o typie: " + noMatchingFunction.name + " z typami argumentów: " +
                                    noMatchingFunction.argsTypes.stream()
                                            .map(String::valueOf)
                                            .collect(Collectors.joining(", ", "", ""))
                                    + " nie istnieje."
                    );
                    break;
                case NAME_OF_VARIABLE_NOT_FOUND_DURING_DECLARATION:
                    NameForDeclarationCannotBeFound nameForDeclarationCannotBeFound = ((NameForDeclarationCannotBeFound) exception);
                    communicator.printAlgorithmError(
                            getMessagePrefix(nameForDeclarationCannotBeFound) +
                                    "Nie znaleziono nazwy zmiennej podczas próby jej deklaracji."
                    );
                    break;
                case EXCEPTED_SEMICOLON:
                    SemicolonNotFound semicolonNotFound = ((SemicolonNotFound) exception);
                    communicator.printAlgorithmError(
                            getMessagePrefix(semicolonNotFound) +
                                    "Oczekiwano średnika (;), ale go nie znaleziono."
                    );
                    break;
                case PROBABLY_EXCEPTED_SEMICOLON:
                    MissingSemicolon missingSemicolon = ((MissingSemicolon) exception);
                    communicator.printAlgorithmError(
                            getMessagePrefix(missingSemicolon) +
                                    "Próbowano wykonać zbyt dużo operacji. Prawdopodobnie zabrakło średnika."
                    );
                    break;
                case ILLEGAL_OPERATION:
                    IllegalOperation illegalOperation = ((IllegalOperation) exception);
                    if (illegalOperation.type2 == null) {
                        communicator.printAlgorithmError(
                                "Próbowano użyć operacji " + illegalOperation + " dla wartości o typie " + illegalOperation.type1 +
                                        ", niestety to działanie jest niedozwolone dla tego typu."
                        );
                    } else {
                        communicator.printAlgorithmError(
                                "Próbowano użyć operacji " + illegalOperation + " dla wartości o typie " + illegalOperation.type1 +
                                        "oraz " + illegalOperation.type2 + ", niestety to działanie jest niedozwolone dla tych typów."
                        );
                    }
                    break;
                case INDEX_OUT_OF_BOUNDS:
                    IndexOutOfBoundsException indexOutOfBoundsException = ((IndexOutOfBoundsException) exception);
                    communicator.printAlgorithmError(
                            "Oczekiwany index wartości (" +
                                    indexOutOfBoundsException.getIndex() +
                                    ") wybiegł poza maksymalny indeks tablicy (" +
                                    indexOutOfBoundsException.getLength() +
                                    ")."
                    );
                    break;
                case EXCEPTED_ARRAY:
                    communicator.printAlgorithmError(
                            "Podczas ustawiania wartości tablicy oczekiwano, że otrzymana zostanie tablica, ale otrzymano coś innego."
                    );
                    break;
                case EXCEPTED_VALUE:
                    communicator.printAlgorithmError(
                            "Podczas ustawiania wartości zmiennej oczekiwano, że otrzmana zostanie wartość nietablicowa, ale otrzymano coś innego."
                    );
                    break;
                default: {
                    communicator.printAlgorithmError(exception.getMessage());
                }
            }
        } else {
            communicator.printAlgorithmError(exception.getMessage());
        }
        if(callback!=null) {
            callback.onStop();
        }
    }

    private String getMessagePrefix(CompilationException exception) {
        return "[Linia: " + exception.line + ", kolumna: " + exception.position + "]: ";
    }
}
