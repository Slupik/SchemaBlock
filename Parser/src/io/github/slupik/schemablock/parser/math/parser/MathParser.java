package io.github.slupik.schemablock.parser.math.parser;

import io.github.slupik.schemablock.parser.math.pattern.PatternFinder;
import io.github.slupik.schemablock.parser.math.value.Value;
import io.github.slupik.schemablock.parser.math.value.ValueType;
import io.github.slupik.schemablock.parser.math.pattern.InvalidArgumentsException;
import io.github.slupik.schemablock.parser.math.pattern.UnsupportedValueException;

import java.util.ArrayList;
import java.util.List;

/**
 * All rights reserved & copyright ©
 */
public class MathParser {

    private final PatternFinder finder;
    private final String command;
    private final String name;
    private final Value[] args;

    public MathParser(PatternFinder finder, String command) {
        this.finder = finder;
        this.command = command;
        this.name = getName(command);
        this.args = getValues(finder, command);
    }

    public Object getValue() {
        try {
            return finder.getForName(name).calculate(args);
        } catch (InvalidArgumentsException | UnsupportedValueException e) {
            e.printStackTrace();
            return null;
        }
    }

    static String getName(String command) {
        StringBuilder name = new StringBuilder();
        for(int i=0;i<command.length();i++) {
            char toCheck = command.charAt(i);
            if(isEndOfName(toCheck)) {
                break;
            }
            if(!isToIgnore(toCheck)) {
                name.append(toCheck);
            }
        }
        return name.toString().trim();
    }

    private static boolean isToIgnore(char value) {
        return value == ' ' || value == '\t' || value == '\n';
    }

    private static boolean isEndOfName(char value) {
        return value == '(' || value == ')';
    }

    private static Value[] getValues(PatternFinder finder, String command) {
        List<Value> args = new ArrayList<>();

        int deepness = 0;
        for(int i=0;i<command.length();i++) {
            char toCheck = command.charAt(i);
            if(isDeepnessIncrement(toCheck)) {
                deepness++;
            } else if(isDeepnessDecrement(toCheck)) {
                deepness--;
            } else if(deepness>0) {
                int endOfValue = i+ getEndIndexOfValue(command.substring(i));
                String valueText = command.substring(i, endOfValue);

                Object value;
                if(ValueType.isValidValue(valueText)) {
                    value = ValueType.parse(valueText);
                } else {
                    value = new MathParser(finder, valueText).getValue();
                }
                args.add(new Value(ValueType.getType(value), value));

                i = endOfValue;
            }
        }

        return args.toArray(new Value[0]);
    }

    private static int getEndIndexOfValue(String substring) {
        int i = 0;
        int deepness = 0;
        for(;i<substring.length();i++) {
            char toCheck = substring.charAt(i);
            if(Character.isWhitespace(toCheck) && deepness == 0) {
                break;
            }
            if(deepness == 0 && isDeepnessDecrement(toCheck)) {
                break;
            }
            if(isDeepnessIncrement(toCheck)) {
                deepness++;
            } else if(isDeepnessDecrement(toCheck)) {
                deepness--;
            }
        }
        return i;
    }

    private static boolean isDeepnessIncrement(char value) {
        return value == '(';
    }

    private static boolean isDeepnessDecrement(char value) {
        return value == ')';
    }
}
