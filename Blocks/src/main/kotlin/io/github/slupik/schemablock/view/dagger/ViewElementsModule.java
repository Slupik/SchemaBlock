package io.github.slupik.schemablock.view.dagger;

import dagger.Module;
import dagger.Provides;
import de.tesis.dynaware.grapheditor.GraphEditorContainer;

/**
 * All rights reserved & copyright ©
 */
@Module
public class ViewElementsModule {

    private final GraphEditorContainer graphEditorContainer;

    public ViewElementsModule(GraphEditorContainer graphEditorContainer) {
        this.graphEditorContainer = graphEditorContainer;
    }

    @Provides
    public GraphEditorContainer graphEditorContainer() {
        return graphEditorContainer;
    }

    @Provides
    public WindowProvider window() {
        return () -> graphEditorContainer.getScene().getWindow();
    }

}
