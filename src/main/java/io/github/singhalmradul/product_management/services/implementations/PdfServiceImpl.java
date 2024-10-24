package io.github.singhalmradul.product_management.services.implementations;

import static com.itextpdf.layout.borders.Border.NO_BORDER;
import static com.itextpdf.layout.properties.VerticalAlignment.MIDDLE;
import static java.lang.String.format;
import static java.nio.file.Files.createTempFile;
import static java.nio.file.Files.newOutputStream;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.stereotype.Component;

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

import io.github.singhalmradul.product_management.model.entities.Category;
import io.github.singhalmradul.product_management.model.entities.OrderProduct;
import io.github.singhalmradul.product_management.model.entities.OrderRequest;
import io.github.singhalmradul.product_management.services.PdfService;
import lombok.extern.slf4j.Slf4j;

/**
 * PdfServiceImpl is a service implementation that generates PDF documents for orders.
 * It uses the iText library to create and manipulate PDF files.
 *
 * <p>This class is annotated with {@code @Slf4j} for logging and {@code @Component} to be
 * recognized as a Spring component.</p>
 *
 * <h2>Methods:</h2>
 * <ul>
 *   <li>{@link #generateOrderPdf(OrderRequest)}: Generates a PDF document for a given order.</li>
 *   <li>{@link #line()}: Creates a horizontal line element for the PDF document.</li>
 *   <li>{@link #heading()}: Creates a heading element for the PDF document.</li>
 *   <li>{@link #orderDetails(OrderRequest)}: Creates a table element containing order details.</li>
 *   <li>{@link #productDetails(List)}: Creates a table element containing product details.</li>
 *   <li>{@link #getScaler(Document)}: Calculates the scaler value for the document.</li>
 *   <li>{@link #getTableWidths(float...)}: Calculates the widths of table columns based on given ratios.</li>
 *   <li>{@link #addKeyValueBorderless(Table, String, Object)}: Adds a key-value pair to a table without borders.</li>
 *   <li>{@link #borderlessCell(BlockElement)}: Creates a table cell without borders containing a given element.</li>
 *   <li>{@link #borderlessCell(String)}: Creates a table cell without borders containing a given text.</li>
 *   <li>{@link #borderlessCell(Object)}: Creates a table cell without borders containing a given object.</li>
 * </ul>
 *
 * <h2>Usage:</h2>
 * <pre>
 * {@code
 * @Autowired
 * private PdfService pdfService;
 *
 * public void generatePdf(OrderRequest order) {
 *     File pdfFile = pdfService.generateOrderPdf(order);
 *     // Handle the generated PDF file
 * }
 * }
 * </pre>
 *
 * <h2>Dependencies:</h2>
 * <ul>
 *   <li>iText library for PDF generation</li>
 *   <li>Slf4j for logging</li>
 *   <li>Spring Framework for component management</li>
 * </ul>
 *
 * <h2>Exceptions:</h2>
 * <ul>
 *   <li>{@link IOException}: Thrown when there is an error creating or writing to the PDF file.</li>
 * </ul>
 *
 * @see PdfService
 */
@Slf4j
@Component
public class PdfServiceImpl implements PdfService {

    private float scaler;

    // MARK: - Pdf generation
    @Override
    public Path generateOrderPdf(final OrderRequest order) {

        Path path = null;

        try {
            path = createTempFile(
                format(
                    "%s_%s_%s",
                    order.getCustomer().getName(),
                    order.getDate().toString(),
                    order.getId()
                ),
                ".pdf"
            );

        } catch (final IOException e) {
            log.error("Failed to create file: {}", e.getMessage());
        }


        try (
            var out = newOutputStream(path);
            var pdf = new PdfDocument(new PdfWriter(out));
            var document = new Document(pdf)
        ) {
            scaler = getScaler(document);
            document
                .add(line())
                .add(heading())
                .add(line())
                .add(orderDetails(order))
                .add(line())
                .add(productDetails(order.getProducts()))
                .add(line())
            ;
        } catch (final IOException e) {
            log.error("Failed to generate pdf: {}", e.getMessage());
        }
        return path;
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

            final var product = orderProduct.getProduct();

            final var productDetails = new Table(getTableWidths(1, 2));
            addKeyValueBorderless(productDetails, "Code", product.getCode());
            addKeyValueBorderless(productDetails, "Name", product.getName());
            addKeyValueBorderless(productDetails, "Quantity", orderProduct.getQuantity());
            addKeyValueBorderless(productDetails, "Weight", product.getWeightString());

            if (!product.getCategories().isEmpty()) {
                final var categories = product
                    .getCategories()
                    .stream()
                    .map(Category::getName)
                    .toList()
                ;
                addKeyValueBorderless(productDetails, "Categories", String.join(", ", categories));
            }

            final var productEntry = new Table(getTableWidths(2, 1));
            productEntry.addCell(borderlessCell(productDetails));

            if (!product.getImages().isEmpty()) {

                ImageData imageData = null;
                try {
                    imageData = ImageDataFactory.create(
                        product
                            .getImages()
                            .stream()
                            .findFirst()
                            .orElseThrow()
                    );
                } catch (final MalformedURLException | NoSuchElementException e) {
                    log.error("Failed to load image: {}", e.getMessage());
                }
                final Image image = new Image(imageData);
                image.setAutoScale(true);
                image.setWidth(80);

                productEntry.addCell(
                    new Cell()
                        .add(image)
                        .setBorder(NO_BORDER)
                        .setVerticalAlignment(MIDDLE)
                );
            }

            productTable.addCell(borderlessCell(productEntry));
        });

        return productTable;
    }

    // MARK: - Helper methods
    private float getScaler(final Document document) {
        return document.getPdfDocument().getDefaultPageSize().getWidth();
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
        return borderlessCell(object.toString());
    }
}
