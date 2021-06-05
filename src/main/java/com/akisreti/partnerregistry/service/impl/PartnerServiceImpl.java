package com.akisreti.partnerregistry.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.akisreti.partnerregistry.domain.Partner;
import com.akisreti.partnerregistry.dto.PartnerDto;
import com.akisreti.partnerregistry.repository.PartnerRepository;
import com.akisreti.partnerregistry.service.PartnerService;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * PartnerServiceImpl.
 *
 * @author kisretia
 */
@Service
public class PartnerServiceImpl implements PartnerService {

    @Autowired
    PartnerRepository partnerRepository;

    @Override
    public PartnerDto getPartner( Long partnerId ) {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.convertValue(partnerRepository.findById(partnerId).get(), PartnerDto.class);
    }

    @Override
    public List<PartnerDto> getPartnerList() {
        ObjectMapper objectMapper = new ObjectMapper();
        return partnerRepository.findAll().stream().map(p -> objectMapper.convertValue(p, PartnerDto.class)).collect(Collectors.toList());
    }

    @Override
    public void savePartner( final PartnerDto partnerDto ) {
        ObjectMapper objectMapper = new ObjectMapper();
        partnerRepository.save(objectMapper.convertValue(partnerDto, Partner.class));
    }

    @Override
    public void deletePartner( Long partnerId ) {
        partnerRepository.deleteById(partnerId);
    }
}
