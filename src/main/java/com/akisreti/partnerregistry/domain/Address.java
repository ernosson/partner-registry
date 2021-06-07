package com.akisreti.partnerregistry.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.Data;

/**
 * Address.
 *
 * @author kisretia
 */
@JsonIdentityInfo(
    generator = ObjectIdGenerators.PropertyGenerator.class,
    property = "addressId")
@Data
@Entity
public class Address {

    @Id
    @GeneratedValue
    private Long addressId;
    private String country;
    private String city;

    @Column( length = 10 )
    private String zipCode;
    private String streetName;
    private String houseNumber;

    @JsonBackReference
    @ManyToOne
    @JoinColumn( name = "partnerId" )
    private Partner partner;

}
