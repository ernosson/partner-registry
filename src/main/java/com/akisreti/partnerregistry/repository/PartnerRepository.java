package com.akisreti.partnerregistry.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.akisreti.partnerregistry.domain.Partner;

/**
 * PartnerRepository.
 *
 * @author kisretia
 */
public interface PartnerRepository extends JpaRepository<Partner, Long>, JpaSpecificationExecutor<Partner> {
}
