package com.fiap.bytesquad.notaqui.service.impl;

import com.fiap.bytesquad.notaqui.model.Bill;
import com.fiap.bytesquad.notaqui.model.dto.BillDTO;
import com.fiap.bytesquad.notaqui.repository.NotaquiRepository;
import com.fiap.bytesquad.notaqui.service.NotaquiService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class NotaquiServiceImpl implements NotaquiService {

    @Autowired private NotaquiRepository repository;

    @Override
    public BillDTO saveBill(BillDTO billDTO) {
        log.info("|| Start service - NotaquiServiceImpl");
        Bill bill = new Bill(billDTO);
        repository.save(bill);
        log.info("|| Save into BD");
        return new BillDTO(repository.save(bill));
    }
}
