package com.akisreti.partnerregistry.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Partner.
 *
 * @author kisretia
 */
@Data
@Builder( toBuilder = true )
@NoArgsConstructor
@AllArgsConstructor
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
