package io.github.slupik.schemablock.newparser.memory.element;

import io.github.slupik.schemablock.newparser.compilator.exception.ExceptedMoreDimensionsThanExists;
import io.github.slupik.schemablock.newparser.compilator.exception.IncompatibleArrayException;
import io.github.slupik.schemablock.newparser.compilator.exception.IncompatibleTypeException;
import io.github.slupik.schemablock.newparser.compilator.exception.IndexOutOfBoundsException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * All rights reserved & copyright ©
 */
class ValueImplTest {

    @Test
    void getDimensionsWithSometimesSingleValue() {
        Value v1 = new ValueImpl(ValueType.INTEGER, 423);
        Value v2 = new ValueImpl(ValueType.INTEGER, 223);
        Value v3 = new ValueImpl(ValueType.INTEGER, 532);
        Value v4 = new ValueImpl(ValueType.INTEGER, 543);
        Value[] values = {v1, v2, v3, v4};

        Value nest2 = new ValueImpl(ValueType.INTEGER, values);
        Value nest1 = new ValueImpl(ValueType.INTEGER, nest2);
        Value array = new ValueImpl(ValueType.INTEGER, nest1);

        assertEquals(1, nest2.getDimensions());
        assertEquals(2, nest1.getDimensions());
        assertEquals(3, array.getDimensions());

        assertEquals(values.length, nest2.getArrayLength());
        assertEquals(1, nest1.getArrayLength());
        assertEquals(1, array.getArrayLength());
    }

    @Test
    void getDimensions() {
        Value v11 = new ValueImpl(ValueType.INTEGER, 423);
        Value v12 = new ValueImpl(ValueType.INTEGER, 223);
        Value v13 = new ValueImpl(ValueType.INTEGER, 532);
        Value v14 = new ValueImpl(ValueType.INTEGER, 877);
        Value[] values1 = {v11, v12, v13, v14};

        Value v21 = new ValueImpl(ValueType.INTEGER, 876);
        Value v22 = new ValueImpl(ValueType.INTEGER, 746);
        Value v23 = new ValueImpl(ValueType.INTEGER, 423);
        Value v24 = new ValueImpl(ValueType.INTEGER, 873);
        Value[] values2 = {v21, v22, v23, v24};

        Value nest2a = new ValueImpl(ValueType.INTEGER, values2);
        Value nest2b = new ValueImpl(ValueType.INTEGER, values1);
        Value[] nested = {nest2a, nest2b};

        Value nest1 = new ValueImpl(ValueType.INTEGER, nested);
        Value array = new ValueImpl(ValueType.INTEGER, nest1);

        assertEquals(1, nest2a.getDimensions());
        assertEquals(1, nest2b.getDimensions());
        assertEquals(2, nest1.getDimensions());
        assertEquals(3, array.getDimensions());

        assertEquals(values1.length, nest2a.getArrayLength());
        assertEquals(values2.length, nest2b.getArrayLength());
        assertEquals(nested.length, nest1.getArrayLength());
        assertEquals(1, array.getArrayLength());
    }

    @Test
    void setValueInOneDim() throws IncompatibleArrayException, IndexOutOfBoundsException, ExceptedMoreDimensionsThanExists, IncompatibleTypeException {
        Value v11 = new ValueImpl(ValueType.INTEGER, 423);
        Value v12 = new ValueImpl(ValueType.INTEGER, 223);
        Value v13 = new ValueImpl(ValueType.INTEGER, 532);
        Value v14 = new ValueImpl(ValueType.INTEGER, 877);
        Value[] values1 = {v11, v12, v13, v14};
        Value nest2a = new ValueImpl(ValueType.INTEGER, values1);

        Value v21 = new ValueImpl(ValueType.INTEGER, 876);
        Value v22 = new ValueImpl(ValueType.INTEGER, 746);
        Value v23 = new ValueImpl(ValueType.INTEGER, 423);
        Value v24 = new ValueImpl(ValueType.INTEGER, 873);
        Value[] values2 = {v21, v22, v23, v24};
        Value nest2b = new ValueImpl(ValueType.INTEGER, values2);

        Value nest1 = new ValueImpl(ValueType.INTEGER, nest2a);
        Assertions.assertEquals(v12.getValue(), nest1.getValue(0).getValue(1).getValue());
        Assertions.assertEquals(v12.getValue(), nest1.getValue(new int[]{0, 1}).getValue());

        nest1.setValue(0, nest2b);
        Assertions.assertEquals(v22.getValue(), nest1.getValue(0).getValue(1).getValue());
        Assertions.assertEquals(v22.getValue(), nest1.getValue(new int[]{0, 1}).getValue());
    }

    @Test
    void setValueAfterGet() throws IncompatibleArrayException, IndexOutOfBoundsException, ExceptedMoreDimensionsThanExists, IncompatibleTypeException {
        Value v11 = new ValueImpl(ValueType.INTEGER, 423);
        Value v12 = new ValueImpl(ValueType.INTEGER, 223);
        Value v13 = new ValueImpl(ValueType.INTEGER, 532);
        Value v14 = new ValueImpl(ValueType.INTEGER, 877);
        Value nest2a = new ValueImpl(ValueType.INTEGER, new Value[]{v11, v12, v13, v14});

        Value v21 = new ValueImpl(ValueType.INTEGER, 876);
        Value v22 = new ValueImpl(ValueType.INTEGER, 746);
        Value v23 = new ValueImpl(ValueType.INTEGER, 423);
        Value v24 = new ValueImpl(ValueType.INTEGER, 873);
        Value nest2b = new ValueImpl(ValueType.INTEGER, new Value[]{v21, v22, v23, v24});

        Value nest1 = new ValueImpl(ValueType.INTEGER, nest2a);
        Value array = new ValueImpl(ValueType.INTEGER, nest1);
        Assertions.assertEquals(v12.getValue(), array.getValue(0).getValue(0).getValue(1).getValue());

        array.getValue(0).setValue(0, nest2b);
        Assertions.assertEquals(v22.getValue(), array.getValue(0).getValue(0).getValue(1).getValue());
        Assertions.assertEquals(v22.getValue(), array.getValue(new int[]{0, 0, 1}).getValue());
    }

    @Test
    void setValueWithManyIndexes() throws IncompatibleArrayException, IndexOutOfBoundsException, ExceptedMoreDimensionsThanExists, IncompatibleTypeException {
        Value v11 = new ValueImpl(ValueType.INTEGER, 423);
        Value v12 = new ValueImpl(ValueType.INTEGER, 223);
        Value v13 = new ValueImpl(ValueType.INTEGER, 532);
        Value v14 = new ValueImpl(ValueType.INTEGER, 877);
        Value nest2a = new ValueImpl(ValueType.INTEGER, new Value[]{v11, v12, v13, v14});

        Value v21 = new ValueImpl(ValueType.INTEGER, 876);
        Value v22 = new ValueImpl(ValueType.INTEGER, 746);
        Value v23 = new ValueImpl(ValueType.INTEGER, 423);
        Value v24 = new ValueImpl(ValueType.INTEGER, 873);
        Value nest2b = new ValueImpl(ValueType.INTEGER, new Value[]{v21, v22, v23, v24});

        Value nest1 = new ValueImpl(ValueType.INTEGER, nest2a);
        Value array = new ValueImpl(ValueType.INTEGER, nest1);
        Assertions.assertEquals(v12.getValue(), array.getValue(0).getValue(0).getValue(1).getValue());

        array.setValue(new int[]{0, 0}, nest2b);
        Assertions.assertEquals(v22.getValue(), array.getValue(0).getValue(0).getValue(1).getValue());
        Assertions.assertEquals(v22.getValue(), array.getValue(new int[]{0, 0, 1}).getValue());

        nest2a.setValue(new int[]{0}, v21);
        Assertions.assertEquals(v21.getValue(), nest2a.getValue(0).getValue());
        Assertions.assertEquals(v21.getValue(), nest2a.getValue(new int[]{0}).getValue());
    }
}