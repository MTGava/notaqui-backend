package com.fiap.bytesquad.notaqui.repository;

import com.fiap.bytesquad.notaqui.model.Qwerty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QwertyRepository extends JpaRepository<Qwerty, Integer> {
}
