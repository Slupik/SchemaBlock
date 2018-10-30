package io.github.slupik.schemablock.javafx.element.custom;

/**
 * All rights reserved & copyright ©
 */
public class Parallelogram extends CustomPolygon {

    @Override
    protected void recreate() {
        getPoints().clear();
        getPoints().addAll((getOuterWidth()/5), 0.0, getOuterWidth(), 0.0, (getOuterWidth()/5*4), getOuterHeight(), 0.0, getOuterHeight(), (getOuterWidth()/5), 0.0);
    }

    @Override
    protected void preInit() {
        super.preInit();
        setOuterSize(250, 124);
        startY.set(0);
        width.addListener((observable, oldValue, newValue) -> {
            innerWidth.setValue(newValue.doubleValue()/5*3);
            startX.setValue(newValue.doubleValue()/5);
            recreate();
        });
        height.addListener((observable, oldValue, newValue) -> {
            innerHeight.setValue(newValue.doubleValue());
            recreate();
        });
    }
}
