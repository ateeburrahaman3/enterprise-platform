package com.ateeb.enterprise_platform.service;

import com.ateeb.enterprise_platform.dto.company.CompanyRequestDto;
import com.ateeb.enterprise_platform.dto.company.CompanyResponseDto;
import com.ateeb.enterprise_platform.entity.Company;
import com.ateeb.enterprise_platform.mapper.CompanyMapper;
import com.ateeb.enterprise_platform.repository.CompanyRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CompanyService {

    private final CompanyRepository companyRepository;
    private final CompanyMapper companyMapper;

    public CompanyService(CompanyRepository companyRepository, CompanyMapper companyMapper) {
        this.companyRepository = companyRepository;
        this.companyMapper = companyMapper;
    }

    @Transactional
    public CompanyResponseDto createCompany(CompanyRequestDto requestDto) {

        return companyMapper
                .toResponse(
                        companyRepository.save(companyMapper.toEntity(requestDto)));
    }

    @Transactional(readOnly = true)
    public CompanyResponseDto getCompanyById(Long id) {
        return companyMapper.toResponse(
                companyRepository.findByIdAndDeletedFalse(id)
                        .orElseThrow(()->
                                new RuntimeException("Company with id " + id + " not found")));

    }

    @Transactional(readOnly = true)
    public Page<CompanyResponseDto> getCompanies(Pageable pageable) {
        return companyRepository.findAllByDeletedFalse(pageable).map(companyMapper::toResponse);
    }

    @Transactional
    public CompanyResponseDto updateCompany(Long id, CompanyRequestDto requestDto) {
        Company company = companyRepository.findByIdAndDeletedFalse(id)
                .orElseThrow(() -> new RuntimeException("Company with id " + id + " not found"));

        company.setName(requestDto.name());
        return companyMapper.toResponse(company);
    }

    @Transactional
    public void deleteCompany(Long id) {
        Company company = companyRepository.findByIdAndDeletedFalse(id)
                .orElseThrow(() -> new RuntimeException("Company with id " + id + " not found"));

        company.setDeleted(true);
    }
}
