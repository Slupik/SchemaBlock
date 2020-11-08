package io.github.slupik.schemablock.newparser.memory.element;

/**
 * All rights reserved & copyright ©
 */
public class SimpleValueImpl implements SimpleValue {

    private final ValueType type;
    private final Object single;
    private final SimpleValue[] array;

    public SimpleValueImpl(ValueType type, Object value) {
        this.type = type;
        if (value instanceof SimpleValue && ((SimpleValue) value).isArray()) {
            this.array = new SimpleValue[]{((SimpleValue) value)};
            this.single = null;
        } else {
            this.single = value;
            this.array = null;
        }
    }

    public SimpleValueImpl(ValueType type, SimpleValue[] value) {
        this.type = type;
        this.array = value;
        this.single = null;
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> T getCastedValue() {
        if (single != null) {
            return ((T) ValueConverter.castValueToType(getType(), single));
        } else {
            return ((T) array);
        }
    }

    @Override
    public Object getValue() {
        if (single != null) {
            return ValueConverter.castValueToType(getType(), single);
        } else {
            return array;
        }
    }

//    @Override
//    public SimpleValue getValue(int index) throws IndexOutOfBoundsException {
//        if(index>=getArrayLength()) {
//            throw new IndexOutOfBoundsException(getArrayLength(), index);
//        }
//        assert array != null;
//        return array[index];
//    }
//
//    @Override
//    public SimpleValue getValue(int[] indexes) throws IndexOutOfBoundsException, ExceptedMoreDimensionsThanExists {
//        int firstIndex = indexes[0];
//        if(firstIndex>=getArrayLength()) {
//            throw new IndexOutOfBoundsException(getArrayLength(), firstIndex);
//        }
//        if(getDimensions()<indexes.length) {
//            throw new ExceptedMoreDimensionsThanExists(getDimensions(), indexes.length);
//        }
//        if(indexes.length!=1) {
//            int[] nestedIndexes = Arrays.copyOfRange(indexes, 1, indexes.length);
//            return getValue(firstIndex).getValue(nestedIndexes);
//        } else {
//            return getValue(firstIndex);
//        }
//    }
//
//    @Override
//    public void setValue(int index, SimpleValue value) throws IndexOutOfBoundsException, IncompatibleArrayException, IncompatibleTypeException {
//        if(index>=getArrayLength()) {
//            throw new IndexOutOfBoundsException(getArrayLength(), index);
//        }
//        SimpleValue copy = null;
//        if(value!=null) {
//            // type
//            if(!ValueType.isCompatible(type, value.getType())) {
//                throw new IncompatibleTypeException(type, value.getType());
//            }
//            // dimension - [][]...[]
//            int dim = getDimensions();
//            if(dim!=1) {
//                if(value.getDimensions()!=(dim-1)) {
//                    throw new IncompatibleArrayException(dim-1, value.getDimensions());
//                }
//            }
//            if(value.isArray()) {
//                copy = new SimpleValueImpl(type, ((SimpleValue[]) value.getValue()));
//            } else {
//                copy = new SimpleValueImpl(type, value.getValue());
//            }
//        }
//        array[index]=copy;
//    }
//
//    @Override
//    public void setValue(int[] indexes, SimpleValue value) throws IndexOutOfBoundsException, IncompatibleArrayException, IncompatibleTypeException {
//        int[] path = Arrays.copyOfRange(indexes, 0, indexes.length-1);
//
//        SimpleValue copy = null;
//        if(value!=null) {
//            // type
//            if(!ValueType.isCompatible(type, value.getType())) {
//                throw new IncompatibleTypeException(type, value.getType());
//            }
//            // dimension - [][]...[]
//            int dim = getDimensions();
//            if(value.getDimensions()!=(dim-path.length-1)) {
//                throw new IncompatibleArrayException(dim-path.length-1, value.getDimensions());
//            }
//            if(value.isArray()) {
//                copy = new SimpleValueImpl(type, ((SimpleValue[]) value.getValue()));
//            } else {
//                copy = new SimpleValueImpl(type, value.getValue());
//            }
//        }
//        try {
//            if(path.length!=0) {
//                getValue(path).setValue(indexes[indexes.length-1], copy);
//            } else {
//                setValue(indexes[0], copy);
//            }
//        } catch (ExceptedMoreDimensionsThanExists e) {
//            //Something were wrong checked before
//            e.printStackTrace();
//        }
//    }
//
//    @Override
//    public int getArrayLength() {
//        if(array==null) {
//            return -1;
//        } else {
//            return array.length;
//        }
//    }

    @Override
    public ValueType getType() {
        return type;
    }

//    @Override
//    public int getDimensions() {
//        if(array==null) {
//            if(single instanceof SimpleValue) {
//                return ((SimpleValue) single).getDimensions()+1;
//            }
//            return 0;
//        } else {
//            if(array.length>0 && array[0] != null) {
//                return array[0].getDimensions() + 1;
//            }
//            return 1;
//        }
//    }
//
//    @Override
//    public boolean isValue() {
//        return true;
//    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
