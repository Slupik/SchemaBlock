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
import io.github.slupik.schemablock.model.ui.abstraction.element.IOData;
import io.github.slupik.schemablock.model.ui.abstraction.element.IOElement;
import io.github.slupik.schemablock.model.ui.abstraction.element.StandardElement;
import io.github.slupik.schemablock.model.ui.implementation.element.specific.IOBlock;
import io.github.slupik.schemablock.model.ui.implementation.element.specific.IOCommunicable;
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
        element = new IOBlock();
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
            if(getLogicElement()!=null) {
                ((IOElement) getLogicElement()).setCommunicator(((IOElement) getLogicElement()).getCommunicator());
            }
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
        //TODO move this code to mapper
        List<IOElement.Data> content = ((IOElement) getLogicElement()).getContentAsList();
        List<IODialogInput.Value> data = new ArrayList<>();
        for(IOElement.Data source:content) {
            IODialogInput.Value value = new IODialogInput.Value();
            if(source.isInput()) {
                value.ioType = IOType.INPUT;
            } else {
                value.ioType = IOType.OUTPUT;
            }
            value.value = source.getValue();
            data.add(value);
        }


        IODialogInput dialogInput = new IODialogInput();
        dialogInput.desc = getDesc();
        dialogInput.data.addAll(data);

        Dialog<IODialogInput> dialog =
                DialogFactory.buildIO(dialogInput);
        Optional<IODialogInput> optionalResult = dialog.showAndWait();

        if(optionalResult.isPresent()) {
            IODialogInput result = optionalResult.get();

            setDesc(result.desc);
            //TODO use generics
            if(getLogicElement() instanceof IOElement) {
                List<IOElement.Data> instructions = getConvertedData(result.data);
                IOElement element = ((IOElement) getLogicElement());
                element.setContent(instructions);
            }
        }
    }

    //TODO move this code to mapper
    private List<IOElement.Data> getConvertedData(List<IODialogInput.Value> data) {
        List<IOElement.Data> converted = new ArrayList<>();
        for(IODialogInput.Value value:data) {
            converted.add(new IOData(value.ioType==IOType.INPUT, value.value));
        }
        return converted;
    }

    public void setCommunicator(IOCommunicable communicator) {
        ((IOElement) getLogicElement()).setCommunicator(communicator);
    }
}
