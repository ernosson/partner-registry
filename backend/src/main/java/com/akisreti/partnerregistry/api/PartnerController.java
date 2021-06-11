package com.akisreti.partnerregistry.api;

import java.io.IOException;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;
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

import com.akisreti.partnerregistry.domain.Partner;
import com.akisreti.partnerregistry.dto.PartnerDto;
import com.akisreti.partnerregistry.service.PartnerService;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import net.kaczmarzyk.spring.data.jpa.domain.LikeIgnoreCase;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Join;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Or;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;

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

    public PartnerController( PartnerService partnerService ) {
        this.partnerService = partnerService;
    }

    // @formatter:off
    @ApiOperation( "Get filtered partner list" )
    @GetMapping( "/filter" )
    public List<PartnerDto> getPartnersFiltered(
        @Join( path = "addresses", alias = "a" )
        @Or( {
            @Spec( path = "name", spec = LikeIgnoreCase.class ),
            @Spec( path = "a.country",     params = "country",     spec = LikeIgnoreCase.class ),
            @Spec( path = "a.city",        params = "city",        spec = LikeIgnoreCase.class ),
            @Spec( path = "a.zipCode",     params = "zipCode",     spec = LikeIgnoreCase.class ),
            @Spec( path = "a.streetName",  params = "streetName",  spec = LikeIgnoreCase.class ),
            @Spec( path = "a.houseNumber", params = "houseNumber", spec = LikeIgnoreCase.class )
        } ) Specification<Partner> spec ) {
        return partnerService.getPartnerListFiltered(spec);
    }
    // @formatter:on

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

    @ApiOperation( "Download all partners in CSV format" )
    @GetMapping( value = "/download", produces = CSV_MIME_TYPE )
    public ResponseEntity<byte[]> downloadFile() throws IOException {
        byte[] content = partnerService.download();

        return ResponseEntity.ok()
            .contentLength(content.length)
            .header(HttpHeaders.CONTENT_TYPE, CSV_MIME_TYPE)
            .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + "File.csv").body(content);
    }

    @ApiOperation( "Upload partners in CSV format" )
    @PostMapping( "/upload" )
    @ResponseBody
    public ResponseEntity uploadFile(
        @RequestParam( "file" )
            MultipartFile file ) throws IOException {
        partnerService.upload(file);

        return ResponseEntity.ok().build();
    }


}
