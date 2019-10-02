package io.github.slupik.schemablock.model.ui.implementation.container;

import com.google.gson.Gson;
import io.github.slupik.schemablock.both.execution.DefaultExecutionFlowController;
import io.github.slupik.schemablock.both.execution.ExecutionFlowController;
import io.github.slupik.schemablock.model.ui.abstraction.ElementType;
import io.github.slupik.schemablock.model.ui.abstraction.container.ElementContainer;
import io.github.slupik.schemablock.model.ui.abstraction.controller.ElementCallback;
import io.github.slupik.schemablock.model.ui.abstraction.element.Element;
import io.github.slupik.schemablock.model.ui.parser.BlockParserException;
import io.github.slupik.schemablock.model.ui.parser.ElementParser;
import io.github.slupik.schemablock.newparser.memory.Register;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * All rights reserved & copyright ©
 */
public class DefaultElementContainer implements ElementContainer, ElementCallback {

    private final Register register;
    private final ElementParser elementParser;
    private final List<Element> elements = new ArrayList<>();
    private ExecutionFlowController controller = new DefaultExecutionFlowController();
    private String start;

    public DefaultElementContainer(Register register, ElementParser elementParser){
        this.register = register;
        this.elementParser = elementParser;
    }

    @Override
    public void run() {
        register.clear();

        controller.onStart();

        try {
            getElement(start).run();
        } catch (Throwable e) {
            controller.onException(e);
        }
    }

    @Override
    public void setExecutionFlowController(ExecutionFlowController controller) {
        this.controller = controller;
    }

    @Override
    public void addElement(Element element) {
        if(element!=null) {
            if(element.getType() == ElementType.START) {
                if(start!=null) {
                    removeElement(element.getId());
                }
                start = element.getId();
            }
            elements.add(element);
            element.registerCallback(this);
        }
    }

    @Override
    public Element getElement(String id) throws NextElementNotFound {
        for(Element element:elements) {
            if(element!=null) {
                if(element.getId()!=null) {
                    if(element.getId().equalsIgnoreCase(id)) {
                        return element;
                    }
                }
            }
        }
        throw new NextElementNotFound(id);
    }

    @Override
    public void removeElement(String id) {
        List<Element> toDelete = new ArrayList<>();
        for(Element element:elements) {
            if(element!=null && element.getId().equalsIgnoreCase(id)) {
                if(element.getType()==ElementType.START) {
                    start = null;
                }
                toDelete.add(element);
                element.unregisterCallback(this);
            }
        }
        for(Element element:toDelete) {
            elements.remove(element);
        }
    }

    @Override
    public void deleteAll() {
        elements.clear();
    }

    @Override
    public String stringify() {
        ElementContainerPOJO pojo = new ElementContainerPOJO();
        pojo.startElement = start;
        for(Element element:elements) {
            pojo.elements.add(element.stringify());
        }
        return new Gson().toJson(pojo);
    }

    @Override
    public void restore(String data) {
        ElementContainerPOJO pojo = new Gson().fromJson(data, ElementContainerPOJO.class);
        start = pojo.startElement;
        for(String elementString:pojo.elements) {
            try {
                Element element = elementParser.parse(elementString);
                addElement(element);
            } catch (BlockParserException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void onStop(Object result) {

    }

    @Override
    public void onTryRun(String elementId) {
        CountDownLatch latch = new CountDownLatch(1);
        controller.run(latch::countDown);
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            getElement(elementId).run();
        } catch (Throwable e) {
            controller.onException(e);
        }
    }
}
