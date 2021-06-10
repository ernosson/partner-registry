package com.akisreti.partnerregistry.service;

import java.io.IOException;
import java.util.List;

import org.apache.commons.csv.CSVRecord;
import org.springframework.web.multipart.MultipartFile;

/**
 * DownloadDocumentService.
 *
 * @author kisretia
 */
public interface DocumentService {

    byte[] downloadFile( List<String> header, List<List<String>> records ) throws IOException;

    List<CSVRecord> uploadFile( MultipartFile file ) throws IOException;

}
