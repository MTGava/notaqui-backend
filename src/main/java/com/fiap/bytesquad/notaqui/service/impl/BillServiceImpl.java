package com.fiap.bytesquad.notaqui.service.impl;

import com.fiap.bytesquad.notaqui.model.Bill;
import com.fiap.bytesquad.notaqui.model.dto.BillDTO;
import com.fiap.bytesquad.notaqui.repository.BillRepository;
import com.fiap.bytesquad.notaqui.service.BillService;
import com.fiap.bytesquad.notaqui.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class BillServiceImpl implements BillService {

    @Autowired private BillRepository repository;
    @Autowired private UserService userService;

    @Override
    public BillDTO save(BillDTO dto) {
        log.info("|| Iniciando billService - cadastrar compra");
        Bill bill = newBill(dto);
        bill = repository.save(bill);
        return new BillDTO(bill);
    }

    private Bill newBill(BillDTO dto) {
        Bill bill = new Bill();
        bill.setCnpj(dto.getCnpj());
        bill.setValue(dto.getValue());
        bill.setAccessKey(dto.getAccessKey());
        bill.setArchive(dto.getAttatchment() != null ? dto.getAttatchment().getArchive() : null);
        bill.setExtension(dto.getAttatchment() != null ? dto.getAttatchment().getExtension() : null);
        bill.setUser(userService.findByLogin(dto.getLogin()));
        return bill;
    }

    @Override
    public List<BillDTO> findAll() {
        log.info("|| Iniciando billService - buscar todas as despesas");
        List<Bill> bills = repository.findAll();
        List<BillDTO> dtoList = new ArrayList<>();
        bills.forEach(bill -> dtoList.add(new BillDTO(bill)));
        return dtoList;
    }
}
