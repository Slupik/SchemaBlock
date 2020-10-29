package io.github.slupik.schemablock.model.ui.abstraction.controller;

/**
 * All rights reserved & copyright ©
 */
public interface ElementCallback {
    void onStart();
    void onStop();
    void onStop(Object result);
    void onTryRun(String elementId);
}
