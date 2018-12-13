package io.github.slupik.schemablock.newparser.compilator;

import io.github.slupik.schemablock.newparser.bytecode.ByteCommand;
import io.github.slupik.schemablock.newparser.compilator.exception.ComExIllegalEscapeChar;

import java.util.Queue;

/**
 * All rights reserved & copyright ©
 */
public interface Compilator {
    Queue<ByteCommand> getCompiled(String code) throws ComExIllegalEscapeChar;

    /* ByteCode ideas
    type name = value;
        1) DECLARE_VAR type name
        2) value
        3) SET_VAR name

    type name = a+b;
        1) DECLARE_VAR type name
        2) HEAP (typeof a) a
        3) HEAP (typeof b) b
        4) OPERATION +
        5) SET_VAR name

    type[a+b] name;
        1) HEAP (typeof a) a
        2) HEAP (typeof b) b
        3) OPERATION +
        4) DECLARE_TAB type name 1

    type[] name = {v1, v2, v3, v4};
        1) HEAP int 4
        2) DECLARE_TAB type name 1
        3) HEAP (typeof v4) v4
        4) HEAP (typeof v3) v3
        5) HEAP (typeof v2) v2
        6) HEAP (typeof v1) v1
        7) HEAP 0
        8) SET_TAB name 1
        9) HEAP 1
        10) SET_TAB name 1
        11) HEAP 2
        12) SET_TAB name 1
        13) HEAP 3
        14) SET_TAB name 1

    type[4][5] name;
        1) HEAP int 4
        2) HEAP int 5
        3) DECLARE_TAB type name 2

    sqrt(a);
        1) HEAP (typeof a) a
        2) EXECUTE sqrt 1

    power(a, b);
        1) HEAP (typeof a) a
        1) HEAP (typeof b) b
        2) EXECUTE power 2

    double name = sqrt(a);
        1) HEAP (typeof a) a
        2) EXECUTE sqrt 1
        3) DECLARE_VAR double name
        5) SET_VAR name

    sqrt(a);
    double name = 5;
        1) HEAP (typeof a) a
        2) EXECUTE sqrt 1
        3) CLEAR_EXEC_HEAP
        4) DECLARE_VAR double name
        5) HEAP int 5
        6) SET_VAR name

    int[4][5] name;
    name[1][2] = 10;
        1) HEAP int 5
        2) HEAP int 4
        3) DECLARE_TAB int name 2
        2) HEAP int 10
        2) HEAP int 2
        2) HEAP int 1
        4) SET_TAB name 2

    type[][] name = {{v11, v12}, {v21, v22}, {v31, v32}};
        1) HEAP int 2
        2) HEAP int 3
        3) DECLARE_TAB type name 2
        4) HEAP (typeof v32) v32
        5) HEAP (typeof v31) v31
        6) HEAP (typeof v22) v22
        7) HEAP (typeof v21) v21
        8) HEAP (typeof v12) v12
        9) HEAP (typeof v11) v11
        10) HEAP 0
        11) HEAP 0
        12) SET_TAB name 2
        13) HEAP 1
        14) HEAP 0
        15) SET_TAB name 2
        16) HEAP 0
        17) HEAP 1
        18) SET_TAB name 2
        19) HEAP 1
        20) HEAP 1
        21) SET_TAB name 2
        22) HEAP 0
        23) HEAP 2
        24) SET_TAB name 2
        25) HEAP 1
        26) HEAP 2
        27) SET_TAB name 2

     */
}
