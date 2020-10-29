package io.github.slupik.schemablock.javafx.dagger;

import dagger.Component;
import io.github.slupik.schemablock.javafx.view.MainViewController;
import io.github.slupik.schemablock.logic.executor.dagger.ExecutorBinding;

import javax.inject.Singleton;

/**
 * All rights reserved & copyright ©
 */
@Component(modules = {BlocksBindingsModule.class, GraphicElementsModule.class, BlocksProvidingModule.class, ExecutorBinding.class, GsonModule.class})
@Singleton
public interface JavaFxComponent {

    void inject(MainViewController main);

    @Component.Builder
    interface Builder {
        JavaFxComponent build();

        Builder addElementsModule(GraphicElementsModule graphicElements);

        Builder addElementsModule(HeapControllerCallbackModule callback);

        Builder addElementsModule(ExecutionElementsModule executionElements);
    }

}
