package com.akisreti.partnerregistry.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.akisreti.partnerregistry.dto.PartnerDto;
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

    @Autowired
    PartnerService partnerService;

    @ApiOperation( "Get partner list" )
    @GetMapping( "/list" )
    public List<PartnerDto> getPartners() {
        return partnerService.getPartnerList();
    }

    @ApiOperation( "Get partner by id" )
    @GetMapping( "/detail" )
    public PartnerDto getPartner(
        @RequestParam
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
    @DeleteMapping( "/delete" )
    public void deletePartner(
        @RequestParam
        final Long partnerId ) {
        partnerService.deletePartner(partnerId);
    }

}
