package io.github.slupik.schemablock.javafx.logic.execution;

import io.github.slupik.schemablock.model.ui.abstraction.container.ElementContainer;
import io.github.slupik.schemablock.model.ui.error.AlgorithmException;
import io.github.slupik.schemablock.model.ui.implementation.container.NextElementNotFound;
import io.github.slupik.schemablock.model.ui.implementation.element.specific.IOCommunicable;
import io.github.slupik.schemablock.both.execution.ExecutionFlowController;
import javafx.scene.control.Button;


/**
 * All rights reserved & copyright ©
 */
public class ExecutionController implements ExecutionFlowController {

    private final IOCommunicable communicator;
    private final ElementContainer container;
    private final Button btnPlay;
    private boolean debugMode;

    public ExecutionController(IOCommunicable communicator, ElementContainer container, Button btnPlay){
        this.communicator = communicator;
        this.container = container;
        this.btnPlay = btnPlay;
        setup();
    }

    private void setup() {
        btnPlay.setDisable(true);
    }

    public void execute(boolean debugMode) {
        this.debugMode = debugMode;
        new Thread(()->{
            communicator.clear();
            container.run();
        }).start();
    }

    @Override
    public void run(Runnable callback) {
        if(debugMode) {
            btnPlay.setDisable(false);
            btnPlay.setOnAction(event ->{
                btnPlay.setDisable(true);
                callback.run();
            });
        } else {
            callback.run();
        }
    }

    @Override
    public void onStart() {
        btnPlay.setDisable(true);
    }

    @Override
    public void onStop() {

    }

    @Override
    public void onException(Throwable exception) {
        exception.printStackTrace();
        if(exception instanceof AlgorithmException) {
            AlgorithmException ae = ((AlgorithmException) exception);
            switch (ae.getType()) {
                case NEXT_ELEMENT_NOT_FOUND: {
                    if(((NextElementNotFound) ae).getElementId().length()==0){
                        communicator.printAlgorithmError("Następny element nie został określony. Przerwano wykonywanie algorytmu.");
                        break;
                    }
                }
                default: {
                    communicator.printAlgorithmError(exception.getMessage());
                }
            }
        } else {
            communicator.printAlgorithmError(exception.getMessage());
        }
    }
}
