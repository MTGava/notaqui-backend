package com.fiap.bytesquad.notaqui.repository;

import com.fiap.bytesquad.notaqui.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
}
