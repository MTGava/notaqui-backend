package com.fiap.bytesquad.notaqui.service.impl;

import com.fiap.bytesquad.notaqui.model.Attatchment;
import com.fiap.bytesquad.notaqui.model.Bill;
import com.fiap.bytesquad.notaqui.model.dto.AttatchmentDTO;
import com.fiap.bytesquad.notaqui.model.dto.BillDTO;
import com.fiap.bytesquad.notaqui.model.dto.CompanyDTO;
import com.fiap.bytesquad.notaqui.repository.AttatchmentRepository;
import com.fiap.bytesquad.notaqui.repository.BillRepository;
import com.fiap.bytesquad.notaqui.service.AttatchmentService;
import com.fiap.bytesquad.notaqui.service.BillService;
import com.fiap.bytesquad.notaqui.service.CompanyService;
import com.fiap.bytesquad.notaqui.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class AttatchmentServiceImpl implements AttatchmentService {

    @Autowired private AttatchmentRepository repository;

    @Override
    public void save(Integer billId, AttatchmentDTO dto) {
        log.info("|| Iniciando attachmentService - cadastrar anexo");
        Attatchment attatchment = newAttatchment(billId, dto);
        repository.save(attatchment);
    }

    private Attatchment newAttatchment(Integer billId, AttatchmentDTO dto) {
        Attatchment attatchment = new Attatchment();
        attatchment.setBillId(billId);
        attatchment.setName(dto.getName());
        attatchment.setExtension(dto.getExtension());
        attatchment.setArchive(dto.getArchive());
        return attatchment;
    }
}
