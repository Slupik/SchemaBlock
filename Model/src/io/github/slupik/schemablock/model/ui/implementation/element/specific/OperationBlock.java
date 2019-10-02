package io.github.slupik.schemablock.model.ui.implementation.element.specific;

import io.github.slupik.schemablock.model.ui.abstraction.ElementType;
import io.github.slupik.schemablock.model.ui.abstraction.element.OperationElement;
import io.github.slupik.schemablock.model.ui.implementation.element.StandardElementBase;
import io.github.slupik.schemablock.model.ui.parser.BlockParserException;
import io.github.slupik.schemablock.model.ui.parser.ElementPOJO;
import io.github.slupik.schemablock.newparser.compilator.exception.IndexOutOfBoundsException;
import io.github.slupik.schemablock.newparser.compilator.exception.*;
import io.github.slupik.schemablock.newparser.compilator.implementation.compilator.ExceptedTypeOfArray;
import io.github.slupik.schemablock.newparser.compilator.implementation.compilator.NameForDeclarationCannotBeFound;
import io.github.slupik.schemablock.newparser.executor.Executor;
import io.github.slupik.schemablock.newparser.executor.implementation.IllegalOperation;
import io.github.slupik.schemablock.newparser.executor.implementation.UnknownOperation;
import io.github.slupik.schemablock.newparser.function.exception.NoMatchingFunction;
import io.github.slupik.schemablock.newparser.utils.ValueTooBig;
import io.github.slupik.schemablock.both.execution.VariableNotFound;

/**
 * All rights reserved & copyright ©
 */
public class OperationBlock extends StandardElementBase implements OperationElement {

    private String nextElement = "";

    public OperationBlock(Executor executor) {
        super(executor);
    }

    @Override
    public void setNextElement(String elementId) {
        nextElement = elementId;
    }

    @Override
    public String getNextElement() {
        return nextElement;
    }

    @Override
    public void removeNextElement(String elementId) {
        if(nextElement.equals(elementId)) {
            nextElement = "";
        }
    }

    @Override
    public ElementType getType() {
        return ElementType.CALCULATION;
    }

    @Override
    public void run() throws UnknownOperation, IncompatibleArrayException, ExceptedArrayButNotReceivedException, ExceptedTypeOfArray, ValueTooBig, IndexOutOfBoundsException, IncompatibleTypeException, IllegalOperation, VariableNotFound, ComExIllegalEscapeChar, NoMatchingFunction, NameForDeclarationCannotBeFound {
        onStart();
        justRunCode();
        tryRun(nextElement);
        onStop();
    }

    @Override
    protected ElementPOJO getPOJO() {
        ElementPOJO pojo = getPreCreatedPOJO();
        pojo.nextBlocks = new String[1];
        if(nextElement!=null) {
            pojo.nextBlocks[0] = nextElement;
        }
        return pojo;
    }

    @Override
    protected void load(ElementPOJO pojo) throws BlockParserException {
        super.load(pojo);
        nextElement = pojo.nextBlocks[0];
    }
}
