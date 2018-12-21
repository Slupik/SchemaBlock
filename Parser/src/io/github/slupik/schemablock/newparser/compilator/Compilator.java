package io.github.slupik.schemablock.newparser.compilator;

import io.github.slupik.schemablock.newparser.bytecode.bytecommand.abstraction.ByteCommand;
import io.github.slupik.schemablock.newparser.compilator.exception.ComExIllegalEscapeChar;

import java.util.Queue;

/**
 * All rights reserved & copyright ©
 */
public interface Compilator {
    Queue<ByteCommand> getCompiled(String code) throws ComExIllegalEscapeChar;

    /* ByteCode ideas
    type name = value;
        1) DECLARE_VARIABLE type name 0
        2) HEAP_VARIABLE name
        3) HEAP_VALUE (typeof value) value
        4) OPERATION =

    type name = a+b;
        1) DECLARE_VARIABLE type name 0
        2) HEAP_VARIABLE name
        3) HEAP_VALUE (typeof a) a
        4) HEAP_VALUE (typeof b) b
        5) OPERATION +
        6) OPERATION =

    type[a+b] name;
        1) HEAP_VALUE (typeof a) a
        2) HEAP_VALUE (typeof b) b
        3) OPERATION +
        4) DECLARE_VARIABLE type name 1

    type[] name = {v1, v2, v3, v4};
        1) HEAP_VALUE int 4
        2) DECLARE_VARIABLE type name 1
        3) HEAP_VALUE (typeof v4) v4
        4) HEAP_VALUE (typeof v3) v3
        5) HEAP_VALUE (typeof v2) v2
        6) HEAP_VALUE (typeof v1) v1
        7) HEAP_VALUE int 4
        8) HEAP_VIRTUAL_ARRAY type 1
        9) OPERATION =

    type[4][5] name;
        1) HEAP_VALUE int 5
        2) HEAP_VALUE int 4
        3) DECLARE_VARIABLE type name 2

    sqrt(a);
        1) HEAP_VALUE (typeof a) a
        2) EXECUTE sqrt 1

    power(a, b);
        1) HEAP_VALUE (typeof a) a
        1) HEAP_VALUE (typeof b) b
        2) EXECUTE power 2

    double name = sqrt(a);
        1) DECLARE_VARIABLE double name 0
        2) HEAP_VARIABLE name
        3) HEAP_VALUE (typeof a) a
        4) EXECUTE sqrt 1
        5) OPERATION =

    sqrt(a);
    double name = 5;
        1) HEAP_VALUE (typeof a) a
        2) EXECUTE sqrt 1
        3) CLEAR_EXEC_HEAP

        4) DECLARE_VARIABLE double name 0
        5) HEAP_VARIABLE name
        6) HEAP_VALUE int 5
        7) OPERATION =

    int[4][5] name;
    name[1][2] = 10;
        1) HEAP_VALUE int 5
        2) HEAP_VALUE int 4
        3) DECLARE_VARIABLE int name 2
        4) CLEAR_EXEC_HEAP

        5) HEAP_VALUE int 2
        6) HEAP_VALUE int 1
        7) HEAP_VARIABLE name 2
        8) HEAP_VALUE int 10
        9) OPERATION =

    type[][] name = {{v11, v12}, {v21, v22}, {v31, v32}};
        1) HEAP_VALUE int 2
        2) HEAP_VALUE int 3
        3) DECLARE_VARIABLE type name 2
        4) HEAP_VARIABLE name
        4) HEAP_VALUE (typeof v32) v32
        5) HEAP_VALUE (typeof v31) v31
        6) HEAP_VALUE (typeof v22) v22
        7) HEAP_VALUE (typeof v21) v21
        8) HEAP_VALUE (typeof v12) v12
        9) HEAP_VALUE (typeof v11) v11
        10) HEAP_VALUE int 2
        11) HEAP_VALUE int 3
        12) HEAP_VIRTUAL_ARRAY type 2
        13) OPERATION =

    int[][] a = new int[2][3];
    variable: {
        name: a
        dim: 2
        value at 0: {
            dim: 1
            arrayLength: 3
            value at 0: {
                dim: 0
                arrayLength: 0
                value: default ex 0
            }
            value at 1: {
                dim: 0
                arrayLength: 0
                value: default ex 0
            }
            value at 2: {
                dim: 0
                arrayLength: 0
                value: default ex 0
            }
        }
        value at 1: {
            dim: 1
            arrayLength: 3
            value at 0: {
                dim: 0
                arrayLength: 0
                value: default ex 0
            }
            value at 1: {
                dim: 0
                arrayLength: 0
                value: default ex 0
            }
            value at 2: {
                dim: 0
                arrayLength: 0
                value: default ex 0
            }
        }
    }
     */
}
