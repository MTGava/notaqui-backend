package com.fiap.bytesquad.notaqui.service;

import com.fiap.bytesquad.notaqui.model.dto.BillDTO;

import java.util.List;

public interface BillService {

    BillDTO save(BillDTO dto);
    List<BillDTO> findAll();
}
