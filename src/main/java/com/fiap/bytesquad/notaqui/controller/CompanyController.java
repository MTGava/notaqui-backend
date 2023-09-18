package com.fiap.bytesquad.notaqui.controller;

import com.fiap.bytesquad.notaqui.model.dto.CompanyDTO;
import com.fiap.bytesquad.notaqui.service.CNPJService;
import com.fiap.bytesquad.notaqui.service.CompanyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/empresas")
@CrossOrigin("*")
@Slf4j
public class CompanyController {

    @Autowired private CompanyService service;

    @Autowired private CNPJService cnpjService;

    @PostMapping("/cadastrar")
    public ResponseEntity<CompanyDTO> save(@RequestBody @Valid CompanyDTO companyDTO) {
        log.info("|| Iniciando companyController - cadastrar empresa");
        return new ResponseEntity<>(service.save(companyDTO), HttpStatus.CREATED);
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<CompanyDTO>> findAll() {
        log.info("|| Iniciando controller - buscar todas as PJs");
        return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
    }

    @GetMapping("/buscar/{cnpj}")
    public ResponseEntity<CompanyDTO> findByLogin(@PathVariable String cnpj) {
        log.info("|| Iniciando controller - buscar por cnpj");
        return new ResponseEntity<>(new CompanyDTO(service.findByCnpj(cnpj)), HttpStatus.OK);
    }

    @GetMapping("/dados/{cnpj}")
    public ResponseEntity<CompanyDTO> getCompanyData(@PathVariable String cnpj) {
        log.info("|| Iniciando controller - puxar dados PJ");
        return new ResponseEntity<>(new CompanyDTO(cnpjService.consult(cnpj)), HttpStatus.OK);
    }

}
