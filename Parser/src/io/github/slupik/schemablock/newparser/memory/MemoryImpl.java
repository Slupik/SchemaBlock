package io.github.slupik.schemablock.newparser.memory;

import io.github.slupik.schemablock.newparser.memory.element.Variable;

import java.util.ArrayList;
import java.util.List;

/**
 * All rights reserved & copyright ©
 */
public class MemoryImpl implements Memory {

    private final List<Variable> data = new ArrayList<>();

    @Override
    public void register(Variable variable) {
        data.add(variable);
    }

    @Override
    public Variable get(String name) {
        for(Variable var:data) {
            if(var.getName().equals(name)) {
                return var;
            }
        }
        return null;
    }
}
