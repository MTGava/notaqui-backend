package com.fiap.bytesquad.notaqui.service;

import com.fiap.bytesquad.notaqui.model.Qwerty;
import com.fiap.bytesquad.notaqui.model.dto.QwertyDTO;

import java.util.List;


public interface QwertyService {

    QwertyDTO save(QwertyDTO dto);

    Qwerty findById(Integer id);

    List<Integer> findAllIds();
}
