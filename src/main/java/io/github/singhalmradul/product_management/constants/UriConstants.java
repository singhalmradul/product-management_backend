package io.github.singhalmradul.product_management.constants;

public class UriConstants {

    private UriConstants() {}

    public static final String VERSION_1 = "/v1";
    public static final String SLASH = "/";
    public static final String PRODUCTS = "/products";
    public static final String CATEGORIES = "/categories";
    public static final String IMAGES = "/images";
    public static final String SEARCH = "/search";
    public static final String pathVariable(String variable) {
        return "/{" + variable + "}";
    }


}
