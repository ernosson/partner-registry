package com.akisreti.partnerregistry.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.akisreti.partnerregistry.domain.Partner;

/**
 * PartnerRepository.
 *
 * @author kisretia
 */
public interface PartnerRepository extends JpaRepository<Partner, Long> {
}
