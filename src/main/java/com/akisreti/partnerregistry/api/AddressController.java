package com.akisreti.partnerregistry.api;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.akisreti.partnerregistry.dto.AddressDto;
import com.akisreti.partnerregistry.service.AddressService;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

/**
 * AddressController.
 *
 * @author kisretia
 */
@Slf4j
@RestController
@RequestMapping( "/address" )
public class AddressController {

    private AddressService addressService;

    public AddressController( AddressService addressService ) {
        this.addressService = addressService;
    }

    @ApiOperation( "Get address list" )
    @GetMapping( "/list" )
    public List<AddressDto> getAddresses() {
        return addressService.getAddressList();
    }

    @ApiOperation( "Get address by id" )
    @GetMapping( "/detail/{addressId}" )
    public AddressDto getAddress(
        @PathVariable
        final Long addressId ) {
        return addressService.getAddress(addressId);
    }

    @ApiOperation( "Save address" )
    @PostMapping( "/save" )
    public void saveAddress(
        @RequestBody
        final AddressDto addressDto ) {
        addressService.saveAddress(addressDto);
    }

    @ApiOperation( "Delete address" )
    @DeleteMapping( "/delete/{addressId}" )
    public void deleteAddress(
        @PathVariable
        final Long addressId ) {
        addressService.deleteAddress(addressId);
    }

}
