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
import java.util.Date;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "TB_QWERTY")
public class Qwerty {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_anexo", nullable = false)
    private Integer id;

    @Column(name = "conteudo")
    private String content;

    @Column(name = "nome")
    private String name;

    @Column(name = "data_entrada")
    private Date date;
}
