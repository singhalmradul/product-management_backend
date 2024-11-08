package io.github.singhalmradul.product_management.services.implementations;

import static java.nio.file.Files.createTempFile;
import static java.nio.file.Files.deleteIfExists;
import static java.nio.file.Files.newOutputStream;
import static java.nio.file.Files.readAllBytes;
import static java.util.Collections.emptyList;
import static java.util.stream.Collectors.toSet;
import static org.springframework.util.ObjectUtils.isEmpty;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.github.singhalmradul.product_management.model.entities.Product;
import io.github.singhalmradul.product_management.repositories.ProductRepository;
import io.github.singhalmradul.product_management.services.CategoryService;
import io.github.singhalmradul.product_management.services.MediaService;
import io.github.singhalmradul.product_management.services.PdfService;
import io.github.singhalmradul.product_management.services.ProductService;
import jakarta.servlet.http.Part;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class ProductServiceImpl implements ProductService {

    private static final byte[] EMPTY_BYTE_ARRAY = new byte[0];

    private final ProductRepository repository;
    private final CategoryService categoryService;
    private final MediaService mediaService;
    private final PdfService pdfService;

    @Override
    @Transactional
    public Product saveProduct(final Product product) {
        if (product.getId() != null) {
            final var existingProduct = getProductById(product.getId());

            for (final var image : existingProduct.getImages()) {
                if (!product.getImages().contains(image)) {
                    mediaService.deleteFile(image);
                }
            }
        }

        var categories = product
                .getCategories()
                .stream()
                .map(category -> categoryService
                .getReferenceById(category.getId())
                .addProduct(product)
                )
                .collect(toSet());

        product.setCategories(categories);

        return repository.save(product);
    }

    @Override
    public List<Product> getAllProducts() {
        return repository.findAll();
    }

    @Override
    public Product getProductById(final UUID id) {
        return repository
                .findById(id)
                .orElseThrow(() -> new IllegalArgumentException(String.format(
                "Product with id %s not found",
                id
        )));
    }

    @Override
    @Transactional
    public List<String> addProductImages(final UUID productId, final List<Part> images) {
        if (isEmpty(images)) {
            return emptyList();
        }
        final var product = getProductById(productId);
        final var inputStreams = images
                .stream()
                .map(part -> {
                    try {
                        return part.getInputStream();
                    } catch (final IOException e) {
                        throw new RuntimeException(e.getMessage());
                    }
                }).toList();
        final var imageUrls = mediaService.saveFiles(inputStreams);
        product.getImages().addAll(imageUrls);
        repository.save(product);
        return imageUrls;
    }

    @Override
    public List<Product> searchProductsByName(final String query) {
        return repository.findByNameSimilar(query);
    }

    @Override
    @Transactional
    public void deleteProduct(final UUID id) {
        final var product = getProductById(id);
        mediaService.deleteFiles(product.getImages());
        repository.delete(product);
    }

    @Override
    public Product getProductByCode(String code) {
        return repository.findByCode(code).orElseThrow()    ;
    }

    @Override
    public byte[] getProductPdf(UUID productId) {

        var product = getProductById(productId);
        try {
            var path = createTempFile(
                product.getCode(),
                ".pdf"
            );


            try (var out = newOutputStream(path)) {

                if(pdfService.generateProductPdf(product, out))
                    return readAllBytes(path);
                else {
                    log.error("Failed to generate pdf");
                    return EMPTY_BYTE_ARRAY;
                }
            } catch (final IOException e) {
                log.error("Failed to save pdf: {}", e.getMessage());
                return EMPTY_BYTE_ARRAY;
            } finally {
                try {
                    deleteIfExists(path);
                    log.info("Deleted temporary file: {}", path);
                } catch (final IOException e) {
                    log.error("Failed to delete pdf: {}", e.getMessage());
                }
            }

        } catch (IOException ex) {
                return EMPTY_BYTE_ARRAY;
        }
    }
}
