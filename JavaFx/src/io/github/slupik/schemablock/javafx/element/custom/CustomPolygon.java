package io.github.slupik.schemablock.javafx.element.custom;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.DoublePropertyBase;
import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

/**
 * All rights reserved & copyright ©
 */
public abstract class CustomPolygon extends Polygon implements CustomShape {

    public CustomPolygon(){
        startY.set(0);
        startX.set(0);
        preInit();
        setStroke(Color.BLACK);
        setStrokeWidth(1);
    }

    protected void preInit() {}

    protected abstract void recreate();

    protected final DoubleProperty innerWidth = new DoublePropertyBase() {

        @Override
        public void invalidated() {}

        @Override
        public Object getBean() {
            return CustomPolygon.this;
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
            return CustomPolygon.this;
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
            return CustomPolygon.this;
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
            return CustomPolygon.this;
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
            return CustomPolygon.this;
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
            return CustomPolygon.this;
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
}
