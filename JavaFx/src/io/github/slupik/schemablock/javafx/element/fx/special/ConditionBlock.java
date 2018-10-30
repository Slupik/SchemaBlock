package io.github.slupik.schemablock.javafx.element.fx.special;

import io.github.slupik.schemablock.javafx.element.UiElementType;
import io.github.slupik.schemablock.javafx.element.UiStandardElement;
import io.github.slupik.schemablock.javafx.element.WrongTypeOfElement;
import io.github.slupik.schemablock.javafx.element.custom.Rhombus;
import io.github.slupik.schemablock.model.ui.abstraction.ElementType;
import io.github.slupik.schemablock.model.ui.abstraction.element.Element;
import io.github.slupik.schemablock.model.ui.abstraction.element.StandardElement;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;

/**
 * All rights reserved & copyright ©
 */
public class ConditionBlock extends UiStandardElement {

    @FXML
    private Pane elementContainer;

    private Rhombus shape;

    @FXML
    private VBox descContainer;

    @FXML
    private Label desc;

    @Override
    protected void onPreInit() {
        super.onPreInit();
        shape = new Rhombus();
        shape.setFill(Color.web("#00e860"));
        getMainContainer().getChildren().add(shape);
        shape.toBack();
    }

    @Override
    protected String getResourcePath() {
        return "/element/conditionElement.fxml";
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
    protected UiElementType getType() {
        return UiElementType.IF;
    }

    @Override
    public Pane getMainContainer() {
        return elementContainer;
    }

    @Override
    public Shape getVisibleShape() {
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
