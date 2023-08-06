package com.fiap.bytesquad.notaqui.service;

import com.fiap.bytesquad.notaqui.model.dto.request.OCRRequestDTO;
import com.fiap.bytesquad.notaqui.model.dto.response.OCRResponseDTO;

public interface OCRService {

    OCRResponseDTO identify(OCRRequestDTO dto);
}
