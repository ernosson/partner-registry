package com.akisreti.partnerregistry.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.akisreti.partnerregistry.domain.Address;
import com.akisreti.partnerregistry.dto.AddressDto;
import com.akisreti.partnerregistry.repository.AddressRepository;
import com.akisreti.partnerregistry.service.AddressService;
import com.fasterxml.jackson.databind.ObjectMapper;

import springfox.documentation.swagger2.mappers.ModelMapper;

/**
 * AddressServiceImpl.
 *
 * @author kisretia
 */
@Service
public class AddressServiceImpl implements AddressService {

    private AddressRepository addressRepository;

    public AddressServiceImpl( AddressRepository addressRepository ) {
        this.addressRepository = addressRepository;
    }

    @Override
    public AddressDto getAddress( final Long addressId ) {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.convertValue(addressRepository.findById(addressId).get(), AddressDto.class);
    }

    @Override
    public List<AddressDto> getAddressList() {
        ObjectMapper objectMapper = new ObjectMapper();
        return addressRepository.findAll().stream().map(a -> objectMapper.convertValue(a, AddressDto.class)).collect(Collectors.toList());
    }

    @Override
    public List<AddressDto> getAddressListFiltered( final Specification<Address> spec ) {
        ObjectMapper objectMapper = new ObjectMapper();
        return addressRepository.findAll(spec).stream().map(a -> objectMapper.convertValue(a, AddressDto.class)).collect(Collectors.toList());
    }

    @Override
    public void saveAddress( final AddressDto addressDto ) {
        ObjectMapper objectMapper = new ObjectMapper();
        addressRepository.save(objectMapper.convertValue(addressDto, Address.class));
    }

    @Override
    public void deleteAddress( final Long addressId ) {
        addressRepository.deleteById(addressId);
    }
}
