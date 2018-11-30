package io.github.slupik.schemablock.javafx.element.fx.port;

import io.github.slupik.schemablock.javafx.element.fx.UiElementBase;
import io.github.slupik.schemablock.javafx.element.fx.arrow.Arrow;
import io.github.slupik.schemablock.javafx.element.fx.port.connector.PortConnector;
import io.github.slupik.schemablock.javafx.element.fx.port.group.PortListener;
import io.github.slupik.schemablock.model.ui.abstraction.element.*;
import io.github.slupik.schemablock.model.utils.RandomString;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static io.github.slupik.schemablock.javafx.element.UiElementType.IF;

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

    public void setNextElement(UiElementBase next) throws CannotSetupPort {
        boolean checkedForTrue = true;
        if(base.getType() == IF) {
            checkedForTrue = getPortBoolType();
        }
        setNextElementInLogic("", checkedForTrue); //just in case
        setNextElementInLogic(next.getElementId(), checkedForTrue);
        isPortForTrue = checkedForTrue;
        if(base.getType() == IF) {
            for(PortListener listener:listeners) {
                listener.onSetNextElement(next.getElementId(), checkedForTrue);
            }
        } else {
            for(PortListener listener:listeners) {
                listener.onSetNextElement(next.getElementId(), getPortId());
            }
        }
    }

    private boolean getPortBoolType() throws CannotSetupPort {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Wybierz typ połączenia");
        alert.setHeaderText("Jest to wyjście dla:");

        ButtonType btnTrue = new ButtonType("Prawdy", ButtonBar.ButtonData.YES);
        ButtonType btnFalse = new ButtonType("Fałszu", ButtonBar.ButtonData.NO);

        alert.getButtonTypes().setAll(btnTrue, btnFalse);

        Optional<ButtonType> result = alert.showAndWait();
        if(result.isPresent()) {
            return result.get() == btnTrue;
        } else {
            throw new CannotSetupPort("Port type is not set.");
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

    public boolean isAllowForInput() {
        return allowForInput;
    }

    public boolean isAllowForOutput() {
        return allowForOutput;
    }
}
