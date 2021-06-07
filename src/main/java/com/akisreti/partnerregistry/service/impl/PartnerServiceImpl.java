package com.akisreti.partnerregistry.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.jpa.domain.Specification;
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

    private PartnerRepository partnerRepository;

    public PartnerServiceImpl( PartnerRepository partnerRepository ) {
        this.partnerRepository = partnerRepository;
    }

    @Override
    public PartnerDto getPartner( final Long partnerId ) {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.convertValue(partnerRepository.findById(partnerId).get(), PartnerDto.class);
    }

    @Override
    public List<PartnerDto> getPartnerList() {
        ObjectMapper objectMapper = new ObjectMapper();
        return partnerRepository.findAll().stream().map(p -> objectMapper.convertValue(p, PartnerDto.class)).collect(Collectors.toList());
    }

    @Override
    public List<PartnerDto> getPartnerListFiltered( final Specification<Partner> spec ) {
        ObjectMapper objectMapper = new ObjectMapper();
        return partnerRepository.findAll(spec).stream().map(p -> objectMapper.convertValue(p, PartnerDto.class)).collect(Collectors.toList());
    }

    @Override
    public void savePartner( final PartnerDto partnerDto ) {
        ObjectMapper objectMapper = new ObjectMapper();
        partnerRepository.save(objectMapper.convertValue(partnerDto, Partner.class));
    }

    @Override
    public void savePartnerList( final List<Partner> partners ) {
        partnerRepository.saveAll(partners);
    }

    @Override
    public void deletePartner( final Long partnerId ) {
        partnerRepository.deleteById(partnerId);
    }
}
