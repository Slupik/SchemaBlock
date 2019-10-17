package io.github.slupik.schemablock.newparser.memory;

import io.github.slupik.schemablock.both.execution.VariableNotFound;
import io.github.slupik.schemablock.model.ui.error.AlgorithmException;
import io.github.slupik.schemablock.newparser.memory.element.Variable;

import java.util.HashMap;

/**
 * All rights reserved & copyright ©
 */
public class MemoryImpl implements Memory {

    private final HashMap<String, Variable> data = new HashMap<>();

    @Override
    public void register(Variable variable) throws AlgorithmException {
        if(data.containsKey(variable.getName())) {
            throw new VariableAlreadyDefined(variable.getName());
        }
        data.put(variable.getName(), variable);
    }

    @Override
    public Variable get(String name) throws VariableNotFound {
        if(data.containsKey(name)) {
            return data.get(name);
        } else {
            throw new VariableNotFound(name);
        }
    }

    @Override
    public void clear() {
        data.clear();
    }
}
