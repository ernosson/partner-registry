package com.akisreti.partnerregistry.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Address.
 *
 * @author kisretia
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder( toBuilder = true )
@Entity
public class Address {

    @Id
    @GeneratedValue
    private Long addressId;
    private String country;
    private String city;

    @Column( length = 10 )
    private String zipCode;
    private String address;
    private String houseNumber;

    @ManyToOne
    @JoinColumn( name = "partnerId" )
    private Partner partner;

}
