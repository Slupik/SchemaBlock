package io.github.slupik.schemablock.parser.math.rpn.variable;

import java.util.*;

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

    public HashMap<String, Variable> getHeap(){
        return heap;
    }

    public List<String> getVariableNames(){
        List<String> names = new ArrayList<>();
        for (Map.Entry<String, Variable> entry : getHeap().entrySet()) {
            String key = entry.getKey();
            names.add(key);
        }
        return names;
    }

    public void clear() {
        List<String> names = getVariableNames();
        for(String name:names) {
            heap.remove(name);
        }
    }
}
