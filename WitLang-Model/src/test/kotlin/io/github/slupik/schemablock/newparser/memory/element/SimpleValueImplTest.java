package io.github.slupik.schemablock.newparser.memory.element;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * All rights reserved & copyright ©
 */
class SimpleValueImplTest {

    //TODO repair
//    @Test
//    void getDimensionsWithSometimesSingleValue() {
//        SimpleValue v1 = new SimpleValueImpl(ValueType.INTEGER, 423);
//        SimpleValue v2 = new SimpleValueImpl(ValueType.INTEGER, 223);
//        SimpleValue v3 = new SimpleValueImpl(ValueType.INTEGER, 532);
//        SimpleValue v4 = new SimpleValueImpl(ValueType.INTEGER, 543);
//        SimpleValue[] values = {v1, v2, v3, v4};
//
//        SimpleValue nest2 = new SimpleValueImpl(ValueType.INTEGER, values);
//        SimpleValue nest1 = new SimpleValueImpl(ValueType.INTEGER, nest2);
//        SimpleValue array = new SimpleValueImpl(ValueType.INTEGER, nest1);
//
//        assertEquals(1, nest2.getDimensions());
//        assertEquals(2, nest1.getDimensions());
//        assertEquals(3, array.getDimensions());
//
//        assertEquals(values.length, nest2.getArrayLength());
//        assertEquals(1, nest1.getArrayLength());
//        assertEquals(1, array.getArrayLength());
//    }
//
//    @Test
//    void getDimensions() {
//        SimpleValue v11 = new SimpleValueImpl(ValueType.INTEGER, 423);
//        SimpleValue v12 = new SimpleValueImpl(ValueType.INTEGER, 223);
//        SimpleValue v13 = new SimpleValueImpl(ValueType.INTEGER, 532);
//        SimpleValue v14 = new SimpleValueImpl(ValueType.INTEGER, 877);
//        SimpleValue[] values1 = {v11, v12, v13, v14};
//
//        SimpleValue v21 = new SimpleValueImpl(ValueType.INTEGER, 876);
//        SimpleValue v22 = new SimpleValueImpl(ValueType.INTEGER, 746);
//        SimpleValue v23 = new SimpleValueImpl(ValueType.INTEGER, 423);
//        SimpleValue v24 = new SimpleValueImpl(ValueType.INTEGER, 873);
//        SimpleValue[] values2 = {v21, v22, v23, v24};
//
//        SimpleValue nest2a = new SimpleValueImpl(ValueType.INTEGER, values2);
//        SimpleValue nest2b = new SimpleValueImpl(ValueType.INTEGER, values1);
//        SimpleValue[] nested = {nest2a, nest2b};
//
//        SimpleValue nest1 = new SimpleValueImpl(ValueType.INTEGER, nested);
//        SimpleValue array = new SimpleValueImpl(ValueType.INTEGER, nest1);
//
//        assertEquals(1, nest2a.getDimensions());
//        assertEquals(1, nest2b.getDimensions());
//        assertEquals(2, nest1.getDimensions());
//        assertEquals(3, array.getDimensions());
//
//        assertEquals(values1.length, nest2a.getArrayLength());
//        assertEquals(values2.length, nest2b.getArrayLength());
//        assertEquals(nested.length, nest1.getArrayLength());
//        assertEquals(1, array.getArrayLength());
//    }
//
//    @Test
//    void setValueInOneDim() throws IncompatibleArrayException, IndexOutOfBoundsException, ExceptedMoreDimensionsThanExists, IncompatibleTypeException {
//        SimpleValue v11 = new SimpleValueImpl(ValueType.INTEGER, 423);
//        SimpleValue v12 = new SimpleValueImpl(ValueType.INTEGER, 223);
//        SimpleValue v13 = new SimpleValueImpl(ValueType.INTEGER, 532);
//        SimpleValue v14 = new SimpleValueImpl(ValueType.INTEGER, 877);
//        SimpleValue[] values1 = {v11, v12, v13, v14};
//        SimpleValue nest2a = new SimpleValueImpl(ValueType.INTEGER, values1);
//
//        SimpleValue v21 = new SimpleValueImpl(ValueType.INTEGER, 876);
//        SimpleValue v22 = new SimpleValueImpl(ValueType.INTEGER, 746);
//        SimpleValue v23 = new SimpleValueImpl(ValueType.INTEGER, 423);
//        SimpleValue v24 = new SimpleValueImpl(ValueType.INTEGER, 873);
//        SimpleValue[] values2 = {v21, v22, v23, v24};
//        SimpleValue nest2b = new SimpleValueImpl(ValueType.INTEGER, values2);
//
//        SimpleValue nest1 = new SimpleValueImpl(ValueType.INTEGER, nest2a);
//        Assertions.assertEquals(v12.getValue(), nest1.getValue(0).getValue(1).getValue());
//        Assertions.assertEquals(v12.getValue(), nest1.getValue(new int[]{0, 1}).getValue());
//
//        nest1.setValue(0, nest2b);
//        Assertions.assertEquals(v22.getValue(), nest1.getValue(0).getValue(1).getValue());
//        Assertions.assertEquals(v22.getValue(), nest1.getValue(new int[]{0, 1}).getValue());
//    }
//
//    @Test
//    void setValueAfterGet() throws IncompatibleArrayException, IndexOutOfBoundsException, ExceptedMoreDimensionsThanExists, IncompatibleTypeException {
//        SimpleValue v11 = new SimpleValueImpl(ValueType.INTEGER, 423);
//        SimpleValue v12 = new SimpleValueImpl(ValueType.INTEGER, 223);
//        SimpleValue v13 = new SimpleValueImpl(ValueType.INTEGER, 532);
//        SimpleValue v14 = new SimpleValueImpl(ValueType.INTEGER, 877);
//        SimpleValue nest2a = new SimpleValueImpl(ValueType.INTEGER, new SimpleValue[]{v11, v12, v13, v14});
//
//        SimpleValue v21 = new SimpleValueImpl(ValueType.INTEGER, 876);
//        SimpleValue v22 = new SimpleValueImpl(ValueType.INTEGER, 746);
//        SimpleValue v23 = new SimpleValueImpl(ValueType.INTEGER, 423);
//        SimpleValue v24 = new SimpleValueImpl(ValueType.INTEGER, 873);
//        SimpleValue nest2b = new SimpleValueImpl(ValueType.INTEGER, new SimpleValue[]{v21, v22, v23, v24});
//
//        SimpleValue nest1 = new SimpleValueImpl(ValueType.INTEGER, nest2a);
//        SimpleValue array = new SimpleValueImpl(ValueType.INTEGER, nest1);
//        Assertions.assertEquals(v12.getValue(), array.getValue(0).getValue(0).getValue(1).getValue());
//
//        array.getValue(0).setValue(0, nest2b);
//        Assertions.assertEquals(v22.getValue(), array.getValue(0).getValue(0).getValue(1).getValue());
//        Assertions.assertEquals(v22.getValue(), array.getValue(new int[]{0, 0, 1}).getValue());
//    }
//
//    @Test
//    void setValueWithManyIndexes() throws IncompatibleArrayException, IndexOutOfBoundsException, ExceptedMoreDimensionsThanExists, IncompatibleTypeException {
//        SimpleValue v11 = new SimpleValueImpl(ValueType.INTEGER, 423);
//        SimpleValue v12 = new SimpleValueImpl(ValueType.INTEGER, 223);
//        SimpleValue v13 = new SimpleValueImpl(ValueType.INTEGER, 532);
//        SimpleValue v14 = new SimpleValueImpl(ValueType.INTEGER, 877);
//        SimpleValue nest2a = new SimpleValueImpl(ValueType.INTEGER, new SimpleValue[]{v11, v12, v13, v14});
//
//        SimpleValue v21 = new SimpleValueImpl(ValueType.INTEGER, 876);
//        SimpleValue v22 = new SimpleValueImpl(ValueType.INTEGER, 746);
//        SimpleValue v23 = new SimpleValueImpl(ValueType.INTEGER, 423);
//        SimpleValue v24 = new SimpleValueImpl(ValueType.INTEGER, 873);
//        SimpleValue nest2b = new SimpleValueImpl(ValueType.INTEGER, new SimpleValue[]{v21, v22, v23, v24});
//
//        SimpleValue nest1 = new SimpleValueImpl(ValueType.INTEGER, nest2a);
//        SimpleValue array = new SimpleValueImpl(ValueType.INTEGER, nest1);
//        Assertions.assertEquals(v12.getValue(), array.getValue(0).getValue(0).getValue(1).getValue());
//
//        array.setValue(new int[]{0, 0}, nest2b);
//        Assertions.assertEquals(v22.getValue(), array.getValue(0).getValue(0).getValue(1).getValue());
//        Assertions.assertEquals(v22.getValue(), array.getValue(new int[]{0, 0, 1}).getValue());
//
//        nest2a.setValue(new int[]{0}, v21);
//        Assertions.assertEquals(v21.getValue(), nest2a.getValue(0).getValue());
//        Assertions.assertEquals(v21.getValue(), nest2a.getValue(new int[]{0}).getValue());
//    }

    @Test
    void getCastedValue() {
        assertTrue(new SimpleValueImpl(ValueType.INTEGER, 876).getCastedValue() instanceof Integer);
        assertTrue(new SimpleValueImpl(ValueType.INTEGER, 876.0).getCastedValue() instanceof Integer);
        assertTrue(new SimpleValueImpl(ValueType.INTEGER, "876").getCastedValue() instanceof Integer);
        assertTrue(new SimpleValueImpl(ValueType.INTEGER, "876.0").getCastedValue() instanceof Integer);
    }
}