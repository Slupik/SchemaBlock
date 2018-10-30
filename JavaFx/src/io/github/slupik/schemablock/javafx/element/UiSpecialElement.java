package io.github.slupik.schemablock.javafx.element;

import io.github.slupik.schemablock.javafx.element.custom.MyEllipse;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;

/**
 * All rights reserved & copyright ©
 */
public abstract class UiSpecialElement extends UiElementBase implements ElementSizeBinder.Input {

    @FXML
    private Pane elementContainer;

    @FXML
    private Ellipse shape;

    private MyEllipse shape2;

    @FXML
    private VBox descContainer;

    @FXML
    private Label desc;

    private ElementSizeBinder size;

    @Override
    protected void onPreInit() {
        super.onPreInit();

        getMainContainer().getChildren().remove(shape);

        shape2 = new MyEllipse();
        shape2.setFill(Color.web("#00e860"));
        getMainContainer().getChildren().add(shape2);
        shape2.toBack();
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
        return shape2;
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
