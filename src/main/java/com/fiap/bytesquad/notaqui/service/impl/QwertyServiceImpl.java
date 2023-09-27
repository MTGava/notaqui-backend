package com.fiap.bytesquad.notaqui.service.impl;

import com.fiap.bytesquad.notaqui.exceptions.ObjectNotFoundException;
import com.fiap.bytesquad.notaqui.model.Qwerty;
import com.fiap.bytesquad.notaqui.model.dto.QwertyDTO;
import com.fiap.bytesquad.notaqui.repository.QwertyRepository;
import com.fiap.bytesquad.notaqui.service.QwertyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class QwertyServiceImpl implements QwertyService {

    @Autowired private QwertyRepository repository;

    @Override
    public QwertyDTO save(QwertyDTO dto) {
        Qwerty qwerty = newQwerty(dto);
        qwerty = repository.save(qwerty);
        return new QwertyDTO(qwerty);
    }

    @Override
    public Qwerty findById(Integer id) {
        Optional<Qwerty> qwerty = repository.findById(id);
        return qwerty.orElseThrow(() -> new ObjectNotFoundException("Anexo: " + id + " não encontrado!"));
    }

    @Override
    public List<Integer> findAllIds() {
        List<Integer> ids = new ArrayList<>();
        repository.findAll().forEach(qwerty -> ids.add(qwerty.getId()));
        return ids;
    }

    private Qwerty newQwerty(QwertyDTO dto) {
        Qwerty qwerty = new Qwerty();
        qwerty.setContent(dto.getContent());
        qwerty.setName(dto.getName());
        qwerty.setDate(new Date(System.currentTimeMillis()));
        return qwerty;
    }
}
