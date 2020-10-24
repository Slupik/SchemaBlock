package de.tesis.dynaware.grapheditor.core.skins.defaults.condition.changer;

import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.shape.Rectangle;

/**
 * All rights reserved & copyright ©
 */
public class FakeConditionChanger implements ConditionChanger {

    private final Group root = new Group();
    private final Rectangle rectangle = new Rectangle(100, 30);

    {
        root.getChildren().add(rectangle);
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
        return rectangle.getWidth();
    }

    @Override
    public double getHeight() {
        return rectangle.getHeight();
    }

    @Override
    public void show() {
        root.setVisible(true);
    }

    @Override
    public void hide() {
        root.setVisible(false);
    }

}
