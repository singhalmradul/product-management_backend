package io.github.singhalmradul.product_management.services.implementations;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.element.BlockElement;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.VerticalAlignment;

import io.github.singhalmradul.product_management.model.OrderProduct;
import io.github.singhalmradul.product_management.model.OrderRequest;
import io.github.singhalmradul.product_management.services.PdfService;

public class PdfServiceImpl implements PdfService {

    private float scaler;

    // MARK: - Pdf generation
    @Override
    public String generatePdf(final OrderRequest order, final List<OrderProduct> products) {
        try {
            Files.createDirectories(Paths.get("tmp"));
        } catch (final IOException e) {
            e.printStackTrace();
        }
        final var destination = String.format(
            "/home/singhalmradul/tmp/%s_%s_%s.pdf",
            order.getCustomer().getName(),
            order.getDate().toString(),
            order.getId()
        );
        try (var pdf = new PdfDocument(new PdfWriter(destination)); var document = new Document(pdf)) {
            scaler = getScaler(document);
            document
                .add(line())
                .add(heading())
                .add(line())
                .add(orderDetails(order))
                .add(line())
                .add(productDetails(products))
                .add(line());
        } catch (final IOException e) {
            e.printStackTrace();
        }
        return destination;
    }

    // MARK: - Document elements
    private BlockElement<?> line() {
        return new Table(getTableWidths(1))
            .addCell(new Cell()
                .setBorderBottom(Border.NO_BORDER)
                .setBorderLeft(Border.NO_BORDER)
                .setBorderRight(Border.NO_BORDER)
            )
        ;
    }

    private BlockElement<?> heading() {
        return new Paragraph("Order Summary").setFontSize(20).setBold();
    }

    private BlockElement<?> orderDetails(final OrderRequest order) {
        final var table = new Table(getTableWidths(1, 2));
        addKeyValueBorderless(table, "Order ID", order.getId());
        addKeyValueBorderless(table, "Customer", order.getCustomer().getName());
        addKeyValueBorderless(table, "Date", order.getDate());
        return table;
    }

    private BlockElement<?> productDetails(final List<OrderProduct> products) {
        final var productTable = new Table(getTableWidths(1, 1));
        products.forEach(orderProduct -> {

            final var productDetails = new Table(getTableWidths(1, 2));
            final var product = orderProduct.product();
            addKeyValueBorderless(productDetails, "Code", product.getCode());
            addKeyValueBorderless(productDetails, "Name", product.getName());
            addKeyValueBorderless(productDetails, "Quantity", orderProduct.quantity());
            addKeyValueBorderless(productDetails, "Weight", product.getWeightString());

            ImageData imageData = null;
            try {
                imageData = ImageDataFactory.create("/home/singhalmradul/Downloads/Pasted image.png");
            } catch (final MalformedURLException e) {
                e.printStackTrace();
            }
            final Image image = new Image(imageData);
            image.setAutoScale(true);
            image.setWidth(80);

            final var productEntry = new Table(getTableWidths(2, 1));
            productEntry.addCell(borderlessCell(productDetails));
            productEntry.addCell(
                new Cell()
                    .add(image)
                    .setBorder(Border.NO_BORDER)
                    .setVerticalAlignment(VerticalAlignment.MIDDLE)
            );

            productTable.addCell(borderlessCell(productEntry));
        });
        return productTable;
    }

    // MARK: - Helper methods
    private float getScaler(final Document document) {
        return document.getPageEffectiveArea(document.getPdfDocument().getDefaultPageSize()).getWidth();
    }

    private float[] getTableWidths(final float... ratios) {
        var sum = 0;
        for (final var ratio : ratios) {
            sum += ratio;
        }
        final float[] widths = new float[ratios.length];
        for (var i = 0; i < ratios.length; i++) {
            widths[i] = scaler * ratios[i] / sum;
        }
        return widths;
    }

    private void addKeyValueBorderless(final Table table, final String key, final Object value) {
        table.addCell(borderlessCell(key).setBold()).addCell(borderlessCell(value));
    }

    private Cell borderlessCell(final BlockElement<?> element) {
        return new Cell().add(element).setBorder(Border.NO_BORDER);
    }

    private Cell borderlessCell(final String text) {
        return borderlessCell(new Paragraph(text));
    }

    private Cell borderlessCell(final Object object) {
        return borderlessCell(new Paragraph(object.toString()));
    }
}
