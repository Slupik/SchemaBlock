package io.github.slupik.schemablock.javafx.element.fx.port;

import io.github.slupik.schemablock.javafx.element.UiElementType;
import io.github.slupik.schemablock.javafx.element.fx.UiElementBase;
import io.github.slupik.schemablock.javafx.element.fx.arrow.Arrow;
import io.github.slupik.schemablock.javafx.element.fx.port.connector.PortConnector;
import io.github.slupik.schemablock.javafx.element.fx.port.group.PortListener;
import io.github.slupik.schemablock.model.ui.abstraction.element.*;
import io.github.slupik.schemablock.model.utils.RandomString;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.util.ArrayList;
import java.util.List;

/**
 * All rights reserved & copyright ©
 */
public class PortElement extends AnchorPane {

    private String id = RandomString.generate(32);

    private final boolean allowForInput;
    private final boolean allowForOutput;
    private final List<PortListener> listeners = new ArrayList<>();

    private UiElementBase base;
    private PortConnector connector;
    private boolean isPortForTrue = true;

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

    public void deleteActiveArrow() {
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

    public void setNextElement(UiElementBase next) {
        setNextElementInLogic("", isPortForTrue); //just in case
        setNextElementInLogic(next.getElementId(), false);
        if(base.getType() == UiElementType.IF) {
            for(PortListener listener:listeners) {
                listener.onSetNextElement(next.getElementId(), false);
            }
        } else {
            for(PortListener listener:listeners) {
                listener.onSetNextElement(next.getElementId(), getPortId());
            }
        }
    }

    private void setNextElementInLogic(String elementId, boolean isForTrue) {
        Element element = base.getLogicElement();
        switch (element.getType()) {
            case CALCULATION: {
                ((OperationElement) element).setNextElement(elementId);
                break;
            }
            case COMMUNICATION: {
                ((IOElement) element).setNextElement(elementId);
                break;
            }
            case START: {
                ((StartElement) element).setNextElement(elementId);
                break;
            }
            case CONDITION: {
                if(isForTrue) {
                    ((ConditionalElement) element).setOnTrue(elementId);
                } else {
                    ((ConditionalElement) element).setOnFalse(elementId);
                }
                break;
            }
            default: {
                System.out.println("Error unsupported type "+element.getType());
            }
        }
    }

    public String getPortId(){
        return id;
    }

    public boolean isPortForTrue() {
        return isPortForTrue;
    }

    public void addListener(PortListener listener) {
        listeners.add(listener);
    }
}
