package io.github.slupik.schemablock.view.dagger;

import dagger.Module;
import dagger.Provides;
import de.tesis.dynaware.grapheditor.GraphEditor;
import de.tesis.dynaware.grapheditor.GraphEditorContainer;
import de.tesis.dynaware.grapheditor.core.DefaultGraphEditor;
import de.tesis.dynaware.grapheditor.demo.GraphEditorPersistence;
import de.tesis.dynaware.grapheditor.demo.customskins.DefaultSkinController;
import de.tesis.dynaware.grapheditor.model.GModel;
import de.tesis.dynaware.grapheditor.model.GraphFactory;

import javax.inject.Singleton;

/**
 * All rights reserved & copyright ©
 */
@Module(includes = {ViewElementsModule.class})
public class DynawareModule {

    @Provides
    @Singleton
    DefaultSkinController defaultSkinController(GraphEditor graphEditor,
                                                GraphEditorContainer graphEditorContainer) {
        return new DefaultSkinController(graphEditor, graphEditorContainer);
    }

    @Provides
    @Singleton
    GraphEditor defaultGraphEditor(GModel model) {
        GraphEditor graphEditor = new DefaultGraphEditor();
        graphEditor.setModel(model);
        return graphEditor;
    }

    @Provides
    @Singleton
    GModel gModel() {
        return GraphFactory.eINSTANCE.createGModel();
    }

    @Provides
    @Singleton
    GraphEditorPersistence graphEditorPersistence() {
        return new GraphEditorPersistence();
    }

}
