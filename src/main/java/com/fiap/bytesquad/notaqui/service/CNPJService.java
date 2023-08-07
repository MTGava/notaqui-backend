package com.fiap.bytesquad.notaqui.service;

import com.fiap.bytesquad.notaqui.model.dto.response.cnpja.CNPJResponseDTO;

public interface CNPJService {

    CNPJResponseDTO consult(String cnpj);
}
