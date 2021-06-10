package com.akisreti.partnerregistry.api;

import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.akisreti.partnerregistry.domain.Address;
import com.akisreti.partnerregistry.dto.AddressDto;
import com.akisreti.partnerregistry.service.AddressService;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import net.kaczmarzyk.spring.data.jpa.domain.Equal;
import net.kaczmarzyk.spring.data.jpa.domain.EqualIgnoreCase;
import net.kaczmarzyk.spring.data.jpa.domain.LikeIgnoreCase;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Or;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;

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

    // @formatter:off
    @ApiOperation( "Get filtered address list" )
    @GetMapping( "/filter" )
    public List<AddressDto> getAddressesFiltered(
        @Or( {
            @Spec( path = "country",     params = "country",     spec = LikeIgnoreCase.class ),
            @Spec( path = "city",        params = "city",        spec = LikeIgnoreCase.class ),
            @Spec( path = "zipCode",     params = "zipCode",     spec = LikeIgnoreCase.class ),
            @Spec( path = "streetName",  params = "streetName",  spec = LikeIgnoreCase.class ),
            @Spec( path = "houseNumber", params = "houseNumber", spec = LikeIgnoreCase.class )
        } )
        Specification<Address> spec ) {
        return addressService.getAddressListFiltered(spec);
    }
    // @formatter:on

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
