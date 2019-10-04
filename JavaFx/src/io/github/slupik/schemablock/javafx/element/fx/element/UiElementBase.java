package io.github.slupik.schemablock.javafx.element.fx.element;

import com.google.gson.Gson;
import io.github.slupik.schemablock.javafx.element.ElementSizeBinder;
import io.github.slupik.schemablock.javafx.element.UiElement;
import io.github.slupik.schemablock.javafx.element.UiElementType;
import io.github.slupik.schemablock.javafx.element.WrongTypeOfElement;
import io.github.slupik.schemablock.javafx.element.background.CustomShapeBase;
import io.github.slupik.schemablock.model.ui.abstraction.container.ElementContainer;
import io.github.slupik.schemablock.model.ui.abstraction.element.Element;
import io.github.slupik.schemablock.model.ui.abstraction.element.StandardElement;
import io.github.slupik.schemablock.model.ui.implementation.container.NextElementNotFound;
import io.github.slupik.schemablock.model.ui.newparser.HeapController;
import io.github.slupik.schemablock.newparser.executor.Executor;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

import java.io.IOException;

/**
 * All rights reserved & copyright ©
 */
public abstract class UiElementBase extends Pane implements UiElement {

    protected final Executor executor;
    protected final HeapController heap;

    private DeletionHandler deletionHandler;
    private ElementSizeBinder size;
    private CustomShapeBase background;
    protected Element element;

    public UiElementBase(Executor executor, HeapController heap){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(getResourcePath()));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        initBackground();
        initDialog();

        this.executor = executor;
        this.heap = heap;

        onPreInit();
        init();
        element = generateLogicElement();
        setupLogicElement();
        onPostInit();
    }

    private void setupLogicElement() {
        if(element instanceof StandardElement)
        ((StandardElement) element).setStateListener(state -> {
                    switch (state) {
                        case STOP:
                            markAsStop();
                            break;
                        case ERROR:
                            markAsError();
                            break;
                        case RUNNING:
                            markAsExecuting();
                            break;
                    }
                }
        );
    }

    protected abstract Element generateLogicElement();

    private void initDialog() {
        addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            if(background.contains(event.getX(), event.getY())) {
                if(event.getButton().equals(MouseButton.PRIMARY)){
                    if(event.getClickCount() == 2){
                        showDialog();
                    }
                }
            }
        });
    }

    protected abstract void showDialog();

    private void initBackground() {
        background = createBackgroundElement();
        background.resetColor();
        getBinderInput().getMainContainer().getChildren().add(background);
        background.toBack();
    }

    protected abstract CustomShapeBase createBackgroundElement();

    protected abstract String getResourcePath();

    protected void onPreInit() {}

    private void init() {
        size = new ElementSizeBinder(getBinderInput());

        initContextMenu();
    }

    protected void initContextMenu() {
        ContextMenu contextMenu = new ContextMenu();

        if(canBeDeleted()) {
            MenuItem item1 = new MenuItem("Usuń");
            item1.setOnAction(event -> {
                if(deletionHandler!=null) {
                    deletionHandler.deleteElement(this);
                }
            });
            contextMenu.getItems().add(item1);
        }
        MenuItem item2 = new MenuItem("Usuń wychodzące");
        item2.setOnAction(event -> {
            if(deletionHandler!=null){
                deletionHandler.deleteOutgoing(getElementId());
            }
        });
        MenuItem item3 = new MenuItem("Usuń przychodzące");
        item3.setOnAction(event -> {
            if(deletionHandler!=null) {
                deletionHandler.deleteIngoing(getElementId());
            }
        });

        contextMenu.getItems().addAll(item2, item3);

        setOnContextMenuRequested(event -> {
            background.highlight();
            contextMenu.show(this, event.getScreenX(), event.getScreenY());
        });
        contextMenu.setOnHiding(event -> background.resetColor());
    }

    @Override
    public void markAsError() {
        background.markAsError();
    }

    @Override
    public void markAsExecuting() {
        background.markAsExecuting();
    }

    @Override
    public void markAsStop() {
        background.resetColor();
    }

    protected abstract boolean canBeDeleted();

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
        pojo.elementId = element.getId();
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
        } catch (NextElementNotFound | WrongTypeOfElement e) {
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

    public void setDeletionHandler(DeletionHandler deletionHandler) {
        this.deletionHandler = deletionHandler;
    }
}
