package io.github.slupik.schemablock.parser.math.rpn.value;

/**
 * All rights reserved & copyright ©
 */
public class Value {

    private final ValueType type;
    private String value;

    public Value(ValueType type, Object value) {
        this(type, String.valueOf(value));
    }

    public Value(ValueType type, String value) {
        this.type = type;
        this.value = value;
    }

    public ValueType getType() {
        return type;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }



    public char getAsChar(){
        return value.charAt(0);
    }

    public short getAsShort(){
        return Short.parseShort(value);
    }

    public int getAsInt(){
        return Integer.parseInt(value);
    }

    public long getAsLong(){
        return Long.parseLong(value);
    }

    public float getAsFloat(){
        return Float.parseFloat(value);
    }

    public double getAsDouble(){
        return Double.parseDouble(value);
    }

    public boolean getAsBoolean(){
        return Boolean.parseBoolean(value);
    }

    public byte getAsByte(){
        return ((byte) getAsChar());
    }

    public String getAsString(){
        return value.substring(1, value.length()-2);
    }
}
