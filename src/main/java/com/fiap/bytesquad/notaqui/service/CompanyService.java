
package com.fiap.bytesquad.notaqui.service;

import com.fiap.bytesquad.notaqui.model.Company;
import com.fiap.bytesquad.notaqui.model.dto.CompanyDTO;

import java.util.List;

public interface CompanyService {

    CompanyDTO save(CompanyDTO dto);

    List<CompanyDTO> findAll();

    Company findByCnpj(String cnpj);
}
