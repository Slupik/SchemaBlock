package io.github.slupik.schemablock.view.logic.memory;

import io.github.slupik.schemablock.execution.VariableNotFound;
import io.github.slupik.schemablock.model.ui.error.AlgorithmException;
import io.github.slupik.schemablock.model.ui.newparser.HeapController;
import io.github.slupik.schemablock.newparser.memory.Memory;
import io.github.slupik.schemablock.newparser.memory.element.Value;
import io.github.slupik.schemablock.newparser.memory.element.ValueType;
import io.github.slupik.schemablock.newparser.memory.element.Variable;
import io.github.slupik.schemablock.view.logic.execution.dagger.AtomicMemory;
import io.github.slupik.schemablock.view.logic.execution.dagger.HeapSpy;
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
    public void setVariableValue(String name, Value value) throws AlgorithmException {
        Variable variable = memory.get(name);
        if (variable == null) {
            throw new VariableNotFound(name);
        } else {
            variable.setContent(value);
        }
    }

    @Override
    public ValueType getVariableType(String name) throws VariableNotFound {
        Variable variable = memory.get(name);
        if (variable == null) {
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
    public Variable get(String name) throws VariableNotFound {
        return memory.get(name);
    }

    @Override
    public void clear() {
        memory.clear();
        list.clear();
    }
}
