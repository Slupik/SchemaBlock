package io.github.slupik.schemablock.view.logic.memory;

import io.github.slupik.schemablock.execution.VariableNotFound;
import io.github.slupik.schemablock.model.ui.error.AlgorithmException;
import io.github.slupik.schemablock.model.ui.newparser.HeapController;
import io.github.slupik.schemablock.newparser.memory.IndexesExtractor;
import io.github.slupik.schemablock.newparser.memory.Memory;
import io.github.slupik.schemablock.newparser.memory.element.*;
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
    private final IndexesExtractor indexesExtractor;

    @Inject
    public NewHeapSpy(@AtomicMemory Memory memory,
                      @HeapSpy Runnable callbackAfterItemChange,
                      IndexesExtractor indexesExtractor) {
        this.memory = memory;
        this.callbackAfterItemChange = callbackAfterItemChange;
        this.indexesExtractor = indexesExtractor;
    }

    public ObservableList<HeapValueFx> getVariableList() {
        return list;
    }

    public void refresh() {
        list.forEach(HeapValueFx::refresh);
    }

    @Override
    public void setVariableValue(String name, int[] indexes, Value value) throws AlgorithmException {
        Variable variable = get(name);
        if (variable == null) {
            throw new VariableNotFound(name);
        }

        if (indexes.length == 0) {
            variable.setContent(value);
        } else {
            Value content = variable.getContent();
            if (content instanceof Array) {
                if (value instanceof SimpleValue) {
                    ((Array) content).setValue(indexes, ((SimpleValue) value));
                } else {
                    ((Array) content).setValue(indexes, ((Array) value));
                }
            }
        }
    }

    @Override
    public ValueType getVariableType(String name) throws AlgorithmException {
        Variable variable = get(name);
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
    public Variable get(String name) throws AlgorithmException {
        String core = indexesExtractor.extractName(name);
        return memory.get(core);
    }

    @Override
    public void clear() {
        memory.clear();
        list.clear();
    }
}
