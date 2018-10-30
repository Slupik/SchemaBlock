package io.github.slupik.schemablock.javafx.element.custom;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.DoublePropertyBase;
import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

/**
 * All rights reserved & copyright ©
 */
public abstract class CustomPolygon extends Polygon {

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

    public CustomPolygon(){
        startY.set(0);
        startX.set(0);
        preInit();
        setStroke(Color.BLACK);
        setStrokeWidth(1);
    }

    protected void preInit() {}

    protected abstract void recreate();

    public void setSize(double width, double height){
        this.width.set(width);
        this.height.set(height);
    }

    public void setWidth(double width) {
        this.width.set(width);
    }

    public double getWidth() {
        return width.get();
    }

    public void setHeight(double height) {
        this.height.set(height);
    }

    public double getHeight() {
        return height.get();
    }

    public double getInnerHeight(){
        return innerHeight.get();
    }

    public ReadOnlyDoubleProperty innerHeightProperty(){
        return innerHeight;
    }

    public DoubleProperty heightProperty(){
        return height;
    }

    public double getInnerWidth(){
        return innerWidth.get();
    }

    public ReadOnlyDoubleProperty innerWidthProperty(){
        return innerWidth;
    }

    public DoubleProperty widthProperty(){
        return width;
    }

    public ReadOnlyDoubleProperty innerStartX(){
        return startX;
    }

    public ReadOnlyDoubleProperty innerStartY(){
        return startY;
    }
}
