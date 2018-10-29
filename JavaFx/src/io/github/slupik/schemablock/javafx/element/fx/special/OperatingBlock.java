package io.github.slupik.schemablock.javafx.element.fx.special;

import io.github.slupik.schemablock.javafx.element.UiElementType;
import io.github.slupik.schemablock.javafx.element.UiStandardElement;
import io.github.slupik.schemablock.javafx.element.WrongTypeOfElement;
import io.github.slupik.schemablock.model.ui.abstraction.ElementType;
import io.github.slupik.schemablock.model.ui.abstraction.element.Element;
import io.github.slupik.schemablock.model.ui.abstraction.element.OperationElement;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

/**
 * All rights reserved & copyright ©
 */
public class OperatingBlock extends UiStandardElement {

    @FXML
    private Pane elementContainer;

    @FXML
    private Rectangle shape;

    @FXML
    private VBox descContainer;

    @FXML
    private Label desc;

    @Override
    protected String getResourcePath() {
        return "/element/operatingElement.fxml";
    }

    @Override
    public void setLogicElement(Element element) throws WrongTypeOfElement {
        if(element.getType()== ElementType.CALCULATION && element instanceof OperationElement) {
            super.setLogicElement(element);
        } else {
            throw new WrongTypeOfElement(element.getType(), ElementType.CALCULATION);
        }
    }

    @Override
    protected UiElementType getType() {
        return UiElementType.CALCULATION;
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
        return "Blok operacyjny";
    }
}
