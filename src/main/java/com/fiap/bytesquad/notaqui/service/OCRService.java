package com.fiap.bytesquad.notaqui.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fiap.bytesquad.notaqui.model.dto.request.OCRRequestDTO;
import com.fiap.bytesquad.notaqui.model.dto.response.OCRResponseDTO;
import com.fiap.bytesquad.notaqui.model.dto.response.cnpja.CNPJResponseDTO;

public interface OCRService {

    OCRResponseDTO identify(OCRRequestDTO dto) throws JsonProcessingException;
}
