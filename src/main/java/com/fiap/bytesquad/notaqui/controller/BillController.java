package com.fiap.bytesquad.notaqui.controller;

import com.fiap.bytesquad.notaqui.model.dto.BillDTO;
import com.fiap.bytesquad.notaqui.service.BillService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/compras")
@Slf4j
public class BillController {

    @Autowired private BillService service;

    @PostMapping("/cadastrar")
    public ResponseEntity<BillDTO> save(@RequestBody @Valid BillDTO billDTO) {
        log.info("|| Iniciando billController - cadastrar compra");
        return new ResponseEntity<>(service.save(billDTO), HttpStatus.CREATED);
    }

//    @PostMapping("/usuario/cadastrar")
//    public
}
