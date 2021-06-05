package com.akisreti.partnerregistry.service;

import java.util.List;

import com.akisreti.partnerregistry.dto.PartnerDto;

/**
 * PartnerService.
 *
 * @author kisretia
 */
public interface PartnerService {

    PartnerDto getPartner( final Long partnerId );

    List<PartnerDto> getPartnerList();

    void savePartner( final PartnerDto partnerDto );

    void deletePartner( final Long partnerId );
}
