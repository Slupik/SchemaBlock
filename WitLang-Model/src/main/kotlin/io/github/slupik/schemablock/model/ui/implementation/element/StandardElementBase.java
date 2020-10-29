package io.github.slupik.schemablock.model.ui.implementation.element;

import com.google.gson.Gson;
import io.github.slupik.schemablock.model.ui.abstraction.element.ElementState;
import io.github.slupik.schemablock.model.ui.abstraction.element.ElementStateListener;
import io.github.slupik.schemablock.model.ui.abstraction.element.StandardElement;
import io.github.slupik.schemablock.model.ui.error.AlgorithmException;
import io.github.slupik.schemablock.model.ui.parser.BlockParserException;
import io.github.slupik.schemablock.model.ui.parser.ElementPOJO;
import io.github.slupik.schemablock.newparser.executor.Executor;

/**
 * All rights reserved & copyright ©
 */
public abstract class StandardElementBase extends ElementBase implements StandardElement {

    private String codeToRun = "";
    private Executor executor;
    private ElementStateListener stateListener;

    public StandardElementBase(Executor executor) {
        this.executor = executor;
    }

    @Override
    public void setContent(String content) {
        codeToRun = content;
    }

    @Override
    public String getContent() {
        return codeToRun;
    }

    protected void justRunCode() throws AlgorithmException {
        executor.execute(codeToRun);
    }

    protected Object runAndGetResult() throws AlgorithmException {
        return executor.getResult(codeToRun);
    }

    @Override
    public String stringify() {
        return new Gson().toJson(getPOJO());
    }

    protected abstract ElementPOJO getPOJO();

    protected ElementPOJO getPreCreatedPOJO() {
        ElementPOJO pojo = new ElementPOJO();
        pojo.elementType = getType();
        pojo.content = codeToRun;
        pojo.id = getId();
        return pojo;
    }

    @Override
    protected void load(ElementPOJO pojo) throws BlockParserException {
        codeToRun = pojo.content;
        id = pojo.id;
    }

    @Override
    public void setState(ElementState state) {
        if (stateListener != null) {
            stateListener.onStateChange(state);
        }
    }

    @Override
    public void setStateListener(ElementStateListener listener) {
        stateListener = listener;
    }

}
