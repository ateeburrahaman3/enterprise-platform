package com.ateeb.enterprise_platform.repository;

import com.ateeb.enterprise_platform.entity.Company;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CompanyRepository extends JpaRepository<Company, Long> {

    Optional<Company> findByIdAndDeletedFalse(Long id);

    Page<Company> findAllByDeletedFalse(Pageable pageable);

}
