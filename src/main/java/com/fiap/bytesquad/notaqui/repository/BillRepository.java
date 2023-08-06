package com.fiap.bytesquad.notaqui.repository;

import com.fiap.bytesquad.notaqui.model.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BillRepository extends JpaRepository<Bill, Integer> {
}
