package com.akisreti.partnerregistry.api;

import java.io.IOException;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.akisreti.partnerregistry.dto.PartnerDto;
import com.akisreti.partnerregistry.service.DocumentService;
import com.akisreti.partnerregistry.service.PartnerService;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

/**
 * AddressController.
 *
 * @author kisretia
 */
@Slf4j
@RestController
@RequestMapping( "/partner" )
public class PartnerController {

    private static final String CSV_MIME_TYPE = "text/csv";

    private PartnerService partnerService;
    private DocumentService documentService;

    public PartnerController( PartnerService partnerService, DocumentService documentService ) {
        this.partnerService = partnerService;
        this.documentService = documentService;
    }

    @ApiOperation( "Get partner list" )
    @GetMapping( "/list" )
    public List<PartnerDto> getPartners() {
        return partnerService.getPartnerList();
    }

    @ApiOperation( "Get partner by id" )
    @GetMapping( "/detail/{partnerId}" )
    public PartnerDto getPartner(
        @PathVariable
        final Long partnerId ) {
        return partnerService.getPartner(partnerId);
    }

    @ApiOperation( "Save partner" )
    @PostMapping( "/save" )
    public void savePartner(
        @RequestBody
        final PartnerDto partnerDto ) {
        partnerService.savePartner(partnerDto);
    }

    @ApiOperation( "Delete partner" )
    @DeleteMapping( "/delete/{partnerId}" )
    public void deletePartner(
        @PathVariable
        final Long partnerId ) {
        partnerService.deletePartner(partnerId);
    }

    @ApiOperation( "Download all partner in CSV format" )
    @PostMapping( value = "/download", produces = CSV_MIME_TYPE )
    public ResponseEntity<byte[]> downloadFile() throws IOException {
        byte[] content = documentService.downloadFile();

        return ResponseEntity.ok().contentLength(content.length).header(HttpHeaders.CONTENT_TYPE, CSV_MIME_TYPE)
            .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + "File.csv").body(content);
    }

    @ApiOperation( "Upload partners in CSV format" )
    @PostMapping( "/upload" )
    @ResponseBody
    public ResponseEntity uploadFile(
        @RequestParam( "file" )
            MultipartFile file ) throws IOException {
        documentService.uploadFile(file);

        return ResponseEntity.ok().build();
    }


}
