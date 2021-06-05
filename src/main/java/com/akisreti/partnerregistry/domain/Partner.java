package com.akisreti.partnerregistry.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Data;

/**
 * Partner.
 *
 * @author kisretia
 */
@Data
@Entity
public class Partner {

    @Id
    @GeneratedValue
    private Long partnerId;
    private String name;
    @Enumerated( EnumType.STRING )
    private PartnerType type;
    @OneToMany( mappedBy = "partner" )
    private List<Address> addresses;


}
