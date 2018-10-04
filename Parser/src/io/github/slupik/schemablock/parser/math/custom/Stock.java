package io.github.slupik.schemablock.parser.math.custom;

import java.util.HashMap;

/**
 * All rights reserved & copyright ©
 */
public class Stock {

    private final HashMap<String, Variable> stock = new HashMap<>();

    public void putVariabe(Variable variable) {
        stock.put(variable.getName(), variable);
    }

    public Variable getVariable(String name) {
        return stock.get(name);
    }

    public void unregisterVariable(String name) {
        stock.remove(name);
    }
}
