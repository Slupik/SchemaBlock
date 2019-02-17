package io.github.slupik.schemablock.newparser.utils;

import java.util.HashMap;

/**
 * All rights reserved & copyright ©
 */
public class CodeOperations extends HashMap<String, Integer> {

    {
        //unary logical NOT
        put("!", 44);

        //unary bitwise NOT
        put("~", 44);

        //multiplicative
        put("\\", 42);
        put("/", 42);
        put("*", 42);
        put("%", 42);

        //additive
        put("+", 41);
        put("-", 41);

        //shift
        put("<<", 40);
        put(">>", 40);

        //relational
        put("<", 39);
        put(">", 39);
        put(">=", 39);
        put("<=", 39);

        //equality
        put("==", 38);
        put("!=", 38);

        //bitwise AND
        put("&", 34);

        //bitwise XOR
        put("^", 36);

        //bitwise OR
        put("|", 35);

        //logical AND
        put("&&", 34);

        //logical OR
        put("||", 33);

        //assigning a value
        put("=", 1);

        put("(", 0);
    }

}
