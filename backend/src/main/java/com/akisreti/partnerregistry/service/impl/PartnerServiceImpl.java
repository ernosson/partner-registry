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

import com.akisreti.partnerregistry.domain.Partner;
import com.akisreti.partnerregistry.domain.PartnerType;
import com.akisreti.partnerregistry.dto.PartnerDto;
import com.akisreti.partnerregistry.repository.PartnerRepository;
import com.akisreti.partnerregistry.service.DocumentService;
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
    private DocumentService documentService;

    public PartnerServiceImpl( PartnerRepository partnerRepository, DocumentService documentService ) {
        this.partnerRepository = partnerRepository;
        this.documentService = documentService;
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

    @Override
    public byte[] download() throws IOException {
        List<List<String>> records = new LinkedList<>();

        for ( PartnerDto p : getPartnerList() ) {
            List<String> record = new LinkedList<>();
            record.add(p.getPartnerId().toString());
            record.add(p.getName());
            record.add(p.getType().name());
            records.add(record);
        }

        return documentService.downloadFile(Arrays.asList("partnerId", "name", "type"), records);
    }

    @Override
    public void upload( final MultipartFile file ) throws IOException {
        List<CSVRecord> records = documentService.uploadFile(file);
        List<Partner> partners = new LinkedList<>();
        for ( CSVRecord record : records ) {
            partners.add(Partner.builder().partnerId(StringUtils.hasLength(record.get(0)) ? Long.parseLong(record.get(0)) : null).name(record.get(1))
                .type(PartnerType.valueOf(record.get(2))).build());

        }

        savePartnerList(partners);
    }
}
