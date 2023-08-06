package com.fiap.bytesquad.notaqui.service.impl;

import com.fiap.bytesquad.notaqui.model.Bill;
import com.fiap.bytesquad.notaqui.model.dto.BillDTO;
import com.fiap.bytesquad.notaqui.repository.BillRepository;
import com.fiap.bytesquad.notaqui.repository.NotaquiRepository;
import com.fiap.bytesquad.notaqui.repository.UserRepository;
import com.fiap.bytesquad.notaqui.service.BillService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class BillServiceImpl implements BillService {

    @Autowired private NotaquiRepository repository;
    @Autowired private BillRepository billRepository;
    @Autowired private UserRepository userRepository;

    @Override
    public BillDTO saveBill(BillDTO billDTO) {
        log.info("|| Start service - NotaquiServiceImpl");
        Bill bill = new Bill(billDTO);
        repository.save(bill);
        log.info("|| Save into BD");
        return new BillDTO();
    }

    private Bill newBill(BillDTO dto) {
        Bill bill = new Bill();
        bill.setCnpj(dto.getCnpj());
        bill.setValue(dto.getValue());
        bill.setAccessKey(dto.getAccessKey());
        bill.setArchive(dto.getAttatchment() != null ? dto.getAttatchment().getArchive() : null);
        bill.setExtension(dto.getAttatchment() != null ? dto.getAttatchment().getExtension() : null);
//        bill.setUser();
        return bill;
    }
}
