package com.akisreti.partnerregistry.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Partner.
 *
 * @author kisretia
 */
@JsonIdentityInfo( generator = ObjectIdGenerators.PropertyGenerator.class, property = "partnerId" )
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
    @JsonManagedReference
    @OneToMany( mappedBy = "partner" )
    private List<Address> addresses;


}
