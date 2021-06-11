package com.akisreti.partnerregistry.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

/**
 * Authority.
 *
 * @author kisretia
 */
@Entity
@Data
@Table( name = "authorities" )
public class Authority {

    @Id
    @Column( name = "username" )
    private String userName;
    private String authority;
}
