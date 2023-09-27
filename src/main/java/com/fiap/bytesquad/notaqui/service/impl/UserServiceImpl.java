package com.fiap.bytesquad.notaqui.service.impl;

import com.fiap.bytesquad.notaqui.exceptions.ObjectNotFoundException;
import com.fiap.bytesquad.notaqui.model.User;
import com.fiap.bytesquad.notaqui.model.dto.UserDTO;
import com.fiap.bytesquad.notaqui.repository.UserRepository;
import com.fiap.bytesquad.notaqui.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired private UserRepository repository;

    @Override
    public UserDTO save(UserDTO dto) {
        log.info("|| Iniciando userService - cadastrar usuário");
        User user = newUser(dto);
        user = repository.save(user);
        return new UserDTO(user);
    }

    @Override
    public List<UserDTO> findAll() {
        log.info("|| Iniciando userService - buscar todos os usuários");
        List<User> users = repository.findAll();
        List<UserDTO> dtoList = new ArrayList<>();
        users.forEach(user -> dtoList.add(new UserDTO(user)));
        return dtoList;
    }

    @Override
    public User findByLogin(String login) {
        log.info("|| Iniciando userService - buscar usuário por matrícula");
        Optional<User> user = repository.findById(login);
        return user.orElseThrow(() -> new ObjectNotFoundException("Usuário com a matrícula: " + login + " não encontrado!"));
    }

    private User newUser(UserDTO dto) {
        User user = new User();
        user.setLogin(dto.getLogin().toLowerCase());
        user.setName(dto.getName());
        user.setRole(dto.getRole().toLowerCase());
        return user;
    }
}
