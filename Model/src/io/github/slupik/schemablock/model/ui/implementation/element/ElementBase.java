package io.github.slupik.schemablock.model.ui.implementation.element;

import com.google.gson.Gson;
import io.github.slupik.schemablock.model.ui.abstraction.controller.ElementCallback;
import io.github.slupik.schemablock.model.ui.abstraction.element.Element;
import io.github.slupik.schemablock.model.ui.implementation.container.NextElementNotFound;
import io.github.slupik.schemablock.model.ui.parser.BlockParserException;
import io.github.slupik.schemablock.model.ui.parser.ElementPOJO;
import io.github.slupik.schemablock.model.utils.RandomString;
import io.github.slupik.schemablock.parser.code.IncompatibleTypeException;
import io.github.slupik.schemablock.parser.code.VariableNotFound;
import io.github.slupik.schemablock.parser.code.WrongArgumentException;
import io.github.slupik.schemablock.parser.math.rpn.pattern.InvalidArgumentsException;
import io.github.slupik.schemablock.parser.math.rpn.pattern.UnsupportedValueException;
import io.github.slupik.schemablock.parser.math.rpn.variable.VariableIsAlreadyDefinedException;
import io.github.slupik.schemablock.parser.math.rpn.variable.value.NotFoundTypeException;

import java.util.ArrayList;
import java.util.List;

/**
 * All rights reserved & copyright ©
 */
public abstract class ElementBase implements Element {

    protected final List<ElementCallback> callbacks = new ArrayList<>();
    private String id = RandomString.generate(32);

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void registerCallback(ElementCallback callback) {
        callbacks.add(callback);
    }

    @Override
    public void unregisterCallback(ElementCallback callback) {
        callbacks.remove(callback);
    }

    @Override
    public final void load(String data) throws BlockParserException {
        ElementPOJO pojo = new Gson().fromJson(data, ElementPOJO.class);
        load(pojo);
    }

    protected abstract void load(ElementPOJO pojo) throws BlockParserException;

    protected final void onStart(){
        for(ElementCallback callback:callbacks) {
            callback.onStart();
        }
    }

    protected final void onStop(){
        for(ElementCallback callback:callbacks) {
            callback.onStop();
        }
    }

    protected final void onStop(Object result){
        for(ElementCallback callback:callbacks) {
            callback.onStop(result);
        }
    }

    protected final void tryRun(String elementId) throws NextElementNotFound, IncompatibleTypeException, UnsupportedValueException, VariableIsAlreadyDefinedException, InvalidArgumentsException, WrongArgumentException, NotFoundTypeException, VariableNotFound {
        for(ElementCallback callback:callbacks) {
            callback.onTryRun(elementId);
        }
    }
}
