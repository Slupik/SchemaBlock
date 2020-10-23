package de.tesis.dynaware.grapheditor.core.skins.defaults.condition.changer;

import javafx.geometry.Point2D;
import javafx.scene.Group;

/**
 * All rights reserved & copyright ©
 */
public interface ConditionChanger {

    Group getRoot();

    void setPosition(Point2D position);

    double getWidth();

    double getHeight();

}
