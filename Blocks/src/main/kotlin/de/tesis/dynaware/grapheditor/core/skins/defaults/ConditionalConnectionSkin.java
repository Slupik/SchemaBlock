/*
 * Copyright (C) 2005 - 2014 by TESIS DYNAware GmbH
 */
package de.tesis.dynaware.grapheditor.core.skins.defaults;

import de.tesis.dynaware.grapheditor.core.connections.ConnectionType;
import de.tesis.dynaware.grapheditor.core.skins.defaults.condition.changer.ConditionChanger;
import de.tesis.dynaware.grapheditor.core.skins.defaults.condition.changer.ConditionChangerPositionCalculator;
import de.tesis.dynaware.grapheditor.core.skins.defaults.condition.changer.ToggleConditionChanger;
import de.tesis.dynaware.grapheditor.core.skins.defaults.connection.SimpleConnectionSkin;
import de.tesis.dynaware.grapheditor.model.GConnection;
import javafx.beans.property.BooleanProperty;
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
public class ConditionalConnectionSkin extends DefaultConnectionSkin {

    private static final String STYLE_CLASS_CONNECTION_FALSE = "conditional-connection-false";
    private static final String STYLE_CLASS_CONNECTION_TRUE = "conditional-connection-true";

    private final ConditionChanger changer = new ToggleConditionChanger();
    private final ConditionChangerPositionCalculator positionCalculator = new ConditionChangerPositionCalculator();

    /**
     * Creates a new default connection skin instance.
     *
     * @param connection the {@link GConnection} the skin is being created for
     */
    public ConditionalConnectionSkin(GConnection connection) {
        super(connection);
        root.getChildren().add(changer.getRoot());
        changer.valueProperty().addListener((observableValue, oldValue, newValue) -> {
            if (newValue) {
                path.getStyleClass().setAll(STYLE_CLASS_CONNECTION_TRUE);
                getConnection().setType(ConnectionType.CONDITIONAL_TRUE.name());
            } else {
                path.getStyleClass().setAll(STYLE_CLASS_CONNECTION_FALSE);
                getConnection().setType(ConnectionType.CONDITIONAL_FALSE.name());
            }
        });
        hideTypeSwitch();
    }

    @Override
    protected String getStyleClass() {
        return STYLE_CLASS_CONNECTION_TRUE;
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

    public BooleanProperty valueProperty() {
        return changer.valueProperty();
    }

    public void setValue(boolean value) {
        changer.setValue(value);
    }
}
