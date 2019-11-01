package io.github.slupik.schemablock.javafx.element.fx.port;

import com.google.gson.Gson;
import io.github.slupik.schemablock.javafx.element.block.implementation.DescribedBlockPrototype;
import io.github.slupik.schemablock.javafx.element.fx.arrow.Arrow;
import io.github.slupik.schemablock.javafx.element.fx.port.connector.old.PortConnector;
import io.github.slupik.schemablock.javafx.element.fx.port.group.PortListener;
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
    private final String name;
    private final String ownerElementId;
    private final String positionName;
    private final PortInfo actualInfo;

    private final boolean allowForInput;
    private final boolean allowForOutput;
    private final List<PortListener> listeners = new ArrayList<>();

    private DescribedBlockPrototype base;
    private PortConnector connector;
    private boolean isPortForTrue = true;

    private Circle circle = new Circle();
    private Arrow arrowToNextElement;

    public PortElement(DescribedBlockPrototype base, PortConnector connector, PortInfo info){
        this.base = base;
        this.connector = connector;
        this.allowForInput = info.allowForInput;
        this.allowForOutput = info.allowForOutput;
        this.ownerElementId = info.parentElementId;
        this.positionName = info.positionName;
        name = ownerElementId+";"+positionName;
        info.id = id;
        this.actualInfo = info;

        getChildren().add(circle);
        circle.setRadius(3);
        circle.setFill(Color.BLACK);
        toFront();

        circle.addEventFilter(MouseEvent.MOUSE_PRESSED, event -> onMousePressed());
    }

    public void setRelativePos(double percentOfWidth, double percentOfHeight){
        layoutXProperty().bind(base.layoutXProperty().add(base.widthProperty().multiply(percentOfWidth)));
        layoutYProperty().bind(base.layoutYProperty().add(base.heightProperty().multiply(percentOfHeight)));
//        layoutXProperty().bind(base.widthProperty().multiply(percentOfWidth));
//        layoutYProperty().bind(base.heightProperty().multiply(percentOfHeight));
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
            //TODO repair
//            connector.setInput(base.getLogicElement());
            toFront();
        }
    }

    private void onMousePressed() {
        if(!connector.isSearchingInput() && allowForOutput) {
            connector.setLineStart(this, getLayoutX(), getLayoutY());
            toFront();
        }
    }

    public DescribedBlockPrototype getElement(){
        return base;
    }

    public void setNextElement(DescribedBlockPrototype next) throws CannotSetupPort {
        boolean checkedForTrue = true;
        if(base.getType() == IF) {
            checkedForTrue = getPortBoolType();
        }
        setNextElementInLogic("", checkedForTrue); //just in case
        setNextElementInLogic(next.getElementId(), checkedForTrue);

        setNextElement(next, checkedForTrue);
    }

    public void setNextElement(DescribedBlockPrototype next, boolean checkedForTrue) {
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

    public void removeNextElement(){
        setNextElementInLogic("", true);
        setNextElementInLogic("", false);
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
        actualInfo.isNextElementForTrue = isForTrue;
        //TODO repair
//        Element element = base.getLogicElement();
//        switch (element.getType()) {
//            case CALCULATION: {
//                ((OperationElement) element).setNextElement(elementId);
//                break;
//            }
//            case COMMUNICATION: {
//                ((IOElement) element).setNextElement(elementId);
//                break;
//            }
//            case START: {
//                ((StartElement) element).setNextElement(elementId);
//                break;
//            }
//            case CONDITION: {
//                if(isForTrue) {
//                    ((ConditionalElement) element).setOnTrue(elementId);
//                } else {
//                    ((ConditionalElement) element).setOnFalse(elementId);
//                }
//                break;
//            }
//            default: {
//                System.err.println("Error unsupported type "+element.getType());
//            }
//        }
    }

    public void configureArrowOut(Arrow arrow) {
        if(base.getType()==IF) {
            if(isPortForTrue()) {
                arrow.setDesc("P");
            } else {
                arrow.setDesc("F");
            }
        }
    }

    public void configurePortOut(PortElement endPort) {
        actualInfo.endPortName = endPort.name;
        actualInfo.endPortId = endPort.id;
    }

    public String getPortId(){
        return id;
    }

    public String getPortName() {
        return name;
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

    public String getEndPortName(){
        return actualInfo.endPortName;
    }

    public String getEndPortId() {
        return actualInfo.endPortId;
    }

    public String stringify() {
        return new Gson().toJson(actualInfo);
    }
}
