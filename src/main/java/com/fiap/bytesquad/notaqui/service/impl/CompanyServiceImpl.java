package com.fiap.bytesquad.notaqui.service.impl;

import com.fiap.bytesquad.notaqui.exceptions.ObjectNotFoundException;
import com.fiap.bytesquad.notaqui.model.Company;
import com.fiap.bytesquad.notaqui.model.dto.CompanyDTO;
import com.fiap.bytesquad.notaqui.repository.CompanyRepository;
import com.fiap.bytesquad.notaqui.service.CompanyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class CompanyServiceImpl implements CompanyService {

    @Autowired private CompanyRepository repository;

    @Override
    public CompanyDTO save(CompanyDTO dto) {
        log.info("|| Iniciando companyService - cadastrar empresa");
        Company company = newCompany(dto);
        company = repository.save(company);
        return new CompanyDTO(company);
    }

    @Override
    public List<CompanyDTO> findAll() {
        log.info("|| Iniciando companyService - buscar todos as empresas");
        List<Company> companies = repository.findAll();
        List<CompanyDTO> companiesDTO = new ArrayList<>();
        companies.forEach(company -> companiesDTO.add(new CompanyDTO(company)));
        return companiesDTO;
    }

    @Override
    public Company findByCnpj(String cnpj) {
        log.info("|| Iniciando companyService - buscar usuário por matrícula");
        Optional<Company> company = repository.findById(formatCNPJ(cnpj));
        return company.orElseThrow(() -> new ObjectNotFoundException("Empresa com o CNPJ: " + company + " não encontrada!"));
    }

    private Company newCompany(CompanyDTO dto) {
        Company company = new Company();
        company.setCnpj(formatCNPJ(dto.getCnpj()));
        company.setLegalNature(dto.getLegalNature());
        company.setCorporateName(dto.getCorporateName());
        company.setCorporateType(dto.getCorporateType());
        company.setCategory(dto.getCategory());
        company.setCorporateTypeId(dto.getCorporateTypeId());
        return company;
    }

    private String formatCNPJ(String cnpj) {
        return cnpj.replace(".", "").replace("/", "").replace("-", "").replace(" ", "");
    }
}
