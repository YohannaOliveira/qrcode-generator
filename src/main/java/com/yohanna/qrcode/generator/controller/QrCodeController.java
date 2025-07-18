package com.yohanna.qrcode.generator.controller;

import com.yohanna.qrcode.generator.dto.QrCodeRequestDto;
import com.yohanna.qrcode.generator.dto.QrCodeResponseDto;
import com.yohanna.qrcode.generator.service.QrCodeGeneratorService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/qrcode")
public class QrCodeController {
    private final QrCodeGeneratorService qrCodeGeneratorService;

    @PostMapping
    public ResponseEntity<QrCodeResponseDto> generateQrCode(@RequestBody QrCodeRequestDto request) {
        try {
            QrCodeResponseDto response = this.qrCodeGeneratorService.generateAndUploadQrCode(request.text());
            return ResponseEntity.ok(response);
        } catch (Exception exception) {
            return ResponseEntity.status(500).body(new QrCodeResponseDto("Error generating QR code: " + exception.getMessage()));
        }
    }
}
