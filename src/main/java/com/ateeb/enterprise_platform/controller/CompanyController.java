package com.ateeb.enterprise_platform.controller;

import com.ateeb.enterprise_platform.dto.company.CompanyRequestDto;
import com.ateeb.enterprise_platform.dto.company.CompanyResponseDto;
import com.ateeb.enterprise_platform.entity.Company;
import com.ateeb.enterprise_platform.mapper.CompanyMapper;
import com.ateeb.enterprise_platform.service.CompanyService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/companies")
public class CompanyController {

    private final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @PostMapping
    public ResponseEntity<CompanyResponseDto> createCompany(
            @Valid
            @RequestBody CompanyRequestDto requestDto) {

        CompanyResponseDto companyResponse = companyService.createCompany(requestDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(companyResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CompanyResponseDto> getCompanyById(
            @PathVariable(name = "id") Long id){

        CompanyResponseDto companyResponse = companyService.getCompanyById(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(companyResponse);
    }

    @GetMapping
    public ResponseEntity<Page<CompanyResponseDto>> getAllCompanies(Pageable pageable){

        Page<CompanyResponseDto> companyResponse = companyService.getCompanies(pageable);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(companyResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CompanyResponseDto> updateCompany(
            @PathVariable Long id,
            @RequestBody CompanyRequestDto requestDto){
        CompanyResponseDto companyResponse = companyService.updateCompany(id, requestDto);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(companyResponse);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public  void deleteCompany(
            @PathVariable Long id){
        companyService.deleteCompany(id);
    }
}
