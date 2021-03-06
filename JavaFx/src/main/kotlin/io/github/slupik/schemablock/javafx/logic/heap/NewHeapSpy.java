package io.github.slupik.schemablock.javafx.logic.heap;

import io.github.slupik.schemablock.execution.VariableNotFound;
import io.github.slupik.schemablock.javafx.dagger.HeapSpy;
import io.github.slupik.schemablock.logic.executor.dagger.AtomicMemory;
import io.github.slupik.schemablock.model.ui.error.AlgorithmException;
import io.github.slupik.schemablock.model.ui.newparser.HeapController;
import io.github.slupik.schemablock.newparser.memory.Memory;
import io.github.slupik.schemablock.newparser.memory.element.Value;
import io.github.slupik.schemablock.newparser.memory.element.ValueType;
import io.github.slupik.schemablock.newparser.memory.element.Variable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.ArrayList;

/**
 * All rights reserved & copyright ©
 */
@Singleton
public class NewHeapSpy implements HeapController, Memory {

    private final ObservableList<HeapValueFx> list = FXCollections.observableList(new ArrayList<>());
    private final Memory memory;
    private final Runnable callbackAfterItemChange;

    @Inject
    public NewHeapSpy(@AtomicMemory Memory memory, @HeapSpy Runnable callbackAfterItemChange) {
        this.memory = memory;
        this.callbackAfterItemChange = callbackAfterItemChange;
    }

    public ObservableList<HeapValueFx> getVariableList() {
        return list;
    }

    @Override
    public void setVariableValue(String name, int[] indexes, Value value) throws AlgorithmException {
        //TODO handling of indexes not implemented
        Variable variable = memory.get(name);
        if(variable==null) {
            throw new VariableNotFound(name);
        } else {
            variable.setContent(value);
        }
    }

    @Override
    public ValueType getVariableType(String name) throws AlgorithmException {
        Variable variable = memory.get(name);
        if(variable==null) {
            throw new VariableNotFound(name);
        } else {
            return variable.getType();
        }
    }

    @Override
    public void register(Variable variable) throws AlgorithmException {
        HeapValueFx wrapper = new HeapValueFx(variable, callbackAfterItemChange);
        memory.register(wrapper);
        list.add(wrapper);
    }

    @Override
    public Variable get(String name) throws AlgorithmException {
        return memory.get(name);
    }

    @Override
    public void clear() {
        memory.clear();
        list.clear();
    }
}
