package com.fiap.bytesquad.notaqui.service;

import com.fiap.bytesquad.notaqui.model.dto.AttatchmentDTO;

public interface AttatchmentService {

    void save(Integer billId, AttatchmentDTO dto);
}
