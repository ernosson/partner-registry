package com.akisreti.partnerregistry.service;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

/**
 * DownloadDocumentService.
 *
 * @author kisretia
 */
public interface DocumentService {

    byte[] downloadFile() throws IOException;

    void uploadFile( MultipartFile file ) throws IOException;
}
