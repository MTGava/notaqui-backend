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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@Slf4j
public class OCRServiceImpl implements OCRService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private CNPJService cnpjService;

    @Value("${notaqui.backing-services.google-cloud.url}")
    private String url;

    @Value("${notaqui.backing-services.google-cloud.api-key}")
    private String apiKey;

    @Override
    public OCRResponseDTO identify(OCRRequestDTO dto) throws JsonProcessingException {
        log.info("|| Iniciando ocrService - identificar conteúdo");
        List<RequestObjectDTO> objectDTOList = new ArrayList<>();
        objectDTOList.add(RequestObjectDTO.builder()
                .image(VisionImageRequestDTO.builder().content(dto.getContent()).build())
                .features(Collections.singletonList(VisionFeaturesRequestDTO.builder().type("TEXT_DETECTION").build()))
                .build());

        VisionRequestDTO request = VisionRequestDTO.builder().requests(objectDTOList).build();

        ResponseEntity<VisionResponseDTO> response;

        try {
            log.info("|| Chamando servico...");
            response = restTemplate.postForEntity(url.concat("?key=".concat(apiKey)), request, VisionResponseDTO.class);
            log.info("|| Servico OK HttpStatus: {}", response.getStatusCode().value());

            if (response.getBody() == null || response.getBody().getResponses().isEmpty()
                    || response.getBody().getResponses().get(0).getFullTextAnnotation() == null) {
                log.info("|| Nada encontrado...");
                OCRResponseDTO ocrResponseDTO = new OCRResponseDTO();
                CNPJResponseDTO cnpjResponseDTO = CNPJResponseDTO.builder()
                        .cnpj("Nao encontrado")
                        .corporateType("-")
                        .legalNature("-")
                        .corporateName("-")
                        .build();
                ocrResponseDTO.setCnpjResponseDTO(cnpjResponseDTO);
                ocrResponseDTO.setValue(new BigDecimal(0));
                return ocrResponseDTO;
            }
        } catch (Exception e) {
            log.error("|| Erro ao chamar o servico: {}", e.getMessage());
            return new OCRResponseDTO();
        }

        String content = response.getBody().getResponses().get(0).getFullTextAnnotation().getText();
        return consultBill(content);
    }

    private OCRResponseDTO consultBill(String content) {
        List<String> strings = List.of(content.split("\n"));
        OCRResponseDTO ocrResponseDTO = new OCRResponseDTO();
        CNPJResponseDTO cnpjResponseDTO = CNPJResponseDTO.builder()
                .cnpj("Nao encontrado")
                .corporateType("-")
                .legalNature("-")
                .corporateName("-")
                .build();
        ocrResponseDTO.setCnpjResponseDTO(cnpjResponseDTO);
        ocrResponseDTO.setValue(new BigDecimal(0));
        strings.forEach(s ->
        {
            if (s.toLowerCase().startsWith("cnpj")
                    && !s.toLowerCase().contains("cpf")
                    && !s.toLowerCase().contains("consumidor")) {
                ocrResponseDTO.setCnpjResponseDTO(cnpjService.consult(s.substring(5)));
            }
            if (s.toLowerCase().startsWith("xx") && s.toLowerCase().endsWith("xx")) {
                String value = s.toLowerCase().replace("x", "").replace(",", ".");
                try {
                    ocrResponseDTO.setValue(new BigDecimal(value));
                } catch (Exception e) {
                    log.info("|| Nao foi possivel settar o valor: ", e.getMessage());
                    ocrResponseDTO.setValue(new BigDecimal(0));
                }
            }
        });
        return ocrResponseDTO;
    }
}
