package io.github.slupik.schemablock.javafx.element.fx.port;

import io.github.slupik.schemablock.javafx.element.fx.UiElementBase;
import io.github.slupik.schemablock.javafx.element.fx.arrow.Arrow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 * All rights reserved & copyright ©
 */
public class PortElement extends AnchorPane {

    private UiElementBase base;
    private PortConnector connector;
    private final boolean allowForInput;
    private final boolean allowForOutput;

    private Circle circle = new Circle();
    private Arrow arrowToNextElement;

    public PortElement(UiElementBase base, PortConnector connector, boolean allowForInput, boolean allowForOutput){
        this.base = base;
        this.connector = connector;
        this.allowForInput = allowForInput;
        this.allowForOutput = allowForOutput;

        getChildren().add(circle);
        circle.setRadius(3);
        circle.setFill(Color.BLACK);
        toFront();

        circle.addEventFilter(MouseEvent.MOUSE_PRESSED, event -> onMousePressed());
    }

    public void setRelativePos(double percentOfWidth, double percentOfHeight){
        layoutXProperty().bind(base.layoutXProperty().add(base.widthProperty().multiply(percentOfWidth)));
        layoutYProperty().bind(base.layoutYProperty().add(base.heightProperty().multiply(percentOfHeight)));
        layoutXProperty().addListener(observable -> toFront());
        layoutYProperty().addListener(observable -> toFront());
    }

    public void bindArrowStart(Arrow arrow) {
        layoutXProperty().addListener((observable, oldValue, newValue) ->
                arrow.setStart(newValue.doubleValue(), getLayoutY()));
        layoutYProperty().addListener((observable, oldValue, newValue) ->
                arrow.setStart(getLayoutX(), newValue.doubleValue()));

        deleteActiveArrow();
        this.arrowToNextElement = arrow;

        //Otherwise port will be under arrow
        toFront();
    }

    private void deleteActiveArrow() {
        if(arrowToNextElement !=null) {
            ((Pane) arrowToNextElement.getParent()).getChildren().remove(arrowToNextElement);
            arrowToNextElement = null;
        }
    }

    public void bindArrowEnd(Arrow arrow) {
        layoutXProperty().addListener((observable, oldValue, newValue) ->
                arrow.setEnd(newValue.doubleValue(), getLayoutY()));
        layoutYProperty().addListener((observable, oldValue, newValue) ->
                arrow.setEnd(getLayoutX(), newValue.doubleValue()));

        //Otherwise port will be under arrow
        toFront();
    }

    public boolean isContainsPoint(double x, double y) {
        x = x-getLayoutX();
        y = y-getLayoutY();
        return circle.contains(x, y);
    }

    private boolean isExit = true;

    public void onMouseEnter() {
        if(isExit) {
            isExit = false;
            if((connector.isSearchingInput() && allowForInput) || (!connector.isSearchingInput() && allowForOutput)) {
                circle.setFill(Color.GREEN);
            } else {
                circle.setFill(Color.DARKRED);
            }
        }
    }

    public void onMouseExit() {
        if(!isExit) {
            isExit = true;
            circle.setFill(Color.BLACK);
        }
    }

    public void onMouseReleased() {
        if(connector.isSearchingInput() && allowForInput) {
            connector.setLineEnd(this, getLayoutX(), getLayoutY());
            connector.setInput(base.getLogicElement());
            toFront();
        }
    }

    private void onMousePressed() {
        if(!connector.isSearchingInput() && allowForOutput) {
            connector.setLineStart(this, getLayoutX(), getLayoutY());
            toFront();
        }
    }

    public UiElementBase getElement(){
        return base;
    }

    public void setNextElement(UiElementBase element) {
        //TODO implement this
        //What if port is part of ConditionBlock?
    }
}
