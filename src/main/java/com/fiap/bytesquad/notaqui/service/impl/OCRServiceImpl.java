package com.fiap.bytesquad.notaqui.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fiap.bytesquad.notaqui.model.dto.request.OCRRequestDTO;
import com.fiap.bytesquad.notaqui.model.dto.request.vision.RequestObjectDTO;
import com.fiap.bytesquad.notaqui.model.dto.request.vision.VisionFeaturesRequestDTO;
import com.fiap.bytesquad.notaqui.model.dto.request.vision.VisionImageRequestDTO;
import com.fiap.bytesquad.notaqui.model.dto.request.vision.VisionRequestDTO;
import com.fiap.bytesquad.notaqui.model.dto.response.OCRResponseDTO;
import com.fiap.bytesquad.notaqui.model.dto.response.cnpja.CNPJResponseDTO;
import com.fiap.bytesquad.notaqui.model.dto.response.vision.VisionResponseDTO;
import com.fiap.bytesquad.notaqui.service.CNPJService;
import com.fiap.bytesquad.notaqui.service.OCRService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@Service
@Slf4j
public class OCRServiceImpl implements OCRService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired private CNPJService cnpjService;

    @Value("${notaqui.backing-services.google-cloud.url}")
    private String url;

    @Value("${notaqui.backing-services.google-cloud.api-key}")
    private String apiKey;

    @Override
    public CNPJResponseDTO identify(OCRRequestDTO dto) throws JsonProcessingException {
        log.info("|| Iniciando ocrService - identificar conteúdo");
        List<RequestObjectDTO> objectDTOList = new ArrayList<>();
        objectDTOList.add(RequestObjectDTO.builder()
                .image(VisionImageRequestDTO.builder().content(dto.getContent()).build())
                .features(Collections.singletonList(VisionFeaturesRequestDTO.builder().type("TEXT_DETECTION").build()))
                .build());

        VisionRequestDTO request = VisionRequestDTO.builder().requests(objectDTOList).build();

        ResponseEntity<VisionResponseDTO> response = restTemplate.postForEntity(url.concat("?key=".concat(apiKey)), request, VisionResponseDTO.class);

        String content = response.getBody() != null ? response.getBody().getResponses().get(0).getFullTextAnnotation().getText() : "No content";
        OCRResponseDTO responseDTO = OCRResponseDTO.builder().content(content).build();
        return consultCNPJ(responseDTO);
    }

    CNPJResponseDTO consultCNPJ(OCRResponseDTO responseDTO) {
        List<String> strings = List.of(responseDTO.getContent().split("\n"));
        AtomicReference<CNPJResponseDTO> cnpjResponseDTO = new AtomicReference<>(new CNPJResponseDTO());
        strings.forEach(s ->
                {
                    if(s.toLowerCase().contains("cnpj")) {
                        cnpjResponseDTO.set(cnpjService.consult(s.substring(5)));
                    }
                });
        return cnpjResponseDTO.get();
    }
}
