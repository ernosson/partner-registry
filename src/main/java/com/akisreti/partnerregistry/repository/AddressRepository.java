package com.akisreti.partnerregistry.repository;

import org.springframework.data.repository.CrudRepository;

import com.akisreti.partnerregistry.domain.Address;

/**
 * AddressRepository.
 *
 * @author kisretia
 */
public interface AddressRepository extends CrudRepository<Address, Long> {
}
