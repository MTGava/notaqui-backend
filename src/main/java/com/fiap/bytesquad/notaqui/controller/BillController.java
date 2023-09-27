package com.fiap.bytesquad.notaqui.controller;

import com.fiap.bytesquad.notaqui.model.dto.BillDTO;
import com.fiap.bytesquad.notaqui.service.BillService;
import com.fiap.bytesquad.notaqui.util.LogUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/compras")
@CrossOrigin("*")
@Slf4j
public class BillController {

    @Autowired private BillService service;

    @PostMapping("/cadastrar")
    public ResponseEntity<BillDTO> save(@RequestBody @Valid BillDTO billDTO) {
        log.info("|| Iniciando billController - cadastrar compra");
        log.info("|| RequestDTO: {}", LogUtil.logJson(billDTO));
        return new ResponseEntity<>(service.save(billDTO), HttpStatus.CREATED);
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<BillDTO>> findAll() {
        log.info("|| Iniciando controller - buscar todas as despesas");
        return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
    }
}
