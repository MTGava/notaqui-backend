package com.fiap.bytesquad.notaqui.service.impl;

import com.fiap.bytesquad.notaqui.model.dto.response.cnpja.CNPJResponseDTO;
import com.fiap.bytesquad.notaqui.model.dto.response.cnpja.CNPJaResponseDTO;
import com.fiap.bytesquad.notaqui.service.CNPJService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
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
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", token);

        ResponseEntity<CNPJaResponseDTO> response = restTemplate.getForEntity(url.concat(cnpj), CNPJaResponseDTO.class, headers);

        CNPJaResponseDTO responseDTO = response.getBody();
        CNPJResponseDTO responseDTO1 = CNPJResponseDTO.builder()
                .cnpj(cnpj)
                .corporateName(responseDTO != null ? responseDTO.getCompany().getName() : "")
                .legalNature(responseDTO != null ? responseDTO.getCompany().getNature().getText() : "")
                .corporateType(responseDTO != null ? responseDTO.getMainActivity().getText() : "")
                .build();
        log.info("\n\n\n\n{}\n\n\n", responseDTO1);
        return responseDTO1;
    }
}
