package io.github.slupik.schemablock.model.ui.implementation.element.specific;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import io.github.slupik.schemablock.model.ui.abstraction.ElementType;
import io.github.slupik.schemablock.model.ui.abstraction.element.IOElement;
import io.github.slupik.schemablock.model.ui.implementation.container.NextElementNotFound;
import io.github.slupik.schemablock.model.ui.implementation.element.StandardElementBase;
import io.github.slupik.schemablock.model.ui.parser.BlockParserException;
import io.github.slupik.schemablock.model.ui.parser.ElementPOJO;
import io.github.slupik.schemablock.parser.code.CodeParser;
import io.github.slupik.schemablock.parser.code.IncompatibleTypeException;
import io.github.slupik.schemablock.parser.code.VariableNotFound;
import io.github.slupik.schemablock.parser.code.WrongArgumentException;
import io.github.slupik.schemablock.parser.math.rpn.pattern.InvalidArgumentsException;
import io.github.slupik.schemablock.parser.math.rpn.pattern.UnsupportedValueException;
import io.github.slupik.schemablock.parser.math.rpn.variable.VariableIsAlreadyDefinedException;
import io.github.slupik.schemablock.parser.math.rpn.variable.value.NotFoundTypeException;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * All rights reserved & copyright ©
 */
//TODO this is not standard element... What even is "standard element"? Better names would be like executable etc.
public class IOBlock extends StandardElementBase implements IOElement {

    private String nextElement = "";
    private final List<Data> instructions = new ArrayList<>();
    private IOCommunicable communicator;

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
    public void setCommunicator(IOCommunicable communicator) {
        this.communicator = communicator;
    }

    @Override
    public void setContent(String content) {
        Type listType = new TypeToken<ArrayList<Data>>(){}.getType();
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
    public void run() throws InvalidArgumentsException, NotFoundTypeException, UnsupportedValueException, NextElementNotFound, VariableNotFound, WrongArgumentException, VariableIsAlreadyDefinedException, IncompatibleTypeException {
        onStart();
        justRunCode();
        tryRun(nextElement);
        onStop();
    }

    @Override
    protected void justRunCode() throws IncompatibleTypeException, InvalidArgumentsException, UnsupportedValueException, VariableIsAlreadyDefinedException, VariableNotFound, WrongArgumentException, NotFoundTypeException {
        //TODO implement this special element with new parser
        CodeParser.execute("");
        if(communicator!=null) {
            for(Data instruction:instructions) {
                if(instruction.isInput()) {
                    String value = communicator.getInput();
                    CodeParser.updateVariable(instruction.getValue(), value);
                } else {
                    String value = CodeParser.getValueToPrint(instruction.getValue());
                    communicator.print(value);
                }
            }
        }
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

    protected ElementPOJO getPreCreatedPOJO(){
        ElementPOJO pojo = new ElementPOJO();
        pojo.elementType = getType();
        pojo.content = getContent();
        return pojo;
    }

    @Override
    protected void load(ElementPOJO pojo) throws BlockParserException {
        nextElement = pojo.nextBlocks[0];
    }
}
