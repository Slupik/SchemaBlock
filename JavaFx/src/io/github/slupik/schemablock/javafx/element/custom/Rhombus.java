package io.github.slupik.schemablock.javafx.element.custom;

/**
 * All rights reserved & copyright ©
 */
public class Rhombus extends CustomPolygon {

    @Override
    protected void recreate() {
        getPoints().clear();
        getPoints().addAll(
                    getWidth()/2, 0d,
                    getWidth(), getHeight()/2,
                    getWidth()/2, getHeight(),
                    0d, getHeight()/2,
                    getWidth()/2, 0d
                );
    }

    @Override
    protected void preInit() {
        super.preInit();
        setSize(250, 124);
        startY.set(0);
        width.addListener((observable, oldValue, newValue) -> {
            innerWidth.setValue(newValue.doubleValue()/2);
            startX.setValue(newValue.doubleValue()/4);
            recreate();
        });
        height.addListener((observable, oldValue, newValue) -> {
            innerHeight.setValue(newValue.doubleValue()/2);
            startY.setValue(newValue.doubleValue()/4);
            recreate();
        });
    }
}
