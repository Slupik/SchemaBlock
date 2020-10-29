package io.github.slupik.schemablock.execution;

/**
 * All rights reserved & copyright ©
 */
public class DefaultExecutionFlowController implements ExecutionFlowController {
    @Override
    public void run(Runnable callback) {
        callback.run();
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void onException(Throwable exception) {
        exception.printStackTrace();
    }
}
