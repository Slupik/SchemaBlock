package io.github.slupik.schemablock.parser.math.rpn.pattern;

import io.github.slupik.schemablock.parser.math.rpn.pattern.specific.MathPatternPow;
import io.github.slupik.schemablock.parser.math.rpn.pattern.specific.MathPatternSqrt;
import io.github.slupik.schemablock.parser.math.rpn.pattern.specific.MathPatternSum;
import io.github.slupik.schemablock.parser.math.rpn.pattern.specific.special.ProgramPrint;
import io.github.slupik.schemablock.parser.math.rpn.pattern.specific.special.ProgramPrintln;
import io.github.slupik.schemablock.parser.math.rpn.pattern.specific.special.ProgramRead;

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

    public void registerDefaultPatterns(){
        registerPattern(new MathPatternPow());
        registerPattern(new MathPatternSqrt());
        registerPattern(new MathPatternSum());
        registerPattern(new ProgramRead());
        registerPattern(new ProgramPrint());
        registerPattern(new ProgramPrintln());
    }
}
