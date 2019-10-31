package io.github.slupik.schemablock.javafx.element.fx.sheet.old;

/**
 * All rights reserved & copyright ©
 */
public class DefaultSheetWithElements {// implements SheetWithElements {

//    private final ElementContainer container;
//    private final Executor executor;
//    private final HeapController heap;
//    private final Pane sheet;
//    private final IOCommunicable communicable;
//    private final StartUiBlock startElement;
//    private final VisibleUIContainer uiContainer;
//    private final DeletionHandler deletionHandler;
//
//    private PortConnector connector;
//    private PortSpawner spawner;
//    private DestContainerAfterDrop childHandler;
//
//    public DefaultSheetWithElements(Pane sheet, IOCommunicable communicable, ElementContainer elementContainer, Executor executor, HeapController heap) {
//        this.sheet = sheet;
//        this.communicable = communicable;
//        container = elementContainer;
//        this.executor = executor;
//        this.heap = heap;
//        uiContainer = new VisibleUIContainerImpl();
//        startElement = ((StartUiBlock) createUiBlock(UiElementType.START));
//        init();
//        deletionHandler = new DeletionHandlerImpl(this, connector);
//        setup();
//    }
//
//    private void init() {
//        connector = new PortConnectorOnSheet(sheet);
//        spawner = new PortSpawnerOnSheet(uiContainer, connector);
//        childHandler = new DestContainerAfterDropImpl(sheet) {
//            @Override
//            public void addNode(Node node) {
//                if(node instanceof DescribedBlockPrototype) {
//                    try {
//                        addElement(((DescribedBlockPrototype) node));
//                    } catch (InvalidTypeException ignore) {/*Impossible*/}
//                } else {
//                    super.addNode(node);
//                }
//            }
//            @Override
//            public void removeNode(Node node) {
//                if(node instanceof DescribedBlockPrototype) {
//                    DescribedBlockPrototype element = ((DescribedBlockPrototype) node);
//                    removeElement(element);
//                } else {
//                    super.addNode(node);
//                }
//            }
//        };
//    }
//
//    private void setup() {
//        spawnStartElement();
//        spawnStop();
//    }
//
//    private void spawnStartElement() {
//        Platform.runLater(()->{
//            childHandler.addNode(startElement);
//            if(sheet.getWidth()<150){
//                startElement.setLayoutX(10);
//            } else if (sheet.getWidth()<600){
//                double elementWidth = startElement.boundsInLocalProperty().get().getWidth();
//                startElement.setLayoutX(sheet.getWidth()/2-elementWidth/2);
//            } else {
//                startElement.setLayoutX(300);
//            }
//            startElement.setLayoutY(10);
//            new ElementDragController(new DraggableElementContainer(startElement, false)).
//                    addListener((newState, draggableNode) -> {
//                        if(newState == DragEventState.DRAG_START) {
//                            startElement.toFront();
//                        }
//                    });
//        });
//    }
//
//    //TODO delete spawnStop()
//    private void spawnStop() {
//        Platform.runLater(()->{
//            Pane end = createUiBlock(UiElementType.STOP);
//            childHandler.addNode(end);
//            if(sheet.getWidth()<150){
//                end.setLayoutX(100);
//            } else {
//                end.setLayoutX(400);
//            }
//            end.setLayoutY(0);
//            new ElementDragController(new DraggableElementContainer(end, false)).
//                    addListener((newState, draggableNode) -> {
//                        if(newState == DragEventState.DRAG_START) {
//                            end.toFront();
//                        }
//                    });
//        });
//    }
//
//    @Override
//    public void addElement(DescribedBlockPrototype element) throws InvalidTypeException {
////        if(element instanceof Node) {
////            container.addElement(element.getLogicElement());
//            addElementWithoutPorts(element);
////            if(element instanceof UiElementBase) {
//                spawner.spawnForElement(element);
////            }
////        } else {
////            throw new InvalidTypeException();
////        }
//    }
//
//    private void addElementWithoutPorts(Block element) throws InvalidTypeException {
//        if(element instanceof Node) {
//            sheet.getChildren().add(((Node) element));
//            if(element instanceof DescribedBlockPrototype) {
//                uiContainer.add((DescribedBlockPrototype) element);
//            }
//
//            if(element.getType()==UiElementType.IO) {
//                //TODO setup
////                ((IOBlock) element).setCommunicator(communicable);
//            }
//            if(element instanceof UiElementBase) {
//                ((UiElementBase) element).setDeletionHandler(deletionHandler);
//            }
//        } else {
//            throw new InvalidTypeException();
//        }
//    }
//
//    @Override
//    public void removeElement(Node element) {
////        if(element instanceof Node) {
//            sheet.getChildren().remove(element);
////        }
//        if(element instanceof DescribedBlockPrototype) {
//            DescribedBlockPrototype block = (DescribedBlockPrototype) element;
//
//            uiContainer.remove(block.getElementId());
//            container.removeElement(block.getElementId());
//
//            deletionHandler.deleteIngoing(block.getElementId());
//            deletionHandler.deleteOutgoing(block.getElementId());
//            List<PortElement> ports = new ArrayList<>(connector.getPorts());
//            for(PortElement port:ports) {
//                if(port.getElement().getElementId().equals(block.getElementId())) {
//                    connector.deletePort(port);
//                    sheet.getChildren().remove(port);
//                }
//            }
//        }
//    }
//
//    @Override
//    public void clear() {
//        List<DescribedBlockPrototype> toDelete = new ArrayList<>();
//        for(Node child:sheet.getChildren()) {
//            toDelete.add(((DescribedBlockPrototype) child));
//        }
//        for(DescribedBlockPrototype element:toDelete) {
//            removeElement(element);
//        }
//        sheet.getChildren().clear();
//
//        getPortConnector().deleteAllPorts();
//        container.deleteAll();
//    }
//
//    @Override
//    public PortConnector getPortConnector() {
//        return connector;
//    }
//
//    @Override
//    public PortSpawner getPortSpawner() {
//        return spawner;
//    }
//
//    @Override
//    public DestContainerAfterDrop getChildrenHandler() {
//        return childHandler;
//    }
//
//    @Override
//    public String stringify() {
//        JsonParser parser = new JsonParser();
//
//        JsonArray blocks = new JsonArray();
//        for(Node node:sheet.getChildren()) {
//            if(node instanceof UiElementBase) {
//                blocks.add(parser.parse(((UiElementBase) node).stringify()).getAsJsonObject());
//            }
//        }
//
//        JsonObject sheetData = getSheetData();
//
//        JsonObject data = new JsonObject();
//        data.add("logicElements", parser.parse(container.stringify()));
//        data.add("sheet", sheetData);
//        data.add("blocks", blocks);
//        data.add("ports", parser.parse(connector.stringify()).getAsJsonArray());
//        return data.toString();
//    }
//
//    private JsonObject getSheetData() {
//        JsonObject data = new JsonObject();
//        data.addProperty("width", sheet.getWidth());
//        data.addProperty("height", sheet.getHeight());
//        return data;
//    }
//
//    @Override
//    public void restore(String data) {
//        clear();
//
//        JsonParser parser = new JsonParser();
//        JsonObject json = parser.parse(data).getAsJsonObject();
//
//        JsonObject sheetData = json.get("sheet").getAsJsonObject();
//        sheet.setPrefHeight(sheetData.getAsJsonPrimitive("height").getAsDouble());
//        sheet.setPrefWidth(sheetData.getAsJsonPrimitive("width").getAsDouble());
//
//        JsonObject logicElements = json.get("logicElements").getAsJsonObject();
//        container.restore(logicElements.toString());
//
//        JsonArray blocks = json.get("blocks").getAsJsonArray();
//        for(JsonElement element:blocks) {
//            UiElementPOJO blockInfo = new Gson().fromJson(element, UiElementPOJO.class);
//
//            DescribedBlockPrototype uiElement = createUiBlock(blockInfo.type);
////            try {
////                //TODO repair
////                uiElement.restore(element.toString(), container);
////                addElementWithoutPorts(uiElement);
////
////                //Make element draggable
////                ElementDragController draggingController = new ElementDragController(new DraggableElementContainer(uiElement, false));
////                draggingController.addListener((dragEvent, draggableNode) -> {
////                    if(dragEvent == DragEventState.DRAG_START) {
////                        draggingController.getEventNode().toFront();
////                    }
////                });
////
////            } catch (InvalidTypeException e) {
////                e.printStackTrace();
////            }
//        }
//
//        JsonArray ports = json.get("ports").getAsJsonArray();
//        spawner.restorePorts(ports.toString());
//
//        connector.restore(ports.toString());
//    }
}
