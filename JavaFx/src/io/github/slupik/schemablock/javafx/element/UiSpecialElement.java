package io.github.slupik.schemablock.javafx.element;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Shape;

/**
 * All rights reserved & copyright ©
 */
public abstract class UiSpecialElement extends UiElementBase implements ElementSizeBinder.Input {

    @FXML
    private Pane elementContainer;

    @FXML
    private Ellipse shape;

    @FXML
    private VBox descContainer;

    @FXML
    private Label desc;

    private ElementSizeBinder size;

    @Override
    protected String getResourcePath() {
        return "/element/specialElement.fxml";
    }

    @Override
    protected ElementSizeBinder.Input getBinderInput() {
        return this;
    }

    @Override
    protected String getContentDesc() {
        return getDesc().getText();
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
    public Label getDesc() {
        return desc;
    }

    @Override
    public void setDesc(String desc) {
        getDesc().setText(desc);
    }
}
