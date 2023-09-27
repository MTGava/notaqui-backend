package com.fiap.bytesquad.notaqui.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fiap.bytesquad.notaqui.model.Bill;
import com.fiap.bytesquad.notaqui.model.Company;
import com.fiap.bytesquad.notaqui.model.dto.AttatchmentDTO;
import com.fiap.bytesquad.notaqui.model.dto.BillDTO;
import com.fiap.bytesquad.notaqui.model.dto.CompanyDTO;
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
public class BillServiceImpl implements BillService {

    @Autowired private BillRepository repository;
    @Autowired private UserService userService;

    @Autowired private AttatchmentService attatchmentService;

    @Autowired private CompanyService companyService;

    @Override
    public BillDTO save(BillDTO dto) {
        log.info("|| Iniciando billService - cadastrar compra");
        Bill bill = newBill(dto);
        bill = repository.save(bill);

        log.info("|| Salvando anexo: {}...", dto.getAttatchment().getName());
        attatchmentService.save(bill.getId(), dto.getAttatchment());
        log.info("|| Anexo: {} salvo...", dto.getAttatchment().getName());

        return new BillDTO(bill);
    }

    private Bill newBill(BillDTO dto) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date registryDate = new Date(System.currentTimeMillis());
        try {
            registryDate = simpleDateFormat.parse(dto.getDate());
        } catch (Exception e) {
            log.info("|| Não foi possível parsear a data, gravando como data local.");
        }

        log.info("|| Cadastrando empresa: {}...", dto.getInfoPj().getCorporateType());
        CompanyDTO company = companyService.save(dto.getInfoPj());

        Bill bill = new Bill();
        bill.setCompany(companyService.findByCnpj(company.getCnpj()));
        bill.setTitle(dto.getTitle());
        bill.setValue(dto.getValue());
        bill.setUser(userService.findByLogin(dto.getLogin()));
        bill.setDate(registryDate);
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
