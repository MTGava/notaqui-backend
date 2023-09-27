package com.fiap.bytesquad.notaqui.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fiap.bytesquad.notaqui.model.dto.QwertyDTO;
import com.fiap.bytesquad.notaqui.model.dto.UserDTO;
import com.fiap.bytesquad.notaqui.model.dto.request.OCRRequestDTO;
import com.fiap.bytesquad.notaqui.model.dto.response.OCRResponseDTO;
import com.fiap.bytesquad.notaqui.service.QwertyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/qwerty")
@CrossOrigin("*")
@Slf4j
public class QwertyController {

    @Autowired private QwertyService service;

    @PostMapping("/anexo")
    public ResponseEntity<QwertyDTO> create(@RequestBody QwertyDTO requestDTO) {
        return new ResponseEntity<>(service.save(requestDTO), HttpStatus.CREATED);
    }

    @GetMapping("/anexo/{id}")
    public ResponseEntity<QwertyDTO> findById(@PathVariable Integer id) {
        return new ResponseEntity<>(new QwertyDTO(service.findById(id)), HttpStatus.OK);
    }

    @GetMapping("/anexo")
    public ResponseEntity<List<Integer>> findAllIds() {
        return new ResponseEntity<>(service.findAllIds(), HttpStatus.OK);
    }
}
