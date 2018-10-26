package io.github.slupik.schemablock.model.ui.implementation;

import com.google.gson.Gson;
import io.github.slupik.schemablock.model.control.ElementInput;
import io.github.slupik.schemablock.model.control.ElementOutput;
import io.github.slupik.schemablock.model.ui.abstraction.StandardElement;
import io.github.slupik.schemablock.model.ui.parser.BlockParserException;
import io.github.slupik.schemablock.model.ui.parser.ElementPOJO;
import io.github.slupik.schemablock.parser.code.CodeParser;
import io.github.slupik.schemablock.parser.code.IncompatibleTypeException;
import io.github.slupik.schemablock.parser.code.VariableNotFound;
import io.github.slupik.schemablock.parser.code.WrongArgumentException;
import io.github.slupik.schemablock.parser.math.rpn.MathCalculation;
import io.github.slupik.schemablock.parser.math.rpn.pattern.InvalidArgumentsException;
import io.github.slupik.schemablock.parser.math.rpn.pattern.UnsupportedValueException;
import io.github.slupik.schemablock.parser.math.rpn.variable.VariableIsAlreadyDefinedException;
import io.github.slupik.schemablock.parser.math.rpn.variable.value.NotFoundTypeException;

/**
 * All rights reserved & copyright ©
 */
public abstract class StandardElementBase implements StandardElement {

    private String codeToRun = "";
    private ElementInput input;
    private ElementOutput output;

    @Override
    public void setContent(String content) {
        codeToRun = content;
    }

    @Override
    public void setInput(ElementInput input) {
        this.input = input;
    }

    @Override
    public void setOutput(ElementOutput output) {
        this.output = output;
    }

    protected void justRunCode() throws IncompatibleTypeException, InvalidArgumentsException, UnsupportedValueException, VariableIsAlreadyDefinedException, VariableNotFound, WrongArgumentException, NotFoundTypeException {
        CodeParser.execute(codeToRun);
    }

    protected Object runAndResult() throws InvalidArgumentsException, NotFoundTypeException, UnsupportedValueException {
        return MathCalculation.getResult(CodeParser.getHeap(), codeToRun);
    }

    @Override
    public String stringify() {
        return new Gson().toJson(getPOJO());
    }

    protected abstract ElementPOJO getPOJO();

    protected ElementPOJO getPreCreatedPOJO(){
        ElementPOJO pojo = new ElementPOJO();
        pojo.elementType = getType();
        pojo.content = codeToRun;
        return pojo;

    }

    @Override
    public void load(String data) throws BlockParserException {
        ElementPOJO pojo = new Gson().fromJson(data, ElementPOJO.class);
        codeToRun = pojo.content;
    }
}
