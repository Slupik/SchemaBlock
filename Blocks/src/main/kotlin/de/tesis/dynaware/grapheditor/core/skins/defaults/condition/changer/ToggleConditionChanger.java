package de.tesis.dynaware.grapheditor.core.skins.defaults.condition.changer;

import com.jfoenix.controls.JFXToggleButton;
import javafx.geometry.Point2D;
import javafx.scene.Group;

/**
 * All rights reserved & copyright ©
 */
public class ToggleConditionChanger implements ConditionChanger {

    private final Group root = new Group();
    private final JFXToggleButton btn = new JFXToggleButton();

    {
        btn.setText("True");
        root.getChildren().add(btn);
        root.setManaged(false);
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

}
