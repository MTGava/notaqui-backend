package com.fiap.bytesquad.notaqui.controller;

import com.fiap.bytesquad.notaqui.model.dto.BillDTO;
import com.fiap.bytesquad.notaqui.service.NotaquiService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@Slf4j
public class NotaquiController {

    @Autowired private NotaquiService service;

    @PostMapping("/save")
    public ResponseEntity<BillDTO> save(@RequestBody BillDTO billDTO) {
        log.info("|| Start Controller - save");
        return new ResponseEntity<>(service.saveBill(billDTO), HttpStatus.CREATED);
    }
}
