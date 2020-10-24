/*
 * Copyright (C) 2005 - 2014 by TESIS DYNAware GmbH
 */
package de.tesis.dynaware.grapheditor.core.skins.defaults;

import de.tesis.dynaware.grapheditor.core.skins.defaults.condition.changer.ConditionChanger;
import de.tesis.dynaware.grapheditor.core.skins.defaults.condition.changer.ConditionChangerPositionCalculator;
import de.tesis.dynaware.grapheditor.core.skins.defaults.condition.changer.ToggleConditionChanger;
import de.tesis.dynaware.grapheditor.core.skins.defaults.connection.SimpleConnectionSkin;
import de.tesis.dynaware.grapheditor.model.GConnection;
import javafx.geometry.Point2D;

import java.util.List;
import java.util.Map;

/**
 * The default connection skin.
 *
 * <p>
 * Extension of {@link SimpleConnectionSkin} that provides a mechanism for creating and removing joints.
 * </p>
 */
public abstract class ConditionalConnectionSkin extends DefaultConnectionSkin {

    private ConditionChanger changer = new ToggleConditionChanger();
    private ConditionChangerPositionCalculator positionCalculator = new ConditionChangerPositionCalculator();

    /**
     * Creates a new default connection skin instance.
     *
     * @param connection the {@link GConnection} the skin is being created for
     */
    public ConditionalConnectionSkin(GConnection connection) {
        super(connection);
        root.getChildren().add(changer.getRoot());
    }

    @Override
    public void draw(final List<Point2D> points, final Map<GConnection, List<Point2D>> allPoints) {
        super.draw(points, allPoints);
        Point2D position = positionCalculator.calculate(points, changer);
        changer.setPosition(position);
    }

    public void showTypeSwitch() {
        changer.show();
    }

    public void hideTypeSwitch() {
        changer.hide();
    }

}
