package io.github.slupik.schemablock.model.ui.implementation.element.specific;

import io.github.slupik.schemablock.model.ui.abstraction.ElementType;
import io.github.slupik.schemablock.model.ui.abstraction.element.OperationElement;
import io.github.slupik.schemablock.model.ui.error.AlgorithmException;
import io.github.slupik.schemablock.model.ui.implementation.element.StandardElementBase;
import io.github.slupik.schemablock.model.ui.parser.BlockParserException;
import io.github.slupik.schemablock.model.ui.parser.ElementPOJO;
import io.github.slupik.schemablock.newparser.executor.Executor;

/**
 * All rights reserved & copyright ©
 */
public class OperationBlock extends StandardElementBase implements OperationElement {

    private String nextElement = "";

    public OperationBlock(Executor executor) {
        super(executor);
    }

    @Override
    public void setNextElement(String elementId) {
        nextElement = elementId;
    }

    @Override
    public String getNextElement() {
        return nextElement;
    }

    @Override
    public void removeNextElement(String elementId) {
        if(nextElement.equals(elementId)) {
            nextElement = "";
        }
    }

    @Override
    public ElementType getType() {
        return ElementType.CALCULATION;
    }

    @Override
    public void run() throws AlgorithmException {
        onStart();
        justRunCode();
        tryRun(nextElement);
        onStop();
    }

    @Override
    protected ElementPOJO getPOJO() {
        ElementPOJO pojo = getPreCreatedPOJO();
        pojo.nextBlocks = new String[1];
        if(nextElement!=null) {
            pojo.nextBlocks[0] = nextElement;
        }
        return pojo;
    }

    @Override
    protected void load(ElementPOJO pojo) throws BlockParserException {
        super.load(pojo);
        nextElement = pojo.nextBlocks[0];
    }
}
