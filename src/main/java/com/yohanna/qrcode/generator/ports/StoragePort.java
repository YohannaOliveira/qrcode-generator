package com.yohanna.qrcode.generator.ports;

public interface StoragePort {
    String uploadFile(byte[] fileData, String fileName, String contentType);
}
