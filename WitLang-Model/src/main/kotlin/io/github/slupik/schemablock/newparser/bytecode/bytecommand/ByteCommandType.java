package io.github.slupik.schemablock.newparser.bytecode.bytecommand;

/**
 * All rights reserved & copyright ©
 */
public enum ByteCommandType {
    HEAP_VALUE, //val -> heap
    HEAP_VAR, //var -> heap
    HEAP_VIRTUAL_ARRAY, //heap-> size, Creates array with specific size
    OPERATION, //Makes operation on elements, result -> heap
    EXECUTE, //Execute function, result -> heap
    DECLARE_VAR, //Creates variable
    CLEAR_EXEC_HEAP, //Clears the heap with temporary values
    ;
}
