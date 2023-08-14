package com.fiap.bytesquad.notaqui.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fiap.bytesquad.notaqui.model.dto.request.OCRRequestDTO;
import com.fiap.bytesquad.notaqui.model.dto.response.OCRResponseDTO;
import com.fiap.bytesquad.notaqui.service.OCRService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ocr")
@Slf4j
public class OCRController {

    @Autowired private OCRService service;

    @PostMapping("/identificar")
    public ResponseEntity<OCRResponseDTO> identify(@RequestBody OCRRequestDTO requestDTO) throws JsonProcessingException {
        log.info("|| Iniciando ocrController - identificar conteúdo");

        return new ResponseEntity<>(service.identify(requestDTO), HttpStatus.OK);
    }
}
