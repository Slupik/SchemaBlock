package io.github.slupik.schemablock.parser.math.rpn.pattern;

import java.util.HashMap;

/**
 * All rights reserved & copyright ©
 */
public class PatternFinder {

    private final HashMap<String, MathPattern> stock = new HashMap<>();

    public void registerPattern(MathPattern pattern) {
        stock.put(pattern.getName(), pattern);
    }

    public MathPattern getForName(String name) {
        return stock.get(name);
    }
}
