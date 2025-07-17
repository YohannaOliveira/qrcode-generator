package com.yohanna.qrcode.generator.service;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.yohanna.qrcode.generator.dto.QrCodeResponseDto;
import com.yohanna.qrcode.generator.ports.StoragePort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.UUID;

@Service
@AllArgsConstructor
public class QrCodeGeneratorService {
    private final StoragePort storagePort;

    public QrCodeResponseDto generateAndUploadQrCode(String text) throws WriterException, IOException {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, 200, 200);

        ByteArrayOutputStream pngOutputStream = new ByteArrayOutputStream();
        MatrixToImageWriter.writeToStream(bitMatrix, "PNG", pngOutputStream);

        byte[] pngData = pngOutputStream.toByteArray();

        String url = storagePort.uploadFile(
                pngData,
                UUID.randomUUID().toString(),
                "image/png"
        );

        return new QrCodeResponseDto(url);
    }
}