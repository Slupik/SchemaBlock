package io.github.slupik.schemablock.javafx.view.resize;

import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;

/**
 * All rights reserved & copyright ©
 */
public class BlocksAwareSizeProvider implements MinimumSizeProvider {

    private final Pane sheet;

    public BlocksAwareSizeProvider(Pane sheet) {
        this.sheet = sheet;
    }

    @Override
    public double getWidth() {
        double minWidth = 0;
        for (Node child : sheet.getChildren()) {
            if(child instanceof Region) {
                Region region = (Region) child;
                double value = region.getLayoutX()+region.getWidth();
                if(value>minWidth) minWidth = value;
            }
        }
        return minWidth;
    }

    @Override
    public double getHeight() {
        double minHeight = 0;
        for (Node child : sheet.getChildren()) {
            if(child instanceof Region) {
                Region region = (Region) child;
                double value = region.getLayoutY()+region.getHeight();
                if(value>minHeight) minHeight = value;
            }
        }
        return minHeight;
    }
}
