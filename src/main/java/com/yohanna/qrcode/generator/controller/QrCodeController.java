package com.yohanna.qrcode.generator.controller;

import com.yohanna.qrcode.generator.dto.QrCodeRequestDto;
import com.yohanna.qrcode.generator.dto.QrCodeResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/qrcode")
public class QrCodeController {
    @PostMapping
    public ResponseEntity<QrCodeResponseDto> generateQrCode(@RequestBody QrCodeRequestDto request) {
        return null;
    }
}
