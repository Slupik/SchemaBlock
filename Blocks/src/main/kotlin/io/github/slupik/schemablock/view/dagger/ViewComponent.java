package io.github.slupik.schemablock.view.dagger;

import dagger.Component;
import io.github.slupik.schemablock.view.MainViewController;
import io.github.slupik.schemablock.view.logic.execution.dagger.DiagramExecutorElementsModule;
import io.github.slupik.schemablock.view.logic.execution.dagger.ExecutionElementsModule;
import io.github.slupik.schemablock.view.logic.execution.dagger.ExecutorBinding;
import io.github.slupik.schemablock.view.logic.execution.dagger.HeapControllerCallbackModule;

import javax.inject.Singleton;

/**
 * All rights reserved & copyright ©
 */
@Singleton
@Component(modules = {DynawareModule.class, ElementsBindingModule.class, ViewElementsModule.class, ExecutorBinding.class,
        DiagramExecutorElementsModule.class})
//, BlockElementsModule.class
public interface ViewComponent {

    void inject(MainViewController controller);

    @Component.Builder
    interface Builder {
        ViewComponent build();

        Builder addViewElementsModule(ViewElementsModule viewElementsModule);

        Builder addViewElementsModule(DiagramExecutorElementsModule diagramExecutorElementsModule);

        Builder addViewElementsModule(ExecutionElementsModule executionElementsModule);

        Builder addViewElementsModule(HeapControllerCallbackModule heapControllerCallbackModule);
//        Builder addViewElementsModule(BlockElementsModule blockElementsModule);
    }

}
