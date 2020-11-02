package io.github.slupik.schemablock.execution;

/**
 * All rights reserved & copyright ©
 */
public interface ExecutionFlowController {
    /**
     * Callback is called when necessary (for instance when user want to do it)
     *
     * @param callback function to call
     */
    void run(Runnable callback);

    void onStart();

    void onStop();

    /**
     * Called after any error
     *
     * @param exception info about error
     */
    void onException(Throwable exception);
}
