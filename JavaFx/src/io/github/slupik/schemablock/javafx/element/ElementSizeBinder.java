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
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.Text;

/**
 * All rights reserved & copyright ©
 */
public class ElementSizeBinder {

    private final Input input;

    public ElementSizeBinder(Input input){
        this.input = input;
        run();
    }

    private void run() {
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

            getMainContainer().setPrefWidth(polygon.getOuterWidth());
            getMainContainer().setPrefHeight(polygon.getOuterHeight());

            polygon.outerWidthProperty().bind(getMainContainer().prefWidthProperty());
            polygon.outerHeightProperty().bind(getMainContainer().prefHeightProperty());
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

            polygon.innerWidthProperty().addListener((observable, oldValue, newValue) ->
                    resetFontSize(newValue.doubleValue(), polygon.getInnerHeight()));
            polygon.innerHeightProperty().addListener((observable, oldValue, newValue) ->
                    resetFontSize(polygon.getInnerWidth(), newValue.doubleValue()));
        } else {
            getDesc().maxWidthProperty().bind(getMainContainer().widthProperty());
            getDesc().maxHeightProperty().bind(getMainContainer().heightProperty());

            getDesc().widthProperty().addListener((observable, oldValue, newValue) ->
                    resetFontSize(newValue.doubleValue(), getMainContainer().heightProperty().get()));
            getDesc().heightProperty().addListener((observable, oldValue, newValue) ->
                    resetFontSize(getMainContainer().widthProperty().get(), newValue.doubleValue()));
        }

        getDescContainer().setAlignment(Pos.CENTER);
    }

    private void resetFontSize(double width, double height) {
        Font unitFont = Font.font(getDesc().getFont().getFamily(), FontPosture.findByName(getDesc().getFont().getStyle()), 1);

        String text = getDesc().getText();
        Text textArea = new Text(text);
        textArea.setFont(unitFont);
        double unitWidth = textArea.getBoundsInLocal().getWidth();

        Font newFont = Font.font(getDesc().getFont().getFamily(), FontPosture.findByName(getDesc().getFont().getStyle()), width/unitWidth);
        textArea.setFont(newFont);

        if(textArea.getBoundsInLocal().getHeight()>height) {
            double unitHeight = textArea.getBoundsInLocal().getHeight();
            newFont = Font.font(getDesc().getFont().getFamily(), FontPosture.findByName(getDesc().getFont().getStyle()), height/unitHeight);
        }

        getDesc().setFont(newFont);
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
