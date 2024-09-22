package io.github.singhalmradul.product_management.services.implementations;

import static com.itextpdf.layout.borders.Border.NO_BORDER;

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
import com.itextpdf.layout.element.BlockElement;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.VerticalAlignment;

import io.github.singhalmradul.product_management.model.OrderProduct;
import io.github.singhalmradul.product_management.model.OrderRequest;
import io.github.singhalmradul.product_management.services.PdfService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PdfServiceImpl implements PdfService {

    private float scaler;

    // MARK: - Pdf generation
    @Override
    public String generateOrderPdf(final OrderRequest order, final List<OrderProduct> products) {
        try {
            Files.createDirectories(Paths.get("tmp"));
        } catch (final IOException e) {
            log.error("Failed to create directory: {}", e.getMessage());
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
            log.error("Failed to generate pdf: {}", e.getMessage());
        }
        return destination;
    }

    // MARK: - Document elements
    private BlockElement<?> line() {
        return new Table(getTableWidths(1))
            .addCell(new Cell()
                .setBorderBottom(NO_BORDER)
                .setBorderLeft(NO_BORDER)
                .setBorderRight(NO_BORDER)
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

            if (!product.getCategories().isEmpty()) {
                final var categories = product.getCategories().stream().map(category -> category.getName()).toList();
                addKeyValueBorderless(productDetails, "Categories", String.join(", ", categories));
            }

            final var productEntry = new Table(getTableWidths(2, 1));
            productEntry.addCell(borderlessCell(productDetails));

            if (!product.getImages().isEmpty()) {

                ImageData imageData = null;
                try {
                    imageData = ImageDataFactory.create(product.getImages().get(0));
                } catch (final MalformedURLException e) {
                    log.error("Failed to load image: {}", e.getMessage());
                }
                final Image image = new Image(imageData);
                image.setAutoScale(true);
                image.setWidth(80);

                productEntry.addCell(
                    new Cell()
                        .add(image)
                        .setBorder(NO_BORDER)
                        .setVerticalAlignment(VerticalAlignment.MIDDLE)
                );
            }

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
        return new Cell().add(element).setBorder(NO_BORDER);
    }

    private Cell borderlessCell(final String text) {
        return borderlessCell(new Paragraph(text));
    }

    private Cell borderlessCell(final Object object) {
        return borderlessCell(new Paragraph(object.toString()));
    }
}
