package com.akisreti.partnerregistry.service.impl;

import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.csv.CSVRecord;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.akisreti.partnerregistry.domain.Address;
import com.akisreti.partnerregistry.domain.Partner;
import com.akisreti.partnerregistry.domain.PartnerType;
import com.akisreti.partnerregistry.dto.AddressDto;
import com.akisreti.partnerregistry.dto.PartnerDto;
import com.akisreti.partnerregistry.repository.AddressRepository;
import com.akisreti.partnerregistry.service.AddressService;
import com.akisreti.partnerregistry.service.DocumentService;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * AddressServiceImpl.
 *
 * @author kisretia
 */
@Service
public class AddressServiceImpl implements AddressService {

    private AddressRepository addressRepository;
    private DocumentService documentService;

    public AddressServiceImpl( AddressRepository addressRepository, DocumentService documentService ) {
        this.addressRepository = addressRepository;
        this.documentService = documentService;
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
    public void saveAddressList( final List<Address> addresses ) {
        addressRepository.saveAll(addresses);
    }

    @Override
    public void deleteAddress( final Long addressId ) {
        addressRepository.deleteById(addressId);
    }

    @Override
    public byte[] download() throws IOException {
        List<List<String>> records = new LinkedList<>();

        for ( AddressDto p : getAddressList() ) {
            List<String> record = new LinkedList<>();
            record.add(p.getAddressId().toString());
            record.add(p.getCountry());
            record.add(p.getCity());
            record.add(p.getZipCode());
            record.add(p.getStreetName());
            record.add(p.getHouseNumber());
            records.add(record);
        }

        return documentService.downloadFile(Arrays.asList("Address Id", "Country", "City", "Zip Code", "Street name", "House number"), records);
    }

    @Override
    public void upload( final MultipartFile file ) throws IOException {
        List<CSVRecord> records = documentService.uploadFile(file);
        List<Address> addresses = new LinkedList<>();
        for ( CSVRecord record : records ) {
            addresses.add(Address.builder().addressId(StringUtils.hasLength(record.get(0)) ? Long.parseLong(record.get(0)) : null)
                .country(record.get(1))
                .zipCode(record.get(2))
                .city(record.get(3))
                .streetName(record.get(4))
                .houseNumber(record.get(5))
                .build());
        }

        saveAddressList(addresses);
    }
}
