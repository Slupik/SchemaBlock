package io.github.slupik.schemablock.javafx.element.background;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.DoublePropertyBase;
import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;

/**
 * All rights reserved & copyright ©
 */
public abstract class CustomShapeBase extends Pane implements CustomShape {

    private Shape shape;

    public CustomShapeBase(){
        startY.set(0);
        startX.set(0);

        shape = createShape();
        onInit();

        width.addListener((observable, oldValue, newValue) -> recreate());
        height.addListener((observable, oldValue, newValue) -> recreate());

        onDimensionsBinding();
        getChildren().add(shape);

        setOuterSize(250, 124);
    }

    protected abstract void onDimensionsBinding();

    protected abstract Shape createShape();

    protected void onInit() {}

    protected abstract void recreate();

    protected final DoubleProperty innerWidth = new DoublePropertyBase() {

        @Override
        public void invalidated() {}

        @Override
        public Object getBean() {
            return CustomShapeBase.this;
        }

        @Override
        public String getName() {
            return "innerWidth";
        }
    };

    protected final DoubleProperty innerHeight = new DoublePropertyBase() {

        @Override
        public void invalidated() {}

        @Override
        public Object getBean() {
            return CustomShapeBase.this;
        }

        @Override
        public String getName() {
            return "innerHeight";
        }
    };

    protected final DoubleProperty width = new DoublePropertyBase() {

        @Override
        public void invalidated() {}

        @Override
        public Object getBean() {
            return CustomShapeBase.this;
        }

        @Override
        public String getName() {
            return "innerWidth";
        }
    };

    protected final DoubleProperty height = new DoublePropertyBase() {

        @Override
        public void invalidated() {}

        @Override
        public Object getBean() {
            return CustomShapeBase.this;
        }

        @Override
        public String getName() {
            return "innerHeight";
        }
    };

    protected final DoubleProperty startX = new DoublePropertyBase() {

        @Override
        public void invalidated() {}

        @Override
        public Object getBean() {
            return CustomShapeBase.this;
        }

        @Override
        public String getName() {
            return "startX";
        }
    };

    protected final DoubleProperty startY = new DoublePropertyBase() {

        @Override
        public void invalidated() {}

        @Override
        public Object getBean() {
            return CustomShapeBase.this;
        }

        @Override
        public String getName() {
            return "startY";
        }
    };

    @Override
    public void setOuterSize(double width, double height){
        this.width.set(width);
        this.height.set(height);
    }

    @Override
    public void setOuterWidth(double width) {
        this.width.set(width);
    }

    @Override
    public double getOuterWidth() {
        return width.get();
    }

    @Override
    public void setOuterHeight(double height) {
        this.height.set(height);
    }

    @Override
    public double getOuterHeight() {
        return height.get();
    }

    @Override
    public double getInnerHeight(){
        return innerHeight.get();
    }

    @Override
    public ReadOnlyDoubleProperty innerHeightProperty(){
        return innerHeight;
    }

    @Override
    public DoubleProperty outerHeightProperty(){
        return height;
    }

    @Override
    public double getInnerWidth(){
        return innerWidth.get();
    }

    @Override
    public ReadOnlyDoubleProperty innerWidthProperty(){
        return innerWidth;
    }

    @Override
    public DoubleProperty outerWidthProperty(){
        return width;
    }

    @Override
    public ReadOnlyDoubleProperty innerStartX(){
        return startX;
    }

    @Override
    public ReadOnlyDoubleProperty innerStartY(){
        return startY;
    }

    @Override
    public abstract void setFill(Color web);

    @Override
    public void highlight() {
        setFill(Color.YELLOW);
    }

    @Override
    public void resetColor() {
        setFill(Color.web("#ffffff"));
    }

    @Override
    public void markAsError() {
        setFill(Color.web("#de3700"));
    }

    @Override
    public void markAsExecuting() {
        setFill(Color.web("#00d5e0"));
    }

    @Override
    public boolean isContainsCoords(double x, double y) {
        return shape.contains(x, y);
    }
}
