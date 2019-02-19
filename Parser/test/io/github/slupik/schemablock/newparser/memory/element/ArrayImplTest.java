package io.github.slupik.schemablock.newparser.memory.element;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * All rights reserved & copyright ©
 */
class ArrayImplTest {

    @Test
    void checkOneDim() {
        ArrayImpl array = new ArrayImpl(ValueType.INTEGER, 1, 10);
        assertNull(array.getElement(new int[]{5}));

        array.setValue(new int[]{5}, new ValueImpl(ValueType.INTEGER, 99));
        assertEquals(99, ((Value) array.getElement(new int[]{5})).getValue());
    }

    @Test
    void checkMultiDim() {
        ArrayImpl parentArray = new ArrayImpl(ValueType.INTEGER, 2, 10);
        ArrayImpl childArray = new ArrayImpl(ValueType.INTEGER, 1, 6);

        parentArray.setValue(new int[]{5}, childArray);
        childArray.setValue(new int[]{2}, new ValueImpl(ValueType.INTEGER, 99));
        assertEquals(99, ((Value) parentArray.getElement(new int[]{5, 2})).getValue());

        parentArray.setValue(new int[]{5, 2}, new ValueImpl(ValueType.INTEGER, 85));
        assertEquals(85, ((Value) parentArray.getElement(new int[]{5, 2})).getValue());
    }
}