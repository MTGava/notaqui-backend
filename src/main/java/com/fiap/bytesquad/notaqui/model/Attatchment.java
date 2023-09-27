package com.fiap.bytesquad.notaqui.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "TB_ANEXOS")
public class Attatchment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_anexo", nullable = false)
    private Integer id;

    @Column(name = "id_conta", nullable = false)
    private Integer billId;

    @Column(name = "nome")
    private String name;

    @Column(name = "arquivo")
    private String archive;

    @Column(name = "extensao")
    private String extension;
}
