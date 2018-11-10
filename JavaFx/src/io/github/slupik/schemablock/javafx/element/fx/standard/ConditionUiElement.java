package io.github.slupik.schemablock.javafx.element.fx.standard;

import io.github.slupik.schemablock.javafx.element.UiElementType;
import io.github.slupik.schemablock.javafx.element.WrongTypeOfElement;
import io.github.slupik.schemablock.javafx.element.background.CustomShapeBase;
import io.github.slupik.schemablock.javafx.element.background.Rhombus;
import io.github.slupik.schemablock.model.ui.abstraction.ElementType;
import io.github.slupik.schemablock.model.ui.abstraction.element.Element;
import io.github.slupik.schemablock.model.ui.abstraction.element.StandardElement;
import io.github.slupik.schemablock.model.ui.implementation.element.specific.ConditionBlock;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

/**
 * All rights reserved & copyright ©
 */
public class ConditionUiElement extends UiStandardElement {

    @FXML
    private Pane elementContainer;

    @FXML
    private VBox descContainer;

    @FXML
    private Label desc;

    private Rhombus shape;

    @Override
    protected void onPostInit() {
        super.onPostInit();
        element = new ConditionBlock();
    }

    @Override
    protected CustomShapeBase createBackgroundElement() {
        shape = new Rhombus();
        return shape;
    }

    @Override
    protected String getResourcePath() {
        return "/element/conditionElement.fxml";
    }

    @Override
    public void setLogicElement(Element element) throws WrongTypeOfElement {
        if(element.getType()== ElementType.CONDITION && element instanceof StandardElement) {
            super.setLogicElement(element);
        } else {
            throw new WrongTypeOfElement(element.getType(), ElementType.CONDITION);
        }
    }

    @Override
    public UiElementType getType() {
        return UiElementType.IF;
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
}
