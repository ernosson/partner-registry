package com.akisreti.partnerregistry.service;

import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import com.akisreti.partnerregistry.domain.Partner;
import com.akisreti.partnerregistry.dto.PartnerDto;

/**
 * PartnerService.
 *
 * @author kisretia
 */
public interface PartnerService {

    PartnerDto getPartner( final Long partnerId );

    List<PartnerDto> getPartnerList();

    List<PartnerDto> getPartnerListFiltered( final Specification<Partner> spec );

    void savePartner( final PartnerDto partnerDto );

    void savePartnerList( List<Partner> partners );

    void deletePartner( final Long partnerId );
}
