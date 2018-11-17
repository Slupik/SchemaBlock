package io.github.slupik.schemablock.javafx.element.fx.dialog;

import java.util.ArrayList;
import java.util.List;

/**
 * All rights reserved & copyright ©
 */
public class IODialogInput {
    public String desc = "";
    public final List<Value> data = new ArrayList<>();

    public class Value {
        public boolean isInput;
        public String value;
    }
}
