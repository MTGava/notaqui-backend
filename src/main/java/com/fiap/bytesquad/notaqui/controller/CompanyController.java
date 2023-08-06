package com.fiap.bytesquad.notaqui.controller;

import com.fiap.bytesquad.notaqui.model.dto.CompanyDTO;
import com.fiap.bytesquad.notaqui.model.dto.UserDTO;
import com.fiap.bytesquad.notaqui.service.CompanyService;
import com.fiap.bytesquad.notaqui.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/empresas")
@Slf4j
public class CompanyController {

    @Autowired private CompanyService service;

    @PostMapping("/cadastrar")
    public ResponseEntity<CompanyDTO> save(@RequestBody @Valid CompanyDTO companyDTO) {
        log.info("|| Iniciando companyController - cadastrar empresa");
        return new ResponseEntity<>(service.save(companyDTO), HttpStatus.CREATED);
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<CompanyDTO>> findAll() {
        log.info("|| Iniciando controller - buscar todos os usuários");
        return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
    }

    @GetMapping("/buscar/{cnpj}")
    public ResponseEntity<CompanyDTO> findByLogin(@PathVariable String cnpj) {
        log.info("|| Iniciando controller - buscar por matrícula");
        return new ResponseEntity<>(new CompanyDTO(service.findByCnpj(cnpj.toLowerCase())), HttpStatus.OK);
    }
}
