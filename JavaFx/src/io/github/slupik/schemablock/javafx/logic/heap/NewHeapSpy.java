package io.github.slupik.schemablock.javafx.logic.heap;

import io.github.slupik.schemablock.both.execution.VariableNotFound;
import io.github.slupik.schemablock.model.ui.newparser.HeapController;
import io.github.slupik.schemablock.newparser.compilator.exception.IncompatibleArrayException;
import io.github.slupik.schemablock.newparser.compilator.exception.IncompatibleTypeException;
import io.github.slupik.schemablock.newparser.memory.Memory;
import io.github.slupik.schemablock.newparser.memory.element.Value;
import io.github.slupik.schemablock.newparser.memory.element.ValueType;
import io.github.slupik.schemablock.newparser.memory.element.Variable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;

/**
 * All rights reserved & copyright ©
 */
public class NewHeapSpy implements HeapController, Memory {

    private final ObservableList<Variable> list = FXCollections.observableList(new ArrayList<>());
    private final Memory memory;

    public NewHeapSpy(Memory memory) {
        this.memory = memory;
    }

    public ObservableList<Variable> getVariableList() {
        return list;
    }

    @Override
    public void setVariableValue(String name, Value value) throws IncompatibleArrayException, IncompatibleTypeException, VariableNotFound {
        Variable variable = memory.get(name);
        if(variable==null) {
            throw new VariableNotFound(name);
        } else {
            variable.setContent(value);
        }
    }

    @Override
    public ValueType getVariableType(String name) throws VariableNotFound {
        Variable variable = memory.get(name);
        if(variable==null) {
            throw new VariableNotFound(name);
        } else {
            return variable.getType();
        }
    }

    @Override
    public void register(Variable variable) {
        list.add(variable);
        memory.register(variable);
    }

    @Override
    public Variable get(String name) {
        return memory.get(name);
    }

    @Override
    public void clear() {
        memory.clear();
        list.clear();
    }
}
