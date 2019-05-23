package io.github.slupik.schemablock.javafx.logic.heap;

import io.github.slupik.schemablock.parser.code.CodeParser;
import io.github.slupik.schemablock.parser.math.rpn.variable.HeapSpy;
import io.github.slupik.schemablock.parser.math.rpn.variable.Variable;
import io.github.slupik.schemablock.parser.math.rpn.variable.VariableHeap;
import io.github.slupik.schemablock.parser.math.rpn.variable.value.ValueType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;

/**
 * All rights reserved & copyright ©
 */
public class DefaultHeapSpy implements HeapSpy {
    private final ObservableList<HeapValueFx> list = FXCollections.observableList(new ArrayList<>());

    public DefaultHeapSpy() {
        CodeParser.registerHeapSpy(this);
    }

    @Override
    public void setHeap(VariableHeap heap) {
        list.clear();
        for(String name:heap.getVariableNames()) {
            Variable var = heap.getVariable(name);
            HeapValueFx value = new HeapValueFx();
            value.setName(name);
            if(var.getType()== ValueType.STRING) {
                value.setValue('\"'+var.getValue()+'\"');
            } else {
                value.setValue(var.getValue());
            }
            value.setType(var.getType());
            list.add(value);
        }
    }

    public ObservableList<HeapValueFx> getList() {
        return list;
    }
}
