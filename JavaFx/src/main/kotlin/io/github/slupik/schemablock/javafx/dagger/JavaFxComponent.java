package io.github.slupik.schemablock.javafx.dagger;

import dagger.BindsInstance;
import dagger.Component;
import io.github.slupik.schemablock.javafx.view.MainViewController;

/**
 * All rights reserved & copyright ©
 */
@Component(modules = BlocksModule.class)
public interface JavaFxComponent {

    void inject(MainViewController main);

    @Component.Builder
    interface Builder {
        JavaFxComponent build();
        @BindsInstance Builder addElementsModule(GraphicElementsModule graphicElements);
    }

}
