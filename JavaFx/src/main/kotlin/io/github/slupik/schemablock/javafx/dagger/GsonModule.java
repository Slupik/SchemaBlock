package io.github.slupik.schemablock.javafx.dagger;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dagger.Module;
import dagger.Provides;

/**
 * All rights reserved & copyright ©
 */
@Module
public class GsonModule {

    @Provides
    Gson provideGson(){
        return new GsonBuilder().create();
    }

}
