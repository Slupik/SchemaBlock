package io.github.slupik.schemablock.javafx.element.fx.special;

import io.github.slupik.schemablock.javafx.element.ElementSizeBinder;
import io.github.slupik.schemablock.javafx.element.background.CustomShapeBase;
import io.github.slupik.schemablock.javafx.element.background.MyEllipse;
import io.github.slupik.schemablock.javafx.element.fx.UiElementBase;
import io.github.slupik.schemablock.javafx.element.fx.port.PortInfo;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;

/**
 * All rights reserved & copyright ©
 */
public abstract class UiSpecialElement extends UiElementBase implements ElementSizeBinder.Input {

    @FXML
    private Pane elementContainer;

    @FXML
    private VBox descContainer;

    @FXML
    private Label desc;

    private ElementSizeBinder size;

    private MyEllipse shape;

    @Override
    protected CustomShapeBase createBackgroundElement() {
        shape = new MyEllipse();
        return shape;
    }

    @Override
    protected String getResourcePath() {
        return "/element/specialElement.fxml";
    }

    @Override
    protected ElementSizeBinder.Input getBinderInput() {
        return this;
    }

    @Override
    protected String getDesc() {
        return getDescLabel().getText();
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
    public void setDesc(String desc) {
        getDescLabel().setText(desc);
    }

    @Override
    public List<PortInfo> getPortsInfo() {
        List<PortInfo> list = new ArrayList<>();

        PortInfo up = getBasicPortInfo();
        up.percentOfHeight = 0;
        up.percentOfWidth = 0.5;
        list.add(up);

        PortInfo left = getBasicPortInfo();
        left.percentOfHeight = 0.5;
        left.percentOfWidth = 1;
        list.add(left);

        PortInfo down = getBasicPortInfo();
        down.percentOfHeight = 1;
        down.percentOfWidth = 0.5;
        list.add(down);

        PortInfo right = getBasicPortInfo();
        right.percentOfHeight = 0.5;
        right.percentOfWidth = 0;
        list.add(right);

        return list;
    }

    protected abstract PortInfo getBasicPortInfo();
}
