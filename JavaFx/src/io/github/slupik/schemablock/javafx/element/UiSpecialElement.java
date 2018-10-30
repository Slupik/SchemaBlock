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
    protected void onPostInit() {
        super.onPostInit();
        setElementSize(100, 62);
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
    public Shape getBackgroundElement() {
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
}
