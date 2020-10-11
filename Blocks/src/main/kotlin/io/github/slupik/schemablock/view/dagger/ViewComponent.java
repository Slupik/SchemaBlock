package io.github.slupik.schemablock.view.dagger;

import dagger.Component;
import io.github.slupik.schemablock.view.MainViewController;

import javax.inject.Singleton;

/**
 * All rights reserved & copyright ©
 */
@Singleton
@Component(modules = {DynawareModule.class, ElementsBindingModule.class, ViewElementsModule.class})
public interface ViewComponent {

    void inject(MainViewController controller);

    @Component.Builder
    interface Builder {
        ViewComponent build();

        Builder addViewElementsModule(ViewElementsModule viewElementsModule);
    }

}
