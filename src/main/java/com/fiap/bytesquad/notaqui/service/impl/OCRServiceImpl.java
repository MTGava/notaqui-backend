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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    private Matcher matcher;

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

        List<BigDecimal> valuesBill = new ArrayList<>();



        String cnpjPattern = "\\d{2}\\.\\d{3}\\.\\d{3}/\\d{4}-\\d{2}";
        String cnpjPattern2 = "CNPJ:\\d{14}";
        String valueOnePattern = "\\d{1},\\d{2}";
        String valueTwoPattern = "\\d{2},\\d{2}";
        String valueThreePattern = "\\d{3},\\d{2}";
        String valueFourPattern = "\\d{4},\\d{2}";

        Pattern patternCnpj = Pattern.compile(cnpjPattern);
        Pattern patternCnpj2 = Pattern.compile(cnpjPattern2);
        Pattern patternValueOne = Pattern.compile(valueOnePattern);
        Pattern patternValueTwo = Pattern.compile(valueTwoPattern);
        Pattern patternValueThree = Pattern.compile(valueThreePattern);
        Pattern patternValueFour = Pattern.compile(valueFourPattern);

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
            Boolean hasCnpj = Boolean.FALSE;
            matcher = patternCnpj.matcher(s);
            if (matcher.find()) {
                String cnpj = matcher.group();
                hasCnpj = Boolean.TRUE;
                ocrResponseDTO.setCnpjResponseDTO(cnpjService.consult(cnpj));
            }

            if (!hasCnpj) {
                matcher = patternCnpj2.matcher(s);
                if (matcher.find()) {
                    String cnpj = matcher.group().substring(5);
                    hasCnpj = Boolean.TRUE;
                    ocrResponseDTO.setCnpjResponseDTO(cnpjService.consult(cnpj));
                }
            }

            matcher = patternValueOne.matcher(s);

            if (matcher.find()) {
                BigDecimal value = new BigDecimal(matcher.group().replace(",", "."));
                valuesBill.add(value);
            }

            matcher = patternValueTwo.matcher(s);

            if (matcher.find()) {
                BigDecimal value = new BigDecimal(matcher.group().replace(",", "."));
                valuesBill.add(value);
            }

            matcher = patternValueThree.matcher(s);

            if (matcher.find()) {
                BigDecimal value = new BigDecimal(matcher.group().replace(",", "."));
                valuesBill.add(value);
            }

            matcher = patternValueFour.matcher(s);

            if (matcher.find()) {
                BigDecimal value = new BigDecimal(matcher.group().replace(",", "."));
                valuesBill.add(value);
            }

//            if (s.toLowerCase().startsWith("xx") && s.toLowerCase().endsWith("xx")) {
//                String value = s.toLowerCase().replace("x", "").replace(",", ".");
//                try {
//                    ocrResponseDTO.setValue(new BigDecimal(value));
//                } catch (Exception e) {
//                    log.info("|| Nao foi possivel settar o valor: ", e.getMessage());
//                    ocrResponseDTO.setValue(new BigDecimal(0));
//                }
//            }
        });

        ocrResponseDTO.setValue(valuesBill.stream().reduce(BigDecimal::max).orElse(new BigDecimal(0)));
        return ocrResponseDTO;
    }
}
