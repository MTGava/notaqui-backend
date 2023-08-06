package com.fiap.bytesquad.notaqui.service.impl;

import com.fiap.bytesquad.notaqui.model.dto.request.OCRRequestDTO;
import com.fiap.bytesquad.notaqui.model.dto.response.OCRResponseDTO;
import com.fiap.bytesquad.notaqui.service.OCRService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class OCRServiceImpl implements OCRService {
    @Override
    public OCRResponseDTO identify(OCRRequestDTO dto) {
        return OCRResponseDTO.builder().content(dto.getContent()).build();
    }
}
