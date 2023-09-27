package com.fiap.bytesquad.notaqui.repository;

import com.fiap.bytesquad.notaqui.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, String> {
}
