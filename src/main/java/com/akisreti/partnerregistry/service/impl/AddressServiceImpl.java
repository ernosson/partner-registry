package com.akisreti.partnerregistry.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.akisreti.partnerregistry.domain.Address;
import com.akisreti.partnerregistry.dto.AddressDto;
import com.akisreti.partnerregistry.repository.AddressRepository;
import com.akisreti.partnerregistry.service.AddressService;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * AddressServiceImpl.
 *
 * @author kisretia
 */
@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    AddressRepository addressRepository;

    @Override
    public AddressDto getAddress( Long addressId ) {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.convertValue(addressRepository.findById(addressId).get(), AddressDto.class);
    }

    @Override
    public List<AddressDto> getAddressList() {
        ObjectMapper objectMapper = new ObjectMapper();
        return addressRepository.findAll().stream().map(a -> objectMapper.convertValue(a, AddressDto.class)).collect(Collectors.toList());
    }

    @Override
    public void saveAddress( AddressDto addressDto ) {
        ObjectMapper objectMapper = new ObjectMapper();
        addressRepository.save(objectMapper.convertValue(addressDto, Address.class));
    }

    @Override
    public void deleteAddress( Long addressId ) {
        addressRepository.deleteById(addressId);
    }
}
