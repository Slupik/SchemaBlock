package io.github.slupik.schemablock.javafx.logic.heap;

import io.github.slupik.schemablock.parser.math.rpn.variable.value.ValueType;
import javafx.beans.property.SimpleStringProperty;

/**
 * All rights reserved & copyright ©
 */
public class HeapValueFx {

    private SimpleStringProperty name = new SimpleStringProperty(null);
    private SimpleStringProperty value = new SimpleStringProperty(null);
    private SimpleStringProperty type = new SimpleStringProperty(null);

    public void setName(String name) {
        this.name.setValue(name);
    }

    public String getName() {
        return name.getValue();
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
        if(value==null) {
            this.value.setValue(null);
        } else {
            this.value.setValue(value.toString());
        }
    }

    public String getType() {
        return type.getValue();
    }

    public SimpleStringProperty getTypeProprty() {
        return type;
    }

    public void setType(ValueType type) {
        if(type==ValueType.STRING) {
            this.type.setValue(
                    type.toString().substring(0, 1).toUpperCase()+
                    type.toString().substring(1).toLowerCase()
            );
        } else {
            this.type.setValue(type.toString().toLowerCase());
        }
    }
}
