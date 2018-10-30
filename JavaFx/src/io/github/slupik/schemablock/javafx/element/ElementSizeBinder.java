package io.github.slupik.schemablock.javafx.element;

import io.github.slupik.schemablock.javafx.element.custom.CustomPolygon;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

/**
 * All rights reserved & copyright ©
 */
public class ElementSizeBinder {

    private final Input input;

    private double defaultWidth;
    private double defaultHeight;

    public ElementSizeBinder(Input input){
        this.input = input;
        run();
    }

    private void run() {
        defaultWidth = getMainContainer().getPrefWidth();
        defaultHeight = getMainContainer().getPrefHeight();
        if(getShape() instanceof Ellipse) {
            Ellipse ellipse = (Ellipse) getShape();

            getMainContainer().setPrefWidth(ellipse.getRadiusX()*2);
            getMainContainer().setPrefHeight(ellipse.getRadiusY()*2);

            ellipse.radiusXProperty().bind(getMainContainer().widthProperty().divide(2));
            ellipse.radiusYProperty().bind(getMainContainer().heightProperty().divide(2));
        } else if(getShape() instanceof Rectangle) {
            Rectangle rect = (Rectangle) getShape();

            getMainContainer().setPrefWidth(rect.getWidth());
            getMainContainer().setPrefHeight(rect.getHeight());

            rect.widthProperty().bind(getMainContainer().prefWidthProperty());
            rect.heightProperty().bind(getMainContainer().prefHeightProperty());
        } else if(getShape() instanceof CustomPolygon) {
            CustomPolygon polygon = (CustomPolygon) getShape();

            getMainContainer().setPrefWidth(polygon.getWidth());
            getMainContainer().setPrefHeight(polygon.getHeight());

            polygon.widthProperty().bind(getMainContainer().prefWidthProperty());
            polygon.heightProperty().bind(getMainContainer().prefHeightProperty());
        }

        getDescContainer().minWidthProperty().bind(getMainContainer().widthProperty());
        getDescContainer().minHeightProperty().bind(getMainContainer().heightProperty());
        getDescContainer().prefWidthProperty().bind(getMainContainer().widthProperty());
        getDescContainer().prefHeightProperty().bind(getMainContainer().heightProperty());
        getDescContainer().maxWidthProperty().bind(getMainContainer().widthProperty());
        getDescContainer().maxHeightProperty().bind(getMainContainer().heightProperty());

        if(getShape() instanceof CustomPolygon) {
            CustomPolygon polygon = (CustomPolygon) getShape();
            getDesc().maxWidthProperty().bind(polygon.innerWidthProperty());
            getDesc().maxHeightProperty().bind(polygon.innerHeightProperty());
        } else {
            getDesc().maxWidthProperty().bind(getMainContainer().widthProperty());
            getDesc().maxHeightProperty().bind(getMainContainer().heightProperty());
        }

        getDescContainer().setAlignment(Pos.CENTER);
    }

    public void setSize(double width, double height){
        setWidth(width);
        setHeight(height);
        resetPos();
    }

    public void setWidth(double width){
        getMainContainer().setPrefWidth(width);
        resetPos();
    }

    public void setHeight(double height){
        getMainContainer().setPrefHeight(height);
        resetPos();
    }

    private void resetPos() {
        Platform.runLater(()->{
            if(getShape() instanceof Ellipse) {
                Ellipse ellipse = (Ellipse) getShape();

                ellipse.setLayoutX(getMainContainer().getWidth()/2);
                ellipse.setLayoutY(getMainContainer().getHeight()/2);
            }
        });
    }

    private Pane getMainContainer() {
        return input.getMainContainer();
    }

    private Shape getShape() {
        return input.getVisibleShape();
    }

    private VBox getDescContainer() {
        return input.getDescContainer();
    }

    private Label getDesc() {
        return input.getDescLabel();
    }

    public interface Input {
        Pane getMainContainer();
        Shape getVisibleShape();
        VBox getDescContainer();
        Label getDescLabel();
    }
}
