package de.tesis.dynaware.grapheditor.core.skins.defaults.condition.changer;

import com.jfoenix.controls.JFXToggleButton;
import javafx.beans.property.BooleanProperty;
import javafx.geometry.Point2D;
import javafx.scene.Group;

/**
 * All rights reserved & copyright ©
 */
public class ToggleConditionChanger implements ConditionChanger {

    private final Group root = new Group();
    private final JFXToggleButton btn = new JFXToggleButton();

    {
        root.getChildren().add(btn);
        root.setManaged(false);
        btn.setSelected(true);
        btn.setText("True");
        btn.selectedProperty().addListener((observableValue, oldValue, newValue) -> {
            if (newValue) {
                btn.setText("True");
            } else {
                btn.setText("False");
            }
        });
    }

    @Override
    public Group getRoot() {
        return root;
    }

    @Override
    public void setPosition(Point2D position) {
        root.setLayoutX(position.getX());
        root.setLayoutY(position.getY());
    }

    @Override
    public double getWidth() {
        return btn.getWidth();
    }

    @Override
    public double getHeight() {
        return btn.getHeight();
    }

    @Override
    public void show() {
        root.setVisible(true);
    }

    @Override
    public void hide() {
        root.setVisible(false);
    }

    @Override
    public BooleanProperty valueProperty() {
        return btn.selectedProperty();
    }

    @Override
    public void setValue(boolean value) {
        btn.setSelected(value);
    }

}
