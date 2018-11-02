package io.github.slupik.schemablock.javafx.element.fx;

import com.google.gson.Gson;
import com.sun.xml.internal.bind.v2.TODO;
import io.github.slupik.schemablock.javafx.element.ElementSizeBinder;
import io.github.slupik.schemablock.javafx.element.UiElement;
import io.github.slupik.schemablock.javafx.element.UiElementType;
import io.github.slupik.schemablock.javafx.element.WrongTypeOfElement;
import io.github.slupik.schemablock.javafx.element.background.CustomShapeBase;
import io.github.slupik.schemablock.javafx.element.fx.connection.BlockConnector;
import io.github.slupik.schemablock.model.ui.abstraction.container.ElementContainer;
import io.github.slupik.schemablock.model.ui.abstraction.element.ConditionalElement;
import io.github.slupik.schemablock.model.ui.abstraction.element.Element;
import io.github.slupik.schemablock.model.ui.abstraction.element.OperationElement;
import io.github.slupik.schemablock.model.ui.abstraction.element.StartElement;
import io.github.slupik.schemablock.model.ui.implementation.container.ElementInContainerNotFound;
import javafx.fxml.FXMLLoader;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import java.io.IOException;
import java.util.List;

/**
 * All rights reserved & copyright ©
 */
public abstract class UiElementBase extends Pane implements UiElement {

    private ElementSizeBinder size;
    private Element element;

    public UiElementBase(){
        this(null);
    }

    public UiElementBase(BlockConnector connector){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(getResourcePath()));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        initBackground();
        onPreInit();
        init();
        onPostInit();
        addEventFilter(MouseEvent.MOUSE_ENTERED, event -> {
//            List<Double> list = getIOPointsList();
//            for(int i=0;i<list.size();i+=2) {
//                TODO zdecydować czy ten element będzie się łączył z innym czy inny do tego
//                connector.spawnPointAt(list.get(i), list.get(i+1), this, (outputElement, isConnectionForTrue) -> {
//                    switch (outputElement.getType()) {
//                        case START: {
//                            This cannot be next element of anything
//                            return;
//                        }
//                        case IF: {
//                            if(isConnectionForTrue) {
//                                ((ConditionalElement) outputElement.getLogicElement()).setOnTrue(element.getId());
//                            }
//                        }
//                    }
//                    if(this instanceof ConditionalElement) {
//                        if(isConnectionForTrue) {
//                            ((ConditionalElement) this).setOnTrue(outputElement.getId());
//                        } else {
//                            ((ConditionalElement) this).setOnTrue(outputElement.getId());
//                        }
//                    } else if(this instanceof OperationElement) {
//                        ((OperationElement) this).setNextElement(outputElement.getId());
//                    } else if(this instanceof StartElement) {
//                        ((StartElement) this).setNextElement(outputElement.getId());
//                    } else if(this instanceof Element) {
//                        Element obj = (Element) this;
//                        switch (obj.getType()) {
//                            case START: {
//
//                            }
//                        }
//                    }
//                });
//            }
        });
    }

//    protected abstract List<Double> getIOPointsList();

    private void initBackground() {
        CustomShapeBase base = createBackgroundElement();
        base.setFill(Color.web("#00e860"));
        getBinderInput().getMainContainer().getChildren().add(base);
        base.toBack();
    }

    protected abstract CustomShapeBase createBackgroundElement();

    protected abstract String getResourcePath();

    protected void onPreInit() {}

    private void init() {
        size = new ElementSizeBinder(getBinderInput());
    }

    protected abstract ElementSizeBinder.Input getBinderInput();

    protected void onPostInit() {}

    @Override
    public void setElementSize(double width, double height){
        size.setWidth(width);
        size.setHeight(height);
    }

    @Override
    public void setElementWidth(double width){
        size.setWidth(width);
    }

    @Override
    public void setElementHeight(double height){
        size.setHeight(height);
    }

    @Override
    public void setLogicElement(Element element) throws WrongTypeOfElement {
        this.element = element;
    }

    @Override
    public Element getLogicElement() {
        return element;
    }

    @Override
    public String stringify() {
        UiElementPOJO pojo = new UiElementPOJO();
        pojo.layoutX = getLayoutX();
        pojo.layoutY = getLayoutY();
        pojo.desc = getDesc();
        pojo.elementId = getId();
        pojo.type = getType();
        return new Gson().toJson(pojo);
    }

    protected abstract String getDesc();

    @Override
    public abstract UiElementType getType();

    @Override
    public void restore(String data, ElementContainer container) {
        UiElementPOJO pojo = new Gson().fromJson(data, UiElementPOJO.class);
        setLayoutX(pojo.layoutX);
        setLayoutY(pojo.layoutY);
        setDesc(pojo.desc);
        try {
            setLogicElement(container.getElement(pojo.elementId));
        } catch (ElementInContainerNotFound | WrongTypeOfElement e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getElementId(){
        if(element!=null) {
            return element.getId();
        } else {
            return "";
        }
    }
}
