package io.github.slupik.schemablock.javafx.logic.drag.icon;

import javafx.fxml.FXML;
import javafx.geometry.Point2D;
import javafx.scene.layout.AnchorPane;

/**
 * All rights reserved & copyright ©
 */
public abstract class DragGhostIcon<TypeOfData> extends AnchorPane {

    private TypeOfData mData = null;

    @FXML
    private void initialize() {}

    void relocateToPoint(Point2D p) {

        //relocates the object to a point that has been converted to
        //scene coordinates
        Point2D localCoords = getParent().sceneToLocal(p);

        relocate (
                (int) (localCoords.getX() - (getBoundsInLocal().getWidth() / 2)),
                (int) (localCoords.getY() - (getBoundsInLocal().getHeight() / 2))
        );
    }

    TypeOfData getData() { return mData; }

    protected DragGhostIcon setData(TypeOfData data) {
        mData = data;
        onSetData(data);
        return this;
    }

    protected abstract void onSetData(TypeOfData data);
}
