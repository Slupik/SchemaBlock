package io.github.slupik.schemablock.view.logic.memory;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import io.github.slupik.schemablock.model.ui.error.AlgorithmException;
import io.github.slupik.schemablock.newparser.memory.element.*;
import javafx.beans.property.SimpleStringProperty;

import javax.inject.Inject;

/**
 * All rights reserved & copyright ©
 */
public class HeapValueFx extends RecursiveTreeObject<HeapValueFx> implements Variable {

    private final Variable source;
    private final Runnable callbackAfterItemChange;
    private final SimpleStringProperty name = new SimpleStringProperty(null);
    private final SimpleStringProperty value = new SimpleStringProperty(null);
    private final SimpleStringProperty type = new SimpleStringProperty(null);

    @Inject
    public HeapValueFx(Variable source, Runnable callbackAfterItemChange) {
        this.source = source;
        this.callbackAfterItemChange = callbackAfterItemChange;
        name.setValue(source.getName());
        updateValue();
        updateType();
    }

    public void refresh() {
        updateValue();
    }

    private void updateValue() {
        value.setValue(getAsString(source.getContent()));
    }

    public String getName() {
        return name.getValue();
    }

    public void setName(String name) {
        this.name.setValue(name);
    }

    @Override
    public int getDimensionsCount() {
        return source.getDimensionsCount();
    }

    @Override
    public ValueType getType() {
        return source.getType();
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
        if (source.getType() == null) {
            type.setValue("???");
        } else {
            String arrayPart = getArrayPart(source.getDimensionsCount());
            type.setValue(source.getType().name().toLowerCase() + arrayPart);
        }
    }

    private String getArrayPart(int dimensionsCount) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < dimensionsCount; i++) {
            builder.append("[]");
        }
        return builder.toString();
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

    public void setValue(Object value) {
        if (value == null) {
            this.value.setValue(null);
        } else {
            this.value.setValue(value.toString());
        }
    }

    public SimpleStringProperty getValueProperty() {
        return value;
    }

    public SimpleStringProperty getTypeProperty() {
        return type;
    }

}
