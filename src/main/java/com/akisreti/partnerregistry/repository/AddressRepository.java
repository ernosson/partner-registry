package com.akisreti.partnerregistry.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.akisreti.partnerregistry.domain.Address;

/**
 * AddressRepository.
 *
 * @author kisretia
 */
public interface AddressRepository extends JpaRepository<Address, Long>, JpaSpecificationExecutor<Address> {
}
