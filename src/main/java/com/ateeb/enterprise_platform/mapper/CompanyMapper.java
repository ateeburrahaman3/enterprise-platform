package com.ateeb.enterprise_platform.mapper;

import com.ateeb.enterprise_platform.dto.company.CompanyRequestDto;
import com.ateeb.enterprise_platform.dto.company.CompanyResponseDto;
import com.ateeb.enterprise_platform.entity.Company;
import org.springframework.stereotype.Component;

@Component
public class CompanyMapper {

    public CompanyResponseDto toResponse(Company company) {
        return new CompanyResponseDto(
                company.getId(),
                company.getName(),
                company.getCreatedAt(),
                company.getUpdatedAt()
        );
    }

    public Company toEntity(CompanyRequestDto companyDto) {
        Company company = new Company();
        company.setName(companyDto.name());
        return company;
    }
}
