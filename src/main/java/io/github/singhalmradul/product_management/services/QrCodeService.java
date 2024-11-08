package io.github.singhalmradul.product_management.services;

import java.nio.file.Path;
public interface QrCodeService {

    boolean generateQrCode(String data, int height, int width, Path path);
}
