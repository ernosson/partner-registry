package com.akisreti.partnerregistry.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;

/**
 * User.
 *
 * @author kisretia
 */
@Entity
@Data
public class User {

    @Id
    @GeneratedValue
    private String userId;
    private String userName;
    private String password;
}
