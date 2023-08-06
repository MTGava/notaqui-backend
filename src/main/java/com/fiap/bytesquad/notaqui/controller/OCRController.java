package com.fiap.bytesquad.notaqui.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fiap.bytesquad.notaqui.model.dto.request.VisionRequestDTO;
import com.fiap.bytesquad.notaqui.model.dto.response.VisionResponseDTO;
import com.fiap.bytesquad.notaqui.service.OCRService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/ocr")
@Slf4j
public class OCRController {

    @Autowired private OCRService service;

    @Value("${notaqui.backing-services.google-cloud.url}")
    private String url;

    @Value("${notaqui.backing-services.google-cloud.api-key}")
    private String apiKey;

    @PostMapping("/identificar")
    public ResponseEntity<VisionResponseDTO> identify(@RequestBody VisionRequestDTO requestDTO) throws JsonProcessingException {
        log.info("|| Iniciando ocrController - identificar conteúdo");
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<VisionResponseDTO> response = restTemplate.postForEntity(url.concat("?key=".concat(apiKey)), requestDTO, VisionResponseDTO.class);

        return response;
    }
}
