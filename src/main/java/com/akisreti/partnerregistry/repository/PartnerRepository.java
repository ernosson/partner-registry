package com.akisreti.partnerregistry.repository;

import org.springframework.data.repository.CrudRepository;

import com.akisreti.partnerregistry.domain.Partner;

/**
 * PartnerRepository.
 *
 * @author kisretia
 */
public interface PartnerRepository extends CrudRepository<Partner, Long> {
}
