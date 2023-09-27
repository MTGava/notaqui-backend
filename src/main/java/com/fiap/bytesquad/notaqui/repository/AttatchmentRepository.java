package com.fiap.bytesquad.notaqui.repository;

import com.fiap.bytesquad.notaqui.model.Attatchment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AttatchmentRepository extends JpaRepository<Attatchment, Integer> {
}
