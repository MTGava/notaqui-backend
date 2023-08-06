package com.fiap.bytesquad.notaqui.service;

import com.fiap.bytesquad.notaqui.model.User;
import com.fiap.bytesquad.notaqui.model.dto.UserDTO;

import java.util.List;

public interface UserService {

    UserDTO save(UserDTO dto);

    List<UserDTO> findAll();

    User findByLogin(String login);
}
