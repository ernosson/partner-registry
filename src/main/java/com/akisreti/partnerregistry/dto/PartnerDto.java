package com.akisreti.partnerregistry.dto;

import java.util.List;

import com.akisreti.partnerregistry.domain.PartnerType;

import lombok.Data;

/**
 * Partner.
 *
 * @author kisretia
 */
@Data
public class PartnerDto {


    private Long partnerId;
    private String name;
    private PartnerType type;
    private List<AddressDto> addresses;

}
