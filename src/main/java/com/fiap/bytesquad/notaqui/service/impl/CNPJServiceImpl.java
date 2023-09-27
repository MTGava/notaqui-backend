package com.fiap.bytesquad.notaqui.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fiap.bytesquad.notaqui.model.dto.response.cnpja.CNPJResponseDTO;
import com.fiap.bytesquad.notaqui.model.dto.response.cnpja.CNPJaResponseDTO;
import com.fiap.bytesquad.notaqui.service.CNPJService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
public class CNPJServiceImpl implements CNPJService {

    @Autowired private RestTemplate restTemplate;

    @Value("${notaqui.backing-services.cnpj-ja.url}")
    private String url;

    @Value("${notaqui.backing-services.cnpj-ja.token}")
    private String token;

    @Override
    public CNPJResponseDTO consult(String cnpj) {
        log.info("|| Iniciando cnpjService - consultar CNPJ");
        log.info("|| CNPJ: {}", cnpj);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", token);
        HttpEntity<String> entity = new HttpEntity<>("parameters", headers);

        String urlFinal = url.concat(formatCNPJ(cnpj));
        log.info("|| Request URI: {}", urlFinal);
        ResponseEntity<CNPJaResponseDTO> response = new ResponseEntity<>(HttpStatus.OK);
        try {
            log.info("|| Chamando servico...");
            response = restTemplate.exchange(urlFinal, HttpMethod.GET, entity, CNPJaResponseDTO.class);
            log.info("|| Servico OK HttpStatus: {}", response.getStatusCode().value());
        } catch (Exception e) {
            log.error("|| Erro ao chamar o servico: {}", e.getMessage());
        }

        CNPJaResponseDTO responseDTO = response.getBody();

        return CNPJResponseDTO.builder()
                .cnpj(formatCNPJ(cnpj))
                .corporateName(responseDTO != null ? responseDTO.getCompany().getName() : "")
                .legalNature(responseDTO != null ? responseDTO.getCompany().getNature().getText() : "")
                .corporateType(responseDTO != null ? responseDTO.getMainActivity().getText() : "")
                .build();
    }

    private String formatCNPJ(String cnpj) {
        return cnpj.replace(".", "").replace("/", "").replace("-", "").replace(" ", "");
    }
}
