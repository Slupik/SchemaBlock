package io.github.slupik.schemablock.model.ui.implementation.element.specific;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import io.github.slupik.schemablock.both.execution.VariableNotFound;
import io.github.slupik.schemablock.model.ui.abstraction.ElementType;
import io.github.slupik.schemablock.model.ui.abstraction.element.IOData;
import io.github.slupik.schemablock.model.ui.abstraction.element.IOElement;
import io.github.slupik.schemablock.model.ui.implementation.element.StandardElementBase;
import io.github.slupik.schemablock.model.ui.newparser.HeapController;
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
import io.github.slupik.schemablock.newparser.memory.ValueFactory;
import io.github.slupik.schemablock.newparser.memory.element.SimpleValue;
import io.github.slupik.schemablock.newparser.utils.ValueTooBig;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * All rights reserved & copyright ©
 */
//TODO this is not standard element... What even is "standard element"? Better names would be like executable etc.
public class IOBlock extends StandardElementBase implements IOElement {

    private final Executor executor;
    private final HeapController heap;
    private String nextElement = "";
    private final List<Data> instructions = new ArrayList<>();
    private IOCommunicable communicator;

    public IOBlock(Executor executor, HeapController heap) {
        super(executor);
        this.executor = executor;
        this.heap = heap;
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
        if (nextElement.equals(elementId)) {
            nextElement = "";
        }
    }

    @Override
    public void setCommunicator(IOCommunicable communicator) {
        this.communicator = communicator;
    }

    @Override
    public IOCommunicable getCommunicator() {
        return communicator;
    }

    @Override
    public void setContent(String content) {
        Type listType = new TypeToken<ArrayList<Data>>() {
        }.getType();
        List<Data> contentAsList = new Gson().fromJson(content, listType);
        setContent(contentAsList);
    }

    @Override
    public void setContent(List<Data> content) {
        instructions.clear();
        instructions.addAll(content);
    }

    @Override
    public String getContent() {
        return new Gson().toJson(getContentAsList());
    }

    @Override
    public List<Data> getContentAsList() {
        return instructions;
    }

    @Override
    public ElementType getType() {
        return ElementType.COMMUNICATION;
    }

    @Override
    public void run() throws UnknownOperation, IndexOutOfBoundsException, NameForDeclarationCannotBeFound, ExceptedArrayButNotReceivedException, ExceptedTypeOfArray, ValueTooBig, IncompatibleArrayException, IncompatibleTypeException, IllegalOperation, ComExIllegalEscapeChar, NoMatchingFunction, VariableNotFound {
        onStart();
        justRunCode();
        tryRun(nextElement);
        onStop();
    }

    @Override
    protected void justRunCode() throws IncompatibleArrayException, IncompatibleTypeException, VariableNotFound, NoMatchingFunction, ExceptedArrayButNotReceivedException, ExceptedTypeOfArray, ValueTooBig, IndexOutOfBoundsException, IllegalOperation, ComExIllegalEscapeChar, UnknownOperation, NameForDeclarationCannotBeFound {
        if (communicator != null) {
            for (Data instruction : instructions) {
                if (instruction.isInput()) {
                    String input = communicator.getInput();
                    SimpleValue value = ValueFactory.createValue(
                            heap.getVariableType(instruction.getValue()),
                            input
                    );
                    heap.setVariableValue(instruction.getValue(), value);
                } else {
                    String output = String.valueOf(executor.getResult(instruction.getValue()).getValue());
                    communicator.print(output);
                }
            }
        }
    }

    @Override
    protected ElementPOJO getPOJO() {
        ElementPOJO pojo = getPreCreatedPOJO();
        pojo.nextBlocks = new String[1];
        if (nextElement != null) {
            pojo.nextBlocks[0] = nextElement;
        }
        return pojo;
    }

    protected ElementPOJO getPreCreatedPOJO() {
        ElementPOJO pojo = new ElementPOJO();
        pojo.elementType = getType();
        pojo.content = getContent();
        pojo.id = id;
        return pojo;
    }

    @Override
    protected void load(ElementPOJO pojo) throws BlockParserException {
        super.load(pojo);
        nextElement = pojo.nextBlocks[0];

        List<Data> input = Arrays.asList(new Gson().fromJson(pojo.content, IOData[].class));
        instructions.clear();
        instructions.addAll(input);
    }
}
