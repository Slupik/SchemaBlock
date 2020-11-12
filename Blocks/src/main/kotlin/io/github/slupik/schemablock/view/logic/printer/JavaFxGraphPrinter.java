package io.github.slupik.schemablock.view.logic.printer;

import de.tesis.dynaware.grapheditor.GraphEditor;
import de.tesis.dynaware.grapheditor.GraphEditorContainer;
import de.tesis.dynaware.grapheditor.model.GModel;
import javafx.print.*;
import javafx.scene.layout.Region;
import javafx.scene.transform.Scale;

import javax.inject.Inject;

/**
 * All rights reserved & copyright ©
 */
public class JavaFxGraphPrinter implements GraphPrinter {

    @Inject
    public JavaFxGraphPrinter() {

    }

    @Override
    public void print(GraphEditorContainer graphEditorContainer, GraphEditor graphEditor) {
        PrinterJob job = PrinterJob.createPrinterJob();

        if (job != null && job.showPrintDialog(graphEditorContainer.getScene().getWindow())) {
            final Region view = getGraphView(graphEditor);

            Printer printer = job.getPrinter();
            PageLayout pageLayout = printer.createPageLayout(Paper.A4, getPageOrientation(view), Printer.MarginType.HARDWARE_MINIMUM);
            PrintResolution resolution = job.getJobSettings().getPrintResolution();

            Double windowX = graphEditorContainer.windowXProperty().getValue();
            Double windowY = graphEditorContainer.windowYProperty().getValue();
            graphEditorContainer.panTo(0, 0);

            double width = view.getWidth() / resolution.getFeedResolution();
            double height = view.getHeight() / resolution.getCrossFeedResolution();

            double scaleX = pageLayout.getPrintableWidth() / width / 600;
            double scaleY = pageLayout.getPrintableHeight() / height / 600;
            Scale scale = new Scale(scaleX, scaleY);
            view.getTransforms().add(scale);

            boolean success = job.printPage(pageLayout, view);
            if (success) {
                job.endJob();
            }
            view.getTransforms().remove(scale);

            graphEditorContainer.panTo(windowX, windowY);
        }
    }

    private PageOrientation getPageOrientation(Region view) {
        if (view.getWidth() >= view.getHeight()) {
            return PageOrientation.LANDSCAPE;
        } else {
            return PageOrientation.PORTRAIT;
        }
    }

    private Region getGraphView(GraphEditor graphEditor) {
        final Region view = graphEditor.getView();
        final GModel model = graphEditor.getModel();
        if (model != null) {
            view.resize(model.getContentWidth(), model.getContentHeight());
        }
        return view;
    }
}
