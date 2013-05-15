package com.adeoservices.backend.repository;

import com.adeoservices.backend.domain.BU;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.RepositoryDefinition;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@RepositoryDefinition(domainClass=BU.class, idClass=Long.class)
public interface BURepository extends  JpaRepository<BU, Long>{

}
