package io.github.slupik.schemablock.javafx.element.custom;

/**
 * All rights reserved & copyright ©
 */
public class Parallelogram extends CustomPolygon {

    @Override
    protected void recreate() {
        getPoints().clear();
        getPoints().addAll((getWidth()/5), 0.0, getWidth(), 0.0, (getWidth()/5*4), getHeight(), 0.0, getHeight(), (getWidth()/5), 0.0);
    }

    @Override
    protected void preInit() {
        super.preInit();
        setSize(250, 124);
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
