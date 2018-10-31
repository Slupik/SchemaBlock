package io.github.slupik.schemablock.javafx.view;

import io.github.slupik.schemablock.javafx.element.UiElementType;
import io.github.slupik.schemablock.javafx.element.fx.UiElementBase;
import io.github.slupik.schemablock.javafx.element.fx.special.StartElement;
import io.github.slupik.schemablock.javafx.element.fx.special.StopElement;
import io.github.slupik.schemablock.javafx.element.fx.standard.ConditionBlock;
import io.github.slupik.schemablock.javafx.element.fx.standard.IOBlock;
import io.github.slupik.schemablock.javafx.element.fx.standard.OperatingBlock;
import javafx.fxml.FXML;
import javafx.geometry.Point2D;
import javafx.scene.layout.AnchorPane;

/**
 * All rights reserved & copyright ©
 */
public class DragIcon extends AnchorPane {

    private UiElementType mType = null;

    @FXML
    private void initialize() {}

    public void relocateToPoint (Point2D p) {

        //relocates the object to a point that has been converted to
        //scene coordinates
        Point2D localCoords = getParent().sceneToLocal(p);

        relocate (
                (int) (localCoords.getX() - (getBoundsInLocal().getWidth() / 2)),
                (int) (localCoords.getY() - (getBoundsInLocal().getHeight() / 2))
        );
    }

    public UiElementType getType () { return mType; }

    public DragIcon setType (UiElementType type) {
        mType = type;
        UiElementBase element = new ConditionBlock();
        switch (type) {
            case CALCULATION:
                element = new OperatingBlock();
                break;
            case IF:
                element = new ConditionBlock();
                break;
            case START:
                element = new StartElement();
                break;
            case STOP:
                element = new StopElement();
                break;
            case IO:
                element = new IOBlock();
                break;
        }
        element.setElementSize(50,31);
        getChildren().clear();
        getChildren().add(element);
        return this;
    }
}
