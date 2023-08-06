package com.fiap.bytesquad.notaqui.controller;

import com.fiap.bytesquad.notaqui.model.dto.UserDTO;
import com.fiap.bytesquad.notaqui.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/usuarios")
@Slf4j
public class UserController {

    @Autowired private UserService service;

    @PostMapping("/cadastrar")
    public ResponseEntity<UserDTO> save(@RequestBody @Valid UserDTO userDTO) {
        log.info("|| Iniciando userController - cadastrar usuário");
        return new ResponseEntity<>(service.save(userDTO), HttpStatus.CREATED);
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<UserDTO>> findAll() {
        log.info("|| Iniciando controller - buscar todos os usuários");
        return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
    }

    @GetMapping("/buscar/{login}")
    public ResponseEntity<UserDTO> findByLogin(@PathVariable String login) {
        log.info("|| Iniciando controller - buscar por matrícula");
        return new ResponseEntity<>(new UserDTO(service.findByLogin(login.toLowerCase())), HttpStatus.OK);
    }
}
