package io.github.slupik.schemablock.newparser.bytecode.bytecommand;

/**
 * All rights reserved & copyright ©
 */
public enum ByteCommandType {
    HEAP_VALUE, //val -> heap
    HEAP_VAR, //var -> heap
    HEAP_ARRAY, //var -> heap
    OPERATION, //Makes operation on elements, result -> heap
    EXECUTE, //Execute function, result -> heap
    SET_VAR, //heap -> value, sets variable to the new value
    SET_TAB, //heap-> index, heap -> value, sets variable at specific index to the new value
    DECLARE_VAR, //Creates variable
    DECLARE_TAB, //heap-> size, Creates array with specific size
    CLEAR_EXEC_HEAP,
    ; //Clears the heap with temporary values
}
