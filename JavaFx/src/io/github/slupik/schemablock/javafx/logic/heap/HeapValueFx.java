package io.github.slupik.schemablock.javafx.logic.heap;

import io.github.slupik.schemablock.model.ui.error.AlgorithmException;
import io.github.slupik.schemablock.newparser.memory.element.*;
import javafx.beans.property.SimpleStringProperty;

/**
 * All rights reserved & copyright ©
 */
public class HeapValueFx implements Variable {

    private final Variable source;
    private final Runnable callbackAfterItemChange;
    private final SimpleStringProperty name = new SimpleStringProperty(null);
    private final SimpleStringProperty value = new SimpleStringProperty(null);
    private final SimpleStringProperty type = new SimpleStringProperty(null);

    public HeapValueFx(Variable source, Runnable callbackAfterItemChange) {
        this.source = source;
        this.callbackAfterItemChange = callbackAfterItemChange;
        name.setValue(source.getName());
        updateValue();
        updateType();
    }

    private void updateValue() {
        value.setValue(getAsString(source.getContent()));
    }

    public void setName(String name) {
        this.name.setValue(name);
    }

    public String getName() {
        return name.getValue();
    }

    @Override
    public int getDimensionsCount() {
        return source.getDimensionsCount();
    }

    @Override
    public ValueType getType() {
        return source.getType();
    }

    @Override
    public Value getContent() {
        return source.getContent();
    }

    @Override
    public void setContent(Value value) throws AlgorithmException {
        setValue(getAsString(value));
        source.setContent(value);
        updateType();
        callbackAfterItemChange.run();
    }

    private void updateType() {
        if(source.getContent()==null) {
            type.setValue("???");
        } else {
            type.setValue(String.valueOf(source.getContent().getType()));
        }
    }

    private String getAsString(Value value) {
        if (value instanceof SimpleValue) {
            SimpleValue simpleValue = ((SimpleValue) value);
            return String.valueOf(simpleValue.getValue());
        }
        if (value instanceof Array) {
            Array arrayValue = ((Array) value);
            StringBuilder result = new StringBuilder("[");
            int index = 0;
            for (ArrayCell cell : arrayValue.getCells()) {
                if (index > 0) {
                    result.append(", ");
                }
                result.append(index).append(": ").append(getAsString(cell.getValue()));
                index++;
            }
            result.append("]");

            return result.toString();
        }
        return String.valueOf(value);
    }

    public SimpleStringProperty getNameProperty() {
        return name;
    }

    public String getValue() {
        return value.getValue();
    }


    public SimpleStringProperty getValueProperty() {
        return value;
    }

    public void setValue(Object value) {
        if (value == null) {
            this.value.setValue(null);
        } else {
            this.value.setValue(value.toString());
        }
    }

    public SimpleStringProperty getTypeProprty() {
        return type;
    }

    public void setType(ValueType type) {
        if (type == ValueType.STRING) {
            this.type.setValue(
                    type.toString().substring(0, 1).toUpperCase() +
                            type.toString().substring(1).toLowerCase()
            );
        } else {
            this.type.setValue(type.toString().toLowerCase());
        }
    }

}
