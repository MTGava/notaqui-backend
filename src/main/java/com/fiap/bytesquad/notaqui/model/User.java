package com.fiap.bytesquad.notaqui.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "TB_USUARIOS")
public class User {

    @Id
    @Column(name = "matricula")
    private String login;

    @Column(name = "nome")
    private String name;

    @Column(name = "cargo")
    private String role;

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<Bill> bills = new ArrayList<>();
}
