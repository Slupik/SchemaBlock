package de.tesis.dynaware.grapheditor.core.skins.defaults.condition.changer;

import com.jfoenix.controls.JFXToggleButton;
import javafx.beans.property.BooleanProperty;
import javafx.css.PseudoClass;
import javafx.geometry.Point2D;
import javafx.scene.Group;

/**
 * All rights reserved & copyright ©
 */
public class ToggleConditionChanger implements ConditionChanger {

    private static final PseudoClass PSEUDO_CLASS_TRUE = PseudoClass.getPseudoClass("true");
    private static final PseudoClass PSEUDO_CLASS_FALSE = PseudoClass.getPseudoClass("false");

    private final Group root = new Group();
    private final JFXToggleButton btn = new JFXToggleButton();

    {
        root.getChildren().add(btn);
        root.setManaged(false);
        btn.setSelected(true);
        setVisualStateValue(true);
        btn.setText("True");
        btn.getStyleClass().setAll("connection-type-toggle");
        btn.selectedProperty().addListener((observableValue, oldValue, newValue) -> setVisualStateValue(newValue));
    }

    private void setVisualStateValue(Boolean newValue) {
        if (newValue) {
            btn.pseudoClassStateChanged(PSEUDO_CLASS_TRUE, true);
            btn.pseudoClassStateChanged(PSEUDO_CLASS_FALSE, false);
            btn.setText("True");
        } else {
            btn.pseudoClassStateChanged(PSEUDO_CLASS_TRUE, false);
            btn.pseudoClassStateChanged(PSEUDO_CLASS_FALSE, true);
            btn.setText("False");
        }
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
