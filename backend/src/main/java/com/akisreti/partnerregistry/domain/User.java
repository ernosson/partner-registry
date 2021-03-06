package com.akisreti.partnerregistry.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table( name = "users" )
public class User {

    @Id
    @Column( name = "username" )
    private String userName;
    private String password;
    private Integer enabled;
}
