package com.akisreti.partnerregistry.dto;

import lombok.Data;

/**
 * Address.
 *
 * @author kisretia
 */
@Data
public class AddressDto {


    private Long addressId;
    private String country;
    private String city;
    private String zipCode;
    private String address;
    private String houseNumber;
    private PartnerDto partner;

}
