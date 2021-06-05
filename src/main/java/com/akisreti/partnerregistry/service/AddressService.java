package com.akisreti.partnerregistry.service;

import java.util.List;

import com.akisreti.partnerregistry.dto.AddressDto;

/**
 * AddressService.
 *
 * @author kisretia
 */
public interface AddressService {

    AddressDto getAddress( final Long addressId );

    List<AddressDto> getAddressList();

    void saveAddress( final AddressDto addressDto );

    void deleteAddress( final Long addressId );

}
