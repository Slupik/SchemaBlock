package io.github.slupik.schemablock.parser.math.rpn.variable;

import java.util.HashMap;

/**
 * All rights reserved & copyright ©
 */
public class VariableHeap {
    private final HashMap<String, Variable> heap = new HashMap<>();

    public void registerVariable(Variable var) throws VariableIsAlreadyDefinedException {
        if(heap.containsKey(var.getName())) {
            throw new VariableIsAlreadyDefinedException(var.getName());
        }
        heap.put(var.getName(), var);
    }

    public Variable getVariable(String name) {
        return heap.get(name);
    }
}
