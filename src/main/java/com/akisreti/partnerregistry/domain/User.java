package com.akisreti.partnerregistry.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

/**
 * User.
 *
 * @author kisretia
 */
@Entity
@Data
@Table( name = "appuser" )
public class User {

    @Id
    @GeneratedValue
    @Column( name = "userid" )
    private Long userId;
    @Column( name = "username" )
    private String userName;
    private String password;
}
