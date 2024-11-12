package io.github.singhalmradul.product_management.services.implementations;

import static com.google.zxing.BarcodeFormat.QR_CODE;
import static com.google.zxing.client.j2se.MatrixToImageWriter.writeToPath;

import java.io.IOException;
import java.nio.file.Path;

import org.springframework.stereotype.Component;

import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import io.github.singhalmradul.product_management.services.QrCodeService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class QrCodeServiceImpl implements QrCodeService{

    @Override
    public boolean generateQrCode(String data, int height, int width, Path path) {
        try {
            QRCodeWriter qrCodeWriter = new QRCodeWriter();
            BitMatrix bitMatrix = qrCodeWriter.encode(data, QR_CODE, width, height);

            writeToPath(bitMatrix, "PNG", path);
            return true;
        } catch (WriterException | IOException e) {
            log.error("Failed to generate QR code: {}", e.getMessage());
            return false;
        }
    }


}
