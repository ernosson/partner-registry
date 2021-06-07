package com.akisreti.partnerregistry.service;

import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import com.akisreti.partnerregistry.domain.Address;
import com.akisreti.partnerregistry.dto.AddressDto;

/**
 * AddressService.
 *
 * @author kisretia
 */
public interface AddressService {

    AddressDto getAddress( final Long addressId );

    List<AddressDto> getAddressList();

    List<AddressDto> getAddressListFiltered( final Specification<Address> spec );

    void saveAddress( final AddressDto addressDto );

    void deleteAddress( final Long addressId );
}
