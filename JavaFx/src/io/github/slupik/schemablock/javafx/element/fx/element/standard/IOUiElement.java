package io.github.slupik.schemablock.javafx.element.fx.element.standard;

import io.github.slupik.schemablock.javafx.element.UiElementType;
import io.github.slupik.schemablock.javafx.element.WrongTypeOfElement;
import io.github.slupik.schemablock.javafx.element.background.CustomShapeBase;
import io.github.slupik.schemablock.javafx.element.background.Parallelogram;
import io.github.slupik.schemablock.javafx.element.fx.dialog.DialogFactory;
import io.github.slupik.schemablock.javafx.element.fx.dialog.IODialogInput;
import io.github.slupik.schemablock.javafx.element.fx.dialog.IOType;
import io.github.slupik.schemablock.javafx.element.fx.port.PortInfo;
import io.github.slupik.schemablock.model.ui.abstraction.ElementType;
import io.github.slupik.schemablock.model.ui.abstraction.element.Element;
import io.github.slupik.schemablock.model.ui.abstraction.element.StandardElement;
import io.github.slupik.schemablock.model.ui.implementation.element.specific.CommunicationBlock;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * All rights reserved & copyright ©
 */
public class IOUiElement extends UiStandardElement {

    @FXML
    private Pane elementContainer;

    @FXML
    private VBox descContainer;

    @FXML
    private Label desc;

    private Parallelogram shape;

    @Override
    protected void onPostInit() {
        super.onPostInit();
        element = new CommunicationBlock();
    }

    @Override
    protected CustomShapeBase createBackgroundElement() {
        shape = new Parallelogram();
        return shape;
    }

    @Override
    protected String getResourcePath() {
        return "/element/ioElement.fxml";
    }

    @Override
    public void setLogicElement(Element element) throws WrongTypeOfElement {
        if(element.getType()== ElementType.COMMUNICATION && element instanceof StandardElement) {
            super.setLogicElement(element);
        } else {
            throw new WrongTypeOfElement(element.getType(), ElementType.COMMUNICATION);
        }
    }

    @Override
    public UiElementType getType() {
        return UiElementType.IO;
    }

    @Override
    public Pane getMainContainer() {
        return elementContainer;
    }

    @Override
    public Node getBackgroundElement() {
        return shape;
    }

    @Override
    public VBox getDescContainer() {
        return descContainer;
    }

    @Override
    public Label getDescLabel() {
        return desc;
    }

    @Override
    protected String getDefaultDesc() {
        return "Input/Output";
    }

    @Override
    public List<PortInfo> getPortsInfo() {
        List<PortInfo> list = new ArrayList<>();

        PortInfo up = getBasicPortInfo();
        up.percentOfHeight = 0;
        up.percentOfWidth = 0.5;
        list.add(up);

        PortInfo right = getBasicPortInfo();
        right.percentOfHeight = 0.5;
        right.percentOfWidth = 0.9;
        list.add(right);

        PortInfo down = getBasicPortInfo();
        down.percentOfHeight = 1;
        down.percentOfWidth = 0.5;
        list.add(down);

        PortInfo left = getBasicPortInfo();
        left.percentOfHeight = 0.5;
        left.percentOfWidth = 0.1;
        list.add(left);

        return list;
    }

    @Override
    protected void showDialog() {
        IODialogInput dialogInput = new IODialogInput();
        dialogInput.desc = getDesc();

        IODialogInput.Value v1 = new IODialogInput.Value();
        v1.ioType = IOType.INPUT;
        v1.value = "a";
        dialogInput.data.add(v1);

        IODialogInput.Value v2 = new IODialogInput.Value();
        v2.ioType = IOType.OUTPUT;
        v2.value = "\"a: \"+a";
        dialogInput.data.add(v2);

        Dialog<IODialogInput> dialog =
                DialogFactory.buildIO(dialogInput);
        Optional<IODialogInput> optionalResult = dialog.showAndWait();

        if(optionalResult.isPresent()) {
            IODialogInput result = optionalResult.get();

            setDesc(result.desc);
            //TODO implement new type of logic element - IO block with UI
        }
    }
}
